package com.dkm.admin.operate.component.web.parameter.validation;

import com.dkm.admin.operate.component.web.parameter.validation.Enumerations.EnumerationsValidator;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.HashSet;
import java.util.Set;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EnumerationsValidator.class)
public @interface Enumerations {

	String[] values();

	boolean caseSensitive() default true;

	String message() default "{com.guohuai.validation.enumerations}";

	String desc() default "";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	public class EnumerationsValidator implements ConstraintValidator<Enumerations, Object> {

		private Set<String> elements = new HashSet<String>();
		private boolean caseSensitive = true;

		@Override
		public void initialize(Enumerations constraintAnnotation) {
			this.caseSensitive = constraintAnnotation.caseSensitive();
			for (String element : constraintAnnotation.values()) {
				if (!this.caseSensitive) {
					this.elements.add(element.toLowerCase());
				} else {
					this.elements.add(element);
				}
			}
		}

		@Override
		public boolean isValid(Object value, ConstraintValidatorContext context) {
			if (null == value) {
				return true;
			}
			if (this.caseSensitive) {
				return this.elements.contains(value.toString());
			} else {
				return this.elements.contains(value.toString().toLowerCase());
			}
		}

	}

}
