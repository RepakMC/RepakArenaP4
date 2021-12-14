package net.repakmc.jogar.repakarenap4.listener;

import lombok.val;
import net.repakmc.jogar.repakarenap4.RepakArenaP4;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItemListener implements Listener {

    private final RepakArenaP4 plugin;

    public PlayerDropItemListener(RepakArenaP4 plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event) {
        val player = event.getPlayer();

        if (plugin.getGameManager().getPlayersInArena().contains(player))
            event.setCancelled(true);

    }

}
