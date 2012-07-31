package com.higok.crawler.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.higok.Constants;
import com.higok.crawler.Crawler;
import com.higok.dao.CategoryDAO;
import com.higok.dao.ItemDAO;
import com.higok.model.Category;
import com.higok.model.Item;
import com.higok.util.TypeUtils;

/**
 * @author xueqiang.mi
 * @since 2012-7-27
 */
public abstract class BaseDFSCrawler implements Crawler {
  private static final Log LOGGER = LogFactory.getLog(BaseDFSCrawler.class);

  protected static final int SIZE_SMALL = 3;
  protected static final int SIZE_MEDIUM = 8;
  protected static final int SIZE_LARGE = 20;

  protected static String buildURL(String pattern, Object... objects) {
    return MessageFormat.format(pattern, objects);
  }

  /**
   * 向Higok.com提交数据。
   */
  protected static boolean submitItemDetail(String cat1, String cat2, String brandName, String title, String price,
      String url, String media) {
    HttpClient httpclient = new DefaultHttpClient();
    HttpParams params = httpclient.getParams();
    HttpConnectionParams.setConnectionTimeout(params, 10000);
    HttpConnectionParams.setSoTimeout(params, 10000);

    HttpPost httpPost = new HttpPost(Constants.URL_SUBMIT_ITEM);
    List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>(7);
    nameValuePairs.add(new BasicNameValuePair("cat1", cat1));
    nameValuePairs.add(new BasicNameValuePair("cat2", cat2));
    nameValuePairs.add(new BasicNameValuePair("brand_name", brandName));
    nameValuePairs.add(new BasicNameValuePair("title", title));
    nameValuePairs.add(new BasicNameValuePair("price", price));
    nameValuePairs.add(new BasicNameValuePair("url", url));
    nameValuePairs.add(new BasicNameValuePair("media", media));

    try {
      // TODO 这里设置的编码格式可能需要根据网站不同有所调整。
      httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
      HttpResponse httpResponse = httpclient.execute(httpPost);

      if (httpResponse.getStatusLine().getStatusCode() == 200) {
        String response = EntityUtils.toString(httpResponse.getEntity());
        Map<String, String> resp = new Gson().fromJson(response, TypeUtils.MAP_STRING_STRING);
        if ("Y".equals(resp.get("status"))) {
          return true;
        }
      }
    } catch (Exception e) {
      LOGGER.error("Submit Item detail error: " + url, e);
    }
    return false;
  }

  @Autowired
  protected CategoryDAO categoryDAO;

  @Autowired
  protected ItemDAO itemDAO;

  @Override
  public void retrieveCategories() {
    Collection<String> brands = readCategories();
    if (brands == null) {
      return;
    }

    for (String brand : brands) {
      categoryDAO.addIfNotExist(getSource(), brand);
    }
  }

  @Override
  public void retrieveItems() {
    List<Item> items = itemDAO.getItemsNeedToUpdate(getSource(), SIZE_MEDIUM);
    if (items == null || items.isEmpty()) {
      // 当数据库中已经没有新的itemId时，执行抓取itemId的任务。
      retrieveNewItemIds();
      return;
    }

    for (Item item : items) {
      retrieveItemDetails(item);
    }
  }

  public abstract Collection<String> readCategories();

  public abstract void retrieveItemDetails(Item item);

  public abstract Collection<String> retrieveItemIds(Category cat);

  private boolean retrieveNewItemIds() {
    List<Category> cats = categoryDAO.getCategoriesNeedToUpdated(getSource(), SIZE_SMALL);
    if (cats == null || cats.isEmpty()) {
      // 当数据库中已经没有新的品牌或类别时，执行抓取品牌或类别的任务。
      retrieveCategories();
      return false;
    }

    boolean added = false;
    for (Category cat : cats) {
      Collection<String> items = retrieveItemIds(cat);
      if (items != null && !items.isEmpty()) {
        added = true;
        for (String item : items) {
          itemDAO.addIfNotExist(getSource(), item);
        }
        categoryDAO.updateStatus(cat.getId());
      }
    }
    return added;
  }
}
