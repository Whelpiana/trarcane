package org.adiris.trarcane.registry.item;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.adiris.trarcane.item.custom.*;

import static org.adiris.trarcane.Trarcane.MOD_ID;


public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> LICH_BOOK =
            ITEMS.register("lich_book",
                    () -> new LichBookItem(new Item.Properties().stacksTo(1)));

    public static final RegistrySupplier<Item> ELEMENTAL_BUNDLE = ITEMS.register("elemental_bundle",
            () -> new ElementalBundle(new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> LIGHT_DARK_BUNDLE = ITEMS.register("light_dark_bundle",
            () -> new Light_DarkBundle(new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> NATURE_BUNDLE = ITEMS.register("nature_bundle",
            () -> new NatureBundle(new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> SPATIAL_BUNDLE = ITEMS.register("spatial_bundle",
            () -> new SpatialBundle(new Item.Properties().stacksTo(1)));

    public static final RegistrySupplier<Item> PHYLACTERY =
            ITEMS.register("phylactery",
                    () -> new PhylacteryItem(new Item.Properties().stacksTo(1)));

    public static final RegistrySupplier<Item> OTHERWORLDER_RELIC =
            ITEMS.register("otherworlder_relic",
                    () -> new OtherworlderRelicItem(new Item.Properties()));

    public static final RegistrySupplier<Item> LOST_RELIC_OF_TIME =
            ITEMS.register("lostrelicoftime",
                    () -> new LostRelicOfTimeItem(new Item.Properties().stacksTo(1)));

    public static final RegistrySupplier<Item> GAUNTLET =
            ITEMS.register("gauntlet",
                    () -> new GauntletItem(new Item.Properties().stacksTo(1).attributes(ItemAttributeModifiers.builder()
                                                    .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, 20.0D, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build())));

    public static final RegistrySupplier<Item> ECLIPSE_INGOT = ITEMS.register("eclipse_ingot",
            () -> new EclipseIngotItem(new Item.Properties()));

    public static final RegistrySupplier<CoinItem> THE_COIN = ITEMS.register("the_coin",
            () -> new CoinItem(new Item.Properties()));

    public static final RegistrySupplier<CalamityStaffItem> CALAMITY_STAFF =
            ITEMS.register("calamity_staff", CalamityStaffItem::new);




    public static void registerModItems() {
        ITEMS.register();

    }
}
