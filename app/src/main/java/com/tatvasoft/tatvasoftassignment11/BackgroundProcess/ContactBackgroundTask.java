package com.tatvasoft.tatvasoftassignment11.BackgroundProcess;

import static com.tatvasoft.tatvasoftassignment11.Fragment.ContactsFragment.fragmentContactsBinding;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.tatvasoft.tatvasoftassignment11.Adapter.ContactAdapter;
import com.tatvasoft.tatvasoftassignment11.Model.ContactsModel;

import java.util.ArrayList;

public class ContactBackgroundTask extends BackgroundTask{

    ArrayList<ContactsModel> contactsModelArrayList = new ArrayList<>();
    ContactAdapter contactAdapter;
    Context context;

    public ContactBackgroundTask(Context context) {
        this.context = context;
    }

    @SuppressLint("Range")
    @Override
    protected ArrayList<ContactsModel> doInBackground() {
        super.doInBackground();
        //Initialize Uri
        Uri uri = ContactsContract.Contacts.CONTENT_URI;

        //Sort by name in ascending order
        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";

        //Initialize cursor
        Cursor cursor = context.getContentResolver().query(
                uri, null, null, null, sort
        );
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Uri uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?";

                Cursor phoneCursor = context.getContentResolver().query(
                        uriPhone, null, selection, new String[]{id}, null
                );
                if (phoneCursor.moveToNext()) {
                    String number = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    ContactsModel contactsModel = new ContactsModel();
                    contactsModel.setContactName(name);
                    contactsModel.setContactNumber(number);

                    contactsModelArrayList.add(contactsModel);
                    phoneCursor.close();
                }
            }
            cursor.close();
        }
        return contactsModelArrayList;
    }

    @Override
    protected void onPostExecute(ArrayList<ContactsModel> arrayList) {
        super.onPostExecute(arrayList);
        fragmentContactsBinding.contactsRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        contactAdapter = new ContactAdapter(arrayList, context);
        fragmentContactsBinding.contactsRecyclerView.setAdapter(contactAdapter);
        fragmentContactsBinding.grantPermissionTextContact.setVisibility(View.GONE);
    }
}
