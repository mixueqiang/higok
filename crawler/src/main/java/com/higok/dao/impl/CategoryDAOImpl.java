package com.higok.dao.impl;

import java.util.Calendar;
import java.util.List;

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
  public List<Category> getCategoriesNeedToUpdated(final String source, final int size) {
    List<Category> cats = jdbcTemplate.query(
        "SELECT * FROM categories WHERE source = ? AND status = 1 ORDER BY updated_date, id limit 0, ?", new Object[] {
            source, size }, RowMapperUtil.CATEGORY_ROW_MAPPER);
    return cats;
  }

  @Override
  public void updateStatus(final long id) {
    jdbcTemplate.update("UPDATE categories SET status = 0, updated_date = ? WHERE id = ?", new Object[] {
        Calendar.getInstance().getTimeInMillis() / 1000, id });
  }

}
