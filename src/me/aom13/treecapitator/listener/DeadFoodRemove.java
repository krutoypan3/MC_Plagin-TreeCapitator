package me.aom13.treecapitator.listener;

import me.aom13.treecapitator.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class DeadFoodRemove implements Listener {
    private Player player_dead;
    int player_food_level;

    public static me.aom13.treecapitator.Main plugin;

    public DeadFoodRemove(Main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent deathEvent) {
        player_dead = deathEvent.getEntity().getPlayer();
        player_food_level = deathEvent.getEntity().getFoodLevel();
        player_dead.sendMessage(String.valueOf(player_food_level));
    }


    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent respawnEvent) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                if (player_food_level < 6){player_food_level = 6;}
                player_dead.setFoodLevel(player_food_level);
                player_dead.setHealth(6f);
            } }, 1L);
    }
}
