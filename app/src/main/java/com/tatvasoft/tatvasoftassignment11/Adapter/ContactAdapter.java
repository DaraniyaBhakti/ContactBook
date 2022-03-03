package com.tatvasoft.tatvasoftassignment11.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
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
    ArrayList<ContactsModel> arrayList = new ArrayList<>();
    Context context;
    @SuppressLint("Range")
    public ContactAdapter(Context context) {
        this.context = context;
        //Initialize Uri
        Uri uri = ContactsContract.Contacts.CONTENT_URI;

        //Sort by name in ascending order
        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC";

        //Initialize cursor
        Cursor cursor = context.getContentResolver().query(
                uri,null,null,null,sort
        );
        if(cursor.getCount() > 0)
        {
            while (cursor.moveToNext())
            {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Uri uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID+" =?";

                Cursor phoneCursor = context.getContentResolver().query(
                        uriPhone,null,selection,new String[]{id},null
                );
                if(phoneCursor.moveToNext())
                {
                    String number = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    ContactsModel contactsModel = new ContactsModel();
                    contactsModel.setContactName(name);
                    contactsModel.setContactNumber(number);

                    arrayList.add(contactsModel);
                    phoneCursor.close();
                }
            }
            cursor.close();
        }

        notifyDataSetChanged();
    }

    @NonNull
    @Override
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
