package org.adiris.trarcane.config.race;

import io.github.manasmods.manascore.config.api.Comment;
import io.github.manasmods.manascore.config.api.ManasConfig;
import io.github.manasmods.manascore.config.api.SyncToClient;
import io.github.manasmods.tensura.config.race.RaceConfig;

@SyncToClient
public class TieflingConfig extends ManasConfig {

    public TieflingRace TieflingRace = new TieflingRace();

    public AbyssalTieflingRace AbyssalTieflingRace = new AbyssalTieflingRace();
    public ChthonicTieflingRace ChthonicTieflingRace = new ChthonicTieflingRace();
    public InfernalTieflingRace InfernalTieflingRace = new InfernalTieflingRace();

    public GreaterTieflingRace GreaterTieflingRace = new GreaterTieflingRace();
    public DivineTieflingRace DivineTieflingRace = new DivineTieflingRace();

    @Override
    public String getFileName() {
        return "tensura/race/tratiefling_config";
    }

    public static class TieflingRace extends RaceConfig.Default {

        @Comment("Minimal aura.")
        public double minAura = 4000.0D;

        @Comment("Maximum aura.")
        public double maxAura = 7000.0D;

        @Comment("Minimal magicule.")
        public double minMagicule = 8000.0D;

        @Comment("Maximum magicule.")
        public double maxMagicule = 12000.0D;

        @Comment("Bonus Size.")
        public double size = 0.15D;

        @Comment("Bonus Max Health.")
        public double maxHealth = 30.0D;

        @Comment("Bonus Max Spiritual Health.")
        public double maxSpiritualHealth = 120.0D;

        @Comment("Bonus Attack Damage.")
        public double attack = 0.8D;

        @Comment("Bonus Attack Speed.")
        public double attackSpeed = -0.1D;

        @Comment("Bonus Knockback Resistance.")
        public double knockbackResistance = 0.15D;

        @Comment("Bonus Movement Speed.")
        public double movementSpeed = 0.0D;

        @Comment("Bonus Swimming Speed Multiplier.")
        public double swimSpeed = 0.0D;



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



    public static class AbyssalTieflingRace extends RaceConfig.Default {

        @Comment("EP requirement to evolve into Abyssal Tiefling.")
        public double epRequirement = 200000.0D;

        @Comment("Minimal aura.")
        public double minAura = 20000.0D;

        @Comment("Maximum aura.")
        public double maxAura = 60000.0D;

        @Comment("Minimal magicule.")
        public double minMagicule = 40000.0D;

        @Comment("Maximum magicule.")
        public double maxMagicule = 140000.0D;

        @Comment("Bonus Size.")
        public double size = 0.15D;

        @Comment("Bonus Max Health.")
        public double maxHealth = 90.0D;

        @Comment("Bonus Max Spiritual Health.")
        public double maxSpiritualHealth = 450.0D;

        @Comment("Bonus Attack Damage.")
        public double attack = 1.5D;

        @Comment("Bonus Attack Speed.")
        public double attackSpeed = 0.1D;

        @Comment("Bonus Knockback Resistance.")
        public double knockbackResistance = 0.3D;

        @Comment("Bonus Movement Speed.")
        public double movementSpeed = 0.01D;

        @Comment("Bonus Swimming Speed Multiplier.")
        public double swimSpeed = 0.05D;



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



    public static class ChthonicTieflingRace extends RaceConfig.Default {

        @Comment("EP requirement to evolve into Chthonic Tiefling.")
        public double epRequirement = 200000.0D;

        @Comment("Minimal aura.")
        public double minAura = 25000.0D;

        @Comment("Maximum aura.")
        public double maxAura = 60000.0D;

        @Comment("Minimal magicule.")
        public double minMagicule = 50000.0D;

        @Comment("Maximum magicule.")
        public double maxMagicule = 140000.0D;

        @Comment("Bonus Size.")
        public double size = 0.15D;

        @Comment("Bonus Max Health.")
        public double maxHealth = 90.0D;

        @Comment("Bonus Max Spiritual Health.")
        public double maxSpiritualHealth = 450.0D;

