package by.ttre16.briana.exception;

import by.ttre16.briana.entity.base.AbstractBaseEntity;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public static EntityNotFoundException with(
            Class<? extends AbstractBaseEntity> entity,
            Integer id) {
        return new EntityNotFoundException(
                String.format(
                        "%s with id '%d' not found.",
                        entity.getSimpleName(),
                        id
                )
        );
    }
}
