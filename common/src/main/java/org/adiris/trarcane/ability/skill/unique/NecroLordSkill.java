package org.adiris.trarcane.ability.skill.unique;

import io.github.manasmods.manascore.skill.api.ManasSkillInstance;
import io.github.manasmods.tensura.ability.skill.Skill;
import io.github.manasmods.tensura.particle.TensuraParticleHelper;
import io.github.manasmods.tensura.race.RaceUtils;
import io.github.manasmods.tensura.registry.item.misc.TensuraDataComponents;
import io.github.manasmods.tensura.registry.particle.TensuraParticleTypes;
import io.github.manasmods.tensura.util.EnergyHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.adiris.trarcane.item.custom.PhylacteryItem;
import org.adiris.trarcane.registry.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class NecroLordSkill extends Skill {

    public NecroLordSkill() {
        super(SkillType.UNIQUE);
    }

    @Override
    public double getDefaultAcquiringMagiculeCost() {
        return 75000F;
    }

    @Override
    public double getMagiculeCost(LivingEntity entity, ManasSkillInstance instance, int mode) {
        return switch (mode) {
            case 0 -> 50000.0;
            case 1 -> 500000.0;
            default -> 0.0;
        };
    }

    @Override
    protected boolean canActivateInRaceLimit(ManasSkillInstance instance, int mode) {
        return mode == 2;
    }

    @Override
    public int getModes(ManasSkillInstance instance) {
        return 3;
    }

    @Override
    public int nextMode(LivingEntity entity, ManasSkillInstance instance, int mode, boolean reverse) {
        return reverse ? (mode == 0 ? 2 : mode - 1) : (mode == 2 ? 0 : mode + 1);
    }

    @Override
    public String getModeId(ManasSkillInstance instance, int mode) {
        return switch (mode) {
            case 0 -> "necrolorddefault";
            case 1 -> "createphylactery";
            case 2 -> "necrolordwraithform";
            default -> super.getModeId(instance, mode);
        };
    }

    @Override
    public @Nullable ResourceLocation getSkillIcon() {
        return ResourceLocation.fromNamespaceAndPath("trarcane", "textures/uniques/necro_lord.png");
    }

    @Override
    public void onPressed(ManasSkillInstance instance, LivingEntity entity, int keyNumber, int mode) {
        if (!(entity instanceof Player player) || player.level().isClientSide()) return;

        switch (mode) {
            case 0 -> {
                if (EnergyHelper.isOutOfEnergy(entity, instance, mode)) {
                    return;
                }

                ItemStack book = new ItemStack(ModItems.LICH_BOOK.get());
                if (!player.getInventory().add(book)) {
                    player.drop(book, false);
                }
            }

            case 1 -> {
                if (EnergyHelper.isOutOfEnergy(entity, instance, mode)) {
                    return;
                }

                ItemStack phylactery = new ItemStack(ModItems.PHYLACTERY.get());
                PhylacteryItem.setOwner(phylactery, player.getUUID());

                if (!player.getInventory().add(phylactery)) player.drop(phylactery, false);

                player.level().playSound(null, player.blockPosition(), SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
                player.sendSystemMessage(Component.literal("Phylactery forged. Bound to your soul.").withStyle(ChatFormatting.DARK_PURPLE));
            }

            case 2 -> {
                if (instance.isToggled()) {
                    instance.setToggled(false);
                    if (!RaceUtils.canStillFly(player, true, false, true)) {
                        player.getAbilities().mayfly = false;
                        player.getAbilities().flying = false;
                        player.onUpdateAbilities();
                    }
                } else {
                    instance.setToggled(true);
                    player.getAbilities().mayfly = true;
                    player.onUpdateAbilities();
                }
            }
        }
    }

    @Override
    public boolean onDeath(ManasSkillInstance instance, LivingEntity entity, DamageSource source) {
        if (entity instanceof Player player && !player.level().isClientSide()) {
            for (ItemStack stack : player.getInventory().items) {
                if (stack.getItem() instanceof PhylacteryItem) {
                    UUID storedOwner = PhylacteryItem.getOwner(stack);

                    if (storedOwner != null) {
                        if (storedOwner.equals(player.getUUID())) {
                            double currentEp = stack.getOrDefault(TensuraDataComponents.EP_DURABILITY.get(), 0.0);
                            if (currentEp >= 250000.0) {
                                stack.shrink(1);
                                player.setHealth(player.getMaxHealth());
                                player.removeAllEffects();
                                player.level().playSound(null, player.blockPosition(), SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
                                player.sendSystemMessage(Component.literal("Soul resurrected via Phylactery!").withStyle(ChatFormatting.DARK_GREEN));
                                return false;
                            }
                        } else {
                            stack.setCount(0);
                            player.sendSystemMessage(Component.literal("An unauthorized phylactery was destroyed!").withStyle(ChatFormatting.RED));
                        }
                    }
                }
            }
        }
        return super.onDeath(instance, entity, source);
    }

    @Override
    public void onRespawn(ManasSkillInstance instance, ServerPlayer owner, boolean conqueredEnd) {
        if (instance.isToggled()) {
            owner.getAbilities().mayfly = true;
            owner.onUpdateAbilities();
        }
    }

    @Override
    public void onTick(ManasSkillInstance instance, LivingEntity entity) {
        if (entity.tickCount % 4 == 0) {
            TensuraParticleHelper.addParticlesAroundSelf(entity, TensuraParticleTypes.SOUL.get(), 1.0D);
        }
    }

    @Override
    public boolean canTick(ManasSkillInstance instance, LivingEntity entity) {
        return instance.isToggled();
    }

    @Override
    public boolean canBeToggled(ManasSkillInstance instance, LivingEntity entity) {
        return true;
    }
}