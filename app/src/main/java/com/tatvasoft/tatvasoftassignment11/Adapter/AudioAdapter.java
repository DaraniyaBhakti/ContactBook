package com.tatvasoft.tatvasoftassignment11.Adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tatvasoft.tatvasoftassignment11.R;

import java.util.ArrayList;

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.ViewHolder> {

    ArrayList<String> arrayList = new ArrayList<>();

    public AudioAdapter(Context context) {

        ContentResolver audioResolver = context.getContentResolver();
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
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.audio_item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioAdapter.ViewHolder holder, int position) {
        holder.tvAudioName.setText(arrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAudioName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAudioName = itemView.findViewById(R.id.tvAudioName);
        }
    }
}
