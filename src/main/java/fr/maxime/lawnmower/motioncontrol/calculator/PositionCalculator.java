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

    @Override
    public Maneuver perform(Orientation currentOrientation, Position currentPosition, MotionControl motionToPerform){
        Movement movementToPerform = (Movement) motionToPerform;
        int newX = currentPosition.getX() + currentOrientation.getShiftX() * movementToPerform.getMultiplierX();
        int newY = currentPosition.getY() + currentOrientation.getShiftY() * movementToPerform.getMultiplierY();

        return new Maneuver(new Position(newX, newY), currentOrientation);
    }


}
