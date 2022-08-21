package com.fitrizuyinanurazizah.aplikasikas;

import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private List<SurfaceControl.Transaction> items;
    private OnItemTransactionListener listener;

    public interface OnItemTransactionListener {
        void onTransactionClicked(int index, SurfaceControl.Transaction item);
    }

    public TransactionAdapter(List<SurfaceControl.Transaction> items, OnItemTransactionListener listener) {
        this.items = items;
        this.listener = listener;
    }


    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder holder, int position) {
        SurfaceControl.Transaction item = items.get(position);
        holder.bind(position, item);
    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView descriptionText;
        TextView amountText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionText = itemView.findViewById(R.id.txt_deskripsi);
            amountText = itemView.findViewById(R.id.txt_amount);
        }
        public void bind(final int index, final SurfaceControl.Transaction item) {
            descriptionText.setText(item.getDescription());
            amountText.setText(String.valueOf(item.getAmount()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTransactionClicked(index, item);
                }
            });
        }
    }

}
