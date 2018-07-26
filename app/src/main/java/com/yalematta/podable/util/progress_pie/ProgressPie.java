package com.yalematta.podable.util.progress_pie;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.yalematta.podable.R;

/**
 * Created by yalematta on 7/26/18.
 */

public class ProgressPie extends ProgressBar {

    private int backgroundColor;
    private int foregroundColor;
    private int progress;

    public ProgressPie(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ProgressPie,0, 0);

        try {
            backgroundColor = a.getColor(R.styleable.ProgressPie_pie_background_color, getResources().getColor(R.color.colorAccent));
            foregroundColor = a.getColor(R.styleable.ProgressPie_pie_foreground_color, getResources().getColor(R.color.colorPrimaryDark2));
        }
        finally {
            a.recycle();
        }
    }


    public int getBackgroundColor() {
        return backgroundColor;
    }


    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }


    public int getForegroundColor() {
        return foregroundColor;
    }


    public void setForegroundColor(int foregroundColor) {
        this.foregroundColor = foregroundColor;
    }


    @Override
    public synchronized void setProgress(int progress) {
        this.progress = progress > 100
                ? 100
                : progress;
        invalidate();
    }


    @Override
    public int getProgress() {
        return progress;
    }


    @Override
    protected synchronized void onDraw(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        int pieRadius = (width < height
                ? width
                : height) / 2;

        RectF rectF = new RectF();
        rectF.set(0, (height / 2) - pieRadius, width, height);

        paint.setColor(getBackgroundColor());

        canvas.drawCircle(width / 2, height / 2, pieRadius, paint);

        paint.setColor(getForegroundColor());

        int progress = getProgress();
        double scale = 3.6;

        canvas.drawArc(rectF, 270, (float) (progress * scale), true, paint);
    }
}

