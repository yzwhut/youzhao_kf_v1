package youzhao.kuaifang;

import java.security.PublicKey;

import youzhao.kuaifang.fragment.FragmentActivitySecond;
import youzhao.kuaifang.fragment.FragmentFstPager;
import youzhao.kuaifang.fragment.FragmentMine;
import youzhao.kuaifang.fragment.FragmentShoppingCart;
import android.R.layout;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;


public class MainActivity extends FragmentActivity{
	private FrameLayout mainFrameLayout;
	private FragmentTransaction ft;
	private FragmentFstPager firstPager;
	private FragmentActivitySecond activity;
	private FragmentShoppingCart shoppingCart;
	private FragmentMine mine;
	private TextView main_fisrtPage, main_activity, main_shopping, main_mine; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mainFrameLayout = (FrameLayout) findViewById(R.id.main_fragments);
		initWidget();
		initFragment();
		initView();
	}
	
	public void initWidget(){
		main_fisrtPage = (TextView) findViewById(R.id.main_fisrtPage);
		main_activity = (TextView) findViewById(R.id.main_activity);
		main_shopping = (TextView) findViewById(R.id.main_shopping);
		main_mine = (TextView) findViewById(R.id.main_mine);
	}
	
	public void initFragment(){
		firstPager = new FragmentFstPager();
		if (firstPager == null) {
			Log.e("kuaifang=========", "MainActivity firstPager is null");
		}
		activity = new FragmentActivitySecond();
		if (activity == null) {
			Log.e("kuaifang=========", "MainActivity activity is null");
		}
		shoppingCart = new FragmentShoppingCart();
		if (shoppingCart == null) {
			Log.e("kuaifang=========", "MainActivity shoppingCart is null");
		}
		mine = new FragmentMine();
		if (mine == null) {
			Log.e("kuaifang=========", "MainActivity mine is null");
		}
	}
	
	public void initView(){
		
		//将首页的导航设置为红色
		main_fisrtPage.setTextColor(getResources().getColor(R.color.red));
		Drawable nav_up=getResources().getDrawable(R.drawable.taba_icon1);  
		nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());  
		main_fisrtPage.setCompoundDrawables(null, nav_up, null, null);  //图片依次为左上右下
		
		ft= getSupportFragmentManager().beginTransaction();		
		ft.add(R.id.main_fragments, firstPager);
		//ft.commit();
		Intent intent = getIntent();
		int fragmentId = intent.getIntExtra("fragmentId", 1);
		switch (fragmentId) {
		case 1:
			ft.replace(R.id.main_fragments, firstPager);
			break;
		case 2:
			ft.replace(R.id.main_fragments, activity);
			break;
		case 3:
			ft.replace(R.id.main_fragments, shoppingCart);
			break;
		case 4:
			ft.replace(R.id.main_fragments, mine);
			break;

		default:
			ft.replace(R.id.main_fragments, firstPager);
			break;
		}
		ft.commit();
	}
	
	public void click(View v){
		FragmentTransaction fTransaction = getSupportFragmentManager().beginTransaction();
		switch (v.getId()) {
		case R.id.main_fisrtPage:
			fragmentChange(R.id.main_fisrtPage);
			fTransaction.replace(R.id.main_fragments, firstPager);
			break;
		case R.id.main_activity:
			fragmentChange(R.id.main_activity);
			fTransaction.replace(R.id.main_fragments, activity);
			break;
		case R.id.main_shopping:
			fragmentChange(R.id.main_shopping);
			fTransaction.replace(R.id.main_fragments, shoppingCart);		
			break;
		case R.id.main_mine:
			fragmentChange(R.id.main_mine);
			fTransaction.replace(R.id.main_fragments, mine);
			break;
			
		default:
			break;
		}
		fTransaction.commit();
	}
	
	public void fragmentChange(int widgetId){
		if (R.id.main_fisrtPage == widgetId) {
			//对应的是哪个导航，就将哪个设置为红色，其他三个设置为默认黑色
			main_fisrtPage.setTextColor(getResources().getColor(R.color.red));
			Drawable nav_up=getResources().getDrawable(R.drawable.taba_icon1);  
			nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());  
			main_fisrtPage.setCompoundDrawables(null, nav_up, null, null);  //图片依次为左上右下
			
			main_activity.setTextColor(getResources().getColor(R.color.gray));
			Drawable main_activity_up=getResources().getDrawable(R.drawable.tabb_icon2);  
			main_activity_up.setBounds(0, 0, main_activity_up.getMinimumWidth(), main_activity_up.getMinimumHeight());  
			main_activity.setCompoundDrawables(null, main_activity_up, null, null);  //图片依次为左上右下
			
			main_shopping.setTextColor(getResources().getColor(R.color.gray));
			Drawable main_shopping_up=getResources().getDrawable(R.drawable.tabc_icon2);  
			main_shopping_up.setBounds(0, 0, main_shopping_up.getMinimumWidth(), main_shopping_up.getMinimumHeight());  
			main_shopping.setCompoundDrawables(null, main_shopping_up, null, null);  //图片依次为左上右下
			
			main_mine.setTextColor(getResources().getColor(R.color.gray));
			Drawable main_mine_up=getResources().getDrawable(R.drawable.tabd_icon2);  
			main_mine_up.setBounds(0, 0, main_mine_up.getMinimumWidth(), main_mine_up.getMinimumHeight());  
			main_mine.setCompoundDrawables(null, main_mine_up, null, null);  //图片依次为左上右下			
		}
		
		if (R.id.main_activity == widgetId) {
			//对应的是哪个导航，就将哪个设置为红色，其他三个设置为默认黑色
			main_fisrtPage.setTextColor(getResources().getColor(R.color.gray));
			Drawable nav_up=getResources().getDrawable(R.drawable.taba_icon2);  
			nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());  
			main_fisrtPage.setCompoundDrawables(null, nav_up, null, null);  //图片依次为左上右下
			
			main_activity.setTextColor(getResources().getColor(R.color.red));
			Drawable main_activity_up=getResources().getDrawable(R.drawable.tabb_icon1);  
			main_activity_up.setBounds(0, 0, main_activity_up.getMinimumWidth(), main_activity_up.getMinimumHeight());  
			main_activity.setCompoundDrawables(null, main_activity_up, null, null);  //图片依次为左上右下
			
			main_shopping.setTextColor(getResources().getColor(R.color.gray));
			Drawable main_shopping_up=getResources().getDrawable(R.drawable.tabc_icon2);  
			main_shopping_up.setBounds(0, 0, main_shopping_up.getMinimumWidth(), main_shopping_up.getMinimumHeight());  
			main_shopping.setCompoundDrawables(null, main_shopping_up, null, null);  //图片依次为左上右下
			
			main_mine.setTextColor(getResources().getColor(R.color.gray));
			Drawable main_mine_up=getResources().getDrawable(R.drawable.tabd_icon2);  
			main_mine_up.setBounds(0, 0, main_mine_up.getMinimumWidth(), main_mine_up.getMinimumHeight());  
			main_mine.setCompoundDrawables(null, main_mine_up, null, null);  //图片依次为左上右下			
		}
		
		if (R.id.main_shopping == widgetId) {
			//对应的是哪个导航，就将哪个设置为红色，其他三个设置为默认黑色
			main_fisrtPage.setTextColor(getResources().getColor(R.color.gray));
			Drawable nav_up=getResources().getDrawable(R.drawable.taba_icon2);  
			nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());  
			main_fisrtPage.setCompoundDrawables(null, nav_up, null, null);  //图片依次为左上右下
			
			main_activity.setTextColor(getResources().getColor(R.color.gray));
			Drawable main_activity_up=getResources().getDrawable(R.drawable.tabb_icon2);  
			main_activity_up.setBounds(0, 0, main_activity_up.getMinimumWidth(), main_activity_up.getMinimumHeight());  
			main_activity.setCompoundDrawables(null, main_activity_up, null, null);  //图片依次为左上右下
			
			main_shopping.setTextColor(getResources().getColor(R.color.red));
			Drawable main_shopping_up=getResources().getDrawable(R.drawable.tabc_icon1);  
			main_shopping_up.setBounds(0, 0, main_shopping_up.getMinimumWidth(), main_shopping_up.getMinimumHeight());  
			main_shopping.setCompoundDrawables(null, main_shopping_up, null, null);  //图片依次为左上右下
			
			main_mine.setTextColor(getResources().getColor(R.color.gray));
			Drawable main_mine_up=getResources().getDrawable(R.drawable.tabd_icon2);  
			main_mine_up.setBounds(0, 0, main_mine_up.getMinimumWidth(), main_mine_up.getMinimumHeight());  
			main_mine.setCompoundDrawables(null, main_mine_up, null, null);  //图片依次为左上右下			
		}
		
		if (R.id.main_mine == widgetId) {
			//对应的是哪个导航，就将哪个设置为红色，其他三个设置为默认黑色
			main_fisrtPage.setTextColor(getResources().getColor(R.color.gray));
			Drawable nav_up=getResources().getDrawable(R.drawable.taba_icon2);  
			nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());  
			main_fisrtPage.setCompoundDrawables(null, nav_up, null, null);  //图片依次为左上右下
			
			main_activity.setTextColor(getResources().getColor(R.color.gray));
			Drawable main_activity_up=getResources().getDrawable(R.drawable.tabb_icon2);  
			main_activity_up.setBounds(0, 0, main_activity_up.getMinimumWidth(), main_activity_up.getMinimumHeight());  
			main_activity.setCompoundDrawables(null, main_activity_up, null, null);  //图片依次为左上右下
			
			main_shopping.setTextColor(getResources().getColor(R.color.gray));
			Drawable main_shopping_up=getResources().getDrawable(R.drawable.tabc_icon2);  
			main_shopping_up.setBounds(0, 0, main_shopping_up.getMinimumWidth(), main_shopping_up.getMinimumHeight());  
			main_shopping.setCompoundDrawables(null, main_shopping_up, null, null);  //图片依次为左上右下
			
			main_mine.setTextColor(getResources().getColor(R.color.red));
			Drawable main_mine_up=getResources().getDrawable(R.drawable.tabd_icon1);  
			main_mine_up.setBounds(0, 0, main_mine_up.getMinimumWidth(), main_mine_up.getMinimumHeight());  
			main_mine.setCompoundDrawables(null, main_mine_up, null, null);  //图片依次为左上右下			
		}
	
	}
		
}
