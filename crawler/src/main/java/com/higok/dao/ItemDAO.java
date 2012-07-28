package com.higok.dao;

import com.higok.model.Item;

/**
 * @author xueqiang.mi
 * @since 2012-7-27
 */
public interface ItemDAO {

  void addIfNotExist(String source, String itemId);

  Item getItemNeedToUpdate(String source);

}
