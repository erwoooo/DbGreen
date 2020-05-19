package com.example.dbgreen.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbgreen.R;
import com.example.dbgreen.bean.Customer;
import com.example.dbgreen.bean.Father;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {
    List<Customer> fathers = new ArrayList<>();
    Context context;
    OnItemClickListener onItemClickListener;

    public CustomerAdapter(Context context) {
        this.context = context;
    }

    public void setFathers(List<Customer> fathers){
        this.fathers.clear();
        this.fathers.addAll(fathers);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.item_con,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Customer father = fathers.get(position);
        holder.tv_id.setText("父id: " + father.getId() + "子长度： " + father.getOrderList().size());
        holder.tv_name.setText("父name: " + father.getCustomerName());

        holder.tv_name.setOnClickListener(v -> {
            if (onItemClickListener != null && father.getOrderList().size() != 0){
                onItemClickListener.onItemClick(father);
            }else {
                Toast.makeText(context,"没有子",Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public int getItemCount() {
        return fathers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name;
        private TextView tv_id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_id = itemView.findViewById(R.id.tv_id);
        }
    }

   public interface OnItemClickListener{
        void onItemClick(Customer father);
    }
}
