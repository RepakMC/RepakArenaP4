package net.repakmc.jogar.repakarenap4.command.subcommand.impl;

import lombok.val;
import net.repakmc.jogar.repakarenap4.RepakArenaP4;
import net.repakmc.jogar.repakarenap4.command.subcommand.SubCommand;
import net.repakmc.jogar.repakarenap4.util.chat.ChatUtils;
import net.repakmc.jogar.repakarenap4.util.player.PlayerUtil;
import net.repakmc.jogar.repakarenap4.util.time.TimeUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class ArenaP4JoinSubCommand implements SubCommand {

    private final RepakArenaP4 plugin;

    public ArenaP4JoinSubCommand(RepakArenaP4 plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("entrar", "join");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player))
            return false;

        val player = (Player) sender;

        if (plugin.getGameManager().getPlayersInArena().contains(player)) {
            player.sendMessage(ChatUtils.colorize("&cVocê já está nessa arena, para sair digite /arenap4 sair."));
            return false;
        }

        if (plugin.getGameManager().getCooldownManager().isInCooldown(player.getName())) {
            player.sendMessage(ChatUtils.colorize("&cVocê só pode entrar nessa arena novamente daqui &7" + plugin.getGameManager().getCooldownManager().getRemainingTime(player.getName())));
            return false;
        }

        if (!(PlayerUtil.inventoryIsCleared(player))) {
            player.sendMessage(ChatUtils.colorize("Limpe seu inventário para poder entrar na arena p4."));
            return false;
        }

        plugin.getGameManager().addPlayer(player);

        player.sendMessage(ChatUtils.colorize("&aVocê entrou na arena. Boa sorte em suas batalhas hein!"));

        return false;
    }
}
