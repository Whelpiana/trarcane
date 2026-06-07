package org.adiris.trarcane.config.race;

import io.github.manasmods.manascore.config.api.Comment;
import io.github.manasmods.manascore.config.api.ManasConfig;
import io.github.manasmods.manascore.config.api.SyncToClient;
import io.github.manasmods.tensura.config.race.RaceConfig;

@SyncToClient
public class GenasiConfig extends ManasConfig {

    public GenasiRace GenasiRace = new GenasiRace();

    public AirGenasiRace AirGenasiRace = new AirGenasiRace();
    public EarthGenasiRace EarthGenasiRace = new EarthGenasiRace();
    public FireGenasiRace FireGenasiRace = new FireGenasiRace();
    public WaterGenasiRace WaterGenasiRace = new WaterGenasiRace();

    public AncientGenasiRace AncientGenasiRace = new AncientGenasiRace();
    public DivineGenasiRace DivineGenasiRace = new DivineGenasiRace();

    @Override
    public String getFileName() {
        return "tensura/race/tragenasi_config";
    }

    public static class GenasiRace extends RaceConfig.Default {

        @Comment("Minimal aura.")
        public double minAura = 1000.0F;

        @Comment("Maximum aura.")
        public double maxAura = 2000.0F;

        @Comment("Minimal magicule.")
        public double minMagicule = 4000.0F;

        @Comment("Maximum magicule.")
        public double maxMagicule = 6000.0F;

        @Comment("Bonus Size.")
        public double size = 0.0F;

        @Comment("Bonus Max Health.")
        public double maxHealth = 10.0F;

        @Comment("Bonus Max Spiritual Health.")
        public double maxSpiritualHealth = 40.0F;

        @Comment("Bonus Attack Damage.")
        public double attack = 0.4F;

        @Comment("Bonus Attack Speed.")
        public double attackSpeed = 0.1F;

        @Comment("Bonus Knockback Resistance.")
        public double knockbackResistance = 0.0F;

        @Comment("Bonus Movement Speed.")
        public double movementSpeed = 0.01F;

        @Comment("Bonus Swimming Speed Multiplier.")
        public double swimSpeed = 0.1F;


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

    public static class AirGenasiRace extends RaceConfig.Default {

        @Comment("EP requirement to evolve into Air Genasi.")
        public double epRequirement = 200000.0F;

        @Comment("Minimal aura.")
        public double minAura = 20000.0F;

        @Comment("Maximum aura.")
        public double maxAura = 30000.0F;

        @Comment("Minimal magicule.")
        public double minMagicule = 70000.0F;

        @Comment("Maximum magicule.")
        public double maxMagicule = 80000.0F;

        @Comment("Bonus Size.")
        public double size = 0.0F;

        @Comment("Bonus Max Health.")
        public double maxHealth = 70.0F;

        @Comment("Bonus Max Spiritual Health.")
        public double maxSpiritualHealth = 400.0F;

        @Comment("Bonus Attack Damage.")
        public double attack = 1.2F;

        @Comment("Bonus Attack Speed.")
        public double attackSpeed = 0.4F;

        @Comment("Bonus Knockback Resistance.")
        public double knockbackResistance = 0.2F;

        @Comment("Bonus Movement Speed.")
        public double movementSpeed = 0.06F;

        @Comment("Bonus Swimming Speed Multiplier.")
        public double swimSpeed = 0.3F;


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

    public static class EarthGenasiRace extends RaceConfig.Default {

        @Comment("EP requirement to evolve into Earth Genasi.")
        public double epRequirement = 200000.0F;

        @Comment("Minimal aura.")
        public double minAura = 35000.0F;

        @Comment("Maximum aura.")
        public double maxAura = 45000.0F;

        @Comment("Minimal magicule.")
        public double minMagicule = 50000.0F;

        @Comment("Maximum magicule.")
        public double maxMagicule = 70000.0F;

        @Comment("Bonus Size.")
        public double size = 0.0F;

        @Comment("Bonus Max Health.")
        public double maxHealth = 70.0F;

        @Comment("Bonus Max Spiritual Health.")
        public double maxSpiritualHealth = 400.0F;

        @Comment("Bonus Attack Damage.")
        public double attack = 1.8F;

        @Comment("Bonus Attack Speed.")
        public double attackSpeed = 0.1F;

        @Comment("Bonus Knockback Resistance.")
        public double knockbackResistance = 0.45F;

        @Comment("Bonus Movement Speed.")
        public double movementSpeed = 0.01F;

        @Comment("Bonus Swimming Speed Multiplier.")
        public double swimSpeed = 0.1F;


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

    public static class FireGenasiRace extends RaceConfig.Default {

