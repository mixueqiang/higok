package com.higok.dao;

import com.higok.model.Category;

/**
 * @author xueqiang.mi
 * @since 2012-7-27
 */
public interface ItemDAO {

  void addIfNotExist(String source, String itemId);

  Category getItemNeedToUpdate(String source);

}
