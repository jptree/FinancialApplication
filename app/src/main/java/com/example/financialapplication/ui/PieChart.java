package com.example.financialapplication.ui;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.financialapplication.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class PieChart extends View {
    Paint piePaint;
    Path ctx = new Path();
    int viewWidth, viewHeight;
    List<Path> slices = new ArrayList<>();
    List<Region> regions = new ArrayList<>();
    Float[] data = new Float[5];
    String expenseText;
    Paint textPaint;
//    LinearGradient linearGradient;

    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    private void init() {
        piePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        piePaint.setStyle(Paint.Style.FILL);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(20 * getResources().getDisplayMetrics().density);
        textPaint.setTextAlign(Paint.Align.CENTER);


        float xpad = (float) (getPaddingLeft() + getPaddingRight());
        float ypad = (float) (getPaddingTop() + getPaddingBottom());

        float ww = (float) getWidth() - xpad;
        float hh = (float) getHeight() - ypad;

        float diameter = Math.min(ww, hh);


        data[0] = 25F;
        data[1] = 15F;
        data[2] = 65F;
        data[3] = 35F;
        data[4] = 15F;

        int radius = Math.min(viewWidth, viewHeight) / 2;



        float totalSum = 0;
        for (int i = 0; i < data.length; i++) {
            totalSum += data[i];
        }


        float runningTotal = 0;
        float pX = radius;
        float pY = 0;


        float centerY = viewHeight / 2;
        float centerX = viewWidth / 2;

        for (int i = 0; i < data.length; i++) {
            Path slice = new Path();
            slice.moveTo(centerX, centerY); // Center
            slice.lineTo(centerX + pX,centerY + pY); // line out
            slice.arcTo(new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius),
                    runningTotal / totalSum * 360, (data[i] / totalSum) * 360); //B-C arc

            slice.close();
            slices.add(slice);

            runningTotal += data[i];
            pY = radius * (float) Math.sin(Math.toRadians((runningTotal / totalSum) * 360));
            pX = radius * (float) Math.cos(Math.toRadians((runningTotal / totalSum) * 360));

            RectF rectF = new RectF();
            slice.computeBounds(rectF, true);
            Region r = new Region();
            r.setPath(slice, new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));
            regions.add(r);
        }

        expenseText = "Total spent: " + totalSum;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int minw = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
        int w = resolveSizeAndState(minw, widthMeasureSpec, 1);

        int minh = MeasureSpec.getSize(w) + getPaddingBottom() + getPaddingTop();
        int h = resolveSizeAndState(MeasureSpec.getSize(w), heightMeasureSpec, 0);

        setMeasuredDimension(w, h);


    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(-500076);
//        canvas.drawPath(ctx, piePaint);
        for (int i = 0; i < slices.size(); i++) {
            piePaint.setColor(Color.rgb(i * 30, i * 30, i * 30));
//            piePaint.setShader(linearGradient);
            canvas.drawPath(slices.get(i), piePaint);

        }
//        canvas.drawPath(slices.get(1), piePaint);
//        canvas.drawPath(slices.get(1), piePaint);

        canvas.drawText(expenseText, viewWidth / 2, viewHeight / 2, textPaint);
    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
        super.onSizeChanged(xNew, yNew, xOld, yOld);

        Log.d(TAG, "onSizeChanged: " + xNew);
        viewWidth = xNew;
        viewHeight = yNew;

        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        Point point = new Point();
        point.x = (int) touchX;
        point.y = (int) touchY;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (int i = 0; i < regions.size(); i++) {
                    if (regions.get(i).contains(point.x, point.y)) {
                        Log.d(TAG, "onTouchEvent: touched a shape");
                        Log.d(TAG, "onTouchEvent: " + data[i]);
                    } else {

                        Log.d(TAG, "not");
                    }
                }
        }
        return true;
    }
}
