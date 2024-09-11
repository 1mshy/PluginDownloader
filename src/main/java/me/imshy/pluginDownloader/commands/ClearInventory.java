package me.imshy.pluginDownloader.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearInventory implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (commandSender instanceof Player player) {
            if (args.length > 0) {
                for (String name : args) {
                    Player otherPlayer = player.getServer().getPlayer(name);
                    if (otherPlayer != null) {
                        otherPlayer.getInventory().clear();
                        otherPlayer.sendMessage(Component.text(String.format("Inventory cleared by %s", player.getName())).color(TextColor.color(0, 222, 0)));
                    }
                }
            } else {
                player.getInventory().clear();
                player.sendMessage(Component.text("Inventory cleared.").color(TextColor.color(0, 222, 0)));
            }
        }
        return true;
    }
}
