package com.zdzisia.tree.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import com.zdzisia.tree.ZdzisiaTreeMod;

public class ZdzisiaItems {
	
	// Zdzisia Acorn - can be thrown or planted
	public static final Item ZDZISIA_ACORN = register(
		"zdzisia_acorn",
		new ZdzisiaAcornItem(new Item.Settings().maxCount(64))
	);
	
	// Zdzisia Sapling - grows into a tree
	public static final Item ZDZISIA_SAPLING = register(
		"zdzisia_sapling",
		new Item(new Item.Settings().maxCount(64))
	);
	
	private static Item register(String name, Item item) {
		return Registry.register(Registries.ITEM, new Identifier(ZdzisiaTreeMod.MOD_ID, name), item);
	}
	
	public static void register() {
		// This method is called in the main mod class to ensure all items are registered
	}
}
