package org.adiris.trarcane.ability.skill.extra;

import io.github.manasmods.manascore.skill.api.ManasSkillInstance;
import io.github.manasmods.tensura.ability.skill.Skill;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

public class MeasureSkill extends Skill {

    public MeasureSkill() {
        super(SkillType.EXTRA);
    }

    @Override
    public boolean checkAcquiringRequirement(Player player, double newEP) {
        return newEP >= 50000;
    }


    @Override
    public @Nullable ResourceLocation getSkillIcon() {
        return ResourceLocation.fromNamespaceAndPath(
                "trarcane",
                "textures/extra/measure.png"
        );
    }

    @Override
    public void onPressed(ManasSkillInstance instance, LivingEntity entity, int keyNumber, int mode) {

        if (!(entity instanceof ServerPlayer player)) {
            return;
        }

        HitResult hit = player.pick(50.0D, 0.0F, false);

        if (!(hit instanceof BlockHitResult blockHit)) {
            player.displayClientMessage(
                    Component.literal("Look at a block!")
                            .withStyle(ChatFormatting.RED),
                    true
            );
            return;
        }

        BlockPos pos = blockHit.getBlockPos();
        CompoundTag tag = instance.getOrCreateTag();

        if (player.isShiftKeyDown()) {

            tag.putInt("Pos1X", pos.getX());
            tag.putInt("Pos1Y", pos.getY());
            tag.putInt("Pos1Z", pos.getZ());

            player.displayClientMessage(
                    Component.literal(
                            "Position 1 set to [" +
                                    pos.getX() + ", " +
                                    pos.getY() + ", " +
                                    pos.getZ() + "]"
                    ).withStyle(ChatFormatting.GREEN),
                    true
            );

            instance.markDirty();
            return;
        }

        if (!tag.contains("Pos1X")) {
            player.displayClientMessage(
                    Component.literal("Set Position 1 first! (Shift + Skill)")
                            .withStyle(ChatFormatting.RED),
                    true
            );
            return;
        }

        tag.putInt("Pos2X", pos.getX());
        tag.putInt("Pos2Y", pos.getY());
        tag.putInt("Pos2Z", pos.getZ());

        BlockPos pos1 = new BlockPos(
                tag.getInt("Pos1X"),
                tag.getInt("Pos1Y"),
                tag.getInt("Pos1Z")
        );

        BlockPos pos2 = new BlockPos(
                tag.getInt("Pos2X"),
                tag.getInt("Pos2Y"),
                tag.getInt("Pos2Z")
        );

        int dx = Math.abs(pos2.getX() - pos1.getX());
        int dy = Math.abs(pos2.getY() - pos1.getY());
        int dz = Math.abs(pos2.getZ() - pos1.getZ());

        int distance = Math.max(dx, Math.max(dy, dz)) + 1;

        player.sendSystemMessage(
                Component.literal("========== Measure ==========")
                        .withStyle(ChatFormatting.AQUA)
        );

        player.sendSystemMessage(
                Component.literal(
                        "Length: " + distance + " blocks"
                ).withStyle(ChatFormatting.WHITE)
        );

        instance.markDirty();
    }
}