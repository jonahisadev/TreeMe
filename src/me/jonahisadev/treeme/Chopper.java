package me.jonahisadev.treeme;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.Random;

public class Chopper {

    public static int go(Main plugin, TreeModel tree, Player player)
    {
        // If there are no leaves, this is not actually a tree
        if (tree.leaves.size() == 0)
            return 0;

        // Generate saplings
        int saplings = (int)Math.floor((double)tree.leaves.size() / 20.0);
        Material sapling_type = Types.sameSapling(tree.getBlock().getType());
        if (sapling_type != Material.AIR && tree.getTopLocation() != null) {
            for (int i = 0; i < saplings; i++) {
                Item item = tree.getWorld().dropItemNaturally(tree.getTopLocation(),
                        new ItemStack(sapling_type));
                item.setVelocity(item.getVelocity().zero());
            }
        }

        // Drop apples from oaks
        if (tree.getBlock().getType().toString().contains("OAK_LOG")) {
            int apples = new Random().nextInt(3);
            for (int i = 0; i < apples; i++) {
                Item item = tree.getWorld().dropItemNaturally(tree.getTopLocation(),
                        new ItemStack(Material.APPLE));
                item.setVelocity(item.getVelocity().zero());
            }
        }

        // Drop some sticks
        int sticks = new Random().nextInt(3) + 2;
        for (int i = 0; i < sticks; i++) {
            Item item = tree.getWorld().dropItemNaturally(tree.getTopLocation(),
                    new ItemStack(Material.STICK));
            item.setVelocity(item.getVelocity().zero());
        }

        // Break the logs naturally for drops
        for (Block log : tree.logs) {
            log.breakNaturally();
        }

        // Get rid of the leaves
        for (Block leaf : tree.leaves) {
            if (Types.isNetherLeaf(leaf))
                leaf.breakNaturally();
            else
                leaf.setType(Material.AIR);
        }

        // Replant if configured
        if (plugin.config.getBoolean("replant")) {
            boolean found_sapling = false;
            if (plugin.config.getBoolean("replant_requires_sapling")
                    && player.getGameMode() != GameMode.CREATIVE) {

                PlayerInventory inv = player.getInventory();
                for (ItemStack item : inv) {
                    if (item.getType() == sapling_type) {
                        found_sapling = true;
                        item.setAmount(item.getAmount() - 1);
                        break;
                    }
                }
            } else
                found_sapling = true;

            if (found_sapling) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,
                        () -> tree.getBaseBlock().getBlock().setType(sapling_type));
            }
        }

        // Only do tool damage for the number of logs we broke
        return tree.logs.size();
    }

}
