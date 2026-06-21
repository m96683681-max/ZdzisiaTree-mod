package com.zdzisia.tree;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zdzisia.tree.block.ZdzisiaBlocks;
import com.zdzisia.tree.item.ZdzisiaItems;
import com.zdzisia.tree.worldgen.ZdzisiaWorldGen;

public class ZdzisiaTreeMod implements ModInitializer {
	public static final String MOD_ID = "zdzisia_tree";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Zdzisia Tree Mod");
		
		ZdzisiaBlocks.register();
		ZdzisiaItems.register();
		ZdzisiaWorldGen.register();
		
		LOGGER.info("Zdzisia Tree Mod initialized successfully!");
	}
}
