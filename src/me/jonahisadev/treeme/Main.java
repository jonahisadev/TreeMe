package me.jonahisadev.treeme;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin {

    public ArrayList<Material> LOGS = new ArrayList<>();

    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {
        LOGS.add(Material.OAK_LOG);
        LOGS.add(Material.SPRUCE_LOG);
        LOGS.add(Material.BIRCH_LOG);
        LOGS.add(Material.JUNGLE_LOG);
        LOGS.add(Material.ACACIA_LOG);
        LOGS.add(Material.DARK_OAK_LOG);

        getServer().getPluginManager().registerEvents(new CutListener(this), this);
    }

}
