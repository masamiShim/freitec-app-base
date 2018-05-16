package com.freitech.kotetsu.atum.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatorSetting {

	/** 編集可能か */
	boolean editable() default false;
	
	/** 検索条件に含めるか */
	boolean searchCondition() default false;
	
	/** 明細部に表示するか */
	boolean displayDetail() default false;

	/** inputType */
	String inputType() default "text";

	/** プレースホルダー */
	String placeholder() default "";

}
