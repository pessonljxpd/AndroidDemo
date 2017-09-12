package com.adark.xchart;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

/**
 * <pre>
 *      author : Shelly
 *      e-mail : xxx@xx
 *      time   : 2017/09/12
 *      desc   :
 *      version: 1.0.0
 * </pre>
 */
public abstract class BaseView extends View {

    private Context mContext;
    private Paint mPaint = new Paint();

    private String mChartTitle;
    private String mXAxisName;
    private String mYAxisName;
    private float mAxisTextSize;
    private int mAxisTextColor;

    //图表视图的宽
    int mWidth;
    //图表视图的高
    int mHeight;
    //图表坐标轴原点的坐标
    int mOriginalX = 50;
    int mOriginalY = 800;

    public BaseView(Context context) {
        this(context, null);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        mContext = context;

        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.XChartView);

        if (typedArray != null) {
            typedArray.recycle();
        }

        initPaint();
    }

    private void initPaint() {
        if (mPaint != null) {
            mPaint = new Paint();
            mPaint.setDither(true);
            mPaint.setAntiAlias(true);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mWidth = getWidth() - mOriginalX;
        mHeight = (mOriginalY > getHeight() ? getHeight() : mOriginalY) - 400;

        drawTitle(canvas, mPaint);
        drawXAxis(canvas, mPaint);
        drawYAxis(canvas, mPaint);
        drawXAxisScale(canvas, mPaint);
        drawXAxisScaleValue(canvas, mPaint);
        drawYAxisScale(canvas, mPaint);
        drawYAxisScaleValue(canvas, mPaint);
        drawXAxisArrow(canvas, mPaint);
        drawYAxisArrow(canvas, mPaint);
        drawColumn(canvas, mPaint);

    }

    private void drawTitle(Canvas pCanvas, Paint pPaint) {
        if (!TextUtils.isEmpty(mChartTitle)) {
            mPaint.setTextSize(mAxisTextSize);
            mPaint.setColor(mAxisTextColor);
            mPaint.setFakeBoldText(true);

            pCanvas.drawText(
                    mChartTitle, (getWidth() >> 1) - (mPaint.measureText(mChartTitle)), mOriginalY + 40, mPaint);
        }

    }

    private void drawXAxisArrow(Canvas pCanvas, Paint pPaint) {

    }

    private void drawYAxisArrow(Canvas pCanvas, Paint pPaint) {

    }

    protected abstract void drawXAxis(Canvas pCanvas, Paint pPaint);

    protected abstract void drawYAxis(Canvas pCanvas, Paint pPaint);

    protected abstract void drawXAxisScale(Canvas pCanvas, Paint pPaint);

    protected abstract void drawXAxisScaleValue(Canvas pCanvas, Paint pPaint);

    protected abstract void drawYAxisScale(Canvas pCanvas, Paint pPaint);

    protected abstract void drawYAxisScaleValue(Canvas pCanvas, Paint pPaint);

    protected abstract void drawColumn(Canvas pCanvas, Paint pPaint);

}
