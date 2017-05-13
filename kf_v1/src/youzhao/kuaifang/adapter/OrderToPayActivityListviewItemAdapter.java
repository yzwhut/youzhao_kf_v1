package youzhao.kuaifang.adapter;

import java.util.List;

import youzhao.kuaifang.R;
import youzhao.kuaifang.utils.BitmapCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.bumptech.glide.Glide;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OrderToPayActivityListviewItemAdapter extends BaseAdapter{
	private List<String> orderNumber, orderState, orderSum, orderTime, orderMedicinePicsUrls;
	private Context context;
	private RequestQueue mQueue;
	private BitmapCache cache;
	public OrderToPayActivityListviewItemAdapter(List<String> orderNumber, List<String> orderState, 
			List<String> orderSum, List<String> orderTime, List<String> orderMedicinePicsUrls,
			Context context,RequestQueue mQueue, BitmapCache cache){
		this.orderNumber = orderNumber;
		this.orderState = orderState;
		this.orderSum = orderSum;
		this.orderTime = orderTime;
		this.orderMedicinePicsUrls = orderMedicinePicsUrls;
		this.context = context;
		this.mQueue = mQueue;
		this.cache = cache;
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return orderNumber.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return orderNumber.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.order_of_wait_to_pay_activity_listview_item, null);
			viewHolder.order_wait_to_pay_activity_order_number_to_show = (TextView) convertView.findViewById(R.id.order_wait_to_pay_activity_order_number_to_show);
			viewHolder.order_wait_to_pay_activity_order_state_to_show = (TextView) convertView.findViewById(R.id.order_wait_to_pay_activity_order_state_to_show);
			viewHolder.order_wait_to_pay_activity_order_to_pay = (TextView) convertView.findViewById(R.id.order_wait_to_pay_activity_order_to_pay);
			viewHolder.order_wait_to_pay_activity_order_sum_to_show = (TextView) convertView.findViewById(R.id.order_wait_to_pay_activity_order_sum_to_show);
			viewHolder.order_wait_to_pay_activity_order_time_to_show = (TextView) convertView.findViewById(R.id.order_wait_to_pay_activity_order_time_to_show);
			viewHolder.order_wait_to_pay_activity_medicine_pics = (LinearLayout) convertView.findViewById(R.id.order_wait_to_pay_activity_medicine_pics);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.order_wait_to_pay_activity_order_number_to_show.setText(orderNumber.get(position));
		viewHolder.order_wait_to_pay_activity_order_state_to_show.setText(orderState.get(position));
		viewHolder.order_wait_to_pay_activity_order_sum_to_show.setText(orderSum.get(position));
		viewHolder.order_wait_to_pay_activity_order_time_to_show.setText(orderTime.get(position));
		//接下来就是展示图片
		initMedicine(viewHolder, orderMedicinePicsUrls.get(position));
				
		return convertView;
	}
	
	class ViewHolder{
		TextView order_wait_to_pay_activity_order_number_to_show, order_wait_to_pay_activity_order_state_to_show,
			order_wait_to_pay_activity_order_to_pay, order_wait_to_pay_activity_order_sum_to_show,
			order_wait_to_pay_activity_order_time_to_show;
		LinearLayout order_wait_to_pay_activity_medicine_pics;
	}
	
	public void initMedicine(ViewHolder viewHolder, String PicUrl){
		//ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);  
		//首先要把传进来的PicUrl分离出每一个图片的url来
		String[] picUrlStrings = PicUrl.split(";");
		//ImageLoader loader = new ImageLoader(mQueue, cache);
		Log.i("kuaifang========", "In OrderToPayActivityListviewItemAdapter the picUrlStrings.length is" + picUrlStrings.length);
		//ImageView[] imageViews = new ImageView[images.length]; 
		if (picUrlStrings.length <= 5) {
			for (int i = 0; i < picUrlStrings.length; i++) {  
//				ImageLoader loader = new ImageLoader(mQueue, cache);
			    ImageView imageView = new ImageView(context);  
			    imageView.setLayoutParams(new LayoutParams(80, 80));
			    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//			    ImageListener listener = loader.getImageListener(imageView, R.drawable.ic_empty, R.drawable.ic_empty);
//			    loader.get(picUrlStrings[i], listener);  
			    Glide.with(context).load(picUrlStrings[i]).placeholder(R.drawable.ic_empty)
				.error(R.drawable.ic_empty).into(imageView);
			    viewHolder.order_wait_to_pay_activity_medicine_pics.addView(imageView);  		    
			}  
		}else {
			for (int i = 0; i < 5; i++) {  
//				ImageLoader loader = new ImageLoader(mQueue, cache);
			    ImageView imageView = new ImageView(context);  
			    imageView.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
			    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//			    ImageListener listener = loader.getImageListener(imageView, R.drawable.ic_empty, R.drawable.ic_empty);
//			    loader.get(picUrlStrings[i], listener);  
			    Glide.with(context).load(picUrlStrings[i]).placeholder(R.drawable.ic_empty)
				.error(R.drawable.ic_empty).into(imageView);
			    viewHolder.order_wait_to_pay_activity_medicine_pics.addView(imageView);  		    
			}  
		}
		
	}

}
