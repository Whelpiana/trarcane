package org.adiris.trarcane.race.fairy;

import io.github.manasmods.manascore.config.ConfigRegistry;
import io.github.manasmods.manascore.race.api.ManasRace;
import io.github.manasmods.manascore.race.api.ManasRaceInstance;
import io.github.manasmods.manascore.skill.api.ManasSkill;
import io.github.manasmods.tensura.config.race.RaceConfig;
import io.github.manasmods.tensura.race.template.EvolutionRequirement;
import io.github.manasmods.tensura.registry.skill.ExtraSkills;
import io.github.manasmods.tensura.registry.skill.IntrinsicSkills;
import net.minecraft.world.entity.LivingEntity;
import org.adiris.trarcane.config.race.FairyConfig;
import org.adiris.trarcane.registry.race.TrArcaneRaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FairyQueenRace extends FairyLordRace {

    public FairyQueenRace(Difficulty difficulty) {
        super(difficulty);
    }

    public FairyQueenRace() {
        super(Difficulty.EXTREME);
        this.applyDefaultAttributeModifiers();
    }

    @Override
    public RaceConfig.Default getDefaultConfig() {
        return ((FairyConfig) ConfigRegistry.getConfig(FairyConfig.class)).FairyQueen;
    }

    @Override
    public ManasRace getDefaultEvolution(ManasRaceInstance instance, LivingEntity entity) {
        return null;
    }

    @Override
    public List<ManasRace> getNextEvolutions(ManasRaceInstance instance, LivingEntity entity) {
        return List.of();
    }

    @Override
    public List<ManasRace> getPreviousEvolutions(ManasRaceInstance instance, LivingEntity entity) {
        return List.of(TrArcaneRaces.FAIRY_LORD.get());
    }

    @Override
    public Map<EvolutionRequirement, Float> getEvolutionRequirements(ManasRaceInstance previous, LivingEntity entity) {
        return Map.of(
                new EvolutionRequirement.EPRequirement(
                        ((FairyConfig) ConfigRegistry.getConfig(FairyConfig.class)).FairyQueen.epRequirement
                ),
                100.0F
        );
    }

    @Override
    public List<ManasSkill> getIntrinsicSkills(ManasRaceInstance instance, LivingEntity entity) {
        List<ManasSkill> list = new ArrayList<>(super.getIntrinsicSkills(instance, entity));
        list.add((ManasSkill) IntrinsicSkills.DIVINE_KI_RELEASE.get());
        list.add((ManasSkill) ExtraSkills.CHANT_ANNULMENT.get());
        return list;
    }
}