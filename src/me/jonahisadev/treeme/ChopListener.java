package me.jonahisadev.treeme;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChopListener implements Listener {

    private Main _plugin;

    public ChopListener(Main plugin)
    {
        _plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onChop(BlockBreakEvent event) {
        // Get some things set up
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack tool = player.getInventory().getItemInMainHand();

        // Verify player has correct permissions
        if (!player.hasPermission("treeme.use"))
            return;

        // Check player store
        if (!_plugin.playerStore.state(player.getUniqueId()).enabled)
            return;

        // If the nether isn't allowed, don't chop in there
        if (Types.isNetherLog(block) && !_plugin.config.getBoolean("allow_in_nether"))
            return;

        // Constraints on when to run chopping algorithm
        if (Types.isLog(block) &&
                !player.isSneaking()) {

            // Check that axe is required
            if (_plugin.config.getBoolean("require_axe") && !tool.getType().toString().contains("AXE"))
                return;

            // Find tree and chop it
            TreeModel tree;
            try {
                tree = new TreeModel(_plugin, player, block);
            } catch (TreeException e) {
                _plugin.getLogger().warning(e.getMessage());
                return;
            }

            int damage = Chopper.go(_plugin, tree, player);

            // Set tool damage if not in creative
            if (player.getGameMode() != GameMode.CREATIVE &&
                    (tool.getItemMeta() instanceof Damageable) &&
                    _plugin.config.getBoolean("damage_tool")) {
                // Item Meta
                ItemMeta meta = tool.getItemMeta();

                // Check that tool should be deleted
                int current_damage = ((Damageable)meta).getDamage();
                _plugin.getLogger().info((current_damage + damage) + "/" + tool.getType().getMaxDurability());
                if (current_damage + damage >= tool.getType().getMaxDurability()) {
                    player.getInventory().setItemInMainHand(null);
                    return;
                }

                // Set damage
                ((Damageable) meta).setDamage(((Damageable) meta).getDamage() + damage);
                tool.setItemMeta(meta);
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        _plugin.playerStore.register(player.getUniqueId());
    }

}
