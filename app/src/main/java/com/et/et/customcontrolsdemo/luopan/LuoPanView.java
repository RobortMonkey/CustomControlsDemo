package com.et.et.customcontrolsdemo.luopan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.et.et.customcontrolsdemo.utils.UIUtils;


/**
 * @author wangxiongfeng
 * @date 2017/7/10 0010 15:17
 */

public class LuoPanView extends View implements SensorEventListener {
    private static final int specSize = 500;
    private static final int lineshort = 25;
    private static final int lineshort30 = 40;
    private static final int textPadd = 50;

    private static final int SANNJIAOBIAN = 20;

    private Context context;

    private int defaultWidth;
    private int defaultHeigth;

    private String CRICLEPAINTCOLOR = "#f0f1f3";

    private String SCALE = "#6c6c6c";

    private String SCALE30 = "#333333";

    private String SANJIAO = "#0078D7";


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
    private Paint sanJiaoPaint;
    private Paint sanjiaolinepaint;
    private Paint textPaintMark;
    private Paint locationpaint;
    private Paint pointer;

    String content = "N";
    String contentValue = "0";

    private float currentValue = 0.0f;
    private String pianZhuan;


    public LuoPanView(Context context) {
        this(context, null);
    }

    public LuoPanView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LuoPanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        paintInit();
        this.context = context;


        // 传感器管理器
        SensorManager sm = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        // 注册传感器(Sensor.TYPE_ORIENTATION(方向传感器);SENSOR_DELAY_FASTEST(0毫秒延迟);
        // SENSOR_DELAY_GAME(20,000毫秒延迟)、SENSOR_DELAY_UI(60,000毫秒延迟))
        sm.registerListener(this,
                sm.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_FASTEST);


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
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();
        mViewCenterX = mViewWidth / 2;
        mViewCenterY = mViewHeight / 2;
        mRectF = new RectF(mViewCenterX - mMinRadio - mRingWidth / 2, mViewCenterY - mMinRadio - mRingWidth / 2, mViewCenterX + mMinRadio + mRingWidth / 2, mViewCenterY + mMinRadio + mRingWidth / 2);
        paintInit();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画默认圆环
        canvas.drawArc(mRectF, 270, 360, false, criclePaint);

        //绘制指针
        drawZhiZheng(canvas);


        canvas.save();
        canvas.rotate(currentValue, mViewCenterX, mViewCenterY);
        //绘制表盘
        drawBiaoPan(canvas);
        canvas.restore();

        //绘制坐标信息

