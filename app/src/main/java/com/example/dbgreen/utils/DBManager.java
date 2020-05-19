package com.example.dbgreen.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.erwo.greendao.gen.CustomerDao;
import com.erwo.greendao.gen.CustomerWithOrderDao;
import com.erwo.greendao.gen.DaoMaster;
import com.erwo.greendao.gen.DaoSession;
import com.erwo.greendao.gen.FatherDao;
import com.erwo.greendao.gen.OrderDao;
import com.erwo.greendao.gen.SonDao;

public class DBManager {

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public static DBManager mDBManager;

    public static synchronized DBManager getInstance(){
    return mDBManager;
    }

    private DBManager(Context context){
        mHelper = new DaoMaster.DevOpenHelper(context, "demo-db", null);  //创建数据库
        db = mHelper.getWritableDatabase(); //获取一个可读的数据库
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }
    public static void init(Context context) {
        if (mDBManager == null)
            mDBManager = new DBManager(context);
    }
    public FatherDao getFatherDao(){
        return mDaoSession.getFatherDao();
    }

    public SonDao getSonDao(){
        return mDaoSession.getSonDao();
    }

    public CustomerDao getCustomerDao(){
        return mDaoSession.getCustomerDao();
    }

    public OrderDao getOrderDao(){
        return mDaoSession.getOrderDao();
    }

    public CustomerWithOrderDao getCustomerWithOrderDao(){
        return mDaoSession.getCustomerWithOrderDao();
    }
}
