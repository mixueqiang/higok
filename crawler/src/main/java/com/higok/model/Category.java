package com.higok.model;

import java.io.Serializable;

/**
 * @author xueqiang.mi
 * @since 2012-7-27
 */
public class Category implements Serializable {
  private static final long serialVersionUID = -1902517544472403760L;

  private long id;
  private String source;
  private String brandId;
  private String categoryId;
  private boolean status;

  public String getBrandId() {
    return brandId;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public long getId() {
    return id;
  }

  public String getSource() {
    return source;
  }

  public boolean isStatus() {
    return status;
  }

  public void setBrandId(String brandId) {
    this.brandId = brandId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Category [id=" + id + ", source=" + source + ", brandId=" + brandId + ", categoryId=" + categoryId
        + ", status=" + status + "]";
  }

}
