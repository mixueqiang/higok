package com.higok.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import com.higok.dao.ItemDAO;
import com.higok.model.Item;
import com.higok.util.RowMapperUtil;

/**
 * @author xueqiang.mi
 * @since 2012-7-27
 */
@Component("itemDAO")
public class ItemDAOImpl extends BaseDAO implements ItemDAO {

  @Override
  public void addIfNotExist(final String source, final String itemId) {
    int rowCount = jdbcTemplate.queryForInt("SELECT count(id) FROM items where item_id = ?", new Object[] { itemId });
    if (rowCount > 0) {
      return;
    }

    jdbcTemplate.update("INSERT INTO items (source, item_id, created_date, updated_date) values (?, ?, ?, ?)",
        new Object[] { source, itemId, Calendar.getInstance().getTimeInMillis() / 1000, 0 });
  }

  @Override
  public List<Item> getItemsNeedToCrawl(final String source, final int size) {
    List<Item> items = jdbcTemplate.query(
        "SELECT * FROM items WHERE source = ? AND status = 1 ORDER BY updated_date, id limit 0, ?", new Object[] {
            source, size }, RowMapperUtil.ITEM_ROW_MAPPER);
    return items;
  }

  @Override
  public List<Item> getItemsNeedToUpdate(final String source, final int size) {
    List<Item> items = jdbcTemplate.query(
        "SELECT * FROM items WHERE source = ? AND status = 0 ORDER BY updated_date, id limit 0, ?", new Object[] {
            source, size }, RowMapperUtil.ITEM_ROW_MAPPER);
    return items;
  }

  @Override
  public void updateStatus(final long id) {
    jdbcTemplate.update("UPDATE items SET status = 0, updated_date = ? WHERE id = ?", new Object[] {
        Calendar.getInstance().getTimeInMillis() / 1000, id });
  }

}
