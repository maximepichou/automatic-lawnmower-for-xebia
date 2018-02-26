package fr.maxime.lawnmower;

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

    public ControllableMower(Position initialPosition, Orientation initialOrientation, List<MotionController> motionControls) {
        this.initialPosition = initialPosition;
        this.initialOrientation = initialOrientation;
        this.motionControls = motionControls;
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
            else{
                System.out.println("can't go into x = " + maneuverToUpdate.getPosition().getX() + " and y = " + maneuverToUpdate.getPosition().getY());
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

        public Lawn.Builder done(){
            callback.accept(managedInstance);
            return parentBuilder;
        }

        public ControllableMower build(){
            return managedInstance;
        }
    }
}
