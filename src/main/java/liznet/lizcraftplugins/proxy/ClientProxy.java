package liznet.lizcraftplugins.proxy;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.RayTraceResult;

public class ClientProxy extends CommonProxy
{
    @Override
    public RayTraceResult getMouseOver()
    {
        return Minecraft.getMinecraft().objectMouseOver;
    }

    @Override
    public boolean isShiftKeyDown()
    {
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
    }
}
