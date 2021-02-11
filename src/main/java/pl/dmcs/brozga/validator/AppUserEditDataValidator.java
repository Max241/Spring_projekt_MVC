package pl.dmcs.brozga.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.dmcs.brozga.model.AppUser;
import pl.dmcs.brozga.model.AppUserEditData;


public class AppUserEditDataValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return AppUserEditData.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object arg0, Errors errors) {
        AppUserEditData appUserEditData = (AppUserEditData) arg0;
        ValidationUtils.rejectIfEmpty(errors, "name", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "surname", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "phoneNumber", "error.field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pesel", "error.field.required");


/*       Valid numbers "2055550125","202 555 0125", "(202) 555-0125", "+111 (202) 555-0125",
      "636 856 789", "+111 636 856 789", "636 85 67 89", "+111 636 85 67 89"   */

        if (!appUserEditData.getPhoneNumber().matches("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$\" \n" +
                "                + \"|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$\"\n" +
                "                + \"|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$")) {
            errors.rejectValue("phoneNumber", "error.phoneNumber.invalid");

        }

        if (((AppUserEditData) arg0).getPesel().toString().length() != 11) {
            errors.rejectValue("pesel", "register.error.pesel");
        }
    }
}

