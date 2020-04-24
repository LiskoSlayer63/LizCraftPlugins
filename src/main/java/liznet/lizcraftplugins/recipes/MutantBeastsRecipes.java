package liznet.lizcraftplugins.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Iterator;
import java.util.List;

import chumbanotz.mutantbeasts.item.MBItems;
import liznet.lizcraftplugins.CLogger;
import liznet.lizcraftplugins.items.UnstablePotion;
import liznet.lizcraftplugins.items.UnstablePotionSplash;

import static liznet.lizcraftplugins.items.Items.UNSTABLE_POTION_SPLASH;

public class MutantBeastsRecipes 
{
	public static void init()
	{
		BrewingRecipeRegistry.addRecipe(new ChemicalXRecipe());
		BrewingRecipeRegistry.addRecipe(new UnstablePotion.BrewingRecipe());
		BrewingRecipeRegistry.addRecipe(new UnstablePotionSplash.BrewingRecipe());
	}
	
	public static void postInit()
	{
		CLogger.info("Patching Chemical X recipes ...");
		
		try
		{
			List<IBrewingRecipe> recipes = ObfuscationReflectionHelper.getPrivateValue(BrewingRecipeRegistry.class, null, "recipes");
			Iterator<IBrewingRecipe> iterator = recipes.iterator();
			
			while (iterator.hasNext())
			{
				IBrewingRecipe recipe = iterator.next();
				if (recipe.getClass().equals(chumbanotz.mutantbeasts.item.ChemicalXItem.BrewingRecipe.class))
				{
					CLogger.info("Chrmical X recipe found at " + recipe.getClass().getName() + "! Removing it ...");
					iterator.remove();
				}
			}
			
			ObfuscationReflectionHelper.setPrivateValue(BrewingRecipeRegistry.class, null, recipes, "recipes");
			
			CLogger.info("Succesfully patched Chemical X recipes");
		}
		catch(Exception ex)
		{
			CLogger.error("Failed to patch Chemical recipes: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	private static class ChemicalXRecipe implements IBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			return input.getItem() == UNSTABLE_POTION_SPLASH;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			for (ItemStack stack : OreDictionary.getOres("dustUranium"))
				if (stack.getItem() == ingredient.getItem())
					return true;
			return false;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			return this.isInput(input) && this.isIngredient(ingredient) ? new ItemStack(MBItems.CHEMICAL_X) : ItemStack.EMPTY;
		}
	}
}
