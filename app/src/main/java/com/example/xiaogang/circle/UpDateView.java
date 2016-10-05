package com.example.xiaogang.circle;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by xiaogang on 16/9/3.
 */
public class UpDateView extends View {
    private Paint paint;
    private RectF rectF;
    private int linecolor;
    private int circlewidth;
    private int progress;

    public UpDateView(Context context) {
        super(context);
        init();
    }

    public UpDateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UpDateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.UpDateView, defStyleAttr, 0);
        int n = a.getIndexCount();

        for (int i = 0; i < n; i++) {

            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.UpDateView_linecolor:
                    linecolor = a.getColor(attr, Color.RED);
                    break;
                case R.styleable.UpDateView_circlewidth:
                    circlewidth = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()));
                    break;

            }
        }
        a.recycle();
        init();

    }

    private void init() {
        paint = new Paint();
        paint.setColor(linecolor);//无效
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);//未使用TypedArray  原因用TypedArray无法改变大小，原因待发现
        paint.setAntiAlias(true);//抗锯齿
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int widch = getWidth();
        rectF = new RectF(80, 80, widch - 80, widch - 80);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(12);
        canvas.drawArc(rectF,0,360,false,paint);
        paint.setColor(Color.GRAY);
        canvas.drawArc(rectF, -90, progress, false, paint);
        paint.setStrokeWidth(8);
        canvas.drawLine((widch /2)-80,widch/2 + 20,widch / 2,widch/2 -90,paint);
        canvas.drawLine(widch / 2,widch/2 -90,(widch /2)+80,widch/2 + 20,paint);
        canvas.drawLine(widch / 2,widch/2 -92,widch / 2,widch/2 + 200,paint);
        startProgress();

    }

    private void startProgress() {
        if (isShown()){
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    progress += 3;
                    invalidate();//请求重绘View树，调用draw()方法    修改完数据，需要重绘时调用
                }

            },50);

        }
    }



}