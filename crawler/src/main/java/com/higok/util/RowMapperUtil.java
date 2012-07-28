package com.higok.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.higok.model.Category;
import com.higok.model.Item;

/**
 * @author xueqiang.mi
 * @since 2012-7-28
 */
public class RowMapperUtil {

  public static final RowMapper<Category> CATEGORY_ROW_MAPPER = new RowMapper<Category>() {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
      Category obj = new Category();
      obj.setId(rs.getLong("id"));
      obj.setSource(rs.getString("source"));
      obj.setBrandId(rs.getString("brand_id"));
      obj.setCategoryId(rs.getString("category_id"));
      obj.setStatus(rs.getBoolean("status"));
      return obj;
    }
  };

  public static final RowMapper<Item> ITEM_ROW_MAPPER = new RowMapper<Item>() {
    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
      Item obj = new Item();
      obj.setId(rs.getLong("id"));
      obj.setSource(rs.getString("source"));
      obj.setItemId(rs.getString("item_id"));
      obj.setLink(rs.getString("link"));
      obj.setStatus(rs.getBoolean("status"));
      return obj;
    }
  };

}
