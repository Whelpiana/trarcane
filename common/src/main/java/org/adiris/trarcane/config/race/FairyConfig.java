package org.adiris.trarcane.config.race;

import io.github.manasmods.manascore.config.api.Comment;
import io.github.manasmods.manascore.config.api.ManasConfig;
import io.github.manasmods.manascore.config.api.SyncToClient;
import io.github.manasmods.tensura.config.race.RaceConfig;

@SyncToClient
public class FairyConfig extends ManasConfig {

    public LesserFairy LesserFairy = new LesserFairy();
    public Fairy Fairy = new Fairy();
    public FairyLord FairyLord = new FairyLord();
    public FairyQueen FairyQueen = new FairyQueen();

    @Override
    public String getFileName() {
        return "tensura/race/trafairy_config";
    }


    public static class LesserFairy extends RaceConfig.Default {

        @Comment("Minimal aura.")
        public double minAura = 150.0F;

        @Comment("Maximum aura.")
        public double maxAura = 250.0F;

        @Comment("Minimal magicule.")
        public double minMagicule = 450.0F;

        @Comment("Maximum magicule.")
        public double maxMagicule = 650.0F;

        @Comment("Bonus Size.")
        public double size = -0.60F;

        @Comment("Bonus Max Health.")
        public double maxHealth = -12.0F;

        @Comment("Bonus Max Spiritual Health.")
        public double maxSpiritualHealth = 65.0F;

        @Comment("Bonus Attack Damage.")
        public double attack = -0.5F;

        @Comment("Bonus Attack Speed.")
        public double attackSpeed = 0.25F;

        @Comment("Bonus Knockback Resistance.")
        public double knockbackResistance = 0.0F;

        @Comment("Bonus Movement Speed.")
        public double movementSpeed = 0.08F;

        @Comment("Bonus Swimming Speed Multiplier.")
        public double swimSpeed = 0.15F;

        public double getMinAura() {
            return this.minAura;
        }

        public double getMaxAura() {
            return this.maxAura;
        }

        public double getMinMagicule() {
            return this.minMagicule;
        }

        public double getMaxMagicule() {
            return this.maxMagicule;
        }

        public double getSize() {
            return this.size;
        }

        public double getMaxHealth() {
            return this.maxHealth;
        }

        public double getMaxSpiritualHealth() {
            return this.maxSpiritualHealth;
        }

        public double getAttack() {
            return this.attack;
        }

        public double getAttackSpeed() {
            return this.attackSpeed;
        }

        public double getKnockbackResistance() {
            return this.knockbackResistance;
        }

        public double getMovementSpeed() {
            return this.movementSpeed;
        }

        public double getSwimSpeed() {
            return this.swimSpeed;
        }

    }





    public static class Fairy extends RaceConfig.Default {

        @Comment("EP requirement to evolve into Fairy.")
        public double epRequirement = 100000.0F;

        @Comment("Minimal aura.")
        public double minAura = 20000.0F;

        @Comment("Maximum aura.")
        public double maxAura = 20000.0F;

        @Comment("Minimal magicule.")
        public double minMagicule = 80000.0F;

        @Comment("Maximum magicule.")
        public double maxMagicule = 80000.0F;

        @Comment("Bonus Size.")
        public double size = -0.55F;

        @Comment("Bonus Max Health.")
        public double maxHealth = 80.0F;

        @Comment("Bonus Max Spiritual Health.")
        public double maxSpiritualHealth = 320.0F;

        @Comment("Bonus Attack Damage.")
        public double attack = 0.5F;

        @Comment("Bonus Attack Speed.")
        public double attackSpeed = 0.35F;

        @Comment("Bonus Knockback Resistance.")
        public double knockbackResistance = 0.1F;

        @Comment("Bonus Movement Speed.")
        public double movementSpeed = 0.12F;

        @Comment("Bonus Swimming Speed Multiplier.")
        public double swimSpeed = 0.25F;


        public double getEpRequirement() {
            return this.epRequirement;
        }

        public double getMinAura() {
            return this.minAura;
        }

        public double getMaxAura() {
            return this.maxAura;
        }

        public double getMinMagicule() {
            return this.minMagicule;
        }

        public double getMaxMagicule() {
            return this.maxMagicule;
        }

        public double getSize() {
            return this.size;
        }

        public double getMaxHealth() {
            return this.maxHealth;
        }

        public double getMaxSpiritualHealth() {
            return this.maxSpiritualHealth;
        }

        public double getAttack() {
            return this.attack;
        }

