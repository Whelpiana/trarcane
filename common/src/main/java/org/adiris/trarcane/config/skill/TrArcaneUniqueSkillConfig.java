package org.adiris.trarcane.config.skill;

import io.github.manasmods.manascore.config.api.Comment;
import io.github.manasmods.manascore.config.api.ManasConfig;
import io.github.manasmods.manascore.config.api.ManasSubConfig;
import io.github.manasmods.manascore.config.api.SyncToClient;

@SyncToClient
public class TrArcaneUniqueSkillConfig extends ManasConfig {
    public Excavation Excavation = new Excavation();
    public Holy Holy = new Holy();
    public Investigator Investigator = new Investigator();

    public TrArcaneUniqueSkillConfig() {
    }

    @Override
    public String getFileName() {
        return "trarcane/ability/skill/unique_config";
    }

    public static class Excavation extends ManasSubConfig {
        @Comment("The cooldown in second after activation.")
        public int cooldown = 300;

        public Excavation() {
        }
    }

    public static class Holy extends ManasSubConfig {
        @Comment("Damage dealt by Holy Blessing.")
        public float holyBlessing = 50.0F;

        @Comment("Damage dealt by Disintegration.")
        public float disintegrationDamage = 300.0F;

        @Comment("Total magicule cost for a full Disintegration hold, distributed across the hold time.")
        public double magiculeCost = 200000.0;

        @Comment("Maximum Disintegration hold time in ticks.")
        public int holdTime = 215;

        @Comment("The cooldown in second after activation.")
        public int cooldown = 900;

        public Holy() {
        }
    }

    public static class Investigator extends ManasSubConfig {
        @Comment("Thought acceleration chant speed bonus.")
        public double chantSpeed = 2.0;

        @Comment("Movement speed bonus before mastery.")
        public double movementSpeed = 0.01;

        @Comment("Movement speed bonus after mastery.")
        public double movementSpeedMastered = 0.02;

        @Comment("Attack speed bonus before mastery.")
        public double attackSpeed = 0.2;

        @Comment("Attack speed bonus after mastery.")
        public double attackSpeedMastered = 0.4;

        @Comment("Dodge invulnerability bonus after mastery.")
        public int dodgeInvulnerability = 1;

        @Comment("Presence sense level while Investigator is toggled on.")
        public double presenceSense = 6.0;

        public Investigator() {
        }
    }
}
