package me.jonahisadev.treeme;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;

public class Chopper {

    public static int go(Main plugin, World world, Block block)
    {
        ArrayList<Block> trunk = new ArrayList<>();
        trunk.add(block);

        Location base = block.getLocation();

        // Go down
        base.add(0, -1, 0);
        while (Types.isLog(base.getBlock())) {
            trunk.add(base.getBlock());
            base.add(0, -1, 0);
        }

        // Go up
        base = block.getLocation();
        base.add(0, 1, 0);
        while (Types.isLog(base.getBlock())) {
            trunk.add(base.getBlock());
            base.add(0, 1, 0);
        }

        int ret = trunk.size();

        // Iterate trunks, and drop them
        for (Block log : trunk) {
            if (log.getState().isPlaced())
                log.breakNaturally();
        }

        return ret;
    }

}
