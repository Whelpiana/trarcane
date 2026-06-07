package org.adiris.trarcane.race.genasi;

import io.github.manasmods.manascore.config.ConfigRegistry;
import io.github.manasmods.manascore.race.api.ManasRace;
import io.github.manasmods.manascore.race.api.ManasRaceInstance;
import io.github.manasmods.manascore.skill.api.ManasSkill;
import io.github.manasmods.tensura.config.race.RaceConfig;
import io.github.manasmods.tensura.race.template.EvolutionRequirement;
import net.minecraft.world.entity.LivingEntity;
import org.adiris.trarcane.config.race.GenasiConfig;
import org.adiris.trarcane.registry.race.TrArcaneRaces;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class WaterGenasiRace extends GenasiRace {

    public WaterGenasiRace() {
        super(Difficulty.HARD);
        this.applyDefaultAttributeModifiers();
    }

    @Override
    public RaceConfig.Default getDefaultConfig() {
        return ((GenasiConfig) ConfigRegistry.getConfig(GenasiConfig.class)).WaterGenasiRace;
    }

    @Override
    public @Nullable ManasRace getDefaultEvolution(ManasRaceInstance instance, LivingEntity entity) {
        return TrArcaneRaces.ANCIENT_GENASI.get();
    }

    @Override
    public List<ManasRace> getNextEvolutions(ManasRaceInstance instance, LivingEntity entity) {
        return List.of(TrArcaneRaces.ANCIENT_GENASI.get());
    }

    @Override
    public Map<EvolutionRequirement, Float> getEvolutionRequirements(ManasRaceInstance previous, LivingEntity entity) {
        return Map.of(
                new EvolutionRequirement.EPRequirement(
                        ((GenasiConfig) ConfigRegistry.getConfig(GenasiConfig.class)).WaterGenasiRace.epRequirement
                ),
                100.0F
        );
    }

    @Override
    public List<ManasSkill> getIntrinsicSkills(ManasRaceInstance instance, LivingEntity entity) {
        List<ManasSkill> list = super.getIntrinsicSkills(instance, entity);

        list.add((ManasSkill) io.github.manasmods.tensura.registry.skill.ExtraSkills.WATER_MANIPULATION.get());
        list.add((ManasSkill) io.github.manasmods.tensura.registry.skill.ResistanceSkills.WATER_ATTACK_RESISTANCE.get());

        return list;
    }
}