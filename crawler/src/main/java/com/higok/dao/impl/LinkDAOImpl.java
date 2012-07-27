package com.higok.dao.impl;

import org.springframework.stereotype.Component;

import com.higok.dao.LinkDAO;

/**
 * @author xueqiang.mi
 * @since 2012-7-27
 */
@Component("linkDAO")
public class LinkDAOImpl extends BaseDAO implements LinkDAO {

  @Override
  public void insert(final String link, final String source) {
    jdbcTemplate.update("INSERT INTO links (link, source) values (?, ?)", new Object[] { link, source });
  }

}
