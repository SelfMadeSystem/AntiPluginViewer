package uwu.smsgamer.antipluginviewer.utils;

import org.bukkit.ChatColor;

public class ChatUtils {
	public static String colorize(String msg) {
		String fmsg = msg;
		fmsg = fmsg.replaceAll("&&", "_=-fa");
		fmsg = fmsg.replaceAll("&", String.valueOf(ChatColor.COLOR_CHAR));
		fmsg = fmsg.replaceAll("_=-fa", "&");
		return fmsg;
	}

	public static String phReplace(String msg, String p) {
		String fmsg = msg;
		fmsg = fmsg.replaceAll("%player%", p);
		return fmsg;
	}
}
