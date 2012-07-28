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
  private String brand_id;
  private String category_id;
  private boolean status;

  public long getId() {
    return id;
  }

  public String getSource() {
    return source;
  }

  public boolean isStatus() {
    return status;
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

  public String getBrand_id() {
    return brand_id;
  }

  public void setBrand_id(String brand_id) {
    this.brand_id = brand_id;
  }

  public String getCategory_id() {
    return category_id;
  }

  public void setCategory_id(String category_id) {
    this.category_id = category_id;
  }

  @Override
  public String toString() {
    return "Category [id=" + id + ", source=" + source + ", brand_id=" + brand_id + ", category_id=" + category_id
        + ", status=" + status + "]";
  }

}
