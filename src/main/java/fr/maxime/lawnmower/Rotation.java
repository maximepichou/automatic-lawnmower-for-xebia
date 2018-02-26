package fr.maxime.lawnmower;

public enum Rotation implements MotionControl{
    G(-1),
    D(1);

    private int clockOrderModifier;

    Rotation(int clockOrderModifier) {
        this.clockOrderModifier = clockOrderModifier;
    }

    public int getClockOrderModifier() {
        return clockOrderModifier;
    }
}
