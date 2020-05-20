package com.example.dbgreen.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.DaoException;
import com.erwo.greendao.gen.DaoSession;
import com.erwo.greendao.gen.HumanDao;
import com.erwo.greendao.gen.ManDao;

@Entity
public class Man {
    @Id
    Long id;

    private String mName;

    private String male;

    private String group;
    @ToMany(joinProperties =
            {@JoinProperty(name = "group",referencedName = "hgroup")})
    List<Human> humanList;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 159868251)
    private transient ManDao myDao;
    @Generated(hash = 1525425218)
    public Man(Long id, String mName, String male, String group) {
        this.id = id;
        this.mName = mName;
        this.male = male;
        this.group = group;
    }
    @Generated(hash = 647924116)
    public Man() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMName() {
        return this.mName;
    }
    public void setMName(String mName) {
        this.mName = mName;
    }
    public String getMale() {
        return this.male;
    }
    public void setMale(String male) {
        this.male = male;
    }
    public String getGroup() {
        return this.group;
    }
    public void setGroup(String group) {
        this.group = group;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1898268875)
    public List<Human> getHumanList() {
        if (humanList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HumanDao targetDao = daoSession.getHumanDao();
            List<Human> humanListNew = targetDao._queryMan_HumanList(group);
            synchronized (this) {
                if (humanList == null) {
                    humanList = humanListNew;
                }
            }
        }
        return humanList;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 346444373)
    public synchronized void resetHumanList() {
        humanList = null;
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
    @Generated(hash = 1620771045)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getManDao() : null;
    }

    @Override
    public String toString() {
        return "Man{" +
                "id=" + id +
                ", mName='" + mName + '\'' +
                ", male='" + male + '\'' +
                ", group='" + group + '\'' +
                ", humanList=" + humanList +
                ", daoSession=" + daoSession +
                ", myDao=" + myDao +
                '}';
    }
}
