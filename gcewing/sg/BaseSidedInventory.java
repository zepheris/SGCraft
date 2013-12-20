//------------------------------------------------------------------------------------------------
//
//   Greg's Mod Base - Generic sided inventory
//
//------------------------------------------------------------------------------------------------

package gcewing.sg;

import net.minecraft.inventory.InventoryBasic;
import net.minecraftforge.common.*;

public class BaseSidedInventory extends InventoryBasic implements ISidedInventory {

	int[][] sideMapping; 

	public BaseSidedInventory(String name, int size, int[][] mapping) {
		super(name, false, size);
		sideMapping = mapping;
	}
	
	/**
	 * Get the start of the side inventory.
	 * @param side The global side to get the start of range.
	 */
	public int getStartInventorySide(ForgeDirection side) {
		return sideMapping[side.ordinal()][0];
	}

	/**
	 * Get the size of the side inventory.
	 * @param side The global side.
	 */
	public int getSizeInventorySide(ForgeDirection side) {
		return sideMapping[side.ordinal()][1];
	}

}
