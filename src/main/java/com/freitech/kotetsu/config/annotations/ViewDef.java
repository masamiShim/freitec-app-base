package com.freitech.kotetsu.config.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = { ElementType.CONSTRUCTOR, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewDef {

	// カラム構成
	ColSize layout() default ColSize.ONE;

	// 名称
	String title() default "";

}
