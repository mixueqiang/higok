import org.junit.Test;

import com.higok.dao.DAOTestSupport;

/**
 * @author xueqiang.mi
 * @since 2012-7-27
 */
public class LinkDAOTest extends DAOTestSupport {

  @Test
  public void testInsert() {
    linkDAO.insert("tes tlink", "test source");
  }

}
