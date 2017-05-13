package youzhao.kuaifang;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageListener;

import youzhao.kuaifang.adapter.MainActivityGridViewAdapter;
import youzhao.kuaifang.mainactivitytemp.TempData;
import youzhao.kuaifang.mywidget.MyGridView;
import youzhao.kuaifang.utils.BitmapCache;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;

public class SpecialActiveActivity extends Activity {
	private ImageView specialAd, seasonMedicine, brandsDiscount, back;
	private MyGridView specialGV, seasonMedicineGV, brandsDiscountGV;
	private RequestQueue mQueue;
	private BitmapCache cache;
	//这几个数组是每个商品的PID，在跳转到后面的页面需要用这个PID作为参数得到商品详情，第一个页面时Html，需要
	//单独把PID拿出来
	private String[] specialStrings = {"15417", "9750", "462", "13365", "15187",
			"7538", "1823", "7482", "1961"}, 
	seasonStrings = {"710", "469", "337", "3782", "2364", "142", "14272", "2979",
			"3560", "7918", "3217", "3790", "450", "12361", "6032", "4939", "148",
			"935", "16528"},
	brandStrings = {"2988", "1027", "6214", "12120", "14948", "15562"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_special_active);
		initWidget();
		initAd();
		initSpecialOffer();
		initSeasonMedicine();
		initBrandsDiscount();
	}
	
	public void initWidget(){
		specialAd = (ImageView) findViewById(R.id.special_active_ad);
		seasonMedicine = (ImageView) findViewById(R.id.special_image_season_medicine);
		brandsDiscount = (ImageView) findViewById(R.id.special_image_brands_discount);
		back = (ImageView) findViewById(R.id.special_back);
		
		specialGV = (MyGridView) findViewById(R.id.special_spacial_offer);
		seasonMedicineGV = (MyGridView) findViewById(R.id.special_season_medicine);
		brandsDiscountGV = (MyGridView) findViewById(R.id.special_brands_discount);
		
		mQueue = Volley.newRequestQueue(this);
		cache = new BitmapCache();
		
	}
	
	public void initAd(){
		ImageLoader loader = new ImageLoader(mQueue, cache);
		ImageView[] imageViews = {specialAd, seasonMedicine, brandsDiscount};
		for (int i = 0; i < imageViews.length; i++) {
			ImageListener listener = loader.getImageListener(imageViews[i], R.drawable.ic_empty, R.drawable.ic_empty);
			loader.get(TempData.ADS[i], listener);
		}
		
	}
	
	public void initSpecialOffer(){

		MainActivityGridViewAdapter adapter = new MainActivityGridViewAdapter(TempData.SPECIAL_STRINGS, this,
				cache, mQueue);
		specialGV.setAdapter(adapter);
		specialGV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SpecialActiveActivity.this, GoodDetailsActivity.class);
				intent.putExtra("PID", specialStrings[position]);
				SpecialActiveActivity.this.startActivity(intent);
			}
		});
	}
	
	public void initSeasonMedicine(){	
		MainActivityGridViewAdapter adapter = new MainActivityGridViewAdapter(TempData.SEASON_STRINGS, this,
				cache, mQueue);
		seasonMedicineGV.setAdapter(adapter);
		seasonMedicineGV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SpecialActiveActivity.this, GoodDetailsActivity.class);
				intent.putExtra("PID", seasonStrings[position]);
				startActivity(intent);
			}
		});
	}
	
	public void initBrandsDiscount(){

		MainActivityGridViewAdapter adapter = new MainActivityGridViewAdapter(TempData.BRAND_STRINGS, this,
				cache, mQueue);
		brandsDiscountGV.setAdapter(adapter);
		Log.i("kuaifang=========", "in SpecialActiveActivity after setAdapter");
		brandsDiscountGV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Log.i("kuaifang=========", "in SpecialActiveActivity listener before intent created");
				Intent intent = new Intent(SpecialActiveActivity.this, GoodDetailsActivity.class);
				intent.putExtra("PID", brandStrings[position]);
				startActivity(intent);
			}
		});
	}
	

}
