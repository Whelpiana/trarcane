package org.adiris.trarcane.mixin;

import io.github.manasmods.manascore.race.api.ManasRaceInstance;
import io.github.manasmods.manascore.skill.api.ManasSkill;
import io.github.manasmods.tensura.ability.SkillUtils;
import io.github.manasmods.tensura.race.lizardman.DivineDragonRace;
import io.github.manasmods.tensura.registry.skill.UniqueSkills;
import net.minecraft.world.entity.LivingEntity;
import org.adiris.trarcane.registry.skill.TrArcaneUniqueSkills;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(DivineDragonRace.class)
public class DivineDragonRaceMixin {

    @Inject(
            method = "getIntrinsicSkills",
            at = @At("RETURN"),
            cancellable = true
    )
    private void addMilimEye(
            ManasRaceInstance instance,
            LivingEntity entity,
            CallbackInfoReturnable<List<ManasSkill>> cir
    ) {

        List<ManasSkill> skills = cir.getReturnValue();

        if (entity == null) return;

        if (!SkillUtils.hasSkill(
                entity,
                UniqueSkills.WRATH.get()
        )) {
            return;
        }

        if (!skills.contains(TrArcaneUniqueSkills.MILIMEYE.get())) {
            skills.add(TrArcaneUniqueSkills.MILIMEYE.get());
        }
    }
}