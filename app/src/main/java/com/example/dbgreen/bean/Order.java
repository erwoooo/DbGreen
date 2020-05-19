package com.example.dbgreen.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Order {
    @Id(autoincrement = true)
    Long id;
    private String orderName;
    @Generated(hash = 1334344717)
    public Order(Long id, String orderName) {
        this.id = id;
        this.orderName = orderName;
    }
    @Generated(hash = 1105174599)
    public Order() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getOrderName() {
        return this.orderName;
    }
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

}
