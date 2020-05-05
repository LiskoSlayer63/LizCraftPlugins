package liznet.lizcraftplugins.items;

import static liznet.lizcraftplugins.items.Items.UNSTABLE_POTION;
import static liznet.lizcraftplugins.items.Items.UNSTABLE_DUST;

import java.util.List;
import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class UnstablePotion extends ItemPotion {

	public UnstablePotion()
	{
		super();
		setRegistryName("unstable_potion");
		setTranslationKey("unstablePotion");
		setHasSubtypes(false);
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
		return stack;
    }
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.NONE;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack)
    {
		return I18n.format(getTranslationKey() + ".name");
    }
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		
    }
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        items.add(new ItemStack(this));
    }
	
	@Override
	public boolean hasEffect(ItemStack stack)
	{
		return true;
	}
	
	public static class BrewingRecipe implements IBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			return input.getItem() == Items.POTIONITEM && PotionUtils.getPotionFromItem(input) == PotionTypes.THICK;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) 
		{
			return ingredient.getItem() == UNSTABLE_DUST;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			return this.isInput(input) && this.isIngredient(ingredient) ? new ItemStack(UNSTABLE_POTION) : ItemStack.EMPTY;
		}
	}
}
