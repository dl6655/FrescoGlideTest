package com.home.macapp.imagelibrarytest.interfaceimpl;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.home.macapp.imagelibrarytest.activity.LinearLayoutTest;
import com.home.macapp.imagelibrarytest.myinterface.IDisplayImage;

/**
 * Created by Liao on 16/1/25.
 */
public class GlideImpl implements IDisplayImage {
    @Override
    public ImageView displayImageView(Context context, String url) {
        ImageView imageView = LinearLayoutTest.InnerImageViewFactory.getImageView(context);
        Glide.with(context).load(url).into(imageView);
        return imageView;
    }
}
