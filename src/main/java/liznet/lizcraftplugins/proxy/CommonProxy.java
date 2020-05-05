package liznet.lizcraftplugins.proxy;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import liznet.lizcraftplugins.LizCraftPlugins;
import liznet.lizcraftplugins.items.Items;
import liznet.lizcraftplugins.recipes.Recipes;
import liznet.lizcraftplugins.waila.WailaRegistrar;
import liznet.lizcraftplugins.permissions.Permissions;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid=LizCraftPlugins.modId)
public class CommonProxy
{
	public void preInit() 
	{
		Items.preInit();
	}
	
	public void init() 
	{
		Items.init();
		Recipes.init();
		WailaRegistrar.init();
		Permissions.Init();
	}
	
	public void postInit() 
	{
		Items.postInit();
		Recipes.postInit();
	}
	
	public RayTraceResult getMouseOver()
    {
        return null;
    }
    
    public boolean isShiftKeyDown()
    {
        return false;
    }
    
    public static void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(null, newValue);
     }
    
    // SERVER EVENTS
	
 	@SubscribeEvent
 	public static void registerItems(RegistryEvent.Register<Item> event) 
 	{
 		Items.registerItems(event);
 	}
 	
 	@SubscribeEvent(priority = EventPriority.LOWEST)
 	public static void onLivingDrops(LivingDropsEvent event)
 	{
 	    Recipes.onLivingDrops(event.getEntityLiving(), event.getDrops());
 	} 

 	@SubscribeEvent(priority = EventPriority.LOWEST)
 	public static void onRegisterRecipes(RegistryEvent.Register<IRecipe> event)
 	{
 		Recipes.onRegisterRecipes(event.getRegistry());
 	}

 	@SideOnly(Side.SERVER)
 	@SubscribeEvent(priority = EventPriority.LOWEST)
 	public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event)
	{
		if (event.player != null)
			Permissions.checkPlayer(event.player);
	}
 	
 	@SideOnly(Side.SERVER)
 	@SubscribeEvent(priority = EventPriority.LOWEST)
 	public static void onPlayerDimensionChanged(PlayerEvent.PlayerChangedDimensionEvent event)
	{
		if (event.player != null)
			Permissions.checkPlayer(event.player);
	}
 	
 	@SideOnly(Side.SERVER)
 	@SubscribeEvent(priority = EventPriority.LOWEST)
 	public static void onPlayerDimensionChanged(PlayerEvent.PlayerRespawnEvent event)
	{
		if (event.player != null)
			Permissions.checkPlayer(event.player);
	}
}
