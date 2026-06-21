package com.zdzisia.tree.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.structure.Structure;

import com.zdzisia.tree.ZdzisiaTreeMod;

public class ZdzisiaWorldGen {
	
	public static final RegistryKey<Structure> ZDZISIA_TREE_KEY = 
		RegistryKey.of(RegistryKeys.STRUCTURE, new Identifier(ZdzisiaTreeMod.MOD_ID, "zdzisia_tree"));
	
	public static void register() {
		// WorldGen registration handled by structure feature
	}
}
