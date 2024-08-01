package com.devhive.propiedaddirect.web.app.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FileSizeValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface FileSize {

    String message() default "El tamaño del archivo no es válido.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    long min() default 0; // tamaño mínimo en bytes
    long max() default Long.MAX_VALUE; // tamaño máximo en bytes

    @Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        FileSize[] value();
    }
}
