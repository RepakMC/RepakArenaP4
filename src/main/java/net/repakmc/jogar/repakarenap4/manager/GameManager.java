package net.repakmc.jogar.repakarenap4.manager;

import lombok.Getter;
import lombok.val;
import net.repakmc.jogar.repakarenap4.RepakArenaP4;
import net.repakmc.jogar.repakarenap4.util.chat.ChatUtils;
import net.repakmc.jogar.repakarenap4.util.location.LocationParser;
import net.repakmc.jogar.repakarenap4.util.player.PlayerUtil;
import net.repakmc.jogar.repakarenap4.util.serializer.ItemSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager {

    private final RepakArenaP4 plugin;

    @Getter private final CooldownManager cooldownManager;

    @Getter private final List<Player> playersInArena;
    private final Map<String, Integer> killStreak;

    private final Location exit;

    private final ItemStack[] kit;

    public GameManager(RepakArenaP4 plugin) {
        this.plugin = plugin;
        this.playersInArena = new ArrayList<>();
        this.killStreak = new HashMap<>();
        this.cooldownManager = new CooldownManager();
        this.kit = ItemSerializer.itemStackArrayFromBase64(plugin.getConfig().getString("Arena.Kit"));
        this.exit = LocationParser.stringToLocation(plugin.getConfig().getString("Arena.Exit"));
    }

    public void addPlayer(Player player) {
        this.playersInArena.add(player);
        PlayerUtil.onJoin(player);
        cooldownManager.add(player.getName());
        giveKit(player);

        for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
            if (!(playersInArena.contains(otherPlayer)))
                otherPlayer.sendMessage(ChatUtils.colorize("&c[ARENA P4] O jogador " + player.getName() + " entrou na arena. Para entrar use: /arenap4 entrar."));
        }

        for (Player otherPlayer : playersInArena) {
            if (!(otherPlayer.getName().equals(player.getName())))
                otherPlayer.sendMessage(ChatUtils.colorize("&c[ARENA P4] O jogador " + player.getName() + " entrou na arena."));
        }

        player.teleport(plugin.getDatabaseManager().getLocations().getRandomLocation());
    }

    public void removePlayer(Player player) {
        this.playersInArena.remove(player);
        PlayerUtil.onLeave(player);
        killStreak.remove(player.getName());
        player.teleport(exit);
    }

    public void addKill(Player player) {
        if (killStreak.containsKey(player.getName())) {
            val oldValue = killStreak.get(player.getName());
            killStreak.replace(player.getName(), oldValue + 1);
        } else {
            killStreak.put(player.getName(), 1);
        }

        if (killStreak.get(player.getName()) % 5 == 0) {
            playersInArena.forEach(it -> {
                it.sendMessage(ChatUtils.colorize("O jogador " + player.getName() + "alcancou o kill-streak de " + killStreak.get(player.getName())));
            });
        }
    }

    public void removeKillStreak(Player target, Player killer) {
        if (killStreak.get(target.getName()) > 5) {
            playersInArena.forEach(it -> it.sendMessage("O jogador " + target.getName() + " morreu com a kill-streak de " + killStreak.get(target.getName()) + " para o " + killer.getName()));
        }

        killStreak.remove(target.getName());
    }

    public void saveAllPlayers() {
        playersInArena.forEach(this::removePlayer);
    }

    public void giveKit(Player player) {
        int slot = 0;

        for (ItemStack itemStack : kit) {
            player.getInventory().setItem(slot, itemStack);
            slot++;
        }

    }

}
