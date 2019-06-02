package com.example.progmobile_android.view.RecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.progmobile_android.R;
import com.example.progmobile_android.view.EventDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.EventViewHolder> {
    private List<Integer> ids;
    private List<String> images;
    private List<String> names;

    private Context context;

    public EventRecyclerAdapter(List<Integer> ids, List<String> images, List<String> names, Context context) {
        this.ids = ids;
        this.images = images;
        this.names = names;
        this.context = context;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.component_event,
                viewGroup, false);
        return new EventViewHolder(view, ids, context);
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

    static class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView eventImage;
        private TextView eventName;
        private List<Integer> ids;
        private Context context;

        EventViewHolder(@NonNull View itemView, List<Integer> ids, Context context) {
            super(itemView);
            eventImage = itemView.findViewById(R.id.event_image);
            eventName = itemView.findViewById(R.id.event_name);
            itemView.setOnClickListener(this);
            this.ids = ids;
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, EventDetails.class);
            intent.putExtra("event_id", ids.get(getAdapterPosition()));
            context.startActivity(intent);
        }
    }
}
