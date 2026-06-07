package org.adiris.trarcane.item.custom;

import io.github.manasmods.manascore.skill.api.ManasSkill;
import io.github.manasmods.manascore.skill.api.SkillAPI;
import io.github.manasmods.tensura.ability.SkillHelper;
import io.github.manasmods.tensura.ability.TensuraSkillInstance;
import io.github.manasmods.tensura.registry.magic.SpiritualMagics;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import static org.adiris.trarcane.Trarcane.MOD_ID;


public class LichBookItem extends Item {

    public LichBookItem(Properties properties) {
        super(properties);
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.literal("Necromancy")
                .withStyle(ChatFormatting.DARK_RED);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide()) {


            ManasSkill necroLord = SkillAPI.getSkillRegistry().get(ResourceLocation.fromNamespaceAndPath(MOD_ID, "necro_lord"));


            if (necroLord == null || SkillAPI.getSkillsFrom(player).getSkill(necroLord).isEmpty()) {
                player.sendSystemMessage(Component.literal("You lack the Necro Lord skill to comprehend this ancient tome.")
                        .withStyle(ChatFormatting.RED));


                return InteractionResultHolder.fail(stack);
            }


            player.sendSystemMessage(Component.literal("The secrets of Necromancy flow into your mind...")
                    .withStyle(ChatFormatting.DARK_PURPLE));

            learn(player, SpiritualMagics.CREATE_LESSER_UNDEAD.get());
            learn(player, SpiritualMagics.CREATE_GREATER_UNDEAD.get());
            learn(player, SpiritualMagics.CURSE.get());
            learn(player, SpiritualMagics.CURSE_BIND.get());


            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }


        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    private void learn(Player player, ManasSkill skill) {
        TensuraSkillInstance instance = new TensuraSkillInstance(skill);
        instance.getOrCreateTag().putBoolean("NoMagiculeCost", true);
        SkillHelper.learnSkill(player, instance);
    }
}