package com.example.dbgreen.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class CustomerWithOrder {
    @Id
    Long id;
    private Long cusId;
    private Long oderId;
    @Generated(hash = 1042584339)
    public CustomerWithOrder(Long id, Long cusId, Long oderId) {
        this.id = id;
        this.cusId = cusId;
        this.oderId = oderId;
    }
    @Generated(hash = 1058037459)
    public CustomerWithOrder() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCusId() {
        return this.cusId;
    }
    public void setCusId(Long cusId) {
        this.cusId = cusId;
    }
    public Long getOderId() {
        return this.oderId;
    }
    public void setOderId(Long oderId) {
        this.oderId = oderId;
    }
}
