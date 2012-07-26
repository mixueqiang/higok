package com.taobao.crawler.teset;

import java.io.IOException;

import junit.framework.Assert;

import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

/**
 * @author xueqiang.mi
 * @since 2012-7-26
 */
public class SKDFTest {

  @Test
  public void testGetBrandsWithHtmlParser1() throws ParserException {
    long sum = 0;
    for (int i = 0; i < 10; i++) {
      long start = System.currentTimeMillis();
      Parser parser = new Parser("http://www.skdutyfree.com/dutyfree/brand/brandMain");
      NodeList nodeList = parser.parse(new AndFilter(
          new HasParentFilter(new CssSelectorNodeFilter(".brd_slist"), true), new TagNameFilter("a")));

      Assert.assertEquals(210, nodeList.size());
      long time = System.currentTimeMillis() - start;
      sum += time;
    }
    System.out.println(sum / 10);
  }

  @Test
  public void testGetBrandsWithHtmlParser2() throws ParserException {
    long sum = 0;
    for (int i = 0; i < 10; i++) {
      long start = System.currentTimeMillis();
      Parser parser = new Parser("http://www.skdutyfree.com/dutyfree/brand/brandMain");
      NodeList nodeList = parser.parse(new AndFilter(new HasParentFilter(new HasAttributeFilter("class", "brd_slist"),
          true), new TagNameFilter("a")));

      Assert.assertEquals(210, nodeList.size());
      long time = System.currentTimeMillis() - start;
      sum += time;
    }
    System.out.println(sum / 10);
  }

  @Test
  public void testGetBrandsWithJsoup() throws IOException {
    long sum = 0;
    for (int i = 0; i < 10; i++) {
      long start = System.currentTimeMillis();
      Document doc = Jsoup.connect("http://www.skdutyfree.com/dutyfree/brand/brandMain").get();
      Elements elements = doc.select(".brd_slist a");

      Assert.assertEquals(210, elements.size());
      long time = System.currentTimeMillis() - start;
      sum += time;
    }
    System.out.println(sum / 10);
  }

}
