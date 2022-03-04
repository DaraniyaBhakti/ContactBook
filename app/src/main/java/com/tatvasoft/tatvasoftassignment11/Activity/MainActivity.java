package com.tatvasoft.tatvasoftassignment11.Activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.tatvasoft.tatvasoftassignment11.Adapter.ViewPagerAdapter;
import com.tatvasoft.tatvasoftassignment11.Fragment.AudioFilesFragment;
import com.tatvasoft.tatvasoftassignment11.Fragment.ContactsFragment;
import com.tatvasoft.tatvasoftassignment11.R;
import com.tatvasoft.tatvasoftassignment11.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 10;
    ContactsFragment contactsFragment;
    AudioFilesFragment audioFilesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new ContactsFragment(this), getString(R.string.tab_title_contact));
        viewPagerAdapter.addFragment(new AudioFilesFragment(this), getString(R.string.tab_title_audio));
        binding.viewPager.setAdapter(viewPagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        if (checkAndRequestPermissions()) {
            contactsFragment = new ContactsFragment(this);
            audioFilesFragment = new AudioFilesFragment(this);

        }
    }

    private boolean checkAndRequestPermissions() {

        int permissionAudio = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionContact = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS);

        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionAudio != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (permissionContact != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CONTACTS);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(MainActivity.this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_ID_MULTIPLE_PERMISSIONS) {

            Map<String, Integer> permissionHashMap = new HashMap<>();
            // Initialize the map with both permissions
            permissionHashMap.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
            permissionHashMap.put(Manifest.permission.READ_CONTACTS, PackageManager.PERMISSION_GRANTED);
            // Fill with actual results from user
            if (grantResults.length > 0) {
                for (int i = 0; i < permissions.length; i++)
                    permissionHashMap.put(permissions[i], grantResults[i]);
                // Check for both permissions

                //permissions granted
                if (permissionHashMap.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        && permissionHashMap.get(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                    // process the normal flow
                    audioFilesFragment = new AudioFilesFragment(this);
                    contactsFragment = new ContactsFragment(this);
                    //else any one or both the permissions are not granted
                } else {
                    //permission is denied---without checking never ask again---- so ask permission again explaining the usage of permission
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                            || ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_CONTACTS)) {
                        showDialogOK(getString(R.string.alert_title),
                                getString(R.string.alert_message),
                                (dialog, which) -> {
                                    if (which == DialogInterface.BUTTON_POSITIVE) {
                                        checkAndRequestPermissions();
                                    }
                                });
                    }
                    //permission is denied and never ask again is checked
                    else {
                        Toast.makeText(MainActivity.this, getString(R.string.deny_toast), Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    private void showDialogOK(String title, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok_button, okListener)
                .create()
                .show();
    }


}