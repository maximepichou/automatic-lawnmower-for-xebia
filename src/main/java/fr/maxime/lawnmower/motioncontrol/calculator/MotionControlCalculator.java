package fr.maxime.lawnmower.motioncontrol.calculator;

import fr.maxime.lawnmower.motioncontrol.Maneuver;
import fr.maxime.lawnmower.motioncontrol.MotionControl;
import fr.maxime.lawnmower.motioncontrol.motion.Orientation;
import fr.maxime.lawnmower.motioncontrol.motion.Position;

public interface MotionControlCalculator {

    Maneuver perform(Orientation currentOrientation, Position currentPosition, MotionControl motionToPerform);
}
