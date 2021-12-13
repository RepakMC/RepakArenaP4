package net.repakmc.jogar.repakarenap4.database.tables;

import lombok.val;
import net.repakmc.jogar.repakarenap4.RepakArenaP4;
import net.repakmc.jogar.repakarenap4.util.location.LocationParser;
import org.bukkit.Location;

import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.*;

public class Locations {

    private final RepakArenaP4 plugin;

    private final String TABLE = "repakarenap4";

    public Locations(RepakArenaP4 plugin) {
        this.plugin = plugin;
    }

    public void createTable() {
        try (val conn = plugin.getDatabaseManager().getDataSource().getConnection()) {
            try (val st = conn.prepareStatement("CREATE TABLE IF NOT EXISTS " + TABLE + "(" +
                    "uuid VARCHAR(32)," +
                    "location VARCHAR(60))")) {
                st.executeUpdate();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void insert(Location location) {
        try (val conn = plugin.getDatabaseManager().getDataSource().getConnection()) {
            try (val st = conn.prepareStatement("INSERT INTO " + TABLE + " (uuid, location) VALUES (?, ?)")) {
                st.setString(1, UUID.randomUUID().toString());
                st.setString(2, LocationParser.locationToString(location));

                st.executeUpdate();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public Location getRandomLocation() {
        List<Location> locations = new ArrayList<>();

        try (val conn = plugin.getDatabaseManager().getDataSource().getConnection()) {
            try (val st = conn.prepareStatement("SELECT * FROM " + TABLE)) {
                try (val rs = st.executeQuery()) {

                    while (rs.next()) {
                        locations.add(LocationParser.stringToLocation(rs.getString("location")));
                    }

                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return locations.get(new Random().nextInt(locations.size()));
    }


}
