package com.example.progmobile_android.view.RecyclerAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.entities.Pair;
import com.example.progmobile_android.model.entities.TicketType;

import java.util.ArrayList;
import java.util.List;

public class TicketTypeRecyclerAdapter extends RecyclerView.Adapter<TicketTypeRecyclerAdapter.TicketTypeViewHolder> {

    private List<TicketType> ticketTypes;
    private static List<Pair> informations;

    public TicketTypeRecyclerAdapter(List<TicketType> ticketTypes) {
        this.ticketTypes = ticketTypes;

        /* To test categories --------------------------------------------------------------------*/
        this.ticketTypes = new ArrayList<>();

        TicketType ticketType1 = new TicketType(1, "Pista", 100.00);
        TicketType ticketType2 = new TicketType(2, "Camarote", 200.00);
        TicketType ticketType3 = new TicketType(3, "Area VIP", 400.00);

        this.ticketTypes.add(ticketType1);
        this.ticketTypes.add(ticketType2);
        this.ticketTypes.add(ticketType3);
        /* To test categories --------------------------------------------------------------------*/

        informations = new ArrayList<>();

        this.ticketTypes.forEach(ticketType -> {
            Pair pair = new Pair(ticketType.getId(), 0);
            informations.add(pair);
        });

    }

    public List<Pair> getInformations() {
        return informations;
    }

    @NonNull
    @Override
    public TicketTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.component_type_ticket, viewGroup, false);
        return new TicketTypeViewHolder(view, ticketTypes, informations);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketTypeViewHolder viewHolder, int position) {
        TicketType ticketType = ticketTypes.get(position);

        viewHolder.tvTicketType.setText(ticketType.getType());
        viewHolder.tvTicketPrice.setText(String.format("%s", ticketType.getPrice()));
        viewHolder.tvTicketAmount.setText("0");
    }

    @Override
    public int getItemCount() {
        return ticketTypes.size();
    }


    static class TicketTypeViewHolder extends RecyclerView.ViewHolder {
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

        TicketTypeViewHolder(@NonNull View itemView, List<TicketType> ticketTypes, List<Pair> informations) {
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
