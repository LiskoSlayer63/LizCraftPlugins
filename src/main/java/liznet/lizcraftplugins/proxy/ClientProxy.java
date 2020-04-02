package liznet.lizcraftplugins.proxy;

import org.lwjgl.input.Keyboard;

import liznet.lizcraftplugins.LizCraftPlugins;
import liznet.lizcraftplugins.items.Items;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid=LizCraftPlugins.modId)
public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit() 
	{
		super.preInit();
	}

	@Override
	public void init() 
	{
		super.init();
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
	
}
