package mas.model;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {SubmitValidator.class})
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface SubsetConstraint {
    String message() default "Invalid book value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
