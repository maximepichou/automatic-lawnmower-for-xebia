package fr.maxime.lawnmower.service;

import fr.maxime.lawnmower.ControllableMower;
import fr.maxime.lawnmower.Lawn;
import fr.maxime.lawnmower.motioncontrol.Maneuver;

import java.util.ArrayList;
import java.util.List;

public class LawnService {
    private Lawn lawn;

    public LawnService(Lawn lawn) {
        this.lawn = lawn;
    }

    /**
     * Move all mowers on the current lawn and return the final position of all mowers on that lawn.
     * @return the final position of all mowers with their orientation.
     */
    public List<Maneuver> moveMowersAndGetFinalPosition(){
        List<Maneuver> maneuverList = new ArrayList<>();
        for(ControllableMower controllableMower : lawn.getControllableMowers()){
            Maneuver finalManeuver = ControllableMowerService.performMotionControls(controllableMower, lawn);
            maneuverList.add(finalManeuver);
        }
        return maneuverList;
    }
}
