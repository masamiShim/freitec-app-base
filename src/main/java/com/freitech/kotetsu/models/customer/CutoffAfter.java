package com.freitech.kotetsu.models.customer;

public enum CutoffAfter {
  /** 翌月　*/
	AFTER_MONTH("1", "翌月"),
  /** 翌々月　*/
  AFTER_TWO_MONTH("2", "翌々月"),
  /** 翌々々月　*/
  AFTER_THREE_MONTH("3", "翌々々月");

  public String code = "";
  public String name = "";

  private CutoffAfter(final String code, final String name) {
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return this.code;
  }

  public String getName() {
    return this.name;
  }

}
