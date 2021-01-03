package com.ywj.lizianimation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;

public class ParticleView extends View {
    private final int ITEM_COUNT = 60;//item数量
    private int mWidth;
    private int mHeight;
    private List<ParticleItem> mItems = new ArrayList<>();
    private Context mContext;
    private Paint mPaint;
    private ValueAnimator animator;

    public ParticleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParticleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (ParticleItem item : mItems) {
            mPaint.setAlpha((int) (item.getAlpha() * 255));
            canvas.drawCircle(item.getX(), item.getY(), item.getRadius(), mPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        start();
    }


    private void init(Context context) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    public void start() {

        Random random = new Random();
        for (int i = 0; i < ITEM_COUNT; i++) {
            ParticleItem particleItem = new ParticleItem();
            particleItem.setRadius((int) (mWidth / 100 * random.nextFloat() + 5));
            particleItem.setRotateLength((int) (mWidth / 10 * random.nextFloat() + 10));
            particleItem.setAlpha((1 - i * 1.0f / ITEM_COUNT) * 0.8f);
            particleItem.setRotateX((int) (mWidth * 0.2 + mWidth * 0.6 * random.nextFloat()));
            particleItem.setRotateY((int) (mHeight * 0.2 + mHeight * 0.6 * random.nextFloat()));
            particleItem.setForward(i % 2 == 1);
            particleItem.setCurrentDegree((int) (360 * random.nextFloat()));
            mItems.add(particleItem);
        }

        animator = ValueAnimator.ofInt(0, 360);
        animator.setDuration(5 * 1000L);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            int lastDegree = Integer.MIN_VALUE;

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int degree = (int) animation.getAnimatedValue();
                if (lastDegree == degree) {
                    return;
                }
                lastDegree = degree;
                for (ParticleItem item : mItems) {
                    item.setCurrentDegree(degree);
                }
                invalidate();
            }
        });
        animator.start();

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    public void stop() {
        if (animator != null) {
            animator.cancel();
            animator = null;
        }
    }
}
