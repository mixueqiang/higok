package com.higok.crawler.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.higok.crawler.Crawler;
import com.higok.dao.CategoryDAO;
import com.higok.dao.ItemDAO;

/**
 * @author xueqiang.mi
 * @since 2012-7-27
 */
public abstract class BaseCrawler implements Crawler {

  @Autowired
  protected CategoryDAO categoryDAO;
  @Autowired
  protected ItemDAO itemDAO;

  public abstract List<String> getCategories();

  public abstract List<String> getItems();

  @Override
  public void getAndSaveCategories() {
    List<String> brands = getCategories();
    if (brands == null) {
      return;
    }

    for (String brand : brands) {
      categoryDAO.addIfNotExist(getSource(), brand);
    }
  }

  @Override
  public void getAndSaveItems() {
    List<String> items = getItems();
    if (items == null || items.isEmpty()) {
      return;
    }

    for (String item : items) {
      itemDAO.addIfNotExist(getSource(), item);
    }
  }

}
