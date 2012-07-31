package com.higok.crawler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.higok.crawler.impl.CrawlerLotte;

/**
 * @author xueqiang.mi
 * @since 2012-07-28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class CrawlerLotteTest {

  @Autowired
  protected CrawlerLotte crawlerLotte;

  @Test
  public void testGetCategories() {
    crawlerLotte.retrieveCategories();
  }

  @Test
  public void testGetItems() {
    crawlerLotte.retrieveItems();
  }

}
