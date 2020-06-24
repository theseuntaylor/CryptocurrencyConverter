package com.example.android.cryptocurrencyconverter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RealListItemAdapter extends Adapter<RealListItemAdapter.ViewHolder> {

    private Context mContext;
    private List<Double> mItemListAdapters;

    public RealListItemAdapter(Context context, List<Double> itemListAdapters) {

        mContext = context;
        mItemListAdapters = itemListAdapters;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RealListItemAdapter.ViewHolder realViewHolder, int i) {

        Double currentItem = mItemListAdapters.get(i);

        /**String cryptUrl = currentItem.getmUrl();
        String cryptTitle = currentItem.getmTitle();
        Double cryptAmount = currentItem.getmAmount();**/

        //realViewHolder.mTextViewTitle.setText(cryptTitle);
        realViewHolder.mTextViewAmount.setText("Amount is: " + currentItem);


    }

    @Override
    public int getItemCount() {
        return mItemListAdapters.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

       public TextView mTextViewTitle;
       public TextView mTextViewAmount;

       public ViewHolder(@NonNull View itemView) {
           super(itemView);

           mTextViewTitle = itemView.findViewById(R.id.textViewTitle);
           mTextViewAmount = itemView.findViewById(R.id.textViewAmount);

       }
   }
}
