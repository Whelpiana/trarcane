package org.adiris.trarcane.item.custom;

import io.github.manasmods.manascore.config.ConfigRegistry;
import io.github.manasmods.tensura.item.TensuraToolTiers;
import io.github.manasmods.tensura.item.weapon.spell.SimpleSpellCastItem;
import org.adiris.trarcane.config.item.TrArcaneItemConfig;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class CalamityStaffItem extends SimpleSpellCastItem {
    private static final TrArcaneItemConfig.CalamityStaff CONFIG =
            ((TrArcaneItemConfig) ConfigRegistry.getConfig(TrArcaneItemConfig.class)).CalamityStaff;

    public CalamityStaffItem() {
        super(
                5,
                CONFIG.magicSlots,
                TensuraToolTiers.PURE_MAGISTEEL,
                CONFIG.cooldown,
                CONFIG.chantSpeed,
                new Item.Properties()
                        .durability(300)
                        .rarity(Rarity.EPIC)
        );
    }

}
