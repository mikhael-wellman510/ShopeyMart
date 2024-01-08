package com.enigma.shopeymart.Util;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
// Todo -> Untuk Validasi seperti username tidak boleh kosong , Pasword harus terisi
public class ValidationUtil {
    private final Validator validator;

    public void validate(Object object){
        Set<ConstraintViolation<Object>> result = validator.validate(object);

        if (!result.isEmpty()){
            throw new ConstraintViolationException(result);
        }
    }

}
