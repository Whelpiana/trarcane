package org.adiris.trarcane.config;

import io.github.manasmods.manascore.config.ConfigRegistry;
import io.github.manasmods.tensura.config.ReincarnationConfig;
import io.github.manasmods.tensura.config.ability.skill.UniqueSkillConfig;
import org.adiris.trarcane.config.race.FairyConfig;
import org.adiris.trarcane.config.race.GenasiConfig;
import org.adiris.trarcane.config.race.TieflingConfig;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class TrArcaneConfigs {

    public static final List<String> RACES = List.of(
            "trarcane:lesser_fairy",
            "trarcane:genasi",
            "trarcane:tiefling"
    );

    public static final List<String> UNIQUE_SKILLS = List.of(
            "trarcane:investigator",
            "trarcane:necro_lord",
            "trarcane:holy"
    );

    public static void init() {
        ConfigRegistry.registerConfig(new TieflingConfig());
        ConfigRegistry.registerConfig(new GenasiConfig());
        ConfigRegistry.registerConfig(new FairyConfig());
    }

    public static void addToConfig() {

        ReincarnationConfig reinc =
                (ReincarnationConfig) ConfigRegistry.getConfig(ReincarnationConfig.class);

        UniqueSkillConfig unique =
                (UniqueSkillConfig) ConfigRegistry.getConfig(UniqueSkillConfig.class);

        boolean changed = false;


        LinkedHashSet<String> startingRaces =
                new LinkedHashSet<>(reinc.Races.startingRaces);

        LinkedHashSet<String> randomRaces =
                new LinkedHashSet<>(reinc.Races.randomRaces);

        LinkedHashSet<String> reincarnationRaces =
                new LinkedHashSet<>(reinc.Races.reincarnationRaces);

        LinkedHashSet<String> masteredRaces =
                new LinkedHashSet<>(reinc.Races.reincarnationRacesMastered);

        for (String race : RACES) {

            if (startingRaces.add(race))
                changed = true;

            if (randomRaces.add(race))
                changed = true;

            if (reincarnationRaces.add(race))
                changed = true;

            if (masteredRaces.add(race))
                changed = true;
        }

        reinc.Races.startingRaces =
                new ArrayList<>(startingRaces);

        reinc.Races.randomRaces =
                new ArrayList<>(randomRaces);

        reinc.Races.reincarnationRaces =
                new ArrayList<>(reincarnationRaces);

        reinc.Races.reincarnationRacesMastered =
                new ArrayList<>(masteredRaces);

        LinkedHashSet<String> startingSkills =
                new LinkedHashSet<>(reinc.Skills.startingSkills);

        LinkedHashSet<String> creatorSkills =
                new LinkedHashSet<>(unique.Creator.uniqueSkills);

        for (String skill : UNIQUE_SKILLS) {

            if (startingSkills.add(skill))
                changed = true;

            if (creatorSkills.add(skill))
                changed = true;
        }

        reinc.Skills.startingSkills =
                new ArrayList<>(startingSkills);

        unique.Creator.uniqueSkills =
                new ArrayList<>(creatorSkills);


        if (changed) {
            ConfigRegistry.saveAllConfigs();
        }
    }
}