package com.higok.dao;
import org.junit.Test;


/**
 * @author xueqiang.mi
 * @since 2012-7-27
 */
public class LinkDAOTest extends DAOTestSupport {

  @Test
  public void testInsert() {
    linkDAO.addIfNotExist("tes tlink", "test source");
  }

}
