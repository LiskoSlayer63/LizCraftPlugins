package liznet.lizcraftplugins;

import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@net.minecraftforge.common.config.Config(modid = LizCraftPlugins.modId)
public class Config 
{
	
	
	@EventBusSubscriber(modid = LizCraftPlugins.modId)
	private static class EventHandler 
	{
		@SubscribeEvent
		public static void onConfigChanged(final OnConfigChangedEvent event) 
		{
			if (event.getModID().equals(LizCraftPlugins.modId)) 
			{
				ConfigManager.sync(LizCraftPlugins.modId, net.minecraftforge.common.config.Config.Type.INSTANCE);
			}
		}
	}
}
