package youzhao.kuaifang.adapter;

import java.util.ArrayList;
import java.util.List;

import youzhao.kuaifang.FamilyActivity;
import youzhao.kuaifang.R;
import youzhao.kuaifang.ReduceWeightActivity;
import youzhao.kuaifang.SpecialActiveActivity;
import youzhao.kuaifang.utils.BitmapCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

public class MainFirstViewPagerAdapter extends PagerAdapter{
	private List<ImageView> FstViewPagerimageViews;
	private int positionTemp;
	private Context context;
	public MainFirstViewPagerAdapter(List<ImageView> imageViews, Context context){
		this.FstViewPagerimageViews = imageViews;
		this.context = context;
	}
	
	@Override
	public int getCount() {  
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		//((ViewPager)container).removeView(imageViews.get(position % imageViews.size())); 
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		  positionTemp = position;
		  ViewParent vp = FstViewPagerimageViews.get(position % FstViewPagerimageViews.size()).getParent();  
          if (vp != null) {  
              ViewGroup parent = (ViewGroup) vp;  
              parent.removeView(FstViewPagerimageViews.get(position % FstViewPagerimageViews.size()));  
          }  
		 
		 ((ViewPager)container).addView(FstViewPagerimageViews.get(position % FstViewPagerimageViews.size()));
		//((ViewPager)container).addView(images.get(position % images.size()));
		//FstViewPagerimageViews.get(position % FstViewPagerimageViews.size()).setOnClickListener(this);
		 //container.getChildAt(((ViewPager)container).getCurrentItem()).setOnClickListener(this);
		 switch (position % FstViewPagerimageViews.size()) {
		 case 0:
			FstViewPagerimageViews.get(0).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(context, SpecialActiveActivity.class); 
					context.startActivity(intent);
				}
			});
			break;
		 case 1:
				FstViewPagerimageViews.get(1).setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(context, FamilyActivity.class); 
						context.startActivity(intent);
					}
				});
				break;
		 case 2:
				FstViewPagerimageViews.get(2).setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(context, ReduceWeightActivity.class); 
						context.startActivity(intent);
					}
				});
				break;
		default:
			break;
		}
		return FstViewPagerimageViews.get(position % FstViewPagerimageViews.size());   
	}

//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		switch (positionTemp % FstViewPagerimageViews.size()) {
//		case 0:
//			Intent intent = new Intent(context, SpecialActiveActivity.class); 
//			context.startActivity(intent);
//			break;
//		case 1:
//			Intent intent1 = new Intent(context, FamilyActivity.class);
//            context.startActivity(intent1);
//			break;
//		case 2:
//			Intent intent2 = new Intent(context, ReduceWeightActivity.class);
//            context.startActivity(intent2);
//			break;
//		default:
//			break;
//		}

//	}
	
}
