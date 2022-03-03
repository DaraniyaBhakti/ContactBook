package com.tatvasoft.tatvasoftassignment11.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tatvasoft.tatvasoftassignment11.Adapter.AudioAdapter;
import com.tatvasoft.tatvasoftassignment11.databinding.FragmentAudioFilesBinding;

public class AudioFilesFragment extends Fragment {

    Context context;
    FragmentAudioFilesBinding binding;

    public AudioFilesFragment(Context context) {
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
        binding = FragmentAudioFilesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        setAudioFiles();
    }

    public void setAudioFiles() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                getAudioFiles();
            }
        } else {
            getAudioFiles();
        }
    }

    public void getAudioFiles() {
        binding.audioRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.audioRecyclerView.setAdapter(new AudioAdapter(context));
    }
}

















