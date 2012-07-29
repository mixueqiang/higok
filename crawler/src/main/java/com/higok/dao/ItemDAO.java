package com.higok.dao;

import java.util.List;

import com.higok.model.Item;

/**
 * @author xueqiang.mi
 * @since 2012-7-27
 */
public interface ItemDAO {

  void addIfNotExist(String source, String itemId);

  List<Item> getItemsNeedToUpdate(String source, int size);

  void updateStatus(long itemId);

}
