package com.tatvasoft.tatvasoftassignment11.AsyncTaskClass;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.tatvasoft.tatvasoftassignment11.Adapter.ContactAdapter;
import com.tatvasoft.tatvasoftassignment11.Model.ContactsModel;
import com.tatvasoft.tatvasoftassignment11.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.tatvasoft.tatvasoftassignment11.Fragment.ContactsFragment.fragmentContactsBinding;

public class ContactAsyncTask extends AsyncTask<Void, Void, ArrayList> {

    private final WeakReference<Context> contextRef;
    ProgressDialog progressDialog;

    ArrayList<ContactsModel> contactsModelArrayList = new ArrayList<>();
    ContactAdapter contactAdapter;

    public ContactAsyncTask(Context context) {
        contextRef = new WeakReference<>(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(contextRef.get());
        progressDialog.setTitle(contextRef.get().getString(R.string.progress_dialog_title));
        progressDialog.setMessage(contextRef.get().getString(R.string.progress_dialog_message));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @SuppressLint("Range")
    @Override
    protected ArrayList doInBackground(Void... voids) {
        //Initialize Uri
        Uri uri = ContactsContract.Contacts.CONTENT_URI;

        //Sort by name in ascending order
        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";

        //Initialize cursor
        Cursor cursor = contextRef.get().getContentResolver().query(
                uri, null, null, null, sort
        );
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Uri uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?";

                Cursor phoneCursor = contextRef.get().getContentResolver().query(
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
    protected void onPostExecute(ArrayList arrayList) {
        super.onPostExecute(arrayList);
        progressDialog.dismiss();
        fragmentContactsBinding.contactsRecyclerView.setLayoutManager(new LinearLayoutManager(contextRef.get()));
        contactAdapter = new ContactAdapter(arrayList, contextRef.get());
        fragmentContactsBinding.contactsRecyclerView.setAdapter(contactAdapter);

    }
}
