package fr.maxime.lawnmower;

public interface MotionControlCalculator {

    Maneuver perform(Orientation currentOrientation, Position currentPosition, MotionControl motionToPerform);
}
