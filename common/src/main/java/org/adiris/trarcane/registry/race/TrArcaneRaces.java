package org.adiris.trarcane.registry.race;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.manasmods.manascore.race.api.ManasRace;
import io.github.manasmods.manascore.race.impl.RaceRegistry;
import net.minecraft.resources.ResourceLocation;
import org.adiris.trarcane.race.fairy.*;
import org.adiris.trarcane.race.genasi.*;
import org.adiris.trarcane.race.tiefling.*;


import java.util.function.Supplier;

import static org.adiris.trarcane.Trarcane.MOD_ID;

public class TrArcaneRaces {

    public static final RegistrySupplier<ManasRace> TIEFLING =
            register("tiefling", TieflingRace::new);

    public static final RegistrySupplier<ManasRace> ABYSSAL_TIEFLING =
            register("abyssal_tiefling", AbyssalTieflingRace::new);

    public static final RegistrySupplier<ManasRace> CHTHONIC_TIEFLING =
            register("chthonic_tiefling", ChthonicTieflingRace::new);

    public static final RegistrySupplier<ManasRace> INFERNAL_TIEFLING =
            register("infernal_tiefling", InfernalTieflingRace::new);

    public static final RegistrySupplier<ManasRace> GREATER_TIEFLING =
            register("greater_tiefling", GreaterTieflingRace::new);

    public static final RegistrySupplier<ManasRace> DIVINE_TIEFLING =
            register("divine_tiefling", DivineTieflingRace::new);

    public static final RegistrySupplier<ManasRace> GENASI =
            register("genasi", GenasiRace::new);

    public static final RegistrySupplier<ManasRace> AIR_GENASI =
            register("air_genasi", AirGenasiRace::new);

    public static final RegistrySupplier<ManasRace> EARTH_GENASI =
            register("earth_genasi", EarthGenasiRace::new);

    public static final RegistrySupplier<ManasRace> FIRE_GENASI =
            register("fire_genasi", FireGenasiRace::new);

    public static final RegistrySupplier<ManasRace> WATER_GENASI =
            register("water_genasi", WaterGenasiRace::new);

    public static final RegistrySupplier<ManasRace> ANCIENT_GENASI =
            register("ancient_genasi", AncientGenasiRace::new);

    public static final RegistrySupplier<ManasRace> DIVINE_GENASI =
            register("divine_genasi", DivineGenasiRace::new);

    public static final RegistrySupplier<ManasRace> LESSER_FAIRY =
            register("lesser_fairy", LesserFairyRace::new);

    public static final RegistrySupplier<ManasRace> FAIRY =
            register("fairy", FairyRace::new);

    public static final RegistrySupplier<ManasRace> FAIRY_LORD =
            register("fairy_lord", FairyLordRace::new);

    public static final RegistrySupplier<ManasRace> FAIRY_QUEEN =
            register("fairy_queen", FairyQueenRace::new);


    private static <E extends ManasRace> RegistrySupplier<E> register(String name, Supplier<E> supplier) {
        return RaceRegistry.RACES.register(
                ResourceLocation.fromNamespaceAndPath(MOD_ID, name),
                supplier
        );
    }

    public static void init() {
    }
}