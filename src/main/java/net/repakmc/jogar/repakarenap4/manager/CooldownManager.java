package net.repakmc.jogar.repakarenap4.manager;

import lombok.val;
import net.repakmc.jogar.repakarenap4.util.time.TimeUtils;

import java.util.HashMap;
import java.util.Map;

public class CooldownManager {

    private final Map<String, Long> playersInCooldown;

    public CooldownManager() {
        this.playersInCooldown = new HashMap<>();
    }

    public void add(String username) {
        playersInCooldown.put(username, (System.currentTimeMillis()) + 60 * 1000);
    }

    public boolean isInCooldown(String username) {
        if (!(playersInCooldown.containsKey(username)))
            return false;

        if (playersInCooldown.get(username) < System.currentTimeMillis())
            playersInCooldown.remove(username);

        return playersInCooldown.containsKey(username);
    }

    public String getRemainingTime(String username) {
        val time = playersInCooldown.get(username) - System.currentTimeMillis();

        return TimeUtils.formatMillisToString(time);
    }

}
