package youzhao.kuaifang;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class MyImageView extends ImageView{

	boolean focus = false;
	
	public MyImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	 public MyImageView(Context context, AttributeSet attrs) {  
	    super(context, attrs);   
	 }
	
	

	public boolean isFocus() {
		return focus;
	}

	public void setFocus(boolean focus) {
		this.focus = focus;
	}
	
	
}
