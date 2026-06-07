package org.adiris.trarcane.race.tiefling;

import io.github.manasmods.manascore.config.ConfigRegistry;
import io.github.manasmods.manascore.race.api.ManasRace;
import io.github.manasmods.manascore.race.api.ManasRaceInstance;
import io.github.manasmods.tensura.config.race.RaceConfig;
import io.github.manasmods.tensura.race.template.EvolutionRequirement;
import net.minecraft.world.entity.LivingEntity;
import org.adiris.trarcane.config.race.TieflingConfig;
import org.adiris.trarcane.registry.race.TrArcaneRaces;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class GreaterTieflingRace extends InfernalTieflingRace {

    public GreaterTieflingRace(Difficulty difficulty) {
        super(difficulty);
    }

    public GreaterTieflingRace() {
        super(Difficulty.EASY);
        this.applyDefaultAttributeModifiers();
    }

    @Override
    public RaceConfig.Default getDefaultConfig() {
        return ((TieflingConfig) ConfigRegistry.getConfig(TieflingConfig.class)).GreaterTieflingRace;
    }

    @Override
    public List<ManasRace> getNextEvolutions(ManasRaceInstance instance, LivingEntity entity) {
        return List.of(TrArcaneRaces.DIVINE_TIEFLING.get());
    }

    @Override
    public List<ManasRace> getPreviousEvolutions(ManasRaceInstance instance, LivingEntity entity) {
        return List.of(
                TrArcaneRaces.ABYSSAL_TIEFLING.get(),
                TrArcaneRaces.CHTHONIC_TIEFLING.get(),
                TrArcaneRaces.INFERNAL_TIEFLING.get()
        );
    }

    @Override
    public @Nullable ManasRace getDefaultEvolution(ManasRaceInstance instance, LivingEntity entity) {
        return TrArcaneRaces.DIVINE_TIEFLING.get();
    }

    @Override
    public Map<EvolutionRequirement, Float> getEvolutionRequirements(ManasRaceInstance previous, LivingEntity entity) {
        return Map.of(
                new EvolutionRequirement.EPRequirement(
                        ((TieflingConfig) ConfigRegistry.getConfig(TieflingConfig.class))
                                .GreaterTieflingRace.epRequirement
                ),
                100.0F
        );
    }
}