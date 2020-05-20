package com.example.dbgreen.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbgreen.R;
import com.example.dbgreen.bean.Man;
import com.example.dbgreen.bean.Order;

import java.util.ArrayList;
import java.util.List;

public class ManAdapter extends RecyclerView.Adapter<ManAdapter.ViewHolder> {
    private Context context;
    private List<Man>sonList = new ArrayList<>();
    OnItemClickListener onItemClickListener;
    public ManAdapter(Context context) {
        this.context = context;
    }

    public void setSonList(List<Man>sonList){
        this.sonList.clear();
        this.sonList.addAll(sonList);
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
        Man son = sonList.get(position);
        holder.tv_id.setText("Man ID： " + son.getId()  + "Human.size: " + (son.getHumanList() == null?"null":son.getHumanList().size()));
        holder.tv_name.setText("Man name: " + son.getMName());
        holder.tv_name.setOnClickListener(v -> {
            if (son.getHumanList().size() != 0){
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(son);
            }
        });
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

    public interface OnItemClickListener{
        void onItemClick(Man man);
    }
}
