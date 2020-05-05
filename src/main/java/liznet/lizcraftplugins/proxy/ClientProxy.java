package liznet.lizcraftplugins.proxy;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import liznet.lizcraftplugins.CLogger;
import liznet.lizcraftplugins.LizCraftPlugins;
import liznet.lizcraftplugins.items.ItemColorer;
import liznet.lizcraftplugins.items.Items;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid=LizCraftPlugins.modId)
public class ClientProxy extends CommonProxy
{
	private static boolean tweakedVSync = false;
	
	@Override
	public void preInit() 
	{
		super.preInit();
		
		if (Minecraft.getMinecraft().gameSettings.enableVsync)
		{
			Display.setVSyncEnabled(false);
			
			tweakedVSync = true;
			
	        CLogger.info("========== VSYNC TURNED OFF DURING STARTUP! ==========");
		}
	}

	@Override
	public void init() 
	{
		super.init();
		
		ItemColorer.colorItems();
	}

	@Override
	public void postInit() 
	{
		super.postInit();
	}
	
    @Override
    public RayTraceResult getMouseOver()
    {
        return Minecraft.getMinecraft().objectMouseOver;
    }

    @Override
    public boolean isShiftKeyDown()
    {
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
    }
    
    
    @SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) 
	{
		Items.registerRenders(event);
	}
	
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onGuiInit(InitGuiEvent.Post event)
    {
    	if (tweakedVSync && event.getGui().getClass().equals(GuiMainMenu.class))
    	{
			Display.setVSyncEnabled(true);

			tweakedVSync = false;
			
			CLogger.info("========== VSYNC TURNED BACK ON! ==========");
    	}
    }
}
