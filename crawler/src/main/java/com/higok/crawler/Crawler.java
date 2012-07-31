package com.higok.crawler;

/**
 * @author xueqiang.mi
 * @since 2012-7-27
 */
public interface Crawler {

  void retrieveCategories();

  /**
   * 抓取商品信息并保存到数据库。
   */
  void retrieveItems();

  /**
   * 该爬虫负责爬取的来源
   * 
   * @return 网站名称、简称等。
   */
  String getSource();

}
