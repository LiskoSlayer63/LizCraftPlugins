package liznet.lizcraftplugins.items;

import static liznet.lizcraftplugins.items.Items.UNSTABLE_POTION;
import static liznet.lizcraftplugins.items.Items.UNSTABLE_POTION_SPLASH;
import static liznet.lizcraftplugins.items.Items.UNSTABLE_DUST;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemSplashPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class UnstablePotionSplash extends ItemSplashPotion {
	private Random random = new Random();
	
	public UnstablePotionSplash()
	{
		super();
		setRegistryName("unstable_potion_splash");
		setTranslationKey("unstablePotionSplash");
		setHasSubtypes(false);
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.NONE;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
    public ItemStack getDefaultInstance()
    {
        return PotionUtils.addPotionToItemStack(new ItemStack(this), PotionTypes.HARMING);
    }
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
		return stack;
    }
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        items.add(new ItemStack(this));
    }
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(TextFormatting.ITALIC + "Do NOT shake!");
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		if (!worldIn.isRemote && random.nextInt(100) <= 10)
			playerIn.sendMessage(new TextComponentString("Do NOT shake it!"));
        playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack)
    {
		return I18n.format(getTranslationKey() + ".name");
    }
	
	@Override
	public boolean hasEffect(ItemStack stack)
	{
		return true;
	}
	
	public static class BrewingRecipe implements IBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) 
		{
			return input.getItem() == UNSTABLE_POTION || (input.getItem() == Items.SPLASH_POTION && PotionUtils.getPotionFromItem(input) == PotionTypes.THICK);
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) 
		{
			return ingredient.getItem() == Items.GUNPOWDER || ingredient.getItem() == UNSTABLE_DUST;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) 
		{
			if (this.isInput(input) && this.isIngredient(ingredient))
			{
				if (input.getItem() == UNSTABLE_POTION && ingredient.getItem() == Items.GUNPOWDER)
					return new ItemStack(UNSTABLE_POTION_SPLASH);
				
				if (input.getItem() == Items.SPLASH_POTION && ingredient.getItem() == UNSTABLE_DUST)
					return new ItemStack(UNSTABLE_POTION_SPLASH);
			}
			return ItemStack.EMPTY;
		}
	}
}
