package com.sem.kingapputils.ui.view.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

/**
 * Created by zhxumao on 2021-05-27 23:09
 */
public class KZoomNestScrollView extends NestedScrollView {

    private final static String TAG_ZOOM_BG = "zoom_bg";

    private View zoomView;
    private Rect zoomViewSrcRect = new Rect();
    private boolean isMove;
    private ValueAnimator anim;

    private ViewGroup.LayoutParams zoomViewLp;

    private int startY;
    private int currentY;
    private int lastY;
    private int offset;

    public KZoomNestScrollView(Context context) {
        this(context, null);
    }

    public KZoomNestScrollView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KZoomNestScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAnimator();
        setFillViewport(true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        zoomView = findViewWithTag(TAG_ZOOM_BG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (zoomView == null) {
            return super.dispatchTouchEvent(ev);
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (anim.isRunning()){
                    anim.cancel();
                }else {
                    // zoomView初始值
                    zoomViewSrcRect.set(zoomView.getLeft(), zoomView.getTop(), zoomView.getRight(), zoomView.getBottom());
                }
                isMove = false;
                startY = (int) ev.getY();
                lastY = startY;
                zoomViewLp = zoomView.getLayoutParams();
                break;
            case MotionEvent.ACTION_MOVE:
                currentY = (int) ev.getY();
                offset = currentY - lastY;
                if (offset > 5){
                    isMove = true;
                }
                lastY = currentY;
                if (((isVisibleLocal(zoomView, true) && offset > 0) || (zoomView.getBottom() > zoomViewSrcRect.bottom))) {
                    zoomViewLp.height = (int) (zoomViewLp.height + offset * 0.45);
                    zoomView.setLayoutParams(zoomViewLp);
                }
                if ((zoomView.getBottom() > zoomViewSrcRect.bottom)) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (isVisibleLocal(zoomView, true)) {
//                    countDownTimer.cancel();
//                    countDownTimer.start();
                    if (isMove){
                        replyImage();
                        return true;
                    }
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private void setAnimator(){
        anim = ObjectAnimator.ofFloat(0.0F, 1.0F).setDuration((long) (600));

        anim.addUpdateListener(animation -> {
            float cVal = (Float) animation.getAnimatedValue();
            zoomViewLp = zoomView.getLayoutParams();
            zoomViewLp.height = zoomViewLp.height - (int) ((zoomViewLp.height - zoomViewSrcRect.bottom) * ((float) (cVal)));
            zoomView.setLayoutParams(zoomViewLp);
        });

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // 防止中间退出导致中断动画
                zoomViewLp = zoomView.getLayoutParams();
                zoomViewLp.height = zoomViewLp.height - (int) ((zoomViewLp.height - zoomViewSrcRect.bottom));
                zoomView.setLayoutParams(zoomViewLp);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }


    // 回弹动画 (使用了属性动画)
    public void replyImage() {
       float distance = zoomViewLp.height - zoomViewSrcRect.bottom;
        // 设置动画
        if (distance > 0) {
            if (anim.isRunning()) {
                anim.cancel();
            }
            anim.start();
        }

    }
    private final CountDownTimer countDownTimer = new CountDownTimer(600, 10) {
        @Override
        public void onTick(long millisUntilFinished) {
            zoomViewLp = zoomView.getLayoutParams();
            zoomViewLp.height = zoomViewLp.height - (int) ((zoomViewLp.height - zoomViewSrcRect.bottom) * ((float) (600 - millisUntilFinished) / 600));
            zoomView.setLayoutParams(zoomViewLp);
        }

        @Override
        public void onFinish() {
            zoomViewLp = zoomView.getLayoutParams();
            zoomViewLp.height = zoomViewSrcRect.bottom;
            zoomView.setLayoutParams(zoomViewLp);
        }
    };

    /**
     * 判断View是否可见
     *
     * @param target   View
     * @param judgeAll 为 true时,判断 View 全部可见才返回 true
     * @return boolean
     */
    public static boolean isVisibleLocal(View target, boolean judgeAll) {
        Rect rect = new Rect();
        target.getLocalVisibleRect(rect);
        if (judgeAll) {
            return rect.top == 0;
        } else {
            return rect.top >= 0;
        }
    }
}
