package liznet.lizcraftplugins.waila;

import java.util.List;

import flaxbeard.immersivepetroleum.common.IPContent;
import flaxbeard.immersivepetroleum.common.entity.EntitySpeedboat;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidTank;

public class IPetroleumEntityProvider extends WailaEntityProvider {

	@Override
	public List<String> getHead(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
		if (entity instanceof EntitySpeedboat)
			currenttip.set(0, "§f" + new ItemStack(IPContent.itemSpeedboat).getDisplayName());
		return currenttip;
	}

	@Override
	public List<String> getBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
		if (entity instanceof EntitySpeedboat)
		{
			EntitySpeedboat boat = (EntitySpeedboat)entity;
			
			FluidTank tank = new FluidTank(boat.getMaxFuel());
			tank.fill(boat.getContainedFluid(), true);
			
			currenttip.addAll(WailaDataProvider.addTanks(new FluidTank[] { tank }));
		}
		return currenttip;
	}

	@Override
	public List<String> getTail(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public NBTTagCompound getNBT(EntityPlayerMP player, Entity ent, NBTTagCompound tag, World world) {
		return tag;
	}

}
