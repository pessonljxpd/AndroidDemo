package com.adark.hencoder.chapter01;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.adark.hencoder.R;

/**
 * <pre>
 *      author : Shelly
 *      e-mail : xxx@xx
 *      time   : 2017/09/12
 *      desc   :
 *      version: 1.0.0
 * </pre>
 */
public class CircleView extends View {

    private Context mContext;
    private Paint mPaint;

    private float mRadius;
    private int mColor;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        mContext = context;

        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.CircleView);
        mRadius = typedArray.getDimensionPixelOffset(R.styleable.CircleView_radius, 200);
        mColor = typedArray.getColor(R.styleable.CircleView_color, Color.YELLOW);


        if (typedArray != null) {
            typedArray.recycle();
        }


        initPaint(mContext);
    }

    private void initPaint(Context pContext) {
        if (mPaint == null) {
            mPaint = new Paint();
        }

        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        String title = "圆的名称";

        mPaint.setColor(mColor);
        mPaint.setTextSize(40);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mRadius, mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawText(title, (getWidth() - mPaint.measureText(title)) / 2, getHeight() / 2 + mRadius + 50, mPaint);
    }
}
