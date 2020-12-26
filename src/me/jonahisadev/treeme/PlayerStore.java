package me.jonahisadev.treeme;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class PlayerStore {

    class Options
    {
        public boolean enabled;
        public boolean replant;

        public Options(boolean enabled, boolean replant)
        {
            this.enabled = enabled;
            this.replant = replant;
        }
    }

    private HashMap<UUID, Options> _internal;

    public PlayerStore()
    {
        _internal = new HashMap<>();
    }

    public void toggle(UUID uuid, String field)
    {
        // Toggle in player store
        Options val = _internal.get(uuid);
        switch (field) {
            case "enabled":
                val.enabled = !val.enabled;
                break;
            case "replant":
                val.replant = !val.replant;
                break;
            default:
                break;
        }

        // Save to file
        FileHandler handler = new FileHandler(new File("plugins/TreeMe/players/" + uuid.toString() + ".yml"));
        handler.set("enabled", val.enabled);
        handler.set("replant", val.replant);
        handler.save();
    }

    public Options state(UUID uuid)
    {
        return _internal.get(uuid);
    }

    public void register(UUID uuid)
    {
        if (!_internal.containsKey(uuid))
            _internal.put(uuid, new Options(true, true));
    }

    public void loadFromDisk(File dir)
    {
        File[] files = dir.listFiles();
        if (files == null)
            return;
        for (File file : files) {
            UUID uuid = UUID.fromString(file.getName().replace(".yml", ""));
            FileHandler config = new FileHandler(file);
            _internal.put(uuid, new Options(
                (Boolean)config.get("enabled"),
                (Boolean)config.get("replant")
            ));
        }
    }

}
