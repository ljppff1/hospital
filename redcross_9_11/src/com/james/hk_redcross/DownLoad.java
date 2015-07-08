package com.james.hk_redcross;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.james.hk_redcross.LatestNew.Data;
import com.james.hk_redcross.LatestNew.MyAdapter;
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
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class DownLoad extends Activity {

	private ListView mListView;
	private    ArrayList<Data>  mDataList=new ArrayList<DownLoad.Data>();
	private boolean isCn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_latest_new);
		TextView mTextView= (TextView) findViewById(R.id.tv_latestnew);
		
		SharedPreferences sp = getSharedPreferences(getResources().getString(R.string.sp_name), MODE_PRIVATE);
		 isCn = sp.getBoolean(getResources().getString(R.string.sp_value), true);
		 
		 if (isCn) {
			 
			 mTextView.setText("表格下載");
		}
		 else {
			 mTextView.setText("Download Forms");
		}
		//download
		downLoad();
		
		
	}
     private void downLoad() {
    	 if (isCn) {
    		 new DownLoadAsy().execute(Content.URL_DOWNLOAD_HK);		
		}
    	 else {
    		 new DownLoadAsy().execute(Content.URL_DOWNLOAD_EN);
		}
	}
     class  DownLoadAsy extends  AsyncTask<String, Void, String>{
 	    @Override
 	  protected void onPostExecute(String result) {
 	    	super.onPostExecute(result);
         	JSONObject   gen=null; 
         	    try {
         	    	 gen=new JSONObject(result);
         	    	 String    code= gen.getString("code");
         	    	 if ("0".equals(code)) {
         	    		 if (isCn) {
         	    			 new AlertInfoDialog(DownLoad.this).show();
							
						} else {
							 new AlertInfoDialog_EN(DownLoad.this).show();
								
						}
         	    		 findViewById(R.id.progressbar_listview).setVisibility(View.GONE);
 					}
         	    	 else {
         	    		 JSONArray data = gen.getJSONArray("data");
         	    		 for (int i = 0; i < data.length(); i++) {
         	    			 Data daTa=new Data();
         	    			 JSONObject data_value = data.getJSONObject(i);
         	    			 daTa .id= data_value.getString("id");
         	    			 daTa .title = data_value.getString("title");
         	    			 daTa .file = data_value.getString("file");
         	    			 Log.e("data", daTa.id);
         	    			 Log.e("data", daTa.title);
         	    			Log.e("data", daTa.file);
         	    			 mDataList.add(daTa);
							}
         	    		 findViewById(R.id.progressbar_listview).setVisibility(View.GONE);
         	    		 mListView = (ListView) findViewById(R.id.listView_latestnew);
         	    		 mListView.setVisibility(View.VISIBLE);
         	    		 mListView.setAdapter(new MyAdapter());
         	    		 //  clicklistView
         	    		 clickListview();
 					}
         	    	 
 				}
         	    catch (JSONException e) {
 					// TODO Auto-generated catch block
         	    	if (isCn) {
         	    		new Dialog_noInternet(DownLoad.this).show();
						
					} else {
         	    		new Dialog_noInternet_En(DownLoad.this).show();

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
     class   Data{
	     String   id;
		 String  title;
		 String  file;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getFile() {
			return file;
		}
		public void setFile(String file) {
			this.file = file;
		}
     }
     class MyAdapter extends BaseAdapter{
    	 @Override
 		public View getView(int position, View convertView, ViewGroup parent) {
    		 View layout = getLayoutInflater().inflate(R.layout.item_listview_latest, null);
    		 TextView tv_Latest= (TextView) layout.findViewById(R.id.tv_latest);
    		 tv_Latest.setText(mDataList.get(position).title);
 			return layout;
 		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mDataList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		}

	public void clickListview() {
		// TODO Auto-generated method stub
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(DownLoad.this,DownLoadDetailActivity.class);
				intent.putExtra("title", mDataList.get(position).title);
				intent.putExtra("file", mDataList.get(position).file);
				
				startActivity(intent);
				
				
			}
		});
		
	}

}
