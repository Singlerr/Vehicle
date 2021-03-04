package io.github.singlerr.threadsafe;

import io.github.singlerr.Vehicle;
import io.github.singlerr.handler.ElementSet;
import io.github.singlerr.handler.VehicleObject;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

@Getter
@Setter
public class BlockController extends BukkitRunnable {

    private VehicleObject object;
    private double speed;
    private double acceleration;
    private double time;
    private double currentTime;
    private int count;
    private double total;
    public BlockController(VehicleObject object,double time,double acceleration){
        this.object = object;
        this.time = time;
        this.speed = 0;
        this.acceleration = acceleration;
        this.currentTime = 0;
        this.count = 0;
        this.total = 0;
    }

    @Override
    public void run() {

        this.count++;
        this.currentTime = count * time;

        this.speed = speed + currentTime*acceleration;

        for(ElementSet set : object.getVehicleParts()){
            set.getFallingBlock().setTicksLived(1);
            set.getArmorStand().setVelocity(new Vector(speed,0,0));
            set.getArmorStand().setGravity(true);

        }
    if(speed != 0)
        Vehicle.passengers.forEach(p ->
                p.setVelocity(new Vector(speed,0,0)));


    }
}
