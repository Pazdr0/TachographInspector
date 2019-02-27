package pl.bgolc.tachograph.user.annotation.validation;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidator;
import org.springframework.beans.BeanWrapperImpl;
import pl.bgolc.tachograph.user.annotation.FieldsMatch;

import javax.validation.ConstraintValidatorContext;

public class FieldsMatchValidator implements HibernateConstraintValidator<FieldsMatch, Object> {

    private String field;
    private String fieldMatch;

    public void initialize(FieldsMatch constraitAnnotation) {
        this.field = constraitAnnotation.field();
        this.fieldMatch = constraitAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);

        if (fieldValue != null) {
            return fieldValue.equals(fieldMatchValue);
        } else {
            return fieldMatchValue == null;
        }

    }
}
