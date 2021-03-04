package io.github.singlerr;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerListener implements Listener {

    private Vehicle instance;
    public PlayerListener(Vehicle instance){
        this.instance = instance;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (instance.enabled.contains(e.getPlayer().getName())) {
            if (e.getClickedBlock().getType() != Material.AIR) {
                if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                    Vehicle.map1.put(e.getPlayer().getName(), e.getClickedBlock().getLocation());
                } else if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    Vehicle.map2.put(e.getPlayer().getName(), e.getClickedBlock().getLocation());
                }
            }
            e.setCancelled(true);
        }
    }
}
