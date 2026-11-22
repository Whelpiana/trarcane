package org.adiris.trarcane.config.item;

import io.github.manasmods.manascore.config.api.Comment;
import io.github.manasmods.manascore.config.api.ManasConfig;
import io.github.manasmods.manascore.config.api.ManasSubConfig;
import io.github.manasmods.manascore.config.api.SyncToClient;

@SyncToClient
public class TrArcaneItemConfig extends ManasConfig {
    public Coin Coin = new Coin();
    public CalamityStaff CalamityStaff = new CalamityStaff();

    @Override
    public String getFileName() {
        return "trarcane/items/items_config";
    }

    public static class Coin extends ManasSubConfig {
        @Comment("Magicules restored when using the coin.")
        public double magiculesRestored = 250_000;
    }

    public static class CalamityStaff extends ManasSubConfig {
        @Comment("Cooldown for the Calamity Staff.")
        public int cooldown = 5;

        @Comment("Number of magic slots provided by the Calamity Staff.")
        public int magicSlots = 8;

        @Comment("Chant speed multiplier for the Calamity Staff.")
        public double chantSpeed = 1.5;
    }
}
