package youzhao.kuaifang.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;

import youzhao.kuaifang.FamilyActivity;
import youzhao.kuaifang.MainActivity;
import youzhao.kuaifang.R;
import youzhao.kuaifang.ReduceWeightActivity;
import youzhao.kuaifang.SpecialActiveActivity;
import youzhao.kuaifang.adapter.BrandRecommendGridViewAdapter;
import youzhao.kuaifang.adapter.HotGoodsGridViewAdapter;
import youzhao.kuaifang.adapter.MainFirstViewPagerAdapter;
import youzhao.kuaifang.adapter.SickListGridViewAdapter;
import youzhao.kuaifang.application.MyApplication;
import youzhao.kuaifang.beans.GJZ;
import youzhao.kuaifang.beans.MainFirstPage;
import youzhao.kuaifang.beans.PP.MM;
import youzhao.kuaifang.beans.SYSJ;
import youzhao.kuaifang.beans.TOPAD;
import youzhao.kuaifang.mockdata.MockFirstPage;
import youzhao.kuaifang.mywidget.MyGridView;
import youzhao.kuaifang.mywidget.MyViewPager;
import youzhao.kuaifang.urlsandutils.DownLoad;
import youzhao.kuaifang.urlsandutils.MainFirstPageUrls;
import youzhao.kuaifang.urlsandutils.StringToBitmap;
import youzhao.kuaifang.utils.BitmapCache;
import youzhao.kuaifang.utils.ListViewAndGridViewHeightBasedOnChildren;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class FragmentFstPager extends Fragment{
	private RequestQueue mQueue;
	//请求所得数据
	private MainFirstPage mainFirstPage;
	private String jsonResponse;
	private View view;
	private MyViewPager mainFstViewPager;	
	private BitmapCache cache;	
	private MyGridView mainFstSickList;
	private MyGridView mainFstRecomemd, mainFstHotGoods;
	private ImageView mainFstAd1, mainFstAd2, mainFstAd3, mainFstScan;
	private TextView mainFstTopicAll, mainFstRecommendAll, mainFstGetlocation;
	private EditText mainFstInput;
	private final long delay = 2500;  
    private final int AUTO = 0; 
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.main_first_page, null);
		initWidget();
		initMain1stViewPager();
		initMain1stSickList();
		initBrandsRecommend();
		initAds();
		initHotGoods();
		return view;
	}
	

		
	
	public void initWidget(){
		mQueue = Volley.newRequestQueue(getActivity());
		cache = new BitmapCache();
		mainFstGetlocation = (TextView) view.findViewById(R.id.main_fst_getlocation);
		mainFstViewPager = (MyViewPager) view.findViewById(R.id.main_fst_viewpager);		
		mainFstInput = (EditText) view.findViewById(R.id.main_fst_input);
		mainFstScan = (ImageView) view.findViewById(R.id.main_fst_scan);
		mainFstSickList = (MyGridView) view.findViewById(R.id.main_fst_sicklist);
		mainFstTopicAll = (TextView) view.findViewById(R.id.main_fst_topic_all);
		mainFstAd1 = (ImageView) view.findViewById(R.id.main_fst_sell1);
		mainFstAd2 = (ImageView) view.findViewById(R.id.main_fst_sell2);
		mainFstAd3 = (ImageView) view.findViewById(R.id.main_fst_sell3);
		mainFstRecommendAll = (TextView) view.findViewById(R.id.main_fst_recommend_all);
		mainFstRecomemd = (MyGridView) view.findViewById(R.id.main_fst_recommendlist);
		mainFstHotGoods = (MyGridView) view.findViewById(R.id.main_fst_hotgoodslist);
	}
	
	public void initMain1stViewPager(){
//		StringRequest request = new StringRequest(Method.POST, MainFirstPageUrls.STRING_MAIN_FISRTPAGE, new Listener<String>() {
//		ImageLoader loader = new ImageLoader(mQueue, cache);
//		List<ImageView> imageViews = new ArrayList<ImageView>();
//		//ImageListener listener = loader.getImageListener(, defaultImageResId, errorImageResId)
//			public void onResponse(String arg0) {
//				// TODO Auto-generated method stub 
//				List<String> imageUrls = new ArrayList<String>();
//				Log.i("kuaifang=======", "in initMain1stViewpager");
//				jsonResponse = arg0;
//				//暂停mock用数据
//				mainFirstPage = MainFirstPage.parseMainFirstPage(new MockFirstPage().getData());
//				Log.i("kuaifang=======", "in initMain1stViewpager before SYSJ created");
//				if (mainFirstPage == null) {
//					Log.i("kuaifang=======", "mainFirstPage is null");
//				}
//				SYSJ[] SYSJ = mainFirstPage.getSYSJ();
//				TOPAD[] TOPAD= SYSJ[0].getMM().getTJ().getTOPAD();
//				for (int i = 0; i < TOPAD.length; i++) {
//					String vpPicUrl = TOPAD[i].getPC();
//					Log.i("kuaifang=======", "in initviewpager for ");
//					ImageView imageView = new ImageView(getActivity());
//					imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//					ImageListener listener = loader.getImageListener(imageView,
//							R.drawable.ic_empty, R.drawable.ic_empty);
//					loader.get(vpPicUrl, listener);
//					imageViews.add(imageView);
//				}
//				MainFirstViewPagerAdapter vPagerAdapter = new MainFirstViewPagerAdapter(imageViews);
//				mainFstViewPager.setAdapter(vPagerAdapter);
//			}
//		}, new ErrorListener() {
//
//			@Override
//			public void onErrorResponse(VolleyError arg0) {
//				// TODO Auto-generated method stub
//				Log.i("youzhao_kuaifang", "in FragmentFstPager, method initMain1stViewPager and error is ==" + arg0.toString());
//			}
//			
//		}){
//			@Override
//			protected Map<String, String> getParams() throws AuthFailureError {
//				// TODO Auto-generated method stub
//				Map<String, String> map = new HashMap<String, String>();
//				map.put("at", "a");
//				map.put("crd", "0");
//				map.put("sjxh", "FRD-AL10");
//				map.put("mk", "6601C559FA2F6D1315727D61885586C4");
//				map.put("ps", "88abbed20a4421e5b923e5fcaed92a44");
//				map.put("ty", "1");
//				map.put("med", "20170411");
//				map.put("csq", "8");
//				map.put("sc", "3");
//				map.put("vr", "220");
//				return map;
//			}
//		};
//		mQueue.add(request);
		
		ImageLoader loader = new ImageLoader(mQueue, cache);
		List<ImageView> imageViews = new ArrayList<ImageView>();
		Log.i("kuaifang=======", "in initMain1stViewpager");
		//jsonResponse = arg0;
		//暂停mock用数据
		mainFirstPage = MainFirstPage.parseMainFirstPage(new MockFirstPage().getData());
		Log.i("kuaifang=======", "in initMain1stViewpager before SYSJ created");
		if (mainFirstPage == null) {
			Log.i("kuaifang=======", "mainFirstPage is null");
		}
		SYSJ[] SYSJ = mainFirstPage.getSYSJ();
		TOPAD[] TOPAD= SYSJ[0].getMM().getTJ().getTOPAD();
		for (int i = 0; i < TOPAD.length; i++) {
			String vpPicUrl = TOPAD[i].getPC();
			Log.i("kuaifang=======", "in initviewpager for ");
			ImageView imageView = new ImageView(getActivity());
			//改成自己的代码后viewpager上面出现了一点空白，imageview加载图片需要进行设置
			//imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			ImageListener listener = loader.getImageListener(imageView,
					R.drawable.ic_empty, R.drawable.ic_empty);
			loader.get(vpPicUrl, listener);
//			Glide.with(getActivity()).load(vpPicUrl).placeholder(R.drawable.ic_empty)
//			.error(R.drawable.ic_empty).into(imageView);
			imageViews.add(imageView);
		}
		MainFirstViewPagerAdapter vPagerAdapter = new MainFirstViewPagerAdapter(imageViews, getActivity());
		
		mainFstViewPager.setAdapter(vPagerAdapter);
		mainFstViewPager.setCurrentItem(imageViews.size() * 100);
		Log.i("kuaifang=======", "in FragmentFstPager after setCurrentItem and the item is " + (imageViews.size() -1));
		 mHandler.sendEmptyMessageDelayed(AUTO, delay); 
		
	}
	
	  //使用handler来进行图片的自动轮播  
    private Handler mHandler = new Handler() {  
  
        @Override  
        public void dispatchMessage(Message msg) {  
  
            switch (msg.what) {  
            case AUTO:  
  
                int index = mainFstViewPager.getCurrentItem();  
  
                mainFstViewPager.setCurrentItem(index + 1);  
  
                mHandler.sendEmptyMessageDelayed(AUTO, delay);  
  
                break;  
  
            default:  
                break;  
            }  
  
        };  
    };  
	
	public void initMain1stSickList(){

			List<String> urlPics = new ArrayList<String>();
			List<String> sicks = new ArrayList<String>(); 
//			MainFirstPage mainFirstPage = MainFirstPage.parseMainFirstPage(new MockFirstPage().getData());
//			GJZ是首页疾病列表数据的Object
			GJZ[] gjzs = mainFirstPage.getKF().getGJZ();
			for (int i = 0; i < gjzs.length; i++) {
				sicks.add(gjzs[i].getKN());
				String strPic = gjzs[i].getPC();
				urlPics.add(strPic);
			}
			Log.i("kuaifang=======", "in FragmentFstPager at the method initMain1stSickList ");
			SickListGridViewAdapter sickAdapter = new SickListGridViewAdapter(sicks, urlPics, getActivity(), mQueue, cache);
			mainFstSickList.setAdapter(sickAdapter);
			//setListViewHeightBasedOnChildren(mainFstSickList);
			
	}
	
	//主题特卖
	public void initAds(){

		List<String> urlPics = new ArrayList<String>();
		ImageView[] imageViews = new ImageView[]{mainFstAd1, mainFstAd2, mainFstAd3};
		ImageLoader loader = new ImageLoader(mQueue, new BitmapCache());
		//GJZ是首页疾病列表数据的Object
	
		SYSJ[] sysjs = mainFirstPage.getSYSJ();
		//MM是主题特卖的数据
		youzhao.kuaifang.beans.ZT.MM[] mms = sysjs[0].getMM().getZT().getMM();
		for (int i = 0; i < mms.length; i++) {
			String urlPic = mms[i].getPC();
			urlPics.add(urlPic);
//			ImageListener listener = loader.getImageListener(imageViews[i], R.drawable.ic_empty, R.drawable.ic_empty);
//			loader.get(urlPic, listener);
			Glide.with(getActivity()).load(urlPic).placeholder(R.drawable.ic_empty)
			.error(R.drawable.ic_empty).into(imageViews[i]);
		}						
}
	
	
	public void initBrandsRecommend(){
		List<String> strBrands = new ArrayList<String>(); 
		List<String> urlPics = new ArrayList<String>();
		//GJZ是首页疾病列表数据的Object
		SYSJ[] sysjs = mainFirstPage.getSYSJ();
		//MM是品牌推荐的数组
		MM[] mms = sysjs[0].getMM().getPP().getMM();
		for (int i = 0; i < mms.length; i++) {
			strBrands.add(mms[i].getTL());
			String strPic = mms[i].getPC();
			urlPics.add(strPic);
		}
		BrandRecommendGridViewAdapter adapter = new BrandRecommendGridViewAdapter(strBrands
				, urlPics, getActivity(), mQueue, cache);
		mainFstRecomemd.setAdapter(adapter);			
	}
	
	
	public void initHotGoods(){
		List<String> hotGoodsPics = new ArrayList<String>();
		List<String> hotGoodsName = new ArrayList<String>(); 
		List<String> hotGoodsNumber = new ArrayList<String>(); 
		List<String> hotGoodsPrice = new ArrayList<String>(); 
		//GJZ是首页疾病列表数据的Object
		SYSJ[] sysjs = mainFirstPage.getSYSJ();
		//MM是品牌推荐的数组
		Log.i("kuaifang=======", "in initHotGoods before ms created");
		youzhao.kuaifang.beans.JJTJ.MM[] ms = sysjs[0].getMM().getJJTJ().getMM();
		for (int i = 0; i < ms.length; i++) {
			hotGoodsName.add(ms[i].getTL());
			hotGoodsNumber.add(ms[i].getMS());
			hotGoodsPrice.add(ms[i].getPR());
			hotGoodsPics.add(ms[i].getPC());	
			Log.i("kuaifang=======", "" + ms[i].getTL()+" "+ ms[i].getMS() +" "+ ms[i].getPR() +" " + ms[i].getPC());
		}
		Log.i("kuaifang=======", "" + hotGoodsName.size());
		HotGoodsGridViewAdapter adapter = new HotGoodsGridViewAdapter(hotGoodsPics, hotGoodsName, hotGoodsNumber
				, hotGoodsPrice, getActivity(), mQueue, cache);
		mainFstHotGoods.setAdapter(adapter);

		
	}
	
	@SuppressLint("NewApi")
	public void setListViewHeightBasedOnChildren(GridView gridview) {   
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
