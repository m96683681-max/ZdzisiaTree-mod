package com.zdzisia.tree.worldgen;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.util.math.random.Random;

import com.zdzisia.tree.block.ZdzisiaBlocks;

public class ZdzisiaTreeGenerator {
	
	private static final BlockState ZDZISIA_LOG = ZdzisiaBlocks.ZDZISIA_LOG.getDefaultState();
	private static final BlockState ZDZISIA_LEAVES = ZdzisiaBlocks.ZDZISIA_LEAVES.getDefaultState();
	private static final BlockState OAK_LOG = Blocks.OAK_LOG.getDefaultState();
	
	public static void generateZdzisiaTree(StructureWorldAccess world, BlockPos origin, Random random) {
		// Tree height: 25-40 blocks
		int treeHeight = 25 + random.nextInt(16);
		
		// === BUILD MAIN TRUNK ===
		buildTrunk(world, origin, treeHeight);
		
		// === BUILD BRANCHES ===
		buildBranches(world, origin, treeHeight, random);
		
		// === BUILD LEAVES CROWN ===
		buildLeafCrown(world, origin, treeHeight);
		
		// === BUILD HOUSE ON BRANCH ===
		int branchHeight = 10 + random.nextInt(10);
		buildHouse(world, origin, branchHeight);
	}
	
	private static void buildTrunk(StructureWorldAccess world, BlockPos origin, int height) {
		// Very thick trunk (3x3)
		for (int y = 0; y < height; y++) {
			for (int x = -1; x <= 1; x++) {
				for (int z = -1; z <= 1; z++) {
					BlockPos pos = origin.add(x, y, z);
					if (world.getBlockState(pos).getMaterial().isReplaceable()) {
						world.setBlockState(pos, ZDZISIA_LOG, 3);
					}
				}
			}
		}
	}
	
	private static void buildBranches(StructureWorldAccess world, BlockPos origin, int treeHeight, Random random) {
		// Generate 4 branches at different heights
		int[] branchHeights = {
			(int)(treeHeight * 0.3),
			(int)(treeHeight * 0.5),
			(int)(treeHeight * 0.7),
			(int)(treeHeight * 0.85)
		};
		
		for (int heightIdx = 0; heightIdx < branchHeights.length; heightIdx++) {
			int branchHeight = branchHeights[heightIdx];
			int direction = heightIdx % 4; // 0=north, 1=east, 2=south, 3=west
			
			int branchLength = 8 + random.nextInt(6);
			buildBranch(world, origin, branchHeight, direction, branchLength);
		}
	}
	
	private static void buildBranch(StructureWorldAccess world, BlockPos origin, int height, int direction, int length) {
		BlockPos branchStart = origin.add(0, height, 0);
		
		// Direction offsets: N, E, S, W
		int[] dx = {0, 1, 0, -1};
		int[] dz = {-1, 0, 1, 0};
		
		int dirX = dx[direction];
		int dirZ = dz[direction];
		
		// Build branch extending outward
		for (int i = 0; i < length; i++) {
			BlockPos pos = branchStart.add(dirX * i, -i / 3, dirZ * i); // Slight downward curve
			
			// Thick branch (2x2)
			for (int x = 0; x < 2; x++) {
				for (int z = 0; z < 2; z++) {
					BlockPos blockPos = pos.add(x, 0, z);
					if (world.getBlockState(blockPos).getMaterial().isReplaceable()) {
						world.setBlockState(blockPos, ZDZISIA_LOG, 3);
					}
				}
			}
		}
	}
	
	private static void buildLeafCrown(StructureWorldAccess world, BlockPos origin, int treeHeight) {
		int crownHeight = treeHeight - 5;
		int crownRadius = 8;
		
		// Sphere of leaves at the top
		for (int x = -crownRadius; x <= crownRadius; x++) {
			for (int y = -crownRadius / 2; y <= crownRadius / 2; y++) {
				for (int z = -crownRadius; z <= crownRadius; z++) {
					double dist = Math.sqrt(x * x + y * y + z * z);
					if (dist <= crownRadius && dist >= crownRadius * 0.6) {
						BlockPos pos = origin.add(x, crownHeight + y, z);
						if (world.getBlockState(pos).getMaterial().isReplaceable()) {
							world.setBlockState(pos, ZDZISIA_LEAVES, 3);
						}
					}
				}
			}
		}
	}
	
	private static void buildHouse(StructureWorldAccess world, BlockPos origin, int branchHeight) {
		// House position: on a branch extending east
		BlockPos houseBase = origin.add(12, branchHeight, 0);
		
		// Clear space for house
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 4; y++) {
				for (int z = 0; z < 5; z++) {
					BlockPos pos = houseBase.add(x, y, z);
					if (world.getBlockState(pos).getMaterial().isReplaceable() || 
						world.getBlockState(pos) == ZDZISIA_LOG) {
						world.setBlockState(pos, Blocks.AIR, 3);
					}
				}
			}
		}
		
		// Walls: oak planks
		BlockState planks = Blocks.OAK_PLANKS.getDefaultState();
		
		// Bottom floor
		for (int x = 0; x < 5; x++) {
			for (int z = 0; z < 5; z++) {
				world.setBlockState(houseBase.add(x, 0, z), planks, 3);
			}
		}
		
		// Walls
		for (int y = 1; y < 3; y++) {
			// Front and back
			for (int x = 0; x < 5; x++) {
				world.setBlockState(houseBase.add(x, y, 0), planks, 3);
				world.setBlockState(houseBase.add(x, y, 4), planks, 3);
			}
			// Left and right
			for (int z = 0; z < 5; z++) {
				world.setBlockState(houseBase.add(0, y, z), planks, 3);
				world.setBlockState(houseBase.add(4, y, z), planks, 3);
			}
		}
		
		// Roof
		for (int x = 0; x < 5; x++) {
			for (int z = 0; z < 5; z++) {
				world.setBlockState(houseBase.add(x, 3, z), Blocks.OAK_STAIRS.getDefaultState(), 3);
			}
		}
		
		// Add loot chests
		world.setBlockState(houseBase.add(1, 1, 1), Blocks.CHEST.getDefaultState(), 3);
		world.setBlockState(houseBase.add(3, 1, 1), Blocks.CHEST.getDefaultState(), 3);
		
		// Add crafting table
		world.setBlockState(houseBase.add(2, 1, 3), Blocks.CRAFTING_TABLE.getDefaultState(), 3);
		
		// Add bed
		world.setBlockState(houseBase.add(1, 1, 3), Blocks.RED_BED.getDefaultState(), 3);
	}
}
