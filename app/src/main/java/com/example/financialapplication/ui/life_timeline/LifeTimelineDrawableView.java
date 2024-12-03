package com.example.financialapplication.ui.life_timeline;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;

import com.example.financialapplication.R;
import com.example.financialapplication.db.entity.LifeEventEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LifeTimelineDrawableView extends View {

    private Path path;
    private Paint paint;
    private TextPaint textPaint;
    private ShapeDrawable drawable;
    private ShapeDrawable drawable2;
    List<LifeEventEntity> lifeEvents;
    List<Drawable> lifeEventDrawables;
    final float scale = getResources().getDisplayMetrics().density;
    int height = toDense(10000);


    public LifeTimelineDrawableView(Context context) {
        super(context);


        int[] xArray = new int[6];
        xArray[0] = 500;
        xArray[1] = 500;
        xArray[2] = 700;
        xArray[3] = 700;
        xArray[4] = 400;
        xArray[5] = 400;

        int[] yArray = new int[6];
        yArray[0] = 30;
        yArray[1] = 930;
        yArray[2] = 1970;
        yArray[3] = 2410;
        yArray[4] = 3600;
        yArray[5] = 4800;

        path = new Path();

        paint = new Paint() {
            {
                setStyle(Paint.Style.STROKE);
                setStrokeCap(Paint.Cap.ROUND);
                setStrokeWidth(15.0f);
                setAntiAlias(true);
            }
        };

        textPaint = new TextPaint() {
            {
                setTextSize(202);
                setAntiAlias(true);
            }
        };

        for (int i = 0; i < xArray.length; i++) {
            if (i == 0) {
                path.moveTo(xArray[i], yArray[i]);
            }

            else {
                float xNew = (xArray[i] + xArray[i - 1]) / 2;
                float yNew = (yArray[i] + yArray[i - 1]) / 2;
                path.lineTo(xNew, yNew);
            }
        }



        drawable = new ShapeDrawable(new RectShape());
        drawable.getPaint().setColor(0xff74AC23);
        drawable.setBounds(0, 0, toDense(50), height);
//
//        drawable2 = new ShapeDrawable(new RectShape());
//        drawable.getPaint().setColor(0xff74AC23);

    }



    protected void onDraw(Canvas canvas) {

        canvas.drawPath(path, paint);
        drawable.draw(canvas);

        for (int i = 0; i < lifeEventDrawables.size(); i++) {

            lifeEventDrawables.get(i).draw(canvas);
//            canvas.drawBitmap(lifeEventDrawables.get(i),20, i * 40, null);
        }


//        for (int i = 0; i < 10; i++) {
//            canvas.drawText("It is lit" + i + lifeEvents.size(), 300 + i, 500 + i * 300, textPaint);
//        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // Since we imposed no vertical restrictions, this window will be as large as my
        // the desiredHeight. The parent imposed width restrictions in that the view will be as
        // wide as the parent.
        //TODO: Figure out pixel densities to make this work on many devices.



        int desiredWidth = 100;
        int desiredHeightP = 10000;

        int desiredHeight = (int) ((desiredHeightP) - 0.5f / scale);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        int width;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height);

    }

    public void setLifeEvents(List<LifeEventEntity> lifeEvents) {
        this.lifeEvents = lifeEvents;
        Resources res = getResources();
        lifeEventDrawables = new ArrayList<>();
        Context context = getContext();
        Drawable d;

        for (int i = 0; i < lifeEvents.size(); i++) {
            Log.d(TAG, "setLifeEvents: Reference:" + R.drawable.ic_dashboard_black_24dp);
            Log.d(TAG, "setLifeEvents: From DB:" + lifeEvents.get(i).getImageId());
            int yeet = lifeEvents.get(i).getImageId();
            if (yeet == 12) {
                yeet = 2131165316;
            }

            d = context.getResources().getDrawable(yeet);
            d.setBounds(50,0 + 100 * i, 100,100 + 100 * i);
            lifeEventDrawables.add(d);
        }


    }

    private int toDense(int desiredPixel) {
        return (int) ((desiredPixel) - 0.5f / scale);
    }
}
