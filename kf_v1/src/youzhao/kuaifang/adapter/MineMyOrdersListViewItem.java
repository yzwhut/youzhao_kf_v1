package youzhao.kuaifang.adapter;

import java.util.List;

import youzhao.kuaifang.R;
import youzhao.kuaifang.utils.BitmapCache;

import com.android.volley.RequestQueue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MineMyOrdersListViewItem extends BaseAdapter{
	private List<String> orderNumber, orderReceiver, orderSum;
	private Context context;
	public MineMyOrdersListViewItem(List<String> orderNumber, List<String> orderReceiver, 
			List<String> orderSum,Context context){
		this.orderNumber = orderNumber;
		this.orderReceiver = orderReceiver;
		this.orderSum = orderSum;
		this.context = context;
		
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
			convertView = LayoutInflater.from(context).inflate(R.layout.main_mine_orders_lv_item, null);
			viewHolder.mine_orders_number_toshow = (TextView) convertView.findViewById(R.id.mine_orders_number_toshow);
			viewHolder.mine_orders_receiver_toshow = (TextView) convertView.findViewById(R.id.mine_orders_receiver_toshow);
			viewHolder.mine_orders_orderprice_toshow = (TextView) convertView.findViewById(R.id.mine_orders_orderprice_toshow);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.mine_orders_number_toshow.setText(orderNumber.get(position));
		viewHolder.mine_orders_receiver_toshow.setText(orderReceiver.get(position));
		viewHolder.mine_orders_orderprice_toshow.setText(orderSum.get(position));
		return convertView;
	}
	
	class ViewHolder{
		TextView mine_orders_number_toshow, mine_orders_receiver_toshow, mine_orders_orderprice_toshow;

	}

}
