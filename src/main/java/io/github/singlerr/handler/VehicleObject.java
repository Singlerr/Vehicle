package io.github.singlerr.handler;

import io.github.singlerr.Cuboid;
import io.github.singlerr.threadsafe.BlockController;

import java.util.ArrayList;
import java.util.List;

public class VehicleObject {

    private List<ElementSet> parts;
    private Cuboid cuboid;
    private BlockController controller;

    public VehicleObject(List<ElementSet> parts,Cuboid cuboid){
        this.parts = parts;
        this.cuboid = cuboid;
    }

    public void setController(BlockController controller){
        this.controller = controller;
    }

    public BlockController getController() {
        return controller;
    }

    public VehicleObject(Cuboid cuboid){
        this.parts = new ArrayList<>();
        this.cuboid = cuboid;
    }

    public Cuboid getCuboid(){
        return cuboid;
    }

    public void addPart(ElementSet set){
        this.parts.add(set);
    }

    public List<ElementSet> getVehicleParts(){
        return parts;
    }


}
