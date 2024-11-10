package com.serenity.api.serenity.validations;

import com.serenity.api.serenity.exceptions.DuplicadoException;
import com.serenity.api.serenity.repositories.ObjectCustomRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueValidator implements ConstraintValidator<Unique, String> {
    String field;
    Class<?> object;

    private final ObjectCustomRepository objectCustomRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean isUnique = !objectCustomRepository.isDuplicated(object, field, value);

        if (!isUnique) {
            throw new DuplicadoException("Duplicado encontrado para " + field + " em " + object.getSimpleName());
        }

        return true;
    }

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.field = constraintAnnotation.field().toLowerCase();
        this.object = constraintAnnotation.object();

        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
