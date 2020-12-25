package me.jonahisadev.treeme;

import org.bukkit.configuration.file.YamlConfiguration;

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
        Boolean val = _internal.get(uuid);
        if (val == null)
            _internal.put(uuid, false);
        else
            _internal.put(uuid, !_internal.get(uuid));
    }

    public boolean state(UUID uuid)
    {
        Boolean val = _internal.get(uuid);
        return (val == null) ? true : val;
    }

}
