package pl.dmcs.brozga.validator;

import org.springframework.util.StringUtils;
import org.springframework.validation.Validator;
import pl.dmcs.brozga.model.AppUser;
import org.springframework.validation.Errors;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.validation.ValidationUtils;


public class AppUserValidator implements Validator {

    EmailValidator emailValidator = EmailValidator.getInstance();


    @Override
    public boolean supports(Class clazz) {return AppUser.class.isAssignableFrom(clazz);}

    @Override
    public void validate(Object arg0, Errors errors) {
        AppUser appUser = (AppUser) arg0;
        //  ValidationUtils.rejectIfEmpty(errors, "name", "error.field.required");
        //  ValidationUtils.rejectIfEmpty(errors, "surname", "error.field.required");
        //  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "error.field.required");
        //  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.field.required");

       /*Valid numbers "2055550125","202 555 0125", "(202) 555-0125", "+111 (202) 555-0125",
      "636 856 789", "+111 636 856 789", "636 85 67 89", "+111 636 85 67 89"   */

            if(!appUser.getPhoneNumber().matches("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$\" \n" +
                    "                + \"|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$\"\n" +
                    "                + \"|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$")){
                errors.rejectValue("phoneNumber","error.phoneNumber.invalid");

        }

            if (!StringUtils.hasText(((AppUser)arg0).getEmail()) && emailValidator.isValid(((AppUser)arg0).getEmail()) == false) {
                errors.rejectValue("email", "error.email.invalid");

        }
    }


}