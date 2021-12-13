package net.repakmc.jogar.repakarenap4.database.datasource;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataSource {

    Connection getConnection() throws SQLException;

    void closeConnection();

}
