package win.suroot.dopi.po;

import java.util.Date;
import win.suroot.dopi.common.BaseEntity;

public class VerifyTable extends BaseEntity {
    private Integer id;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}