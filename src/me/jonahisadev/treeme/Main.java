package me.jonahisadev.treeme;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    public PlayerStore playerStore;

    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {
        // Make sure we can place data
        File players_dir = new File("plugins/TreeMe/players/");
        players_dir.mkdirs();

        // Stuff
        playerStore = new PlayerStore();
        playerStore.loadFromDisk(players_dir);

        // Register
        getServer().getPluginManager().registerEvents(new ChopListener(this), this);
        getCommand("treeme").setExecutor(new ChopCommand(this));
        getCommand("treeme").setTabCompleter(new ChopCommandCompleter());
    }

}
