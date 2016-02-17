package com.home.macapp.imagelibrarytest;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.home.macapp.imagelibrarytest.activity.LinearLayoutTest;
import com.home.macapp.imagelibrarytest.other.ImageUrl;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String PICASSO_CACHE = "picasso-cache";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openLinearLayoutTest(View view) {
        Intent intent = new Intent(this, LinearLayoutTest.class);
        switch (view.getId()) {
            case R.id.btn_fresco:
                intent.putExtra(LinearLayoutTest.WHITCH_TYPE, LinearLayoutTest.FRESCO);
                break;
            case R.id.btn_glide:
                intent.putExtra(LinearLayoutTest.WHITCH_TYPE, LinearLayoutTest.GLIDE);
                break;
            case R.id.btn_picasso:
                intent.putExtra(LinearLayoutTest.WHITCH_TYPE, LinearLayoutTest.PICASSO);
                break;
        }
        startActivity(intent);
    }

    public void clearCache(View view) {
        Glide.get(this).clearMemory();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                clearDisCache();
            }
        });
        thread.start();
    }

    private void clearDisCache() {
        //--------Glide----------------
        Glide glide = Glide.get(this);
        glide.clearDiskCache();

        //--------picasso---------------
        File cacheDir = createDefaultCacheDir(this);
        try {
            deleteImage(cacheDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> strings = ImageUrl.imageList();
        for (String url : strings) {
            Picasso.with(this).invalidate(Uri.parse(url));
        }
        //--------Fresco----------------------
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.clearCaches();
    }

    public void deleteImage(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                deleteImage(file1);
            }
        } else {
            file.delete();
        }
    }

    File createDefaultCacheDir(Context context) {
        File cache = new File(context.getApplicationContext().getCacheDir(), PICASSO_CACHE);
        if (!cache.exists()) {
            //noinspection ResultOfMethodCallIgnored
            cache.mkdirs();
        }
        return cache;
    }


}
