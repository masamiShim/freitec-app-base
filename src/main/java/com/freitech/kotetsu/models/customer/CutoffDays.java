package com.freitech.kotetsu.models.customer;

public enum CutoffDays {
  /** 5日　*/
  FIFTH("5", "5日締め"),
  /** 10日　*/
  TENTH("10", "10日締め"),
  /** 15日　*/
  FIFTEENTH("15", "15日締め"),
  /** 20日　*/
  TWENTYTH("20", "20日締め"),
  /** 25日　*/
  TWENTY_FIFTH("25", "25日締め"),
  /** その他 */
  OTHRE("31", "末締");

  public String code = "";
  public String name = "";

  private CutoffDays(final String code, final String name) {
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