        @Comment("Bonus Attack Damage.")
        public double attack = 1.8D;

        @Comment("Bonus Attack Speed.")
        public double attackSpeed = 0.15D;

        @Comment("Bonus Knockback Resistance.")
        public double knockbackResistance = 0.4D;

        @Comment("Bonus Movement Speed.")
        public double movementSpeed = 0.02D;

        @Comment("Bonus Swimming Speed Multiplier.")
        public double swimSpeed = 0.1D;



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



    public static class InfernalTieflingRace extends RaceConfig.Default {

        @Comment("EP requirement to evolve into Infernal Tiefling.")
        public double epRequirement = 200000.0D;

        @Comment("Minimal aura.")
        public double minAura = 60000.0D;

        @Comment("Maximum aura.")
        public double maxAura = 60000.0D;

        @Comment("Minimal magicule.")
        public double minMagicule = 140000.0D;

        @Comment("Maximum magicule.")
        public double maxMagicule = 140000.0D;

        @Comment("Bonus Size.")
        public double size = 0.15D;

        @Comment("Bonus Max Health.")
        public double maxHealth = 90.0D;

        @Comment("Bonus Max Spiritual Health.")
        public double maxSpiritualHealth = 450.0D;

        @Comment("Bonus Attack Damage.")
        public double attack = 2.2D;

        @Comment("Bonus Attack Speed.")
        public double attackSpeed = 0.2D;

        @Comment("Bonus Knockback Resistance.")
        public double knockbackResistance = 0.35D;

        @Comment("Bonus Movement Speed.")
        public double movementSpeed = 0.03D;

        @Comment("Bonus Swimming Speed Multiplier.")
        public double swimSpeed = 0.1D;



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



    public static class GreaterTieflingRace extends RaceConfig.Default {

        @Comment("EP requirement to evolve into Greater Tiefling.")
        public double epRequirement = 800000.0D;

        @Comment("Minimal aura.")
        public double minAura = 300000.0D;

        @Comment("Maximum aura.")
        public double maxAura = 300000.0D;

        @Comment("Minimal magicule.")
        public double minMagicule = 500000.0D;

        @Comment("Maximum magicule.")
        public double maxMagicule = 500000.0D;

        @Comment("Bonus Size.")
        public double size = 0.15D;

        @Comment("Bonus Max Health.")
        public double maxHealth = 350.0D;

        @Comment("Bonus Max Spiritual Health.")
        public double maxSpiritualHealth = 1800.0D;

        @Comment("Bonus Attack Damage.")
        public double attack = 3.0D;

        @Comment("Bonus Attack Speed.")
        public double attackSpeed = 0.35D;

        @Comment("Bonus Knockback Resistance.")
        public double knockbackResistance = 0.55D;

        @Comment("Bonus Movement Speed.")
        public double movementSpeed = 0.05D;

        @Comment("Bonus Swimming Speed Multiplier.")
        public double swimSpeed = 0.3D;



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



    public static class DivineTieflingRace extends RaceConfig.Default {

        @Comment("EP requirement to evolve into Divine Tiefling.")
        public double epRequirement = 2000000.0D;

        @Comment("Minimal aura.")
        public double minAura = 800000.0D;

        @Comment("Maximum aura.")
        public double maxAura = 800000.0D;

        @Comment("Minimal magicule.")
        public double minMagicule = 1200000.0D;

        @Comment("Maximum magicule.")
        public double maxMagicule = 1200000.0D;

        @Comment("Bonus Size.")
        public double size = 0.15D;

        @Comment("Bonus Max Health.")
        public double maxHealth = 800.0D;

        @Comment("Bonus Max Spiritual Health.")
        public double maxSpiritualHealth = 5000.0D;

        @Comment("Bonus Attack Damage.")
        public double attack = 4.5D;

        @Comment("Bonus Attack Speed.")
        public double attackSpeed = 0.6D;

        @Comment("Bonus Knockback Resistance.")
        public double knockbackResistance = 0.8D;

        @Comment("Bonus Movement Speed.")
        public double movementSpeed = 0.08D;

        @Comment("Bonus Swimming Speed Multiplier.")
        public double swimSpeed = 0.6D;



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