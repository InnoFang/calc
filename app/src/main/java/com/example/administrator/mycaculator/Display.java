package com.example.administrator.mycaculator;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 *  2016/2/14
 */
public class Display extends EditText {

    // Attributes
    private Paint testPaint;
    private float minTextSize, maxTextSize;

    public Display(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise();
    }

    /**
     * 初始化字体大小,若字体设置最大值小于默认值,就将字体大小设为默认值
     * 且最小字体设置为默认值
     */
    private void initialise() {
        float DEFAULT_MIN_TEXT_SIZE = 10;
        float DEFAULT_MAX_TEXT_SIZE = 25;

        testPaint = new Paint();
        testPaint.set(this.getPaint());

        maxTextSize = this.getTextSize();

        if (maxTextSize <= DEFAULT_MAX_TEXT_SIZE) {
            maxTextSize = DEFAULT_MAX_TEXT_SIZE;
        }

        minTextSize = DEFAULT_MIN_TEXT_SIZE;
    }

    /**
     * 让字体大小随着文本输入的增多而逐渐减小
     */
    private void refitText(String text, int textWidth) {
        if (textWidth > 0) {
            int availableWidth = textWidth - this.getPaddingLeft()
                    - this.getPaddingRight();
            float trySize = maxTextSize;
            testPaint.setTextSize(trySize);
            while ((trySize > minTextSize)
                    && (testPaint.measureText(text) > availableWidth)) {
                trySize -= 5;
                if (trySize <= minTextSize) {
                    trySize = minTextSize;
                    break;
                }
                testPaint.setTextSize(trySize);
            }
            this.setTextSize(trySize);
        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int before,
                                 int after) {
        super.onTextChanged(text, start, before, after);
        refitText(text.toString(), this.getWidth());
    }


    @Override
    protected void onSizeChanged (int w, int h, int oldw, int oldh) {
        if (w != oldw) {
            refitText(this.getText().toString(), w);
        }
    }
}
