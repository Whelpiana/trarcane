package org.adiris.trarcane.handler;

import dev.architectury.event.EventResult;
import io.github.manasmods.manascore.skill.api.EntityEvents;
import io.github.manasmods.tensura.ability.SkillUtils;
import io.github.manasmods.tensura.entity.human.undead.SkeletonHumanoidEntity;
import io.github.manasmods.tensura.entity.human.undead.ZombieHumanoidEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.*;
import org.adiris.trarcane.registry.skill.TrArcaneUniqueSkills;

public class NecroLordHandler {

    public static void init() {


        EntityEvents.LIVING_CHANGE_TARGET_EARLY.register((entity, changeableTarget) -> {

            if (!(entity instanceof Mob mob)) return EventResult.pass();
            if (!changeableTarget.isPresent()) return EventResult.pass();

            LivingEntity target = changeableTarget.get();
            if (target == null) return EventResult.pass();

            if (!SkillUtils.hasSkill(target, TrArcaneUniqueSkills.NECRO_LORD.get())) {
                return EventResult.pass();
            }

            if (mob instanceof Zombie
                    || mob instanceof Husk
                    || mob instanceof Drowned
                    || mob instanceof ZombifiedPiglin
                    || mob instanceof AbstractSkeleton
                    || mob instanceof WitherSkeleton
                    || mob instanceof ZombieHumanoidEntity
                    || mob instanceof SkeletonHumanoidEntity
                    || mob instanceof Stray) {

                changeableTarget.set(null);
                return EventResult.interruptFalse();
            }

            return EventResult.pass();
        });
    }
}