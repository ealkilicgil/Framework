package es.framework.es.framework.ui.controls;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by 02481552 on 03.12.2015.
 */

public class LinedEditText extends EditText {

    private Rect mRect;
    private Paint mPaint;

    public LinedEditText(Context context,AttributeSet attrs) {
        super(context,attrs);

        mRect=new Rect();
        mPaint=new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.parseColor("#212121 "));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int height=getHeight();
        int line_height=getLineHeight();
        int count=height/line_height;

        Rect r=mRect;
        Paint paint=mPaint;

        int baseline=getLineBounds(0,r);
        if(getLineCount()>count)
        {
            count=getLineCount();
        }

        for (int i=0;i<count;i++){
            canvas.drawLine(r.left,baseline+1,r.right,baseline+1,paint);
            baseline+=getLineHeight();
        }
        super.onDraw(canvas);
    }
}
