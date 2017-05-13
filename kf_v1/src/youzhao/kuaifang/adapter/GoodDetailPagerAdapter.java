package youzhao.kuaifang.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class GoodDetailPagerAdapter extends PagerAdapter{
	private List<ImageView> imageViews;
	
	public GoodDetailPagerAdapter(List<ImageView> imageViews){
		this.imageViews = imageViews;
	};
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imageViews.size();
	}
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	
	@Override
	public void destroyItem(View container, int position, Object object) {
		// TODO Auto-generated method stub
		((ViewPager)container).removeView(imageViews.get(position));
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		((ViewPager)container).addView(imageViews.get(position));
		return imageViews.get(position);
	}
}
