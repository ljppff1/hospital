package com.james.hk_redcross;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.james.hk_redcross.LatestNew.Data;
import com.james.hk_redcross.LatestNew.MyAdapter;
import com.james.hk_redcross.util.AlertInfoDialog;
import com.james.hk_redcross.util.AlertInfoDialog_EN;
import com.james.hk_redcross.util.Dialog_noInternet;
import com.james.hk_redcross.util.Dialog_noInternet_En;
import com.james.hk_redcross.util.getJson;
import com.webdesign688.redcross.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class BloodMatch extends Activity {

	private Spinner mSpinner_Mother;
	private Spinner mSpinner_Father;
	private String[] mStringFaLists;
	private String[] mStringMaLists;
	private String mStringMo_select;
	private String mStringFa_select;
	private TextView mTv_Result;
	private boolean isCn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blood_match);
		
		TextView mTextView= (TextView) findViewById(R.id.tv_match_title);
		TextView tv_Father= (TextView) findViewById(R.id.tv_match_father);
		TextView tv_Mother= (TextView) findViewById(R.id.tv_match_mother);
		TextView tv_Result_Title= (TextView) findViewById(R.id.tv_match_result_title);
		Button btn_match= (Button) findViewById(R.id.button_match);
		
		
		
		SharedPreferences sp = getSharedPreferences(getResources().getString(R.string.sp_name), MODE_PRIVATE);
		 isCn = sp.getBoolean(getResources().getString(R.string.sp_value), true);
		 if (isCn) {
			
			 mTextView.setText("血型對對碰");
		}
		 else {
			 mTextView.setText("Blood Group Heredity");
			 tv_Father.setText("Father's group");
			 tv_Mother.setText("Mother's group");
			 tv_Result_Title.setText("Possible blood group(s) for the children");
			 btn_match.setText("Matching");
		}
		initUi();
	}

	private void initUi() {
                       mSpinner_Father = (Spinner) findViewById(R.id.spinner_father);		
                       mSpinner_Mother = (Spinner) findViewById(R.id.spinner_mother);
                       mTv_Result = (TextView) findViewById(R.id.tv_match_result);
                  mStringFaLists = new String[]{"A","B","O","AB"    };
                  mStringMaLists = new String[]{"A","B","O","AB"    };
                      
                       ArrayAdapter<String>  mAdapter1=new ArrayAdapter<String>(this, R.layout.item_spinner1, mStringFaLists);
                       mSpinner_Father.setAdapter(mAdapter1);
                       ArrayAdapter<String>  mAdapter2=new ArrayAdapter<String>(this, R.layout.item_spinner1, mStringMaLists);
                       mSpinner_Mother.setAdapter(mAdapter2);
                       //clickSpinner
                       click();
	}

	       private void click() {
          mSpinner_Father.setOnItemSelectedListener(new OnItemSelectedListener() {

	

	     @Override
	     public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		          mStringFa_select = mStringFaLists[position];	
		        
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
})	;	
 mSpinner_Mother.setOnItemSelectedListener(new OnItemSelectedListener() {

		

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) {
			mStringMo_select = mStringMaLists[position];
	          
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
	})	;	
	}
	public  void btn_match(View v) {
                      		
  new DownLoadAsy().execute("http://patrick.imymedia.com/redcross/json_bloodmatch.asp?" +
  		"m="+mStringMo_select+"&f="+mStringFa_select);
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

                                 new AlertInfoDialog(BloodMatch.this).show();
                             } else {
                                 new AlertInfoDialog_EN(BloodMatch.this).show();
                             }
                         }
                         else {

                             String string = gen.getString("data");
                             mTv_Result.setText(string);
 					}
 				}
         	    catch (JSONException e) {
 					// TODO Auto-generated catch block
         	    	if (isCn) {
						
         	    		new Dialog_noInternet(BloodMatch.this).show();
					} else {
         	    		new Dialog_noInternet_En(BloodMatch.this).show();

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
