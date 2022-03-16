package com.tatvasoft.tatvasoftassignment11.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tatvasoft.tatvasoftassignment11.databinding.AudioItemRowBinding;

import java.util.ArrayList;

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.ViewHolder> {

    ArrayList<String> arrayList;

    public AudioAdapter(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        AudioItemRowBinding binding = AudioItemRowBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioAdapter.ViewHolder holder, int position) {
        holder.audioItemRowBinding.tvAudioName.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final AudioItemRowBinding audioItemRowBinding;
        public ViewHolder(@NonNull AudioItemRowBinding audioItemRowBinding) {
            super(audioItemRowBinding.getRoot());
            this.audioItemRowBinding = audioItemRowBinding;

        }
    }
}
