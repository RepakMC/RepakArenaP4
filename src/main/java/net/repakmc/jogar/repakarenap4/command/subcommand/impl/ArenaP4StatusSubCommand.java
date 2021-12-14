package net.repakmc.jogar.repakarenap4.command.subcommand.impl;

import net.repakmc.jogar.repakarenap4.RepakArenaP4;
import net.repakmc.jogar.repakarenap4.command.subcommand.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class ArenaP4StatusSubCommand implements SubCommand {

    private final RepakArenaP4 plugin;

    public ArenaP4StatusSubCommand(RepakArenaP4 plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("status", "info");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {


        return false;
    }
}
