package fr.maxime.lawnmower;

import java.util.Arrays;

public enum Orientation {
    N(0, 0, 1),
    E(1, 1, 0),
    W(3, -1, 0),
    S(2, 0, -1);

    private int clockOrder;
    private int shiftX;
    private int shiftY;

    Orientation(int clockOrder, int shiftX, int shiftY) {
        this.clockOrder = clockOrder;
        this.shiftX = shiftX;
        this.shiftY = shiftY;
    }

    public static Orientation findByClockOrder(int clockOrder){
        return Arrays.stream(Orientation.values()).filter(x -> x.clockOrder == clockOrder).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public int getClockOrder() {
        return clockOrder;
    }

    public int getShiftX() {
        return shiftX;
    }

    public int getShiftY() {
        return shiftY;
    }

}
