package org.adiris.trarcane.race.genasi;

import io.github.manasmods.manascore.config.ConfigRegistry;
import io.github.manasmods.manascore.race.api.ManasRace;
import io.github.manasmods.manascore.race.api.ManasRaceInstance;
import io.github.manasmods.manascore.skill.api.ManasSkill;
import io.github.manasmods.tensura.config.race.RaceConfig;
import io.github.manasmods.tensura.race.template.DefaultRace;
import io.github.manasmods.tensura.storage.Alignment;
import net.minecraft.world.entity.LivingEntity;
import org.adiris.trarcane.config.race.GenasiConfig;
import org.adiris.trarcane.registry.race.TrArcaneRaces;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GenasiRace extends DefaultRace {

    public GenasiRace() {
        this(Difficulty.HARD);
        this.applyDefaultAttributeModifiers();
    }

    public GenasiRace(Difficulty difficulty) {
        super(difficulty);
        this.applyDefaultAttributeModifiers();
    }

    @Override
    public RaceConfig.Default getDefaultConfig() {
        return ((GenasiConfig) ConfigRegistry.getConfig(GenasiConfig.class)).GenasiRace;
    }


    @Override
    public Alignment getAlignment() {
        return Alignment.MAJIN;
    }

    @Override
    public @Nullable ManasRace getDefaultEvolution(ManasRaceInstance instance, LivingEntity entity) {
        return TrArcaneRaces.AIR_GENASI.get();
    }

    @Override
    public List<ManasRace> getNextEvolutions(ManasRaceInstance instance, LivingEntity entity) {
        return List.of(
                TrArcaneRaces.AIR_GENASI.get(),
                TrArcaneRaces.EARTH_GENASI.get(),
                TrArcaneRaces.FIRE_GENASI.get(),
                TrArcaneRaces.WATER_GENASI.get()
        );
    }

    @Override
    public List<ManasSkill> getIntrinsicSkills(ManasRaceInstance instance, LivingEntity entity) {
        List<ManasSkill> list = super.getIntrinsicSkills(instance, entity);

        list.add((ManasSkill) io.github.manasmods.tensura.registry.skill.ExtraSkills.MAGIC_SENSE.get());

        return list;
    }
}