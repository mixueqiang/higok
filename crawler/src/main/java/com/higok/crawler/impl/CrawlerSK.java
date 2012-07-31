package com.higok.crawler.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
public class CrawlerSK extends BaseDFSCrawler implements Crawler {
  private static final Log LOGGER = LogFactory.getLog(CrawlerSK.class);

  private static final String START_URL = "http://www.skdutyfree.com/dutyfree/brand/brandMain";
  private static final String URL_PATTERN_CATEGORY = "http://www.skdutyfree.com/dutyfree/brand/brandDetail/detail.do?brnd_no={0}";
  private static final String URL_PATTERN_ITEM = "http://www.skdutyfree.com/dutyfree/goods/detail/{0}/view.do";

  @Override
  public String getSource() {
    return Constants.SK_SOURCE;
  }

  @Override
  public Collection<String> readCategories() {
    try {
      Document doc = Jsoup.connect(START_URL).get();
      Elements elements = doc.select(".brd_slist a");
      Set<String> brands = new HashSet<String>();
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
  public void retrieveItemDetails(final Item item) {
    String itemId = item.getItemId();
    String link = buildURL(URL_PATTERN_ITEM, itemId);
    try {
      Document doc = Jsoup.connect(link).get();
      Elements elements = doc.select(".prod_main_info .txt_info");
      if (elements.size() != 1) {
        return;
      }

      Element itemInfo = elements.get(0);
      String cat1 = doc.select("#slctOneDepthCategory option[selected]").text().trim();
      String cat2 = doc.select("#slctTwoDepthCategory option[selected]").text().trim();
      String brandName = itemInfo.select("dd:eq(1)").get(0).text().trim();
      String title = itemInfo.select(".pname").get(0).text().trim();
      String price = "";
      elements = itemInfo.select(".price dd");
      for (Element e : elements) {
        if (e.select("del").size() > 0 || "innetCostBtn".equals(e.attr("id"))) {
          continue;
        }

        price = e.text();
      }
      price = StringUtils.substringBefore(price, "(").trim();
      String media = doc.select(".prod_main_info .img_info #prod_img img").attr("src");

      submitItemDetail(cat1, cat2, brandName, title, price, link, media);
      itemDAO.updateStatus(item.getId());
    } catch (Exception e) {
      LOGGER.error("Error on get item detail: " + link, e);
    }
  }

  @Override
  public Collection<String> retrieveItemIds(final Category cat) {
    String link = buildURL(URL_PATTERN_CATEGORY, cat.getBrandId());
    try {
      Document doc = Jsoup.connect(link).get();
      Elements elements = doc.select("#divGoodImg .prod_name a");
      Set<String> itemIds = new HashSet<String>();
      for (Element e : elements) {
        String id = StringUtils.substringBetween(e.attr("onclick"), "('", "')");
        if (id != null) {
          itemIds.add(id);
        }
      }
      return itemIds;
    } catch (Exception e) {
      LOGGER.error("Error on get items from: " + link, e);
    }
    return null;
  }

}
