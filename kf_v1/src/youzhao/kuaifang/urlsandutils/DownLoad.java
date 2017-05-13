package youzhao.kuaifang.urlsandutils;

import java.util.Map;

import youzhao.kuaifang.application.MyApplication;

import android.R.string;
import android.util.Log;

import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;

public class DownLoad {
	//网络加载后返回的String类型数据
	private static String strData;
	private static RequestQueue mQueue = MyApplication.getHttpQueue();
	public static String downLoad(String url){
		StringRequest main1stVpStringRequest = new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				//MainFirstViewPager mainFirstViewPager = MainFirstViewPager.parseMainFirstViewPager(response);
				//MainFirstViewPagerArray[] pagerArrays = mainFirstViewPager.getMainFirstViewPagerArray();
				strData = response;
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				Log.i(" in class DownLoad. method download error " , arg0.toString());
			}
		});
		mQueue.add(main1stVpStringRequest);
		return strData;
		
	}
	
	public static String postDownLoad(String url){
		StringRequest request = new StringRequest(Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				strData = response;
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				Log.i(" in class postDownLoad. method download error " , arg0.toString());
			}
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				return super.getParams();
			}
		};
		mQueue.add(request);
		return strData;
	}
}
