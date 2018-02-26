package fr.maxime.lawnmower;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Lawn {
    private int maxX;
    private int maxY;
    private List<ControllableMower> controllableMowers;

    private Lawn() {
        controllableMowers = new ArrayList<>();
    }

    public Lawn(int maxX, int maxY, List<ControllableMower> controllableMowers) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.controllableMowers = controllableMowers;
    }

    public static Lawn.Builder builder(){
        return new Lawn.Builder();
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public List<ControllableMower> getControllableMowers() {
        return controllableMowers;
    }

    public boolean isValidManeuver(Maneuver maneuver){
        return maneuver.getPosition().getX() >= 0 && maneuver.getPosition().getX() <= maxX
                && maneuver.getPosition().getY() >= 0 && maneuver.getPosition().getY() <= maxY;
    }

    public List<Maneuver> performFinalPositionOfControllableMower(){
        List<Maneuver> maneuverList = new ArrayList<>();
        for(ControllableMower controllableMower1 : controllableMowers){
            Maneuver finalManeuver = controllableMower1.performMotionControls(this);
            maneuverList.add(finalManeuver);
        }
        return maneuverList;
    }

    public static final class Builder{
        private Lawn managedInstance = new Lawn();

        public Lawn.Builder withMaxX(int maxX){
            managedInstance.maxX = maxX;
            return this;
        }

        public Lawn.Builder withMaxY(int maxY){
            managedInstance.maxY = maxY;
            return this;
        }

        public ControllableMower.Builder withControllableMower(){
            Consumer<ControllableMower> f = obj -> {managedInstance.controllableMowers.add(obj); };
            return new ControllableMower.Builder(this, f);
        }

        public Lawn build(){
            return managedInstance;
        }
    }
}
