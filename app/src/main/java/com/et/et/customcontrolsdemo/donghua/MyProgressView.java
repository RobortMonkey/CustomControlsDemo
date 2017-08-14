package com.et.et.customcontrolsdemo.donghua;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Region;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author wangxiongfeng
 * @date 2017/8/7 0007 14:59
 */

public class
MyProgressView extends View {
    private boolean isMeasure = false;

    private float levelLine;
    private int viewWidth;
    private int viewHeight;
    private float waveHeight;
    private int waveWeight;
    private int leftSide;

    private List<Point> mPointsList = new ArrayList<>();
    private Paint mPaint;
    private Path mWavePath;
    private MyTimerTask mTask;
    private Timer timer;


    private final static float SPEED = 5.0f;
    private float mMoveLen;
    private Paint mCriclePaint;
    private Canvas mCanvas;
    private Bitmap mBitmap;
    private Paint paint;

    public MyProgressView(Context context) {
        this(context, null);
    }

    public MyProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (!isMeasure) {

            isMeasure = true;
            viewWidth = getMeasuredWidth();
            viewHeight = getMeasuredHeight();


            levelLine = viewHeight; //水平位
            waveHeight = viewWidth / 10f;//波峰高度；
            waveWeight = viewWidth;//波长宽度；

            leftSide = -waveWeight; //预留一个波长

            int n = (int) Math.round(viewWidth / waveWeight + 0.5);

            for (int i = 0; i < (4 * n + 5); i++) {

                // 从P0开始初始化到P4n+4，总共4n+5个点
                float x = i * waveWeight / 4 - waveWeight;
                float y = 0;
                switch (i % 4) {
                    case 0:
                    case 2:
                        // 零点位于水位线上
                        y = levelLine;
                        break;
                    case 1:
                        // 往下波动的控制点
                        y = levelLine + waveHeight;
                        break;
                    case 3:
                        // 往上波动的控制点
                        y = levelLine - waveHeight;
                        break;
                }
                mPointsList.add(new Point(x, y));
            }

            //生成一个bitmap
            mBitmap = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.ARGB_8888);
            //讲bitmp放在我们自己的画布上，实际上mCanvas.draw的时候 改变的是这个bitmap对象
            mCanvas = new Canvas(mBitmap);

        }
    }

    private void initPaint() {


        timer = new Timer();
        //曲线画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.BLUE);


        mCriclePaint = new Paint();
        mCriclePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        mCriclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mCriclePaint.setStrokeWidth(1);

        paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);

        // 曲线路径
        mWavePath = new Path();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //数据初始化


    }

    private PorterDuffXfermode mMode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mBitmap.eraseColor(Color.parseColor("#00000000"));
//        mPaint.setXfermode(mMode);
//        dst
//        mCanvas.drawCircle(viewWidth / 2, viewHeight / 2, viewHeight / 2, mPaint);
//       src

        //裁剪成圆区域
        Path path = new Path();
        canvas.save();
        path.reset();
        canvas.clipPath(path);
        path.addCircle(viewWidth / 2, viewHeight / 2, viewHeight / 2, Path.Direction.CCW);
        canvas.clipPath(path, Region.Op.REPLACE);

        drawWave();
        canvas.drawBitmap(mBitmap, 0, 0, null);
        canvas.restore();
    }

    private Bitmap drawWave() {


        mWavePath.reset();
        int i = 0;
        mWavePath.moveTo(mPointsList.get(0).getPointX(), mPointsList.get(0).getPointY());
        for (; i < mPointsList.size() - 2; i = i + 2) {
            mWavePath.quadTo(mPointsList.get(i + 1).getPointX(),
                    mPointsList.get(i + 1).getPointY(), mPointsList.get(i + 2)
                            .getPointX(), mPointsList.get(i + 2).getPointY());
        }
        mWavePath.lineTo(mPointsList.get(i).getPointX(), viewHeight);
        mWavePath.lineTo(leftSide, viewHeight);
        mWavePath.close();
        mCanvas.drawPath(mWavePath, mPaint);
        return mBitmap;
    }


//    private void flowingAnimation(){
//        ObjectAnimator animator = ObjectAnimator.ofFloat(this,"wave",0,100)
//                .setDuration(100);
//        animator.setRepeatCount(INFINITE);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//
//                // 记录平移总位移
//                mMoveLen += SPEED;
//                // 水位上升
//                levelLine -= 0.1f;
//                if (levelLine < 0)
//                    levelLine = 0;
//                leftSide += SPEED;
//
//
//                dx = dx + speed;
//                shd_dx = shd_dx + speed/2;//Half the speed of the normal waves
//
//               ....
//                rerefreshPoints();//更新初始化点
//                postInvalidate();
//            }
//        })
//        ;
//        animator.start();
//    }

    private void start() {
        if (mTask != null) {
            mTask.cancel();
            mTask = null;
        }
        mTask = new MyTimerTask(updateHandler);
        timer.schedule(mTask, 0, 10);
    }

    class MyTimerTask extends TimerTask {
        Handler handler;

        public MyTimerTask(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void run() {
            handler.sendMessage(handler.obtainMessage());
        }

    }

    Handler updateHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 记录平移总位移
            mMoveLen += SPEED;
            // 水位上升
            levelLine -= 0.1f;
            if (levelLine < -waveHeight){
                levelLine = (int) -waveHeight;
                return;
            }

            Log.i("levelLine",levelLine+"   ");
            leftSide += SPEED;
            // 波形平移
            for (int i = 0; i < mPointsList.size(); i++) {
                mPointsList.get(i).setPointX(mPointsList.get(i).getPointX() + SPEED);
                switch (i % 4) {
                    case 0:
                    case 2:
                        mPointsList.get(i).setPointY(levelLine);
                        break;
                    case 1:
                        mPointsList.get(i).setPointY(levelLine + waveHeight);
                        break;
                    case 3:
                        mPointsList.get(i).setPointY(levelLine - waveHeight);
                        break;
                }
            }
            if (mMoveLen >= waveWeight) {
                // 波形平移超过一个完整波形后复位
                mMoveLen = 0;
                resetPoints();
            }
            Log.i("log", mMoveLen + "");
            invalidate();
        }


    };

    private void resetPoints() {
        leftSide = -waveWeight;
        for (int i = 0; i < mPointsList.size(); i++) {
            mPointsList.get(i).setPointX(i * waveWeight / 4 - waveWeight);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus)
            start();
        else
            stop();
    }

    private void stop() {
        mTask.cancel();
    }
}
