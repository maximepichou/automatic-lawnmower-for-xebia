package fr.maxime.lawnmower.motioncontrol;

import fr.maxime.lawnmower.*;
import fr.maxime.lawnmower.motioncontrol.calculator.MotionControlCalculator;
import fr.maxime.lawnmower.motioncontrol.calculator.OrientationCalculator;
import fr.maxime.lawnmower.motioncontrol.calculator.PositionCalculator;
import fr.maxime.lawnmower.motioncontrol.motion.Movement;
import fr.maxime.lawnmower.motioncontrol.motion.Rotation;

import java.util.function.Consumer;

public class MotionController {
    private MotionControl motionControl;
    private MotionControlCalculator motionControlCalculator;

    private MotionController(){}

    public static MotionController.Builder builder(){
        return new MotionController.Builder();
    }

    public MotionControl getMotionControl() {
        return motionControl;
    }

    public MotionControlCalculator getMotionControlCalculator() {
        return motionControlCalculator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MotionController that = (MotionController) o;

        if (motionControl != null ? !motionControl.equals(that.motionControl) : that.motionControl != null)
            return false;
        return motionControlCalculator != null ? motionControlCalculator.equals(that.motionControlCalculator) : that.motionControlCalculator == null;
    }

    @Override
    public int hashCode() {
        int result = motionControl != null ? motionControl.hashCode() : 0;
        result = 31 * result + (motionControlCalculator != null ? motionControlCalculator.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MotionController{" +
                "motionControl=" + motionControl +
                ", motionControlCalculator=" + motionControlCalculator +
                '}';
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
