package me.jonahisadev.treeme;

import org.bukkit.block.Block;

public class Types {

    public static boolean isLog(Block b)
    {
        return b.getBlockData().getMaterial().toString().contains("_LOG");
    }

}
