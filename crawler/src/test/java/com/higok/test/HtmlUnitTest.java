package com.higok.test;

import java.io.IOException;
import java.net.MalformedURLException;

import net.sourceforge.htmlunit.corejs.javascript.EcmaError;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;

/**
 * @author xueqiang.mi
 * @since 2012-8-1
 */
public class HtmlUnitTest {

  @Test
  public void testHtmlUnit() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
    WebClient client = new WebClient(BrowserVersion.FIREFOX_3_6);
    client.setThrowExceptionOnScriptError(true);
    Page page = null;
    try {
      page = client
          .getPage("http://www.lottedfs.com/handler/ProductDetail-Start?productId=10000048395&viewCategoryId=&tracking=");
      String pageContent = page.getWebResponse().getContentAsString();
      Document doc = Jsoup.parse(pageContent);
      Elements elements = doc.select("#id_brand_nm");
      System.out.println(elements);
    } catch (EcmaError e) {
      // ignore it.
    }

  }

}
