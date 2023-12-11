package com.example.contador.rvstuff;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contador.R;

import java.util.List;

public class ItemRankingAdapter extends RecyclerView.Adapter<ItemRankingAdapter.ViewHolder> {

    List<ItemRanking> modelList;

    public ItemRankingAdapter(List<ItemRanking> modelList){
        this.modelList = modelList;
    }
    @NonNull
    @Override
    public ItemRankingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ranking, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRankingAdapter.ViewHolder holder, int position) {
        holder.textoRanking.setText(modelList.get(position).getName());
        holder.imageRanking.setImageResource(modelList.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView textoRanking;
        private final ImageView imageRanking;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textoRanking = itemView.findViewById(R.id.textoRanking);
            imageRanking = itemView.findViewById(R.id.imagenRanking);
        }
    }
}
