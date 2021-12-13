package net.repakmc.jogar.repakarenap4.database.manager.impl;

import lombok.Getter;
import net.repakmc.jogar.repakarenap4.RepakArenaP4;
import net.repakmc.jogar.repakarenap4.database.datasource.DataSource;
import net.repakmc.jogar.repakarenap4.database.datasource.impl.SQLite;
import net.repakmc.jogar.repakarenap4.database.manager.Manager;
import net.repakmc.jogar.repakarenap4.database.tables.Locations;

public class DatabaseManager implements Manager {

    private final RepakArenaP4 plugin;

    @Getter private DataSource dataSource;

    @Getter private Locations locations;

    public DatabaseManager(RepakArenaP4 plugin) {
        this.plugin = plugin;
        this.dataSource = new SQLite(plugin);
    }

    @Override
    public void load() {
        this.locations = new Locations(plugin);
        locations.createTable();
    }
}
