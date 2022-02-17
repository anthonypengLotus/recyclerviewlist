package com.example.myapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import android.view.View;


import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.myapplication.R;

import java.lang.reflect.Field;

/**
 * @author: pengweidong
 */
public class IndicatorDrawable extends Drawable {
    private  final int INDICATOR_MARGIN ;
    private  final int INDICATOR_HEIGHT ;
    private  final int INDICATOR_RADIUS ;



    private View view;
    private Paint paint;
    private Context context;

    public IndicatorDrawable(Context context, View view) {
        this.context= context;
        this.view = view;
        this.paint = new Paint();
        paint.setColor(view.getContext().getResources().getColor(R.color.blue_7799fd));

        INDICATOR_MARGIN = dip2px(10);//24
        INDICATOR_HEIGHT = dip2px(3);
        INDICATOR_RADIUS = dip2px(1.5f);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        //最新API
        int mIndicatorLeft = getIntValue("indicatorLeft");
        int mIndicatorRight = getIntValue("indicatorRight");
        //旧版API
//        int oldIndicatorLeft = getIntValue("mIndicatorLeft");
//        int oldIndicatorRight = getIntValue("mIndicatorRight");
        int radius = INDICATOR_RADIUS;
        if (mIndicatorLeft >= 0 && mIndicatorRight > mIndicatorLeft) {
            canvas.drawRoundRect(new RectF(mIndicatorLeft + INDICATOR_MARGIN, view.getHeight()-view.getPaddingBottom() - INDICATOR_HEIGHT, mIndicatorRight - INDICATOR_MARGIN, view.getHeight()-view.getPaddingBottom()), radius, radius, paint);
        }
    }

    private int getIntValue(String name) {
        try {
            Field f = view.getClass().getDeclaredField(name);
            f.setAccessible(true);
            Object obj = f.get(view);
            return (Integer) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

    public int dip2px(float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
