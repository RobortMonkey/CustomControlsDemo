package com.et.et.customcontrolsdemo.luopan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.et.et.customcontrolsdemo.utils.UIUtils;

/**
 * @author wangxiongfeng
 * @date 2017/7/10 0010 15:17
 */

public class LuoPanView extends View {
    private static final int specSize = 500;
    private static final int lineshort = 25;
    private static final int lineshort30 = 40;
    private static final int textPadd = 50;

    private Context context;

    private int defaultWidth;
    private int defaultHeigth;

    private String CRICLEPAINTCOLOR = "#f0f1f3";

    private String SCALE = "#6c6c6c";

    private String SCALE30 = "#333333";


    private String POINTER = "#333333";

    private String SCALUEVALUE = "#333333";
    private Paint criclePaint;
    private Paint scalePaint;
    private Paint scalePaint30;
    private Paint scalePaint45;
    private RectF mRectF;
    private float radius;
    private int mViewWidth;
    private int mViewHeight;
    private int mViewCenterX;
    private int mViewCenterY;


    private int mMinRadio; //最里面白色圆的半径
    private float mRingWidth; //圆环的宽度
    private Paint textPaint;


    public LuoPanView(Context context) {
        this(context, null);
    }

    public LuoPanView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LuoPanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        paintInit();
        this.context=context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        int size = Math.min(width, height);
        radius = size / 2 * 1.0f;
        mRingWidth = radius / 4;
        mMinRadio = (int) radius / 4 * 3;
        setMeasuredDimension(size, size);
    }

    private int measureWidth(int widthMeasureSpec) {
        int result;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = getDefaultWidth();
        } else {
            result = getDefaultWidth();//最大尺寸模式，getDefaultWidth方法需要我们根据控件实际需要自己实现
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.max(size, specSize);
            }
        }
        return result;
    }

    public int getDefaultWidth() {


        return specSize;
    }


    private int measureHeight(int heigthMeasureSpec) {
        int result;
        int mode = MeasureSpec.getMode(heigthMeasureSpec);
        int size = MeasureSpec.getSize(heigthMeasureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = getDefaultHeigth();
        } else {
            result = getDefaultHeigth();
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.max(size, specSize);
            }
        }

        return result;
    }

    public int getDefaultHeigth() {
        return specSize;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paintInit();
        //画默认圆环
        canvas.drawArc(mRectF, 270, 360, false, criclePaint);

        //绘制指针



        //绘制表盘
        drawBiaoPan(canvas);


    }

    private void drawBiaoPan(Canvas canvas) {
        for (int i = 0; i < 360; i++) {

            if (i % 2 == 0) {
                if (i % 10 == 0) {
                    canvas.drawLine(mViewCenterX,
                            mViewCenterY - mMinRadio - lineshort30,
                            mViewCenterX,
                            mViewCenterY - mMinRadio,
                            scalePaint30);
                    Rect mTxtRect = new Rect();
                    String content = i + "";
                    textPaint.getTextBounds(content, 0, content.length(), mTxtRect);
                    if (i == 360 || i == 0)

                        canvas.drawText("0",
                                mViewCenterX - mTxtRect.width() / 2,
                                mViewCenterY - mMinRadio - textPadd,
                                textPaint);
                    else {


                        canvas.drawText(content,
                                mViewCenterX - mTxtRect.width() / 2,
                                mViewCenterY - mMinRadio - textPadd,
                                textPaint);
                    }


                } else {
                    canvas.drawLine(mViewCenterX,
                            mViewCenterY - mMinRadio - lineshort,
                            mViewCenterX,
                            mViewCenterY - mMinRadio,
                            scalePaint);
                }

                canvas.rotate(2, mViewCenterX, mViewCenterY);
            }

        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();
        mViewCenterX = mViewWidth / 2;
        mViewCenterY = mViewHeight / 2;
        mRectF = new RectF(mViewCenterX - mMinRadio - mRingWidth / 2, mViewCenterY - mMinRadio - mRingWidth / 2, mViewCenterX + mMinRadio + mRingWidth / 2, mViewCenterY + mMinRadio + mRingWidth / 2);
    }


    public void paintInit() {
        criclePaint = new Paint();
        criclePaint.setStyle(Paint.Style.STROKE);
        criclePaint.setStrokeWidth(mRingWidth);
        criclePaint.setColor(Color.parseColor(CRICLEPAINTCOLOR));
        criclePaint.setAntiAlias(true);
        mRectF = new RectF(mViewCenterX - mMinRadio - mRingWidth / 2, mViewCenterY - mMinRadio - mRingWidth / 2, mViewCenterX + mMinRadio + mRingWidth / 2, mViewCenterY + mMinRadio + mRingWidth / 2);


        scalePaint = new Paint();
        scalePaint.setColor(Color.parseColor(SCALE));
        scalePaint.setAntiAlias(true);
        scalePaint.setStrokeWidth(2);

        scalePaint30 = new Paint();
        scalePaint30.setColor(Color.parseColor(SCALE30));
        scalePaint30.setAntiAlias(true);
        scalePaint30.setStrokeWidth(3);


        Paint pointer = new Paint();
        pointer.setStrokeWidth(2);
        pointer.setAntiAlias(true);
        pointer.setColor(Color.parseColor(POINTER));


        textPaint = new Paint();
        textPaint.setColor(Color.parseColor(SCALE30));
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(UIUtils.dip2px(10,context));
    }

}
