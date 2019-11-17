package uwu.smsgamer.antipluginviewer.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.TabCompleteEvent;
import uwu.smsgamer.antipluginviewer.AntiPluginViewer;
import uwu.smsgamer.antipluginviewer.utils.ChatUtils;

import java.util.ArrayList;
import java.util.List;

public class EventsClass implements Listener {
    @EventHandler
    public void commandCatcher(PlayerCommandPreprocessEvent e) {
        for (String num : AntiPluginViewer.instance.getConfig().getConfigurationSection("blocked-commands.").getKeys(false)) {
            if (AntiPluginViewer.instance.getConfig().getBoolean("blocked-commands." + num + ".starts-with")) {
                if (e.getMessage().startsWith("/" + num)) {
                    if (e.getPlayer().hasPermission(AntiPluginViewer.instance.getConfig().getString("blocked-commands." + num + ".bypass-permission")))
                        return;
                    e.getPlayer().sendMessage(ChatUtils.phReplace(
                            ChatUtils.colorize(
                                    AntiPluginViewer.instance.getConfig().getString("blocked-commands." + num + ".message")),
                            e.getPlayer().getName()));
                    e.setCancelled(true);
                    return;
                }
            } else if (("/" + num).equalsIgnoreCase(e.getMessage())) {
                if (e.getPlayer().hasPermission(AntiPluginViewer.instance.getConfig().getString("blocked-commands." + num + ".bypass-permission")))
                    return;
                e.getPlayer().sendMessage(ChatUtils.phReplace(
                        ChatUtils.colorize(
                                AntiPluginViewer.instance.getConfig().getString("blocked-commands." + num + ".message")),
                        e.getPlayer().getName()));
                e.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler
    public void tabCatcher(TabCompleteEvent e) {
        for (String num : AntiPluginViewer.instance.getConfig().getConfigurationSection("blocked-tabs.").getKeys(false)) {
            //e.getSender().sendMessage(ChatColor.DARK_RED + ("/"+num) + " " + e.getBuffer().trim() + "|");
            if (AntiPluginViewer.instance.getConfig().getBoolean("blocked-tabs." + num + ".starts-with")) {
                if (e.getBuffer().trim().toLowerCase().startsWith(("/" + num).toLowerCase())) {
                    if (e.getSender().hasPermission(AntiPluginViewer.instance.getConfig().getString("blocked-tabs." + num + ".bypass-permission")))
                        return;
                    List<String> lst = new ArrayList<>();
                    for(String th : AntiPluginViewer.instance.getConfig().getStringList("blocked-tabs." + num + ".tab-completion")){
                        lst.add(ChatUtils.phReplace(th, e.getSender().getName()));
                    }
                    //e.getSender().sendMessage(AntiPluginViewer.instance.getConfig().getList("blocked-tabs." + num + ".tab-completion").toString());
                    e.setCompletions(lst);
                    //e.setCancelled(true);
                    return;
                }
            }else if (("/" + num).equalsIgnoreCase(e.getBuffer().trim())) {
                if (e.getSender().hasPermission(AntiPluginViewer.instance.getConfig().getString("blocked-tabs." + num + ".bypass-permission")))
                    return;
                List<String> lst = new ArrayList<>();
                for(String th : AntiPluginViewer.instance.getConfig().getStringList("blocked-tabs." + num + ".tab-completion")){
                    lst.add(ChatUtils.phReplace(th, e.getSender().getName()));
                }
                //e.getSender().sendMessage(AntiPluginViewer.instance.getConfig().getList("blocked-tabs." + num + ".tab-completion").toString());
                e.setCompletions(lst);
                //e.setCancelled(true);
                return;
            }
        }
    }
}
