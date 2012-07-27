package com.higok.crawler.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.higok.crawler.Crawler;
import com.higok.dao.LinkDAO;

/**
 * @author xueqiang.mi
 * @since 2012-7-27
 */
public abstract class BaseCrawler implements Crawler {

  @Autowired
  protected LinkDAO linkDAO;

  public abstract List<String> getLinks();

  public void getAndSaveLinks() {
    List<String> links = getLinks();
    if (links == null) {
      return;
    }

    for (String link : links) {
      linkDAO.insert(link, getSource());
    }
  }

}
