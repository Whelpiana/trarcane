package org.adiris.trarcane.ability.skill.unique;

import io.github.manasmods.manascore.network.api.util.Changeable;
import io.github.manasmods.manascore.race.api.ManasRaceInstance;
import io.github.manasmods.manascore.skill.api.ManasSkillInstance;
import io.github.manasmods.tensura.ability.SkillHelper;
import io.github.manasmods.tensura.ability.skill.Skill;
import io.github.manasmods.tensura.ability.skill.extra.HeavenlyEyeSkill;
import io.github.manasmods.tensura.damage.TensuraDamageHelper;
import io.github.manasmods.tensura.damage.TensuraDamageTypes;
import io.github.manasmods.tensura.entity.magic.barrier.DisintegrationEntity;
import io.github.manasmods.tensura.registry.skill.ExtraSkills;
import io.github.manasmods.tensura.registry.sound.TensuraSoundEvents;
import io.github.manasmods.tensura.storage.Alignment;
import io.github.manasmods.tensura.storage.TensuraStorages;
import io.github.manasmods.tensura.storage.ep.IExistence;
import io.github.manasmods.tensura.util.ObjectSelectionHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class HolySkill extends Skill {

    private static final int HOLD_TIME = 215;

    private static final double TICK_MAGICULE_COST = 200000.0 / HOLD_TIME;
    private static final float HOLY_BLESSING = 50.0F;

    public HolySkill() {
        super(SkillType.UNIQUE);
    }

    @Override
    public @Nullable ResourceLocation getSkillIcon() {
        return ResourceLocation.fromNamespaceAndPath(
                "trarcane",
                "textures/uniques/holy.png"
        );
    }

    @Override
    public double getDefaultAcquiringMagiculeCost() {
        return 100000.0F;
    }

    @Override
    public double getMagiculeCost(LivingEntity entity, ManasSkillInstance instance, int mode) {
        return 0.0;
    }

    @Override
    public int getModes(ManasSkillInstance instance) {
        return 1;
    }

    @Override
    public int nextMode(LivingEntity entity, ManasSkillInstance instance, int mode, boolean reverse) {
        return 0;
    }

    @Override
    public String getModeId(ManasSkillInstance instance, int mode) {
        return "disintegration";
    }

    @Override
    public void onLearnSkill(ManasSkillInstance instance, LivingEntity entity) {

        if (!(instance.getMastery() < (double)0.0F) && !instance.isTemporarySkill()) {
            SkillHelper.learnSkill(entity, ((HeavenlyEyeSkill)ExtraSkills.HEAVENLY_EYE.get()).createLearningInstance(entity));
        }

        IExistence existence = TensuraStorages.getExistenceFrom(entity);
        if (existence == null) return;

        existence.setBlessed(true);

        if (existence.getAlignment() == Alignment.MAJIN || existence.getAlignment() == Alignment.CHAOS || existence.getAlignment() == Alignment.DEFAULT) {
            existence.setAlignment(Alignment.HOLY);
        }

        existence.markDirty();
    }

    public void onRaceEvolution(ManasSkillInstance instance, LivingEntity living, ManasRaceInstance evolution) {
        IExistence existence = TensuraStorages.getExistenceFrom(living);
        if (existence == null) return;

        existence.setBlessed(true);

        Alignment current = existence.getAlignment();

        if (current == Alignment.MAJIN || current == Alignment.CHAOS || current == Alignment.DEFAULT) {
            existence.setAlignment(Alignment.HOLY);
        }

        existence.markDirty();
    }

    @Override
    public boolean onDamageEntity(ManasSkillInstance instance, LivingEntity owner, LivingEntity target, DamageSource source, Changeable<Float> amount) {

        if (source.is(TensuraDamageTypes.DISINTEGRATION)) {
            amount.set(300.0F);
            return true;
        }

        if (target != owner && this.isInSlot(owner)) {


            if (TensuraDamageHelper.isPhysicalAttack(source) && !TensuraDamageHelper.isHoly(source)) {


                target.invulnerableTime = 0;


                target.hurt(this.createSource(instance, owner, TensuraDamageTypes.HOLY_DAMAGE, 1), HOLY_BLESSING);


                instance.getOrCreateTag().putBoolean("check", false);
            }
        }


        if (!instance.getOrCreateTag().getBoolean("check")) {
            instance.getOrCreateTag().putBoolean("check", true);
        }

        return true;
    }

    @Override
    public void onTick(ManasSkillInstance instance, LivingEntity living) {

        if (instance.getOrCreateTag().getBoolean("LawManipulationGranted")) {
            return;
        }


        IExistence existence = TensuraStorages.getExistenceFrom(living);
        if (existence == null) return;


        if (existence.isTrueHero()) {


            if (!(instance.getMastery() < 0.0) && !instance.isTemporarySkill()) {


                SkillHelper.learnSkill(living, ExtraSkills.LAW_MANIPULATION.get().createLearningInstance(living));


                instance.getOrCreateTag().putBoolean("LawManipulationGranted", true);
                instance.markDirty();
            }
        }
    }

    @Override
    public boolean canTick(ManasSkillInstance instance, LivingEntity entity) {
        return true;
    }

    @Override
    public boolean onHeld(ManasSkillInstance instance, LivingEntity user, int heldTicks, int mode) {

        IExistence existence = TensuraStorages.getExistenceFrom(user);


        if (existence == null || existence.getMagicule() < TICK_MAGICULE_COST) {
            cancelDisintegration(instance, user, heldTicks);
            return false;
        } else {
            existence.setMagicule(existence.getMagicule() - TICK_MAGICULE_COST);
            existence.markDirty();
        }

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
        cancelDisintegration(instance, user, heldTicks);
    }

    private void cancelDisintegration(ManasSkillInstance instance, LivingEntity user, int heldTicks) {
        Level level = user.level();

        if (!level.isClientSide()) {
            if (instance.getOrCreateTag().hasUUID("DisintegrationUUID")) {
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

                            user.sendSystemMessage(Component.literal("Disintegration cancelled"));
                        }
                    }
                }
            }
        }

        setCoolDown(instance, user, heldTicks >= HOLD_TIME);
        instance.getOrCreateTag().remove("DisintegrationUUID");
        instance.getOrCreateTag().remove("HasTarget");
    }

    private void spawnDisintegration(ManasSkillInstance instance, LivingEntity user, LivingEntity target) {
        Level level = user.level();
        DisintegrationEntity entity = new DisintegrationEntity(level, user);

        entity.setSize(3.0F);
        entity.setHeight(50.0F);
        entity.setLife(310);
        entity.setDamage(400.0F);
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

    private void setCoolDown(ManasSkillInstance instance, LivingEntity user, boolean success) {
        instance.setCoolDown(900, 0);
    }
}