package liznet.lizcraftplugins.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class UnstableDust extends Item 
{
	public UnstableDust()
	{
		super();
		setRegistryName("unstable_dust");
		setTranslationKey("unstableDust");
	}
	
	@Override
	public boolean hasEffect(ItemStack stack)
	{
		return true;
	}
}
