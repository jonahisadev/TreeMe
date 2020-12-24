package me.jonahisadev.treeme;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.HashSet;

public class TreeModel {

    public HashSet<Block> logs;
    public HashSet<Block> leaves;

    private Main _plugin;

    public TreeModel(Main plugin, World world, Block block)
    {
        _plugin = plugin;
        logs = new HashSet<>();
        leaves = new HashSet<>();

        find(block);
    }

    private void find(Block block)
    {
        // Algorithm for logs
        if (Types.isLog(block)) {
            logs.add(block);

            // Check all surrounding blocks
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        // Get new location
                        Block check = block.getLocation().add(x, y, z).getBlock();

                        // Recursively find surrounding blocks
                        if ((Types.isLog(check) || Types.isLeaf(check)) && !logs.contains(check))
                            find(check);
                    }
                }
            }
        }

        // Algorithm for leaves
        else if (Types.isLeaf(block)) {
            // TODO: handle leaves
        }
    }


}
