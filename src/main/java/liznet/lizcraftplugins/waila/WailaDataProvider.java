package liznet.lizcraftplugins.waila;

import java.util.ArrayList;
import java.util.List;

import blusunrize.immersiveengineering.common.util.inventory.MultiFluidTank;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fluids.FluidTank;

public abstract class WailaDataProvider implements IWailaDataProvider 
{
	@Override
	public List<String> getWailaBody(ItemStack stack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
		return getBody(stack, currenttip, accessor, config);
    }
	public abstract List<String> getBody(ItemStack stack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config);
	
	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, BlockPos pos)
    {
		return getNBT(player, te, tag, world, pos);
    }
	public abstract NBTTagCompound getNBT(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, BlockPos pos);
	
    public static NBTTagCompound writeTanksToNBT(FluidTank[] tanks, NBTTagCompound tag)
    {
    	NBTTagList tagList = new NBTTagList();
    	for(FluidTank tank : tanks)
    	{
			NBTTagCompound tankTag = tank.writeToNBT(new NBTTagCompound());
			tankTag.setInteger("Capacity", tank.getCapacity());
			tagList.appendTag(tankTag);
		}
		tag.setTag("tank", tagList);
		return tag;
    }
    
    public static NBTTagCompound writeMultiTanksToNBT(MultiFluidTank[] tanks, NBTTagCompound tag)
    {
    	NBTTagList tagList = new NBTTagList();
    	for(MultiFluidTank tank : tanks)
    	{
			NBTTagCompound tankTag = tank.writeToNBT(new NBTTagCompound());
			tankTag.setInteger("Capacity", tank.getCapacity());
			tagList.appendTag(tankTag);
		}
		tag.setTag("tank", tagList);
		return tag;
    }

    public static FluidTank[] readTanksFromNBT(NBTTagCompound tag)
    {
    	NBTTagList tagList = tag.getTagList("tank", NBT.TAG_COMPOUND);
    	FluidTank[] tanks = new FluidTank[tagList.tagCount()];
    	for(int i = 0; i < tanks.length; i++)
    	{
    		NBTTagCompound tankTag = (NBTTagCompound)tagList.get(i);
    		FluidTank tank = new FluidTank(tankTag.getInteger("Capacity"));
    		tanks[i] = tank.readFromNBT(tankTag);
    	}
    	return tanks;
    }

    public static MultiFluidTank[] readMultiTanksFromNBT(NBTTagCompound tag)
    {
    	NBTTagList tagList = tag.getTagList("tank", NBT.TAG_COMPOUND);
    	MultiFluidTank[] tanks = new MultiFluidTank[tagList.tagCount()];
    	for(int i = 0; i < tanks.length; i++)
    	{
    		NBTTagCompound tankTag = (NBTTagCompound)tagList.get(i);
    		MultiFluidTank tank = new MultiFluidTank(tankTag.getInteger("Capacity"));
    		tanks[i] = tank.readFromNBT(tankTag);
    	}
    	return tanks;
    }
    
    public static List<String> addTanks(FluidTank[] tanks) {
    	List<String> list = new ArrayList<String>();
    	for(FluidTank tank : tanks)
		{
			if(tank.getFluidAmount() > 0)
				list.add(String.format("%s: %d / %d mB", tank.getFluid().getLocalizedName(), Integer.valueOf(tank.getFluidAmount()), Integer.valueOf(tank.getCapacity())));
			else
				list.add(I18n.format("hud.msg.empty"));
		}
    	return list;
    }
}
