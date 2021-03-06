package com.example.dbgreen.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Son {

    @Id(autoincrement = true)
    Long id;

    private String sonName;

    private String sonHome;

    private int sonTag;
    private long fatherId;

    @Generated(hash = 2058619631)
    public Son(Long id, String sonName, String sonHome, int sonTag, long fatherId) {
        this.id = id;
        this.sonName = sonName;
        this.sonHome = sonHome;
        this.sonTag = sonTag;
        this.fatherId = fatherId;
    }

    @Generated(hash = 1259336981)
    public Son() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSonName() {
        return this.sonName;
    }

    public void setSonName(String sonName) {
        this.sonName = sonName;
    }

    public String getSonHome() {
        return this.sonHome;
    }

    public void setSonHome(String sonHome) {
        this.sonHome = sonHome;
    }

    public long getFatherId() {
        return this.fatherId;
    }

    public void setFatherId(long fatherId) {
        this.fatherId = fatherId;
    }

    public int getSonTag() {
        return sonTag;
    }

    public void setSonTag(int sonTag) {
        this.sonTag = sonTag;
    }
}
