package org.adiris.trarcane.config;

import io.github.manasmods.manascore.config.ConfigRegistry;
import org.adiris.trarcane.config.race.FairyConfig;
import org.adiris.trarcane.config.race.GenasiConfig;
import org.adiris.trarcane.config.race.TieflingConfig;

public class TrArcaneConfigs {
    public static void init() {
        ConfigRegistry.registerConfig(new TieflingConfig());
        ConfigRegistry.registerConfig(new GenasiConfig());
        ConfigRegistry.registerConfig(new FairyConfig());
    }
}
