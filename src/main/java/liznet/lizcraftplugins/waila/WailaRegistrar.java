package liznet.lizcraftplugins.waila;

import liznet.lizcraftplugins.CLogger;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class WailaRegistrar  
{
    private static final String REGISTRAR_CLASSPATH = "liznet.lizcraftplugins.waila.WailaRegistrar.register";

    public static void init() {
    	 FMLInterModComms.sendMessage("waila", "register", REGISTRAR_CLASSPATH);
    }

    public static void register(IWailaRegistrar registrar) {
    	if (Loader.isModLoaded("immersiveengineering")) 
	   	{
	        try 
	        {
	        	WailaDataProvider provider = new IEngineeringDataProvider();
	        	registrar.registerHeadProvider(provider, TileEntity.class);
	        	registrar.registerBodyProvider(provider, TileEntity.class);
	        	registrar.registerTailProvider(provider, TileEntity.class);
	        	
	            CLogger.info("Loaded Waila addon for Immersive Engineering");
	        }
	        catch (Exception e) 
	        {
	            CLogger.error("Error while loading Waila addon for Immersive Engineering");
	            e.printStackTrace(System.err);
	        }
	   	}
    	if (Loader.isModLoaded("immersivepetroleum")) 
	   	{
	        try 
	        {
	        	WailaDataProvider dProvider = new IPetroleumDataProvider();
	        	registrar.registerHeadProvider(dProvider, TileEntity.class);
	        	registrar.registerBodyProvider(dProvider, TileEntity.class);
	        	registrar.registerTailProvider(dProvider, TileEntity.class);
	        	
	        	WailaEntityProvider eProvider = new IPetroleumEntityProvider();
	        	registrar.registerHeadProvider(eProvider, Entity.class);
	        	registrar.registerBodyProvider(eProvider, Entity.class);
	        	registrar.registerTailProvider(eProvider, Entity.class);
	        	
	            CLogger.info("Loaded Waila addon for Immersive Petroleum");
	        }
	        catch (Exception e) 
	        {
	            CLogger.error("Error while loading Waila addon for Immersive Petroleum");
	            e.printStackTrace(System.err);
	        }
	   	}
    	if (Loader.isModLoaded("immersivetech")) 
	   	{
	        try 
	        {
	        	WailaDataProvider provider = new ITechnologyDataProvider();
	        	registrar.registerHeadProvider(provider, TileEntity.class);
	        	registrar.registerBodyProvider(provider, TileEntity.class);
	        	registrar.registerTailProvider(provider, TileEntity.class);
	        	
	            CLogger.info("Loaded Waila addon for Immersive Technology");
	        }
	        catch (Exception e) 
	        {
	            CLogger.error("Error while loading Waila addon for Immersive Technology");
	            e.printStackTrace(System.err);
	        }
	   	}
    }
}