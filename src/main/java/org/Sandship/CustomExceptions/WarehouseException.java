package org.Sandship.CustomExceptions;

//exception that should be thrown if any failure was noticed
public class WarehouseException extends Exception {
    public WarehouseException() {
    }

    public WarehouseException(String message) {
        super(message);
    }
}
