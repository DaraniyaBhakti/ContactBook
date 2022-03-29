package com.tatvasoft.tatvasoftassignment11.Fragment;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tatvasoft.tatvasoftassignment11.Adapter.AudioAdapter;
import com.tatvasoft.tatvasoftassignment11.databinding.FragmentAudioFilesBinding;

import java.util.ArrayList;

public class AudioFilesFragment extends Fragment {

    ArrayList<String> arrayList = new ArrayList<>();
    public static FragmentAudioFilesBinding fragmentAudioFilesBinding;

    public AudioFilesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentAudioFilesBinding = FragmentAudioFilesBinding.inflate(inflater, container, false);

        return fragmentAudioFilesBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        setAudioFiles();
    }

    public void setAudioFiles() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                getAudioFiles();
            }
        } else {
            getAudioFiles();
        }
    }

    public void getAudioFiles() {
        ContentResolver audioResolver = requireContext().getContentResolver();
        Uri audioUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor audioCursor = audioResolver.query(audioUri, null, null, null, null);
        if (audioCursor != null && audioCursor.moveToNext()) {
            int titleColumn = audioCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            do {
                String title = audioCursor.getString(titleColumn);
                arrayList.add(title);
            } while (audioCursor.moveToNext());
        }
        assert audioCursor != null;
        audioCursor.close();
        fragmentAudioFilesBinding.audioRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentAudioFilesBinding.audioRecyclerView.setAdapter(new AudioAdapter(arrayList));

        fragmentAudioFilesBinding.grantPermissionText.setVisibility(View.GONE);
    }
}

















