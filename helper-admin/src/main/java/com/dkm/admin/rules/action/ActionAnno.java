package com.dkm.admin.rules.action;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionAnno {
	String id() default "";
	
	@AliasFor("value")
	String desc() default "";
	
	@AliasFor("desc")
	String value() default "";

	ActionParam[] params() default {};
}
