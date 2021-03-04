package io.github.singlerr;

import io.github.singlerr.handler.BlockHandler;
import io.github.singlerr.handler.ElementSet;
import io.github.singlerr.handler.VehicleHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Vehicle extends JavaPlugin {


    private static Vehicle instance;

    private VehicleHandler vehicleHandler;
    private BlockHandler blockHandler;


    public static HashMap<String, Location> map1 = new HashMap<>();
    public static HashMap<String, Location> map2 = new HashMap<>();
    public  List<String> enabled = new ArrayList<>();

    public static List<Player> passengers = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;
        blockHandler = new BlockHandler(instance);
        vehicleHandler = new VehicleHandler(instance,blockHandler);

        Bukkit.getPluginManager().registerEvents(new PlayerListener(instance),instance);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if(label.equalsIgnoreCase("vehicle")){
            if(args.length > 0){
                if(args[0].equalsIgnoreCase("mode")){
                    if(enabled.contains(player.getName())){
                        enabled.remove(player.getName());
                        player.sendMessage("MODE 비활성화");
                    }else{
                        enabled.add(player.getName());
                        player.sendMessage("MODE 활성화");
                    }
                }else if(args[0].equalsIgnoreCase("spawn")){
                    Cuboid cuboid = new Cuboid(map1.get(player.getName()),map2.get(player.getName()));
                    vehicleHandler.addVehicle("test",cuboid);
                    vehicleHandler.spawnVehicle("test",player.getLocation());

                    player.sendMessage("스폰 완료");
                }else if(args[0].equalsIgnoreCase("acc")){
                    double acc = Double.parseDouble(args[1]);
                    vehicleHandler.getActivatedVehicle("test").getController().setSpeed(acc);
                }else if(args[0].equalsIgnoreCase("speed")){
                    vehicleHandler.getActivatedVehicle("test").getController().setSpeed(Double.parseDouble(args[1]));
                }else if(args[0].equalsIgnoreCase("tt")){
                    blockHandler.spawnBlock_(player.getLocation(),Material.IRON_BLOCK);
                }else if(args[0].equalsIgnoreCase("add")){
                    passengers.add(player);
                }

            }
        }

        if(label.equalsIgnoreCase("mblock")){


        }else if(label.equalsIgnoreCase("enabled")){

        }
        return false;
    }
}
