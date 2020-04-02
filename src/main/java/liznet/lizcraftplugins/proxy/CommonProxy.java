package liznet.lizcraftplugins.proxy;

import liznet.lizcraftplugins.LizCraftPlugins;
import liznet.lizcraftplugins.items.Items;
import liznet.lizcraftplugins.waila.WailaRegistrar;
import net.minecraft.item.Item;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid=LizCraftPlugins.modId)
public class CommonProxy
{
	public void preInit() 
	{
		Items.preInit();
	}
	
	public void init() 
	{
		WailaRegistrar.init();
		Items.init();
	}
	
	public void postInit() 
	{
		Items.postInit();
	}
	
	public RayTraceResult getMouseOver()
    {
        return null;
    }
    
    public boolean isShiftKeyDown()
    {
        return false;
    }
    
    
    // SERVER EVENTS
	
 	@SubscribeEvent
 	public static void registerItems(RegistryEvent.Register<Item> event) 
 	{
 		Items.registerItems(event);
 	}
}
