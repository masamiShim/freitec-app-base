package com.freitech.kotetsu.config.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InputDomAttr {
	// 要素名
	String tag() default "";
	
	// 属性名
	String name() default "";

	// 名称
	String exclude();
}
