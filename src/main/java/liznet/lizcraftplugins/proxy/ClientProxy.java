package liznet.lizcraftplugins.proxy;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import liznet.lizcraftplugins.CLogger;
import liznet.lizcraftplugins.LizCraftPlugins;
import liznet.lizcraftplugins.items.Items;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static liznet.lizcraftplugins.items.Items.UNSTABLE_POTION;
import static liznet.lizcraftplugins.items.Items.UNSTABLE_POTION_SPLASH;
import static liznet.lizcraftplugins.items.Items.UNSTABLE_DUST;

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
		
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor()
        {
            public int colorMultiplier(ItemStack stack, int tintIndex)
            {
                return tintIndex > 0 ? -1 : PotionUtils.getPotionColorFromEffectList(PotionTypes.LEAPING.getEffects());
            }
        }, UNSTABLE_POTION, UNSTABLE_POTION_SPLASH);
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor()
        {
            public int colorMultiplier(ItemStack stack, int tintIndex)
            {
            	return (155 << 24) + (100 << 16) + (155 << 8) + (200);
            }
        }, UNSTABLE_DUST);
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
