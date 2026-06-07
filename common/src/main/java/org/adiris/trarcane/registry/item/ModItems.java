package org.adiris.trarcane.registry.item;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import org.adiris.trarcane.item.custom.LichBookItem;
import org.adiris.trarcane.item.custom.PhylacteryItem;

import static org.adiris.trarcane.Trarcane.MOD_ID;


public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> LICH_BOOK =
            ITEMS.register("lich_book",
                    () -> new LichBookItem(new Item.Properties().stacksTo(1)));

    public static final RegistrySupplier<Item> PHYLACTERY =
            ITEMS.register("phylactery",
                    () -> new PhylacteryItem(new Item.Properties().stacksTo(1)));

    public static void registerModItems() {
        ITEMS.register();

    }
}