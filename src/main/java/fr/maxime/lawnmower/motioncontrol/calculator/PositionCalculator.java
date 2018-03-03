package fr.maxime.lawnmower.motioncontrol.calculator;

import fr.maxime.lawnmower.motioncontrol.*;
import fr.maxime.lawnmower.motioncontrol.motion.Movement;
import fr.maxime.lawnmower.motioncontrol.motion.Orientation;
import fr.maxime.lawnmower.motioncontrol.motion.Position;

public class PositionCalculator implements MotionControlCalculator {

    private static PositionCalculator instance = null;

    private PositionCalculator(){}

    public static synchronized PositionCalculator getInstance(){
        if(instance == null){
            instance = new PositionCalculator();
        }
        return instance;
    }

    /**
     * Move the mower based on the current position and the motion to perform.
     * @param currentOrientation the current orientation of the mower.
     * @param currentPosition the current position of the mower.
     * @param motionToPerform the motion control to perform on the mower. (Must be a movement)
     * @return the position of the mower after the motion has been applied on it.
     */
    @Override
    public Maneuver perform(Orientation currentOrientation, Position currentPosition, MotionControl motionToPerform){
        Movement movementToPerform = (Movement) motionToPerform;
        int newX = currentPosition.getX() + (currentOrientation.getShiftX() * movementToPerform.getMultiplierX());
        int newY = currentPosition.getY() + (currentOrientation.getShiftY() * movementToPerform.getMultiplierY());

        return new Maneuver(new Position(newX, newY), currentOrientation);
    }


}
