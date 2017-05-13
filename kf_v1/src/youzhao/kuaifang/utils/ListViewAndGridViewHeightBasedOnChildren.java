package youzhao.kuaifang.utils;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListViewAndGridViewHeightBasedOnChildren {
	
	//动态计算listview的高度
	public static void setListViewHeightBasedOnChildren(ListView listView) {   
        // 获取ListView对应的Adapter   
        ListAdapter listAdapter = listView.getAdapter();   
        if (listAdapter == null) {   
            return;   
        }   
   
        int totalHeight = 0;   
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {   
            // listAdapter.getCount()返回数据项的数目   
            View listItem = listAdapter.getView(i, null, listView);   
            // 计算子项View 的宽高   
            listItem.measure(0, 0);    
            // 统计所有子项的总高度   
            totalHeight += listItem.getMeasuredHeight();    
        }   
   
        ViewGroup.LayoutParams params = listView.getLayoutParams();   
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));   
        // listView.getDividerHeight()获取子项间分隔符占用的高度   
        // params.height最后得到整个ListView完整显示需要的高度   
        listView.setLayoutParams(params);   
    }
	
	@SuppressLint("NewApi")
	public static void setListViewHeightBasedOnChildren(GridView gridview) {   
        // 获取ListView对应的Adapter   
        ListAdapter listAdapter = gridview.getAdapter();   
        if (listAdapter == null) {   
            return;   
        }   
        //有多少列
        int col = gridview.getNumColumns();
        int totalHeight = 0; 
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，  
        // listAdapter.getCount()小于等于8时计算两次高度相加    
        for (int i = 0; i < listAdapter.getCount(); i += col) {   
            // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, gridview);   
            // 计算子项View 的宽高   
            listItem.measure(0, 0);    
            // 获取item的高度和  
            totalHeight += (listItem.getMeasuredHeight() + gridview.getVerticalSpacing());    
        }   
        //获取GridView的布局参数
        ViewGroup.LayoutParams params = gridview.getLayoutParams();   
        params.height = totalHeight - gridview.getVerticalSpacing() ; 
        // listView.getDividerHeight()获取子项间分隔符占用的高度   
        // params.height最后得到整个ListView完整显示需要的高度   
        gridview.setLayoutParams(params);   
    }




}
