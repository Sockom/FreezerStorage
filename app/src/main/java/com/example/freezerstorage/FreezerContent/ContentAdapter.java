package com.example.freezerstorage.FreezerContent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freezerstorage.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder>  {
    private ArrayList<Item> mItems = new ArrayList<>();

    @NonNull
    @Override
    public ContentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contents_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentAdapter.ViewHolder cHolder, int position) {
        cHolder.contentsName.setText(mItems.get(position).getContentsName());
        cHolder.amount.setText(mItems.get(position).getAmount());
    }

    @Override
    public int getItemCount(){
        return mItems.size();
    }

    public void updateData(ArrayList<Item> items){
        this.mItems = items;
        notifyDataSetChanged();
    }

    // Clicker stuff
    public interface OnListItemClickListener{
        void onListItemClick(Item item);
    }

    final private OnListItemClickListener mOnListItemClickListener;

    ContentAdapter(ArrayList<Item> items, OnListItemClickListener listener){
        mItems = items;
        mOnListItemClickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView contentsName;
        private final TextView amount;

        @Override
        public void onClick(View view){
            mOnListItemClickListener.onListItemClick(mItems.get(getAdapterPosition()));
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contentsName = itemView.findViewById(R.id.contentnamefield);
            amount = itemView.findViewById(R.id.amountfield);
            itemView.setOnClickListener(this);
        }
    }
}
