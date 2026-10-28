package org.adiris.trarcane.config.skill;

import io.github.manasmods.manascore.config.api.Comment;
import io.github.manasmods.manascore.config.api.ManasConfig;
import io.github.manasmods.manascore.config.api.SyncToClient;

import java.util.List;

@SyncToClient
public class ExcavationRewardPoolConfig extends ManasConfig {
    @Comment("Maximum number of Legendary Excavations a player the Unique Skill can complete")
    public int LegendaryExcavations = 3;

    @Comment("Common rewards: item|item id|min amount|max amount|relative weight, or skill|skill id|relative weight")
    public List<String> Common = List.of(
            "item|minecraft:netherite_ingot|2|2|1", "item|tensura:low_magisteel_ingot|2|6|1",
            "item|tensura:dragon_essence|2|4|1", "item|tensura:monster_leather_a|4|6|1",
            "item|tensura:magic_stone|2|4|1");

    @Comment("Rare rewards: item|item id|min amount|max amount|relative weight, or skill|skill id|relative weight")
    public List<String> Rare = List.of(
            "item|tensura:magic_ore|8|16|1", "item|tensura:pure_magisteel_ingot|1|3|1",
            "item|tensura:grade_a_leather|8|16|1", "item|tensura:elemental_essence|2|4|1",
            "item|trarcane:the_coin|1|1|1");

    @Comment("Epic rewards: item|item id|min amount|max amount|relative weight, or skill|skill id|relative weight")
    public List<String> Epic = List.of(
            "item|tensura:pure_magisteel_block|2|4|1", "item|trarcane:magic_staff_schematic|1|1|1",
            "item|tensura:daemon_essence|2|4|1", "item|tensura:marionette_heart|1|2|1",
            "item|tensura:zane_blood|2|3|1",
            "skill|tensura:ultraspeed_regeneration|1", "skill|tensura:black_lightning|1",
            "skill|tensura:ultra_instinct|1", "skill|tensura:charm|1");

    @Comment("Legendary rewards: item|item id|min amount|max amount|relative weight, or skill|skill id|relative weight")
    public List<String> Legendary = List.of(
            "item|trarcane:light_dark_bundle|1|1|1", "item|trarcane:nature_bundle|1|1|1",
            "item|trarcane:spatial_bundle|1|1|1", "item|trarcane:elemental_bundle|1|1|1",
            "item|trarcane:otherworlder_relic|1|1|2");

    @Override
    public String getFileName() {
        return "tensura/excavation_reward_pool";
    }
}
