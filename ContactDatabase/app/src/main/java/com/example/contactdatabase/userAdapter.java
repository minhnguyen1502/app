package com.example.contactdatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class userAdapter extends RecyclerView.Adapter<userAdapter.MyViewHolder> {

    private Context context;
    private ArrayList name_id, emai_id,date_id;

    public userAdapter(Context context, ArrayList name_id, ArrayList emai_id, ArrayList date_id) {
        this.context = context;
        this.name_id = name_id;
        this.emai_id = emai_id;
        this.date_id = date_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name_id.setText(String.valueOf(name_id.get(position)));
        holder.emai_id.setText(String.valueOf(emai_id.get(position)));
        holder.date_id.setText(String.valueOf(date_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return name_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name_id, emai_id,date_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name_id = itemView.findViewById(R.id.NameTxt);
            emai_id = itemView.findViewById(R.id.EmailTxt);
            date_id = itemView.findViewById(R.id.DateOfBirthTxt);
        }
    }
}
