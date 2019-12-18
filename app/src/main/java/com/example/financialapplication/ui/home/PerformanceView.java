package com.example.financialapplication.ui.home;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.Log;
import android.view.View;

import com.example.financialapplication.R;

public class PerformanceView extends View {
    private ShapeDrawable drawable;
    private Path path;
    private Paint paint;

    public PerformanceView(Context context) {
        super(context);

        int x = 10;
        int y = 10;
        int width = 3000;
        int height = 5000;
        setContentDescription(context.getResources().getString(R.string.my_view_desc));

        drawable = new ShapeDrawable(new OvalShape());
        drawable.getPaint().setColor(0xff74AC23);
        drawable.setBounds(x, y, x + width, y + height);


        paint = new Paint() {
            {
                setStyle(Style.STROKE);
                setStrokeCap(Paint.Cap.ROUND);
                setStrokeWidth(3.0f);
                setAntiAlias(true);
            }
        };

        int centerX = 500;
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
//        path.moveTo(centerX, y);
//        final float x2 = (520 + centerX) / 2;
//        final float y2 = (500 + y) / 2;
//        path.quadTo(x2, y2, 520, 500);
//        final float x3 = (600 + centerX) / 2;
//        final float y3 = (900 + y) / 2;
//        path.quadTo(x3, y3, 600, 900);

        for (int i = 0; i < xArray.length; i++) {
            if (i == 0) {
               path.moveTo(xArray[i], yArray[i]);
            }

            else {
                float xNew = (xArray[i] + xArray[i - 1]) / 2;
//                float xNew = xArray[i];
//                float yNew = yArray[i];
                float yNew = (yArray[i] + yArray[i - 1]) / 2;
                path.lineTo(xNew, yNew);

            }
        }

    }

    protected void onDraw(Canvas canvas) {
        drawable.draw(canvas);
        canvas.drawPath(path, paint);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int desiredWidth = 100;
        int desiredHeight = 10000;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

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


}
