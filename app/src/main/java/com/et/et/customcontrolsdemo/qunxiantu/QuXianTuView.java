package com.et.et.customcontrolsdemo.qunxiantu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.et.et.customcontrolsdemo.utils.UIUtils;

/**
 * @author wangxiongfeng
 * @date 2017/7/18 0018 10:38
 */

public class QuXianTuView extends View {
    private static final String XYPAINT_COLOR = "#333333";
    private static final float XYPAINT_WIDTH = 1.0f;
    private Context context;

    public QuXianTuView(Context context) {
        this(context, null);
    }

    public QuXianTuView(Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public QuXianTuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void paintInit() {
        //xy坐标 画笔
        Paint xyPaint = new Paint();
        xyPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        xyPaint.setColor(Color.parseColor(XYPAINT_COLOR));
        xyPaint.setStrokeWidth(XYPAINT_WIDTH);


        // 坐标 刻度线
        Paint markPaint = new Paint();
        markPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        markPaint.setTextSize(UIUtils.dip2px(5, context));
        markPaint.setColor(Color.parseColor(XYPAINT_COLOR));

    }


}
