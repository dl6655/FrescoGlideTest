package com.home.macapp.imagelibrarytest.interfaceimpl;

import android.content.Context;
import android.widget.ImageView;

import com.home.macapp.imagelibrarytest.activity.LinearLayoutTest;
import com.home.macapp.imagelibrarytest.myinterface.IDisplayImage;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by Liao on 16/1/25.
 */
public class PicassoIml implements IDisplayImage {
    @Override
    public ImageView displayImageView(Context context, String url) {
        ImageView imageView = LinearLayoutTest.InnerImageViewFactory.getImageView(context);
        Picasso picasso=new Picasso.Builder(context).build();
        picasso.shutdown();
        Picasso.with(context).load(url).fit().centerCrop().into(imageView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });
        return imageView;
    }
}
