package org.adiris.trarcane.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.adiris.trarcane.registry.item.ModItems;

import static org.adiris.trarcane.Trarcane.MOD_ID;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> TRARCANE_TAB =
            CREATIVE_TABS.register("trarcane", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                    .title(Component.translatable("itemGroup.trarcane"))
                    .icon(() -> new ItemStack(ModItems.ECLIPSE_INGOT.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.LICH_BOOK.get());
                        output.accept(ModItems.ELEMENTAL_BUNDLE.get());
                        output.accept(ModItems.LIGHT_DARK_BUNDLE.get());
                        output.accept(ModItems.NATURE_BUNDLE.get());
                        output.accept(ModItems.SPATIAL_BUNDLE.get());
                        output.accept(ModItems.PHYLACTERY.get());
                        output.accept(ModItems.OTHERWORLDER_RELIC.get());
                        output.accept(ModItems.GAUNTLET.get());
                        output.accept(ModItems.ECLIPSE_INGOT.get());
                        output.accept(ModItems.CALAMITY_STAFF.get());
                        output.accept(ModItems.THE_COIN.get());
                        output.accept(ModItems.LOST_RELIC_OF_TIME.get());
                    })
                    .build());

    public static void registerModCreativeTabs() {
        CREATIVE_TABS.register();
    }
}
