package me.nentify.randomitems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RandomItems extends JavaPlugin {
    private Random random = new Random();
    private ArrayList<RandomItem> randomItems = new ArrayList<RandomItem>();
    private int total;

    @Override
    public void onEnable() {
        randomItems.put(20, new ItemStack(Material.COBBLESTONE, 20));
        randomItems.put(10, new ItemStack(578, 1, (short) 6));
        randomItems.put(30, new ItemStack(Material.ACACIA_STAIRS));
        randomItems.put(5, new ItemStack(4266, 6));
        randomItems.put(2, new ItemStack(Material.DIAMOND, 65));

        for (RandomItem randomItem : randomItems) {
            total += randomItem.getProbability();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("randomitems")) {
            if (args.length == 1) {
                Player player = getServer().getPlayer(args[0]);

                if (player != null) {
                    int chance = random.nextInt(total);

                    int chanceTotal = 0;
                    for (RandomItem randomItem : randomItems) {
                        chanceTotal += randomItem.getProbability();

                        if (chance < chanceTotal) {
                            ItemStack item = randomItem.getItem();
                            item.setAmount(random.nextInt(2) + 1);
                            player.getInventory().addItem(item);
                            player.sendMessage(ChatColor.GREEN + "You have received " + item.getAmount() + " " + item.getType().name());
                            break;
                        }
                    }

                    return true;
                }

                sender.sendMessage(ChatColor.RED + "Player " + args[0] + " could not be found");
                return true;
            }
        }

        return false;
    }
}
