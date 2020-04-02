package liznet.lizcraftplugins.waila;

import java.util.List;

import blusunrize.immersiveengineering.common.util.inventory.MultiFluidTank;
import flaxbeard.immersivepetroleum.common.blocks.metal.TileEntityAutoLubricator;
import flaxbeard.immersivepetroleum.common.blocks.metal.TileEntityDistillationTower;
import flaxbeard.immersivepetroleum.common.blocks.metal.TileEntityGasGenerator;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class IPetroleumDataProvider extends WailaDataProvider
{
	@Override
	public List<String> getHead(ItemStack stack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return currenttip;
	}
	
	@Override
	public List<String> getBody(ItemStack stack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
		//currenttip.add(accessor.getNBTData().toString());
		TileEntity tile = accessor.getTileEntity();
		
		// Auto Lubricator
		if(tile instanceof TileEntityAutoLubricator)
		{
			TileEntityAutoLubricator tEntity = (TileEntityAutoLubricator)tile;
			
			if (tEntity.dummy > 0)
			{
				TileEntity te = accessor.getWorld().getTileEntity(tile.getPos().add(0, -tEntity.dummy, 0));
				if(te instanceof TileEntityAutoLubricator)
					tEntity = (TileEntityAutoLubricator)te;
			}
			
			currenttip.addAll(addTanks(new FluidTank[]{ tEntity.tank }));
		}

		// Portable Generator
		if(tile instanceof TileEntityGasGenerator)
			currenttip.addAll(addTanks(new FluidTank[]{ ((TileEntityGasGenerator)tile).tank }));
		
		// Distillation Tower
		if(tile instanceof TileEntityDistillationTower)
		{
			MultiFluidTank[] tanks = ((TileEntityDistillationTower)tile).master().tanks;
			
			if(tanks.length > 0 && tanks[0].getFluidAmount() > 0)
				for(FluidStack fluid : tanks[0].fluids)
					currenttip.add(String.format("%s: %d / %d mB", fluid.getLocalizedName(), Integer.valueOf(fluid.amount), Integer.valueOf(tanks[0].getCapacity())));
			else
				currenttip.add(I18n.format("hud.msg.empty"));

			currenttip.add("");
			if(tanks.length > 1 && tanks[1].getFluidAmount() > 0)
				for(FluidStack fluid : tanks[1].fluids)
					currenttip.add(String.format("%s: %d mB (%d%%)", fluid.getLocalizedName(), Integer.valueOf(fluid.amount), Integer.valueOf(Math.round((float)fluid.amount / tanks[1].getCapacity() * 100))));
			else
				currenttip.add(I18n.format("hud.msg.empty"));

			currenttip.add("");
		}
		
        return currenttip;
    }

	@Override
	public List<String> getTail(ItemStack stack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return currenttip;
	}
	
	@Override
    public NBTTagCompound getNBT(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, BlockPos pos)
    {
		return tag;
    }
}
