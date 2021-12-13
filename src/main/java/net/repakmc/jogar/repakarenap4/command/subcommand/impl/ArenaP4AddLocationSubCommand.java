package net.repakmc.jogar.repakarenap4.command.subcommand.impl;

import lombok.val;
import net.repakmc.jogar.repakarenap4.RepakArenaP4;
import net.repakmc.jogar.repakarenap4.command.subcommand.SubCommand;
import net.repakmc.jogar.repakarenap4.util.chat.ChatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class ArenaP4AddLocationSubCommand implements SubCommand {

    private final RepakArenaP4 plugin;

    public ArenaP4AddLocationSubCommand(RepakArenaP4 plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("addlocation");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player))
            return false;

        val player = (Player) sender;

        val location = player.getLocation();

        plugin.getDatabaseManager().getLocations().insert(location);

        player.sendMessage(ChatUtils.colorize("&aVocê adicionou essa localidade como um ponto de spawn da arena."));

        return false;
    }
}
