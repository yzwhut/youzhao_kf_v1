package youzhao.kuaifang.application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application{
	private static RequestQueue mRequestQueue;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("kuaifang=========", "in MyApplication");
		mRequestQueue = Volley.newRequestQueue(getApplicationContext());
	}
	
	public static RequestQueue getHttpQueue(){
		return mRequestQueue;
	}
}
