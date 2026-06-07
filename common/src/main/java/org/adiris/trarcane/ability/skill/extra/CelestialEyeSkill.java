package org.adiris.trarcane.ability.skill.extra;

import io.github.manasmods.manascore.skill.api.ManasSkillInstance;
import io.github.manasmods.tensura.ability.skill.Skill;
import io.github.manasmods.tensura.util.AttributeHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;


public class CelestialEyeSkill extends Skill {

    public CelestialEyeSkill() {
        super(Skill.SkillType.EXTRA);
    }

    @Override
    public @Nullable ResourceLocation getSkillIcon() {
        return ResourceLocation.fromNamespaceAndPath(
                "trarcane",
                "textures/extra/celestial_eye.png"
        );
    }

    protected boolean canActivateInRaceLimit(ManasSkillInstance instance, int mode) {
        return true;
    }

    public boolean canBeToggled(ManasSkillInstance instance, LivingEntity living) {
        return instance.getMastery() >= (double)0.0F;
    }

    public boolean canTick(ManasSkillInstance instance, LivingEntity entity) {
        return instance.isToggled();
    }


    public void onTick(ManasSkillInstance instance, LivingEntity entity) {
        CompoundTag tag = instance.getOrCreateTag();
        int time = tag.getInt("activatedTimes");
        if (time % BASE_CONFIG.Mastery.masteryActivateTime == 0) {
            instance.addMasteryPoint(entity);
        }

        tag.putInt("activatedTimes", time + 1);
    }

    public void onToggleOn(ManasSkillInstance instance, LivingEntity entity) {
        AttributeHelper.addPresenceSense(entity, 5);
    }

    public void onToggleOff(ManasSkillInstance instance, LivingEntity entity) {
        AttributeHelper.removePresenceSense(entity, 5);
    }


}
