package me.jonahisadev.treeme;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class ChopCommandCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            String[] list = new String[]{
                "toggle",
                "help"
            };
            return Arrays.asList(list);
        }

        return null;
    }

}
