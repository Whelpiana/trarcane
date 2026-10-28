package org.adiris.trarcane.item.custom;

import io.github.manasmods.tensura.item.TensuraToolTiers;
import io.github.manasmods.tensura.item.weapon.spell.SimpleSpellCastItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class CalamityStaffItem extends SimpleSpellCastItem {

    public CalamityStaffItem() {
        super(
                5,
                8,
                TensuraToolTiers.PURE_MAGISTEEL,
                5,
                1.5,
                new Item.Properties()
                        .durability(300)
                        .rarity(Rarity.EPIC)
        );
    }

}
