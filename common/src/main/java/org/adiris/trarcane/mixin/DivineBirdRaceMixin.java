package org.adiris.trarcane.mixin;

import io.github.manasmods.manascore.race.api.ManasRaceInstance;
import io.github.manasmods.manascore.skill.api.ManasSkill;
import io.github.manasmods.tensura.race.harpy.DivineBirdRace;
import net.minecraft.world.entity.LivingEntity;
import org.adiris.trarcane.registry.skill.TrArcaneExtraSkills;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(DivineBirdRace.class)
public class DivineBirdRaceMixin {

    @Inject(
            method = "getIntrinsicSkills",
            at = @At("RETURN"),
            cancellable = true
    )
    private void addCelestialEye(
            ManasRaceInstance instance,
            LivingEntity entity,
            CallbackInfoReturnable<List<ManasSkill>> cir
    ) {

        List<ManasSkill> skills = cir.getReturnValue();

        if (!skills.contains(TrArcaneExtraSkills.CELESTIAL_EYE.get())) {
            skills.add(TrArcaneExtraSkills.CELESTIAL_EYE.get());
        }
    }
}