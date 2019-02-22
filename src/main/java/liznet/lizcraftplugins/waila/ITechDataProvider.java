package liznet.lizcraftplugins.waila;

import java.util.List;

import ferro2000.immersivetech.common.blocks.metal.tileentities.TileEntityBoiler;
import ferro2000.immersivetech.common.blocks.metal.tileentities.TileEntityDistiller;
import ferro2000.immersivetech.common.blocks.metal.tileentities.TileEntitySolarTower;
import ferro2000.immersivetech.common.blocks.metal.tileentities.TileEntitySteamTurbine;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ITechDataProvider extends WailaDataProvider
{
	@Override
	public List<String> getBody(ItemStack stack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
		TileEntity tile = accessor.getTileEntity();
		
		// Boiler
		if(tile instanceof TileEntityBoiler)
			currenttip.addAll(addTanks(((TileEntityBoiler)tile).master().tanks));
		
		// Distiller
		if(tile instanceof TileEntityDistiller)
			currenttip.addAll(addTanks(((TileEntityDistiller)tile).master().tanks));

		// Solar Tower
		if(tile instanceof TileEntitySolarTower)
			currenttip.addAll(addTanks(((TileEntitySolarTower)tile).master().tanks));

		// Steam Turbine
		if(tile instanceof TileEntitySteamTurbine)
			currenttip.addAll(addTanks(((TileEntitySteamTurbine)tile).master().tanks));
		
        return currenttip;
    }
	
	@Override
    public NBTTagCompound getNBT(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, BlockPos pos)
    {
		return tag;
    }
}
