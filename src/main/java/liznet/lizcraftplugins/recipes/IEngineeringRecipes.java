package liznet.lizcraftplugins.recipes;

import java.util.List;

import blusunrize.immersiveengineering.api.crafting.CrusherRecipe;
import blusunrize.immersiveengineering.api.crafting.IngredientStack;
import blusunrize.immersiveengineering.api.crafting.SqueezerRecipe;
import blusunrize.immersiveengineering.api.energy.ThermoelectricHandler;
import liznet.lizcraftplugins.CLogger;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

public class IEngineeringRecipes {
	
	public static void init() 
	{
		initCrusherRecipes();
		initSqueezerRecipes();
		initMiscRecipes();
	}
	
	private static void initMiscRecipes()
	{
		ThermoelectricHandler.registerSource(new IngredientStack(OreDictionary.getOres("blockLudicrite")), 8000);
		CLogger.info("Registered miscellaneous recipes for Immersive Engineering");
	}
	
	private static void initSqueezerRecipes()
	{
		Fluid fluidBlood = FluidRegistry.getFluid("blood");
		FluidStack blood = fluidBlood != null ? new FluidStack(fluidBlood, 5) : null;
		
		SqueezerRecipe.addRecipe(blood, new ItemStack(Items.SLIME_BALL, 4), new ItemStack(Items.SKULL, 1, 2), 6400);
		SqueezerRecipe.addRecipe(blood, new ItemStack(Items.SLIME_BALL, 4), new ItemStack(Items.SKULL, 1, 3), 6400);
		SqueezerRecipe.addRecipe(blood, new ItemStack(Items.SLIME_BALL, 4), new ItemStack(Items.SKULL, 1, 4), 6400);
		
		CLogger.info("Registered Squeezer recipes for Immersive Engineering");
	}
	
	private static void initCrusherRecipes()
	{
		CrusherRecipe.addRecipe(new ItemStack(Items.DYE, 4, 15), new ItemStack(Items.SKULL, 1, 0), 3200);
		CrusherRecipe.addRecipe(new ItemStack(Items.ROTTEN_FLESH, 4), new ItemStack(Items.SKULL, 1, 2), 3200);
		CrusherRecipe.addRecipe(new ItemStack(Items.GUNPOWDER, 4), new ItemStack(Items.SKULL, 1, 4), 3200);
		
		for (EnumDyeColor dye : EnumDyeColor.values())
			CrusherRecipe.addRecipe(new ItemStack(Blocks.CONCRETE_POWDER, 1, dye.getMetadata()), new ItemStack(Blocks.CONCRETE, 1, dye.getMetadata()), 3200);
		
		if (Loader.isModLoaded("quark"))
			CrusherRecipe.addRecipe(new ItemStack(Item.getByNameOrId("quark:black_ash"), 4), new ItemStack(Items.SKULL, 1, 1), 3200);

		CLogger.info("Registered Crusher recipes for Immersive Engineering");
		
		if (Loader.isModLoaded("biomesoplenty") && Loader.isModLoaded("netherex"))
		{
			ItemStack bop_gem = new ItemStack(Item.getByNameOrId("biomesoplenty:gem"), 2, 0);
			ItemStack bop_ore = new ItemStack(Item.getByNameOrId("biomesoplenty:gem_ore"), 1, 0);
			
			ItemStack nex_gem = new ItemStack(ItemBlock.getByNameOrId("netherex:amethyst_crystal"), 2);
			ItemStack nex_ore = new ItemStack(ItemBlock.getByNameOrId("netherex:amethyst_ore"), 1);
			
			CrusherRecipe.removeRecipesForInput(bop_ore);
			CrusherRecipe.removeRecipesForOutput(bop_gem);
			
			CrusherRecipe.removeRecipesForInput(nex_ore);
			CrusherRecipe.removeRecipesForOutput(nex_gem);
			
			CrusherRecipe.addRecipe(bop_gem, bop_ore, 3200);
			CrusherRecipe.addRecipe(nex_gem, nex_ore, 3200);
			
			CLogger.info("Patched Crusher recipes for Biomes O' Plenty and NetherEx");
		}
	}
	
	public static void onEntityLivingDrops(EntityLivingBase entity, List<EntityItem> drops)
	{
		if (entity == null)
 	    	return;
		
		DamageSource source = entity.getLastDamageSource();
 		
		if (source != null && source.damageType.equals("ieCrushed"))
		{
			if (entity instanceof EntitySkeleton && Math.random() * 100 <= 10)
				drops.add(new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.SKULL, 1, 0)));

			if (entity instanceof EntityWitherSkeleton && Math.random() * 100 <= 10)
				drops.add(new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.SKULL, 1, 1)));

			if (entity instanceof EntityZombie && Math.random() * 100 <= 10)
				drops.add(new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.SKULL, 1, 2)));

			if (entity instanceof EntityCreeper && Math.random() * 100 <= 10)
				drops.add(new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.SKULL, 1, 4)));
		}
	}
}
