package net.repakmc.jogar.repakarenap4.util.player;

import lombok.experimental.UtilityClass;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class PlayerUtil {

    public boolean inventoryIsCleared(Player player) {
        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (itemStack != null && itemStack.getType() != Material.AIR)
                return false;
        }

        for (ItemStack itemStack : player.getInventory().getArmorContents()) {
            if (itemStack != null && itemStack.getType() != Material.AIR)
                return false;
        }

        if (player.getItemOnCursor() != null && player.getItemOnCursor().getType() != Material.AIR)
            return false;

        return true;
    }

    private void clearInventory(Player player) {
        player.getInventory().clear();

        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
        player.setItemOnCursor(null);
    }

    public void onJoin(Player player) {
        clearEffects(player);
        player.setFireTicks(0);
        player.setFoodLevel(20);
        player.setFlying(false);
        player.setGameMode(GameMode.SURVIVAL);
        player.setHealth(20);
        player.setSaturation(20);
    }

    public void onLeave(Player player) {
        clearEffects(player);
        player.setHealth(20);
        player.setSaturation(20);
        player.setFireTicks(0);
        player.setFoodLevel(20);
        clearInventory(player);
    }

    private void clearEffects(Player player) {
        player.getActivePotionEffects().forEach(it -> {
            player.removePotionEffect(it.getType());
        });
    }

}
