package org.adiris.trarcane.ability.skill.unique;

import io.github.manasmods.manascore.skill.api.ManasSkillInstance;
import io.github.manasmods.manascore.skill.api.SkillAPI;
import io.github.manasmods.manascore.skill.impl.SkillStorage;
import io.github.manasmods.tensura.ability.skill.Skill;
import io.github.manasmods.tensura.registry.attribute.TensuraAttributes;
import io.github.manasmods.tensura.registry.attribute.TensuraGlobalAttributeIds;
import io.github.manasmods.tensura.registry.sound.TensuraSoundEvents;
import io.github.manasmods.tensura.storage.TensuraStorages;
import io.github.manasmods.tensura.storage.ability.IAbility;
import io.github.manasmods.tensura.storage.player.ITensuraPlayer;
import io.github.manasmods.tensura.util.AttributeHelper;
import io.github.manasmods.tensura.util.ObjectSelectionHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.jetbrains.annotations.Nullable;

public class InvestigatorSkill extends Skill {

    private static final ResourceLocation INVESTIGATOR_ACCELERATION =
            ResourceLocation.fromNamespaceAndPath(
                    "trarcane",
                    "investigator_acceleration"
            );

    private static final double CHANT_SPEED = 2.0;
    private static final double MOVEMENT_SPEED = 0.01;
    private static final double MOVEMENT_SPEED_MASTERED = 0.02;
    private static final double ATTACK_SPEED = 0.2;
    private static final double ATTACK_SPEED_MASTERED = 0.4;
    private static final int DODGE_INVULNERABILITY = 1;

    public InvestigatorSkill() {
        super(SkillType.UNIQUE);
    }

    @Override
    public double getDefaultAcquiringMagiculeCost() {
        return 50000F;
    }

    @Override
    public int getModes(ManasSkillInstance instance) {
        return 3;
    }

    @Override
    protected boolean canActivateInRaceLimit(ManasSkillInstance instance, int mode) {
        return true;
    }

    @Override
    public @Nullable ResourceLocation getSkillIcon() {
        return ResourceLocation.fromNamespaceAndPath(
                "trarcane",
                "textures/uniques/investigator.png"
        );
    }

    @Override
    public String getModeId(ManasSkillInstance instance, int mode) {
        return switch (mode) {
            case 0 -> "investigatoranalyticalappraisal";
            case 1 -> "investigatorpursuitoftruth";
            default -> "investigatorthoughtacceleration";
        };
    }

    @Override
    public int nextMode(LivingEntity entity, ManasSkillInstance instance, int mode, boolean reverse) {
        int max = getModes(instance) - 1;

        if (reverse) {
            return mode <= 0 ? max : mode - 1;
        } else {
            return mode >= max ? 0 : mode + 1;
        }
    }

    @Override
    public boolean canBeToggled(ManasSkillInstance instance, LivingEntity entity) {
        return true;
    }


