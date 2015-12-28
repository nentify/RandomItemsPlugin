package me.nentify.randomitems;

import org.bukkit.inventory.ItemStack;

public class RandomItem {
    private int probability;
    private ItemStack item;

    public RandomItem(int probability, ItemStack item) {
        this.probability = probability;
        this.item = item;
    }

    public int getProbability() {
        return probability;
    }

    public ItemStack getItem() {
        return item;
    }
}
