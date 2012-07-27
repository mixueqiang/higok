package com.higok.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author xueqiang.mi
 * @since 2012-7-27
 */
public abstract class BaseDAO {

  @Autowired
  protected JdbcTemplate jdbcTemplate;

}
