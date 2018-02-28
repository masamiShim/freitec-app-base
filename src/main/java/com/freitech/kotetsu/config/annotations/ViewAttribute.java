package com.freitech.kotetsu.config.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewAttribute {

	// カラムのサイズ
	String colSize() default "col-md-12";

	// 名称
	String name() default "";

	// 順番
	int ordinal() default 0;

	// 順番
	boolean visible() default true;

	boolean readonly() default false;

	boolean disabled() default false;

	// プレースホルダー
	String placeholder() default "";

}
