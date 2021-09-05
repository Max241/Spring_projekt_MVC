package pl.dmcs.brozga.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.dmcs.brozga.model.VisitHoursDTO;


import java.time.LocalDateTime;

public class AddVisitHoursValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return VisitHoursDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "startDate", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "visitLength", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "visitCost", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "doctorId", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "description", "error.field.required");

        if (errors.getErrorCount() == 0) {
            if (((VisitHoursDTO) o).getStartDate().isBefore(LocalDateTime.now())) {
                errors.rejectValue("startDate", "visitHours.error.startDate");
            }


            if (((VisitHoursDTO) o).getVisitCost() < 0) {
                errors.rejectValue("visitCost", "visitHours.error.visitCost");
            }

            if (((VisitHoursDTO) o).getVisitLength() != 15 &&
                    ((VisitHoursDTO) o).getVisitLength() != 30) {
                errors.rejectValue("visitLength", "visitHours.error.visitLength");
            }
        }
    }
}

