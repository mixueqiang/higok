package com.higok.crawler;

/**
 * @author xueqiang.mi
 * @since 2012-7-27
 */
public interface Crawler {

  void getAndSaveCategories();

  void getAndSaveItems();

  String getSource();

}
