package youzhao.kuaifang.adapter;

import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.bumptech.glide.Glide;

import youzhao.kuaifang.R;
import youzhao.kuaifang.adapter.BrandRecommendGridViewAdapter.ViewHolder;
import youzhao.kuaifang.utils.BitmapCache;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MainActivityGridViewAdapter extends BaseAdapter{
	private String[] imageUrls;
	private List<String> imageUrlslList;
	private Context context;
	private BitmapCache cache;
	private RequestQueue mQueue;
	private ImageLoader loader;	
	public MainActivityGridViewAdapter(String[] imageUrls, Context context,
			BitmapCache cache, RequestQueue mQueue){
		this.imageUrls = imageUrls;
		this.cache = cache;
		this.context = context;
		this.mQueue = mQueue;
		loader = new ImageLoader(mQueue, cache);
	}
	
	public MainActivityGridViewAdapter(List<String> imageUrlsList, Context context,
			BitmapCache cache, RequestQueue mQueue){
		this.imageUrlslList = imageUrlsList;
		this.cache = cache;
		this.context = context;
		this.mQueue = mQueue;
		loader = new ImageLoader(mQueue, cache);
	} 
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (imageUrlslList != null &&imageUrlslList.size() > 0) {
			return imageUrlslList.size();
		}
		return imageUrls.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (imageUrlslList != null &&imageUrlslList.size() > 0) {
			return imageUrlslList.get(position);
		}
		return imageUrls[position];
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
			convertView = LayoutInflater.from(context).inflate(R.layout.main_activity_griditem, null);
			viewHolder.imageView = (ImageView) convertView.findViewById(R.id.main_activity_item);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
//		ImageListener listener = loader.getImageListener(viewHolder.imageView,
//				R.drawable.ic_empty, R.drawable.ic_empty);
		if (imageUrlslList != null &&imageUrlslList.size() > 0) {
//			loader.get(imageUrlslList.get(position), listener);
			Glide.with(context).load(imageUrlslList.get(position)).placeholder(R.drawable.ic_empty)
			.error(R.drawable.ic_empty).centerCrop().dontAnimate().into(viewHolder.imageView);
		}else {
//			loader.get(imageUrls[position], listener);
			Glide.with(context).load(imageUrls[position]).placeholder(R.drawable.ic_empty)
			.error(R.drawable.ic_empty).centerCrop().dontAnimate().into(viewHolder.imageView);
		}		
		return convertView;
	}
	
	class ViewHolder{
		ImageView imageView;
	}
	
}
