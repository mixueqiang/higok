package com.higok.crawler.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
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
import com.higok.util.TypeUtils;

/**
 * @author xueqiang.mi
 * @since 2012-7-27
 */
public abstract class BaseCrawler implements Crawler {
  private static final Log LOGGER = LogFactory.getLog(BaseCrawler.class);
  protected static final int DEFAULT_SIZE = 5;

  @Autowired
  protected CategoryDAO categoryDAO;
  @Autowired
  protected ItemDAO itemDAO;

  public abstract List<String> getCategories();

  public abstract List<String> getItemsOnCategory(Category cat);

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
    List<Category> cats = categoryDAO.getCategoriesNeedToUpdated(getSource(), DEFAULT_SIZE);
    if (cats == null || cats.isEmpty()) {
      return;
    }

    for (Category cat : cats) {
      List<String> items = getItemsOnCategory(cat);
      if (items == null || items.isEmpty()) {
        return;
      }

      for (String item : items) {
        itemDAO.addIfNotExist(getSource(), item);
      }
      categoryDAO.updateStatus(cat.getId());
    }
  }

  protected boolean submitItemDetail(String cat1, String cat2, String brandName, String title, String price,
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
      httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
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

  protected static String buildURL(String pattern, Object... objects) {
    return MessageFormat.format(pattern, objects);
  }

}
