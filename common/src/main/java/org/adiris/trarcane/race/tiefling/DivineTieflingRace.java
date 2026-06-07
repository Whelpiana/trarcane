package org.adiris.trarcane.race.tiefling;

import io.github.manasmods.manascore.config.ConfigRegistry;
import io.github.manasmods.manascore.race.api.ManasRace;
import io.github.manasmods.manascore.race.api.ManasRaceInstance;
import io.github.manasmods.manascore.skill.api.ManasSkill;
import io.github.manasmods.tensura.config.race.RaceConfig;
import io.github.manasmods.tensura.race.template.EvolutionRequirement;
import io.github.manasmods.tensura.registry.skill.IntrinsicSkills;
import net.minecraft.world.entity.LivingEntity;
import org.adiris.trarcane.config.race.TieflingConfig;
import org.adiris.trarcane.registry.race.TrArcaneRaces;

import java.util.List;
import java.util.Map;

public class DivineTieflingRace extends GreaterTieflingRace {

    public DivineTieflingRace(Difficulty difficulty) {
        super(difficulty);
    }

    public DivineTieflingRace() {
        super(Difficulty.EASY);
        this.applyDefaultAttributeModifiers();
    }

    @Override
    public RaceConfig.Default getDefaultConfig() {
        return ((TieflingConfig) ConfigRegistry.getConfig(TieflingConfig.class)).DivineTieflingRace;
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
        return List.of(TrArcaneRaces.GREATER_TIEFLING.get());
    }

    @Override
    public List<ManasSkill> getIntrinsicSkills(ManasRaceInstance instance, LivingEntity entity) {
        List<ManasSkill> list = super.getIntrinsicSkills(instance, entity);
        list.add((ManasSkill) IntrinsicSkills.DIVINE_KI_RELEASE.get());
        return list;
    }

    @Override
    public Map<EvolutionRequirement, Float> getEvolutionRequirements(ManasRaceInstance previous, LivingEntity entity) {
        return Map.of(
                new EvolutionRequirement.EPRequirement(
                        ((TieflingConfig) ConfigRegistry.getConfig(TieflingConfig.class))
                                .DivineTieflingRace.epRequirement
                ),
                100.0F
        );
    }
}