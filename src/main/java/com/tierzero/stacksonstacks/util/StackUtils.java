package com.tierzero.stacksonstacks.util;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class StackUtils {
	public static ItemStack getStackFromInfo(int id, int stackSize, int meta) {
		return new ItemStack(Item.getItemById(id), stackSize, meta);
	}


	public static ItemStack getOneFromStack(ItemStack stack) {
		if (stack == null)
			return null;
		ItemStack copy = stack.copy();
		copy.stackSize = 1;
		return copy;
	}

	public static ItemStack getItemsFromStack(ItemStack stack, int num) {
		if (stack == null)
			return null;
		ItemStack copy = stack.copy();
		copy.stackSize = num;
		return copy;
	}

	public static void decrementStack(ItemStack stack, int amount) {
		stack.stackSize -= amount;		
		
	}
	
	public static void decrementStack(EntityPlayer player, ItemStack stack, int amount) {
		if(!player.capabilities.isCreativeMode) {
			decrementStack(stack, amount);
		}
		removeStackIfEmpty(player, stack);
	}

	public static void spawnItemInWorld(World world, int x, int y, int z, ItemStack stack) {
		if (world.isRemote)
			return;
		EntityItem item = new EntityItem(world);
		item.setEntityItemStack(StackUtils.getOneFromStack(stack));
		item.setPosition(x, y, z);
		world.spawnEntityInWorld(item);
	}
	
	private static void removeStackIfEmpty(EntityPlayer player, ItemStack stack) {
		if(stack.stackSize <= 0) {
			player.setCurrentItemOrArmor(0, null);
		}
	}

}
