package com.example.progmobile_android.view.RecyclerAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.entity.Ticket;

import java.util.List;

public class RATicketType3 extends RecyclerView.Adapter<RATicketType3.ViewHolder> {

    private List<Ticket> list;

    public RATicketType3(List<Ticket> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comp_ticket_type_3,
                viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Ticket ticket = list.get(i);
        viewHolder.tvTicketPrice.setText(String.format("%s", ticket.getTicketType().getPrice()));
        viewHolder.tvTicketType.setText(ticket.getTicketType().getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTicketPrice;
        private TextView tvTicketType;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTicketPrice = itemView.findViewById(R.id.tvTicketPrice);
            tvTicketType = itemView.findViewById(R.id.tvTicketType);
        }
    }
}
