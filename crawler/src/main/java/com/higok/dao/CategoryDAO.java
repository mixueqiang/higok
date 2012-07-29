package com.higok.dao;

import java.util.List;

import com.higok.model.Category;

/**
 * @author xueqiang.mi
 * @since 2012-7-27
 */
public interface CategoryDAO {

  void addIfNotExist(String source, String brandId);

  List<Category> getCategoriesNeedToUpdated(String source, int size);

  void updateStatus(long catId);

}
