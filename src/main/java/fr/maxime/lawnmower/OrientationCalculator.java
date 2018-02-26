package fr.maxime.lawnmower;

public class OrientationCalculator implements MotionControlCalculator{

    private static OrientationCalculator instance = null;

    private OrientationCalculator(){}

    public static OrientationCalculator getInstance(){
        if(instance == null){
            synchronized (OrientationCalculator.class){
                if(instance == null){
                    instance = new OrientationCalculator();
                }
            }
        }
        return instance;
    }

    @Override
    public Maneuver perform(Orientation currentOrientation, Position currentPosition, MotionControl motionToPerform){
        Rotation rotationToPerform = (Rotation) motionToPerform;
        int newOrientationClockOrder = (currentOrientation.getClockOrder() + rotationToPerform.getClockOrderModifier()) % (Orientation.values().length);
        if(newOrientationClockOrder < 0){
            newOrientationClockOrder = Orientation.values().length - 1;
        }
        return new Maneuver(currentPosition, Orientation.findByClockOrder(newOrientationClockOrder));
    }
}