        @Comment("EP requirement to evolve into Fire Genasi.")
        public double epRequirement = 200000.0F;

        @Comment("Minimal aura.")
        public double minAura = 30000.0F;

        @Comment("Maximum aura.")
        public double maxAura = 40000.0F;

        @Comment("Minimal magicule.")
        public double minMagicule = 60000.0F;

        @Comment("Maximum magicule.")
        public double maxMagicule = 70000.0F;

        @Comment("Bonus Size.")
        public double size = 0.0F;

        @Comment("Bonus Max Health.")
        public double maxHealth = 70.0F;

        @Comment("Bonus Max Spiritual Health.")
        public double maxSpiritualHealth = 400.0F;

        @Comment("Bonus Attack Damage.")
        public double attack = 2.0F;

        @Comment("Bonus Attack Speed.")
        public double attackSpeed = 0.3F;

        @Comment("Bonus Knockback Resistance.")
        public double knockbackResistance = 0.25F;

        @Comment("Bonus Movement Speed.")
        public double movementSpeed = 0.03F;

        @Comment("Bonus Swimming Speed Multiplier.")
        public double swimSpeed = 0.1F;


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

    public static class WaterGenasiRace extends RaceConfig.Default {

        @Comment("EP requirement to evolve into Water Genasi.")
        public double epRequirement = 200000.0F;

        @Comment("Minimal aura.")
        public double minAura = 25000.0F;

        @Comment("Maximum aura.")
        public double maxAura = 35000.0F;

        @Comment("Minimal magicule.")
        public double minMagicule = 65000.0F;

        @Comment("Maximum magicule.")
        public double maxMagicule = 75000.0F;

        @Comment("Bonus Size.")
        public double size = 0.0F;

        @Comment("Bonus Max Health.")
        public double maxHealth = 70.0F;

        @Comment("Bonus Max Spiritual Health.")
        public double maxSpiritualHealth = 400.0F;

        @Comment("Bonus Attack Damage.")
        public double attack = 1.3F;

        @Comment("Bonus Attack Speed.")
        public double attackSpeed = 0.2F;

        @Comment("Bonus Knockback Resistance.")
        public double knockbackResistance = 0.2F;

        @Comment("Bonus Movement Speed.")
        public double movementSpeed = 0.03F;

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

    public static class AncientGenasiRace extends RaceConfig.Default {

        @Comment("EP requirement to evolve into Ancient Genasi.")
        public double epRequirement = 1000000.0F;

        @Comment("Minimal aura.")
        public double minAura = 200000.0F;

        @Comment("Maximum aura.")
        public double maxAura = 300000.0F;

        @Comment("Minimal magicule.")
        public double minMagicule = 700000.0F;

        @Comment("Maximum magicule.")
        public double maxMagicule = 800000.0F;

        @Comment("Bonus Size.")
        public double size = 0.0F;

        @Comment("Bonus Max Health.")
        public double maxHealth = 500.0F;

        @Comment("Bonus Max Spiritual Health.")
        public double maxSpiritualHealth = 3500.0F;

        @Comment("Bonus Attack Damage.")
        public double attack = 3.5F;

        @Comment("Bonus Attack Speed.")
        public double attackSpeed = 0.5F;

        @Comment("Bonus Knockback Resistance.")
        public double knockbackResistance = 0.5F;

        @Comment("Bonus Movement Speed.")
        public double movementSpeed = 0.08F;

        @Comment("Bonus Swimming Speed Multiplier.")
        public double swimSpeed = 0.8F;


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

    public static class DivineGenasiRace extends RaceConfig.Default {

        @Comment("EP requirement to evolve into Divine Genasi.")
        public double epRequirement = 2000000.0F;

        @Comment("Minimal aura.")
        public double minAura = 700000.0F;

        @Comment("Maximum aura.")
        public double maxAura = 900000.0F;

        @Comment("Minimal magicule.")
        public double minMagicule = 1100000.0F;

        @Comment("Maximum magicule.")
        public double maxMagicule = 1300000.0F;

        @Comment("Bonus Size.")
        public double size = 0.0F;

        @Comment("Bonus Max Health.")
        public double maxHealth = 950.0F;

        @Comment("Bonus Max Spiritual Health.")
        public double maxSpiritualHealth = 6500.0F;

        @Comment("Bonus Attack Damage.")
        public double attack = 5.0F;

        @Comment("Bonus Attack Speed.")
        public double attackSpeed = 0.7F;

        @Comment("Bonus Knockback Resistance.")
        public double knockbackResistance = 0.7F;

        @Comment("Bonus Movement Speed.")
        public double movementSpeed = 0.1F;

        @Comment("Bonus Swimming Speed Multiplier.")
        public double swimSpeed = 1.0F;


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