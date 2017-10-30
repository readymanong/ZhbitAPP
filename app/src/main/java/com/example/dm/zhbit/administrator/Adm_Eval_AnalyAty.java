package com.example.dm.zhbit.administrator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.display.VirtualDisplay;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dm.zhbit.R;
import com.example.dm.zhbit.beans.AEvaluation;
import com.example.dm.zhbit.beans.Evaluation;
import com.example.dm.zhbit.beans.SelectCourse;
import com.example.dm.zhbit.utiltools.ScreenShotUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarException;

import adapter.AEvaAdapter;
import adapter.EvaluationAdpter;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;


public class Adm_Eval_AnalyAty extends Activity {
	private List<AEvaluation> datas = new ArrayList<>();
	Button info_getfile_btn;
	private static final int SCREEN_SHOT = 0;
	private static final String TAG = "TAG";

	MediaProjection mediaProjection;
	MediaProjectionManager projectionManager;
	VirtualDisplay virtualDisplay;
	int mResultCode;
	Intent mData;
	ImageReader imageReader;

	int width;
	int height;
	int dpi;

	String imageName;
	Bitmap bitmap;
	ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adm_eva_analysis);
		ViewGroup tableTitle = (ViewGroup) findViewById(R.id.table_title);
		tableTitle.setBackgroundColor(Color.rgb(177, 173, 172));
		getData();
		info_getfile_btn= (Button) findViewById(R.id.info_getfile_btn);
		info_getfile_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//GetandSaveCurrentImage();
				Toast.makeText(Adm_Eval_AnalyAty.this,"成功",Toast.LENGTH_SHORT).show();
			}
		});
	}

public void getData(){
	final BmobQuery<Evaluation> bmobQuery=new BmobQuery<Evaluation>();
	final String ateaStr=getIntent().getStringExtra("e_tea");
	final String acourseStr=getIntent().getStringExtra("e_course");
	final String atualNumStr=getIntent().getStringExtra("e_atual_num");
	final String planNumStr=getIntent().getStringExtra("e_planNum");

	Log.i("数据接收", planNumStr);


	float rate= (float) (Integer.parseInt(atualNumStr)*100/Integer.parseInt(planNumStr));
	int scale=2;
	int roundingMode=4;
	BigDecimal bd=new BigDecimal((double)rate);
	bd=bd.setScale(scale,roundingMode);
	rate=bd.floatValue();
	Log.i("数据1", String.valueOf(rate));
	bmobQuery.addWhereEqualTo("evaluate_tea_name",ateaStr);
	bmobQuery.addWhereEqualTo("evaluate_tea_course",acourseStr);

	bmobQuery.average(new String[] { "totalscore" });
	final float finalRate = rate;
	bmobQuery.findStatistics(Evaluation.class,new QueryListener<JSONArray>() {

		@Override
		public void done(JSONArray ary, BmobException e) {
			if(e==null){
				if(ary!=null){//
					try {
						JSONObject obj = ary.getJSONObject(0);
						final int sum = obj.getInt("_avgTotalscore");//_(关键字)+首字母大写的列名
						final String evStr;
						if(sum>=90){
							evStr="优秀";
						}else if(sum>=80){evStr="良好";}
						else if(sum>=70){evStr="中等";}
						else if(sum>=60){evStr="及格";}
						else {evStr="不及格";}

						//showToast("游戏总得分：" + sum);
						Button info_sava_btn= (Button) findViewById(R.id.info_sava_btn);
						info_sava_btn.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								final AEvaluation aeva=new AEvaluation();
								aeva.setEvaluate_tea_name(ateaStr);
								aeva.setEvaluate_tea_course(acourseStr);
								aeva.setAvg_score(String.valueOf(sum));
								aeva.setEvl_actual_num(atualNumStr);
								aeva.setEvl_rate(evStr);
								aeva.setEvl_plan_num(planNumStr);
								aeva.setEvl_rate(String.valueOf(finalRate)+"%");
								final BmobQuery<AEvaluation> Query=new BmobQuery<AEvaluation>();
								Query.addWhereEqualTo("evaluate_tea_name",ateaStr);
								Query.addWhereEqualTo("evaluate_tea_course",acourseStr);
								Query.count(AEvaluation.class, new CountListener() {
									@Override
									public void done(Integer integer, BmobException e) {
										if(integer>=1){

											Toast.makeText(Adm_Eval_AnalyAty.this, "数据已经保存过!", Toast.LENGTH_LONG).show();
										}
										else{
											aeva.save(new SaveListener<String>() {
												@Override
												public void done(String s, BmobException e) {
													if(e==null){

														Toast.makeText(Adm_Eval_AnalyAty.this, "数据保存成功!", Toast.LENGTH_LONG).show();
													}else {
														Toast.makeText(Adm_Eval_AnalyAty.this, "数据保存失败!", Toast.LENGTH_LONG).show();
													}
												}
											});
										}

									}
								});

							}
						});


								datas.add(new AEvaluation("01",acourseStr,ateaStr,String.valueOf(sum),evStr,planNumStr,atualNumStr,String.valueOf(finalRate)+"%"));
								ListView tableListView = (ListView) findViewById(R.id.a_eva_alylist);
								AEvaAdapter adapter = new AEvaAdapter(Adm_Eval_AnalyAty.this, R.layout.adm_eva_analysis_item, (ArrayList<AEvaluation>) datas);
								tableListView.setAdapter(adapter);
								Toast.makeText(Adm_Eval_AnalyAty.this,"评价得分："+sum,Toast.LENGTH_SHORT).show();


					} catch (JSONException e1) {
						e1.printStackTrace();
					}
				}else{
					Toast.makeText(Adm_Eval_AnalyAty.this,"查询失败",Toast.LENGTH_SHORT).show();
				}
			}else{
				Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
			}
		}

	});


}
//	public Bitmap myShot(Adm_Eval_AnalyAty activity) {
//		// 获取windows中最顶层的view
//		View view = activity.getWindow().getDecorView();
//		view.buildDrawingCache();
//
//		// 获取状态栏高度
//		Rect rect = new Rect();
//		view.getWindowVisibleDisplayFrame(rect);
//		int statusBarHeights = rect.top;
//		Display display = activity.getWindowManager().getDefaultDisplay();
//
//		// 获取屏幕宽和高
//		int widths = display.getWidth();
//		int heights = display.getHeight();
//
//		// 允许当前窗口保存缓存信息
//		view.setDrawingCacheEnabled(true);
//
//		// 去掉状态栏
//		Bitmap bmp = Bitmap.createBitmap(view.getDrawingCache(), 0,
//				statusBarHeights, widths, heights - statusBarHeights);
//
//		// 销毁缓存信息
//		view.destroyDrawingCache();
//
//		return bmp;
//	}
//	// 将bitmap转换成drawable
//	BitmapDrawable bd=new BitmapDrawable(myShot());
//
//	imageView.setBackgroundDrawable(bd);
//
//	imageView.setImageBitmap(myShot());

