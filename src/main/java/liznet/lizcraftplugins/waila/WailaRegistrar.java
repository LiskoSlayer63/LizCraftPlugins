package liznet.lizcraftplugins.waila;

import liznet.lizcraftplugins.CLogger;
import mcp.mobius.waila.api.IWailaRegistrar;
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
	        	registrar.registerBodyProvider(new IEngineeringDataProvider(), TileEntity.class);
	            CLogger.info("Loaded addon for Immersive Engineering");
	        }
	        catch (Exception e) 
	        {
	            CLogger.error("Error while loading addon for Immersive Engineering");
	            e.printStackTrace(System.err);
	        }
	   	}
    	if (Loader.isModLoaded("immersivepetroleum")) 
	   	{
	        try 
	        {
	        	registrar.registerBodyProvider(new IPetroleumDataProvider(), TileEntity.class);
	            CLogger.info("Loaded addon for Immersive Petroleum");
	        }
	        catch (Exception e) 
	        {
	            CLogger.error("Error while loading addon for Immersive Petroleum");
	            e.printStackTrace(System.err);
	        }
	   	}
    	if (Loader.isModLoaded("immersivetech")) 
	   	{
	        try 
	        {
	        	registrar.registerBodyProvider(new ITechDataProvider(), TileEntity.class);
	            CLogger.info("Loaded addon for Immersive Tech");
	        }
	        catch (Exception e) 
	        {
	            CLogger.error("Error while loading addon for Immersive Tech");
	            e.printStackTrace(System.err);
	        }
	   	}
    }
}