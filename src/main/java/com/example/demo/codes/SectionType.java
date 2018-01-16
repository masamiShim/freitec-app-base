package com.example.demo.codes;

public enum SectionType {
  /** ホームページ */
  KIKAKU("010", "企画", 1),
  /** デザイン */
  REQUIREMENT("100", "要件定義",1),
  /** 要件定義 */
  RESEARCH("101", "調査",2),
  /** 設計 */
  ARCHITECT("102", "設計", 2),
  /** 実装 */
  DEVELOPMENT("103", "開発",3),
  /** テスト */
  TEST("104", "テスト",3),
  /** ドキュメント */
  DOCUMENT("901", "ドキュメント作成",4),
  /** その他 */
  OTHRE("999", "その他",999);

  public String code = "";
  public String name = "";
  public int ordinaly = 0;
  
  private SectionType(final String code, final String name, final int ordinaly) {
    this.code = code;
    this.name = name;
    this.ordinaly = ordinaly;
  }

  public String getCode() {
    return this.code;
  }
  public String getName() {
    return this.name;
  }
  public int getOrdinaly() {
    return this.ordinaly;
  }

  
}
