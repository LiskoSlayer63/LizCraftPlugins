package liznet.lizcraftplugins.proxy;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import liznet.lizcraftplugins.LizCraftPlugins;
import liznet.lizcraftplugins.items.Items;
import liznet.lizcraftplugins.recipes.Recipes;
import liznet.lizcraftplugins.waila.WailaRegistrar;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
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
		Items.init();
		Recipes.init();
		WailaRegistrar.init();
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
}
