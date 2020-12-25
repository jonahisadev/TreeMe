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
        switch (logMat) {
            case OAK_LOG:
            case STRIPPED_OAK_LOG:
                return Material.OAK_SAPLING;
            case DARK_OAK_LOG:
            case STRIPPED_DARK_OAK_LOG:
                return Material.DARK_OAK_SAPLING;
            case SPRUCE_LOG:
            case STRIPPED_SPRUCE_LOG:
                return Material.SPRUCE_SAPLING;
            case BIRCH_LOG:
            case STRIPPED_BIRCH_LOG:
                return Material.BIRCH_SAPLING;
            case ACACIA_LOG:
            case STRIPPED_ACACIA_LOG:
                return Material.ACACIA_SAPLING;
            default:
                return Material.AIR;
        }
    }

}
