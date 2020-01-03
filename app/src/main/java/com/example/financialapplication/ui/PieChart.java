package com.example.financialapplication.ui;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.financialapplication.R;
import com.example.financialapplication.db.dao.TransactionDao;
//import com.example.financialapplication.db.entity.SubcategoryEntity;
import com.example.financialapplication.db.entity.TransactionEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class PieChart extends View {
    Paint piePaint;
    Paint piePaint2;
    Paint textPaint;

    int viewWidth, viewHeight;
    float centerY, centerX;
    int radius;
    Integer pSlice = null;

    List<Path> slices = new ArrayList<>();
    List<Region> regions = new ArrayList<>();
    List<Float[]> sliceCoordinates = new ArrayList<>();

    String[] subcategoryNames;
    List<TransactionDao.SubcategorySum> data = new ArrayList<>();
    float totalSum = 0;

    String expenseText;

    RectF rectOuter;
    RectF rectInner;

    private boolean animated;
    private long animationDuration = 4000L;
    ValueAnimator animation = null;

//    LinearGradient linearGradient;

    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    private void init() {
        piePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        piePaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        piePaint.setStyle(Paint.Style.FILL);
        piePaint2.setStyle(Paint.Style.STROKE);
        piePaint2.setStrokeWidth(2);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(20 * getResources().getDisplayMetrics().density);
        textPaint.setTextAlign(Paint.Align.CENTER);


        float xpad = (float) (getPaddingLeft() + getPaddingRight());
        float ypad = (float) (getPaddingTop() + getPaddingBottom());

        float ww = (float) getWidth() - xpad;
        float hh = (float) getHeight() - ypad;

        if (data.size() > 0) {
            createSlices();
        }

        createBlankRing();


    }

    private void createSlices() {
        radius = Math.min((int) (viewWidth * 0.75), (int) (viewHeight * 0.75)) / 2;

        totalSum = 0;

        for (int i = 0; i < data.size(); i++) {
            totalSum += data.get(i).getSum();
        }


        float runningTotal = 0;
        float pX = radius;
        float pY = 0;
        sliceCoordinates.clear();


        centerY = viewHeight / 2;
        centerX = viewWidth / 2;

        slices.clear();
        regions.clear();
        for (int i = 0; i < data.size(); i++) {

            float sweepAngle = data.get(i).getSum() / totalSum * 360;
            float cumulativeAngle = runningTotal / totalSum * 360;
            Path slice = new Path();
            slice.moveTo(centerX + (float) (pX * 0.75), centerY + (float) (pY * 0.75)); // Center
            slice.lineTo(centerX + pX, centerY + pY); // line out
            slice.arcTo(new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius),
                    cumulativeAngle, sweepAngle); // arc
            runningTotal += data.get(i).getSum();
            pY = radius * (float) Math.sin(Math.toRadians((runningTotal / totalSum) * 360));
            pX = radius * (float) Math.cos(Math.toRadians((runningTotal / totalSum) * 360));
            slice.lineTo(centerX + (float) (pX * 0.75), centerY + (float) (pY * 0.75));
            slice.arcTo(new RectF(centerX - (float) (radius * 0.75), centerY - (float) (radius * 0.75), centerX + (float) (radius * 0.75), centerY + (float) (radius * 0.75)),
                    runningTotal / totalSum * 360, -sweepAngle);

            slice.close();
            slices.add(slice);

            Float[] coordinates = new Float[2];
            coordinates[0] = (float) Math.cos(Math.toRadians((runningTotal / totalSum * 360) - ((data.get(i).getSum() / totalSum) * 360) / 2)) * (float) (radius * 0.1);
            coordinates[1] = (float) Math.sin(Math.toRadians((runningTotal / totalSum * 360) - ((data.get(i).getSum() / totalSum) * 360) / 2)) * (float) (radius * 0.1);
            sliceCoordinates.add(coordinates);

            RectF rectF = new RectF();
            slice.computeBounds(rectF, true);
            Region r = new Region();
            r.setPath(slice, new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));
            regions.add(r);
        }
        expenseText = "Total Spent: " + totalSum;

    }

    private void createBlankRing() {

        radius = Math.min((int) (viewWidth * 0.75), (int) (viewHeight * 0.75)) / 2;

        centerY = viewHeight / 2;
        centerX = viewWidth / 2;

        rectOuter = new RectF((int) (centerX - radius), (int) (centerY - radius), (int) (centerX + radius), (int) (centerY + radius));
        rectInner = new RectF((int) (centerX - radius * 0.75), (int) (centerY - radius * 0.75), (int) (centerX + radius * 0.75), (int) (centerY + radius * 0.75));


    }

    private void onSliceSelected(int slice) {
        Matrix translateMatrix = new Matrix();
        Region r = new Region();
        RectF rectF = new RectF();


        if (pSlice == null) {
            // Just open this slice because nothing else is open.
            translateMatrix.setTranslate(sliceCoordinates.get(slice)[0], sliceCoordinates.get(slice)[1]);
            slices.get(slice).transform(translateMatrix);
            slices.get(slice).computeBounds(rectF, true);
            r.setPath(slices.get(slice), new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));
            regions.set(slice, r);
            pSlice = slice;
            expenseText = data.get(slice).getSubcategoryName() + " Spent:" + data.get(slice).getSum();
        } else {
            if (pSlice == slice) {
                // Close previously open slice because it was selected again
                translateMatrix.setTranslate(-sliceCoordinates.get(slice)[0], -sliceCoordinates.get(slice)[1]);
                slices.get(slice).transform(translateMatrix);
                slices.get(slice).computeBounds(rectF, true);
                r.setPath(slices.get(slice), new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));
                regions.set(slice, r);
                expenseText = "Total Spent: " + totalSum;
                pSlice = null;
            } else {
                // Close previously open slice and open new slice.
                translateMatrix.setTranslate(-sliceCoordinates.get(pSlice)[0], -sliceCoordinates.get(pSlice)[1]);
                slices.get(pSlice).transform(translateMatrix);
                slices.get(pSlice).computeBounds(rectF, true);
                r.setPath(slices.get(pSlice), new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));
                regions.set(pSlice, r);

                Region rr = new Region();
                translateMatrix.setTranslate(sliceCoordinates.get(slice)[0], sliceCoordinates.get(slice)[1]);
                slices.get(slice).transform(translateMatrix);
                slices.get(slice).computeBounds(rectF, true);
                rr.setPath(slices.get(slice), new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));
                regions.set(slice, rr);
                expenseText = data.get(slice).getSubcategoryName() + " Spent:" + data.get(slice).getSum();
                pSlice = slice;
            }
        }

        invalidate();
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

        if (slices.size() > 1) {
            for (int i = 0; i < slices.size(); i++) {
                // TODO: Make colors better! Be careful for going above 255!
                piePaint.setColor(Color.rgb(0, 0, i * (250 / slices.size())));
//            piePaint.setShader(linearGradient);
                canvas.drawPath(slices.get(i), piePaint);

            }
            for (int i = 0; i < slices.size(); i++) {
                // TODO: Make colors better! Be careful for going above 255!
                piePaint2.setColor(-500076);
//            piePaint.setShader(linearGradient);
                canvas.drawPath(slices.get(i), piePaint2);

            }
        } else {
            piePaint.setColor(Color.WHITE);
            canvas.drawOval(rectOuter, piePaint);
            piePaint.setColor(-500076);
            canvas.drawOval(rectInner, piePaint);
            if (slices.size() == 1) {
                expenseText = data.get(0).getSubcategoryName() + " Spent: " + data.get(0).getSum();
                piePaint.setColor(Color.BLUE);
                canvas.drawOval(rectOuter, piePaint);
                piePaint.setColor(-500076);
                canvas.drawOval(rectInner, piePaint);
            } else {
                piePaint.setColor(Color.WHITE);
                canvas.drawOval(rectOuter, piePaint);
                piePaint.setColor(-500076);
                canvas.drawOval(rectInner, piePaint);
                expenseText = "No data found!";
            }
        }


        canvas.drawText(expenseText, viewWidth / 2, viewHeight / 2, textPaint);
    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld) {
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
                Log.d(TAG, "onTouchEvent: action down");
                for (int i = 0; i < regions.size(); i++) {
                    if (regions.get(i).contains(point.x, point.y)) {
//                        Log.d(TAG, "onTouchEvent: touched a shape");
//                        Log.d(TAG, "onTouchEvent: " + data[i]);
                        onSliceSelected(i);
                    } else {

                        Log.d(TAG, "not");
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: action up");
                break;
        }
        return true;
    }

    public void setExpenseData(List<TransactionDao.SubcategorySum> subcategoryEntities) {
        this.data = subcategoryEntities;
        pSlice = null;
        createSlices();
        invalidate();

    }

//    public void setAnimated(boolean animated) {
//        this.animated = animated;
//    }
//
//    public void setAnimationDuration(long animationDuration) {
//        this.animationDuration = animationDuration;
//    }
//
//    public ValueAnimator createAnimator() {
//        PropertyValuesHolder propertyX = PropertyValuesHolder.ofInt(PROPERTY_X, 100, 300);
//        PropertyValuesHolder propertyY = PropertyValuesHolder.ofInt(PROPERTY_Y, 100, 300);
//        PropertyValuesHolder propertyAlpha = PropertyValuesHolder.ofInt(PROPERTY_ALPHA, 0, 255);
//
//        ValueAnimator animator = new ValueAnimator();
//        animator.setValues();
//        animator.setDuration(2000);
//        animator.setInterpolator(new AccelerateDecelerateInterpolator());
//
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//
//                }
//            });
//        return animator;
//    }
}
