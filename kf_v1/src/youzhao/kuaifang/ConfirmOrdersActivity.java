package youzhao.kuaifang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import youzhao.kuaifang.adapter.ConfirmOrdersMedicineList;
import youzhao.kuaifang.adapter.ShoppingcartMedicineList;
import youzhao.kuaifang.beans.confirmOrders.ConfirmOrdersData;
import youzhao.kuaifang.beans.confirmOrders.SPCD;
import youzhao.kuaifang.beans.shoppingcart.Medicine;
import youzhao.kuaifang.utils.BitmapCache;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ConfirmOrdersActivity extends Activity {
	private ListView confirm_orders_medicine_lv;
	private ImageView confirm_orders_back;
	private TextView confirm_orders_should_pay, confirm_orders_submit_orders, confirm_orders_price_sum,
			confirm_order_user_name, confirm_order_user_phone_number, confirm_order_user_address_city,
			confirm_order_user_address_building;
	private RelativeLayout confirm_order_user_information, confirm_orders_daofu, confirm_orders_zhifubao,
			confirm_orders_weixing, confirm_orders_coupon; //coupon 优惠券
	private RequestQueue mQueue;	
	private BitmapCache cache;
	private String url = "http://okm.kfyao.com/v3/ck.php";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirm_orders);
		initWidget();
		getDataByVolley();
	}
	
	public void initWidget(){
		confirm_orders_medicine_lv = (ListView) findViewById(R.id.confirm_orders_medicine_lv);
		
		confirm_orders_back = (ImageView) findViewById(R.id.confirm_orders_back);
		
		confirm_orders_should_pay = (TextView) findViewById(R.id.confirm_orders_should_pay);
		confirm_orders_submit_orders = (TextView) findViewById(R.id.confirm_orders_submit_orders);
		confirm_orders_price_sum = (TextView) findViewById(R.id.confirm_orders_price_sum);
		confirm_order_user_name = (TextView) findViewById(R.id.confirm_order_user_name);
		confirm_order_user_phone_number = (TextView) findViewById(R.id.confirm_order_user_phone_number);
		confirm_order_user_address_city = (TextView) findViewById(R.id.confirm_order_user_address_city);
		confirm_order_user_address_building = (TextView) findViewById(R.id.confirm_order_user_address_building);
		
		confirm_order_user_information = (RelativeLayout) findViewById(R.id.confirm_order_user_information);
		confirm_orders_daofu = (RelativeLayout) findViewById(R.id.confirm_orders_daofu);
		confirm_orders_zhifubao = (RelativeLayout) findViewById(R.id.confirm_orders_zhifubao);
		confirm_orders_weixing = (RelativeLayout) findViewById(R.id.confirm_orders_weixing);
		confirm_orders_coupon = (RelativeLayout) findViewById(R.id.confirm_orders_coupon);
		
		mQueue = Volley.newRequestQueue(ConfirmOrdersActivity.this);
		cache = new BitmapCache();
	}
	
	public void getDataByVolley(){
		StringRequest request = new StringRequest(Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String arg0) {
				// TODO Auto-generated method stub
				//得到最外层的类
				 JsonObject data = new JsonParser().parse(arg0).getAsJsonObject();
				 //得到订单联系人的信息的类
				 JsonObject userInfo = data.get("USIN").getAsJsonObject();
				 //接下来就是订单用户信息的展示
				 confirm_order_user_name.setText(userInfo.get("SNT").getAsString());
				 confirm_order_user_phone_number.setText(userInfo.get("TEL").getAsString());
				 confirm_order_user_address_city.setText(userInfo.get("XQ").getAsString());
				 confirm_order_user_address_building.setText(userInfo.get("ADD").getAsString());
				 //然后是展示订单的总额
				 confirm_orders_should_pay.setText(data.get("YFJE").getAsString());
				 confirm_orders_price_sum.setText(data.get("YFJE").getAsString());
				 //接下来就是展示需要提交的药品信息列表,首先要得到药品列表的数据
				 List<String> medicineNames = new ArrayList<String>();
				 List<String> medicinePics = new ArrayList<String>();
				 List<String> medicineNumbers = new ArrayList<String>();
				 List<String> medicinePrices = new ArrayList<String>();
				 List<String> medicineSpec = new ArrayList<String>();
				 Gson gson = new Gson();
				 JsonArray medicines = data.get("SPCD").getAsJsonArray();
				 for (JsonElement jsonElement : medicines) {	
					 JsonObject medicine = jsonElement.getAsJsonObject(); 
					 medicineNames.add(medicine.get("TL").getAsString());
					 medicinePics.add(medicine.get("PC1").getAsString());
					 medicineNumbers.add(medicine.get("QTY").getAsString());
					 medicinePrices.add(medicine.get("PR").getAsString());
					 medicineSpec.add(medicine.get("MS").getAsString());
				}
				 ConfirmOrdersMedicineList adapter = new ConfirmOrdersMedicineList(medicineNames, medicinePics, medicineNumbers, medicinePrices, medicineSpec, ConfirmOrdersActivity.this, mQueue, cache);
				 confirm_orders_medicine_lv.setAdapter(adapter);
				 //动态计算listView控件的高度
				 setListViewHeightBasedOnChildren(confirm_orders_medicine_lv);
			}                                                                                                                                
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				Log.i("kuaifang==========", "in ConfirmOrdersActivity volley get Data is wrong");
			}
		}){
			//进行post请求时，post请求的body部分
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				//这里的参数和添加到购物车里面的参数相比，ty的value值从1变成了4
				Log.i("kuaifang==========", "in ConfirmOrdersActivity at the start of method getParams ");
				Map<String, String> map = new HashMap<String, String>();
				map.put("tk", "031ca39d61ab7b9347d68a8011630945f77075b8");			
				map.put("mno", "18600540893");
				map.put("at", "a");
				map.put("mk", "6601C559FA2F6D1315727D61885586C4");
				map.put("ty", "4");
				map.put("ps", "88abbed20a4421e5b923e5fcaed92a44");
				return map;
			}					
		};
		mQueue.add(request);		
	}
	
	public void setListViewHeightBasedOnChildren(ListView listView) {   
        // 获取ListView对应的Adapter   
        ListAdapter listAdapter = listView.getAdapter();   
        if (listAdapter == null) {   
            return;   
        }   
   
        int totalHeight = 0;   
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {   
            // listAdapter.getCount()返回数据项的数目   
            View listItem = listAdapter.getView(i, null, listView);   
            // 计算子项View 的宽高   
            listItem.measure(0, 0);    
            // 统计所有子项的总高度   
            totalHeight += listItem.getMeasuredHeight();    
        }   
   
        ViewGroup.LayoutParams params = listView.getLayoutParams();   
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));   
        // listView.getDividerHeight()获取子项间分隔符占用的高度   
        // params.height最后得到整个ListView完整显示需要的高度   
        listView.setLayoutParams(params);   
    }   
	
	public void click(View v){
		switch (v.getId()) {
		case R.id.confirm_orders_submit_orders:
			Intent intent = new Intent(ConfirmOrdersActivity.this, PayOrderActivity.class);
			ConfirmOrdersActivity.this.startActivity(intent);
			break;

		default:
			break;
		}
	}
	
	
}
