package liznet.lizcraftplugins;

import liznet.lizcraftplugins.proxy.CommonProxy;
import net.minecraft.init.Items;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
		modid = LizCraftPlugins.modId, 
		name = LizCraftPlugins.name, 
		version = LizCraftPlugins.version, 
		dependencies = 
			"after:mutantbeasts;" + 
			"after:bopadditions;" + 
			"after:immersiveengineering;" +
			"after:immersivepetroleum;" +
			"after:immersivetech;" +
			"after:adventurersamulets;" +
			"after:waila;" + 
			"after:harvestcraft;" + 
			"after:quark;",
		acceptedMinecraftVersions = "[1.12.2]"
	)
public class LizCraftPlugins 
{
	public static final String modId = "lizcraftplugins";
	public static final String name = "LizCraft Plugins";
	public static final String version = "0.1.8";

	@Mod.Instance(LizCraftPlugins.modId)
	public static LizCraftPlugins instance;
	
	@SidedProxy(serverSide = "liznet.lizcraftplugins.proxy.CommonProxy", clientSide = "liznet.lizcraftplugins.proxy.ClientProxy")
    public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{
		CLogger.init(event.getModLog());
		proxy.preInit();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) 
	{
		proxy.init();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
		proxy.postInit();
	}

}