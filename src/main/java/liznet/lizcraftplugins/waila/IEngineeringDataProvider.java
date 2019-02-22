package liznet.lizcraftplugins.waila;

import java.util.List;

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
			currenttip.addAll(addTanks(new FluidTank[]{ ((TileEntityTurretChem)tile).tank }));

		// Belljar
		if(tile instanceof TileEntityBelljar)
			currenttip.addAll(addTanks(new FluidTank[]{ ((TileEntityBelljar)tile).tank }));
		
		// Charging Station
		if(tile instanceof TileEntityChargingStation)
		{
			TileEntityChargingStation charginStation = ((TileEntityChargingStation)tile);
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
    public NBTTagCompound getNBT(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, BlockPos pos)
    {
		return tag;
    }
}
