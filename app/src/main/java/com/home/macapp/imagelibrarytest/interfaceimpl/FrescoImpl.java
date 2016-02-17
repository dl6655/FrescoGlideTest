package com.home.macapp.imagelibrarytest.interfaceimpl;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.home.macapp.imagelibrarytest.activity.LinearLayoutTest;
import com.home.macapp.imagelibrarytest.myinterface.IDisplayImage;

/**
 * Created by Liao on 16/1/25.
 */
public class FrescoImpl implements IDisplayImage {

    @Override
    public ImageView displayImageView(Context context, String url) {
//        ImageView imageView = null;
        SimpleDraweeView imageView = new SimpleDraweeView(context);
        imageView.setLayoutParams(LinearLayoutTest.mLayoutParams);
        imageView.setImageURI(Uri.parse(url));


//        ImageRequest request = ImageRequestBuilder
//                .newBuilderWithSource(Uri.parse(url))
//                .setProgressiveRenderingEnabled(true)
//                .build();
//        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
//                .setImageRequest(request)
//                .setOldController(imageView.getController())
//                .build();
//        imageView.setController(controller);


        return imageView;
    }
}
