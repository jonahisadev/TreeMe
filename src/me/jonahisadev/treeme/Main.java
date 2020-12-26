package me.jonahisadev.treeme;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    public PlayerStore playerStore;
    public FileConfiguration config;

    public WorldGuardPlugin wg;

    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {
        // Setup config
        saveDefaultConfig();
        config = getConfig();

        // Make sure we can place data
        File players_dir = new File("plugins/TreeMe/players/");
        players_dir.mkdirs();

        // Player store
        playerStore = new PlayerStore();
        playerStore.loadFromDisk(players_dir);

        // Load WorldGuard
        Plugin pl = getServer().getPluginManager().getPlugin("WorldGuard");
        if (pl instanceof WorldGuardPlugin) {
            getLogger().info("TreeMe will integrate with WorldGuard");
            wg = WorldGuardPlugin.inst();
        } else
            wg = null;

        // Register
        getServer().getPluginManager().registerEvents(new ChopListener(this), this);
        getCommand("treeme").setExecutor(new ChopCommand(this));
        getCommand("treeme").setTabCompleter(new ChopCommandCompleter(this));
    }

}
