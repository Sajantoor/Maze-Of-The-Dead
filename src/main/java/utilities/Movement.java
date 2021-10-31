package main.java.utilities;

public enum Movement {
    FORWARD(0), BACKWARD(1), LEFT(2), RIGHT(3);
    private int value;

    Movement(int value) {
        this.value = value;
    }

}