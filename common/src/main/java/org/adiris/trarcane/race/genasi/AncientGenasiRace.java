package org.adiris.trarcane.race.genasi;

import io.github.manasmods.manascore.config.ConfigRegistry;
import io.github.manasmods.manascore.race.api.ManasRace;
import io.github.manasmods.manascore.race.api.ManasRaceInstance;
import io.github.manasmods.tensura.config.race.RaceConfig;
import io.github.manasmods.tensura.race.template.EvolutionRequirement;
import net.minecraft.world.entity.LivingEntity;
import org.adiris.trarcane.config.race.GenasiConfig;
import org.adiris.trarcane.registry.race.TrArcaneRaces;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class AncientGenasiRace extends GenasiRace {

    public AncientGenasiRace() {
        super(Difficulty.EXTREME);
        this.applyDefaultAttributeModifiers();
    }

    @Override
    public RaceConfig.Default getDefaultConfig() {
        return ((GenasiConfig) ConfigRegistry.getConfig(GenasiConfig.class)).AncientGenasiRace;
    }

    @Override
    public @Nullable ManasRace getDefaultEvolution(ManasRaceInstance instance, LivingEntity entity) {
        return TrArcaneRaces.DIVINE_GENASI.get();
    }

    @Override
    public List<ManasRace> getNextEvolutions(ManasRaceInstance instance, LivingEntity entity) {
        return List.of(TrArcaneRaces.DIVINE_GENASI.get());
    }

    @Override
    public List<ManasRace> getPreviousEvolutions(ManasRaceInstance instance, LivingEntity entity) {
        return List.of(
                TrArcaneRaces.AIR_GENASI.get(),
                TrArcaneRaces.EARTH_GENASI.get(),
                TrArcaneRaces.FIRE_GENASI.get(),
                TrArcaneRaces.WATER_GENASI.get()
        );
    }

    @Override
    public Map<EvolutionRequirement, Float> getEvolutionRequirements(ManasRaceInstance previous, LivingEntity entity) {
        return Map.of(
                new EvolutionRequirement.EPRequirement(
                        ((GenasiConfig) ConfigRegistry.getConfig(GenasiConfig.class)).AncientGenasiRace.epRequirement
                ),
                100.0F
        );
    }
}