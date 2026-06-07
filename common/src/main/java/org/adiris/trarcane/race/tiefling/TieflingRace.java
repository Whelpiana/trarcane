package org.adiris.trarcane.race.tiefling;

import io.github.manasmods.manascore.config.ConfigRegistry;
import io.github.manasmods.manascore.race.api.ManasRace;
import io.github.manasmods.manascore.race.api.ManasRaceInstance;
import io.github.manasmods.tensura.config.race.RaceConfig;
import io.github.manasmods.tensura.race.template.DefaultRace;
import io.github.manasmods.tensura.storage.Alignment;
import net.minecraft.world.entity.LivingEntity;
import org.adiris.trarcane.config.race.TieflingConfig;
import org.adiris.trarcane.registry.race.TrArcaneRaces;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TieflingRace extends DefaultRace {

    public TieflingRace(Difficulty difficulty) {
        super(difficulty);
    }

    public TieflingRace() {
        this(Difficulty.INTERMEDIATE);
        this.applyDefaultAttributeModifiers();
    }

    @Override
    public RaceConfig.Default getDefaultConfig() {
        return ((TieflingConfig) ConfigRegistry.getConfig(TieflingConfig.class)).TieflingRace;
    }

    @Override
    public Alignment getAlignment() {
        return Alignment.MAJIN;
    }

    @Override
    public @Nullable ManasRace getDefaultEvolution(ManasRaceInstance instance, LivingEntity entity) {
        return TrArcaneRaces.INFERNAL_TIEFLING.get();
    }



    @Override
    public List<ManasRace> getNextEvolutions(ManasRaceInstance instance, LivingEntity entity) {
        return List.of(
                TrArcaneRaces.ABYSSAL_TIEFLING.get(),
                TrArcaneRaces.CHTHONIC_TIEFLING.get(),
                TrArcaneRaces.INFERNAL_TIEFLING.get()
        );
    }


}