package liznet.lizcraftplugins.waila;

import java.util.List;

import blusunrize.immersiveengineering.api.tool.BelljarHandler.IPlantHandler;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityBelljar;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityChargingStation;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityDieselGenerator;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityFermenter;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityRefinery;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntitySheetmetalTank;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntitySqueezer;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityTurretChem;
import blusunrize.immersiveengineering.common.util.EnergyHelper;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidTank;

public class IEngineeringDataProvider extends WailaDataProvider
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
		
		 // Sheetmetal Tank 
		if(tile instanceof TileEntitySheetmetalTank)
			currenttip.addAll(addTanks(new FluidTank[]{ ((TileEntitySheetmetalTank)tile).master().tank }));
		
		// Diesel Generator
		if(tile instanceof TileEntityDieselGenerator)
			currenttip.addAll(addTanks(((TileEntityDieselGenerator)tile).master().tanks));
		
		// Fermenter
		if(tile instanceof TileEntityFermenter)
			currenttip.addAll(addTanks(((TileEntityFermenter)tile).master().tanks));
		
		// Chem Turret
		if(tile instanceof TileEntityTurretChem) 
			currenttip.addAll(addTanks(new FluidTank[]{ ((TileEntityTurretChem)tile).tank }));

		// Squeezer
		if(tile instanceof TileEntitySqueezer)
			currenttip.addAll(addTanks(((TileEntitySqueezer)tile).master().tanks ));

		// Refinery
		if(tile instanceof TileEntityRefinery)
			currenttip.addAll(addTanks(((TileEntityRefinery)tile).master().tanks));

		// Belljar
		if(tile instanceof TileEntityBelljar)
		{
			TileEntityBelljar bellJar = (TileEntityBelljar)tile;
			
			if (bellJar.dummy > 0)
			{
				TileEntity te = accessor.getWorld().getTileEntity(tile.getPos().add(0, -bellJar.dummy, 0));
				if(te instanceof TileEntityBelljar)
					bellJar = (TileEntityBelljar)te;
			}
			
			if (bellJar.renderActive && bellJar.renderGrowth > 0)
			{
				int value = Integer.valueOf(Math.round(bellJar.renderGrowth * 100));
				try 
				{
					if (bellJar.getInventory().get(0) != null && bellJar.getInventory().get(1) != null)
					{
						IPlantHandler handler = bellJar.getCurrentPlantHandler();
						ItemStack soil = bellJar.getInventory().get(0);
						ItemStack seed = bellJar.getInventory().get(1);
						ItemStack[] plants = handler.getOutput(seed, soil, bellJar);
						
						if (handler.isValid(seed) && handler.isCorrectSoil(seed, soil) && plants != null && plants.length > 0)
							currenttip.add(String.format("%s: %d%%", plants[0].getDisplayName(), value));
					}
				}
				catch (Exception e) 
				{ 
					currenttip.add(String.format("%s: %d%%", "Growing", value));
				}
			}
			
			currenttip.addAll(addTanks(new FluidTank[]{ bellJar.tank }));
		}
		
		// Charging Station
		if(tile instanceof TileEntityChargingStation)
		{
			TileEntityChargingStation charginStation = (TileEntityChargingStation)tile;
			if(EnergyHelper.isFluxItem(charginStation.inventory.get(0)))
			{
				float maxCharge = EnergyHelper.getMaxEnergyStored(charginStation.inventory.get(0));
				if(maxCharge > 0)
					currenttip.add(String.format("%s: %d%%", "Charging", Integer.valueOf(Math.round((float)EnergyHelper.getEnergyStored(charginStation.inventory.get(0)) / maxCharge * 100))));
			}
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
