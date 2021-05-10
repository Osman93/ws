package com.hoaxify.ws.user;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername,String> {

    @Autowired
    UserRepository userRepository;


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s != null){
            User user = userRepository.findByUsername(s);
            if(user != null){
                return false;
            }
        }

        return true;
    }
}
