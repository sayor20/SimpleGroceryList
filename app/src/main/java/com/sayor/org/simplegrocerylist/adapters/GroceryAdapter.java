package com.sayor.org.simplegrocerylist.adapters;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sayor.org.simplegrocerylist.R;
import com.sayor.org.simplegrocerylist.models.Grocery;

import java.util.List;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryHolder> {

    private List<Grocery> mGroceries;
    String qty=null;

    public GroceryAdapter(List<Grocery> Groceries){
        this.mGroceries = Groceries;
    }

    @Override
    public GroceryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new GroceryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final GroceryHolder holder, final int position) {
        final Grocery grocery = mGroceries.get(position);
        holder.tvName.setText(grocery.getName());
        holder.tvDesc.setText(grocery.getDesc());
        String qty = grocery.getQty()==-1?"":grocery.getQty()+"";
        holder.tvQty.setText("qty : "+ qty);
        holder.cbDone.setChecked(grocery.isDone());
        if(grocery.isDone()){
            //Add strike through, set text to gray
            holder.tvName.setPaintFlags(holder.tvName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvDesc.setPaintFlags(holder.tvDesc.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvQty.setPaintFlags(holder.tvQty.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvName.setTextColor(Color.GRAY);
            holder.tvDesc.setTextColor(Color.GRAY);
            holder.tvQty.setTextColor(Color.GRAY);
        } else {
            //Remove strike through, set text to black
            holder.tvName.setPaintFlags(holder.tvName.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvDesc.setPaintFlags(holder.tvDesc.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvQty.setPaintFlags(holder.tvQty.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvName.setTextColor(Color.BLACK);
            holder.tvDesc.setTextColor(Color.BLACK);
            holder.tvQty.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return mGroceries.size();
    }
}
