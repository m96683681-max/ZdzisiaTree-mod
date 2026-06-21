package com.zdzisia.tree.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ZdzisiaAcornItem extends Item {
	
	public ZdzisiaAcornItem(Settings settings) {
		super(settings);
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack itemStack = player.getStackInHand(hand);
		
		if (!world.isClient && player.isSneaking()) {
			// Sneak + right-click: convert acorn to sapling
			itemStack.decrement(1);
			if (!player.getAbilities().creativeMode) {
				player.giveItemStack(new ItemStack(ZdzisiaItems.ZDZISIA_SAPLING));
			}
			return TypedActionResult.success(itemStack, false);
		}
		
		return TypedActionResult.pass(itemStack);
	}
}
