package com.james.hk_redcross;

import org.json.JSONException;
import org.json.JSONObject;

import com.james.hk_redcross.STationDetailActivity.DownLoadAsy;
import com.james.hk_redcross.util.AlertInfoDialog;
import com.james.hk_redcross.util.AlertInfoDialog_EN;
import com.james.hk_redcross.util.Content;
import com.james.hk_redcross.util.Dialog_noInternet;
import com.james.hk_redcross.util.Dialog_noInternet_En;
import com.james.hk_redcross.util.getJson;
import com.webdesign688.redcross.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class ContactUs extends Activity {

	private WebView mWebView;
	private String mString_Id;
	private boolean misDonate;
	private boolean misIntroduce;
	private boolean isCn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_condition);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		TextView mtTextView= (TextView) findViewById(R.id.tv_conditon_title);
		SharedPreferences sp = getSharedPreferences(getResources().getString(R.string.sp_name), MODE_PRIVATE);
		 isCn = sp.getBoolean(getResources().getString(R.string.sp_value), true);
		 if (isCn) {
			
			 mtTextView.setText("聯絡我們");
		}
		 else {
			 mtTextView.setText("Contact Us");
		}
		downLoad();
		
	}

	private void downLoad() {
			if (isCn) {
				new DownLoadAsy().execute(Content.URL_CONTACT_HK)	;
			}
			else {
				new DownLoadAsy().execute(Content.URL_CONTACT_EN)	;
			}
	}
	class  DownLoadAsy extends  AsyncTask<String, Void, String>{
        private WebView mWebView;
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
      	    			 new AlertInfoDialog(ContactUs.this).show();
					} else {
						new AlertInfoDialog_EN(ContactUs.this).show();
					}
					}
      	    	 else {
      	    		 JSONObject data = gen.getJSONObject("data");
      	    		 String string_Content = data.getString("ArticleContent");
      	    		 Log.e("int_StockB", string_Content+"");
      	    		 
      	    		 //show content
      	    		mWebView = (WebView) findViewById(R.id.wb_condition);
      	    		mWebView.getSettings().setUseWideViewPort(false);
        			  mWebView.getSettings().setBuiltInZoomControls(true);
        			  WebSettings setting = mWebView.getSettings();
        					setting.setJavaScriptEnabled(true);
        					setting.setBuiltInZoomControls(false);
        					setting.setDisplayZoomControls(false);
        					setting.setSupportZoom(false);

        					setting.setDomStorageEnabled(true);
        					setting.setDatabaseEnabled(true);
        					// ȫ����ʾ
        					//setting.setLoadWithOverviewMode(true);
      			  
      			    //�D�Q
      			  String resultss = string_Content.replace("\"", "'");
      			  String resultss2 = resultss.replace("\\", "");
      			  Log.e("resultss", resultss);
      			  Log.e("resultss2", resultss2);
      		      //�@ʾ����
      			  mWebView.loadDataWithBaseURL(null, resultss2, null, "UTF-8", null);
					}
      	    	 
				}
      	    catch (JSONException e) {
					// TODO Auto-generated catch block
      	    	if (isCn) {
					
      	    		new Dialog_noInternet(ContactUs.this).show();
				} else {
					new Dialog_noInternet_En(ContactUs.this).show();
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

}
