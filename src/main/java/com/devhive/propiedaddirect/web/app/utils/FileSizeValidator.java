package com.devhive.propiedaddirect.web.app.utils;


import com.devhive.propiedaddirect.web.app.models.beans.AgentBean;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;
// FileSizeValidator.java
public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

    private long minSize;
    private long maxSize;

    @Override
    public void initialize(FileSize fileSize) {
        this.minSize = fileSize.min();
        this.maxSize = fileSize.max();
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        // Permitir archivos nulos (manejar esto en el controlador)
        if (multipartFile == null || multipartFile.isEmpty()) {
            return true;
        }
        long size = multipartFile.getSize();
        return size >= minSize && size <= maxSize;
    }
}
