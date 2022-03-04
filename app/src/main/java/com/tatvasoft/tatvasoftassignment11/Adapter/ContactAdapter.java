package com.tatvasoft.tatvasoftassignment11.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tatvasoft.tatvasoftassignment11.Model.ContactsModel;
import com.tatvasoft.tatvasoftassignment11.R;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    ArrayList<ContactsModel> arrayList;
    Context context;

    public ContactAdapter(ArrayList<ContactsModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    @NonNull
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.contact_item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        holder.tvContactName.setText(arrayList.get(position).getContactName());
        holder.tvContactNumber.setText(arrayList.get(position).getContactNumber());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvContactName, tvContactNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContactName = itemView.findViewById(R.id.tvContactName);
            tvContactNumber = itemView.findViewById(R.id.tvContactNumber);
        }
    }
}
