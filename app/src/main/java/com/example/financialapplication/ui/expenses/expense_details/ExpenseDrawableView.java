package com.example.financialapplication.ui.expenses.expense_details;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.text.TextPaint;
import android.view.View;

public class ExpenseDrawableView extends View {
    private Path path;
    private Paint paint;
    private TextPaint textPaint;
    private ShapeDrawable drawable;
    private ShapeDrawable drawable2;

    public ExpenseDrawableView(Context context) {
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
                setStyle(Style.STROKE);
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
        drawable.setBounds(0, 100, MeasureSpec.EXACTLY, 120);

        drawable2 = new ShapeDrawable(new RectShape());
        drawable.getPaint().setColor(0xff74AC23);

    }

    protected void onDraw(Canvas canvas) {

        canvas.drawPath(path, paint);
        drawable.draw(canvas);


        for (int i = 0; i < 10; i++) {
            canvas.drawText("It is lit" + i, 300 + i, 500 + i * 300, textPaint);
        }
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
