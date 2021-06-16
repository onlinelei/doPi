package win.suroot.dopi.po;


import win.suroot.dopi.common.BaseEntity;

import java.util.Date;

public class User extends BaseEntity {
  private Integer id;

  private String ename;

  private String group;

  private String email;

  private String cname;

  private Integer roleId;

  private Date lastModTime;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEname() {
    return ename;
  }

  public void setEname(String ename) {
    this.ename = ename == null ? null : ename.trim();
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group == null ? null : group.trim();
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email == null ? null : email.trim();
  }

  public String getCname() {
    return cname;
  }

  public void setCname(String cname) {
    this.cname = cname == null ? null : cname.trim();
  }

  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  public Date getLastModTime() {
    return lastModTime;
  }

  public void setLastModTime(Date lastModTime) {
    this.lastModTime = lastModTime;
  }
}