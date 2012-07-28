package com.higok.dao;

import com.higok.model.Category;

/**
 * @author xueqiang.mi
 * @since 2012-7-27
 */
public interface CategoryDAO {

  void addIfNotExist(String source, String brandId);

  Category getCategoryNeedToUpdated(String source);

}
