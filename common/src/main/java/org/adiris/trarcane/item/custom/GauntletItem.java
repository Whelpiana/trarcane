package org.adiris.trarcane.item.custom;

import io.github.manasmods.tensura.particle.TensuraParticleHelper;
import io.github.manasmods.tensura.particle.TensuraParticleUtils;
import io.github.manasmods.tensura.registry.effect.TensuraMobEffects;
import io.github.manasmods.tensura.registry.sound.TensuraSoundEvents;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class GauntletItem extends Item {

    private static final int RANGE = 15;
    private static final int COOLDOWN = 200;

    public GauntletItem(Properties properties) {
        super(properties);
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.literal("Eclipse Gauntlet")
                .withStyle(ChatFormatting.GRAY);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (player.getCooldowns().isOnCooldown(this)) {
            return InteractionResultHolder.fail(stack);
        }

        if (!level.isClientSide()) {
            Vec3 source = player.position().add(
                    0.0D,
                    player.getEyeHeight(),
                    0.0D
            );

            Vec3 direction = player.getLookAngle().normalize();

            level.playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    TensuraSoundEvents.COERCION.get(),
                    SoundSource.PLAYERS,
                    5.0F,
                    1.0F
            );

            ParticleOptions particle =
                    TensuraParticleUtils.getColorlessSonic(0.75F, 1.5F);

            for (int distance = 1; distance <= RANGE; distance++) {
                Vec3 particlePos = source.add(direction.scale(distance));

                TensuraParticleHelper.spawnServerParticles(
                        level,
                        particle,
                        particlePos.x,
                        particlePos.y,
                        particlePos.z
                );

                AABB aabb = new AABB(
                        new BlockPos(
                                (int) particlePos.x,
                                (int) particlePos.y,
                                (int) particlePos.z
                        )
                ).inflate(2.0D);

                List<LivingEntity> entities = level.getEntitiesOfClass(
                        LivingEntity.class,
                        aabb,
                        entity -> !entity.is(player) && !entity.isAlliedTo(player)
                );

                for (LivingEntity entity : entities) {
                    entity.addEffect(new MobEffectInstance(
                            MobEffects.MOVEMENT_SLOWDOWN,
                            60,2
                    ));

                    entity.addEffect(new MobEffectInstance(
                            Holder.direct(TensuraMobEffects.BURDEN.get()),
                            60,5

                    ));
                }
            }

            player.getCooldowns().addCooldown(this, COOLDOWN);
        }

        return InteractionResultHolder.sidedSuccess(
                stack,
                level.isClientSide()
        );
    }
}
