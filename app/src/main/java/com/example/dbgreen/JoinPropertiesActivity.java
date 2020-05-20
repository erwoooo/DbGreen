package com.example.dbgreen;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbgreen.adapter.HumanAdapter;
import com.example.dbgreen.adapter.ManAdapter;
import com.example.dbgreen.bean.Human;
import com.example.dbgreen.bean.Man;
import com.example.dbgreen.utils.DBManager;

import org.greenrobot.greendao.test.DbTest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JoinPropertiesActivity extends AppCompatActivity {
    @BindView(R.id.recycle_father)
    RecyclerView recycle_human;
    @BindView(R.id.recycle_son)
    RecyclerView recycle_man;

    HumanAdapter humanAdapter;
    ManAdapter manAdapter;
    List<Human>humanList = new ArrayList<>();
    List<Man> manList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        ButterKnife.bind(this);
        DBManager.init(this);

        initView();
    }

    private void initView() {
        humanAdapter = new HumanAdapter(this);
        recycle_human.setHasFixedSize(true);
        recycle_human.setLayoutManager(new LinearLayoutManager(this));
        recycle_human.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        humanAdapter.setOnItemClickListener(onItemClickListener);
        recycle_human.setAdapter(humanAdapter);

        manAdapter = new ManAdapter(this);
        recycle_man.setHasFixedSize(true);
        recycle_man.setLayoutManager(new LinearLayoutManager(this));
        recycle_man.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        manAdapter.setOnItemClickListener(itemClickListener);
        recycle_man.setAdapter(manAdapter);
    }

    private void setRecycle_show(boolean showSon){
        if (showSon){
            recycle_human.setVisibility(View.GONE);
            recycle_man.setVisibility(View.VISIBLE);
        }else {
            recycle_human.setVisibility(View.VISIBLE);
            recycle_man.setVisibility(View.GONE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick({R.id.text1,R.id.text2,R.id.text3,R.id.text4,R.id.text5,R.id.text6,R.id.text7,R.id.text8})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.text1:
                insertManToDB();
                break;
            case R.id.text2:
                insetHumanToDB();
                break;
            case R.id.text3:
                querryManWithDB();
                setRecycle_show(true);
                break;
            case R.id.text4:
                querryHumanWithDB();
                setRecycle_show(false);
                break;
            case R.id.text5:
                insertManToHuman();
                break;
            case R.id.text6:
                relieveRelation();
                break;
            case R.id.text7:
                insertHuManToManWithDB();
                break;
            case R.id.text8:
                updateManToNewHumanWithDB();
                break;

        }
    }

    private void updateManToNewHumanWithDB() {
        List<Man>tempList = new ArrayList<>();
        manList.clear();
        manList = DBManager.getInstance().getManDao().loadAll();
        humanList.clear();
        humanList = DBManager.getInstance().getHumanDao().loadAll();
        Human human = humanList.get(1);
        human.setSex("humanTag" + human.getId());
        for (int i = 0; i < manList.size() / 4 ;i ++){
            Man man = manList.get(i);
            man.setMale(human.getSex());
            tempList.add(man);
        }
        DBManager.getInstance().getManDao().updateInTx(tempList);
        DBManager.getInstance().getHumanDao().update(human);
    }

  
    private void insertHuManToManWithDB() {
        manList.clear();
        manList = DBManager.getInstance().getManDao().loadAll();
        humanList.clear();
        humanList = DBManager.getInstance().getHumanDao().loadAll();
        Man man = manList.get(0);
        man.setGroup("manTag" + man.getId());

        for (int i = 0 ; i < humanList.size() /2 ; i ++){
            Human human = humanList.get(i);
            human.setHgroup(man.getGroup());
            DBManager.getInstance().getHumanDao().update(human);
        }
        DBManager.getInstance().getManDao().update(man);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void relieveRelation() {
        List<Man>tempList = new ArrayList<>();
        List<Human>tempHumans = new ArrayList<>();
        humanList.clear();
        humanList = DBManager.getInstance().getHumanDao().loadAll();
        humanList.forEach(human -> {
            human.setHgroup("1");
            human.setSex("1");
            tempHumans.add(human);
        });
        if (tempHumans.size() != 0)
        DBManager.getInstance().getHumanDao().updateInTx(tempHumans);

        manList.clear();
        manList = DBManager.getInstance().getManDao().loadAll();

        manList.forEach(man -> {
            man.setMale("0");
            man.setGroup("0");
            tempList.add(man);

        });
        if (tempList.size() != 0)
        DBManager.getInstance().getManDao().updateInTx(tempList);
    }

    private void insertManToHuman() {
        manList.clear();
        manList = DBManager.getInstance().getManDao().loadAll();
        humanList.clear();
        humanList = DBManager.getInstance().getHumanDao().loadAll();
        Human human = humanList.get(0);
        human.setSex("human" + human.getId());
        List<Man>tempList = new ArrayList<>();
        for (int i = 0; i < manList.size() / 2 ; i++){
            Man man = manList.get(i);
            man.setMale(human.getSex());
            tempList.add(man);
        }
        DBManager.getInstance().getManDao().updateInTx(tempList);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void querryHumanWithDB() {
        humanList.clear();
        humanList = DBManager.getInstance().getHumanDao().loadAll();
        humanList.forEach(human -> {
            human.resetManList();
        });
        humanAdapter.setFathers(humanList);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void querryManWithDB() {
        manList.clear();
        manList = DBManager.getInstance().getManDao().loadAll();
        manList.forEach(man -> {
            man.resetHumanList();
        });
        manAdapter.setSonList(manList);

    }

    private void insetHumanToDB() {
        humanList.clear();
        for (int i = 0; i < 10 ; i ++){
            Human human = new Human();
            human.setSex("1");
            human.setHgroup("1");
            human.setHuName("宝塔镇河妖" + i);
            humanList.add(human);
        }
        DBManager.getInstance().getHumanDao().insertInTx(humanList);
    }

    private void insertManToDB() {
        manList.clear();
        for (int i= 0; i < 20 ; i ++){
            Man m = new Man();
            m.setMale("0");
            m.setGroup("0");
            m.setMName("天王盖地虎" + i);
            manList.add(m);
        }
        DBManager.getInstance().getManDao().insertInTx(manList);

    }

    HumanAdapter.OnItemClickListener onItemClickListener = new HumanAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(Human father) {
            setRecycle_show(true);
            manList.clear();
            manList.addAll(father.getManList());
            manAdapter.setSonList(manList);
        }
    };

    ManAdapter.OnItemClickListener itemClickListener = new ManAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(Man man) {
        setRecycle_show(false);
        humanList.clear();
        humanList.addAll(man.getHumanList());
        humanAdapter.setFathers(humanList);
        }
    };

}
