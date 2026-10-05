package org.adiris.trarcane.registry.item;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.adiris.trarcane.item.custom.EclipseIngotItem;
import org.adiris.trarcane.item.custom.GauntletItem;
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

    public static final RegistrySupplier<Item> GAUNTLET =
            ITEMS.register("gauntlet",
                    () -> new GauntletItem(new Item.Properties().stacksTo(1).attributes(ItemAttributeModifiers.builder()
                                                    .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, 20.0D, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build())));

    public static final RegistrySupplier<Item> ECLIPSE_INGOT = ITEMS.register("eclipse_ingot",
            () -> new EclipseIngotItem(new Item.Properties()));

    public static void registerModItems() {
        ITEMS.register();

    }
}