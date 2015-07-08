package com.james.hk_redcross;

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
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

@SuppressLint("NewApi")
            public class LatestNewDetailActivity extends Activity {

                private String mString_Id;
                private TextView mTv_Title;
                private boolean isDonate;
                private boolean isCn;

                @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_latest_new_detail);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    SharedPreferences sp = getSharedPreferences(getResources().getString(R.string.sp_name), MODE_PRIVATE);
                    isCn = sp.getBoolean(getResources().getString(R.string.sp_value), true);
                    mTv_Title = (TextView) findViewById(R.id.tv_title_detail);
                    //obtain id
                    Intent intent = getIntent();
                    mString_Id = intent.getStringExtra("id");
                    Log.e("Detail", mString_Id);
                    //downLoad
                    downLoad();
                }

                private void downLoad() {
                    if (isCn) {
                        new  DownLoadAsy().execute(Content.URL_ARTCLE_HK+mString_Id);
                    } else {
                        new  DownLoadAsy().execute(Content.URL_ARTCLE_EN+mString_Id);
                    }
                    // TODO Auto-generated method stub
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
      	    			 new AlertInfoDialog(LatestNewDetailActivity.this).show();
						
					} else {
     	    			 new AlertInfoDialog_EN(LatestNewDetailActivity.this).show();

					}
					}
      	    	 else {
      	    		 JSONObject data = gen.getJSONObject("data");
      	    		 String  string_Title = data.getString("Title");
      	    		 String string_Content = data.getString("Content");
      	    		 Log.e("int_StockA", string_Title+"");
      	    		 Log.e("int_StockB", string_Content+"");
      	    		 
      	    		 
      	    		 //show content
      	    		mTv_Title.setText(string_Title);
      	    		
      	    		 mWebView = (WebView) findViewById(R.id.webView1);
      	    		mWebView.getSettings().setUseWideViewPort(false);
        			  mWebView.getSettings().setBuiltInZoomControls(false);
        			  WebSettings setting = mWebView.getSettings();
        					setting.setJavaScriptEnabled(true);
        					setting.setBuiltInZoomControls(false);
        					setting.setDisplayZoomControls(false);
        					setting.setSupportZoom(false);

        					setting.setDomStorageEnabled(true);
        					setting.setDatabaseEnabled(true);
        				//	setting.setLoadWithOverviewMode(true);
      			  
      			  String resultss = string_Content.replace("\"", "'");
      			  String resultss2 = resultss.replace("\\", "");
      			  Log.e("resultss", resultss);
      			  Log.e("resultss2", resultss2);
      			  mWebView.loadDataWithBaseURL(null, resultss2, null, "UTF-8", null);
					}
      	    	 
				}
      	    catch (JSONException e) {
      	    	if (isCn) {
      	    		 new Dialog_noInternet(LatestNewDetailActivity.this).show();
				} else {
					new Dialog_noInternet_En(LatestNewDetailActivity.this).show();
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
