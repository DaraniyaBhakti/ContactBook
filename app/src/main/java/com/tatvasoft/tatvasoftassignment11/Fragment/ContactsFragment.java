package com.tatvasoft.tatvasoftassignment11.Fragment;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.tatvasoft.tatvasoftassignment11.BackgroundProcess.ContactBackgroundTask;
import com.tatvasoft.tatvasoftassignment11.databinding.FragmentContactsBinding;

public class ContactsFragment extends Fragment {
    public static FragmentContactsBinding fragmentContactsBinding;

    public ContactsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentContactsBinding = FragmentContactsBinding.inflate(inflater, container, false);
        return fragmentContactsBinding.getRoot();

    }

    @Override
    public void onResume() {
        super.onResume();
        setContact();
    }

    public void setContact() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                getContactList();
            }
        } else {
            getContactList();
        }
    }

    public void getContactList() {

        ContactBackgroundTask contactBackgroundTask = new ContactBackgroundTask(getContext());
        contactBackgroundTask.execute();
    }

}