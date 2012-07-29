package com.higok.crawler;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.higok.crawler.impl.CrawlerSK;

/**
 * @author xueqiang.mi
 * @since 2012-07-28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class CrawlerSKTest {

  @Autowired
  protected CrawlerSK crawlerSK;

  @Ignore
  public void testGetAndSaveCategories() {
    crawlerSK.getAndSaveCategories();
  }

  @Test
  public void testGetAndSaveItems() {
    crawlerSK.getAndSaveItems();
  }

  @Test
  public void testGetItemsDetail() {
    crawlerSK.getItemsDetail();
  }

}
