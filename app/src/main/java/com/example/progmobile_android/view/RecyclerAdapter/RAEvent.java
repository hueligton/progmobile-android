package com.example.progmobile_android.view.RecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.entities.Event;
import com.example.progmobile_android.model.manager.Constants;
import com.example.progmobile_android.view.EventDetails;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

public class RAEvent extends RecyclerView.Adapter<RAEvent.ViewHolder> {

    private List<Event> events;
    private Context context;

    public RAEvent(List<Event> events, Context context) {
        this.events = events;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comp_event,
                viewGroup, false);
        return new ViewHolder(view, events, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Event event = events.get(position);
        Date date = event.getDate();
        String url = event.getImageUrl();

        viewHolder.ivEventImage.setContentDescription(event.getDescription());
        Picasso.get().load(Constants.URL + url).into(viewHolder.ivEventImage);
        viewHolder.tvEventDate.setText(new SimpleDateFormat("dd.MM.yyyy").format(date.getTime()));
        viewHolder.tvEventName.setText(event.getName());
        viewHolder.tvEventTime.setText(new SimpleDateFormat("HH:mm").format(date.getTime()));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView ivEventImage;
        private TextView tvEventDate;
        private TextView tvEventName;
        private TextView tvEventTime;

        private List<Event> events;
        private Context context;

        ViewHolder(@NonNull View itemView, List<Event> events, Context context) {
            super(itemView);
            ivEventImage = itemView.findViewById(R.id.ivEventImage);
            tvEventDate = itemView.findViewById(R.id.tvEventDate);
            tvEventName = itemView.findViewById(R.id.tvEventName);
            tvEventTime = itemView.findViewById(R.id.tvEventTime);

            this.events = events;
            this.context = context;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, EventDetails.class);
            intent.putExtra("event_id", events.get(getAdapterPosition()).getId());
            context.startActivity(intent);
        }
    }
}
