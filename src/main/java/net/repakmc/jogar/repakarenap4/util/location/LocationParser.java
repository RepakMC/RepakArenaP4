package net.repakmc.jogar.repakarenap4.util.location;

import lombok.experimental.UtilityClass;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.Location;

@UtilityClass
public class LocationParser {

    public String locationToString(Location location) {
        return location.getWorld().getName() + ";" +
                location.getX() + ";" +
                location.getY() + ";" +
                location.getZ() + ";" +
                location.getYaw() + ";" +
                location.getPitch();
    }

    public Location stringToLocation(String data) {
        val split = data.split(";");

        return new Location(
                Bukkit.getWorld(split[0]),
                Double.parseDouble(split[1]),
                Double.parseDouble(split[2]),
                Double.parseDouble(split[3]),
                Float.parseFloat(split[4]),
                Float.parseFloat(split[5])
        );
    }

}
