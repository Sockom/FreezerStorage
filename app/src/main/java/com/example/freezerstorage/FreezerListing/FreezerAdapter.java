package com.example.freezerstorage.FreezerListing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freezerstorage.R;

import java.util.ArrayList;
import java.util.List;

public class FreezerAdapter extends RecyclerView.Adapter<FreezerAdapter.ViewHolder> {
    private final ArrayList<Freezer> mFreezers;

    @NonNull
    @Override
    public FreezerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.freezer_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FreezerAdapter.ViewHolder holder, int position) {
        holder.name.setText(mFreezers.get(position).getName());
    }

    @Override
    public int getItemCount(){
        return mFreezers.size();
    }

    public void updateData(List<Freezer> freezers){
        notifyDataSetChanged();
    }


    // Clicker stuff
    public interface OnListItemClickListener{
        void onListItemClick(Freezer freezer);
    }

    final private OnListItemClickListener mOnListItemClickListener;

    FreezerAdapter(ArrayList<Freezer> freezers, OnListItemClickListener listener){
        mFreezers = freezers;
        mOnListItemClickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView name;

        @Override
        public void onClick(View view){
            mOnListItemClickListener.onListItemClick(mFreezers.get(getAdapterPosition()));

        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.freezer_name);
            itemView.setOnClickListener(this);
        }
    }



}

