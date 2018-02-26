package fr.maxime.lawnmower;

import java.util.function.Consumer;

public class MotionController {
    private MotionControl motionControl;
    private MotionControlCalculator motionControlCalculator;

    private MotionController(){}

    public MotionController(MotionControl motionControl, MotionControlCalculator motionControlCalculator) {
        this.motionControl = motionControl;
        this.motionControlCalculator = motionControlCalculator;
    }

    public MotionControl getMotionControl() {
        return motionControl;
    }

    public MotionControlCalculator getMotionControlCalculator() {
        return motionControlCalculator;
    }

    public static final class Builder{
        private MotionController managedInstance = new MotionController();
        private ControllableMower.Builder parentBuilder;
        private Consumer<MotionController> callback;

        public Builder(){}

        public Builder(ControllableMower.Builder b, Consumer<MotionController> f) {
            this.parentBuilder = b;
            this.callback = f;
        }

        public MotionController.Builder withMotionControl(MotionControl motionControl){
            managedInstance.motionControl = motionControl;
            if(motionControl instanceof Movement){
                managedInstance.motionControlCalculator = PositionCalculator.getInstance();
            }
            else if(motionControl instanceof Rotation){
                managedInstance.motionControlCalculator = OrientationCalculator.getInstance();
            }
            else{
                throw new IllegalStateException("Unknown motion control");
            }
            return this;
        }

        public ControllableMower.Builder done(){
            callback.accept(managedInstance);
            return parentBuilder;
        }

        public MotionController build(){
            return managedInstance;
        }
    }
}
