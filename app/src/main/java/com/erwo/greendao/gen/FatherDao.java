package com.erwo.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.dbgreen.bean.Father;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "FATHER".
*/
public class FatherDao extends AbstractDao<Father, Long> {

    public static final String TABLENAME = "FATHER";

    /**
     * Properties of entity Father.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property FatherName = new Property(1, String.class, "fatherName", false, "FATHER_NAME");
        public final static Property FatherHome = new Property(2, String.class, "fatherHome", false, "FATHER_HOME");
    }

    private DaoSession daoSession;


    public FatherDao(DaoConfig config) {
        super(config);
    }
    
    public FatherDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"FATHER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"FATHER_NAME\" TEXT," + // 1: fatherName
                "\"FATHER_HOME\" TEXT);"); // 2: fatherHome
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"FATHER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Father entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String fatherName = entity.getFatherName();
        if (fatherName != null) {
            stmt.bindString(2, fatherName);
        }
 
        String fatherHome = entity.getFatherHome();
        if (fatherHome != null) {
            stmt.bindString(3, fatherHome);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Father entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String fatherName = entity.getFatherName();
        if (fatherName != null) {
            stmt.bindString(2, fatherName);
        }
 
        String fatherHome = entity.getFatherHome();
        if (fatherHome != null) {
            stmt.bindString(3, fatherHome);
        }
    }

    @Override
    protected final void attachEntity(Father entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Father readEntity(Cursor cursor, int offset) {
        Father entity = new Father( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // fatherName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // fatherHome
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Father entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setFatherName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setFatherHome(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Father entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Father entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Father entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
