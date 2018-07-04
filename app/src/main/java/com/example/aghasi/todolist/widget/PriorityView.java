package com.example.aghasi.todolist.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aghasi.todolist.R;

public class PriorityView extends ViewGroup {

    private TextView mPriorityTxt, mCounterTxt;
    private ImageView mArrowUp, mArrowDown;
    private int mCounter;

    public PriorityView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPriorityTxt = new TextView(context);
        mCounterTxt = new TextView(context);
        mArrowUp = new ImageView(context);
        mArrowDown = new ImageView(context);

        addView(mArrowDown);
        addView(mArrowUp);
        addView(mCounterTxt);
        addView(mPriorityTxt);


        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PriorityView, 0, 0);

        try {

        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    public int getCounter() {
        return mCounter;
    }
}
