package com.sriram.ecommerce.user.validator;

import com.sriram.ecommerce.user.domain.UserAddressDomain;
import com.sriram.ecommerce.user.valid.UserAddressValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserAddressValidator implements ConstraintValidator<UserAddressValid, UserAddressDomain> {
    @Override
    public boolean isValid(UserAddressDomain userAddressDomain, ConstraintValidatorContext constraintValidatorContext) {
        boolean isAddressesReqValid = true;
        constraintValidatorContext.disableDefaultConstraintViolation();

        String addressLine1 = userAddressDomain.getAddressLine1();
        if (addressLine1 == null || addressLine1.isEmpty()) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("addressLine1 is required")
                    .addPropertyNode("addressLine1")
                    .addConstraintViolation();
            isAddressesReqValid = false;
        }
        if (userAddressDomain.getState()==null || userAddressDomain.getState().isEmpty()) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("state is required")
                    .addPropertyNode("state")
                    .addConstraintViolation();
            isAddressesReqValid = false;
        }
        if (userAddressDomain.getCity()==null || userAddressDomain.getCity().isEmpty()) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("city is required")
                    .addPropertyNode("city")
                    .addConstraintViolation();
            isAddressesReqValid = false;
        }
        if (userAddressDomain.getCountry()==null || userAddressDomain.getCountry().isEmpty()) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("country is required")
                    .addPropertyNode("country")
                    .addConstraintViolation();
            isAddressesReqValid = false;
        }
        if (userAddressDomain.getPincode()==null || userAddressDomain.getPincode().isEmpty()) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("country is required")
                    .addPropertyNode("pincode")
                    .addConstraintViolation();
            isAddressesReqValid = false;
        }

        return isAddressesReqValid;
    }
}
