package fr.maxime.lawnmower.motioncontrol.motion;

import fr.maxime.lawnmower.ControllableMower;

import java.util.function.Consumer;

public class Position {
    private int x;
    private int y;

    private Position(){}

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public static final class Builder{
        private Position managedInstance = new Position();
        private ControllableMower.Builder parentBuilder;
        private Consumer<Position> callback;

        public Builder(){}

        public Builder(ControllableMower.Builder b, Consumer<Position> f) {
            this.parentBuilder = b;
            this.callback = f;
        }

        public Position.Builder x(int x){
            managedInstance.x = x;
            return this;
        }

        public Position.Builder y(int y){
            managedInstance.y = y;
            return this;
        }

        public ControllableMower.Builder done(){
            callback.accept(managedInstance);
            return parentBuilder;
        }

        public Position build(){
            return managedInstance;
        }
    }
}
