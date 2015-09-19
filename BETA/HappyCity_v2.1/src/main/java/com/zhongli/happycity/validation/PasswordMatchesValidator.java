package com.zhongli.happycity.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.zhongli.happycity.persistence.service.UserDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final UserDto user = (UserDto) obj;
        System.out.println("密码比较："+user.getPassword()+"<>"+user.getMatchingPassword());
        return user.getPassword().equals(user.getMatchingPassword());
    }

}
