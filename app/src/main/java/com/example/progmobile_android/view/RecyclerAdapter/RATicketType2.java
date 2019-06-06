package com.example.progmobile_android.view.RecyclerAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.entities.Pair;
import com.example.progmobile_android.model.entities.TicketType;

import java.util.ArrayList;
import java.util.List;

public class RATicketType2 extends RecyclerView.Adapter<RATicketType2.ViewHolder> {

    private List<TicketType> ticketTypes;
    private List<Pair> pairList;

    public RATicketType2(List<TicketType> ticketTypes, List<Pair> pairList) {
        this.ticketTypes = ticketTypes;
        this.pairList = pairList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comp_ticket_type_2,
                viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TicketType ticketType = ticketTypes.get(i);
        Pair pair = pairList.get(i);

        viewHolder.tvTicketType.setText(ticketType.getName());
        viewHolder.tvTicketPrice.setText(String.format("%s", ticketType.getPrice()));
        viewHolder.tvTicketAmount.setText(String.format("%s", pair.getAmount()));

        double totalPrice = pair.getAmount() * ticketType.getPrice();
        viewHolder.tvTotalPrice.setText(String.format("%s", totalPrice));

    }

    @Override
    public int getItemCount() {
        return ticketTypes.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTicketType;
        private TextView tvTicketPrice;
        private TextView tvTicketAmount;
        private TextView tvTotalPrice;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTicketType = itemView.findViewById(R.id.tvTicketType);
            tvTicketPrice = itemView.findViewById(R.id.tvTicketPrice);
            tvTicketAmount = itemView.findViewById(R.id.tvTicketAmount);
            tvTotalPrice = itemView.findViewById(R.id.tvTotalPrice);
        }
    }

}
