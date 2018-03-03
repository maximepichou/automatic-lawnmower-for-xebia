package fr.maxime.lawnmower;

import fr.maxime.lawnmower.motioncontrol.Maneuver;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lawn lawn = (Lawn) o;

        if (maxX != lawn.maxX) return false;
        if (maxY != lawn.maxY) return false;
        return controllableMowers != null ? controllableMowers.equals(lawn.controllableMowers) : lawn.controllableMowers == null;
    }

    @Override
    public int hashCode() {
        int result = maxX;
        result = 31 * result + maxY;
        result = 31 * result + (controllableMowers != null ? controllableMowers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Lawn{" +
                "maxX=" + maxX +
                ", maxY=" + maxY +
                ", controllableMowers=" + controllableMowers +
                '}';
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
            Consumer<ControllableMower> f = obj -> managedInstance.controllableMowers.add(obj);
            return new ControllableMower.Builder(this, f);
        }

        public Lawn.Builder withControllableMowerList(List<ControllableMower> controllableMowerList){
            managedInstance.controllableMowers = controllableMowerList;
            return this;
        }

        public Lawn build(){
            if(managedInstance.maxX > 0 && managedInstance.maxY > 0 ) {
                return managedInstance;
            }
            else{
                throw new IllegalArgumentException("Lawn must have positive coordinates");
            }
        }
    }
}
