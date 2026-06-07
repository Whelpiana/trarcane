package org.adiris.trarcane.registry.skill;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.manasmods.manascore.skill.api.ManasSkill;
import io.github.manasmods.manascore.skill.api.SkillAPI;
import net.minecraft.resources.ResourceLocation;
import org.adiris.trarcane.ability.skill.ultimate.MegaDisintegrationSkill;


import java.util.function.Supplier;

import static org.adiris.trarcane.Trarcane.MOD_ID;

public class TrArcaneUltimateSkills {


    public static final RegistrySupplier<ManasSkill> MEGA_DISINTEGRATION = register("mega_disintegration", MegaDisintegrationSkill::new);

    private static <E extends ManasSkill> RegistrySupplier<E> register(String name, Supplier<E> supplier) {
        return SkillAPI.getSkillRegistry().register(ResourceLocation.fromNamespaceAndPath(MOD_ID, name), supplier);
    }

    public static void init() {
    }
}

