package org.adiris.trarcane.ability.skill.extra;

import io.github.manasmods.manascore.skill.api.ManasSkillInstance;
import io.github.manasmods.tensura.ability.skill.Skill;
import io.github.manasmods.tensura.registry.sound.TensuraSoundEvents;
import io.github.manasmods.tensura.storage.TensuraStorages;
import io.github.manasmods.tensura.storage.ep.IExistence;
import io.github.manasmods.tensura.util.EnergyHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

public class ReplenishSkill extends Skill {

    public ReplenishSkill() {
        super(SkillType.EXTRA);
    }

    @Override
    public @Nullable ResourceLocation getSkillIcon() {
        return ResourceLocation.fromNamespaceAndPath(
                "trarcane",
                "textures/extra/replenish.png"
        );
    }

    @Override
    public boolean checkAcquiringRequirement(Player player, double newEP) {
        return newEP >= 400000;
    }


    @Override
    public void onPressed(ManasSkillInstance instance, LivingEntity entity, int keyNumber, int mode) {

        instance.setCoolDown(600 * 3, mode);

        IExistence existence = TensuraStorages.getExistenceFrom(entity);

        existence.setMagicule(EnergyHelper.getMaxMagicule(entity));
        existence.markDirty();

        entity.level().playSound(
                null,
                entity.getX(),
                entity.getY(),
                entity.getZ(),
                TensuraSoundEvents.BUFF_ACTIVATE.get(),
                SoundSource.PLAYERS,
                1.0F,
                1.0F
        );
    }

}
