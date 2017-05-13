package youzhao.kuaifang.adapter;

import java.util.List;

import youzhao.kuaifang.R;
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

public class ShoppingcartMedicineList extends BaseAdapter{
	private List<String> medicineNames, medicinePics, medicineNumbers, medicinePrices, medicineWantBuys;
	private Context context;
	private RequestQueue mQueue;
	private BitmapCache cache;
	public ShoppingcartMedicineList(List<String> medicineNames, List<String> medicinePics, 
			List<String> medicineNumbers, List<String> medicinePrices, List<String> medicineWantBuys,
			Context context, RequestQueue mQueue, BitmapCache cache){
		this.medicineNames = medicineNames;
		this.medicinePics = medicinePics;
		this.medicineNumbers = medicineNumbers;
		this.medicinePrices = medicinePrices;
		this.medicineWantBuys = medicineWantBuys;
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
			convertView = LayoutInflater.from(context).inflate(R.layout.main_firstpage_shoppingcart_medicine_lv_item, null);
			viewHolder.medicine_name = (TextView) convertView.findViewById(R.id.medicine_name);
			viewHolder.medicine_number = (TextView) convertView.findViewById(R.id.medicine_number);
			viewHolder.medicine_number_want_buy = (TextView) convertView.findViewById(R.id.medicine_number_want_buy);
			viewHolder.medicine_price = (TextView) convertView.findViewById(R.id.medicine_price);
			viewHolder.medicine_pic = (ImageView) convertView.findViewById(R.id.medicine_pic);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.medicine_name.setText(medicineNames.get(position));
		viewHolder.medicine_number.setText(medicineNumbers.get(position));
		viewHolder.medicine_price.setText(medicinePrices.get(position));
		viewHolder.medicine_number_want_buy.setText(medicineWantBuys.get(position));
		//接下来就是设置药品的图片
//		ImageLoader loader = new ImageLoader(mQueue, cache);
//		ImageListener listener = loader.getImageListener(viewHolder.medicine_pic, R.drawable.ic_empty, R.drawable.ic_empty);
//		loader.get(medicinePics.get(position), listener);
		Glide.with(context).load(medicinePics.get(position)).placeholder(R.drawable.ic_empty)
		.error(R.drawable.ic_empty).into(viewHolder.medicine_pic);
		return convertView;
	}
	
	class ViewHolder{
		TextView medicine_name, medicine_number, medicine_number_want_buy, medicine_price; 
		ImageView medicine_pic;
	}

}
