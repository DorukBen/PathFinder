package data;

public class Gene {
    private Direction mDirection;

    public Gene(Direction mDirection) {
        this.mDirection = mDirection;
    }

    public Direction getDirection() {
        return mDirection;
    }

    public void setDirection(Direction direction) {
        this.mDirection = direction;
    }

    @Override
    public Gene clone(){
        return new Gene(this.mDirection);
    }

    @Override
    public String toString() {
        return mDirection.toString();
    }
}
