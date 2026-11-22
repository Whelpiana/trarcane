package org.adiris.trarcane.ability.skill.extra;

import io.github.manasmods.manascore.config.ConfigRegistry;
import io.github.manasmods.manascore.skill.api.ManasSkillInstance;
import io.github.manasmods.tensura.ability.magic.Element;
import io.github.manasmods.tensura.ability.skill.Skill;
import io.github.manasmods.tensura.item.misc.ElementCoreItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemLore;
import org.adiris.trarcane.config.skill.TrArcaneExtraSkillConfig;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ClairvoyanceSkill extends Skill {
    public static final TrArcaneExtraSkillConfig.Clairvoyance CONFIG =
            ((TrArcaneExtraSkillConfig) ConfigRegistry.getConfig(TrArcaneExtraSkillConfig.class)).Clairvoyance;

    public ClairvoyanceSkill() {
        super(SkillType.EXTRA);
    }

    @Override
    public @Nullable ResourceLocation getSkillIcon() {
        return ResourceLocation.fromNamespaceAndPath(
                "trarcane",
                "textures/extra/clairvoyance.png"
        );
    }

    @Override
    public boolean checkAcquiringRequirement(Player player, double newEP) {
        return newEP >= CONFIG.epAcquirement;
    }

    @Override
    public int getModes(ManasSkillInstance instance) {
        return 1;
    }

    @Override
    public String getModeId(ManasSkillInstance instance, int mode) {
        return "clairvoyance";
    }

    @Override
    public void onPressed(ManasSkillInstance instance, LivingEntity entity, int keyNumber, int mode) {
        if (!(entity instanceof ServerPlayer player)) return;

        for (InteractionHand hand : InteractionHand.values()) {
            ItemStack stack = player.getItemInHand(hand);
            if (!(stack.getItem() instanceof ElementCoreItem core)
                    || core.getElement() != Element.UNIDENTIFIED
                    || !stack.has(DataComponents.CUSTOM_NAME)) {
                continue;
            }

            String playerName = stack.getHoverName().getString().trim();
            if (playerName.isEmpty()) return;

            ServerPlayer target = Objects.requireNonNull(player.getServer()).getPlayerList().getPlayerByName(playerName);
            if (target == null) return;

            String coordinates = String.format(
                    Locale.ROOT,
                    "%.2f, %.2f, %.2f",
                    target.getX(),
                    target.getY(),
                    target.getZ()
            );
            String dimension = target.level().dimension().location().toString();
            Component lore = Component.literal(
                    playerName + " has been seen inside " + dimension + " at coordinates " + coordinates
            );
            stack.set(DataComponents.LORE, new ItemLore(List.of(lore)));
            return;
        }
    }
}
