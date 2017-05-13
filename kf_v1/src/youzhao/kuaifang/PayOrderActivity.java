package youzhao.kuaifang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PayOrderActivity extends Activity implements OnClickListener{
	private TextView pay_order_activity_sum, pay_order_activity_check_order, pay_order_activity_go_pay;
	private ImageView pay_order_activity_back, pay_order_activity_daofu_choose, 
				pay_order_activity_zhifubao_choose, pay_order_activity_weixing_choose;
	private RequestQueue mQueue;
	private String url = "http://okm.kfyao.com/v3/ck.php";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pay_order);
		initWidget();
		initView();
		pay_order_activity_back.setOnClickListener(this);
	}
	
	private void initWidget(){
		pay_order_activity_sum = (TextView) findViewById(R.id.pay_order_activity_sum);
		pay_order_activity_check_order = (TextView) findViewById(R.id.pay_order_activity_check_order);
		pay_order_activity_go_pay = (TextView) findViewById(R.id.pay_order_activity_go_pay);
		
		pay_order_activity_back = (ImageView) findViewById(R.id.pay_order_activity_back);
		pay_order_activity_daofu_choose = (ImageView) findViewById(R.id.pay_order_activity_daofu_choose);
		pay_order_activity_zhifubao_choose = (ImageView) findViewById(R.id.pay_order_activity_zhifubao_choose);
		pay_order_activity_weixing_choose = (ImageView) findViewById(R.id.pay_order_activity_weixing_choose);
		
		mQueue = Volley.newRequestQueue(PayOrderActivity.this);
	}
	
	public void initView(){
		StringRequest request = new StringRequest(Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String arg0) {
				// TODO Auto-generated method stub
				List<String> states = new ArrayList<String>();
				JsonObject data = new JsonParser().parse(arg0).getAsJsonObject();
				String sum = data.get("JE").getAsString();
				pay_order_activity_sum.setText(sum);
				//接下来得到付款方式类型，通过PTY字段里面的MR来判断，为1就是选中状态
				JsonArray array = data.get("PTY").getAsJsonArray();
				for (JsonElement jsonElement : array) {
					String state = jsonElement.getAsJsonObject().get("MR").getAsString();
					states.add(state);
				}
				ImageView[] imageViews = {pay_order_activity_daofu_choose, pay_order_activity_zhifubao_choose,
						pay_order_activity_weixing_choose};
				for (int i = 0; i < states.size(); i++) {
					if (states.get(i).equals("1")) {
						imageViews[i].setImageResource(R.drawable.ico22);
					}
					if (states.get(i).equals("0")) {
						imageViews[i].setImageResource(R.drawable.ico23);
					}
				}
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				Log.i("kuaifang=======", "in PayOrderActivity volley getData encode problems");
				Toast.makeText(PayOrderActivity.this, "网络出现错误", Toast.LENGTH_SHORT).show();
			}
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				Log.i("kuaifang==========", "in ConfirmOrdersActivity at the start of method getParams ");
				Map<String, String> map = new HashMap<String, String>();
				map.put("pt", "z");			
				map.put("tk", "031ca39d61ab7b9347d68a8011630945f77075b8");
				map.put("mno", "18600540893");
				map.put("at", "a");
				map.put("psyf", "0");
				map.put("mk", "6601C559FA2F6D1315727D61885586C4");
				map.put("eid", "0");
				map.put("ysfw", "0");
				map.put("ty", "6");
				map.put("ps", "88abbed20a4421e5b923e5fcaed92a44");
				return map;
			}
		}; 
		mQueue.add(request);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.pay_order_activity_check_order:
			
			break;
		case R.id.pay_order_activity_go_pay:
			break;
		case R.id.pay_order_activity_back:
			intent.setClass(PayOrderActivity.this, MainActivity.class);
			intent.putExtra("fragmentId", 4);
			PayOrderActivity.this.startActivity(intent);
			finish();
		default:
			
			break;
		}
	}
	
}
