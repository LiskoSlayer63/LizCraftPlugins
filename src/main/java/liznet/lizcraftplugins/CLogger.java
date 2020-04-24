package liznet.lizcraftplugins;

import org.apache.logging.log4j.Logger;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class CLogger {
	
	private static Logger LOGGER = null;
	private static boolean CHAT = false;
	
	public static void enableChat()
	{
		CHAT = true;
	}
	public static void disableChat()
	{
		CHAT = false;
	}
	public static void chatEnabled(boolean enabled)
	{
		CHAT = enabled;
	}
	public static boolean isChatEnabled()
	{
		return CHAT;
	}
	
	public static void init(Logger Log)
	{
		LOGGER = Log;
	}
	
	public static void info(String message)
	{
		info(message, CHAT);
	}
	
	public static void info(String message, boolean useChat)
	{
		if(LOGGER != null)
			LOGGER.info(message);
		
		if (useChat)
			chat(message);
	}
	
	public static void chat(String message)
	{
		MinecraftServer minecraft = FMLCommonHandler.instance().getMinecraftServerInstance();
		
		if (minecraft != null)
			minecraft.getPlayerList().sendMessage(new TextComponentString(message));
	}
	
	public static void debug(String message)
	{
		if(LOGGER != null)
			LOGGER.debug(message);
	}
	
	public static void warn(String message)
	{
		if(LOGGER != null)
			LOGGER.warn(message);
	}
	
	public static void error(String message)
	{
		if(LOGGER != null)
			LOGGER.error(message);
	}
}
