package net.repakmc.jogar.repakarenap4.listener;

import lombok.val;
import net.repakmc.jogar.repakarenap4.RepakArenaP4;
import net.repakmc.jogar.repakarenap4.util.chat.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final RepakArenaP4 plugin;

    public PlayerQuitListener(RepakArenaP4 plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    // Right
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        val player = event.getPlayer();

        if (!(plugin.getGameManager().getPlayersInArena().contains(player)))
            return;

        plugin.getGameManager().removePlayer(player);

        plugin.getGameManager().getPlayersInArena().forEach(it -> it.sendMessage(ChatUtils.colorize("&c[ARENA P4] O jogador &7" + player.getName() + "&c arregou e saiu do servidor.")));
    }
}
