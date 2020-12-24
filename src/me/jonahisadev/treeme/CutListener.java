package me.jonahisadev.treeme;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CutListener implements Listener {

    private Main _plugin;

    public CutListener(Main plugin)
    {
        _plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onChop(BlockBreakEvent event) {
        // Get some things set up
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack tool = player.getInventory().getItemInMainHand();

        // Constraints on when to run chopping algorithm
        if (Types.isLog(block) &&
                tool.getType().toString().contains("AXE") &&
                !player.isSneaking()) {

            // Find tree and chop it
            TreeModel tree = new TreeModel(_plugin, player.getWorld(), block);
            int damage = Chopper.go(_plugin, tree);

            // Set tool damage if not in creative
            if (player.getGameMode() != GameMode.CREATIVE) {
                ItemMeta meta = tool.getItemMeta();
                ((Damageable) meta).setDamage(((Damageable) meta).getDamage() + damage);
                tool.setItemMeta(meta);
            }
        }
    }

}
