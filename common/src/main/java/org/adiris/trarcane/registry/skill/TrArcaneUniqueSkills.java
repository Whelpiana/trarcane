package org.adiris.trarcane.registry.skill;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.manasmods.manascore.skill.api.ManasSkill;
import io.github.manasmods.manascore.skill.api.SkillAPI;
import net.minecraft.resources.ResourceLocation;
import org.adiris.trarcane.ability.skill.unique.*;

import java.util.function.Supplier;

import static org.adiris.trarcane.Trarcane.MOD_ID;


public class TrArcaneUniqueSkills {

    public static final RegistrySupplier<NecroLordSkill> NECRO_LORD = register("necro_lord", NecroLordSkill::new);
    public static final RegistrySupplier<MilimEyeSkill> MILIMEYE = register("milim_eye", MilimEyeSkill::new);
    public static final RegistrySupplier<InvestigatorSkill> INVESTIGATOR = register("investigator", InvestigatorSkill::new);
    public static final RegistrySupplier<DisintegrationSkill> DISINTEGRATION = register("disintegration", DisintegrationSkill::new);
    public static final RegistrySupplier<HolySkill> HOLY = register("holy", HolySkill::new);

    private static <E extends ManasSkill> RegistrySupplier<E> register(String name, Supplier<E> supplier) {
        return SkillAPI.getSkillRegistry().register(ResourceLocation.fromNamespaceAndPath(MOD_ID, name), supplier);
    }

    public static void init() {
    }
}