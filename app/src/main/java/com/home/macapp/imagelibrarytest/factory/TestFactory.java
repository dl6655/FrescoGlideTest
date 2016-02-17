package com.home.macapp.imagelibrarytest.factory;

import android.content.Context;
import android.widget.ImageView;

import com.home.macapp.imagelibrarytest.interfaceimpl.FrescoImpl;
import com.home.macapp.imagelibrarytest.interfaceimpl.GlideImpl;
import com.home.macapp.imagelibrarytest.interfaceimpl.PicassoIml;
import com.home.macapp.imagelibrarytest.myinterface.IDisplayImage;

/**
 * Created by Liao on 16/1/25.
 */
public class TestFactory {
    static PicassoIml picassoImpl;

    static GlideImpl glideImpl;
    static FrescoImpl frescoImpl;

    public static IDisplayImage getPicassoImpl() {
        if (picassoImpl == null) {
            synchronized (Object.class) {
                if (picassoImpl == null) {
                    picassoImpl = new PicassoIml();
                }
            }
        }
        return picassoImpl;
    }

    public static IDisplayImage getGlideImpl() {
        if (glideImpl == null) {
            synchronized (Object.class) {
                if (glideImpl == null) {
                    glideImpl = new GlideImpl();
                }
            }
        }
        return glideImpl;
    }

    public static IDisplayImage getFresscoImpl() {
        if (frescoImpl == null) {
            synchronized (Object.class) {
                if (frescoImpl == null) {
                    frescoImpl = new FrescoImpl();
                }
            }
        }
        return frescoImpl;
    }

    public static IDisplayImage getErrorImpl() {

        return new IDisplayImage() {
            @Override
            public ImageView displayImageView(Context context, String url) {
                return null;
            }
        };
    }
}
