package liznet.lizcraftplugins.waila;

import java.util.List;

import mctmods.immersivetechnology.common.blocks.metal.tileentities.TileEntityBoilerSlave;
import mctmods.immersivetechnology.common.blocks.metal.tileentities.TileEntityDistillerSlave;
import mctmods.immersivetechnology.common.blocks.metal.tileentities.TileEntitySolarTowerSlave;
import mctmods.immersivetechnology.common.blocks.metal.tileentities.TileEntitySteamTurbineSlave;
import mctmods.immersivetechnology.common.blocks.metal.tileentities.TileEntitySteelSheetmetalTankSlave;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidTank;

public class ITechnologyDataProvider extends WailaDataProvider
{
	@Override
	public List<String> getHead(ItemStack stack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return currenttip;
	}
	
	@Override
	public List<String> getBody(ItemStack stack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
		TileEntity tile = accessor.getTileEntity();
		
		// Boiler
		if(tile instanceof TileEntityBoilerSlave)
			currenttip.addAll(addTanks(((TileEntityBoilerSlave)tile).master().tanks));
		
		// Distiller
		if(tile instanceof TileEntityDistillerSlave)
			currenttip.addAll(addTanks(((TileEntityDistillerSlave)tile).master().tanks));

		// Solar Tower
		if(tile instanceof TileEntitySolarTowerSlave)
			currenttip.addAll(addTanks(((TileEntitySolarTowerSlave)tile).master().tanks));

		// Steam Turbine
		if(tile instanceof TileEntitySteamTurbineSlave)
			currenttip.addAll(addTanks(((TileEntitySteamTurbineSlave)tile).master().tanks));
		
		// Steel Tank
		if(tile instanceof TileEntitySteelSheetmetalTankSlave)
			currenttip.addAll(addTanks(new FluidTank[]{ ((TileEntitySteelSheetmetalTankSlave)tile).master().tank }));
		
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