//	private void saveToSD(Bitmap bmp, String dirName,String fileName) throws IOException {
//		// 判断sd卡是否存在
//		if (Environment.getExternalStorageState().equals(
//				Environment.MEDIA_MOUNTED)) {
//			File dir = new File(dirName);
//			// 判断文件夹是否存在，不存在则创建
//			if(!dir.exists()){
//				dir.mkdir();
//			}
//
//			File file = new File(dirName + fileName);
//			// 判断文件是否存在，不存在则创建
//			if (!file.exists()) {
//				file.createNewFile();
//			}
//
//			FileOutputStream fos = null;
//			try {
//				fos = new FileOutputStream(file);
//				if (fos != null) {
//					// 第一参数是图片格式，第二个是图片质量，第三个是输出流
//					bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
//					// 用完关闭
//					fos.flush();
//					fos.close();
//				}
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
	/**
	 * 获取和保存当前屏幕的截图
	 */
//	private void GetandSaveCurrentImage()
//	{
//		//1.构建Bitmap
//		WindowManager windowManager = getWindowManager();
//		Display display = windowManager.getDefaultDisplay();
//		int w = display.getWidth();
//		int h = display.getHeight();
////		Bitmap Bmp = Bitmap.createBitmap( w, h, Bitmap.Config.ARGB_8888 );
//		Bitmap Bm = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
//		//2.获取屏幕
//		View dewcorview = this.getWindow().getDecorView();
//		dewcorview.setDrawingCacheEnabled(true);
//		Bm = dewcorview.getDrawingCache();
//		String SavePath = getSDCardPath()+"/ZhbitAPP/ScreenImage";
////3.保存Bitmap
//		try {
//			File path = new File(SavePath);
////文件
//			String filepath = SavePath + "/Screen_1.png";
//			File file = new File(filepath);
//			if(!path.exists()){
//				path.mkdirs();
//			}
//			if (!file.exists()) {
//				file.createNewFile();
//			}
//			FileOutputStream fos = null;
//			fos = new FileOutputStream(file);
//			if (null != fos) {
//				Bm.compress(Bitmap.CompressFormat.PNG, 90, fos);
//				fos.flush();
//				fos.close();
//				Log.i("LW", "截屏文件已保存至SDCard/ScreenImage/下");
//				Toast.makeText(Adm_Eval_AnalyAty.this, "截屏文件已保存至SDCard/ZhbitAPP/ScreenImage/下", Toast.LENGTH_LONG).show();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	/**
	 * 获取SDCard的目录路径功能
	 * @return
	 */
//	private String getSDCardPath(){
//		File sdcardDir = null;
////判断SDCard是否存在
//		boolean sdcardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
//		if(sdcardExist){
//			sdcardDir = Environment.getExternalStorageDirectory();
//		}
//		return sdcardDir.toString();
//	}
//
//	// 获取指定Activity的截屏，保存到png文件
//	public static Bitmap takeScreenShot(Activity activity) {
//		// View是你需要截图的View
//		View view = activity.getWindow().getDecorView();
//		view.setDrawingCacheEnabled(true);
//		view.buildDrawingCache();
//		Bitmap b1 = view.getDrawingCache();
//
//		// 获取状态栏高度
//		Rect frame = new Rect();
//		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
//		int statusBarHeight = frame.top;
//		System.out.println(statusBarHeight);
//
//		// 获取屏幕长和高
//		int width = activity.getWindowManager().getDefaultDisplay().getWidth();
//		int height = activity.getWindowManager().getDefaultDisplay()
//				.getHeight();
//		// 去掉标题栏
//		// Bitmap b = Bitmap.createBitmap(b1, 0, 25, 320, 455);
//		Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height
//				- statusBarHeight);
//		view.destroyDrawingCache();
//		return b;
//	}
//
//	// 保存到sdcard
//	public static void savePic(Bitmap b, String strFileName) {
//		FileOutputStream fos = null;
//		try {
//			fos = new FileOutputStream(strFileName);
//			if (null != fos) {
//				b.compress(Bitmap.CompressFormat.PNG, 90, fos);
//				fos.flush();
//				fos.close();
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	// 程序入口 截取当前屏幕
//	public static void shootLoacleView(Activity a,String picpath) {
//		ScreenShot.savePic(ScreenShot.takeScreenShot(a), picpath);
//	}
}
