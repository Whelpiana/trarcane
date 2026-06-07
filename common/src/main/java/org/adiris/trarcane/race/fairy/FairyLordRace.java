package org.adiris.trarcane.race.fairy;

import io.github.manasmods.manascore.config.ConfigRegistry;
import io.github.manasmods.manascore.race.api.ManasRace;
import io.github.manasmods.manascore.race.api.ManasRaceInstance;
import io.github.manasmods.manascore.skill.api.ManasSkill;
import io.github.manasmods.tensura.config.race.RaceConfig;
import io.github.manasmods.tensura.race.template.EvolutionRequirement;
import io.github.manasmods.tensura.registry.skill.ExtraSkills;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import org.adiris.trarcane.config.race.FairyConfig;
import org.adiris.trarcane.registry.race.TrArcaneRaces;

import java.util.List;
import java.util.Map;

public class FairyLordRace extends FairyRace {

    public FairyLordRace(Difficulty difficulty) {
        super(difficulty);
    }

    public FairyLordRace() {
        super(Difficulty.EXTREME);
        this.applyDefaultAttributeModifiers();
    }

    @Override
    public RaceConfig.Default getDefaultConfig() {
        return ((FairyConfig) ConfigRegistry.getConfig(FairyConfig.class)).FairyLord;
    }

    @Override
    public ManasRace getDefaultEvolution(ManasRaceInstance instance, LivingEntity entity) {
        return TrArcaneRaces.FAIRY_QUEEN.get();
    }

    @Override
    public List<ManasRace> getNextEvolutions(ManasRaceInstance instance, LivingEntity entity) {
        return List.of(TrArcaneRaces.FAIRY_QUEEN.get());
    }

    @Override
    public List<ManasRace> getPreviousEvolutions(ManasRaceInstance instance, LivingEntity entity) {
        return List.of(TrArcaneRaces.FAIRY.get());
    }

    @Override
    public Map<EvolutionRequirement, Float> getEvolutionRequirements(ManasRaceInstance previous, LivingEntity entity) {
        return Map.of(
                new EvolutionRequirement.EPRequirement(
                        ((FairyConfig) ConfigRegistry.getConfig(FairyConfig.class)).FairyLord.epRequirement
                ),
                100.0F
        );
    }

    @Override
    public List<ManasSkill> getIntrinsicSkills(ManasRaceInstance instance, LivingEntity entity) {
        List<ManasSkill> list = super.getIntrinsicSkills(instance, entity);

        switch (RandomSource.create().nextIntBetweenInclusive(1, 3)) {
            case 1 -> list.add((ManasSkill) ExtraSkills.WATER_MANIPULATION.get());
            case 2 -> list.add((ManasSkill) ExtraSkills.WIND_MANIPULATION.get());
            case 3 -> list.add((ManasSkill) ExtraSkills.EARTH_MANIPULATION.get());
        }

        return list;
    }

    @Override
    public List<ManasSkill> getRenderingIntrinsicSkills(ManasRaceInstance instance, LivingEntity entity) {
        List<ManasSkill> list = super.getIntrinsicSkills(instance, entity);

        switch (entity.tickCount / 20 % 3) {
            case 0 -> list.add((ManasSkill) ExtraSkills.WATER_MANIPULATION.get());
            case 1 -> list.add((ManasSkill) ExtraSkills.WIND_MANIPULATION.get());
            case 2 -> list.add((ManasSkill) ExtraSkills.EARTH_MANIPULATION.get());
        }

        return list;
    }

    @Override
    public boolean isIntrinsicSkill(ManasRaceInstance instance, LivingEntity entity, ManasSkill skill) {
        if (skill == ExtraSkills.WATER_MANIPULATION.get()) {
            return true;
        } else if (skill == ExtraSkills.WIND_MANIPULATION.get()) {
            return true;
        } else {
            return skill == ExtraSkills.EARTH_MANIPULATION.get()
                    ? true
                    : super.isIntrinsicSkill(instance, entity, skill);
        }
    }
}