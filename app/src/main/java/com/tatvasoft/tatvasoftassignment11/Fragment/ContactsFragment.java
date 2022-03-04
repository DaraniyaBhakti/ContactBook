package com.tatvasoft.tatvasoftassignment11.Fragment;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tatvasoft.tatvasoftassignment11.Adapter.ContactAdapter;
import com.tatvasoft.tatvasoftassignment11.AsyncTaskClass.ContactAsyncTask;
import com.tatvasoft.tatvasoftassignment11.databinding.FragmentContactsBinding;

public class ContactsFragment extends Fragment {
    public static FragmentContactsBinding binding;
    Context context;

    public ContactsFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentContactsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        setContact();
    }

    public void setContact() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                getContactList();
            }
        } else {
            getContactList();
        }
    }

    public void getContactList() {
        ContactAsyncTask contactAsyncTask = new ContactAsyncTask(context);
        contactAsyncTask.execute();
    }

}