package fr.maxime.lawnmower;

public class Maneuver {
    private Position position;
    private Orientation orientation;

    public Maneuver(Position position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    public Position getPosition() {
        return position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Maneuver maneuver = (Maneuver) o;

        if (position != null ? !position.equals(maneuver.position) : maneuver.position != null) return false;
        return orientation == maneuver.orientation;
    }

    @Override
    public int hashCode() {
        int result = position != null ? position.hashCode() : 0;
        result = 31 * result + (orientation != null ? orientation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Maneuver{" +
                "position=" + position +
                ", orientation=" + orientation +
                '}';
    }
}
