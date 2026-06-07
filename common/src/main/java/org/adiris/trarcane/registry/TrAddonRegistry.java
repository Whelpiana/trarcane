package org.adiris.trarcane.registry;


import org.adiris.trarcane.config.TrArcaneConfigs;
import org.adiris.trarcane.handler.TrArcaneHandlers;
import org.adiris.trarcane.registry.item.ModItems;
import org.adiris.trarcane.registry.race.TrArcaneRaces;
import org.adiris.trarcane.registry.skill.TrArcaneExtraSkills;
import org.adiris.trarcane.registry.skill.TrArcaneUltimateSkills;
import org.adiris.trarcane.registry.skill.TrArcaneUniqueSkills;

public class TrAddonRegistry {
    public static void init() {
        TrArcaneHandlers.init();
        ModItems.registerModItems();

    }

    public static void injectInit() {
        TrArcaneConfigs.init();
        TrArcaneRaces.init();
        TrArcaneExtraSkills.init();
        TrArcaneUniqueSkills.init();
        TrArcaneUltimateSkills.init();
    }
}
