package me.theboiboi8.abyssal;

import me.theboiboi8.abyssal.effects.AbyssalConfusionEffect;
import me.theboiboi8.abyssal.items.AbyssCarrotItem;
import me.theboiboi8.abyssal.items.AbyssIngotItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Abyssal implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(Abyssal.class);

	public static final StatusEffect ABYSSAL_CONFUSION = new AbyssalConfusionEffect();

	public static final Item ABYSS_INGOT = new AbyssIngotItem(new QuiltItemSettings());
	public static final Item ABYSS_CARROT = new AbyssCarrotItem(new QuiltItemSettings().food(
		new FoodComponent.Builder()
			.hunger(6)
			.saturationModifier(1)
			.snack()
			.alwaysEdible()
			.statusEffect(new StatusEffectInstance(ABYSSAL_CONFUSION, 10), 0.5F)
			.build()
	));

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Initialized {}", mod.metadata().name());

		Registry.register(
			Registries.ITEM,
			new Identifier(mod.metadata().id(), "abyss_ingot"),
			ABYSS_INGOT
		);
		Registry.register(
			Registries.ITEM,
			new Identifier(mod.metadata().id(), "abyss_carrot"),
			ABYSS_CARROT
		);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> entries.addItem(ABYSS_INGOT));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINKS).register(entries -> entries.addItem(ABYSS_CARROT));
	}
}
