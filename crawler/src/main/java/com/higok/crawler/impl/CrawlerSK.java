package com.higok.crawler.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.higok.crawler.Crawler;

/**
 * @author xueqiang.mi
 * @since 2012-7-27
 */
public class CrawlerSK extends BaseCrawler implements Crawler {

  @Override
  public String getURL() {
    return "http://www.skdutyfree.com/dutyfree/brand/brandMain";
  }

  @Override
  public String getSource() {
    return "SKDutyFree";
  }

  @Override
  public List<String> getLinks() {
    try {
      Document doc = Jsoup.connect("http://www.skdutyfree.com/dutyfree/brand/brandMain").get();
      Elements elements = doc.select(".brd_slist a");
      List<String> links = new ArrayList<String>();
      for (Element e : elements) {

      }
    } catch (Exception e) {
    }
    return null;
  }

}
