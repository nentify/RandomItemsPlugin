package me.nentify.randomitems;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class RandomItems extends JavaPlugin {

    private Random random = new Random();
    private Map<Integer, ItemStack> items = new HashMap<>();
    private int total;

    @Override
    public void onEnable() {
        getLogger().info("Hello from RandomItems!");
        items.put(20, new ItemStack(Material.COBBLESTONE, 20));
        items.put(10, new ItemStack(578, 1, (short) 6));
        items.put(30, new ItemStack(Material.ACACIA_STAIRS));
        items.put(5, new ItemStack(4266, 6));
        items.put(2, new ItemStack(Material.DIAMOND, 65));

        for (Integer key : items.keySet()) {
            total += key;
        }
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
                    int chance = random.nextInt(total);

                    int chanceTotal = 0;
                    for (Map.Entry<Integer, ItemStack> item : items.entrySet()) {
                        chanceTotal += item.getKey();

                        if (chance < chanceTotal) {
                            player.getInventory().addItem(item.getValue());
                            player.sendMessage("You have received " + item.getValue());
                            break;
                        }
                    }

                    return true;
                }

                sender.sendMessage(Color.RED + "Player " + args[0] + " could not be found");
            }
            return false;
        }
        return false;
    }
}
