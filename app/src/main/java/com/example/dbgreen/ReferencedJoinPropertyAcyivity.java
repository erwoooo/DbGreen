package com.example.dbgreen;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbgreen.adapter.FatherAdapter;
import com.example.dbgreen.adapter.SonAdapter;
import com.example.dbgreen.bean.Father;
import com.example.dbgreen.bean.Son;
import com.example.dbgreen.utils.DBManager;
import com.example.dbgreen.utils.ThreadPoolUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReferencedJoinPropertyAcyivity extends AppCompatActivity {
    @BindView(R.id.recycle_father)
    RecyclerView recycle_father;
    @BindView(R.id.recycle_son)
    RecyclerView recycle_son;
    FatherAdapter fatherAdapter;
    SonAdapter sonAdapter;
    private List<Father>fatherList = new ArrayList<>();
    private List<Son>sonList = new ArrayList<>();
    List<Son>father_son = new ArrayList<>();
    Father tempFather;
    List<Son> tempSons = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_one);
        ButterKnife.bind(this);
        DBManager.init(this);
        initView();
    }

    private void setRecycle_show(boolean showSon){
        if (showSon){
            recycle_father.setVisibility(View.GONE);
            recycle_son.setVisibility(View.VISIBLE);
        }else {
            recycle_father.setVisibility(View.VISIBLE);
            recycle_son.setVisibility(View.GONE);
        }
    }

    private void initView() {
        fatherAdapter = new FatherAdapter(this);
        sonAdapter = new SonAdapter(this);
        recycle_father.setLayoutManager(new LinearLayoutManager(this));
        fatherAdapter.setOnItemClickListener(onItemClickListener);
        recycle_father.setHasFixedSize(true);
        recycle_father.setAdapter(fatherAdapter);
        recycle_son.setLayoutManager(new LinearLayoutManager(this));
        recycle_son.setHasFixedSize(true);
        recycle_son.setAdapter(sonAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick({R.id.text1,R.id.text2,R.id.text3,R.id.text4,R.id.text5,R.id.text6,R.id.text7,R.id.text8})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.text1:
                insertSonToDB();
                break;
            case R.id.text2:
                insetFatherToDB();
                break;
            case R.id.text3:
                querrySonWithDB();
                setRecycle_show(true);
                break;
            case R.id.text4:
                querryFatherWithDB();
                setRecycle_show(false);
                break;
            case R.id.text5:
                insertSonToFather();
                break;
            case R.id.text6:
                relieveRelation();
                break;
            case R.id.text7:
                deleteSonWithDB();
                break;
            case R.id.text8:
                deleteFatherWithDB();
                break;

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void deleteFatherWithDB() {
        ThreadPoolUtils.getThreadPool().execute(()->{
            List<Father>list = DBManager.getInstance().getFatherDao().loadAll();
            list.forEach(father1 -> {
                if (father1.getId() % 5 == 0){
                    DBManager.getInstance().getFatherDao().delete(father1);
                }
            });
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void deleteSonWithDB() {
        ThreadPoolUtils.getThreadPool().execute(()->{
            List<Son>list = DBManager.getInstance().getSonDao().loadAll();
            list.forEach(son -> {
                if (son.getId() % 6 == 0){
                    DBManager.getInstance().getSonDao().delete(son);
                }

            });

        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void relieveRelation() {
        ThreadPoolUtils.getThreadPool().execute(()->{

            if (tempFather == null){
                tempFather = DBManager.getInstance().getFatherDao().queryBuilder().build().list().get(0);
            }
                tempFather.getSonList().forEach(son -> {
                    son.setFatherId(0);
                    DBManager.getInstance().getSonDao().updateInTx(son);
                });


        });


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void insertSonToFather() {

        ThreadPoolUtils.getThreadPool().execute(()->{
            if (sonList.size() == 0)
                sonList = DBManager.getInstance().getSonDao().loadAll();
            tempFather = DBManager.getInstance().getFatherDao().queryBuilder().build().list().get(0);
            tempSons = new ArrayList<>();
            sonList.forEach(son -> {
                if (son.getId() % 3 == 0){
                    son.setFatherId(tempFather.getId());
                    tempSons.add(son);
                }
            });
            DBManager.getInstance().getSonDao().updateInTx(tempSons);
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void querryFatherWithDB() {
        fatherList.clear();
        fatherList = DBManager.getInstance().getFatherDao().loadAll();
        fatherList.forEach(father -> {
            father.resetSonList();
        });
        fatherAdapter.setFathers(fatherList);
    }

    private void querrySonWithDB() {
        sonList.clear();
        sonList = DBManager.getInstance().getSonDao().loadAll();
        sonAdapter.setSonList(sonList);
    }

    private void insetFatherToDB() {
        List<Father>fathers = new ArrayList<>();
        ThreadPoolUtils.getThreadPool().execute(()->{
            for (int i = 0 ; i < 20 ; i ++){
                Father father = new Father();
                father.setFatherName("这是第"+i+"个父亲");
                fathers.add(father);
            }
            DBManager.getInstance().getFatherDao().insertInTx(fathers);
        });

    }

    private void insertSonToDB() {
        List<Son> sons = new ArrayList<>();
        ThreadPoolUtils.getThreadPool().execute(()->{
            for (int i = 0 ; i < 60 ; i ++){
                Son son = new Son();
                son.setSonName("这是第" + i + "个孩子");
                sons.add(son);
            }
            DBManager.getInstance().getSonDao().insertOrReplaceInTx(sons);
        });
    }

    FatherAdapter.OnItemClickListener onItemClickListener = new FatherAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(Father father) {
            father.resetSonList();
            tempFather = father;
            setRecycle_show(true);
            father_son.clear();
            father_son.addAll(father.getSonList());
            sonAdapter.setSonList(father_son);
        }
    };
}
