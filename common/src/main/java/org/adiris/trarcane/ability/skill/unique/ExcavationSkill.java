package org.adiris.trarcane.ability.skill.unique;

import io.github.manasmods.manascore.config.ConfigRegistry;
import io.github.manasmods.manascore.skill.api.ManasSkill;
import io.github.manasmods.manascore.skill.api.ManasSkillInstance;
import io.github.manasmods.manascore.skill.api.SkillAPI;
import io.github.manasmods.tensura.ability.SkillHelper;
import io.github.manasmods.tensura.ability.skill.Skill;
import io.github.manasmods.tensura.util.EnergyHelper;
import io.github.manasmods.tensura.ability.TensuraSkillInstance;
import io.github.manasmods.tensura.registry.sound.TensuraSoundEvents;
import io.github.manasmods.tensura.storage.TensuraStorages;
import io.github.manasmods.tensura.storage.ep.IExistence;
import io.github.manasmods.tensura.util.AttributeHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.adiris.trarcane.config.skill.ExcavationRewardPoolConfig;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ExcavationSkill extends Skill {
    private static final String TIER_TAG = "ExcavationTier";
    private static final String LEGENDARY_EXCAVATIONS_TAG = "LegendaryExcavations";

    public ExcavationSkill() { super(SkillType.UNIQUE); }

    @Override public double getDefaultAcquiringMagiculeCost() { return 75_000; }

    @Override public int getModes(ManasSkillInstance instance) { return 2; }

    @Override public @Nullable ResourceLocation getSkillIcon() {
        return ResourceLocation.fromNamespaceAndPath("trarcane", "textures/uniques/excavation.png");
    }

    @Override public String getModeId(ManasSkillInstance instance, int mode) {
        return mode == 0 ? "investigatoranalyticalappraisal" : "excavate";
    }

    @Override public int nextMode(LivingEntity entity, ManasSkillInstance instance, int mode, boolean reverse) {
        return mode == 0 ? 1 : 0;
    }

    @Override public double getMagiculeCost(LivingEntity entity, ManasSkillInstance instance, int mode) {
        return mode == 1 ? 100_000 : 0;
    }

    @Override
    public void onPressed(ManasSkillInstance instance, LivingEntity entity, int keyNumber, int mode) {
        if (!(entity instanceof ServerPlayer player)) return;
        if (mode == 0) {
            boolean enabled = !instance.isToggled();
            instance.setToggled(enabled);
            if (enabled) {
                AttributeHelper.addAnalysisAttributes(player, 2, 15);
            } else {
                AttributeHelper.removeAnalysisAttributes(player, true, true, false);
            }
            player.playNotifySound((SoundEvent) TensuraSoundEvents.GENERIC_CAST.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            return;
        }
        excavate(instance, player);
    }

    private void excavate(ManasSkillInstance instance, ServerPlayer player) {
        ExcavationRewardPoolConfig config = (ExcavationRewardPoolConfig) ConfigRegistry.getConfig(ExcavationRewardPoolConfig.class);
        int legendaryExcavationLimit = Math.max(0, config.LegendaryExcavations);
        IExistence existence = TensuraStorages.getExistenceFrom(player);
        if (existence == null) {
            player.displayClientMessage(Component.literal("Not enough magicules to excavate."), true);
            return;
        }
        if (instance.getOrCreateTag().getInt(LEGENDARY_EXCAVATIONS_TAG) >= legendaryExcavationLimit) {
            player.displayClientMessage(Component.literal("You have completed all " + legendaryExcavationLimit + " Legendary excavations."), true);
            return;
        }
        int tier = instance.getOrCreateTag().getInt(TIER_TAG);
        String tierName = switch (tier) { case 1 -> "Rare"; case 2 -> "Epic"; case 3 -> "Legendary"; default -> "Common"; };
        List<String> pool = switch (tier) { case 1 -> config.Rare; case 2 -> config.Epic; case 3 -> config.Legendary; default -> config.Common; };
        Reward reward = choose(pool);
        if (reward == null) {
            player.displayClientMessage(Component.literal("The " + tierName + " excavation reward pool has no valid entries."), true);
            return;
        }
        if (EnergyHelper.isOutOfEnergy(player, instance, 1)) {
            player.displayClientMessage(Component.literal("Not enough magicules to excavate."), true);
            return;
        }
        int count = reward.type.equals("item") ? ThreadLocalRandom.current().nextInt(reward.min, reward.max + 1) : 1;
        Component rewardName;

        if (reward.type.equals("item")) {
            Item item = BuiltInRegistries.ITEM.getOptional(reward.id).orElse(null);
            if (item == null || item == Items.AIR) {
                player.displayClientMessage(Component.literal("Excavation item reward does not exist: " + reward.id).withStyle(ChatFormatting.RED), false);
                return;
            }
            ItemStack stack = new ItemStack(item, count);
            if (!player.getInventory().add(stack)) player.drop(stack, false);
            rewardName = Component.literal(reward.id.toString());
        } else {
            ManasSkill skill = SkillAPI.getSkillRegistry().get(reward.id);
            if (skill == null) {
                player.displayClientMessage(Component.literal("Excavation skill reward does not exist: " + reward.id).withStyle(ChatFormatting.RED), false);
                return;
            }
            SkillHelper.learnSkill(player, new TensuraSkillInstance(skill));
            rewardName = Component.literal(reward.id.toString());
        }
        ChatFormatting rarityColor = switch (tier) {
            case 1 -> ChatFormatting.BLUE;
            case 2 -> ChatFormatting.DARK_PURPLE;
            case 3 -> ChatFormatting.GOLD;
            default -> ChatFormatting.GRAY;
        };
        Component message = Component.empty()
                .append(Component.literal(tierName).withStyle(rarityColor))
                .append(Component.literal(" Excavation: Received " + count + " x "))
                .append(rewardName);
        player.displayClientMessage(message, false);
        if (tier == 3) instance.getOrCreateTag().putInt(LEGENDARY_EXCAVATIONS_TAG, instance.getOrCreateTag().getInt(LEGENDARY_EXCAVATIONS_TAG) + 1);
        instance.getOrCreateTag().putInt(TIER_TAG, tier == 3 ? 0 : tier + 1);
        instance.markDirty();
        instance.setCoolDown(300, 1);
    }

    private Reward choose(List<String> entries) {
        if (entries == null || entries.isEmpty()) return null;
        record Weighted(Reward reward, double weight) {}
        java.util.ArrayList<Weighted> valid = new java.util.ArrayList<>();
        double total = 0;
        for (String entry : entries) {
            try {
                String[] fields = entry.split("\\|");
                String type = fields[0].trim().toLowerCase(java.util.Locale.ROOT);
                ResourceLocation id = ResourceLocation.parse(fields[1].trim());
                int min, max;
                double weight;
                if (type.equals("item") && fields.length == 5) {
                    min = Integer.parseInt(fields[2].trim());
                    max = Integer.parseInt(fields[3].trim());
                    weight = Double.parseDouble(fields[4].trim());
                    if (BuiltInRegistries.ITEM.getOptional(id).filter(item -> item != Items.AIR).isEmpty() || min < 1 || max < min) continue;
                } else if (type.equals("skill") && fields.length == 3) {
                    min = max = 1;
                    weight = Double.parseDouble(fields[2].trim());
                } else continue;
                if (weight <= 0) continue;
                total += weight;
                valid.add(new Weighted(new Reward(type, id, min, max), weight));
            } catch (RuntimeException ignored) { }
        }
        if (valid.isEmpty()) return null;
        double roll = ThreadLocalRandom.current().nextDouble(total);
        for (Weighted candidate : valid) {
            roll -= candidate.weight;
            if (roll < 0) return candidate.reward;
        }
        return valid.get(valid.size() - 1).reward;
    }

    private record Reward(String type, ResourceLocation id, int min, int max) {}
}
