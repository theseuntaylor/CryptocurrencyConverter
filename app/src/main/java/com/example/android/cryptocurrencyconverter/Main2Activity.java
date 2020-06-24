package com.example.android.cryptocurrencyconverter;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main2Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RealListItemAdapter mRealListItemAdapter;
    private ArrayList<ItemListAdapter> mItemListAdapater;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.spinner, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mItemListAdapater = new ArrayList<>();

        progressDialog = new ProgressDialog(Main2Activity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        parseJSON();
    }

    private void parseJSON(){
        String url = "https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=" +
                "USD,EUR,NGN,GBP,AED,SAR,JPY,INR,EGP,CNY,BRL,AUD,CAD,CHF,DKK,ISK,KES," +
                "QAR,SGD,ZAR";

        Request request = new Request.Builder()
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
                e.printStackTrace();


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonResponse = response.body().string();
                final List<Double> currencyModel = retrieveCurrencyValue(jsonResponse);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRealListItemAdapter = new RealListItemAdapter(Main2Activity.this, currencyModel);
                        mRecyclerView.setAdapter(mRealListItemAdapter);
                        progressDialog.dismiss();
                    }
                });



                Log.v("jsonResponse",  "" + currencyModel.get(0));

                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else{
                    Log.i("It worked!", "" + response   );


                }
            }
        });

    }

    private List<Double> retrieveCurrencyValue(String response){
        List<Double> currencyList = new ArrayList<>();
        try{
            JSONObject jsonObject = new JSONObject(response);
            double usd = jsonObject.optDouble("USD");
            double eur = jsonObject.optDouble("EUR");
            double ngn = jsonObject.optDouble("NGN");
            double gbp = jsonObject.optDouble("GBP");
            double aed = jsonObject.optDouble("AED");
            double sar = jsonObject.optDouble("SAR");
            double jpy = jsonObject.optDouble("JPY");
            double inr = jsonObject.optDouble("INR");
            double egp = jsonObject.optDouble("EGP");
            double cny = jsonObject.optDouble("CNY");
            double brl = jsonObject.optDouble("BRL");
            double aud = jsonObject.optDouble("AUD");
            double cad = jsonObject.optDouble("CAD");
            double chf = jsonObject.optDouble("CHF");
            double dkk = jsonObject.optDouble("DKK");
            double isk = jsonObject.optDouble("ISK");
            double kes = jsonObject.optDouble("KES");
            double qar = jsonObject.optDouble("QAR");
            double sgd = jsonObject.optDouble("SGD");
            double zar = jsonObject.optDouble("ZAR");

            currencyList.add(usd);
            currencyList.add(eur);
            currencyList.add(ngn);
            currencyList.add(gbp);
            currencyList.add(aed);
            currencyList.add(sar);
            currencyList.add(jpy);
            currencyList.add(inr);
            currencyList.add(egp);
            currencyList.add(cny);
            currencyList.add(brl);
            currencyList.add(aud);
            currencyList.add(cad);
            currencyList.add(chf);
            currencyList.add(dkk);
            currencyList.add(isk);
            currencyList.add(kes);
            currencyList.add(qar);
            currencyList.add(sgd);
            currencyList.add(zar);

            return currencyList;
        }catch (JSONException e){
               return null;
        }
    }

}
