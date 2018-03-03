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

    /**
     * Orientate the mower based on the current orientation and the motion to perform.
     * @param currentOrientation the current orientation of the mower.
     * @param currentPosition the current position of the mower.
     * @param motionToPerform the motion control to perform on the mower. (Must be a rotation)
     * @return the position of the mower after the motion has been applied on it.
     */
    @Override
    public Maneuver perform(Orientation currentOrientation, Position currentPosition, MotionControl motionToPerform){
        Rotation rotationToPerform = (Rotation) motionToPerform;
        // Retrieve the new orientation of the mower based on the clock order of the enum Orientation
        int newOrientationClockOrder = (currentOrientation.getClockOrder() + rotationToPerform.getClockOrderModifier()) % (Orientation.values().length);
        // If the new clock order is negative, return the highest clock order (like a negative modulo)
        if(newOrientationClockOrder < 0){
            newOrientationClockOrder = Orientation.values().length - 1;
        }
        return new Maneuver(currentPosition, Orientation.findByClockOrder(newOrientationClockOrder));
    }
}
