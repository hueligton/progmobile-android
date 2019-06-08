package com.example.progmobile_android.view.RecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.entity.Purchase;
import com.example.progmobile_android.view.PurchaseDetails;

import java.util.Date;
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
        Date date = purchase.getDate();


        viewHolder.tvPurchaseDate.setText(new SimpleDateFormat("dd.MM.yyyy").format(date.getTime()));
        viewHolder.tvPurchaseId.setText(String.format("%s", purchase.getId()));
        viewHolder.tvPurchaseStatus.setText(String.format("%s", purchase.getPayment_status()));
        viewHolder.tvPurchaseTime.setText(new SimpleDateFormat("HH:mm").format(date.getTime()));
        viewHolder.tvPurchaseValue.setText(String.format("%s", purchase.getTotal_value()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvPurchaseDate;
        private TextView tvPurchaseId;
        private TextView tvPurchaseStatus;
        private TextView tvPurchaseTime;
        private TextView tvPurchaseValue;

        private List<Purchase> list;
        private Context context;

        ViewHolder(@NonNull View itemView, List<Purchase> list, Context context) {
            super(itemView);

            this.tvPurchaseDate = itemView.findViewById(R.id.tvPurchaseDate);
            this.tvPurchaseId = itemView.findViewById(R.id.tvPurchaseId);
            this.tvPurchaseStatus = itemView.findViewById(R.id.tvPurchaseStatus);
            this.tvPurchaseTime = itemView.findViewById(R.id.tvPurchaseTime);
            this.tvPurchaseValue = itemView.findViewById(R.id.tvPurchaseValue);
            this.list = list;
            this.context = context;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, PurchaseDetails.class);
            intent.putExtra("purchase", list.get(getAdapterPosition()));
            context.startActivity(intent);
        }
    }
}
