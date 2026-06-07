package org.adiris.trarcane.race.fairy;

import io.github.manasmods.manascore.config.ConfigRegistry;
import io.github.manasmods.manascore.race.api.ManasRace;
import io.github.manasmods.manascore.race.api.ManasRaceInstance;
import io.github.manasmods.manascore.skill.api.ManasSkill;
import io.github.manasmods.tensura.config.race.RaceConfig;
import io.github.manasmods.tensura.race.RaceUtils;
import io.github.manasmods.tensura.race.template.DefaultRace;
import io.github.manasmods.tensura.registry.skill.ResistanceSkills;
import io.github.manasmods.tensura.registry.sound.TensuraSoundEvents;
import io.github.manasmods.tensura.storage.Alignment;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.adiris.trarcane.config.race.FairyConfig;
import org.adiris.trarcane.registry.race.TrArcaneRaces;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LesserFairyRace extends DefaultRace {

    public LesserFairyRace(Difficulty difficulty) {
        super(difficulty);
    }

    public LesserFairyRace() {
        super(Difficulty.EXTREME);
        this.applyDefaultAttributeModifiers();
    }

    @Override
    public RaceConfig.Default getDefaultConfig() {
        return ((FairyConfig) ConfigRegistry.getConfig(FairyConfig.class)).LesserFairy;
    }

    @Override
    public Alignment getAlignment() {
        return Alignment.MAJIN;
    }

    @Override
    public @Nullable ManasRace getDefaultEvolution(ManasRaceInstance instance, LivingEntity entity) {
        return TrArcaneRaces.FAIRY.get();
    }

    @Override
    public @Nullable ManasRace getHarvestFestivalEvolution(ManasRaceInstance instance, LivingEntity entity) {
        return TrArcaneRaces.FAIRY_LORD.get();
    }

    @Override
    public List<ManasRace> getNextEvolutions(ManasRaceInstance instance, LivingEntity entity) {
        return List.of(TrArcaneRaces.FAIRY.get());
    }

    @Override
    public List<ManasSkill> getIntrinsicSkills(ManasRaceInstance instance, LivingEntity entity) {
        List<ManasSkill> list = new ArrayList<>();
        list.add((ManasSkill) ResistanceSkills.MAGIC_RESISTANCE.get());
        return list;
    }

    @Override
    public void onActivateAbility(ManasRaceInstance instance, LivingEntity entity) {
        if (entity instanceof Player player) {
            if (!player.isSpectator() && !player.isCreative()) {
                Level level = player.level();

                if (player.getAbilities().mayfly) {
                    if (RaceUtils.canStillFly(player, true, true, false)) {
                        return;
                    }

                    player.getAbilities().mayfly = false;
                    player.getAbilities().flying = false;
                } else {
                    player.getAbilities().mayfly = true;
                    player.getAbilities().flying = true;
                }

                player.onUpdateAbilities();

                level.playSound(
                        null,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        (SoundEvent) TensuraSoundEvents.BUFF_ACTIVATE.get(),
                        SoundSource.PLAYERS,
                        1.0F,
                        1.0F
                );
            }
        }
    }
}