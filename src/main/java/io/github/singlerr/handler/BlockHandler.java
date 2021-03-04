package io.github.singlerr.handler;

import io.github.singlerr.Vehicle;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftBoat;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.*;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BlockHandler {

    private Vehicle instance;

    public BlockHandler(Vehicle instance){
        this.instance = instance;
    }

    public ElementSet spawnBlock_(Location loc,Material material){
        ArmorStand armorStand = loc.getWorld().spawn(loc,ArmorStand.class);

        armorStand.setMarker(false);
        armorStand.setVisible(false);




        FallingBlock block = loc.getWorld().spawnFallingBlock(loc, material,(byte)0x0);

        block.setDropItem(false);
        block.setGravity(false);
        block.setInvulnerable(true);





       Boat boat = loc.getWorld().spawn(loc,Boat.class);
        ((CraftBoat)boat).getHandle().setInvisible(true);

        boat.setGravity(false);

        boat.setSilent(true);
        armorStand.setGravity(false);

        armorStand.addPassenger(boat);
        armorStand.addPassenger(block);


        return new ElementSet(armorStand,block);
    }
    public ElementSet spawnBlock(Location loc, Block b){
        ArmorStand armorStand = loc.getWorld().spawn(loc,ArmorStand.class);

        armorStand.setMarker(false);
        armorStand.setVisible(false);




        FallingBlock block = loc.getWorld().spawnFallingBlock(loc,b.getState().getData());

        block.setDropItem(false);
        block.setGravity(false);
        block.setInvulnerable(true);



        Shulker shulker = loc.getWorld().spawn(loc,Shulker.class);


        shulker.setGravity(false);
        shulker.setAI(false);
        shulker.setSilent(true);

        shulker.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,9999,99999));
        shulker.setFallDistance(0);
        armorStand.setGravity(false);

        armorStand.addPassenger(shulker);
        armorStand.addPassenger(block);

        return new ElementSet(armorStand,block);
    }
}
