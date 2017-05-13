package youzhao.kuaifang;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import youzhao.kuaifang.adapter.GoodDetailListViewAdapter;
import youzhao.kuaifang.adapter.GoodDetailPagerAdapter;
import youzhao.kuaifang.adapter.HotGoodsGridViewAdapter;
import youzhao.kuaifang.adapter.MainActivityGridViewAdapter;
import youzhao.kuaifang.beans.goodDetail.GoodDetail;
import youzhao.kuaifang.beans.goodDetail.TM;
import youzhao.kuaifang.mywidget.MyGridView;
import youzhao.kuaifang.mywidget.MyListView;
import youzhao.kuaifang.utils.BitmapCache;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GoodDetailsActivity extends Activity {
	private String url = "http://okm.kfyao.com/v3/p.php";
	//每个药品传过来的PID值，tag即为PID
	private String PID = null;
	//private String volleyGetDataString = "";
	//刚进来就进行网络请求拿到数据，然后解析出来的结果goodDetailData
	//private GoodDetail goodDetailData;
	private RequestQueue mQueue;
	private BitmapCache cache;
	private ImageView goodDetailBack, goodDetailcollect, goodDetailShare,
		goodDetailKuaifangLogo;
	private TextView goodDetailScanPic, goodName, goodNumber, goodDetailEffect, 
		goodDetailDrugstoreLocation, goodDetailPrice, goodDetailBuyNow,
		goodDetailAddToShoppingcartMiddle, goodDetailPhone, goodDetailAlwaysBuy,
		goodDetailHidePrice, goodDetailHideAddTo;
//		medicineNa, medicineNaDetail,
//		medicineCf, medicineCfDetail, medicineXz, medicineXZDetail, 
//		medicineZz, medicineZzDetail, medicineMs, medicineMsDetail,
//		medicineYf, medicineYfDetail, medicineBl, medicineBlDetail,
//		medicineJj, medicineJjDetail, medicineSx, medicineSxDetail,
//		medicineZy, medicineZyDetail, medicineCc, medicineCcDetail,
//		medicineBz, medicineBzDetail, medicineXq, medicineXqDetail,
//		medicineAn, medicineAnDetail, medicineCy, medicineCyDetail,
		
	private RelativeLayout goodDetailSetUpDestination, 
		goodDetailShoppingcartRightAndButtomLayout;
	private LinearLayout good_detail_detail_linearLayout;
	private ViewPager goodDetailViewPager;
	private MyGridView goodDetailDetailGv, goodDetailAlwaysBuyGv;
	private MyListView goodDetailInstructionLv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_good_details);
		initWidget();
		getIntentData();
		Log.i("kuaifang========", "in GoodDetailsActivity after method getIntentData, the PID is = " + PID);
		getDataByVolleyAndParse();
		Log.i("kuaifang========", "in GoodDetailsActivity after method getDataByVolleyAndParse, the volleyGetDataString is null");
