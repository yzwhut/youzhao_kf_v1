package youzhao.kuaifang;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.bumptech.glide.Glide;

import youzhao.kuaifang.adapter.FamilyActivityAdapter;
import youzhao.kuaifang.adapter.MainActivityGridViewAdapter;
import youzhao.kuaifang.mywidget.MyGridView;
import youzhao.kuaifang.utils.BitmapCache;
import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;

public class FamilyActivity extends Activity {
	private ImageView familyAd, familyBack;
	private MyGridView familyGV;
	private String url = "http://okm.kfyao.com/v3/zhuti/24/images/chunjiebuday_01.jpg";
	private RequestQueue mQueue;
	private BitmapCache cache;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_family);
		initWidget();
		initAd();
		initGv();
	}
	
	public void initWidget(){
		familyAd = (ImageView) findViewById(R.id.family_store_ad);
		familyBack = (ImageView) findViewById(R.id.family_store_back);
		familyGV = (MyGridView) findViewById(R.id.family_store_gv);
		mQueue = Volley.newRequestQueue(this);
		cache = new BitmapCache();
	}
	
	public void initAd(){
		
		ImageLoader loader = new ImageLoader(mQueue, cache);
		ImageListener listener = loader.getImageListener(familyAd, R.drawable.ic_empty,
				R.drawable.ic_empty);
		loader.get(url, listener);
//		Glide.with(this).load(url).placeholder(R.drawable.ic_empty).error(R.drawable.ic_empty).
//		into(familyAd);
	}
	
	public void initGv(){
		//String[] imageurls = new String[22];
		List<String> imageurls = new ArrayList<String>();
		//GridView内容是从第二章图片开始的
		Log.i("kuaifang========", "in FamilyActivity in method initGv before for ");
		for (int i = 2; i <= 23; i++) {	
			//接下来是2到9图片的展示，因为字段里面是02-09，故而要单独拿出来,第一张图片在上面已经展示出来
			if (i <= 9) {
				//imageurls[i-2] = "http://okm.kfyao.com/v3/zhuti/24/images/chunjiebuday_" + "0" + i + ".jpg";
				imageurls.add("http://okm.kfyao.com/v3/zhuti/24/images/chunjiebuday_0" + i + ".jpg");
			}else {
				//imageurls[i-2] = "http://okm.kfyao.com/v3/zhuti/24/images/chunjiebuday_" + i + ".jpg";
				imageurls.add("http://okm.kfyao.com/v3/zhuti/24/images/chunjiebuday_" + i + ".jpg");
			}			
		}
		Log.i("kuaifang========", (imageurls == null) + "" + imageurls);
//		MainActivityGridViewAdapter adapter = new MainActivityGridViewAdapter(imageurls, this, cache, mQueue);
//		familyGV.setAdapter(adapter);
		
		Log.i("kuaifang========", "in FamilyActivity in method initGv after for ");
		FamilyActivityAdapter adapter = new FamilyActivityAdapter(imageurls, this, cache, mQueue);
		Log.i("kuaifang========", "in FamilyActivity in method initGv before setAdapter ");
		Log.i("kuaifang========", (adapter == null) + "");
		Log.i("kuaifang========", (familyGV == null) + "");
		familyGV.setAdapter(adapter);
		Log.i("kuaifang========", "in FamilyActivity in method initGv after setAdapter ");
		
	}
	
}
