package me.theboiboi8.abyssal.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class AbyssalConfusionEffect extends StatusEffect {
	public AbyssalConfusionEffect() {
		super(StatusEffectType.HARMFUL, 0x70d38b);
	}

	@Override
	public boolean shouldApplyUpdateEffect(int tick, int amplifier) {
		return true;
	}

	@Override
	public void applyUpdateEffect(LivingEntity entity, int amplifier) {
		if (entity instanceof PlayerEntity) {
			((PlayerEntity) entity).addExperience(1 << amplifier);
		}
	}
}
