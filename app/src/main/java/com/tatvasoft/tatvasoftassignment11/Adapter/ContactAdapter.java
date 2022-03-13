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
import com.tatvasoft.tatvasoftassignment11.databinding.ContactItemRowBinding;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    ArrayList<ContactsModel> arrayList;
    Context context;

    public ContactAdapter(ArrayList<ContactsModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    ContactItemRowBinding binding;
    @NonNull
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ContactItemRowBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        binding.tvContactName.setText(arrayList.get(position).getContactName());
        binding.tvContactNumber.setText(arrayList.get(position).getContactNumber());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
