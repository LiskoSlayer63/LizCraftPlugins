package liznet.lizcraftplugins.items;

import com.yyon.grapplinghook.items.LongFallBoots;

import java.util.List;

import liznet.bopadditions.interfaces.ICustomEnchantColor;
import liznet.bopadditions.materials.ModMaterial;
import liznet.bopadditions.proxy.ClientProxy;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GemLongFallBoots extends LongFallBoots implements ICustomEnchantColor  
{
	private String gemName;
	
	public GemLongFallBoots(ModMaterial material) 
	{
		super(material.getArmorMaterial(), 0);
		super.setCreativeTab(CreativeTabs.COMBAT);
		
		gemName = material.name().toLowerCase();
		
		super.setRegistryName(gemName + "_longfallboots");
	}

	@Override
	public int getEnchantColor() {
		return ClientProxy.getEffectColor(gemName.toUpperCase());
	}

	@SideOnly(Side.CLIENT)
	@Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
      tooltip.add("Crafted by using §o" + I18n.format(new StringBuilder().append("item.boots").append(this.gemName.substring(0, 1).toUpperCase()).append(this.gemName.substring(1)).append(".name").toString(), new Object[0]));
      //tooltip.add("Cancels fall damage when worn");
      super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
