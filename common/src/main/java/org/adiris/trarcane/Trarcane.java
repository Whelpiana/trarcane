package org.adiris.trarcane;

import org.adiris.trarcane.config.TrArcaneConfigs;
import org.adiris.trarcane.registry.TrAddonRegistry;

public final class Trarcane {
    public static final String MOD_ID = "trarcane";

    public static void init() {
        TrAddonRegistry.init();

    }
}
