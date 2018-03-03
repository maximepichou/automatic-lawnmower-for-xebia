package fr.maxime.lawnmower.service;

import fr.maxime.lawnmower.ControllableMower;
import fr.maxime.lawnmower.Lawn;
import fr.maxime.lawnmower.motioncontrol.Maneuver;
import fr.maxime.lawnmower.motioncontrol.MotionController;
import fr.maxime.lawnmower.motioncontrol.motion.Orientation;
import fr.maxime.lawnmower.motioncontrol.motion.Position;

public class ControllableMowerService {

    private ControllableMowerService() {
    }

    /**
     * Move a mower on a lawn with a predefined motion control list.
     * @param controllableMower the mower to move.
     * @param lawn the lawn where the mower is.
     * @return the final position of the mower after all motion has been applied.
     */
    protected static Maneuver performMotionControls(ControllableMower controllableMower, Lawn lawn){
        Orientation currentOrientation = controllableMower.getInitialOrientation();
        Position currentPosition = controllableMower.getInitialPosition();
        for(MotionController motionController : controllableMower.getMotionControls()){
            Maneuver maneuverToUpdate = motionController.getMotionControlCalculator().perform(currentOrientation, currentPosition, motionController.getMotionControl());
            if(lawn.isValidManeuver(maneuverToUpdate)){
                currentOrientation = maneuverToUpdate.getOrientation();
                currentPosition = maneuverToUpdate.getPosition();
            }
        }
        return new Maneuver(currentPosition, currentOrientation);
    }

}
