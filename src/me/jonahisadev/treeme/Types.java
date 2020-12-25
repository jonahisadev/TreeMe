package me.jonahisadev.treeme;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Leaves;

public class Types {

    public static boolean isLog(Block b)
    {
        return (
                b.getBlockData().getMaterial().toString().contains("_LOG") ||
                isNetherLog(b)
        );
    }

    public static boolean isNetherLog(Block b)
    {
        return (
                b.getBlockData().getMaterial().toString().equals("CRIMSON_STEM") ||
                b.getBlockData().getMaterial().toString().equals("WARPED_STEM")
        );
    }

    public static boolean isLeaf(Block b)
    {
        return (
                (b.getBlockData() instanceof Leaves) ||
                isNetherLeaf(b)
        );
    }

    public static boolean isNetherLeaf(Block b)
    {
        return (
                b.getBlockData().getMaterial().toString().equals("WARPED_WART_BLOCK") ||
                b.getBlockData().getMaterial().toString().equals("NETHER_WART_BLOCK") ||
                b.getBlockData().getMaterial().toString().equals("SHROOMLIGHT")
        );
    }

    public static boolean isNetherBlock(Block b)
    {
        return isNetherLog(b) || isNetherLeaf(b);
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
