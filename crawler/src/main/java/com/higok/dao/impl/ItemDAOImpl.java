package com.higok.dao.impl;

import java.util.Calendar;

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
  public Item getItemNeedToUpdate(String source) {
    Item item = jdbcTemplate.queryForObject(
        "SELECT * FROM items WHERE source = ? AND status = 1 ORDER BY updated_date, id limit 0,1",
        new Object[] { source }, RowMapperUtil.ITEM_ROW_MAPPER);
    jdbcTemplate.update("UPDATE items SET status = 0, updated_date = ? WHERE id = ?", new Object[] {
        Calendar.getInstance().getTimeInMillis() / 1000, item.getId() });

    return item;
  }

}
