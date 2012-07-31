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
@Component("crawlerLotte")
public class CrawlerLotte extends BaseDFSCrawler implements Crawler {
  private static final Log LOGGER = LogFactory.getLog(CrawlerLotte.class);

  private static final String START_URL = "http://www.lottedfs.com/handler/BrandShop-Main?brandId=0501";
  private static final String URL_PATTERN_CATEGORY = "http://www.lottedfs.com/handler/BrandShop-Main?brandId={0}";
  private static final String URL_PATTERN_ITEM = "http://www.lottedfs.com/handler/ProductDetail-Start?productId={0}&viewCategoryId=&tracking=";

  @Override
  public String getSource() {
    return Constants.LOTTE_SOURCE;
  }

  @Override
  public Collection<String> readCategories() {
    try {
      Document doc = Jsoup.connect(START_URL).get();
      Elements elements = doc.select(".search_result .result_middle .cate a");
      Set<String> brands = new HashSet<String>();
      for (Element e : elements) {
        String[] ids = StringUtils.substringsBetween(e.attr("href"), "('", "',");
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
      String[] cats = new String[3];
      Elements catInfo = doc.select("#middle #main .location .sub");
      for (int i = 0; i < catInfo.size(); i++) {
        Element e = catInfo.get(i);
        cats[i] = e.select("select option[selected]").text().trim();
      }

      String brandName = doc.select("#id_brand_nm").get(0).text().trim();
      String title = doc.select("#id_product_nm").get(0).text().trim();
      String price = doc.select("#id_sale_price_s").get(0).text().trim();
      String media = doc.select("#ProdPhoto2").get(0).text().trim();

      submitItemDetail(cats[0], cats[1], brandName, title, price, link, media);
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
      Elements elements = doc.select(".prodImg .photo a");
      Set<String> itemIds = new HashSet<String>();
      for (Element e : elements) {
        String id = StringUtils.substringBetween(e.attr("href"), "(\"", "\",");
        if (id != null) {
          itemIds.add(id);
        }
      }
      return itemIds;
    } catch (Exception e) {
      LOGGER.error("Error on get items from " + link, e);
    }
    return null;
  }

}
