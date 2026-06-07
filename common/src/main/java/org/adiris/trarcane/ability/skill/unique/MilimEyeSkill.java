package org.adiris.trarcane.ability.skill.unique;

import io.github.manasmods.manascore.skill.api.ManasSkillInstance;
import io.github.manasmods.tensura.ability.SkillHelper;
import io.github.manasmods.tensura.ability.skill.Skill;
import io.github.manasmods.tensura.ability.skill.extra.ManaManipulationSkill;
import io.github.manasmods.tensura.registry.attribute.TensuraAttributes;
import io.github.manasmods.tensura.registry.attribute.TensuraGlobalAttributeIds;
import io.github.manasmods.tensura.registry.skill.ExtraSkills;
import io.github.manasmods.tensura.registry.sound.TensuraSoundEvents;
import io.github.manasmods.tensura.storage.TensuraStorages;
import io.github.manasmods.tensura.storage.player.ITensuraPlayer;
import io.github.manasmods.tensura.util.AttributeHelper;
import io.github.manasmods.tensura.util.ObjectSelectionHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext.Block;
import net.minecraft.world.level.ClipContext.Fluid;
import org.jetbrains.annotations.Nullable;

public class MilimEyeSkill extends Skill {

    public MilimEyeSkill() {
        super(SkillType.UNIQUE);
    }

    public double getDefaultAcquiringMagiculeCost() {
        return 30000F;
    }

    @Override
    public int getModes(ManasSkillInstance instance) {
        return 2;
    }

    protected boolean canActivateInRaceLimit(ManasSkillInstance instance, int mode) {
        return true;
    }

    @Override
    public @Nullable ResourceLocation getSkillIcon() {
        return ResourceLocation.fromNamespaceAndPath(
                "trarcane",
                "textures/uniques/milim_eye.png"
        );
    }

    public void onLearnSkill(ManasSkillInstance instance, LivingEntity entity) {
        if (!(instance.getMastery() < (double)0.0F) && !instance.isTemporarySkill()) {
            SkillHelper.learnSkill(entity, ((ManaManipulationSkill) ExtraSkills.MANA_MANIPULATION.get()).createLearningInstance(entity));
        }
    }

    @Override
    public String getModeId(ManasSkillInstance instance, int mode) {
        if (mode == 0) {
            return "milimeyeanalyticalappraisal";
        }
        return "milimeyepresencesense";
    }

    @Override
    public boolean canBeToggled(ManasSkillInstance instance, LivingEntity entity) {
        return true;
    }


    public void onToggleOn(ManasSkillInstance instance, LivingEntity entity) {
        AttributeHelper.addPresenceSense(entity, (double) 6);
    }

    public void onToggleOff(ManasSkillInstance instance, LivingEntity entity) {
        AttributeHelper.removePresenceSense(entity, (double) 6);
    }

    @Override
    public void onPressed(ManasSkillInstance instance, LivingEntity entity, int keyNumber, int mode) {
        if (mode == 0 && entity instanceof ServerPlayer player) {
            if (player.isShiftKeyDown()) {
                ITensuraPlayer data = TensuraStorages.getPlayerDataFrom(player);
                switch (data.getAnalysisMode()) {
                    case 1:
                        data.setAnalysisMode(2);
                        player.displayClientMessage(Component.translatable("tensura.skill.analytical.analyzing_mode.block").setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_AQUA)), true);
                        break;
                    case 2:
                        data.setAnalysisMode(0);
                        player.displayClientMessage(Component.translatable("tensura.skill.analytical.analyzing_mode.both").setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_AQUA)), true);
                        break;
                    default:
                        data.setAnalysisMode(1);
                        player.displayClientMessage(Component.translatable("tensura.skill.analytical.analyzing_mode.entity").setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_AQUA)), true);
                }

                player.playNotifySound((SoundEvent) TensuraSoundEvents.GENERIC_CAST.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                data.markDirty();
            } else {
                AttributeInstance level = player.getAttribute(TensuraAttributes.ANALYSIS_LEVEL);
                if (level != null && level.hasModifier(TensuraGlobalAttributeIds.ANALYSIS)) {
                    AttributeHelper.removeAnalysisAttributes(player, true, true, false);
                } else {
                    int targetLevel = instance.isMastered(entity) ? 4 : 1;
                    int targetRadius = instance.isMastered(entity) ? 6 : 2;
                    AttributeHelper.addAnalysisAttributes(player, targetLevel, targetRadius);
                }

                player.playNotifySound((SoundEvent)TensuraSoundEvents.GENERIC_CAST.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            }
        }
    }

    @Override
    public boolean canTick(ManasSkillInstance instance, LivingEntity entity) {
        if (entity instanceof Player player) {
            return player.getAttributeValue(TensuraAttributes.ANALYSIS_LEVEL) >= 2.0;
        }
        return false;
    }

    @Override
    public void onTick(ManasSkillInstance instance, LivingEntity entity) {
        if (entity instanceof Player player) {
            double distance = player.getAttributeValue(TensuraAttributes.ANALYSIS_DISTANCE);
            boolean success = ObjectSelectionHelper.getTargetingEntity(entity, distance, false, true) != null;
            success = success || !entity.level().getBlockState(ObjectSelectionHelper.getPlayerPOVHitResult(player.level(), player, Fluid.NONE, Block.OUTLINE, distance).getBlockPos()).isAir();

            if (success) {
                CompoundTag tag = instance.getOrCreateTag();
                int time = tag.getInt("activatedTimes");
                if (time % BASE_CONFIG.Mastery.masteryActivateTime == 0) {
                    instance.addMasteryPoint(entity);
                }

                tag.putInt("activatedTimes", time + 1);
            }
        }
    }

    @Override
    public void onForgetSkill(ManasSkillInstance instance, LivingEntity entity) {
        super.onForgetSkill(instance, entity);
        if (entity instanceof ServerPlayer player) {
            AttributeHelper.removeAnalysisAttributes(player, true, true, false);
        }
    }
}