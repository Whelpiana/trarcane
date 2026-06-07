package org.adiris.trarcane.ability.skill.unique;

import io.github.manasmods.manascore.skill.api.ManasSkillInstance;
import io.github.manasmods.tensura.ability.skill.Skill;
import io.github.manasmods.tensura.entity.magic.barrier.DisintegrationEntity;
import io.github.manasmods.tensura.registry.sound.TensuraSoundEvents;
import io.github.manasmods.tensura.util.ObjectSelectionHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class DisintegrationSkill extends Skill {

    public DisintegrationSkill() {
        super(SkillType.UNIQUE);
    }

    private static final int HOLD_TIME = 215;

    @Override
    public @Nullable ResourceLocation getSkillIcon() {
        return ResourceLocation.fromNamespaceAndPath(
                "trarcane",
                "textures/uniques/disintegration.png"
        );
    }

    public double getDefaultAcquiringMagiculeCost() {
        return 100000F;
    }

    @Override
    public double getMagiculeCost(LivingEntity entity, ManasSkillInstance instance, int mode) {
        return mode == 0 ? 500000.0 : 0.0;
    }


    @Override
    public int getModes(ManasSkillInstance instance) {
        return 1;
    }

    @Override
    public String getModeId(ManasSkillInstance instance, int mode) {
        return "disintegration";
    }

    private void spawnDisintegration(ManasSkillInstance instance, LivingEntity user, LivingEntity target) {
        Level level = user.level();

        DisintegrationEntity entity = new DisintegrationEntity(level, user);

        entity.setSize(3.0F);
        entity.setHeight(50.0F);
        entity.setLife(310);
        entity.setDamage(1500F);

        entity.setPos(target.getX(), target.getY(), target.getZ());

        level.addFreshEntity(entity);

        instance.getOrCreateTag().putUUID("DisintegrationUUID", entity.getUUID());

        target.hurtMarked = true;
    }

    private void spawnBlockCast(ManasSkillInstance instance, LivingEntity user) {
        Level level = user.level();

        DisintegrationEntity entity = new DisintegrationEntity(level, user);

        BlockHitResult hit = ObjectSelectionHelper.getPlayerPOVHitResult(
                level,
                user,
                ClipContext.Fluid.NONE,
                15.0
        );

        BlockPos pos = hit.getBlockPos();

        entity.setSize(3.0F);
        entity.setHeight(50.0F);
        entity.setLife(310);
        entity.setPos(new Vec3(pos.getX(), pos.getY(), pos.getZ()));

        level.addFreshEntity(entity);

        instance.getOrCreateTag().putUUID("DisintegrationUUID", entity.getUUID());
    }

    @Override
    public boolean onHeld(ManasSkillInstance instance, LivingEntity user, int heldTicks, int mode) {

        if (heldTicks == 0) {
            LivingEntity target = (LivingEntity) ObjectSelectionHelper.getTargetingEntity(
                    LivingEntity.class,
                    user,
                    15.0,
                    1.0,
                    true,
                    true
            );

            instance.getOrCreateTag().putBoolean("HasTarget", target != null);

            if (target != null) {
                spawnDisintegration(instance, user, target);
            } else {
                spawnBlockCast(instance, user);
            }
        }

        if (heldTicks >= HOLD_TIME) {

            if (user instanceof Player player && !player.isCreative()) {
                setCoolDown(instance, user, true);
            }

            return false;
        }

        return true;
    }

    @Override
    public void onRelease(ManasSkillInstance instance, LivingEntity user, int heldTicks, int keyNumber, int mode) {

        Level level = user.level();

        if (!level.isClientSide()) {

            UUID id = instance.getOrCreateTag().getUUID("DisintegrationUUID");

            if (id != null && level instanceof ServerLevel serverLevel) {

                if (serverLevel.getEntity(id) instanceof DisintegrationEntity entity) {

                    boolean completed = heldTicks >= HOLD_TIME;

                    if (!completed) {
                        entity.discard();

                        serverLevel.playSound(
                                null,
                                user.blockPosition(),
                                TensuraSoundEvents.BARRIER_BREAK.get(),
                                SoundSource.PLAYERS,
                                1.0F,
                                1.0F
                        );

                        user.sendSystemMessage(Component.literal("§cDisintegration cancelled"));
                    }
                }
            }
        }

        setCoolDown(instance, user, heldTicks >= HOLD_TIME);
        instance.getOrCreateTag().remove("DisintegrationUUID");
        instance.getOrCreateTag().remove("HasTarget");
    }

    private void setCoolDown(ManasSkillInstance instance, LivingEntity user, boolean success) {
        int cooldown = success ? 400 : 200;
        instance.setCoolDown(cooldown, 0);
    }
}