        drawLocaltionInfo(canvas);


    }
    Rect mTxtValueRect = new Rect();
    Rect mTxtLocaltionInfoRect = new Rect();
    Rect mTxtRectValue2 = new Rect();
    private void drawLocaltionInfo(Canvas canvas) {

        locationpaint.getTextBounds(pianZhuan, 0, pianZhuan.length(), mTxtLocaltionInfoRect);
        canvas.drawText(content,
                mViewCenterX - mTxtLocaltionInfoRect.width() / 2,
                mViewCenterY - 20,
                locationpaint);
        canvas.drawText(pianZhuan,
                mViewCenterX - mTxtLocaltionInfoRect.width() / 2,
                mViewCenterY + 20 + mTxtLocaltionInfoRect.height(),
                locationpaint);
        canvas.drawCircle(mViewCenterX + mTxtLocaltionInfoRect.width() / 2 + 20,
                mViewCenterY + 30, 8, pointer);
    }



    private void drawZhiZheng(Canvas canvas) {
        Path path = new Path();
        path.moveTo(mViewCenterX - SANNJIAOBIAN / 2, mViewCenterY - radius);
        path.lineTo(mViewCenterX + SANNJIAOBIAN / 2, mViewCenterY - radius);
        path.lineTo(mViewCenterX, mViewCenterY - radius + SANNJIAOBIAN / 2);
        path.close();

        canvas.drawPath(path, sanJiaoPaint);


        canvas.drawLine(mViewCenterX, mViewCenterY - radius + SANNJIAOBIAN / 2, mViewCenterX, mViewCenterY - mRingWidth, sanjiaolinepaint);
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

                    String content = i + "";
                    textPaint.getTextBounds(content, 0, content.length(), mTxtValueRect);
                    if (i == 360 || i == 0)

                        canvas.drawText("0",
                                mViewCenterX - mTxtValueRect.width() / 2,
                                mViewCenterY - mMinRadio - textPadd,
                                textPaint);
                    else {


                        canvas.drawText(content,
                                mViewCenterX - mTxtValueRect.width() / 2,
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

            }

            String content = "N";
            if (i % 45 == 0) {
                if (i % 90 == 0) {
                    textPaintMark.setTextSize(UIUtils.dip2px(10, context));
                    if (i == 0) {
                        content = "N";
                    }
                    if (i == 90) {
                        content = "E";
                    }
                    if (i == 180) {
                        content = "S";
                    }
                    if (i == 270) {
                        content = "W";
                    }
                    textPaintMark.getTextBounds(content, 0, content.length(), mTxtRectValue2);
                } else {
                    textPaintMark.setTextSize(UIUtils.dip2px(8, context));
                    if (i == 45) {
                        content = "NE";
                    }
                    if (i == 135) {
                        content = "SE";
                    }
                    if (i == 225) {
                        content = "WS";
                    }
                    if (i == 315) {
                        content = "NW";
                    }
                    textPaintMark.getTextBounds(content, 0, content.length(), mTxtRectValue2);
                }
                canvas.drawText(content,
                        mViewCenterX - mTxtRectValue2.width() / 2,
                        mViewCenterY - mMinRadio / 2 - mTxtRectValue2.height(),
                        textPaintMark);
            }


            canvas.rotate(1, mViewCenterX, mViewCenterY);

        }
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


        pointer = new Paint();
        pointer.setAntiAlias(true);
        pointer.setStyle(Paint.Style.STROKE);
        pointer.setStrokeWidth(3);
        pointer.setColor(Color.parseColor(POINTER));


        textPaint = new Paint();
        textPaint.setColor(Color.parseColor(SCALE30));
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(UIUtils.dip2px(10, context));

        textPaintMark = new Paint();
        textPaintMark.setColor(Color.parseColor(SCALE30));
        textPaintMark.setAntiAlias(true);
        textPaintMark.setTextSize(UIUtils.dip2px(5, context));

        sanJiaoPaint = new Paint();
        sanJiaoPaint.setColor(Color.parseColor(SANJIAO));
        sanJiaoPaint.setAntiAlias(true);
        sanJiaoPaint.setStyle(Paint.Style.FILL_AND_STROKE);


        sanjiaolinepaint = new Paint();
        sanjiaolinepaint.setColor(Color.parseColor(SANJIAO));
        sanjiaolinepaint.setAntiAlias(true);
        sanjiaolinepaint.setStyle(Paint.Style.STROKE);
        sanjiaolinepaint.setStrokeWidth(3);


        locationpaint = new Paint();
        locationpaint.setColor(Color.parseColor(SCALE30));
        locationpaint.setAntiAlias(true);
        locationpaint.setTextSize(UIUtils.dip2px(20, context));
    }


    //传感器报告新的值(方向改变)
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            float degree = event.values[0];

            float value1 = new Float(currentValue + "").intValue() + 1;
            float value2 = new Float(currentValue + "").intValue() - 1;
            if ((360 - degree) < value1 && (360 - degree) > value2) {
                return;
            } else {
                currentValue = 360 - degree;


            if (0 < currentValue && currentValue < 22 || 338 < currentValue && currentValue < 360)
                content = "N";
            if (22 < currentValue && currentValue < 67)
                content = "NW";
            if (76 < currentValue && currentValue < 112)
                content = "W";
            if (112 < currentValue && currentValue < 157)
                content = "SW";
            if (157 < currentValue && currentValue < 202)
                content = "S";
            if (202 < currentValue && currentValue < 247)
                content = "SE";
            if (247 < currentValue && currentValue < 292)
                content = "E";
            if (292 < currentValue && currentValue < 335)
                content = "NE";


            pianZhuan = new Float(degree).intValue() + "";

            postInvalidate();
            }

        }
    }

    //传感器精度的改变
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
