package com.higok.model;

import java.io.Serializable;

/**
 * @author xueqiang.mi
 * @since 2012-7-27
 */
public class Link implements Serializable {

  private static final long serialVersionUID = -1902517544472403760L;

  private long id;
  private String link;
  private String source;
  private boolean status;

  public long getId() {
    return id;
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
    return "Link [id=" + id + ", link=" + link + ", source=" + source + ", status=" + status + "]";
  }
}