//		initViewpager();
//		initBetweenVpAndInstruction();
//		initInstuction();
//		initAlwaysBuy();	
	}
	
	public void initWidget(){
		mQueue = Volley.newRequestQueue(this);
		cache = new BitmapCache();
		
		goodDetailBack = (ImageView) findViewById(R.id.good_detail_back);
		goodDetailcollect = (ImageView) findViewById(R.id.good_detail_collect);
		goodDetailShare = (ImageView) findViewById(R.id.good_detail_share);
		goodDetailKuaifangLogo = (ImageView) findViewById(R.id.good_detail_kuaifang_logo);
		
		goodDetailScanPic = (TextView) findViewById(R.id.good_detail_scanpic);
		goodName = (TextView) findViewById(R.id.good_name);
		goodNumber = (TextView) findViewById(R.id.good_detail_number);
		goodDetailEffect =  (TextView) findViewById(R.id.good_detail_effect);
		goodDetailDrugstoreLocation = (TextView) findViewById(R.id.good_detail_drugstore_location);
		goodDetailPrice = (TextView) findViewById(R.id.good_detail_price);
		goodDetailBuyNow = (TextView) findViewById(R.id.good_detail_buy_now);
		goodDetailAddToShoppingcartMiddle = (TextView) findViewById(R.id.good_detail_addto_shoppingcart_middle);
		goodDetailPhone = (TextView) findViewById(R.id.good_detail_phone);
		goodDetailAlwaysBuy = (TextView) findViewById(R.id.good_detail_always_buy);
		goodDetailHidePrice = (TextView) findViewById(R.id.good_detail_hide_price);
		goodDetailHideAddTo = (TextView) findViewById(R.id.good_detail_hide_addto);
		
		goodDetailSetUpDestination = (RelativeLayout) findViewById(R.id.good_detail_set_destination);
		goodDetailShoppingcartRightAndButtomLayout = (RelativeLayout) findViewById(R.id.good_detail_shoppingcart_rightandbuttom);
		
		good_detail_detail_linearLayout = (LinearLayout) findViewById(R.id.good_detail_detail_linearLayout);
		
		goodDetailViewPager = (ViewPager) findViewById(R.id.good_detail_viewpager);
		
		goodDetailDetailGv = (MyGridView) findViewById(R.id.good_detail_detail_gv);
		goodDetailAlwaysBuyGv = (MyGridView) findViewById(R.id.good_detail_always_buy_gv);
		
		goodDetailInstructionLv = (MyListView) findViewById(R.id.good_detail_instruction_lv);
	}
	
	public void getIntentData(){
		Intent intent = getIntent();
		String intentData = intent.getStringExtra("PID");
		PID = intentData;
		
	}
		
	public void getDataByVolleyAndParse(){
		Log.i("kuaifang========", "in GoodDetailsActivity at the start of method getDataByVolleyAndParse ");
		Log.i("kuaifang========", "in GoodDetailsActivity at the start of method Thread run ");
		//用于返回的数据
		
		StringRequest request = new StringRequest(Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String arg0) {
				// TODO Auto-generated method stub
				//对请求的数据进行解析，然后赋值传出去
				GoodDetail goodDetailData = GoodDetail.parseGoodDetail(arg0);
				initViewpager(goodDetailData);
				initBetweenVpAndInstruction(goodDetailData);
				initInstuction(goodDetailData);
				initGoodDetailDetail(goodDetailData);
				initAlwaysBuy(goodDetailData);		
			}
		                                                                                                                                    
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				Log.i("kuaifang==========", "in GoodDetailActivity volley get Data is wrong");
			}
		}){
			//进行post请求时，post请求的body部分
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				Log.i("kuaifang==========", "in GoodDetailActivity at the start of method getParams ");
				Map<String, String> map = new HashMap<String, String>();
				map.put("at", "a");
				if (PID == null) {
					Log.i("kuaifang==========", "in GoodDetailActivity PID is null");
				}else {
					map.put("pid", GoodDetailsActivity.this.PID);	
				}	
				map.put("tk", "031ca39d61ab7b9347d68a8011630945f77075b8");			
				map.put("mno", "18600540893");
				map.put("mk", "6601C559FA2F6D1315727D61885586C4");
				map.put("ty", "1");
				map.put("ps", "88abbed20a4421e5b923e5fcaed92a44");
				return map;
			}					
		};
		mQueue.add(request);				
	}
	
	public void initViewpager(GoodDetail goodDetailData){
		List<ImageView> imageViews = new ArrayList<ImageView>();
		List<String> picUrls = new ArrayList<String>();
		ImageLoader loader = new ImageLoader(mQueue, cache);		
		String[] imageurls = {goodDetailData.getPC1(),goodDetailData.getPC2(),
				goodDetailData.getPC3(),goodDetailData.getPC4(),
				goodDetailData.getPC5(),goodDetailData.getPC6()};
		for (int i = 0; i < imageurls.length; i++) {
			if (imageurls[i] != "") {
				picUrls.add(imageurls[i]);
			}
		}
		for (int i = 0; i < picUrls.size(); i++) {
			ImageView imageView = new ImageView(this);
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			ImageListener listener = loader.getImageListener(imageView,
					R.drawable.ic_empty, R.drawable.ic_empty);
			loader.get(picUrls.get(i), listener);
			imageViews.add(imageView);
		}
		GoodDetailPagerAdapter adapter = new GoodDetailPagerAdapter(imageViews);
		goodDetailViewPager.setAdapter(adapter);
		
	}
	
	public void initBetweenVpAndInstruction(GoodDetail goodDetailData){
		goodName.setText(goodDetailData.getTL());
		goodNumber.setText(goodDetailData.getMM_MS());
		goodDetailEffect.setText(goodDetailData.getMM_ZZ());
		//设置地址，快方logo，药房的监听事件还没写
		goodDetailDrugstoreLocation.setText(goodDetailData.getYDTL());
		goodDetailPrice.setText(goodDetailData.getPR());
		//立即购买，加购物车，药师咨询专线的监听事件还没写
		//接下来是设置快方logo
		String logo = goodDetailData.getYDPC();
		ImageLoader loader = new ImageLoader(mQueue, cache);
		ImageListener listener = loader.getImageListener(goodDetailKuaifangLogo, R.drawable.tuijian_01, R.drawable.tuijian_01);
		loader.get(logo, listener);
		//增加中间购物车的点击事件,这里也是发送一个post请求，然后改变的是传进来的PID
		
		
	}
	
	public void initInstuction(GoodDetail goodDetailData){
		List<String> instructionName = new ArrayList<String>();
		List<String> instructionDetail = new ArrayList<String>();
		String[] instrucionNameTemp = {"【药品名称】",    "【成        分】", "【性        状】", 
				"【功能主治】", "【规       格】", "【用法用量】", "【不良反应】", "【禁        忌】", 
				"【注意事项】", "【药物相互作用】","【贮        藏】", "【包        装】", "【有  效  期】", 
				"【批准文号】", "【生产企业】"};
		if (goodDetailData.getMM_NA() != null) {
			instructionName.add(instrucionNameTemp[0]);
			instructionDetail.add(goodDetailData.getMM_NA());
		}
		if (goodDetailData.getMM_CF() != null) {
			instructionName.add(instrucionNameTemp[1]);
			instructionDetail.add(goodDetailData.getMM_CF());
		}
		if (goodDetailData.getMM_XZ() != null) {
			instructionName.add(instrucionNameTemp[2]);
			instructionDetail.add(goodDetailData.getMM_XZ());
		}
		if (goodDetailData.getMM_ZZ() != null) {
			instructionName.add(instrucionNameTemp[3]);
			instructionDetail.add(goodDetailData.getMM_ZZ());
		}
		if (goodDetailData.getMM_MS() != null) {
			instructionName.add(instrucionNameTemp[4]);
			instructionDetail.add(goodDetailData.getMM_MS());
		}
		if (goodDetailData.getMM_YF() != null) {
			instructionName.add(instrucionNameTemp[5]);
			instructionDetail.add(goodDetailData.getMM_YF());
		}
		if (goodDetailData.getMM_BL() != null) {
			instructionName.add(instrucionNameTemp[6]);
			instructionDetail.add(goodDetailData.getMM_BL());
		}
		if (goodDetailData.getMM_JJ() != null) {
			instructionName.add(instrucionNameTemp[7]);
			instructionDetail.add(goodDetailData.getMM_JJ());
		}
		if (goodDetailData.getMM_SX() != null) {
			instructionName.add(instrucionNameTemp[8]);
			instructionDetail.add(goodDetailData.getMM_SX());
		}
		if (goodDetailData.getMM_ZY() != null) {
			instructionName.add(instrucionNameTemp[9]);
			instructionDetail.add(goodDetailData.getMM_ZY());
		}
		if (goodDetailData.getMM_CC() != null) {
			instructionName.add(instrucionNameTemp[10]);
			instructionDetail.add(goodDetailData.getMM_CC());
		}
		if (goodDetailData.getMM_BZ() != null) {
			instructionName.add(instrucionNameTemp[11]);
			instructionDetail.add(goodDetailData.getMM_BZ());
		}
		if (goodDetailData.getMM_XQ() != null) {
			instructionName.add(instrucionNameTemp[12]);
			instructionDetail.add(goodDetailData.getMM_XQ());
		}
		if (goodDetailData.getMM_AN() != null) {
			instructionName.add(instrucionNameTemp[13]);
			instructionDetail.add(goodDetailData.getMM_AN());
		}
		if (goodDetailData.getMM_CY() != null) {
			instructionName.add(instrucionNameTemp[14]);
			instructionDetail.add(goodDetailData.getMM_CY());
		}
		GoodDetailListViewAdapter adapter = new GoodDetailListViewAdapter(instructionName, instructionDetail, GoodDetailsActivity.this);
		goodDetailInstructionLv.setAdapter(adapter);
	}
	
	//这里要进行判断SPXQTP字段有没有内容，有的话，就要将里面图片的url取出来，并让整体的布局显示出来
	public void initGoodDetailDetail(GoodDetail goodDetailData){
		Log.i("kuaifang========", "in GoodDetailActivity at the start of initGoodDetailDetail");
		//List<String> imageUrl = new ArrayList<String>();
		String StringUrls = goodDetailData.getSPXQTP();
		if (StringUrls != null) {
			good_detail_detail_linearLayout.setVisibility(View.VISIBLE);
		}
		//接下来用正则表达式来取出SPXQTP中的图片url
		Log.i("kuaifang========", "in GoodDetailActivity at the start of regex");
//		String regex = "^http://.+\\..+\\..+\\.jpg$";
//		Pattern pattern = Pattern.compile(regex);
//		Matcher matcher = pattern.matcher(imageUrls);
//		Log.i("kuaifang========", "in GoodDetailActivity at the start of while");
//		while (matcher.find()) {
//			imageUrl.add(matcher.group());
//			Log.i("kuaifang========", "in GoodDetailActivity at the method of initGoodDetailDetail the url is" + matcher.group());
//		}
		String[] imageUrls = StringUrls.split(";");
		MainActivityGridViewAdapter adapter = new MainActivityGridViewAdapter(imageUrls, GoodDetailsActivity.this, cache, mQueue);
		goodDetailDetailGv.setAdapter(adapter);
	}
	
	public void initAlwaysBuy(GoodDetail goodDetailData){
		List<String> alwaysBuyPics = new ArrayList<String>();
		List<String> alwaysBuyName = new ArrayList<String>(); 
		List<String> alwaysBuyNumber = new ArrayList<String>(); 
		List<String> alwaysBuyPrice = new ArrayList<String>(); 
		//GJZ是首页疾病列表数据的Object
		TM[] tms = goodDetailData.getTM();
		//这里是将经常一起购买的textview和gv一起显示出来
		if (tms.length > 0) {
			goodDetailAlwaysBuy.setVisibility(View.VISIBLE);
			goodDetailAlwaysBuyGv.setVisibility(View.VISIBLE);
		}
		
		for (int i = 0; i < tms.length; i++) {
			alwaysBuyName.add(tms[i].getTL());
			alwaysBuyNumber.add(tms[i].getMS());
			alwaysBuyPrice.add(tms[i].getPR());
			alwaysBuyPics.add(tms[i].getPC1());	
			//Log.i("kuaifang=======", "" + ms[i].getTL()+" "+ ms[i].getMS() +" "+ ms[i].getPR() +" " + ms[i].getPC());
		}
		//Log.i("kuaifang=======", "" + hotGoodsName.size());
		HotGoodsGridViewAdapter adapter = new HotGoodsGridViewAdapter(alwaysBuyPics, alwaysBuyName, alwaysBuyNumber
				, alwaysBuyPrice, this, mQueue, cache);
		goodDetailAlwaysBuyGv.setAdapter(adapter);
		
	}
	
	public void click(View view){
		switch (view.getId()) {
		case R.id.good_detail_addto_shoppingcart_middle:
			Log.i("kuaifang=========", "in GoodDetailActivity add to shoppingcart");
			StringRequest request = new StringRequest(Method.POST, "http://okm.kfyao.com/v3/ck.php", new Listener<String>() {

				@Override
				public void onResponse(String arg0) {
					// TODO Auto-generated method stub	
				}
			                                                                                                                                    
			}, new ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError arg0) {
					// TODO Auto-generated method stub
					Log.i("kuaifang=======", "in GoodDetailActivity shoppingcart in the middle volley request is wrong");
				}
			}){
				//进行post请求时，post请求的body部分
				@Override
				protected Map<String, String> getParams() throws AuthFailureError {
					// TODO Auto-generated method stub
					Map<String, String> map = new HashMap<String, String>();
					map.put("at", "a");
					if (PID == null) {
						Log.i("kuaifang==========", "in GoodDetailActivity PID is null");
					}else {
						map.put("pid", GoodDetailsActivity.this.PID);	
					}	
					map.put("tk", "031ca39d61ab7b9347d68a8011630945f77075b8");			
					map.put("mno", "18600540893");
					map.put("at", "a");
					map.put("mk", "6601C559FA2F6D1315727D61885586C4");
					map.put("ty", "1");
					map.put("ps", "88abbed20a4421e5b923e5fcaed92a44");
					return map;
				}					
			};
			mQueue.add(request);
			Log.i("kuaifang========", "in GoodDetailActivity add to shoppingcart is successful");
			Toast.makeText(GoodDetailsActivity.this, "加入购物车成功", Toast.LENGTH_LONG).show();
			break;
		case R.id.good_detail_buy_now:
			
			break;
		default:
			break;
		}
	}
	
}
