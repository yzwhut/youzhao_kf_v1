package youzhao.kuaifang.adapter;

import java.util.List;
import youzhao.kuaifang.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GoodDetailListViewAdapter extends BaseAdapter{	
	private List<String> instructionName;
	private List<String> instructionDetail;
	private Context context;
	
	public GoodDetailListViewAdapter(List<String> instructionName, List<String> instructionDetail,
			Context context){
		this.instructionDetail = instructionDetail;
		this.instructionName = instructionName;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return instructionName.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return instructionDetail.get(position);
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
			convertView = LayoutInflater.from(context).inflate(R.layout.good_detail_instrucion_item, null);
			viewHolder.instructionName = (TextView) convertView.findViewById(R.id.good_detail_instruction_name);
			viewHolder.instructionDetail = (TextView) convertView.findViewById(R.id.good_detail_instruction_detail);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.instructionName.setText(instructionName.get(position));
		viewHolder.instructionDetail.setText(instructionDetail.get(position));
		return convertView;
	}
	
	class ViewHolder{
		TextView instructionName, instructionDetail;
	}

}
