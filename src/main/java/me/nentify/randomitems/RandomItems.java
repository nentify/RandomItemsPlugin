package me.nentify.randomitems;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class RandomItems extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Hello from RandomItems!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Goodbye from RandomItems!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("randomitems")) {
            if (args.length == 1) {
                getLogger().info("Success!");
                Player player = getServer().getPlayer(args[0]);

                if (player != null) {
                    player.getInventory().addItem(new ItemStack(Material.COBBLESTONE));
                    return true;
                }

                sender.sendMessage(Color.RED + "Player " + args[0] + " could not be found");
            }
            return false;
        }
        return false;
    }
}
