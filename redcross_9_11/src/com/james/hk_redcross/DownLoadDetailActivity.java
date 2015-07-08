package com.james.hk_redcross;

import java.io.File;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.webdesign688.redcross.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class DownLoadDetailActivity extends Activity {

	private TextView mTv_title;
	private Button mBtn_Down;
	private String mString_Id;
	private String mString_Title;
	private HttpHandler handler;
	private String mString_File;
	private TextView mTv_result;
	private boolean isCn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_down_load_detail);
		
		  mBtn_Down = (Button) findViewById(R.id.btn_download);
		  TextView   tv_bartitle= (TextView) findViewById(R.id.tv_download_bartitle);
		
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		SharedPreferences sp = getSharedPreferences(getResources().getString(R.string.sp_name), MODE_PRIVATE);
		 isCn = sp.getBoolean(getResources().getString(R.string.sp_value), true);
		 
		 if (isCn) {
             tv_bartitle.setText("表格下載");
             mBtn_Down.setText("下載");
		} else {
		              	tv_bartitle.setText("Download Forms");
                    mBtn_Down.setText("Download");
		}
		 
		initUi();
		Intent intent = getIntent();
		mString_Title = intent.getStringExtra("title");
		mString_File = intent.getStringExtra("file");
		mTv_title.setText(mString_Title);
	}

	private void initUi() {
                         mTv_title = (TextView) findViewById(R.id.tv_download_title);
                       
                         mTv_result = (TextView) findViewById(R.id.tv_download_result);
	}
	public  void btn_download(View v) {
		if (mString_File==null||"".equals(mString_File)) {
			
		}
		else {
			download();
		}
		
		
	}

	private void download() {
		// TODO Auto-generated method stub
		//��ȡ��������ׂ��ַ�?
        final String substring = mString_File.substring(54, mString_File.length());
        Log.e("substring", substring);
         HttpUtils http = new HttpUtils();
            final String   AudioUrl="/sdcard/redcross/";
/*downloadingMediaFile = new File(AudioUrl+"/hk_feel/test55.mp3");
if (downloadingMediaFile.exists()) {
downloadingMediaFile.delete();
}*/

             handler = http.download(mString_File, AudioUrl+substring,
	true, 
	new RequestCallBack<File>() { 
                
@Override
		public void onLoading(long total, long current,
				boolean isUploading) {
	             ProgressBar mProgressBar= (ProgressBar) findViewById(R.id.progressbar_download);
	             TextView mTextView= (TextView) findViewById(R.id.tv_download_percent);
	             mTextView.setText("  "+(int)current*100/total+"%");
	             mProgressBar.setMax((int)total);
	             mProgressBar.setProgress((int)current);
	             
	             Log.e("onLoading", ""+current);
	             
			super.onLoading(total, current, isUploading);
		}
@Override 
public void onStart() { 
	if (isCn) {
		
		Toast.makeText(DownLoadDetailActivity.this, "下載...", Toast.LENGTH_SHORT).show();
	}
	else {
		Toast.makeText(DownLoadDetailActivity.this, "download...", Toast.LENGTH_SHORT).show();
	}
	//testTextView.setText("conn...");
	} 
@Override 
public void onFailure(HttpException error, String msg) {
 	               mTv_result.setText(msg);
	          //testTextView.setText(msg); 
	}
@SuppressWarnings("deprecation")
@Override
public void onSuccess(ResponseInfo<File> responseInfo) {
	
	if (isCn) {
		
		Toast.makeText(DownLoadDetailActivity.this, "下載成功", Toast.LENGTH_LONG).show();
		mTv_result.setText("成功下載到"+AudioUrl+substring);
	}
	else {
		Toast.makeText(DownLoadDetailActivity.this, "Download successful", Toast.LENGTH_LONG).show();
		mTv_result.setText("Has been successfully downloaded to "+AudioUrl+substring);
	}
} });
	}

}
