package me.jonahisadev.treeme;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class PlayerStore {

    private HashMap<UUID, Boolean> _internal;

    public PlayerStore()
    {
        _internal = new HashMap<>();
    }

    public void toggle(UUID uuid)
    {
        // Toggle in player store
        Boolean val = _internal.get(uuid);
        if (val == null)
            _internal.put(uuid, false);
        else
            _internal.put(uuid, !_internal.get(uuid));

        // Save to file
        FileHandler handler = new FileHandler(new File("plugins/TreeMe/players/" + uuid.toString() + ".yml"));
        handler.set("enabled", _internal.get(uuid));
        handler.save();
    }

    public boolean state(UUID uuid)
    {
        Boolean val = _internal.get(uuid);
        return (val == null) ? true : val;
    }

    public void loadFromDisk(File dir)
    {
        File[] files = dir.listFiles();
        if (files == null)
            return;
        for (File file : files) {
            UUID uuid = UUID.fromString(file.getName().replace(".yml", ""));
            FileHandler config = new FileHandler(file);
            _internal.put(uuid, (Boolean)config.get("enabled"));
        }
    }

}
