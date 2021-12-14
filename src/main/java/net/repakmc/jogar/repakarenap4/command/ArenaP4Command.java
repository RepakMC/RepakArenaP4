package net.repakmc.jogar.repakarenap4.command;

import net.repakmc.jogar.repakarenap4.RepakArenaP4;
import net.repakmc.jogar.repakarenap4.command.subcommand.SubCommand;
import net.repakmc.jogar.repakarenap4.command.subcommand.impl.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ArenaP4Command implements CommandExecutor {

    private final RepakArenaP4 plugin;
    private final List<SubCommand> subCommands;

    public ArenaP4Command(RepakArenaP4 plugin) {
        this.plugin = plugin;
        plugin.getCommand("arenap4").setExecutor(this);
        subCommands = new ArrayList<>();

        subCommands.add(new ArenaP4JoinSubCommand(plugin));
        subCommands.add(new ArenaP4LeaveSubCommand(plugin));
        subCommands.add(new ArenaP4StatusSubCommand(plugin));
        subCommands.add(new ArenaP4AddLocationSubCommand(plugin));
        subCommands.add(new ArenaP4SilentSubCommand(plugin));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length < 1 && sender instanceof Player) {
            Bukkit.dispatchCommand(sender, "arenap4 join");
            return false;
        }

        for (SubCommand subCommand : subCommands) {
            if (subCommand.getAliases().contains(args[0].toLowerCase()))
                return subCommand.execute(sender, args);
        }

        return false;
    }
}
