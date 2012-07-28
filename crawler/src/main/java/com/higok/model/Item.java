package com.higok.model;

import java.io.Serializable;

/**
 * @author xueqiang.mi
 * @since 2012-7-28
 */
public class Item implements Serializable {
  private static final long serialVersionUID = -3682969698136105092L;

  private long id;
  private String title;
  private String caption;
  private String url;
  private String path;
  private String name;
  private String prices;

  private long category_id;
  private long brand_id;
  private long location_id;
  private long user_id;
  private long circle_id;

  public long getBrand_id() {
    return brand_id;
  }

  public String getCaption() {
    return caption;
  }

  public long getCategory_id() {
    return category_id;
  }

  public long getCircle_id() {
    return circle_id;
  }

  public long getId() {
    return id;
  }

  public long getLocation_id() {
    return location_id;
  }

  public String getName() {
    return name;
  }

  public String getPath() {
    return path;
  }

  public String getPrices() {
    return prices;
  }

  public String getTitle() {
    return title;
  }

  public String getUrl() {
    return url;
  }

  public long getUser_id() {
    return user_id;
  }

  public void setBrand_id(long brand_id) {
    this.brand_id = brand_id;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public void setCategory_id(long category_id) {
    this.category_id = category_id;
  }

  public void setCircle_id(long circle_id) {
    this.circle_id = circle_id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setLocation_id(long location_id) {
    this.location_id = location_id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public void setPrices(String prices) {
    this.prices = prices;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setUser_id(long user_id) {
    this.user_id = user_id;
  }

  @Override
  public String toString() {
    return "Item [id=" + id + ", title=" + title + ", brand_id=" + brand_id + ", user_id=" + user_id + ", circle_id="
        + circle_id + "]";
  }

}
