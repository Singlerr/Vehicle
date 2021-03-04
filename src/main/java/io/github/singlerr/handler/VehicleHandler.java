package io.github.singlerr.handler;


import io.github.singlerr.Cuboid;
import io.github.singlerr.Vehicle;
import io.github.singlerr.threadsafe.BlockSpawner;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.HashMap;


public class VehicleHandler {


    private Vehicle instance;

    private BlockHandler blockHandler;
    private HashMap<String, VehicleObject> vehicles;
    private HashMap<String, VehicleObject> activatedVehicles;


    public VehicleHandler(Vehicle instance, BlockHandler blockHandler){
        this.vehicles = new HashMap<>();
        this.activatedVehicles = new HashMap<>();
        this.blockHandler = blockHandler;
        this.instance = instance;
    }

    public void addVehicle(String name,VehicleObject vehicle){
        this.vehicles.put(name,vehicle);
    }

    public void addVehicle(String name,Cuboid cuboid){
        VehicleObject object = new VehicleObject(cuboid);
        this.vehicles.put(name,object);
    }

    public void spawnVehicle(String name, Location start){
        BlockSpawner spawner = new BlockSpawner(instance,blockHandler,start,this.vehicles.get(name));
        spawner.runTask(instance);
        this.activatedVehicles.put(name,vehicles.get(name));
    }

    public void removeVehicle(String name){

        if(this.activatedVehicles.containsKey(name)){
            VehicleObject object = this.activatedVehicles.get(name);
            object.getController().cancel();
            object.getVehicleParts().forEach(set -> {
                set.getFallingBlock().remove();
                set.getArmorStand().remove();
            });
            this.activatedVehicles.remove(name);

        }
        this.vehicles.remove(name);
    }
    public VehicleObject getVehicle(String name){
        return this.vehicles.get(name);
    }

    public VehicleObject getActivatedVehicle(String name){
        return this.activatedVehicles.get(name);
    }
    public void destroy(String name){
        if(this.activatedVehicles.containsKey(name)){
            VehicleObject object = this.activatedVehicles.get(name);
            object.getController().cancel();
            object.getVehicleParts().forEach(set -> {
                set.getFallingBlock().remove();
                set.getArmorStand().remove();
            });
            this.activatedVehicles.remove(name);
        }
    }

}
