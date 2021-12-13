package net.repakmc.jogar.repakarenap4.database.datasource.impl;

import lombok.val;
import net.repakmc.jogar.repakarenap4.RepakArenaP4;
import net.repakmc.jogar.repakarenap4.database.datasource.DataSource;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite implements DataSource {

    private final String url;

    public SQLite(RepakArenaP4 plugin) {
        val file = new File(plugin.getDataFolder(), "database.db");

        this.url = "jdbc:sqlite:" + file;

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }

    @Override
    public void closeConnection() {
        // Not used
    }
}
