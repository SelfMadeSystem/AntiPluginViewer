package uwu.smsgamer.antipluginviewer;

import org.bukkit.plugin.java.JavaPlugin;
import uwu.smsgamer.antipluginviewer.commands.CommandsClass;
import uwu.smsgamer.antipluginviewer.events.EventsClass;

import java.io.File;

public final class AntiPluginViewer extends JavaPlugin {

    public static AntiPluginViewer instance;

    @Override
    public void onEnable() { instance = this;

        CommandsClass cmd = new CommandsClass();

        //getCommand("version").setTabCompleter(cmd);

        loadConfig();

        getServer().getPluginManager().registerEvents(new EventsClass(), this);
    }

    @Override
    public void onDisable() {
    }

    private void loadConfig() {
        File configFile = new File(this.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveDefaultConfig();
        }
    }
}
