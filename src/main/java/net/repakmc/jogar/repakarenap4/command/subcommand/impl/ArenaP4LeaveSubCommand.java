package net.repakmc.jogar.repakarenap4.command.subcommand.impl;

import lombok.val;
import net.repakmc.jogar.repakarenap4.RepakArenaP4;
import net.repakmc.jogar.repakarenap4.command.subcommand.SubCommand;
import net.repakmc.jogar.repakarenap4.util.chat.ChatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class ArenaP4LeaveSubCommand implements SubCommand {

    private final RepakArenaP4 plugin;

    public ArenaP4LeaveSubCommand(RepakArenaP4 plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("sair", "leave");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player))
            return false;

        val player = (Player) sender;

        if (!(plugin.getGameManager().getPlayersInArena().contains(player))) {
            player.sendMessage(ChatUtils.colorize("&cVocê não está na arena p4. Para entrar digite /arenap4 entrar."));
            return false;
        }

        plugin.getGameManager().removePlayer(player);
        player.teleport(plugin.getGameManager().getExit());

        return false;
    }
}
