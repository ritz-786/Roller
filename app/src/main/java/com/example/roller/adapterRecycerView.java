package com.example.roller;

import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapterRecycerView extends RecyclerView.Adapter<adapterRecycerView.viewHolder>{

    ArrayList<Order_info> arrayList;

    public adapterRecycerView(ArrayList<Order_info> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_history_row, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.id.setText(String.valueOf(arrayList.get(position).getId()));
        holder.time.setText(arrayList.get(position).getOrder_time());
        holder.cancel.setOnClickListener(view -> Data.CancelOrder(arrayList.get(position),position));
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    class viewHolder extends RecyclerView.ViewHolder {
        Button cancel;
        TextView id,time;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            id  = itemView.findViewById(R.id.id);
            time = itemView.findViewById(R.id.time);
            cancel = itemView.findViewById(R.id.cancel);
        }
    }
}
