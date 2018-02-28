package com.freitech.kotetsu.codes;

public enum View implements EnumStringBase{
    //@formatter:off
	DISP("disp"),
	INDEX("index"),
	INPUT("input"),
	CONFIRM("confirm"),
	COMPLETE("complete"),
	CREATE("create"),
	BACK("back"),
	LIST("list"),
	FORM_PARAM("form"),
	REG_PARAM("reg"),
	SUCCESS_MESSAGE("success");
	//@formatter:on
  
  public String code = "";

  private View(final String code) {
    this.code = code;
  }

  @Override
  public String getCode() {
    return this.code;
  }
}
