package adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Thanh-PC on 8/15/2017.
 */

public class CustomIMG extends ImageView {
    public CustomIMG(Context context) {
        super(context);
    }

    public CustomIMG(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomIMG(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int a=getMeasuredWidth();
        int b=getMeasuredWidth()+200;

        setMeasuredDimension(a,b ); //Snap to width
    }
}
