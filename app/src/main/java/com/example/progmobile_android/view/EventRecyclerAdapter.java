package com.example.progmobile_android.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.progmobile_android.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.EventViewHolder> {
    private List<String> images;
    private List<String> names;

    EventRecyclerAdapter(List<String> images, List<String> names) {
        this.images = images;
        this.names = names;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_layout,
                viewGroup, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder viewHolder, int position) {
        String url = images.get(position);
        Picasso.get().load(url).into(viewHolder.eventImage);
        viewHolder.eventName.setText(names.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {
        private ImageView eventImage;
        private TextView eventName;

        EventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventImage = itemView.findViewById(R.id.event_image);
            eventName = itemView.findViewById(R.id.event_name);
        }
    }
}
