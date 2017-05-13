package youzhao.kuaifang;


import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import youzhao.kuaifang.MyImageView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class WelcomeActivity extends Activity {
	private ViewPager welPager;
	//private MyImageView welDotsFst, welDotsScd, welDotsThd, welDotsFth;
	private int[] dotsId = {R.id.wel_dots_fst, R.id.wel_dots_scd, R.id.wel_dots_thd, R.id.wel_dots_fth}; 
	private List<MyImageView> imageViews = new ArrayList<MyImageView>();
	private List<View> views = new ArrayList<View>();
	private int[] welLayouts = {R.layout.welcome_first, R.layout.welcome_second, R.layout.welcome_third, R.layout.welcome_forth};
	private ImageView welFthJump, welFthCheck;
	private MyImageView  wel_dots_fst, wel_dots_scd, wel_dots_thd, wel_dots_fth;
	private LinearLayout welcome_dots;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		initWidget();
		initView();
		welPager.setAdapter(new MyPagerAdapter(views, imageViews));
		welPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				initdots(arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void initdots(int index){
		switch (index) {
		case 0:
			welcome_dots.setVisibility(View.VISIBLE);
			wel_dots_fst.setImageResource(R.drawable.enable_true);
			wel_dots_scd.setImageResource(R.drawable.enable_false);
			wel_dots_thd.setImageResource(R.drawable.enable_false);
			wel_dots_fth.setImageResource(R.drawable.enable_false);
			break;
		case 1:
			welcome_dots.setVisibility(View.VISIBLE);
			wel_dots_fst.setImageResource(R.drawable.enable_false);
			wel_dots_scd.setImageResource(R.drawable.enable_true);
			wel_dots_thd.setImageResource(R.drawable.enable_false);
			wel_dots_fth.setImageResource(R.drawable.enable_false);
			break;
		case 2:
			welcome_dots.setVisibility(View.VISIBLE);
			wel_dots_fst.setImageResource(R.drawable.enable_false);
			wel_dots_scd.setImageResource(R.drawable.enable_false);
			wel_dots_thd.setImageResource(R.drawable.enable_true);
			wel_dots_fth.setImageResource(R.drawable.enable_false);			
			break;
		case 3:
			welcome_dots.setVisibility(View.GONE);
			break;

		default:
			break;
		}
	}
	
	public void initWidget(){
		
		welcome_dots = (LinearLayout) findViewById(R.id.welcome_dots);
		
		wel_dots_fst = (MyImageView) findViewById(R.id.wel_dots_fst);
		wel_dots_scd = (MyImageView) findViewById(R.id.wel_dots_scd);
		wel_dots_thd = (MyImageView) findViewById(R.id.wel_dots_thd);
		wel_dots_fth = (MyImageView) findViewById(R.id.wel_dots_fth);
		
		welPager = (ViewPager) findViewById(R.id.welcomePager);
		for (int i = 0; i < dotsId.length; i++) {
			MyImageView imageView = (MyImageView) findViewById(dotsId[i]);
			imageViews.add(imageView);
		}
		welFthJump = (ImageView) findViewById(R.id.wel_forth_jump);
		welFthCheck = (ImageView) findViewById(R.id.wel_forth_check);
	}
	
	public void initView(){
		LayoutInflater layoutInflater = getLayoutInflater();
		for (int i = 0; i < welLayouts.length; i++) {
			View view = layoutInflater.inflate(welLayouts[i], null);
			views.add(view);
		}
	}
	
	public void click(View v){
		switch (v.getId()) {
		case R.id.wel_forth_jump:
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			break;
		case R.id.wel_forth_check:
			Intent intent2 = new Intent(this, MainActivity.class);
			startActivity(intent2);
		default:
			break;
		}
	}

	class MyPagerAdapter extends PagerAdapter{
		private List<View> views;
		private List<MyImageView> imageViews;
		
		public MyPagerAdapter(List<View> views, List<MyImageView> imageViews){
			this.views = views;
			this.imageViews = imageViews;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return views.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			container.addView(views.get(position));			
			return views.get(position);
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView(views.get(position));
		}
		
	}

	
}
