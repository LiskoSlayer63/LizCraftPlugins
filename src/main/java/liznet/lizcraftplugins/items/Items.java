package liznet.lizcraftplugins.items;

import java.util.ArrayList;
import java.util.List;

import com.yyon.grapplinghook.grapplemod;

import liznet.bopadditions.materials.ModMaterial;
import liznet.bopadditions.materials.ModMaterials;
import liznet.lizcraftplugins.CLogger;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

public class Items 
{
	public static List<Item> ARMORS = new ArrayList<Item>();
	public static UnstablePotion UNSTABLE_POTION = null;
	public static UnstablePotionSplash UNSTABLE_POTION_SPLASH = null;
	public static UnstableDust UNSTABLE_DUST = null;
	
	public static void preInit() 
	{
		if (Loader.isModLoaded("bopadditions") && Loader.isModLoaded("grapplemod"))
			for(ModMaterial material : ModMaterials.getMaterials())
				ARMORS.add(new GemLongFallBoots(material));
		
		if (Loader.isModLoaded("mutantbeasts"))
		{
			UNSTABLE_POTION = new UnstablePotion();
			UNSTABLE_POTION_SPLASH = new UnstablePotionSplash();
			UNSTABLE_DUST = new UnstableDust();
		}
	}
	
	public static void init() 
	{
		OreDictionary.registerOre("dye", net.minecraft.init.Items.COAL);
		OreDictionary.registerOre("dyeBlack", net.minecraft.init.Items.COAL);
		OreDictionary.registerOre("blockTerracotta", Blocks.HARDENED_CLAY);
		
		for (EnumDyeColor dye : EnumDyeColor.values())
			OreDictionary.registerOre("blockTerracotta", new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, dye.getMetadata()));

		CLogger.info("Registered items for Minecraft");
		
		if (Loader.isModLoaded("adventurersamulets"))
		{
			ResourceLocation registryName = new ResourceLocation("adventurersamulets", "pestle");
			
			if(Item.REGISTRY.containsKey(registryName))
			{
				Item pestle = Item.REGISTRY.getObject(registryName);
				pestle.setContainerItem(pestle);

				CLogger.info("Registered items for Adventurer's Amulets");
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
		
		if (Loader.isModLoaded("mutantbeasts"))
		{
			event.getRegistry().register(UNSTABLE_POTION);
			event.getRegistry().register(UNSTABLE_POTION_SPLASH);
			event.getRegistry().register(UNSTABLE_DUST);
		}
	}
	
	public static void registerRenders(ModelRegistryEvent event)
	{
	    for (Item armor : ARMORS)
	    	ModelLoader.setCustomModelResourceLocation(armor, 0, new ModelResourceLocation(grapplemod.longfallboots.getRegistryName(), "inventory"));

		if (Loader.isModLoaded("mutantbeasts"))
		{
			ModelLoader.setCustomModelResourceLocation(UNSTABLE_POTION, 0, new ModelResourceLocation("bottle_drinkable", "inventory"));
			ModelLoader.setCustomModelResourceLocation(UNSTABLE_POTION_SPLASH, 0, new ModelResourceLocation("bottle_splash", "inventory"));
			ModelLoader.setCustomModelResourceLocation(UNSTABLE_DUST, 0, new ModelResourceLocation("gunpowder", "inventory"));
		}
	}
}
