package liznet.lizcraftplugins.permissions;

import liznet.lizcraftplugins.CLogger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.management.PlayerInteractionManager;
import net.minecraft.world.GameType;

public class Permissions {
	public static final String FORCE_GAMEMODE_ADVENTURE = "lizcraftplugins.force-gamemode.adventure";
	public static final String FORCE_GAMEMODE_SURVIVAL = "lizcraftplugins.force-gamemode.survival";
	public static final String FORCE_GAMEMODE_CREATIVE = "lizcraftplugins.force-gamemode.creative";
	public static final String FORCE_GAMEMODE_SPECTATOR = "lizcraftplugins.force-gamemode.spectator";
	
	public static void Init()
	{
		
	}
	
	public static void checkPlayer(EntityPlayer player)
	{
		PlayerInteractionManager manager = new PlayerInteractionManager(player.getEntityWorld());
		GameType gameType = manager.getGameType();
		GameType forcedType = getForcedGameType(player);
		
		if (forcedType != gameType)
		{
			player.setGameType(forcedType);
			CLogger.info(player.getName() + "'s gamemode was changed from " + gameType.getName() + " to " + forcedType.getName() + " mode");
		}
	}
	
	public static GameType getForcedGameType(EntityPlayer player)
	{
		if (hasPermission(player, FORCE_GAMEMODE_ADVENTURE))
			return GameType.ADVENTURE;
		
		if (hasPermission(player, FORCE_GAMEMODE_SURVIVAL))
			return GameType.SURVIVAL;
		
		if (hasPermission(player, FORCE_GAMEMODE_CREATIVE))
			return GameType.CREATIVE;
		
		if (hasPermission(player, FORCE_GAMEMODE_SPECTATOR))
			return GameType.SPECTATOR;
		
		return player.getServer().getGameType();
	}
	
	public static boolean hasPermission(EntityPlayer player, String node)
	{
		return player.canUseCommand(0, node);
	}
}
