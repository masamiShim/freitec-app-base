package com.freitech.kotetsu.codes;

public enum Unit  implements EnumStringBase {
  /** 個 */
  KO("01","個"),
  /** 一式 */
  ISSIKI("02","一式"),
  /** 人/月 */
  NIN_GETSU("11","人/月"),
  /** 設計 */
  NIN_JIKAN("12","人/時"),
  /** 回 */
  KAI("13","回");

  public String code = "";
  public String codeName = "";

  private Unit(final String code, final String codeName) {
    this.code = code;
    this.codeName = codeName;
  }

  @Override
  public String getCode() {
    return this.code;
  }


}
