package youzhao.kuaifang.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import youzhao.kuaifang.ConfirmOrdersActivity;
import youzhao.kuaifang.GoodDetailsActivity;
import youzhao.kuaifang.MainActivity;
import youzhao.kuaifang.R;
import youzhao.kuaifang.adapter.ShoppingcartMedicineList;
import youzhao.kuaifang.beans.shoppingcart.Medicine;
import youzhao.kuaifang.utils.BitmapCache;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentShoppingCart extends Fragment{
	private View view;
	private RequestQueue mQueue;
	private BitmapCache cache;
	private String url = "http://okm.kfyao.com/v3/ck.php";
	private ListView medicinelv;
	private RelativeLayout shoppingcart_is_empty_relativelayout, shoppingcart_sum;
	private Button begin_scan;
	private ImageView choose_all_button;
	private TextView price_sum, small_price_sum, go_pay;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i("kuaifang=======", "in FragmentShoppingCart at the start");
		view = inflater.inflate(R.layout.main_firstpage_shoppingcart, null);
		initWidget();
		Log.i("kuaifang=======", "in FragmentShoppingCart before initView()");
		initview();
		Log.i("kuaifang=======", "in FragmentShoppingCart after initView()");
		go_pay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("kuaifang=======", "in FragmentShoppingcart click to pay");
				Toast.makeText(getActivity(), "go pay", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getActivity(), ConfirmOrdersActivity.class);
				getActivity().startActivity(intent);
			}
		});
		return view;
	}
	
	public void initWidget(){
		medicinelv = (ListView) view.findViewById(R.id.shoppingcart_medicine_lv);
		
		//购物车为空的时候要显示的画面
		shoppingcart_is_empty_relativelayout = (RelativeLayout) view.findViewById(R.id.shoppingcart_is_empty_relativelayout);
		//购物车不为空的时候下面要显示的商品的总价
		shoppingcart_sum = (RelativeLayout) view.findViewById(R.id.shoppingcart_sum);
		
		begin_scan = (Button) view.findViewById(R.id.begin_scan);
		
		choose_all_button = (ImageView) view.findViewById(R.id.choose_all_button);
		
		price_sum = (TextView) view.findViewById(R.id.price_sum);
		small_price_sum = (TextView) view.findViewById(R.id.small_price_sum);
		go_pay = (TextView) view.findViewById(R.id.go_pay);
		
		mQueue = Volley.newRequestQueue(getActivity()); 
		cache = new BitmapCache();
	}
	
	public void initview(){
		
		StringRequest request = new StringRequest(Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String arg0) {
				// TODO Auto-generated method stub
				List<Medicine> medicines = parse(arg0);		
				List<String> medicineNames = new ArrayList<String>();
				List<String> medicinePics = new ArrayList<String>(); 
				List<String> medicineNumbers = new ArrayList<String>();
				List<String> medicinePrices = new ArrayList<String>();
				List<String> medicineWantBuys = new ArrayList<String>();
				//下面的集合为当前购物车中药品的PID集合，目的是点击每个Item的时候跳转到此药品详情页面
				final List<String> medicinePID = new ArrayList<String>();
				String sumPrice = medicines.get(0).getYFJE();
				Log.i("kuaifang=======", sumPrice);
				//判断总价是否为0，如果为0，那说明购物车为空，展示空的
				if (sumPrice.equals("0") ) {
					shoppingcart_is_empty_relativelayout.setVisibility(View.VISIBLE);
					shoppingcart_sum.setVisibility(View.GONE);
					medicinelv.setVisibility(View.GONE);
				}else {
					shoppingcart_is_empty_relativelayout.setVisibility(View.GONE);
					shoppingcart_sum.setVisibility(View.VISIBLE);
					medicinelv.setVisibility(View.VISIBLE);
					//接下来就是要将两个金额展示出来					
					price_sum.setText(sumPrice);
					small_price_sum.setText(sumPrice);
					//然后就是将药品列表展示出来
					for (int i = 0; i < medicines.size(); i++) {
						//得到每一个药品的名称
						medicineNames.add(medicines.get(i).getTL());
						medicinePics.add(medicines.get(i).getPC1());
						Log.i("kuaifang========", medicines.get(i).getPC1());
						medicineNumbers.add(medicines.get(i).getMS());
						medicinePrices.add(medicines.get(i).getPR());
						medicineWantBuys.add(medicines.get(i).getQTY());
						
						medicinePID.add(medicines.get(i).getPID());
					}
				}
				ShoppingcartMedicineList adapter = new ShoppingcartMedicineList(medicineNames,
						medicinePics, medicineNumbers, medicinePrices, medicineWantBuys
						, getActivity(), mQueue, cache);
				medicinelv.setAdapter(adapter);
				//接下来是给listview设置点击事件
//				medicinelv.setOnItemClickListener(new OnItemClickListener() {
//
//					@Override
//					public void onItemClick(AdapterView<?> parent, View view,
//							int position, long id) {
//						// TODO Auto-generated method stub
//						Intent intent = new Intent(getActivity(), GoodDetailsActivity.class);
//						intent.putExtra("PID", medicinePID.get(position));
//						startActivity(intent);
//					}
//				});
				listViewItemOnClick(medicinelv, medicinePID);
			}
		                                                                                                                                    
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				Log.i("kuaifang==========", "in FragmentShoppingCart volley get Data is wrong");
			}
		}){
			//进行post请求时，post请求的body部分
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				//这里的参数和添加到购物车里面的参数相比，多了一个Pg，然后ty的value值从1变成了3
				Log.i("kuaifang==========", "in FragmentShoppingCart at the start of method getParams ");
				Map<String, String> map = new HashMap<String, String>();
				map.put("pg", "1");	
				map.put("tk", "031ca39d61ab7b9347d68a8011630945f77075b8");			
				map.put("mno", "18600540893");
				map.put("at", "a");
				map.put("mk", "6601C559FA2F6D1315727D61885586C4");
				map.put("ty", "3");
				map.put("ps", "88abbed20a4421e5b923e5fcaed92a44");
				return map;
			}					
		};
		mQueue.add(request);				
	}
	
	public List<Medicine> parse(String json){
		Gson gson = new Gson();
		List<Medicine> medicines = gson.fromJson(json, new TypeToken<List<Medicine>>(){}.getType());
		return medicines;		
	}
	
	public void listViewItemOnClick(ListView listView, final List<String> list){
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), GoodDetailsActivity.class);
				intent.putExtra("PID", list.get(position));
				startActivity(intent);
			}
		});
	}
	
	//Fragment中onClick属性是有问题的
//	public void onClick(View view){
//		Log.i("kuaifang=======", "in FragmentShoppingcart click to pay");
//		switch (view.getId()) {
//		case R.id.go_pay:
//			Log.i("kuaifang=======", "in FragmentShoppingcart click to pay");
//			Toast.makeText(getActivity(), "go pay", Toast.LENGTH_SHORT).show();
//			Intent intent = new Intent(getActivity(), ConfirmOrdersActivity.class);
//			getActivity().startActivity(intent);
//			break;
//
//		default:
//			break;
//		}
//	}
}
