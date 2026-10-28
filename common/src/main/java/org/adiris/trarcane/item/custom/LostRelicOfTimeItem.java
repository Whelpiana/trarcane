package org.adiris.trarcane.item.custom;

import dev.architectury.registry.registries.Registrar;
import io.github.manasmods.manascore.config.ConfigRegistry;
import io.github.manasmods.manascore.skill.api.ManasSkill;
import io.github.manasmods.manascore.skill.api.SkillAPI;
import io.github.manasmods.manascore.skill.impl.SkillRegistry;
import io.github.manasmods.tensura.ability.SkillHelper;
import io.github.manasmods.tensura.ability.TensuraSkillInstance;
import io.github.manasmods.tensura.config.ReincarnationConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class LostRelicOfTimeItem extends Item {
    public LostRelicOfTimeItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, net.minecraft.world.item.TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.literal("A relic lost to time. Its fractured core remembers the gifts of otherworlders.")
                .withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.literal("Right-click to awaken one random Unique skill.")
                .withStyle(ChatFormatting.DARK_PURPLE, ChatFormatting.ITALIC));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide()) {
            ReincarnationConfig config = ConfigRegistry.getConfig(ReincarnationConfig.class);
            List<String> configuredSkills = config == null || config.Skills == null
                    ? List.of()
                    : config.Skills.startingSkills;
            Registrar<ManasSkill> skills = SkillRegistry.SKILLS;

            List<ManasSkill> candidates = new ArrayList<>();
            var learnedSkills = SkillAPI.getSkillsFrom(player);
            if (configuredSkills != null) {
                for (String entry : configuredSkills) {
                    ResourceLocation id = ResourceLocation.tryParse(entry);
                    if (id == null) continue;
                    ManasSkill skill = skills.get(id);
                    if (skill != null && learnedSkills.getSkill(skill).isEmpty()) candidates.add(skill);
                }
            }

            if (candidates.isEmpty()) {
                player.sendSystemMessage(Component.literal("The relic has no remaining skills to reveal.")
                        .withStyle(ChatFormatting.RED));
                return InteractionResultHolder.fail(stack);
            }

            ManasSkill skill = candidates.get(level.random.nextInt(candidates.size()));
            SkillHelper.learnSkill(player, new TensuraSkillInstance(skill));
            player.sendSystemMessage(Component.literal("The relic stirs, revealing a skill from another life: ")
                    .append(skill.getName().copy().withStyle(ChatFormatting.GOLD))
                    .append(Component.literal(".")));
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}
