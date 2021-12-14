package net.repakmc.jogar.repakarenap4.listener;

import lombok.val;
import net.repakmc.jogar.repakarenap4.RepakArenaP4;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerKillListener implements Listener {

    private final RepakArenaP4 plugin;

    public PlayerKillListener(RepakArenaP4 plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    // to fix
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        val player = event.getEntity().getPlayer();
        val killer = event.getEntity().getKiller();

    }

}
