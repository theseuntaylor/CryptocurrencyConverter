package com.example.android.cryptocurrencyconverter;

import java.util.ArrayList;

public class ItemListAdapter {

    private String mUrl;
    private String mTitle;
    private Double mAmount;

    public ItemListAdapter(String Url, String Title, Double Amount) {
        mUrl = Url;
        mTitle = Title;
        mAmount = Amount;

    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public Double getmAmount() {
        return mAmount;
    }
}
