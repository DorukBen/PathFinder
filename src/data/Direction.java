package data;

public enum Direction {
    NONE(0),
    LEFT(1),
    UP(2),
    RIGHT(3),
    DOWN(4);

    private final int mValue;

    Direction(final int value) {
        mValue = value;
    }

    public int getValue() {
        return mValue;
    }

    public static Direction randomDirection() {
        return values()[((int)(Math.random() * (values().length - 1))) + 1];
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }
}
