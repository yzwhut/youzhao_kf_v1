package youzhao.kuaifang.mywidget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;


public class MyViewPager extends ViewPager{

	public MyViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
   public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
   
   @Override
   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

       int height = 0;
       for (int i = 0; i < getChildCount(); i++) {
           View child = getChildAt(i);
           child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
           int h = child.getMeasuredHeight();
           if (h > height)height = h;
       }

       heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

       super.onMeasure(widthMeasureSpec, heightMeasureSpec);
   }

}
