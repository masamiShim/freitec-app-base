package com.freitech.kotetsu.config.tymeleaf;

import lombok.Data;

@Data
public class ViewModel {
	// class="required"
	private boolean required;
	// disabled="true"
	private boolean disabled;
	// readonly="true"
	private boolean readonly;
	// visble="true"
	private boolean visible;
	// value=??? どうしよ
	private Object value;
	// placeholder="small"
	private String placeholder;
	// class="" TODO: もうちょい細かくした方が良いかも
	private String cssClass;
	// ラベルテキスト<label></lable>
	private String label;
	// max=""
	private Integer max;
	// min=""
	private Integer min;
	// maxlength=""
	private Integer maxLength;
	// minlength=""
	private Integer minLength;
	private Integer ordinal;
	// type="text"
	private String type;
	// id="name", name="name"
	private String name;
	// <div class="col-md-4">
	private int colSize = 12;
	
	private String helpText;
}
