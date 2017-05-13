package youzhao.kuaifang.fragment;


import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import youzhao.kuaifang.CheckMapAreaActivity;
import youzhao.kuaifang.OrderOfWaitToPayActivity;
import youzhao.kuaifang.R;
import youzhao.kuaifang.adapter.MineMyOrdersListViewItem;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FragmentMine extends Fragment{
	private ImageView mine_setup, mine_register_and_login_forward;
	private TextView mine_register_and_login_user_name, mine_my_order_current, mine_my_order_wait_to_pay,
	mine_my_order_finished , mine_my_sevice_address_manage, mine_my_sevice_coupon, mine_my_sevice_mymessage,
	mine_my_systemsevice_check_assess, mine_my_systemsevice_addess_range, mine_my_systemsevice_recommendgift;
	private RelativeLayout mine_my_order, mine_my_sevice, mine_my_systemsevice, mine_custom_telephonenumber;
	private ListView mine_my_ordier_listview;
	private View view;
	private RequestQueue mQueue;
	private String url = "http://okm.kfyao.com/v3/up.php";
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.main_firstpage_mine, null);
		initWidget();
		initOrders();
		mine_my_order_wait_to_pay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), OrderOfWaitToPayActivity.class);
				getActivity().startActivity(intent);
			}
		});
		mine_my_systemsevice_addess_range.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), CheckMapAreaActivity.class);
				getActivity().startActivity(intent);
			}
		});
		return view;
		
	}
	
	public void initWidget(){
		mine_setup = (ImageView) view.findViewById(R.id.mine_setup);
		mine_register_and_login_forward = (ImageView) view.findViewById(R.id.mine_register_and_login_forward);
		
		mine_register_and_login_user_name = (TextView) view.findViewById(R.id.mine_register_and_login_user_name);
		mine_my_order_current = (TextView) view.findViewById(R.id.mine_my_order_current);
		mine_my_order_wait_to_pay = (TextView) view.findViewById(R.id.mine_my_order_wait_to_pay);
		mine_my_order_finished = (TextView) view.findViewById(R.id.mine_my_order_finished); 
		mine_my_sevice_address_manage = (TextView) view.findViewById(R.id.mine_my_sevice_address_manage); 
		mine_my_sevice_coupon = (TextView) view.findViewById(R.id.mine_my_sevice_coupon);
		mine_my_sevice_mymessage = (TextView) view.findViewById(R.id.mine_my_sevice_mymessage);
		mine_my_systemsevice_check_assess = (TextView) view.findViewById(R.id.mine_my_systemsevice_check_assess); 
		mine_my_systemsevice_addess_range = (TextView) view.findViewById(R.id.mine_my_systemsevice_addess_range); 
		mine_my_systemsevice_recommendgift = (TextView) view.findViewById(R.id.mine_my_systemsevice_recommendgift);
		
		mine_my_order = (RelativeLayout) view.findViewById(R.id.mine_my_order);
		mine_my_sevice = (RelativeLayout) view.findViewById(R.id.mine_my_sevice);
		mine_my_systemsevice = (RelativeLayout) view.findViewById(R.id.mine_my_systemsevice);
		mine_custom_telephonenumber = (RelativeLayout) view.findViewById(R.id.mine_custom_telephonenumber);
		
		mine_my_ordier_listview = (ListView) view.findViewById(R.id.mine_my_ordier_listview);
		
		mQueue = Volley.newRequestQueue(getActivity());
	}
	
	public void initOrders(){
		StringRequest request = new StringRequest(Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String arg0) {
				// TODO Auto-generated method stub
				//得到最外层的类
				List<String> orderNumber = new ArrayList<String>();
				List<String> orderReceiver = new ArrayList<String>();
				List<String> orderSum = new ArrayList<String>();
				JsonObject data = new JsonParser().parse(arg0).getAsJsonObject();
				
				/**
				 * 然后直接拿到FK字段的数据，判断此数据是字符串还是数组，如果是String类型的，那就说明没有订单数据
				 * 如果是JsonArray的话，就说明有订单数据，需要展示
				 * */
				if (data.get("FK").isJsonPrimitive()) {
					mine_my_ordier_listview.setVisibility(View.GONE);
				}
				if (data.get("FK").isJsonArray()) {
					mine_my_ordier_listview.setVisibility(View.VISIBLE);
					JsonArray orderInfos = data.get("FK").getAsJsonArray();
					//
					for (JsonElement jsonElement : orderInfos) {
						orderNumber.add(jsonElement.getAsJsonObject().get("ID").getAsString());
						orderReceiver.add(jsonElement.getAsJsonObject().get("NT").getAsString());
						orderSum.add(jsonElement.getAsJsonObject().get("JE").getAsString());
					}
					MineMyOrdersListViewItem adapter = new MineMyOrdersListViewItem(orderNumber, orderReceiver, orderSum, getActivity());
					mine_my_ordier_listview.setAdapter(adapter);
					//动态计算listView控件的高度
					setListViewHeightBasedOnChildren(mine_my_ordier_listview);
				}
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				
			}
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				Map<String, String> map = new HashMap<String, String>();
				map.put("tk", "031ca39d61ab7b9347d68a8011630945f77075b8");			
				map.put("mno", "18600540893");
				map.put("at", "a");
				map.put("mk", "6601C559FA2F6D1315727D61885586C4");
				map.put("ty", "7");
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
}
