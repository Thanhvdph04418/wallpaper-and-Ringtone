package adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Thanh-PC on 8/16/2017.
 */

public class CustomButton extends Button {


    public CustomButton(Context context) {
        super(context);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int a=getMeasuredWidth();
        int b=getMeasuredWidth()/3;
        setMeasuredDimension(a,b );
    }
}
