package me.imshy.pluginDownloader.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Download implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command,  String label, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("This command can only be used by players.");
            return true;
        }

        if (args.length != 1) {
            commandSender.sendMessage("Usage: /download <url>");
            return true;
        }

        String pluginUrl = args[0];

        try {
            // Validate and download the plugin
            URL url = new URL(pluginUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Check if the connection was successful
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                commandSender.sendMessage("Failed to download the plugin. Server responded with code: " + responseCode);
                return false;
            }

            // Define where to save the plugin (usually in the server's 'plugins' folder)
            File pluginFile = new File("plugins", "downloaded-plugin.jar");
            try (InputStream inputStream = connection.getInputStream();
                 FileOutputStream outputStream = new FileOutputStream(pluginFile)) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                commandSender.sendMessage("Plugin downloaded successfully! Saved as " + pluginFile.getName());

                // Load the plugin dynamically
                PluginManager pluginManager = Bukkit.getPluginManager();
                Plugin plugin = pluginManager.loadPlugin(pluginFile);

                if (plugin != null) {
                    pluginManager.enablePlugin(plugin);
                    commandSender.sendMessage("Plugin " + plugin.getName() + " loaded successfully!");
                } else {
                    commandSender.sendMessage("Failed to load the plugin.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            commandSender.sendMessage(Component.text("An error occurred while downloading the plugin.").color(TextColor.color(244,0,0)));
        }

        return true;
    }
}
