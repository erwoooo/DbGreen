package com.example.dbgreen.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbgreen.R;
import com.example.dbgreen.bean.Son;

import java.util.ArrayList;
import java.util.List;

public class SonAdapter extends RecyclerView.Adapter<SonAdapter.ViewHolder> {
    private Context context;
    private List<Son>sonList = new ArrayList<>();

    public SonAdapter(Context context) {
        this.context = context;
    }

    public void setSonList(List<Son>sonList){
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
        Son son = sonList.get(position);
        holder.tv_id.setText("子ID： " + son.getId() + "父id: " + son.getFatherId() );
        holder.tv_name.setText("子name: " + son.getSonName());
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
