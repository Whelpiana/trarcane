package org.adiris.trarcane.item.custom;

import io.github.manasmods.manascore.skill.api.ManasSkill;
import io.github.manasmods.manascore.skill.api.SkillAPI;
import io.github.manasmods.tensura.ability.SkillHelper;
import io.github.manasmods.tensura.ability.TensuraSkillInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ElementalBundle extends Item {
    private static final List<String> MAGIC_IDS = List.of("blizzard", "megiddo", "hellfire", "flare_circle");

    public ElementalBundle(Item.Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltip, tooltipFlag);
        tooltip.add(Component.translatable("item.trarcane.spiritual_magic_bundle.use"));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide()) {
            List<ManasSkill> skills = MAGIC_IDS.stream().map(id -> SkillAPI.getSkillRegistry()
                    .get(ResourceLocation.fromNamespaceAndPath("tensura", id))).toList();
            if (skills.stream().anyMatch(skill -> skill == null)) {
                player.sendSystemMessage(Component.translatable("item.trarcane.spiritual_magic_bundle.unavailable"));
                return InteractionResultHolder.fail(stack);
            }
            for (ManasSkill skill : skills) {
                TensuraSkillInstance instance = new TensuraSkillInstance(skill);
                instance.getOrCreateTag().putBoolean("NoMagiculeCost", true);
                SkillHelper.learnSkill(player, instance);
            }
            if (!player.getAbilities().instabuild) stack.shrink(1);
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}
