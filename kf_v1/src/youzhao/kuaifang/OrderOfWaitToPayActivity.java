package youzhao.kuaifang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import youzhao.kuaifang.adapter.OrderToPayActivityListviewItemAdapter;
import youzhao.kuaifang.utils.BitmapCache;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;


public class OrderOfWaitToPayActivity extends Activity {
	private ListView order_of_wait_to_pay_activity_listview;
	private ImageView order_of_wait_to_pay_activity_back;
	private RequestQueue mQueue;
	private BitmapCache cache;
	private String url = "http://okm.kfyao.com/v3/ck.php";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_of_wait_to_pay);
		initWidget();
		initlistview();
		Log.i("kuaifang========", "in OrderOfWaitToPayActivity after method initlistview");
	}
	
	public void initWidget(){
		order_of_wait_to_pay_activity_listview = (ListView) findViewById(R.id.order_of_wait_to_pay_activity_listview);
		//order_of_wait_to_pay_activity_listview = (PullToRefreshListView) findViewById(R.id.order_of_wait_to_pay_activity_listview);
		order_of_wait_to_pay_activity_back = (ImageView) findViewById(R.id.order_of_wait_to_pay_activity_back);
		mQueue = Volley.newRequestQueue(OrderOfWaitToPayActivity.this);
		cache = new BitmapCache();
	}
	
	public void initlistview(){
		StringRequest request = new StringRequest(Method.POST, url, new Listener<String>() {
 
			@Override
			public void onResponse(String arg0) {
				// TODO Auto-generated method stub
				Log.i("kuaifang========", "in OrderOfWaitToPayActivity at method initlistview ++++++++" + arg0);
				//这里就是要得到listview中的数据
				List<String> orderNumber = new ArrayList<String>();
				List<String> orderState = new ArrayList<String>();
				List<String> orderSum = new ArrayList<String>();
				List<String> orderTime = new ArrayList<String>();
				List<String> orderMedicinePicsUrls = new ArrayList<String>();
				JsonObject data = new JsonParser().parse(arg0).getAsJsonObject();
				//NR字段就是需要的数组数据
				JsonArray orderWaitToPay = data.get("NR").getAsJsonArray();
				for (JsonElement jsonElement : orderWaitToPay) {
					orderNumber.add(jsonElement.getAsJsonObject().get("ORM").getAsString()); 
					orderState.add(jsonElement.getAsJsonObject().get("STNM").getAsString());
					orderSum.add(jsonElement.getAsJsonObject().get("SP").getAsString());
					orderTime.add(jsonElement.getAsJsonObject().get("CT").getAsString());
					orderMedicinePicsUrls.add(jsonElement.getAsJsonObject().get("MM").getAsString());
				}
				Log.i("kuaifang========", "in OrderOfWaitToPayActivity at method initlistview before adapter created");
				OrderToPayActivityListviewItemAdapter adapter = new OrderToPayActivityListviewItemAdapter(orderNumber, orderState, orderSum, orderTime, orderMedicinePicsUrls, OrderOfWaitToPayActivity.this, mQueue, cache);
				Log.i("kuaifang========", "in OrderOfWaitToPayActivity at method initlistview after adapter created");
				order_of_wait_to_pay_activity_listview.setAdapter(adapter);
				Log.i("kuaifang========", "in OrderOfWaitToPayActivity at method initlistview after setadapter ");
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				Log.i("kuaifang========", "in OrderOfWaitToPayActivity at the method initlistview ,get data is wrong by Volley");
			}
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				Map<String, String> map = new HashMap<String, String>();
				map.put("pg", "1");
				map.put("tk", "031ca39d61ab7b9347d68a8011630945f77075b8");			
				map.put("mno", "18600540893");
				map.put("at", "a");
				map.put("mk", "6601C559FA2F6D1315727D61885586C4");
				map.put("ty", "8");
				map.put("ps", "88abbed20a4421e5b923e5fcaed92a44");
				map.put("cs", "2");
				return map;		
			}
		};
		mQueue.add(request);
		Log.i("kuaifang========", "in OrderOfWaitToPayActivity at method initlistview after add(request)");
	}
	
}
