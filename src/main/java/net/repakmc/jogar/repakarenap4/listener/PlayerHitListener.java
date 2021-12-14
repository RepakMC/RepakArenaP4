package net.repakmc.jogar.repakarenap4.listener;

import lombok.val;
import net.repakmc.jogar.repakarenap4.RepakArenaP4;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerHitListener implements Listener {

    private final RepakArenaP4 plugin;

    public PlayerHitListener(RepakArenaP4 plugin) {
        this.plugin = plugin;
    }

    // to fix
    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player) && !(event.getDamager() instanceof Player))
            return;

        val damager = (Player) event.getDamager();

        if (!(plugin.getGameManager().getPlayersInArena().contains(damager)))
            event.setCancelled(true);

    }

}
