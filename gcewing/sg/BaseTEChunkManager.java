//------------------------------------------------------------------------------------------------
//
//   Greg's Mod Base - Chunk manager for tile entities
//
//------------------------------------------------------------------------------------------------

package gcewing.sg;

import java.util.*;

import net.minecraft.nbt.*;
import net.minecraft.tileentity.*;
import net.minecraft.world.*;
import net.minecraft.world.chunk.*;
import net.minecraft.util.*;

import net.minecraftforge.common.*;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;
import net.minecraftforge.event.*;
import net.minecraftforge.event.world.*;

public class BaseTEChunkManager implements ForgeChunkManager.LoadingCallback {

	public static boolean debug = false;

	static BaseMod base;
	
	public BaseTEChunkManager(BaseMod mod) {
		base = mod;
		ForgeChunkManager.setForcedChunkLoadingCallback(mod, this);
	}
	
	Ticket newTicket(World world) {
		return ForgeChunkManager.requestTicket(base, world, Type.NORMAL);
	}
	
	@Override
	public void ticketsLoaded(List<ForgeChunkManager.Ticket> tickets, World world) {
		if (debug)
			System.out.printf("BaseTEChunkManager: ticketsLoaded for %s\n", world);
		for (Ticket ticket : tickets) {
			NBTTagCompound nbt = ticket.getModData();
			if (nbt != null)
				if (nbt.getString("type").equals("TileEntity")) {
					int x = nbt.getInteger("xCoord");
					int y = nbt.getInteger("yCoord");
					int z = nbt.getInteger("zCoord");
					TileEntity te = world.getBlockTileEntity(x, y, z);
					if (debug)
						System.out.printf("BaseTEChunkManager: Ticket for %s at (%d, %d, %d)\n",
							te, x, y, z);
					if (te instanceof BaseChunkLoadingTE)
						if (!((BaseChunkLoadingTE)te).reinstateChunkTicket(ticket)) {
							if (debug)
								System.out.printf("BaseTEChunkManager: Unable to reinstate ticket\n");
							ForgeChunkManager.releaseTicket(ticket);
						}
				}
		}
	}
	
}
