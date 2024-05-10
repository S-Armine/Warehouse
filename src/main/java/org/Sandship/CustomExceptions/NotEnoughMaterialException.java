package org.Sandship.CustomExceptions;

//exception that should be thrown when operation can end in lack of certain material in warehouse
public class NotEnoughMaterialException extends WarehouseException {
    public NotEnoughMaterialException() {
    }

    public NotEnoughMaterialException(String message) {
        super(message);
    }
}
