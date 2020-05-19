package com.example.dbgreen.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbgreen.R;
import com.example.dbgreen.bean.Order;
import com.example.dbgreen.bean.Son;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private Context context;
    private List<Order>sonList = new ArrayList<>();

    public OrderAdapter(Context context) {
        this.context = context;
    }

    public void setSonList(List<Order>sonList){
        this.sonList.clear();
        this.sonList.addAll(sonList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.item_con,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order son = sonList.get(position);
        holder.tv_id.setText("子ID： " + son.getId() );
        holder.tv_name.setText("子name: " + son.getOrderName());
    }

    @Override
    public int getItemCount() {
        return sonList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_id;
        private TextView tv_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }
}
