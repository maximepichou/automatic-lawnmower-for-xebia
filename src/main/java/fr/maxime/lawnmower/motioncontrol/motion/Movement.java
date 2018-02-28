package fr.maxime.lawnmower.motioncontrol.motion;

import fr.maxime.lawnmower.motioncontrol.MotionControl;

public enum Movement implements MotionControl {

    A(1, 1);

    private int multiplierX;
    private int multiplierY;

    Movement(int multiplierX, int multiplierY) {
        this.multiplierX = multiplierX;
        this.multiplierY = multiplierY;
    }

    public int getMultiplierX() {
        return multiplierX;
    }

    public int getMultiplierY() {
        return multiplierY;
    }
}
