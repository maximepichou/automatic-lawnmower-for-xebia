package fr.maxime.lawnmower;

public class PositionCalculator implements MotionControlCalculator{

    private static PositionCalculator instance = null;

    private PositionCalculator(){}

    public static PositionCalculator getInstance(){
        if(instance == null){
            synchronized (PositionCalculator.class){
                if(instance == null){
                    instance = new PositionCalculator();
                }
            }
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
