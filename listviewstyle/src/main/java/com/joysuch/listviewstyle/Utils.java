package com.joysuch.listviewstyle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by admin2017 on 2017/9/22.
 */

public class Utils {

    public static Drawable getDrawable(Context context ,String picName) throws IOException {
        InputStream is = context.getAssets().open(picName);
        if(is == null) {
            return null;
        } else {
            Drawable drawable = Drawable.createFromStream(is, (String)null);
            is.close();
            return drawable;
        }
    }
}
