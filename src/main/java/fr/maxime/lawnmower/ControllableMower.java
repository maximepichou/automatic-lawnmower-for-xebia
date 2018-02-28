package fr.maxime.lawnmower;

import fr.maxime.lawnmower.motioncontrol.Maneuver;
import fr.maxime.lawnmower.motioncontrol.MotionController;
import fr.maxime.lawnmower.motioncontrol.motion.Orientation;
import fr.maxime.lawnmower.motioncontrol.motion.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ControllableMower {
    private Position initialPosition;
    private Orientation initialOrientation;
    private List<MotionController> motionControls;

    private ControllableMower(){
        motionControls = new ArrayList<>();
    }

    public static ControllableMower.Builder builder(){
        return new ControllableMower.Builder();
    }

    public Maneuver performMotionControls(Lawn lawn){
        Orientation currentOrientation = initialOrientation;
        Position currentPosition = initialPosition;
        for(MotionController motionController : motionControls){
            Maneuver maneuverToUpdate = motionController.getMotionControlCalculator().perform(currentOrientation, currentPosition, motionController.getMotionControl());
            if(lawn.isValidManeuver(maneuverToUpdate)){
                currentOrientation = maneuverToUpdate.getOrientation();
                currentPosition = maneuverToUpdate.getPosition();
            }
        }
        return new Maneuver(currentPosition, currentOrientation);
    }

    public Position getInitialPosition() {
        return initialPosition;
    }

    public Orientation getInitialOrientation() {
        return initialOrientation;
    }

    public List<MotionController> getMotionControls() {
        return motionControls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ControllableMower that = (ControllableMower) o;

        if (initialPosition != null ? !initialPosition.equals(that.initialPosition) : that.initialPosition != null)
            return false;
        if (initialOrientation != that.initialOrientation) return false;
        return motionControls != null ? motionControls.equals(that.motionControls) : that.motionControls == null;
    }

    @Override
    public int hashCode() {
        int result = initialPosition != null ? initialPosition.hashCode() : 0;
        result = 31 * result + (initialOrientation != null ? initialOrientation.hashCode() : 0);
        result = 31 * result + (motionControls != null ? motionControls.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ControllableMower{" +
                "initialPosition=" + initialPosition +
                ", initialOrientation=" + initialOrientation +
                ", motionControls=" + motionControls +
                '}';
    }


    public static final class Builder{
        private ControllableMower managedInstance = new ControllableMower();
        private Lawn.Builder parentBuilder;
        private Consumer<ControllableMower> callback;

        public Builder(){}

        public Builder(Lawn.Builder b, Consumer<ControllableMower> f){
            this.parentBuilder = b;
            this.callback = f;
        }

        public Position.Builder withInitialPosition(){
            Consumer<Position> f = obj -> managedInstance.initialPosition = obj;
            return new Position.Builder(this, f);
        }

        public ControllableMower.Builder withInitialOrientation(Orientation orientation){
            managedInstance.initialOrientation = orientation;
            return this;
        }

        public MotionController.Builder withMotionController(){
            Consumer<MotionController> f = obj -> managedInstance.motionControls.add(obj);
            return new MotionController.Builder(this, f);
        }

        public ControllableMower.Builder withMotionControllerList(List<MotionController> motionControllerList){
            managedInstance.motionControls = motionControllerList;
            return this;
        }

        public Lawn.Builder done(){
            callback.accept(managedInstance);
            return parentBuilder;
        }

        public ControllableMower build(){
            return managedInstance;
        }
    }
}
