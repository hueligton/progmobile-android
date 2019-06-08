package com.example.progmobile_android.view.RecyclerAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.entity.Pair;
import com.example.progmobile_android.model.entity.TicketType;

import java.util.ArrayList;
import java.util.List;

public class RATicketType1 extends RecyclerView.Adapter<RATicketType1.ViewHolder> {

    private List<TicketType> ticketTypes;
    private static List<Pair> information;

    public RATicketType1(List<TicketType> ticketTypes) {
        this.ticketTypes = ticketTypes;

        information = new ArrayList<>();

        this.ticketTypes.forEach(ticketType -> {
            Pair pair = new Pair(ticketType.getId(), 0);
            information.add(pair);
        });

    }

    public List<Pair> getInformations() {
        return information;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.comp_ticket_type_1, viewGroup, false);
        return new ViewHolder(view, ticketTypes, information);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        TicketType ticketType = ticketTypes.get(position);

        viewHolder.tvTicketType.setText(ticketType.getName());
        viewHolder.tvTicketPrice.setText(String.format("%s", ticketType.getPrice()));
        viewHolder.tvTicketAmount.setText("0");
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
        private Button btLess;
        private Button btMore;

        private List<TicketType> ticketTypes;
        private List<Pair> informations;

        private Pair pair;
        private double ticketPrice;

        ViewHolder(@NonNull View itemView, List<TicketType> ticketTypes, List<Pair> informations) {
            super(itemView);
            tvTicketType = itemView.findViewById(R.id.tvTicketType);
            tvTicketPrice = itemView.findViewById(R.id.tvTicketPrice);
            tvTicketAmount = itemView.findViewById(R.id.tvTicketAmount);
            tvTotalPrice = itemView.findViewById(R.id.tvTotalPrice);
            btLess = itemView.findViewById(R.id.btLess);
            btMore = itemView.findViewById(R.id.btMore);

            this.ticketTypes = ticketTypes;
            this.informations = informations;

            btLess.setOnClickListener(v -> {
                ticketPrice = this.ticketTypes.get(getAdapterPosition()).getPrice();
                pair = this.informations.get(getAdapterPosition());
                if (pair.getAmount() > 0) {
                    pair.setAmount(pair.getAmount() - 1);
                    tvTicketAmount.setText(String.format("%s", pair.getAmount()));
                    tvTotalPrice.setText(String.format("%s", pair.getAmount() * ticketPrice));
                }
            });

            btMore.setOnClickListener(v -> {
                ticketPrice = this.ticketTypes.get(getAdapterPosition()).getPrice();
                pair = this.informations.get(getAdapterPosition());
                pair.setAmount(pair.getAmount() + 1);
                tvTicketAmount.setText(String.format("%s", pair.getAmount()));
                tvTotalPrice.setText(String.format("%s", pair.getAmount() * ticketPrice));
            });
        }

    }
}
