package com.example.dbgreen;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.erwo.greendao.gen.CustomerWithOrderDao;
import com.erwo.greendao.gen.DaoMaster;
import com.example.dbgreen.adapter.CustomerAdapter;
import com.example.dbgreen.adapter.OrderAdapter;
import com.example.dbgreen.bean.Customer;
import com.example.dbgreen.bean.CustomerWithOrder;
import com.example.dbgreen.bean.Order;
import com.example.dbgreen.utils.DBManager;
import com.example.dbgreen.utils.ThreadPoolUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JoinEntityActivity extends AppCompatActivity {
    @BindView(R.id.recycle_father)
    RecyclerView recycle_customer;
    @BindView(R.id.recycle_son)
    RecyclerView recycle_order;
    CustomerAdapter customerAdapter;
    OrderAdapter orderAdapter;
    List<Order>orderList = new ArrayList<>();
    List<Customer>customerList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_one);
        ButterKnife.bind(this);
        DBManager.init(this);

        initView();
    }

    private void initView() {
        recycle_customer.setLayoutManager(new LinearLayoutManager(this));
        recycle_customer.setHasFixedSize(true);
        recycle_customer.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        customerAdapter = new CustomerAdapter(this);
        recycle_customer.setAdapter(customerAdapter);
        customerAdapter.setOnItemClickListener(onItemClickListener);

        recycle_order.setLayoutManager(new LinearLayoutManager(this));
        recycle_order.setHasFixedSize(true);
        recycle_order.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        orderAdapter = new OrderAdapter(this);
        recycle_order.setAdapter(orderAdapter);
    }

    private void setRecycle_show(boolean showSon){
        if (showSon){
            recycle_customer.setVisibility(View.GONE);
            recycle_order.setVisibility(View.VISIBLE);
        }else {
            recycle_customer.setVisibility(View.VISIBLE);
            recycle_order.setVisibility(View.GONE);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick({R.id.text1,R.id.text2,R.id.text3,R.id.text4,R.id.text5,R.id.text6,R.id.text7,R.id.text8})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.text1:
                insertOrderToDB();
                break;
            case R.id.text2:
                insetCustomerToDB();
                break;
            case R.id.text3:
                querryOrderWithDB();
                setRecycle_show(true);
                break;
            case R.id.text4:
                querryCustomerWithDB();
                setRecycle_show(false);
                break;
            case R.id.text5:
                insertOrderToCustomer();
                break;
            case R.id.text6:
                relieveRelation();
                break;
            case R.id.text7:
                deleteOrderWithDB();
                break;
            case R.id.text8:
                updateOrderToNewCustomerWithDB();
                break;

        }
    }

    private void updateOrderToNewCustomerWithDB() {
        if (orderList.size() == 0 )
            orderList = DBManager.getInstance().getOrderDao().loadAll();
        if (customerList.size() == 0)
            customerList = DBManager.getInstance().getCustomerDao().loadAll();
        Customer customer = customerList.get(1);
        for (int i = 0 ; i < orderList.size() / 4 ; i ++){
            Order order = orderList.get(i);
            CustomerWithOrder customerWithOrder = new CustomerWithOrder();
            customerWithOrder.setOderId(order.getId());
            customerWithOrder.setCusId(customer.getId());
            DBManager.getInstance().getCustomerWithOrderDao().insertOrReplace(customerWithOrder);

        }
    }

    private void deleteOrderWithDB() {
        if (orderList.size() == 0)
            orderList = DBManager.getInstance().getOrderDao().loadAll();
        for (int i = 0 ; i < 2; i++){
            Order order = orderList.get(i);
            DBManager.getInstance().getOrderDao().delete(order);
        }
    }

    private void relieveRelation() {
        List<Customer>customers = DBManager.getInstance().getCustomerDao().loadAll();
        for (int i = 0 ; i < customers.size() ; i ++){
            Customer customer = customers.get(i);
            if (customer.getOrderList().size() != 0){
                List<CustomerWithOrder>list = DBManager.getInstance().getCustomerWithOrderDao().queryBuilder()
                        .where(CustomerWithOrderDao.Properties.CusId.eq(customer.getId())).list();
                DBManager.getInstance().getCustomerWithOrderDao().deleteInTx(list);
            }
        }
    }

    private void insertOrderToCustomer() {
        if (orderList.size() != 0 && customerList.size() != 0){
            Customer customer = customerList.get(0);
        for (int i = 0; i < orderList.size() / 2 ; i ++){
            Order order = orderList.get(i);
            CustomerWithOrder customerWithOrder = new CustomerWithOrder();
            customerWithOrder.setCusId(customer.getId());
            customerWithOrder.setOderId(order.getId());
            DBManager.getInstance().getCustomerWithOrderDao().insertInTx(customerWithOrder);
        }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void querryCustomerWithDB() {
        customerList.clear();
        customerList = DBManager.getInstance().getCustomerDao().loadAll();
        customerList.forEach(customer -> {
            customer.resetOrderList();
        });
        customerAdapter.setFathers(customerList);
    }

    private void querryOrderWithDB() {
        orderList.clear();
        orderList = DBManager.getInstance().getOrderDao().loadAll();
        orderAdapter.setSonList(orderList);
    }

    private void insetCustomerToDB() {
        customerList.clear();
        ThreadPoolUtils.getThreadPool().execute(()->{
            for (int i = 0; i < 10 ; i++){
                Customer customer = new Customer();
                customer.setCustomerName("customer no." + i);
                customerList.add(customer);
            }
            DBManager.getInstance().getCustomerDao().insertInTx(customerList);
        });
    }

    private void insertOrderToDB() {
        orderList.clear();
        ThreadPoolUtils.getThreadPool().execute(()->{
            for (int i = 0 ; i < 20 ; i ++){
                Order order = new Order();
                order.setOrderName("my name is " + i);
                orderList.add(order);
            }
            DBManager.getInstance().getOrderDao().insertInTx(orderList);
        });
    }

    private CustomerAdapter.OnItemClickListener onItemClickListener = new CustomerAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(Customer father) {
            setRecycle_show(true);
            orderList.clear();
            orderList.addAll(father.getOrderList());
            orderAdapter.setSonList(orderList);
        }
    };
}
