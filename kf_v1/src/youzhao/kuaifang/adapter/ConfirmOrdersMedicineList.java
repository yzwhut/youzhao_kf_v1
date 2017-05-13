package youzhao.kuaifang.adapter;

import java.util.List;

import youzhao.kuaifang.R;
import youzhao.kuaifang.adapter.ShoppingcartMedicineList.ViewHolder;
import youzhao.kuaifang.utils.BitmapCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.bumptech.glide.Glide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ConfirmOrdersMedicineList extends BaseAdapter{
	private List<String> medicineNames, medicinePics, medicineNumbers, medicinePrices, medicineSpec;
	private Context context;
	private RequestQueue mQueue;
	private BitmapCache cache;
	public ConfirmOrdersMedicineList(List<String> medicineNames, List<String> medicinePics, 
			List<String> medicineNumbers, List<String> medicinePrices, List<String> medicineSpec,
			Context context,RequestQueue mQueue, BitmapCache cache){
		this.medicineNames = medicineNames;
		this.medicinePics = medicinePics;
		this.medicineNumbers = medicineNumbers;
		this.medicinePrices = medicinePrices;
		this.medicineSpec = medicineSpec;
		this.context = context;
		this.mQueue = mQueue;
		this.cache = cache;
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return medicineNames.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return medicineNames.get(position);
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
			convertView = LayoutInflater.from(context).inflate(R.layout.confirm_orders_medicine_lv_item, null);
			viewHolder.medicine_name = (TextView) convertView.findViewById(R.id.confirm_orders_medicine_name);
			viewHolder.medicine_number = (TextView) convertView.findViewById(R.id.confirm_orders_medicine_number_toshow);
			viewHolder.medicine_spec = (TextView) convertView.findViewById(R.id.confirm_orders_medicine_spec);
			viewHolder.medicine_sumprice = (TextView) convertView.findViewById(R.id.confirm_orders_medicine_sumprice);
			viewHolder.medicine_pic = (ImageView) convertView.findViewById(R.id.confirm_orders_medicine_pic);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.medicine_name.setText(medicineNames.get(position));
		viewHolder.medicine_number.setText(medicineNumbers.get(position));
		viewHolder.medicine_spec.setText(medicineSpec.get(position));
		viewHolder.medicine_sumprice.setText(medicinePrices.get(position));
		//接下来就是设置药品的图片
//		ImageLoader loader = new ImageLoader(mQueue, cache);
//		ImageListener listener = loader.getImageListener(viewHolder.medicine_pic, R.drawable.ic_empty, R.drawable.ic_empty);
//		loader.get(medicinePics.get(position), listener);
		Glide.with(context).load(medicinePics.get(position)).placeholder(R.drawable.ic_empty)
		.error(R.drawable.ic_empty).into(viewHolder.medicine_pic);
		return convertView;
	}
	
	class ViewHolder{
		TextView medicine_name, medicine_number, medicine_spec, medicine_sumprice; 
		ImageView medicine_pic;
	}
	

}
