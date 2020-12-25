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
        // Stuff
        playerStore = new PlayerStore();

        // Make sure we can place data
        File file = new File("plugins/TreeMe/players/");
        file.mkdirs();

        // Register
        getServer().getPluginManager().registerEvents(new ChopListener(this), this);
        getCommand("treeme").setExecutor(new ChopCommand(this));
    }

}
