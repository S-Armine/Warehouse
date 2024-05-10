package org.Sandship.CustomExceptions;

//exception that should be thrown when max capacity of a material is exceeded
public class MaxCapacityExceededException extends WarehouseException{
    public MaxCapacityExceededException() {
    }

    public MaxCapacityExceededException(String message) {
        super(message);
    }
}
