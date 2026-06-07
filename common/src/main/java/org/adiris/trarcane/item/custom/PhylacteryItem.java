package org.adiris.trarcane.item.custom;

import io.github.manasmods.tensura.item.tool.MultitoolItem;
import io.github.manasmods.tensura.registry.item.misc.TensuraDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public class PhylacteryItem extends MultitoolItem {

    public PhylacteryItem(Properties properties) {
        super(Tiers.WOOD, BlockTags.LEAVES, properties, MultitoolItem.createAttributes(0, -2.4F, 0.0D, 0.0D));
    }


    public static void setOwner(ItemStack stack, UUID owner) {
        CustomData existing = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        CompoundTag tag = existing.copyTag();
        tag.putUUID("owner_uuid", owner);
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }

    @Override
    public @NotNull Component getName(ItemStack stack) {
        return Component.literal("Phylactery").withStyle(ChatFormatting.DARK_PURPLE);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return 1.0F;
    }

    public static UUID getOwner(ItemStack stack) {

        CustomData data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        CompoundTag tag = data.copyTag();


        if (tag.contains("owner_uuid")) {
            return tag.getUUID("owner_uuid");
        }
        return null;
    }

    @Override
    public boolean canAttackBlock(BlockState state, net.minecraft.world.level.Level level, net.minecraft.core.BlockPos pos, net.minecraft.world.entity.player.Player player) {
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);


        CustomData data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        CompoundTag tag = data.copyTag();

        if (tag.contains("owner_uuid")) {
            UUID ownerId = tag.getUUID("owner_uuid");
            String ownerName = "Unknown Soul";

            Minecraft mc = Minecraft.getInstance();
            if (mc.level != null) {
                var player = mc.level.getPlayerByUUID(ownerId);
                ownerName = (player != null) ? player.getName().getString() : "Offline Player";
            }

            tooltip.add(Component.literal("Bound to: ")
                    .append(Component.literal(ownerName).withStyle(ChatFormatting.WHITE))
                    .withStyle(ChatFormatting.GRAY));
        }


        var epComponent = TensuraDataComponents.EP_DURABILITY.get();
        if (stack.has(epComponent)) {
            double currentEp = stack.get(epComponent);
            ChatFormatting color = currentEp >= 500000.0 ? ChatFormatting.GREEN : ChatFormatting.GRAY;
            tooltip.add(Component.literal("Soul Energy: " + (int)currentEp + " / 500000").withStyle(color));
        } else {
            tooltip.add(Component.literal("Soul Energy: 0 / 10000").withStyle(ChatFormatting.GRAY));
        }

        tooltip.add(Component.literal("Harvest souls to restore life.")
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
    }
}