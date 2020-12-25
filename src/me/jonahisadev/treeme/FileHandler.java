package me.jonahisadev.treeme;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class FileHandler {

    private File _file;
    private YamlConfiguration _config;

    public FileHandler(File file)
    {
        _file = file;
        _config = new YamlConfiguration();
        if (file.exists())
            load();
    }

    public void set(String var, Object obj)
    {
        _config.set(var, obj);
    }

    public Object get(String var)
    {
        return _config.get(var);
    }

    public void save()
    {
        try {
            _config.save(_file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load()
    {
        try {
            _config.load(_file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
