package win.suroot.dopi.po;

import java.util.Date;

import win.suroot.dopi.common.BaseEntity;

public class DailyInfo extends BaseEntity {
  private Integer id;

  private String dateStr;

  private String dateWorld;

  private String datePicUrl;

  private Date createTime;

  private Date updateTime;

  private Byte deleted;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDateStr() {
    return dateStr;
  }

  public void setDateStr(String dateStr) {
    this.dateStr = dateStr == null ? null : dateStr.trim();
  }

  public String getDateWorld() {
    return dateWorld;
  }

  public void setDateWorld(String dateWorld) {
    this.dateWorld = dateWorld == null ? null : dateWorld.trim();
  }

  public String getDatePicUrl() {
    return datePicUrl;
  }

  public void setDatePicUrl(String datePicUrl) {
    this.datePicUrl = datePicUrl == null ? null : datePicUrl.trim();
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public Byte getDeleted() {
    return deleted;
  }

  public void setDeleted(Byte deleted) {
    this.deleted = deleted;
  }
}