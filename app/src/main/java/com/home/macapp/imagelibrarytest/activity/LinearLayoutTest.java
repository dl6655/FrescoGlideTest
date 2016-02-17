package com.home.macapp.imagelibrarytest.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.home.macapp.imagelibrarytest.R;
import com.home.macapp.imagelibrarytest.factory.TestFactory;
import com.home.macapp.imagelibrarytest.myinterface.IDisplayImage;
import com.home.macapp.imagelibrarytest.other.ImageUrl;

import java.util.List;

public class LinearLayoutTest extends AppCompatActivity {

    public static final String WHITCH_TYPE = "whitch_type";
    public static String PICASSO = "picasso";
    public static String FRESCO = "fresco";
    public static String GLIDE = "glide";

    private LinearLayout mLinearLayout;
    private List<String> mImageUrls;
    public static LinearLayout.LayoutParams mLayoutParams;
    private String mTYPE;
    private IDisplayImage mDisplayImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout_test);
        initView();
        initDate();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int height = (int) (400 * displayMetrics.density);
        mLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        displayImageView();

    }

    private void initDate() {

        Intent intent = getIntent();
        mTYPE = intent.getStringExtra(WHITCH_TYPE);
        mImageUrls = ImageUrl.imageList();

        if (mTYPE.equals(PICASSO)) {
            mDisplayImage = TestFactory.getPicassoImpl();
        } else if (mTYPE.equals(FRESCO)) {
            mDisplayImage = TestFactory.getFresscoImpl();
        } else if (mTYPE.equals(GLIDE)) {
            mDisplayImage = TestFactory.getGlideImpl();
        } else {
            mDisplayImage = TestFactory.getErrorImpl();
        }
        int memoryClass = ((ActivityManager) getSystemService(ACTIVITY_SERVICE)).getMemoryClass();
    }

    private void initView() {
        mLinearLayout = (LinearLayout) findViewById(R.id.main_TestLayout);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void displayImageView() {
        for (String imgUrl : mImageUrls) {
//            ImageView imageView = TestFactory.getPicassoImpl().displayImageView(this, imgUrl);
//            ImageView imageView = TestFactory.getGlideImpl().displayImageView(this, imgUrl);
            ImageView imageView = mDisplayImage.displayImageView(this, imgUrl);
            mLinearLayout.addView(imageView, mLayoutParams);
            mLinearLayout.invalidate();
        }
    }

    public static class InnerImageViewFactory {
        public static ImageView getImageView(Context context) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(mLayoutParams);
            return imageView;
        }

    }
}
