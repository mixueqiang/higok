package com.higok.dao.impl;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.higok.dao.CategoryDAO;
import com.higok.model.Category;
import com.higok.util.RowMapperUtil;

/**
 * @author xueqiang.mi
 * @since 2012-7-27
 */
@Component("categoryDAO")
public class CategoryDAOImpl extends BaseDAO implements CategoryDAO {

  @Override
  public void addIfNotExist(final String source, final String brandId) {
    int rowCount = jdbcTemplate.queryForInt("SELECT count(id) FROM categories where brand_id = ?",
        new Object[] { brandId });
    if (rowCount > 0) {
      return;
    }

    jdbcTemplate.update("INSERT INTO categories (source, brand_id, created_date, updated_date) values (?, ?, ?, ?)",
        new Object[] { source, brandId, Calendar.getInstance().getTimeInMillis() / 1000, 0 });
  }

  @Override
  public Category getCategoryNeedToUpdated(final String source) {
    Category cat = jdbcTemplate.queryForObject(
        "SELECT * FROM categories WHERE source = ? ORDER BY updated_date, id limit 0,1", new Object[] { source },
        RowMapperUtil.CATEGORY_ROW_MAPPER);
    jdbcTemplate.update("UPDATE categories SET updated_date = ? WHERE id = ?", new Object[] {
        Calendar.getInstance().getTimeInMillis() / 1000, cat.getId() });

    return cat;
  }

}
