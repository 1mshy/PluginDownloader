package me.imshy.pluginDownloader;

import org.bukkit.plugin.java.JavaPlugin;


public final class PluginDownloader extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Starting Plugin Downloader...");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
