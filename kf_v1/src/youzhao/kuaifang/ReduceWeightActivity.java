package youzhao.kuaifang;

import java.util.ArrayList;
import java.util.List;

import youzhao.kuaifang.adapter.FamilyActivityAdapter;
import youzhao.kuaifang.adapter.MainActivityGridViewAdapter;
import youzhao.kuaifang.utils.BitmapCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;

public class ReduceWeightActivity extends Activity {
	private ListView reduceWeightLv;
	private ImageView reduceWeightBack;
	//这个里面只有8张图片，4张是药品，里面有ID需要传到下级页面
	private String url = "http://okm.kfyao.com/v3/zhuti/26/images/ss_01.jpg";
	private RequestQueue mQueue;
	private BitmapCache cache;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reduce_weight);
		initWidget();
		initLv();
	}
	
	public void initWidget(){
		reduceWeightLv = (ListView) findViewById(R.id.reduce_weight_lv);
		reduceWeightBack = (ImageView) findViewById(R.id.reduce_weight_back);
		mQueue = Volley.newRequestQueue(this);
		cache = new BitmapCache();
	}
	
	public void initLv(){
		List<String> imageurls = new ArrayList<String>();
		for (int i = 1; i <= 8; i++) {
			imageurls.add("http://okm.kfyao.com/v3/zhuti/26/images/ss_0" + i + ".jpg");;				
		}
		FamilyActivityAdapter adapter = new FamilyActivityAdapter(imageurls, this, cache, mQueue);
		reduceWeightLv.setAdapter(adapter);
	}

	
}
