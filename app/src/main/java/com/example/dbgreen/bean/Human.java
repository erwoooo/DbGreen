package com.example.dbgreen.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.erwo.greendao.gen.DaoSession;
import com.erwo.greendao.gen.ManDao;
import com.erwo.greendao.gen.HumanDao;

@Entity
public class Human {
    @Id
    Long id;

    private String huName;

    private String sex;

    private String hgroup;

    @ToMany(joinProperties =
            {@JoinProperty(name = "sex",referencedName = "male")})
    List<Man> manList;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 182860447)
    private transient HumanDao myDao;

    @Generated(hash = 347807904)
    public Human(Long id, String huName, String sex, String hgroup) {
        this.id = id;
        this.huName = huName;
        this.sex = sex;
        this.hgroup = hgroup;
    }

    @Generated(hash = 1258601089)
    public Human() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHuName() {
        return this.huName;
    }

    public void setHuName(String huName) {
        this.huName = huName;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHgroup() {
        return this.hgroup;
    }

    public void setHgroup(String hgroup) {
        this.hgroup = hgroup;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1232900747)
    public List<Man> getManList() {
        if (manList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ManDao targetDao = daoSession.getManDao();
            List<Man> manListNew = targetDao._queryHuman_ManList(sex);
            synchronized (this) {
                if (manList == null) {
                    manList = manListNew;
                }
            }
        }
        return manList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1025857003)
    public synchronized void resetManList() {
        manList = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1318371701)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHumanDao() : null;
    }

    @Override
    public String toString() {
        return "Human{" +
                "id=" + id +
                ", huName='" + huName + '\'' +
                ", sex='" + sex + '\'' +
                ", hgroup='" + hgroup + '\'' +
                ", manList=" + manList +
                ", daoSession=" + daoSession +
                ", myDao=" + myDao +
                '}';
    }
}
