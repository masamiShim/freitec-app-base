package com.example.demo.codes;

public enum ItemType {
  /** ホームページ */
  HOME_PAGE("01", "ホームページ"),
  /** デザイン */
  DESIGN("02", "デザイン "),
  /** 要件定義 */
  REQUIR_EDEF("11", "要件定義 "),
  /** 設計 */
  ARCHITECT("12", "設計"),
  /** コーディング */
  CODING("13", "コーディング"),
  /** テスト */
  TEST("14", "テスト"),
  /** ドキュメント */
  DOCUMENT("91", "ドキュメント"),
  /** その他 */
  OTHRE("99", "その他");

  public String code = "";
  public String name = "";

  private ItemType(final String code, final String name) {
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
