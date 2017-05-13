package youzhao.kuaifang.fragment;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import youzhao.kuaifang.R;
import youzhao.kuaifang.adapter.MainActivityGridViewAdapter;
import youzhao.kuaifang.application.MyApplication;
import youzhao.kuaifang.mainactivitytemp.TempData;
import youzhao.kuaifang.mywidget.MyGridView;
import youzhao.kuaifang.utils.BitmapCache;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;

public class FragmentActivitySecond extends Fragment{
	private RequestQueue mQueue;
	private BitmapCache cache;
	private View view;
	private ImageView ad, imageSeasonMedicine, imageBrandsDiscount;
	private MyGridView specialOffer, seasonMedicine, brandDiscount;
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.main_activity_second, null);
		initwidget();
		initAd();
		Log.i("kuaifang=======", "before initSpecialOffer FragmentActivity has error");
		initSpecialOffer();
		initSeasonMedicine();
		initBrandsDiscount();		
		Log.i("kuaifang=======", "FragmentActivity has error");
		return view;
	}
	
	public void initwidget(){		
		ad = (ImageView) view.findViewById(R.id.main_activity_ad);
		imageSeasonMedicine = (ImageView) view.findViewById(R.id.image_season_medicine);
		imageBrandsDiscount = (ImageView) view.findViewById(R.id.image_brands_discount);
		specialOffer = (MyGridView) view.findViewById(R.id.spacial_offer);
		seasonMedicine = (MyGridView) view.findViewById(R.id.season_medicine);
		brandDiscount = (MyGridView) view.findViewById(R.id.brands_discount);
		mQueue = Volley.newRequestQueue(getActivity());
		cache = new BitmapCache();
	}
	
	public void initAd(){
		ImageLoader loader = new ImageLoader(mQueue, cache);
		ImageView[] imageViews = {ad, imageSeasonMedicine, imageBrandsDiscount};
		for (int i = 0; i < imageViews.length; i++) {
			ImageListener listener = loader.getImageListener(imageViews[i], R.drawable.ic_empty, R.drawable.ic_empty);
			loader.get(TempData.ADS[i], listener);
		}
		
	}
	
	public void initSpecialOffer(){

		MainActivityGridViewAdapter adapter = new MainActivityGridViewAdapter(TempData.SPECIAL_STRINGS, getActivity(),
				cache, mQueue);
		specialOffer.setAdapter(adapter);
		
	}
	
	public void initSeasonMedicine(){	
		MainActivityGridViewAdapter adapter = new MainActivityGridViewAdapter(TempData.SEASON_STRINGS, getActivity(),
				cache, mQueue);
		seasonMedicine.setAdapter(adapter);
		
	}
	
	public void initBrandsDiscount(){

		MainActivityGridViewAdapter adapter = new MainActivityGridViewAdapter(TempData.BRAND_STRINGS, getActivity(),
				cache, mQueue);
		brandDiscount.setAdapter(adapter);
		
	}
}
