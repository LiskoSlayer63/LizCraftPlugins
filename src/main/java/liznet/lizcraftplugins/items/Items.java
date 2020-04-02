package liznet.lizcraftplugins.items;

import java.util.ArrayList;
import java.util.List;

import com.yyon.grapplinghook.grapplemod;

import liznet.bopadditions.materials.ModMaterial;
import liznet.bopadditions.materials.ModMaterials;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

public class Items 
{
	private static List<Item> ARMORS = new ArrayList<Item>();
	
	public static void preInit() 
	{
		if (Loader.isModLoaded("bopadditions") && Loader.isModLoaded("grapplemod"))
			for(ModMaterial material : ModMaterials.getMaterials())
				ARMORS.add(new GemLongFallBoots(material));
	}
	
	public static void init() 
	{
		OreDictionary.registerOre("dye", net.minecraft.init.Items.COAL);
		OreDictionary.registerOre("dyeBlack", net.minecraft.init.Items.COAL);
		
		if (Loader.isModLoaded("adventurersamulets"))
		{
			ResourceLocation registryName = new ResourceLocation("adventurersamulets", "pestle");
			
			if(Item.REGISTRY.containsKey(registryName)){
				Item pestle = Item.REGISTRY.getObject(registryName);
				pestle.setContainerItem(pestle);
			}
		}
	}
	
	public static void postInit() 
	{
		
	}
	
	public static void registerItems(RegistryEvent.Register<Item> event) 
	{
		for(Item armor : ARMORS)
			event.getRegistry().register(armor);
	}
	
	public static void registerRenders(ModelRegistryEvent event)
	{
	    for (Item armor : ARMORS)
	      ModelLoader.setCustomModelResourceLocation(armor, 0, new ModelResourceLocation(grapplemod.longfallboots.getRegistryName(), "inventory"));
	}
}
