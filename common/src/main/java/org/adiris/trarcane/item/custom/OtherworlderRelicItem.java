package org.adiris.trarcane.item.custom;

import io.github.manasmods.manascore.skill.api.ManasSkill;
import io.github.manasmods.manascore.skill.api.SkillAPI;
import io.github.manasmods.tensura.ability.SkillHelper;
import io.github.manasmods.tensura.ability.TensuraSkillInstance;
import io.github.manasmods.tensura.registry.skill.UniqueSkills;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class OtherworlderRelicItem extends Item {
    public OtherworlderRelicItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, net.minecraft.world.item.TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.literal("An item lost to time containing the soul of an Otherworlder. Use to obtain an Otherworlder Unique Skill.")
                .withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.literal("Right-click to awaken one random Otherworlder Unique skill.")
                .withStyle(ChatFormatting.DARK_PURPLE, ChatFormatting.ITALIC));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide()) {
            List<ManasSkill> unlearnedSkills = new ArrayList<>();
            List<ManasSkill> relicSkills = List.of(
                    UniqueSkills.BERSERKER.get(),
                    UniqueSkills.SURVIVOR.get(),
                    UniqueSkills.SPEARHEAD.get(),
                    UniqueSkills.BEWILDER.get(),
                    UniqueSkills.HEALER.get(),
                    UniqueSkills.SEVERER.get(),
                    UniqueSkills.TRAVELER.get()
            );
            var learnedSkills = SkillAPI.getSkillsFrom(player);
            for (ManasSkill relicSkill : relicSkills) {
                if (learnedSkills.getSkill(relicSkill).isEmpty()) {
                    unlearnedSkills.add(relicSkill);
                }
            }

            if (unlearnedSkills.isEmpty()) {
                player.sendSystemMessage(Component.literal("The relic has no remaining skills to reveal.")
                        .withStyle(ChatFormatting.RED));
                return InteractionResultHolder.fail(stack);
            }

            ManasSkill skill = unlearnedSkills.get(level.random.nextInt(unlearnedSkills.size()));
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
