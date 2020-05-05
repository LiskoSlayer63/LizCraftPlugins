package liznet.lizcraftplugins.recipes;

import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import liznet.lizcraftplugins.CLogger;
import liznet.lizcraftplugins.LizCraftPlugins;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;

public class Recipes {

	public static void init()
	{
		if (Loader.isModLoaded("mutantbeasts"))
			MutantBeastsRecipes.init();
		
		if (Loader.isModLoaded("immersiveengineering"))
			IEngineeringRecipes.init();
		
		if (Loader.isModLoaded("harvestcraft"))
			HarvestcraftRecipes.init();
	}
	
	public static void postInit()
	{
		if (Loader.isModLoaded("mutantbeasts"))
			MutantBeastsRecipes.postInit();
	}
	
	public static void removeRecipe(IForgeRegistryModifiable<IRecipe> registry, ResourceLocation group) 
	{
		if (registry.containsKey(group))
		{
			IRecipe recipe = registry.getValue(group);
			replaceRecipe(registry, group, DummyRecipe.from(recipe));
		}
	}
	
	public static void replaceRecipe(IForgeRegistryModifiable<IRecipe> registry, ResourceLocation group, IRecipe recipe)
	{
		if (registry.containsKey(group))
		{
			registry.remove(group);
			registry.register(recipe);
		}
	}
	
	public static void onLivingDrops(EntityLivingBase entity, List<EntityItem> drops)
	{
		if (Loader.isModLoaded("immersiveengineering"))
			IEngineeringRecipes.onEntityLivingDrops(entity, drops);
	}
	
	public static void onRegisterRecipes(IForgeRegistry<IRecipe> registry)
	{
		IForgeRegistryModifiable<IRecipe> modRegistry = (IForgeRegistryModifiable<IRecipe>)registry;
		Set<Entry<ResourceLocation, IRecipe>> entries = new HashSet<>(registry.getEntries());
		
 		for (Entry<ResourceLocation, IRecipe> entry : entries)
 		{
 			ResourceLocation group = entry.getKey();
 			IRecipe recipe = entry.getValue();
 			
 			if (group.toString().contains("stained_hardened_clay"))
	 			replaceRecipe(modRegistry, group, get9x9ColorRecipe(group, recipe.getRecipeOutput(), "blockTerracotta"));
 			
 			if (group.toString().contains("stained_glass") && !group.toString().contains("pane"))
	 			replaceRecipe(modRegistry, group, get9x9ColorRecipe(group, new ItemStack(recipe.getRecipeOutput().getItem(), 8, recipe.getRecipeOutput().getItemDamage()), "blockGlass"));
 			
 			if (group.toString().contains("bed_from_white_bed"))
	 			replaceRecipe(modRegistry, group, getShapelessColorRecipe(group, recipe.getRecipeOutput(), new ItemStack(net.minecraft.init.Items.BED, 1, OreDictionary.WILDCARD_VALUE)));
 		}
 		
 		for (EnumDyeColor dye : EnumDyeColor.values())
 		{
 			modRegistry.register(get9x9ColorRecipe(
 					new ResourceLocation(LizCraftPlugins.modId, dye.name() + "_concrete_powder"), 
 					new ItemStack(Blocks.CONCRETE_POWDER, 8, dye.getMetadata()), 
 					new ItemStack(Blocks.CONCRETE_POWDER, 1, OreDictionary.WILDCARD_VALUE)
 			));
 		}
 		
		CLogger.info("Registered miscellaneous recipes for Minecraft");
	}
	
	public static IRecipe get9x9ColorRecipe(ResourceLocation group, ItemStack output, Object input)
	{
		String dyeName = EnumDyeColor.byMetadata(output.getItemDamage()).getName();
		dyeName = dyeName.substring(0, 1).toUpperCase() + dyeName.substring(1);
		
		ShapedOreRecipe colorRecipe = new ShapedOreRecipe(group, output, new Object[]{
	            "TTT",
	            "TCT",
	            "TTT",
	            'C', "dye" + dyeName,
	            'T', input
	    });

		return colorRecipe.setRegistryName(group);
	}
	
	public static IRecipe getShapelessColorRecipe(ResourceLocation group, ItemStack output, Object input)
	{
		String dyeName = EnumDyeColor.byMetadata(output.getItemDamage()).getName();
		dyeName = dyeName.substring(0, 1).toUpperCase() + dyeName.substring(1);
		
		ShapelessOreRecipe colorRecipe = new ShapelessOreRecipe(group, output, new Object[]{
	            input,
	            "dye" + dyeName
	    });

		return colorRecipe.setRegistryName(group);
	}

	public static BrewingRecipe addBrewingRecipe(ItemStack input, ItemStack ingredient, ItemStack output) {
	  if (input.isEmpty() || input.getItem() == null) {
	    return null;
	  }
	  BrewingRecipe recipe = new BrewingRecipe(
	      input,
	      ingredient,
	      output);
	  BrewingRecipeRegistry.addRecipe(recipe);
	  
	  return recipe;
	}
}
