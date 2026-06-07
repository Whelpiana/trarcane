package org.adiris.trarcane.ability.skill.ultimate;

import io.github.manasmods.manascore.skill.api.ManasSkillInstance;
import io.github.manasmods.tensura.ability.skill.Skill;
import io.github.manasmods.tensura.entity.magic.barrier.DisintegrationEntity;
import io.github.manasmods.tensura.registry.sound.TensuraSoundEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MegaDisintegrationSkill extends Skill {

    public MegaDisintegrationSkill() {
        super(SkillType.ULTIMATE);
    }

    @Override
    public @Nullable ResourceLocation getSkillIcon() {
        return ResourceLocation.fromNamespaceAndPath(
                "trarcane",
                "textures/ultimate/mega_disintegration.png"
        );
    }

    public double getDefaultAcquiringMagiculeCost() {
        return 2000000F;
    }

    @Override
    public double getMagiculeCost(LivingEntity entity, ManasSkillInstance instance, int mode) {
        return mode == 0 ? 1000000.0 : 0.0;
    }

    private static final int HOLD_TIME = 215;

    @Override
    public int getModes(ManasSkillInstance instance) {
        return 1;
    }

    @Override
    public String getModeId(ManasSkillInstance instance, int mode) {
        return "mega_disintegration";
    }

    private void spawnAreaDisintegration(ManasSkillInstance instance, LivingEntity user) {

        Level level = user.level();

        AABB area = user.getBoundingBox().inflate(25.0D);

        List<LivingEntity> targets = level.getEntitiesOfClass(
                LivingEntity.class,
                area,
                entity ->
                        entity != user &&
                                entity.isAlive()
        );

        List<UUID> spawnedIds = new ArrayList<>();

        for (LivingEntity target : targets) {

            DisintegrationEntity entity = new DisintegrationEntity(level, user);

            entity.setSize(3.0F);
            entity.setHeight(50.0F);
            entity.setLife(310);
            entity.setDamage(1500F);

            entity.setPos(
                    target.getX(),
                    target.getY(),
                    target.getZ()
            );

            level.addFreshEntity(entity);

            spawnedIds.add(entity.getUUID());

            target.hurtMarked = true;
        }

        for (int i = 0; i < spawnedIds.size(); i++) {
            instance.getOrCreateTag().putUUID("DisintegrationUUID_" + i, spawnedIds.get(i));
        }

        instance.getOrCreateTag().putInt("DisintegrationCount", spawnedIds.size());
    }

    @Override
    public boolean onHeld(ManasSkillInstance instance, LivingEntity user, int heldTicks, int mode) {

        if (heldTicks == 0) {
            spawnAreaDisintegration(instance, user);
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

        if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {

            int count = instance.getOrCreateTag().getInt("DisintegrationCount");

            boolean completed = heldTicks >= HOLD_TIME;

            if (!completed) {

                for (int i = 0; i < count; i++) {

                    String key = "DisintegrationUUID_" + i;

                    if (instance.getOrCreateTag().hasUUID(key)) {

                        UUID id = instance.getOrCreateTag().getUUID(key);

                        if (serverLevel.getEntity(id) instanceof DisintegrationEntity entity) {
                            entity.discard();
                        }
                    }
                }

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

        setCoolDown(instance, user, heldTicks >= HOLD_TIME);

        int count = instance.getOrCreateTag().getInt("DisintegrationCount");

        for (int i = 0; i < count; i++) {
            instance.getOrCreateTag().remove("DisintegrationUUID_" + i);
        }

        instance.getOrCreateTag().remove("DisintegrationCount");
    }

    private void setCoolDown(ManasSkillInstance instance, LivingEntity user, boolean success) {
        int cooldown = success ? 400 : 200;
        instance.setCoolDown(cooldown, 0);
    }
}
