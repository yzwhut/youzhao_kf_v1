package youzhao.kuaifang.adapter;

import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.bumptech.glide.Glide;

import youzhao.kuaifang.R;
import youzhao.kuaifang.utils.BitmapCache;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HotGoodsGridViewAdapter extends BaseAdapter{
	private List<String> hotGoodsName;
	private List<String> hotGoodsPic;
	private List<String> hotGoodsNumber;
	private List<String> hotGoodsPrice;
	private Context context;
	private ImageLoader loader;
	private ImageListener listener;
	private BitmapCache cache;
	public HotGoodsGridViewAdapter(List<String> hotGoodsPic, List<String> hotGoodsName, List<String> hotGoodsNumber, List<String> hotGoodsPrice,
			Context context, RequestQueue mQueue, BitmapCache cache){
		this.hotGoodsName = hotGoodsName;
		this.hotGoodsPic = hotGoodsPic;
		this.hotGoodsNumber = hotGoodsNumber;
		this.hotGoodsPrice = hotGoodsPrice;
		this.context = context;
		loader = new ImageLoader(mQueue, cache);
		Log.i("kuaifang=======", "in hotGoodsGridView number = " +hotGoodsName.size());
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return hotGoodsName.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		//返回当前item数据
		return hotGoodsName.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.main_firstpage_hotgoods, null);
			holder.imageView = (ImageView) convertView.findViewById(R.id.main_1stpage_hotgoods_pic);
			holder.goodsName = (TextView) convertView.findViewById(R.id.main_hotgoods_medname);
			holder.goodsNumber = (TextView) convertView.findViewById(R.id.main_hotgoods_mednumber);
			holder.goodsPrice = (TextView) convertView.findViewById(R.id.hot_goods_price);
			holder.goodsChinese = (TextView) convertView.findViewById(R.id.hot_goods_chinese);
			holder.goodsAddShoppingCart = (TextView) convertView.findViewById(R.id.hot_goods_shoppingcart);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.goodsName.setText(hotGoodsName.get(position));
		Log.i("kuaifang=======", "in getView position = " + position );
		holder.goodsNumber.setText(hotGoodsNumber.get(position));
		holder.goodsPrice.setText(hotGoodsPrice.get(position));
//		listener = loader.getImageListener(holder.imageView, R.drawable.ic_empty, R.drawable.ic_empty);
//		loader.get(hotGoodsPic.get(position), listener);
		Glide.with(context).load(hotGoodsPic.get(position)).placeholder(R.drawable.ic_empty)
		.error(R.drawable.ic_empty).into(holder.imageView);
		return convertView;
	}
	
	class ViewHolder{
		ImageView imageView;
		TextView goodsName;
		TextView goodsNumber;
		TextView goodsPrice;
		TextView goodsChinese;
		TextView goodsAddShoppingCart;
	}

}
