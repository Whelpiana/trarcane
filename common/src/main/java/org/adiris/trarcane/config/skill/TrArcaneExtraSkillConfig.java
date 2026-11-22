package org.adiris.trarcane.config.skill;

import io.github.manasmods.manascore.config.api.Comment;
import io.github.manasmods.manascore.config.api.ManasConfig;
import io.github.manasmods.manascore.config.api.ManasSubConfig;
import io.github.manasmods.manascore.config.api.SyncToClient;

@SyncToClient
public class TrArcaneExtraSkillConfig extends ManasConfig {
    public Rest Rest = new Rest();
    public Replenish Replenish = new Replenish();
    public CelestialEye CelestialEye = new CelestialEye();
    public Measure Measure = new Measure();
    public Clairvoyance Clairvoyance = new Clairvoyance();

    public TrArcaneExtraSkillConfig() {
    }

    @Override
    public String getFileName() {
        return "trarcane/ability/skill/extra_config";
    }

    public static class Rest extends ManasSubConfig {
        @Comment("EP requirement for learning Rest.")
        public double epAcquirement = 200000.0F;

        @Comment("The cooldown in second after activation.")
        public int cooldown = 1800;

        public Rest() {
        }
    }

    public static class Replenish extends ManasSubConfig {
        @Comment("EP requirement for learning Replenish.")
        public double epAcquirement = 400000.0F;

        @Comment("The cooldown in second after activation.")
        public int cooldown = 1800;

        public Replenish() {
        }
    }

    public static class CelestialEye extends ManasSubConfig {
        @Comment("The presence sense level.")
        public int presenceSense = 5;

        public CelestialEye() {
        }
    }

    public static class Measure extends ManasSubConfig {
        @Comment("EP requirement for learning Measure.")
        public double epAcquirement = 50000.0F;

        public Measure() {
        }
    }

    public static class Clairvoyance extends ManasSubConfig {
        @Comment("EP requirement for learning Clairvoyance.")
        public double epAcquirement = 200000.0F;

        public Clairvoyance() {
        }
    }
}
