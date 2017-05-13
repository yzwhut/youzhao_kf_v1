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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SickListGridViewAdapter extends BaseAdapter{
	private List<String> sicks;
	private List<String> urlPics;
	private Context context;
	private ImageLoader loader;
	private ImageListener listener;
	private BitmapCache cache;
	public SickListGridViewAdapter(List<String> sicks, List<String> urlPics, Context context
			,RequestQueue mQueue, BitmapCache cache){
		this.sicks = sicks;
		this.urlPics = urlPics;
		this.context = context;
		loader = new ImageLoader(mQueue, cache);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return sicks.size();
		//return sicks.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		//返回当前item数据
		return sicks.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.main_fistpager_sicklist, null);
			holder.imageView = (ImageView) convertView.findViewById(R.id.main1stpageSicklistPic);
			holder.textView = (TextView) convertView.findViewById(R.id.main1stpageSicklistText);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.textView.setText(sicks.get(position)); 
//		listener = loader.getImageListener(holder.imageView, R.drawable.ic_empty, R.drawable.ic_empty);
//		loader.get(urlPics.get(position), listener);
		Glide.with(context).load(urlPics.get(position)).placeholder(R.drawable.ic_empty)
		.error(R.drawable.ic_empty).into(holder.imageView);
//		if (position == (sicks.size() + 1)) {
//			holder.imageView.setImageResource(R.drawable.sy003);
//			holder.textView.setText("全部");
//		}
		return convertView;
	}
	
	class ViewHolder{
		ImageView imageView;
		TextView textView;
	}

}
