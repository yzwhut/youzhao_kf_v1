package youzhao.kuaifang;

import java.util.List;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;


public class CheckMapAreaActivity extends Activity {
	
	private BaiduMap mBaiduMap;
	private MapView checkMapAreaActivityMapView;
	//通过为止提供器得到当前位置
	private LocationManager locationManager;
	//用于判断使用哪种定位
	private String provider;
	//用于判断是不是第一次
	private boolean isFirstLocate = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
        SDKInitializer.initialize(getApplicationContext());  
		setContentView(R.layout.activity_check_map_area);
        initWidget();
        Log.i("kuaifang=========", "in CheckMapAreaActivity after the method initWidget");
        mBaiduMap = checkMapAreaActivityMapView.getMap();  
        //要将自己在地图上面展示出来，还要使用setMyLocationEnable()来开启该功能
        mBaiduMap.setMyLocationEnabled(true);
        //普通地图    
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);  
        Log.i("kuaifang=========", "in CheckMapAreaActivity after the method setMapType");
        mBaiduMap.setTrafficEnabled(true);
        Log.i("kuaifang=========", "in CheckMapAreaActivity after the method setTrafficEnabled");
        mBaiduMap.setBaiduHeatMapEnabled(true);  
        Log.i("kuaifang=========", "in CheckMapAreaActivity after the method setBaiduHeatMapEnabled");
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //获取所有可用的为止提供器
        List<String> providerList = locationManager.getAllProviders();
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
			provider = LocationManager.GPS_PROVIDER;
		}else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
			provider = LocationManager.NETWORK_PROVIDER;
		}else {
			//没有可用的位置提供器
			Toast.makeText(this, "No location provider to use", Toast.LENGTH_SHORT).show();
			return;
		}
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
			getMyLocation(location);
		}
        locationManager.requestLocationUpdates(provider, 4000, 1, listener);
	}
	
	private void getMyLocation(Location location){
		if (isFirstLocate) {
			LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
			MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
			mBaiduMap.animateMapStatus(update);
			update = MapStatusUpdateFactory.zoomTo(15f);
			mBaiduMap.animateMapStatus(update);
			isFirstLocate = false;
		}
		MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
		locationBuilder.latitude(location.getLatitude());
		locationBuilder.longitude(location.getLongitude());
		MyLocationData locationData = locationBuilder.build();
		mBaiduMap.setMyLocationData(locationData);
	}
	
	LocationListener listener = new LocationListener() {
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			//更新设备当前的位置信息
			if(location != null)
				getMyLocation(location);
		}
	};
	
	private void initWidget(){
		checkMapAreaActivityMapView = (MapView) findViewById(R.id.checkMapAreaActivityMapView);
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		checkMapAreaActivityMapView.onResume();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		checkMapAreaActivityMapView.onPause();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mBaiduMap.setMyLocationEnabled(false);
		checkMapAreaActivityMapView.onDestroy();
		//关闭程序将监听器移除
		if (locationManager != null) {
			locationManager.removeUpdates(listener);	
		}
	}

}
