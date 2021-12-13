package net.repakmc.jogar.repakarenap4;

import lombok.Getter;
import net.repakmc.jogar.repakarenap4.command.ArenaP4Command;
import net.repakmc.jogar.repakarenap4.database.manager.impl.DatabaseManager;
import net.repakmc.jogar.repakarenap4.manager.GameManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class RepakArenaP4 extends JavaPlugin {

    @Getter private DatabaseManager databaseManager;

    @Getter private GameManager gameManager;

    @Override
    public void onEnable() {
        loadModules();
        registerCommands();
    }

    @Override
    public void onDisable() {
        gameManager.saveAllPlayers();
    }

    private void registerCommands() {
        new ArenaP4Command(this);
    }

    private void loadModules() {
        saveDefaultConfig();
        gameManager = new GameManager(this);
        initDatabase();
    }

    private void initDatabase() {
        databaseManager = new DatabaseManager(this);
        databaseManager.load();
    }

}
