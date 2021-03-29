package com.example.freezerstorage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FridgeAdapter extends RecyclerView.Adapter<FridgeAdapter.ViewHolder> {

    private List<Freezer> freezerList = new ArrayList<>();

    @NonNull
    @Override
    public FridgeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FridgeAdapter.ViewHolder holder, int position) {
        holder.name.setText(freezerList.get(position).getName());
        holder.id.setText(String.valueOf(freezerList.get(position).getId()));
        holder.image.setImageResource(freezerList.get(position).getImageId());
    }

    public void updateList(List<Freezer> freezerList) {
        this.freezerList = freezerList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return freezerList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView id;
        private final ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.freezer_name);
            id = itemView.findViewById(R.id.freezer_id);
            image = itemView.findViewById(R.id.freezer_image);
        }
    }
}