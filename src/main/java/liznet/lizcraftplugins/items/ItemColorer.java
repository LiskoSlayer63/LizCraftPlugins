package liznet.lizcraftplugins.items;

import static liznet.lizcraftplugins.items.Items.UNSTABLE_DUST;
import static liznet.lizcraftplugins.items.Items.UNSTABLE_POTION;
import static liznet.lizcraftplugins.items.Items.UNSTABLE_POTION_SPLASH;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ItemColorer {
	public static void colorItems()
	{
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor()
        {
            public int colorMultiplier(ItemStack stack, int tintIndex)
            {
                return tintIndex > 0 ? -1 : PotionUtils.getPotionColorFromEffectList(PotionTypes.LEAPING.getEffects());
            }
        }, UNSTABLE_POTION, UNSTABLE_POTION_SPLASH);
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor()
        {
            public int colorMultiplier(ItemStack stack, int tintIndex)
            {
            	return (155 << 24) + (100 << 16) + (155 << 8) + (200);
            }
        }, UNSTABLE_DUST);
	}
}
