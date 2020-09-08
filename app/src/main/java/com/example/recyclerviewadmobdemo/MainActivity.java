package com.example.recyclerviewadmobdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public  static final  int item_per_ads = 4;
    private static final String banner_id = "ca-app-pub-3940256099942544/6300978111";
    private List<Object> recycleritem = new ArrayList<>();

    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this , "ca-app-pub-3940256099942544~3347511713");

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        getimage();
        getBannerAds();
        loadbannerAds();

        adapter = new RecyclerAdapter(recycleritem,this);
        recyclerView.setAdapter(adapter);
    }

    private void getimage(){
        List<String> imagename = Arrays.asList(getResources().getStringArray(R.array.Images));
        List<String> imagevalue = Arrays.asList(getResources().getStringArray(R.array.Images_values));

        int count = 0;
        for(String imagenames : imagename){
            recycleritem.add(new Image(imagenames, imagevalue.get(count)));
            count++;
        }
    }

    private void getBannerAds(){

        for(int i =0; i<recycleritem.size(); i+=item_per_ads){
            final AdView adView = new AdView(MainActivity.this);
            adView.setAdSize(AdSize.BANNER);
            adView.setAdUnitId(banner_id);
            recycleritem.add(i,adView);
        }
    }

    @SuppressLint("MissingPermission")
    private  void loadbannerAds(){
        for(int i = 0; i<recycleritem.size();i++){
            Object item = recycleritem.get(i);
            if(item instanceof AdView){
                final AdView adView = (AdView) item;
                adView.loadAd(new AdRequest.Builder().build());
            }
        }
    }


}