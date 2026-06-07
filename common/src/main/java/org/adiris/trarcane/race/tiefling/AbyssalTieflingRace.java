package org.adiris.trarcane.race.tiefling;

import io.github.manasmods.manascore.config.ConfigRegistry;
import io.github.manasmods.manascore.race.api.ManasRace;
import io.github.manasmods.manascore.race.api.ManasRaceInstance;
import io.github.manasmods.manascore.skill.api.ManasSkill;
import io.github.manasmods.tensura.config.race.RaceConfig;
import io.github.manasmods.tensura.race.template.EvolutionRequirement;
import io.github.manasmods.tensura.registry.skill.CommonSkills;
import io.github.manasmods.tensura.registry.skill.ResistanceSkills;
import net.minecraft.world.entity.LivingEntity;
import org.adiris.trarcane.config.race.TieflingConfig;
import org.adiris.trarcane.registry.race.TrArcaneRaces;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class AbyssalTieflingRace extends TieflingRace {

    public AbyssalTieflingRace(Difficulty difficulty) {
        super(difficulty);
    }

    public AbyssalTieflingRace() {
        super(Difficulty.EASY);
        this.applyDefaultAttributeModifiers();
    }

    @Override
    public RaceConfig.Default getDefaultConfig() {
        return ((TieflingConfig) ConfigRegistry.getConfig(TieflingConfig.class)).AbyssalTieflingRace;
    }

    @Override
    public List<ManasRace> getNextEvolutions(ManasRaceInstance instance, LivingEntity entity) {
        return List.of(TrArcaneRaces.GREATER_TIEFLING.get());
    }

    @Override
    public List<ManasRace> getPreviousEvolutions(ManasRaceInstance instance, LivingEntity entity) {
        return List.of(TrArcaneRaces.TIEFLING.get());
    }

    @Override
    public Map<EvolutionRequirement, Float> getEvolutionRequirements(ManasRaceInstance previous, LivingEntity entity) {
        return Map.of(
                new EvolutionRequirement.EPRequirement(
                        ((TieflingConfig) ConfigRegistry.getConfig(TieflingConfig.class))
                                .AbyssalTieflingRace.epRequirement
                ),
                100.0F
        );
    }

    @Override
    public @Nullable ManasRace getDefaultEvolution(ManasRaceInstance instance, LivingEntity entity) {
        return TrArcaneRaces.GREATER_TIEFLING.get();
    }

    @Override
    public List<ManasSkill> getIntrinsicSkills(ManasRaceInstance instance, LivingEntity entity) {
        List<ManasSkill> list = super.getIntrinsicSkills(instance, entity);

        list.add((ManasSkill) CommonSkills.POISON.get());
        list.add((ManasSkill) ResistanceSkills.POISON_RESISTANCE.get());

        return list;
    }
}