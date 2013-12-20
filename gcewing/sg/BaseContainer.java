//------------------------------------------------------------------------------------------------
//
//   Greg's Mod Base - Generic inventory container
//
//------------------------------------------------------------------------------------------------

package gcewing.sg;

import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;

public class BaseContainer extends Container {

	public void addPlayerSlots(EntityPlayer player, int x, int y) {
		InventoryPlayer inventory = player.inventory;
		for (int var3 = 0; var3 < 3; ++var3)
			for (int var4 = 0; var4 < 9; ++var4)
				this.addSlotToContainer(new Slot(inventory, var4 + var3 * 9 + 9, x + var4 * 18, y + var3 * 18));
		for (int var3 = 0; var3 < 9; ++var3)
			this.addSlotToContainer(new Slot(inventory, var3, x + var3 * 18, y + 58));
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < crafters.size(); i++) {
			ICrafting crafter = (ICrafting)crafters.get(i);
			sendStateTo(crafter);
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		// TODO: Try to come up with a generic way of implementing this
		return null;
	}

	void sendStateTo(ICrafting crafter) {
	}

	public void updateProgressBar(int i, int value) {
	}

}
