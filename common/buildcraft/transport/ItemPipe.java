/**
 * Copyright (c) SpaceToad, 2011
 * http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */

package buildcraft.transport;

import buildcraft.BuildCraftTransport;
import buildcraft.core.IItemPipe;
import buildcraft.core.ItemBuildCraft;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class ItemPipe extends ItemBuildCraft implements IItemPipe {

	Pipe dummyPipe;

	private int textureIndex = 0;

	protected ItemPipe(int i) {
		super(i);
		this.setCreativeTab(CreativeTabs.tabTransport);
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int side, float par8, float par9, float par10) {
		int blockID = BuildCraftTransport.genericPipeBlock.blockID;

		if (world.getBlockId(i, j, k) == Block.snow.blockID)
			side = 0;
		else {
			if (side == 0)
				j--;
			if (side == 1)
				j++;
			if (side == 2)
				k--;
			if (side == 3)
				k++;
			if (side == 4)
				i--;
			if (side == 5)
				i++;
		}

		if (itemstack.stackSize == 0)
			return false;
		if (entityplayer.func_82246_f(i, j, k)
				&& world.canPlaceEntityOnSide(blockID, i, j, k, false, side, entityplayer)) {

			Pipe pipe = BlockGenericPipe.createPipe(shiftedIndex);
			if (BlockGenericPipe.placePipe(pipe, world, i, j, k, blockID, 0)) {

				Block.blocksList[blockID].onBlockPlacedBy(world, i, j, k, entityplayer);
				// To move to a proxt
				// world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F,
				// (float)k + 0.5F, block.stepSound.func_1145_d(),
				// (block.stepSound.getVolume() + 1.0F) / 2.0F,
				// block.stepSound.getPitch() * 0.8F);
				itemstack.stackSize--;
			}
			return true;
		} else
			return false;
	}

	public ItemPipe setTextureIndex(int textureIndex){
		this.textureIndex = textureIndex;
		return this;
	}

	public int getTextureIndex() {
		return textureIndex;
	}
}