        public double getAttackSpeed() {
            return this.attackSpeed;
        }

        public double getKnockbackResistance() {
            return this.knockbackResistance;
        }

        public double getMovementSpeed() {
            return this.movementSpeed;
        }

        public double getSwimSpeed() {
            return this.swimSpeed;
        }

    }



    public static class FairyLord extends RaceConfig.Default {

        @Comment("EP requirement to evolve into Fairy Lord.")
        public double epRequirement = 800000.0F;

        @Comment("Minimal aura.")
        public double minAura = 200000.0F;

        @Comment("Maximum aura.")
        public double maxAura = 200000.0F;

        @Comment("Minimal magicule.")
        public double minMagicule = 600000.0F;

        @Comment("Maximum magicule.")
        public double maxMagicule = 600000.0F;

        @Comment("Bonus Size.")
        public double size = -0.50F;

        @Comment("Bonus Max Health.")
        public double maxHealth = 480.0F;

        @Comment("Bonus Max Spiritual Health.")
        public double maxSpiritualHealth = 3000.0F;

        @Comment("Bonus Attack Damage.")
        public double attack = 2.0F;

        @Comment("Bonus Attack Speed.")
        public double attackSpeed = 0.5F;

        @Comment("Bonus Knockback Resistance.")
        public double knockbackResistance = 0.2F;

        @Comment("Bonus Movement Speed.")
        public double movementSpeed = 0.18F;

        @Comment("Bonus Swimming Speed Multiplier.")
        public double swimSpeed = 0.45F;


        public double getEpRequirement() {
            return this.epRequirement;
        }

        public double getMinAura() {
            return this.minAura;
        }

        public double getMaxAura() {
            return this.maxAura;
        }

        public double getMinMagicule() {
            return this.minMagicule;
        }

        public double getMaxMagicule() {
            return this.maxMagicule;
        }

        public double getSize() {
            return this.size;
        }

        public double getMaxHealth() {
            return this.maxHealth;
        }

        public double getMaxSpiritualHealth() {
            return this.maxSpiritualHealth;
        }

        public double getAttack() {
            return this.attack;
        }

        public double getAttackSpeed() {
            return this.attackSpeed;
        }

        public double getKnockbackResistance() {
            return this.knockbackResistance;
        }

        public double getMovementSpeed() {
            return this.movementSpeed;
        }

        public double getSwimSpeed() {
            return this.swimSpeed;
        }

    }





    public static class FairyQueen extends RaceConfig.Default {

        @Comment("EP requirement to evolve into Fairy Queen.")
        public double epRequirement = 2000000.0F;

        @Comment("Minimal aura.")
        public double minAura = 500000.0F;

        @Comment("Maximum aura.")
        public double maxAura = 500000.0F;

        @Comment("Minimal magicule.")
        public double minMagicule = 1500000.0F;

        @Comment("Maximum magicule.")
        public double maxMagicule = 1500000.0F;

        @Comment("Bonus Size.")
        public double size = -0.45F;

        @Comment("Bonus Max Health.")
        public double maxHealth = 880.0F;

        @Comment("Bonus Max Spiritual Health.")
        public double maxSpiritualHealth = 6200.0F;

        @Comment("Bonus Attack Damage.")
        public double attack = 3.6F;

        @Comment("Bonus Attack Speed.")
        public double attackSpeed = 0.7F;

        @Comment("Bonus Knockback Resistance.")
        public double knockbackResistance = 0.4F;

        @Comment("Bonus Movement Speed.")
        public double movementSpeed = 0.22F;

        @Comment("Bonus Swimming Speed Multiplier.")
        public double swimSpeed = 0.7F;


        public double getEpRequirement() {
            return this.epRequirement;
        }

        public double getMinAura() {
            return this.minAura;
        }

        public double getMaxAura() {
            return this.maxAura;
        }

        public double getMinMagicule() {
            return this.minMagicule;
        }

        public double getMaxMagicule() {
            return this.maxMagicule;
        }

        public double getSize() {
            return this.size;
        }

        public double getMaxHealth() {
            return this.maxHealth;
        }

        public double getMaxSpiritualHealth() {
            return this.maxSpiritualHealth;
        }

        public double getAttack() {
            return this.attack;
        }

        public double getAttackSpeed() {
            return this.attackSpeed;
        }

        public double getKnockbackResistance() {
            return this.knockbackResistance;
        }

        public double getMovementSpeed() {
            return this.movementSpeed;
        }

        public double getSwimSpeed() {
            return this.swimSpeed;
        }


    }
}