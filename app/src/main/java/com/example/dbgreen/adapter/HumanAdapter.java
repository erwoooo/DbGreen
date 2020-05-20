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
import com.example.dbgreen.bean.Human;

import java.util.ArrayList;
import java.util.List;

public class HumanAdapter extends RecyclerView.Adapter<HumanAdapter.ViewHolder> {
    List<Human> fathers = new ArrayList<>();
    Context context;
    OnItemClickListener onItemClickListener;

    public HumanAdapter(Context context) {
        this.context = context;
    }

    public void setFathers(List<Human> fathers){
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
        Human father = fathers.get(position);
        holder.tv_id.setText("Human ID: " + father.getId() + "man.size： " + father.getManList().size());
        holder.tv_name.setText("Human name: " + father.getHuName());

        holder.tv_name.setOnClickListener(v -> {
            if (onItemClickListener != null && father.getManList().size() != 0){
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
        void onItemClick(Human father);
    }
}
