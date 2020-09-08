package com.example.recyclerviewadmobdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter {

    private List<Object> recycleritems = new ArrayList<>();
    private Context context;

    private static final int item_image = 0;
    private static final int item_banner_ads = 1;

    public RecyclerAdapter(List<Object> recycleritems, Context context) {
        this.recycleritems = recycleritems;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType){
            case item_image:
                View imageview  = LayoutInflater.from(parent.getContext()).inflate(R.layout.images_item_layout,parent,false);
                return new ImageviewHolder(imageview);

            case  item_banner_ads:

            default:
                View bannerAdsview  = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_ads,parent,false);
                return new BannerAdsviewHolder(bannerAdsview);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int viewType = getItemViewType(position);

        switch (viewType){

            case item_image:
                ImageviewHolder imageviewHolder = (ImageviewHolder) holder;
                Image image = (Image) recycleritems.get(position);

                int imageId = context.getResources().getIdentifier(image.getImage().toLowerCase(),"drawable",context.getPackageName());

                imageviewHolder.imageView.setImageResource(imageId);
                imageviewHolder.imagetext.setText(image.getImage());
                imageviewHolder.imagevalue.setText("Value:"+ image.getImage_value());
                break;

            case item_banner_ads:


            default:

                BannerAdsviewHolder adsviewHolder = (BannerAdsviewHolder) holder;
                AdView adView = (AdView) recycleritems.get(position);

                ViewGroup adCardView = (ViewGroup) adsviewHolder.itemView;

                if(adCardView.getChildCount() > 0){
                    adCardView.removeAllViews();
                }

                if(adCardView.getParent()!= null){
                    ((ViewGroup) adView.getParent()).removeView(adView);
                }
                adCardView.addView(adView);
        }



    }

    @Override
    public int getItemCount() {
        return recycleritems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position % MainActivity.item_per_ads ==0){
            return item_banner_ads;
        } else {
            return item_image;
        }
    }

    public static  class ImageviewHolder extends RecyclerView.ViewHolder
    {

        private ImageView imageView;
        private TextView imagetext, imagevalue;

        public ImageviewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            imagetext = itemView.findViewById(R.id.image_name);
            imagevalue = itemView.findViewById(R.id.image_value);
        }
    }

    public static  class BannerAdsviewHolder extends RecyclerView.ViewHolder
    {

        public BannerAdsviewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
