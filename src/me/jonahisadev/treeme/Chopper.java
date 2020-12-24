package me.jonahisadev.treeme;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;

public class Chopper {

    public static int go(Main plugin, TreeModel tree)
    {
        // If there are no leaves, this is not actually a tree
        if (tree.leaves.size() == 0)
            return 0;

        // Break the logs naturally for drops
        for (Block log : tree.logs) {
            log.breakNaturally();
        }

        // Get rid of the leaves
        for (Block leaf : tree.leaves) {
            leaf.setType(Material.AIR);
        }

        // TODO: Generate other things that trees drop

        // Only do tool damage for the number of logs we broke
        return tree.logs.size();
    }

}
