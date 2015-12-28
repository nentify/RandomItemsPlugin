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
        randomItems.add(new RandomItem(60, new ItemStack(Material.DIRT)));
        randomItems.add(new RandomItem(60, new ItemStack(Material.SAND)));
        randomItems.add(new RandomItem(20, new ItemStack(Material.GLOWSTONE_DUST)));
        randomItems.add(new RandomItem(30, new ItemStack(Material.IRON_INGOT)));
        randomItems.add(new RandomItem(20, new ItemStack(Material.GOLD_INGOT)));
        randomItems.add(new RandomItem(6, new ItemStack(Material.DIAMOND)));
        randomItems.add(new RandomItem(4, new ItemStack(Material.EMERALD)));

        randomItems.add(new RandomItem(25, new ItemStack(4356))); // Forestry Copper Ingot
        randomItems.add(new RandomItem(22, new ItemStack(4357))); // Forestry Tin Ingot
        randomItems.add(new RandomItem(40, new ItemStack(4255))); // IC2 Single-Use Battery
        randomItems.add(new RandomItem(10, new ItemStack(4201, 1, (short) 9))); // IC2 Glass Fibre Cables
        randomItems.add(new RandomItem(15, new ItemStack(567, 1, (short) 16))); // Project: Red Inverted White Lamp

        ItemStack premium = new ItemStack(4758, 1, (short) 37);
        ItemMeta meta = premium.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Premium Winner!");
        meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "Contact an" + ChatColor.RED + " Owner " + ChatColor.LIGHT_PURPLE + "to redeem", ChatColor.LIGHT_PURPLE + "1 week of premium for free"));
        premium.setItemMeta(meta);

        randomItems.add(new RandomItem(1, premium));

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
                            if (item.getTypeId() == 4758) {
                                item.setAmount(1);
                            } else {
                                item.setAmount(random.nextInt(3) + 1);
                            }
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
