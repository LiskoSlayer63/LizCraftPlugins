package liznet.lizcraftplugins.recipes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class HarvestcraftRecipes 
{
	public static void init()
	{
		if (Loader.isModLoaded("quark"))
			GameRegistry.addSmelting(Item.getByNameOrId("quark:tallow"), new ItemStack(Item.getByNameOrId("harvestcraft:butteritem")), 0.1f);
	}
}