    private MutableComponent styled(MutableComponent msg) {
        return msg.withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_AQUA));
    }


    @Override
    public void onToggleOn(ManasSkillInstance instance, LivingEntity entity) {

        AttributeHelper.addPresenceSense(entity, 4.0);

        AttributeInstance movement = entity.getAttribute(Attributes.MOVEMENT_SPEED);
        if (movement != null && !movement.hasModifier(INVESTIGATOR_ACCELERATION)) {
            movement.addOrReplacePermanentModifier(
                    new AttributeModifier(
                            INVESTIGATOR_ACCELERATION,
                            instance.isMastered(entity)
                                    ? MOVEMENT_SPEED_MASTERED
                                    : MOVEMENT_SPEED,
                            AttributeModifier.Operation.ADD_VALUE
                    )
            );
        }

        AttributeInstance attack = entity.getAttribute(Attributes.ATTACK_SPEED);
        if (attack != null && !attack.hasModifier(INVESTIGATOR_ACCELERATION)) {
            attack.addOrReplacePermanentModifier(
                    new AttributeModifier(
                            INVESTIGATOR_ACCELERATION,
                            instance.isMastered(entity)
                                    ? ATTACK_SPEED_MASTERED
                                    : ATTACK_SPEED,
                            AttributeModifier.Operation.ADD_VALUE
                    )
            );
        }

        AttributeInstance chant = entity.getAttribute(TensuraAttributes.CHANT_SPEED);
        if (chant != null && !chant.hasModifier(INVESTIGATOR_ACCELERATION)) {
            chant.addOrReplacePermanentModifier(
                    new AttributeModifier(
                            INVESTIGATOR_ACCELERATION,
                            CHANT_SPEED,
                            AttributeModifier.Operation.ADD_VALUE
                    )
            );
        }

        if (instance.isMastered(entity)) {
            AttributeInstance dodge = entity.getAttribute(TensuraAttributes.DODGE_INVULNERABILITY);
            if (dodge != null) {
                dodge.addOrReplacePermanentModifier(
                        new AttributeModifier(
                                INVESTIGATOR_ACCELERATION,
                                DODGE_INVULNERABILITY,
                                AttributeModifier.Operation.ADD_VALUE
                        )
                );
            }
        }
    }


    @Override
    public void onToggleOff(ManasSkillInstance instance, LivingEntity entity) {

        AttributeHelper.removePresenceSense(entity, 6.0);

        for (var attr : new AttributeInstance[]{
                entity.getAttribute(Attributes.MOVEMENT_SPEED),
                entity.getAttribute(Attributes.ATTACK_SPEED),
                entity.getAttribute(TensuraAttributes.CHANT_SPEED),
                entity.getAttribute(TensuraAttributes.DODGE_INVULNERABILITY)
        }) {
            if (attr != null) {
                attr.removeModifier(INVESTIGATOR_ACCELERATION);
            }
        }

        if (entity instanceof ServerPlayer player) {
            AttributeHelper.removeAnalysisAttributes(player, true, true, false);
        }
    }


    @Override
    public void onPressed(ManasSkillInstance instance, LivingEntity entity, int keyNumber, int mode) {

        if (!(entity instanceof ServerPlayer player)) return;

        if (mode == 0) {

            if (player.isShiftKeyDown()) {

                ITensuraPlayer data = TensuraStorages.getPlayerDataFrom(player);

                int next = switch (data.getAnalysisMode()) {
                    case 1 -> 2;
                    case 2 -> 0;
                    default -> 1;
                };

                data.setAnalysisMode(next);

                MutableComponent msg = switch (next) {
                    case 1 -> Component.translatable("tensura.skill.analytical.analyzing_mode.entity");
                    case 2 -> Component.translatable("tensura.skill.analytical.analyzing_mode.block");
                    default -> Component.translatable("tensura.skill.analytical.analyzing_mode.both");
                };

                player.displayClientMessage(styled(msg), true);
                player.playNotifySound(
                        (SoundEvent) TensuraSoundEvents.GENERIC_CAST.get(),
                        SoundSource.PLAYERS,
                        1.0F,
                        1.0F
                );

                data.markDirty();
                return;
            }

            AttributeInstance level = player.getAttribute(TensuraAttributes.ANALYSIS_LEVEL);

            if (level != null && level.hasModifier(TensuraGlobalAttributeIds.ANALYSIS)) {
                AttributeHelper.removeAnalysisAttributes(player, true, true, false);
            } else {
                AttributeHelper.addAnalysisAttributes(
                        player,
                        instance.isMastered(entity) ? 4 : 1,
                        instance.isMastered(entity) ? 6 : 2
                );
            }

            player.playNotifySound(
                    (SoundEvent) TensuraSoundEvents.GENERIC_CAST.get(),
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F
            );
        }

        if (mode == 1) {

            CompoundTag tag = instance.getOrCreateTag();

            if (player.isShiftKeyDown()) {

                int pursuitMode = (tag.getInt("PursuitMode") + 1) % 6;
                tag.putInt("PursuitMode", pursuitMode);

                MutableComponent msg = switch (pursuitMode) {
                    case 1 -> Component.literal("Intrinsic Skills");
                    case 2 -> Component.literal("Common Skills");
                    case 3 -> Component.literal("Unique Skills");
                    case 4 -> Component.literal("Ultimate Skills");
                    case 5 -> Component.literal("Resistance Skills");
                    default -> Component.literal("Extra Skills");
                };

                player.displayClientMessage(styled(msg), true);

                player.playNotifySound(
                        (SoundEvent) TensuraSoundEvents.GENERIC_CAST.get(),
                        SoundSource.PLAYERS,
                        1.0F,
                        1.0F
                );
                return;
            }

            pursuitOfTruth(player, instance);
        }

        if (mode == 2) {
            boolean currentToggledState = instance.isToggled();

            instance.setToggled(!currentToggledState);

            if (!currentToggledState) {
                onToggleOn(instance, player);
                player.displayClientMessage(styled(Component.literal("Thought Acceleration Activated")), true);
            } else {
                onToggleOff(instance, player);
                player.displayClientMessage(styled(Component.literal("Thought Acceleration Deactivated")), true);
            }

            player.playNotifySound(
                    (SoundEvent) TensuraSoundEvents.GENERIC_CAST.get(),
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F
            );
        }

    }


    private void pursuitOfTruth(ServerPlayer player, ManasSkillInstance instance) {

        double distance = 12.0;

        Entity targetEntity = ObjectSelectionHelper.getTargetingEntity(
                player, distance, false, true
        );

        if (!(targetEntity instanceof LivingEntity target)) return;

        SkillStorage storage = SkillAPI.getSkillsFrom(target);
        IAbility ability = TensuraStorages.getAbilityFrom(target);

        int activePreset = ability.getActivePreset();
        int pursuitMode = instance.getOrCreateTag().getInt("PursuitMode");

        player.sendSystemMessage(styled(Component.literal("==========")));

        for (ManasSkillInstance skillInstance : storage.getLearnedSkills()) {

            if (!(skillInstance.getSkill() instanceof Skill skill)) continue;

            SkillType type = skill.getType();

            boolean valid = switch (pursuitMode) {
                case 1 -> type == SkillType.INTRINSIC;
                case 2 -> type == SkillType.COMMON;
                case 3 -> type == SkillType.UNIQUE;
                case 4 -> type == SkillType.ULTIMATE;
                case 5 -> type == SkillType.RESISTANCE;
                default -> type == SkillType.EXTRA;
            };

            if (!valid) continue;

            boolean equipped = ability.isAbilityInActivePreset(skill);

            ResourceLocation id = skill.getRegistryName();
            if (id == null) continue;

            MutableComponent msg = equipped
                    ? Component.literal("[EQUIPPED] " + id)
                    : Component.literal(id.toString()).withStyle(type.getChatFormatting());

            player.sendSystemMessage(msg);
        }

        player.sendSystemMessage(
                styled(Component.literal("Preset: " + ability.getPresetName(activePreset)))
        );

        player.sendSystemMessage(styled(Component.literal("==========")));
    }
}