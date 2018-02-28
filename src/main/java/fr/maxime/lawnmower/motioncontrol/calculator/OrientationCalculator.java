package fr.maxime.lawnmower.motioncontrol.calculator;

import fr.maxime.lawnmower.motioncontrol.*;
import fr.maxime.lawnmower.motioncontrol.motion.Orientation;
import fr.maxime.lawnmower.motioncontrol.motion.Position;
import fr.maxime.lawnmower.motioncontrol.motion.Rotation;

public class OrientationCalculator implements MotionControlCalculator {

    private static OrientationCalculator instance = null;

    private OrientationCalculator(){}

    public static synchronized OrientationCalculator getInstance(){
        if(instance == null){
            instance = new OrientationCalculator();
        }
        return instance;
    }

    @Override
    public Maneuver perform(Orientation currentOrientation, Position currentPosition, MotionControl motionToPerform){
        Rotation rotationToPerform = (Rotation) motionToPerform;
        int newOrientationClockOrder = (currentOrientation.getClockOrder() + rotationToPerform.getClockOrderModifier()) % (Orientation.values().length);
        if(newOrientationClockOrder < 0){
            newOrientationClockOrder = Orientation.values().length - 1;
        }
        return new Maneuver(currentPosition, Orientation.findByClockOrder(newOrientationClockOrder));
    }
}
