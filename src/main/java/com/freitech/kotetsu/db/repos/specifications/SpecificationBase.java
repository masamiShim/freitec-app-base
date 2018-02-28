package com.freitech.kotetsu.db.repos.specifications;

public class SpecificationBase {

  /**
   * 部分一致
   * 
   * @param param
   * @return
   */
  protected String castPartLikeString(String param) {
    return "%".concat(param).concat("%");

  }

  /**
   * 前方一致
   * 
   * @param param
   * @return
   */
  protected String castHeadLikeString(String param) {
    return param.concat("%");
  }

  /**
   * 後方一致
   * 
   * @param param
   * @return
   */
  protected String castTailLikeString(String param) {
    return "%".concat(param);
  }
}
