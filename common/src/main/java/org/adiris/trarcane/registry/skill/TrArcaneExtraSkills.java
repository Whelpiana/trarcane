package org.adiris.trarcane.registry.skill;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.manasmods.manascore.skill.api.ManasSkill;
import io.github.manasmods.manascore.skill.api.SkillAPI;
import net.minecraft.resources.ResourceLocation;
import org.adiris.trarcane.ability.skill.extra.CelestialEyeSkill;
import org.adiris.trarcane.ability.skill.extra.RestSkill;
import org.adiris.trarcane.ability.skill.extra.ReplenishSkill;

import java.util.function.Supplier;

import static org.adiris.trarcane.Trarcane.MOD_ID;


public class TrArcaneExtraSkills {

    public static final RegistrySupplier<CelestialEyeSkill> CELESTIAL_EYE = register("celestial_eye", CelestialEyeSkill::new);
    public static final RegistrySupplier<ReplenishSkill> REPLENISH = register("replenish", ReplenishSkill::new);
    public static final RegistrySupplier<RestSkill> REST = register("rest", RestSkill::new);

    private static <E extends ManasSkill> RegistrySupplier<E> register(String name, Supplier<E> supplier) {
        return SkillAPI.getSkillRegistry().register(ResourceLocation.fromNamespaceAndPath(MOD_ID, name), supplier);
    }

    public static void init() {

    }

}
