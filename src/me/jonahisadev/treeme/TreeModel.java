package me.jonahisadev.treeme;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Leaves;

import java.util.HashSet;

public class TreeModel {

    public HashSet<Block> logs;
    public HashSet<Block> leaves;

    private Main _plugin;
    private World _world;
    private Block _block;
    private Location _top;
    private Location _base;

    public TreeModel(Main plugin, World world, Block block)
    {
        _plugin = plugin;
        _world = world;
        _block = block;

        logs = new HashSet<>();
        leaves = new HashSet<>();

        find(block);
        if (_plugin.config.getBoolean("replant"))
            findBase(block);
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
                        if ((Types.isLog(check) || Types.isLeaf(check)) &&
                                !logs.contains(check) && !leaves.contains(check))
                            find(check);
                    }
                }
            }
        }

        // Nether leaves are weird
        else if (Types.isNetherLeaf(block)) {
            leaves.add(block);

            while (true) {
                HashSet<Block> local_set = new HashSet<>();

                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        for (int z = -1; z <= 1; z++) {
                            Block check = block.getLocation().add(x, y, z).getBlock();

                            if ((Types.isLog(check) || Types.isLeaf(check)) &&
                                    !logs.contains(check) && !leaves.contains(check)) {

                                local_set.add(check);
                                _top = check.getLocation();
                                find(check);
                            }
                        }
                    }
                }

                if (local_set.size() == 0)
                    break;
            }
        }

        // Algorithm for leaves
        else if (Types.isLeaf(block)) {
            leaves.add(block);

            int y = 0;
            while (true) {
                HashSet<Block> local_set = new HashSet<>();

                for (int x = -1; x <= 1; x++) {
                    for (int z = -1; z <= 1; z++) {
                        Block check = block.getLocation().add(x, y, z).getBlock();

                        if ((Types.isLeaf(check)) && !leaves.contains(check)) {

                            // Maybe a way to check if it's the same tree?
                            int check_dist = ((Leaves)check.getBlockData()).getDistance();
                            int this_dist = ((Leaves)block.getBlockData()).getDistance();
                            if (check_dist <= this_dist)
                                continue;

                            local_set.add(check);
                            _top = check.getLocation();
                            find(check);
                        }
                    }
                }

                if (local_set.size() == 0)
                    break;

                ++y;
            }
        }
    }

    public void findBase(Block block)
    {
        Location loc = block.getLocation();

        while (true)
        {
            loc.subtract(0, 1, 0);
            Block check = loc.getBlock();

            if (!Types.isLog(check)) {
                break;
            }
        }

        loc.add(0, 1, 0);
        _base = loc;
    }

    public World getWorld()
    {
        return _world;
    }

    public Block getBlock()
    {
        return _block;
    }

    public Location getTopLocation()
    {
        return _top;
    }

    public Location getBaseBlock()
    {
        return _base;
    }

}
