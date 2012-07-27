package com.higok.crawler.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.higok.crawler.Crawler;

/**
 * @author xueqiang.mi
 * @since 2012-7-27
 */
@Component("crawlerSK")
public class CrawlerSK extends BaseCrawler implements Crawler {
  private static final String LINK_PREFIX = "http://www.skdutyfree.com/dutyfree/brand/brandDetail/detail.do?brnd_no=";

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
        String[] ids = StringUtils.substringsBetween(e.attr("onclick"), "'", "'");
        if (ids != null && ids.length > 0) {
          links.add(LINK_PREFIX + ids[0]);
        }
      }
      return links;
    } catch (Exception e) {
    }
    return null;
  }

  @Override
  public void getAndSaveProducts() {
    // TODO Auto-generated method stub

  }

}
