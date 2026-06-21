package com.zdzisia.tree.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import com.zdzisia.tree.ZdzisiaTreeMod;

public class ZdzisiaBlocks {
	
	// Zdzisia Log Block
	public static final Block ZDZISIA_LOG = register(
		"zdzisia_log",
		new LogBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG))
	);
	
	// Zdzisia Leaves Block
	public static final Block ZDZISIA_LEAVES = register(
		"zdzisia_leaves",
		new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES))
	);
	
	private static Block register(String name, Block block) {
		registerBlockItem(name, block);
		return Registry.register(Registries.BLOCK, new Identifier(ZdzisiaTreeMod.MOD_ID, name), block);
	}
	
	private static void registerBlockItem(String name, Block block) {
		Registry.register(Registries.ITEM, new Identifier(ZdzisiaTreeMod.MOD_ID, name),
			new BlockItem(block, new Item.Settings()));
	}
	
	public static void register() {
		// This method is called in the main mod class to ensure all blocks are registered
	}
}
