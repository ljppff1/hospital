package com.james.hk_redcross;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.james.hk_redcross.util.AlertInfoDialog;
import com.james.hk_redcross.util.AlertInfoDialog_EN;
import com.james.hk_redcross.util.Content;
import com.james.hk_redcross.util.Dialog_noInternet;
import com.james.hk_redcross.util.Dialog_noInternet_En;
import com.james.hk_redcross.util.getJson;
import com.webdesign688.redcross.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class IndexActivity extends Activity {

	private ImageView mIv_A;
	private ImageView mIv_O;
	private ImageView mIv_B;
	private ImageView mIv_AB;
	private TextView mTv_TargetNum;
	private TextView mTv_Num;
	private ProgressBar mProgressBar;
	private boolean isCn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		TextView tv_bloodtitle= (TextView) findViewById(R.id.tv_index_bloodtitle);
		TextView tv_Index1= (TextView) findViewById(R.id.tv_index_1);
		TextView tv_Index2= (TextView) findViewById(R.id.tv_index_2);
		TextView tv_Index3= (TextView) findViewById(R.id.tv_index_3);
		TextView tv_Index4= (TextView) findViewById(R.id.tv_index_4);
		
		TextView tv_zuori= (TextView) findViewById(R.id.tv_index_zuori);
		Button button= (Button) findViewById(R.id.btn_index_enter);
		
		
		
		SharedPreferences sp = getSharedPreferences(getResources().getString(R.string.sp_name), MODE_PRIVATE);
		 isCn = sp.getBoolean(getResources().getString(R.string.sp_value), true);
		 if (isCn) {
			 tv_zuori.setText("昨日捐血人數:  ");
             tv_bloodtitle.setText("血庫存量");
             tv_Index1.setText("充裕");
             tv_Index2.setText("滿意");
             tv_Index3.setText("尚可");
             tv_Index4.setText("低");
             button.setText(" 進入主頁 >>");
		}
		 else {
			 tv_bloodtitle.setText("Blood Inventories");
			 tv_Index1.setText("Adequate");
			 tv_Index2.setText("Satisfactory");
			 tv_Index3.setText("Acceptable");
			 tv_Index4.setText("Low");
			 tv_zuori.setText("Yesterday the number of donors:  ");
			 button.setText(" Enter >>");
			
		}
		findId();
		//download
		download();
	}
	private void download() {
		
	new DownLoadAsy().execute(Content.URL_BLOODSTOCK);
		
	}
	class  DownLoadAsy extends  AsyncTask<String, Void, String>{
          @Override
        protected void onPostExecute(String result) {
        	super.onPostExecute(result);
        	JSONObject   gen; 
        	    try {
        	    	 gen=new JSONObject(result);
        	    	 String    code= gen.getString("code");
        	    	 Log.e("0", code);
        	    	 if ("0".equals(code)) {
        	    		 if (isCn) {
        	    			 new AlertInfoDialog(IndexActivity.this).show();
							
						} else {
							 new AlertInfoDialog_EN(IndexActivity.this).show();
						}
					}
        	    	 else {
        	    		 JSONObject data = gen.getJSONObject("data");
        	    		 int  int_StockA = data.getInt("StockA");
        	    		 int int_StockB = data.getInt("StockB");
        	    		 int int_StockO = data.getInt("StockO");
        	    		 int int_StockAB = data.getInt("StockAB");
        	    		 String string_StockPercent = data.getString("StockPercent");
        	    		 String string_StockPeople = data.getString("StockPeople");
        	    		 Log.e("int_StockA", int_StockA+"");
        	    		 Log.e("int_StockB", int_StockB+"");
        	    		 Log.e("int_StockO", int_StockO+"");
        	    		 Log.e("int_StockAB", int_StockAB+"");
        	    		 //���ý�����Ϣ
        	    		 if (isCn) {
							
        	    			 mTv_Num.setText(string_StockPeople+"人");
        	    			 mTv_TargetNum.setText("每日捐血人數目標"+string_StockPercent+"人");
						} else {
       	    			 mTv_Num.setText(string_StockPeople+"");
       	    			 mTv_TargetNum.setText("Daily target of donors"+string_StockPercent+"");

						}
        	    		 //����Ѫ��
        	    		 blood(int_StockA,int_StockB,int_StockO,int_StockAB);
        	    		 //set progressbar
        	    		 if ("".equals(string_StockPeople)||string_StockPeople==null) {
							
						}
        	    		 else {
							if ("".equals(string_StockPercent)||string_StockPercent==null) {
								
							}
							else {
								          setProgressbar(string_StockPercent,string_StockPeople);
							}
						}
					}
        	    	 
				}
        	    catch (JSONException e) {
					// TODO Auto-generated catch block
        	    	if (isCn) {
        	    		new Dialog_noInternet(IndexActivity.this).show();
					} else {
						new Dialog_noInternet_En(IndexActivity.this).show();
					}
					e.printStackTrace();
				}
        }
		@Override
		protected String doInBackground(String... params) {
			              String string = params[0];
			return getJson.getData(string);
		}
	}
	private void findId() {
		mIv_A = (ImageView) findViewById(R.id.iv_index_a);
		mIv_O = (ImageView) findViewById(R.id.iv_index_o);
		mIv_B = (ImageView) findViewById(R.id.iv_index_b);
		mIv_AB = (ImageView) findViewById(R.id.iv_index_ab);
		mTv_Num = (TextView) findViewById(R.id.tv_index_num);
		mTv_TargetNum = (TextView) findViewById(R.id.tv_index_targetnum);
		mProgressBar = (ProgressBar) findViewById(R.id.progressBar_index);
		
	}
	public void setProgressbar(String string_StockPercent,
			String string_StockPeople) {
		
		int target_People = Integer.valueOf(string_StockPeople);
		int target_Percent = Integer.valueOf(string_StockPercent);
		Log.e("target_People", ""+target_People);
		Log.e("target_Percent", ""+target_Percent);
		
		mProgressBar.setMax(1500);
		mProgressBar.setProgress(target_People);
		
		
		
	}
	public void blood(int int_StockA, int int_StockB, int int_StockO, int int_StockAB) {
		//A��Ѫ
		switch (int_StockA) {
		case 1:
			mIv_A.setBackgroundResource(R.drawable.icon_style_o);
			break;
        case 2:
        	mIv_A.setBackgroundResource(R.drawable.icon_style_ab);
			break;
         case 3:
        	 mIv_A.setBackgroundResource(R.drawable.icon_style_a);
	  break;
          case 4:
        	  mIv_A.setBackgroundResource(R.drawable.icon_style_b);
	break;

		default:
			break;
		}
		//B��Ѫ
				switch (int_StockB) {
				case 1:
					mIv_B.setBackgroundResource(R.drawable.icon_style_o);
					break;
		        case 2:
		        	mIv_B.setBackgroundResource(R.drawable.icon_style_ab);
					break;
		         case 3:
		        	 mIv_B.setBackgroundResource(R.drawable.icon_style_a);
			  break;
		          case 4:
		        	  mIv_B.setBackgroundResource(R.drawable.icon_style_b);
			break;

				default:
					break;
				}
				//AB��Ѫ
				switch (int_StockAB) {
				case 1:
					mIv_AB.setBackgroundResource(R.drawable.icon_style_o);
					break;
		        case 2:
		        	mIv_AB.setBackgroundResource(R.drawable.icon_style_ab);
					break;
		         case 3:
		        	 mIv_AB.setBackgroundResource(R.drawable.icon_style_a);
			  break;
		          case 4:
		        	  mIv_AB.setBackgroundResource(R.drawable.icon_style_b);
			break;

				default:
					break;
				}
				//O��Ѫ
				switch (int_StockO) {
				case 1:
					mIv_O.setBackgroundResource(R.drawable.icon_style_o);
					break;
		        case 2:
		        	mIv_O.setBackgroundResource(R.drawable.icon_style_ab);
					break;
		         case 3:
		        	 mIv_O.setBackgroundResource(R.drawable.icon_style_a);
			  break;
		          case 4:
		        	  mIv_O.setBackgroundResource(R.drawable.icon_style_b);
			break;

				default:
					break;
				}
	}
	public void btn_enter(View v) {
                   startActivity(new Intent(this,MainActivity.class));
                   finish();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
