package com.tatvasoft.tatvasoftassignment11.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tatvasoft.tatvasoftassignment11.Model.ContactsModel;
import com.tatvasoft.tatvasoftassignment11.databinding.ContactItemRowBinding;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    ArrayList<ContactsModel> arrayList;
    Context context;

    public ContactAdapter(ArrayList<ContactsModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(ContactItemRowBinding.inflate(inflater,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        holder.contactItemRowBinding.tvContactName.setText(arrayList.get(position).getContactName());
        holder.contactItemRowBinding.tvContactNumber.setText(arrayList.get(position).getContactNumber());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ContactItemRowBinding contactItemRowBinding;

        public ViewHolder(ContactItemRowBinding contactItemRowBinding) {
            super(contactItemRowBinding.getRoot());
            this.contactItemRowBinding = contactItemRowBinding;
        }
    }
}
