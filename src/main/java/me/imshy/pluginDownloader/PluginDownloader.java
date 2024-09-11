package me.imshy.pluginDownloader;

import me.imshy.pluginDownloader.commands.ClearInventory;
import me.imshy.pluginDownloader.commands.Download;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;


public final class PluginDownloader extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Starting Plugin Downloader...");
        getServer().getPluginCommand("download").setExecutor(new Download());
        getServer().getPluginCommand("clear").setExecutor(new ClearInventory());
//        System.out.println(command);
//        System.out.println(this.co);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
