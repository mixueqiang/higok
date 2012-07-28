package com.higok.crawler.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.higok.Constants;
import com.higok.crawler.Crawler;
import com.higok.model.Category;
import com.higok.model.Item;

/**
 * @author xueqiang.mi
 * @since 2012-7-27
 */
@Component("crawlerSK")
public class CrawlerSK extends BaseCrawler implements Crawler {
  private static final Log LOGGER = LogFactory.getLog(CrawlerSK.class);

  private static final String START_URL = "http://www.skdutyfree.com/dutyfree/brand/brandMain";
  private static final String URL_PATTERN_CATEGORY = "http://www.skdutyfree.com/dutyfree/brand/brandDetail/detail.do?brnd_no={0}";
  private static final String URL_PATTERN_ITEM = "http://www.skdutyfree.com/dutyfree/goods/detail/{0}/view.do";

  @Override
  public List<String> getCategories() {
    try {
      Document doc = Jsoup.connect(START_URL).get();
      Elements elements = doc.select(".brd_slist a");
      List<String> brands = new ArrayList<String>();
      for (Element e : elements) {
        String[] ids = StringUtils.substringsBetween(e.attr("onclick"), "'", "'");
        if (ids != null && ids.length > 0) {
          brands.add(ids[0]);
        }
      }
      return brands;
    } catch (Exception e) {
      LOGGER.error("Error on get categories from " + getSource(), e);
    }
    return null;
  }

  @Override
  public void getItemDetail() {
    Item item = itemDAO.getItemNeedToUpdate(getSource());
    String link = buildURL(URL_PATTERN_ITEM, item.getItemId());
    try {
      Document doc = Jsoup.connect(link).get();
      Elements elements = doc.select(".prod_main_info .txt_info");
      if (elements.size() != 1) {
        return;
      }

      Element itemInfo = elements.get(0);
      String cat1 = "";
      String cat2 = "";
      String brandName = itemInfo.select("dd:first").text().trim();
      String title = itemInfo.select(".pname").text().trim();
      String price = "";
      elements = itemInfo.select(".price dd");
      for (Element e : elements) {
        if (e.select("del").size() > 0 || "innetCostBtn".equals(e.attr("id"))) {
          continue;
        }

        price = e.text();
      }

    } catch (Exception e) {
      LOGGER.error("", e);
    }
  }

  @Override
  public List<String> getItems() {
    Category cat = categoryDAO.getCategoryNeedToUpdated(getSource());
    String link = buildURL(URL_PATTERN_CATEGORY, cat.getBrandId());
    try {
      Document doc = Jsoup.connect(link).get();
      Elements elements = doc.select("#divGoodImg .prod_name a");
      List<String> items = new ArrayList<String>();
      for (Element e : elements) {
        String id = StringUtils.substringBetween(e.attr("onclick"), "('", "')");
        if (id != null) {
          items.add(id);
        }
      }
      return items;
    } catch (Exception e) {
      LOGGER.error("Error on get items from " + getSource(), e);
    }
    return null;
  }

  @Override
  public String getSource() {
    return Constants.SK_SOURCE;
  }

}
