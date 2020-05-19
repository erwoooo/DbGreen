package com.example.dbgreen.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.erwo.greendao.gen.DaoSession;
import com.erwo.greendao.gen.SonDao;
import com.erwo.greendao.gen.FatherDao;

@Entity
public class Father {
    @Id(autoincrement = true)
    Long id;

    private String fatherName;

    private String fatherHome;

    @ToMany(referencedJoinProperty= "fatherId")
    List<Son>sonList;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 442328359)
    private transient FatherDao myDao;

    @Generated(hash = 1783426818)
    public Father(Long id, String fatherName, String fatherHome) {
        this.id = id;
        this.fatherName = fatherName;
        this.fatherHome = fatherHome;
    }

    @Generated(hash = 383274692)
    public Father() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFatherName() {
        return this.fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherHome() {
        return this.fatherHome;
    }

    public void setFatherHome(String fatherHome) {
        this.fatherHome = fatherHome;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1212748793)
    public List<Son> getSonList() {
        if (sonList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SonDao targetDao = daoSession.getSonDao();
            List<Son> sonListNew = targetDao._queryFather_SonList(id);
            synchronized (this) {
                if (sonList == null) {
                    sonList = sonListNew;
                }
            }
        }
        return sonList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 909162430)
    public synchronized void resetSonList() {
        sonList = null;
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
    @Generated(hash = 6472366)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getFatherDao() : null;
    }
}
