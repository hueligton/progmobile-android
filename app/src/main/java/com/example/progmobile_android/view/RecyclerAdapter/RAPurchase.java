package com.example.progmobile_android.view.RecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.entities.Purchase;
import com.example.progmobile_android.view.PurchaseDetails;

import java.util.List;

public class RAPurchase extends RecyclerView.Adapter<RAPurchase.ViewHolder> {

    private List<Purchase> list;
    private Context context;

    public RAPurchase(List<Purchase> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.comp_purcharse, viewGroup, false);
        return new ViewHolder(view, list, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Purchase purchase = list.get(i);
        viewHolder.tvPurchaseId.setText(String.format("%s", purchase.getId()));
        viewHolder.tvPurchaseDate.setText(String.format("%s", purchase.getDate()));
        viewHolder.tvPurchaseValue.setText(String.format("%s", purchase.getValue()));
        viewHolder.tvPurchaseStatus.setText(String.format("%s", purchase.getPaymentStatus()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvPurchaseId;
        private TextView tvPurchaseDate;
        private TextView tvPurchaseValue;
        private TextView tvPurchaseStatus;
        private List<Purchase> list;
        private Context context;

        ViewHolder(@NonNull View itemView, List<Purchase> list, Context context) {
            super(itemView);

            this.tvPurchaseId = itemView.findViewById(R.id.tvPurchaseId);
            this.tvPurchaseDate = itemView.findViewById(R.id.tvPurchaseDate);
            this.tvPurchaseValue = itemView.findViewById(R.id.tvPurchaseValue);
            this.tvPurchaseStatus = itemView.findViewById(R.id.tvPurchaseStatus);
            this.list = list;
            this.context = context;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, PurchaseDetails.class);
            intent.putExtra("purchase_id", list.get(getAdapterPosition()).getId());
            context.startActivity(intent);
        }
    }
}
