package mas.model;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SubmitValidator implements ConstraintValidator<SubsetConstraint, Collection> {
    @Override
    public void initialize(SubsetConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(Collection collection, ConstraintValidatorContext constraintValidatorContext) {
        return collection.getCover() == null || collection.getBooks().contains(collection.getCover());
    }
}
