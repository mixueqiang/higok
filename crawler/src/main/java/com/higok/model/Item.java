package com.higok.model;

import java.io.Serializable;

/**
 * @author xueqiang.mi
 * @since 2012-7-28
 */
public class Item implements Serializable {
  private static final long serialVersionUID = -3682969698136105092L;

  private long id;
  private String source;
  private String itemId;
  private String link;
  private boolean status;

  public long getId() {
    return id;
  }

  public String getItemId() {
    return itemId;
  }

  public String getLink() {
    return link;
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

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Item [id=" + id + ", source=" + source + ", itemId=" + itemId + ", link=" + link + ", status=" + status
        + "]";
  }

}
