package me.jonahisadev.treeme;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Leaves;

public class Types {

    public static boolean isLog(Block b)
    {
        return b.getBlockData().getMaterial().toString().contains("_LOG");
    }

    public static boolean isLeaf(Block b)
    {
        return (b.getBlockData() instanceof Leaves);
    }

    public static Material sameSapling(Material logMat)
    {
        return Material.OAK_SAPLING;
    }

}
