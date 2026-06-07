package org.adiris.trarcane.race.fairy;

import io.github.manasmods.manascore.config.ConfigRegistry;
import io.github.manasmods.manascore.race.api.ManasRace;
import io.github.manasmods.manascore.race.api.ManasRaceInstance;
import io.github.manasmods.manascore.skill.api.ManasSkill;
import io.github.manasmods.tensura.config.race.RaceConfig;
import io.github.manasmods.tensura.race.template.EvolutionRequirement;
import io.github.manasmods.tensura.registry.skill.ResistanceSkills;
import net.minecraft.world.entity.LivingEntity;
import org.adiris.trarcane.config.race.FairyConfig;
import org.adiris.trarcane.registry.race.TrArcaneRaces;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class FairyRace extends LesserFairyRace {

    public FairyRace(Difficulty difficulty) {
        super(difficulty);
    }

    public FairyRace() {
        super(Difficulty.EXTREME);
        this.applyDefaultAttributeModifiers();
    }

    @Override
    public RaceConfig.Default getDefaultConfig() {
        return ((FairyConfig) ConfigRegistry.getConfig(FairyConfig.class)).Fairy;
    }

    @Override
    public @Nullable ManasRace getDefaultEvolution(ManasRaceInstance instance, LivingEntity entity) {
        return TrArcaneRaces.FAIRY_LORD.get();
    }

    @Override
    public @Nullable ManasRace getHarvestFestivalEvolution(ManasRaceInstance instance, LivingEntity entity) {
        return null;
    }

    @Override
    public List<ManasRace> getNextEvolutions(ManasRaceInstance instance, LivingEntity entity) {
        return List.of(TrArcaneRaces.FAIRY_LORD.get());
    }

    @Override
    public List<ManasRace> getPreviousEvolutions(ManasRaceInstance instance, LivingEntity entity) {
        return List.of(TrArcaneRaces.LESSER_FAIRY.get());
    }

    @Override
    public Map<EvolutionRequirement, Float> getEvolutionRequirements(ManasRaceInstance previous, LivingEntity entity) {
        return Map.of(
                new EvolutionRequirement.EPRequirement(
                        ((FairyConfig) ConfigRegistry.getConfig(FairyConfig.class)).Fairy.epRequirement
                ),
                100.0F
        );
    }

    @Override
    public List<ManasSkill> getIntrinsicSkills(ManasRaceInstance instance, LivingEntity entity) {
        List<ManasSkill> list = super.getIntrinsicSkills(instance, entity);
        list.add((ManasSkill) ResistanceSkills.PHYSICAL_ATTACK_RESISTANCE.get());
        return list;
    }
}