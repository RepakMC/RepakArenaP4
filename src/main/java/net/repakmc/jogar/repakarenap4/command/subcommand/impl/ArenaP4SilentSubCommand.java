package net.repakmc.jogar.repakarenap4.command.subcommand.impl;

import lombok.val;
import net.repakmc.jogar.repakarenap4.RepakArenaP4;
import net.repakmc.jogar.repakarenap4.command.subcommand.SubCommand;
import net.repakmc.jogar.repakarenap4.util.chat.ChatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class ArenaP4SilentSubCommand implements SubCommand {

    private final RepakArenaP4 plugin;

    public ArenaP4SilentSubCommand(RepakArenaP4 plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("silent", "silenciar");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player))
            return false;

        val player = (Player) sender;

        if (!(plugin.getGameManager().getSilentPlayers().contains(player))) {
            plugin.getGameManager().getSilentPlayers().add(player);
            player.sendMessage(ChatUtils.colorize("&cVocê silenciou as notificações da Arena P4."));
        } else {
            plugin.getGameManager().getSilentPlayers().remove(player);
            player.sendMessage(ChatUtils.colorize("&cVocê dessilenciou as notificações da Arena P4."));
        }

        return false;
    }
}
