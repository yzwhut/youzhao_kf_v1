package youzhao.kuaifang.mywidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ListView;

public class MyListView extends ListView{

	public MyListView(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
	}

	public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override 
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { 
 
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 1, 
       		 MeasureSpec.AT_MOST); 
        super.onMeasure(widthMeasureSpec, expandSpec); 
	}
}
