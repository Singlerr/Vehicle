package io.github.singlerr.threadsafe;

import io.github.singlerr.Vehicle;
import io.github.singlerr.handler.BlockHandler;
import io.github.singlerr.handler.ElementSet;
import io.github.singlerr.handler.VehicleObject;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class BlockSpawner extends BukkitRunnable {

    private BlockHandler blockHandler;
    private Queue<Block> queue;
    private VehicleObject vehicleObject;
    private Vehicle instance;

    private int x,y,z;

    public BlockSpawner(Vehicle instance,BlockHandler blockHandler,Location startLocation, VehicleObject object){

        this.queue = new ArrayDeque<>(object.getCuboid().getBlocks());
        this.blockHandler = blockHandler;

        this.x = startLocation.getBlockX() - object.getCuboid().getLowerX();
        this.y = startLocation.getBlockY() - object.getCuboid().getLowerY();
        this.z = startLocation.getBlockZ() - object.getCuboid().getLowerZ();

        this.vehicleObject = object;
        this.instance = instance;
    }

    @Override
    public void run() {
        while (! queue.isEmpty()){
            Block block = queue.poll();

            if(block.getType() != Material.AIR) {
                //   Location loc = block.getLocation().add(new Vector(x, y, z));

                Location loc = block.getLocation().add(new Vector(-100,-1,0));
                ElementSet set = blockHandler.spawnBlock(loc, block);
                vehicleObject.addPart(set);
            }

        }
        BlockController controller = new BlockController(vehicleObject,0.01,0);
        vehicleObject.setController(controller);
        controller.runTaskTimer(instance,1L,1L);
        cancel();

    }
}
