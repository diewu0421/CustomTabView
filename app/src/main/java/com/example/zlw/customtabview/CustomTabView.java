package com.example.zlw.customtabview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * CustomTabView
 *
 * @author zlw
 * @date 2018/5/6
 */
public class CustomTabView extends ViewGroup {
    private List<String> mTabs;

    private Paint mPaint;
    private Paint mLinePaint;
    private LinearLayout mContainer;
    private LayoutParams mMContainerParams;

    public CustomTabView(Context context) {
        this(context, null);
    }

    public CustomTabView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackgroundColor(Color.GRAY);
        mTabs = new ArrayList<>();
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(50);

        mLinePaint = new Paint();
        mLinePaint.setDither(true);
        mLinePaint.setAntiAlias(true);
        mLinePaint.setColor(Color.BLACK);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(10);

        mContainer = new LinearLayout(getContext());
        mMContainerParams = new LinearLayout.LayoutParams(100, 100,2);
        mContainer.setLayoutParams(mMContainerParams);
        mContainer.setVerticalGravity(Gravity.CENTER_VERTICAL);
        TextView btn1 = new TextView(getContext());
        btn1.setText("素材");
        btn1.setGravity(Gravity.CENTER);
        LayoutParams btnParam = new LinearLayout.LayoutParams(0,LayoutParams.MATCH_PARENT,1);
        btn1.setLayoutParams(btnParam);
        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "btn1", Toast.LENGTH_SHORT).show();
            }
        });
        mContainer.addView(btn1);

        View view = new View(getContext());
        LayoutParams lineParam = new LayoutParams(dp2px(getContext(),2),20);
        view.setBackgroundColor(Color.YELLOW);
        view.setLayoutParams(lineParam);
        mContainer.addView(view);

        TextView btn2 = new TextView(getContext());
        btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "btn2", Toast.LENGTH_SHORT).show();
            }
        });
        btn2.setText("收藏");
        btn2.setGravity(Gravity.CENTER);
        LayoutParams btnParam2 = new LinearLayout.LayoutParams(0,LayoutParams.MATCH_PARENT,1);
        btn2.setLayoutParams(btnParam2);
        mContainer.addView(btn2);


        addView(mContainer);
    }


    public void addTabs(String... tabs) {
        mTabs.addAll(Arrays.asList(tabs));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int specHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        int height = 30;
        int width = MeasureSpec.getSize(widthMeasureSpec);
        if (specHeightMode == MeasureSpec.AT_MOST) {
            height = dp2px(getContext(), 30);
        }

        mMContainerParams.width = width;
        mMContainerParams.height = height;
        mContainer.setLayoutParams(mMContainerParams);
        measureChildWithMargins(mContainer,widthMeasureSpec,0,heightMeasureSpec,0);
        setMeasuredDimension(width, height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int count = mTabs.size();
//        Log.e("count", count + "");
//        for (int i = 0; i < count; i++) {
//            canvas.drawText(mTabs.get(i), 10 * (i*50), getMeasuredHeight() / 2, mPaint);
//        }
//
//        canvas.drawLine(0, getMeasuredHeight() - 2, 50, getMeasuredHeight() - 2, mLinePaint);

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        if (b) {
//            mContainer.layout(0, 0, 100, 100);
            mContainer.layout(0, 0, mContainer.getMeasuredWidth(), mContainer.getMeasuredHeight());
        }
    }

    private int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转换成dp
     */
    private int px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


}
