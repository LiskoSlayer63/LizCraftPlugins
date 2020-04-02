package liznet.lizcraftplugins.waila;

import java.util.List;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import mcp.mobius.waila.api.IWailaEntityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class WailaEntityProvider implements IWailaEntityProvider {
	@Override
	public List<String> getWailaHead(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
    {
		return getHead(entity, currenttip, accessor, config);
    }
	public abstract List<String> getHead(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config);
	
	@Override
	public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
    {
		return getBody(entity, currenttip, accessor, config);
    }
	public abstract List<String> getBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config);
	
	@Override
	public List<String> getWailaTail(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
	{
		return getTail(entity, currenttip, accessor, config);
	}
	public abstract List<String> getTail(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config);
	
	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, Entity ent, NBTTagCompound tag, World world) 
    {
		return getNBT(player, ent, tag, world);
    }
	public abstract NBTTagCompound getNBT(EntityPlayerMP player, Entity ent, NBTTagCompound tag, World world) ;
}
