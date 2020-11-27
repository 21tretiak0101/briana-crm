package by.ttre16.briana.controller;

import by.ttre16.briana.exception.IllegalRequestDataException;
import by.ttre16.briana.transport.base.BaseTo;

import static java.lang.String.format;

public class ApiValidation {
    public static void assureThatIdConsistent(
            BaseTo entity,
            Integer id) {
        if (!entity.getId().equals(id)) {
            throw new IllegalRequestDataException(
                    format("%s must be with id: '%d'.",
                            entity.getClass().getSimpleName(),
                            id
                    )
            );
        }
    }
}
