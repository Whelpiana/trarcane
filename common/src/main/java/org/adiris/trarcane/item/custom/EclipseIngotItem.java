package org.adiris.trarcane.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class EclipseIngotItem extends Item {

    public EclipseIngotItem(Properties properties) {
        super(properties);
    }

    public Component getName(ItemStack stack) {
        return Component.literal("Eclipse Ingot")
                .withStyle(ChatFormatting.GRAY);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("Contains opposing magical properties in perfect equilibrium.").withStyle(ChatFormatting.WHITE));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}