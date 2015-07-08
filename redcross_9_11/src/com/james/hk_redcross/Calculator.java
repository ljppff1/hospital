package com.james.hk_redcross;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.james.hk_redcross.util.AlertInfoDialog;
import com.james.hk_redcross.util.Dialog_noInternet;
import com.james.hk_redcross.util.Dialog_noInternet_En;
import com.james.hk_redcross.util.getJson;
import com.webdesign688.redcross.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Calculator extends Activity  {

	private RadioGroup mRadioGroup_Year;
	private RadioGroup mRadioGroup_Pres;
	private RadioGroup mRadioGroup_Next;
	private Spinner mSpinner_Day;
	private Spinner mSpinner_Month;
	private Spinner mSpinner_Year;
	private String mString_Group_year;
	private String mString_Group_pres;
	private String mString_Group_Next;
	private String[] mStringYearList;
	private String[] mStringDayList;
	private String[] mStringMonthList;
	
	private String mString_Year;
	private String mString_Month;
	private String mString_Day;
	
	private RadioButton mRadioButton0;
	private RadioButton mRadioButton1;
	private RadioButton mRadioButton2;
	private RadioButton mRadioButton5;
	private RadioButton mRadioButton6;
	private RadioButton mRadioButton7;
	private RadioButton mRadioButton8;
	public TextView mTv_Cal1;
	public TextView mTv_Cal2;
	public TextView mTv_Cal3;
	private boolean isCn;
	private String[] mStringMonthList_En;
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculator);
		TextView textView= (TextView) findViewById(R.id.tv_calculator_title);
		SharedPreferences sp = getSharedPreferences(getResources().getString(R.string.sp_name), MODE_PRIVATE);
		 isCn = sp.getBoolean(getResources().getString(R.string.sp_value), true);
		 if (isCn) {
			
			 textView.setText("捐血計算器");
		}
		 else {
			 textView.setText("Donation Due Date Calculator");
		}
		initString();
		initUi();
	}

	private void initString() {
		
		if (isCn) {
			
			
			mStringDayList = new String[]{"月份",
					"1","2","3","4",  "5","6","7","8","9","10","11","12","13","14","15","16", 
					"17","18","19","20",  "21","22","23","24","25","26","27","28","29","30","31"};
			mStringMonthList = new String[]{"日期","1","2","3","4","5","6","7","8","9","10","11","12"};
			mStringYearList= new String[]{"年份","2013","2014","2015"};
		}
		else {
			mStringDayList = new String[]{"Day",
					"1","2","3","4",  "5","6","7","8","9","10","11","12","13","14","15","16", 
					"17","18","19","20",  "21","22","23","24","25","26","27","28","29","30","31"};
			mStringMonthList = new String[]{"Month","1","2","3","4",  "5","6","7","8","9","10","11","12"};
			mStringMonthList_En = new String[]{"Month","Jan","Feb","Mar","Apr", "May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
			mStringYearList= new String[]{"Year","2013","2014","2015"};
		}
		
	}

	private void initUi() {
        mRadioGroup_Year = (RadioGroup) findViewById(R.id.radioGroup_year);      
        mRadioGroup_Pres= (RadioGroup) findViewById(R.id.radioGroup_pres);         
        mRadioGroup_Next = (RadioGroup) findViewById(R.id.radioGroup_next);  
       
             mRadioButton0= (RadioButton) findViewById(R.id.radio_calculator_1);
             mRadioButton1= (RadioButton) findViewById(R.id.radio_calculator_2);
              mRadioButton2= (RadioButton) findViewById(R.id.radio_calculator_3);
            mRadioButton5= (RadioButton) findViewById(R.id.radio_calculator_5);
              mRadioButton6= (RadioButton) findViewById(R.id.radio_calculator_6);
            mRadioButton7= (RadioButton) findViewById(R.id.radio_calculator_7);
            mRadioButton8= (RadioButton) findViewById(R.id.radio_calculator_8);
            
            
            TextView mTv_predate= (TextView) findViewById(R.id.tv_calculator_predate);
            TextView mTv_pretype= (TextView) findViewById(R.id.tv_calculator_pretype);
            TextView mTv_nexttype= (TextView) findViewById(R.id.tv_calculator_nexttype);
            TextView mTv_fit= (TextView) findViewById(R.id.tv_calcutor_fit);
            Button btn_calculator= (Button) findViewById(R.id.btn_calculator_calculator);
            Button btn_clear= (Button) findViewById(R.id.btn_calculator_clear);
            
            if (isCn) {
				
			}
            else {
				
            	mRadioButton0.setText("Adult Male (18 years or above) ");
            	mRadioButton1.setText("Adult Female (18 years or above)");
            	mRadioButton2.setText("People aged 16 /17");
            	mRadioButton5.setText("Whole Blood");
            	mRadioButton6.setText("Apheresis");
            	mRadioButton7.setText("Whole Blood");
            	mRadioButton8.setText("Apheresis");
            	
            	mTv_predate.setText("Last Donation Date:");
            	mTv_pretype.setText("Last Donation Type:");
            	mTv_nexttype.setText("Next Donation Type:");
            	mTv_fit.setText("You are eligible to donate on or after:");
            	
            	btn_calculator.setText("Calculate");
            	btn_clear.setText("Clear");
            	
			}
            
             
            mTv_Cal1 = (TextView) findViewById(R.id.tv_calculator1);
              mTv_Cal2 = (TextView) findViewById(R.id.tv_calculator2);
              mTv_Cal3 = (TextView) findViewById(R.id.tv_calculator3);
            
             
              
        mSpinner_Day = (Spinner) findViewById(R.id.spinner_day);		
        mSpinner_Month = (Spinner) findViewById(R.id.spinner_month);
        mSpinner_Year = (Spinner) findViewById(R.id.spinner_year);

        ArrayAdapter<String>  mAdapter1=new ArrayAdapter<String>(this, R.layout.item_spinner1, mStringDayList);
        mSpinner_Day.setAdapter(mAdapter1);
        if (isCn) {
        	ArrayAdapter<String>  mAdapter2=new ArrayAdapter<String>(this, R.layout.item_spinner1, mStringMonthList);
        	mSpinner_Month.setAdapter(mAdapter2);
		}
        else {
        	ArrayAdapter<String>  mAdapter2=new ArrayAdapter<String>(this, R.layout.item_spinner1, mStringMonthList_En);
        	mSpinner_Month.setAdapter(mAdapter2);
		}
        ArrayAdapter<String>  mAdapter3=new ArrayAdapter<String>(this, R.layout.item_spinner1, mStringYearList);
        mSpinner_Year.setAdapter(mAdapter3);
        
        itemSelect();
	}

	private void itemSelect() {
             mSpinner_Day.setOnItemSelectedListener(new OnItemSelectedListener() {


				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					if (arg2==0) {
						mString_Day= "0";
					}
					else {
						mString_Day=mStringDayList[arg2];
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			})	;	
             mSpinner_Month.setOnItemSelectedListener(new OnItemSelectedListener() {

 				@Override
 				public void onItemSelected(AdapterView<?> arg0, View arg1,
 						int arg2, long arg3) {
					// TODO Auto-generated method stub
					if (arg2==0) {
						mString_Month= "0";
					}
					else {
						
						mString_Month=mStringMonthList[arg2];
					
					}
				}

 				@Override
 				public void onNothingSelected(AdapterView<?> arg0) {
 					// TODO Auto-generated method stub
 					
 				}
 			})	;	
             mSpinner_Year.setOnItemSelectedListener(new OnItemSelectedListener() {

 				@Override
 				public void onItemSelected(AdapterView<?> arg0, View arg1,
 						int arg2, long arg3) {
					// TODO Auto-generated method stub
					if (arg2==0) {
						mString_Year= "0";
					}
					else {
						
						mString_Year=mStringYearList[arg2];
					
					}
				}

 				@Override
 				public void onNothingSelected(AdapterView<?> arg0) {
 					// TODO Auto-generated method stub
 					
 				}
 			})	;	
	}
	public  void btn_calculator(View  v) {
		
		  mTv_Cal1.setText("  ");
		    mTv_Cal2.setText("  ");
		    mTv_Cal3.setText("  ");
	       	isChecked();
		  Log.e("btn_calculator", R.string.UI_URL+"json_blooddonation.asp?" +
				"ageold=" +mString_Group_year+
				"&prevD=" +mString_Day+
				"&prevM=" +mString_Month+
				"&prevY=" +mString_Year+
				"&lasttype=" +mString_Group_pres+
				"&nexttype="+mString_Group_Next);
		  if ("0".equals(mString_Day)) {
			showDialog(1);
		}
		  else {
			if ("0".equals(mString_Month)) {
				showDialog(2);
			}
			else {
				if ("0".equals(mString_Year)) {
					showDialog(3);
				}
				else {
					new DownLoadAsy().execute("http://patrick.imymedia.com/redcross/json_blooddonation.asp?" +
							"ageold=" +mString_Group_year+
							"&prevD=" +mString_Day+
							"&prevM=" +mString_Month+
							"&prevY=" +mString_Year+
							"&lasttype=" +mString_Group_pres+
							"&nexttype="+mString_Group_Next);
				}
			}
		}
	}
	public  void btn_clear(View  v) {
		
    mRadioButton0.setChecked(true);
    mRadioButton5.setChecked(true);
    mRadioButton7.setChecked(true);
    mSpinner_Day.setSelection(0);
    mSpinner_Month.setSelection(0);
    mSpinner_Year.setSelection(0);
    mTv_Cal1.setText("  ");
    mTv_Cal2.setText("  ");
    mTv_Cal3.setText("  ");
		
	}
	class  DownLoadAsy extends  AsyncTask<String, Void, String>{
		@Override
      protected void onPostExecute(String result) {
      	super.onPostExecute(result);
      	JSONObject   gen; 
      	    try {
      	    	 gen=new JSONObject(result);
      	    	 String    code= gen.getString("code");
      	    	 if ("0".equals(code)) {
      	    		 Log.e("0", code);

      	    		 String msg = gen.getString("msg");
      	    		  if ("Error date".equals(msg)) {
						showDialog(4);
					}
      	    		  if ("Error type".equals(msg)) {
  						showDialog(5);
  					}
      	    		 if ("ok".equals(msg)) {
   						showDialog(6);
   					}
					}
      	    	 else {
      	    		    String mdata = gen.getString("data");
      	    		 Log.e("int_StockB", mdata+"");
      	    		 String[] split = mdata.split("/");
      	    		  if (isCn) {
						
      	    			  mTv_Cal1.setText(split[0]+"年");
      	    			  mTv_Cal2.setText(split[1]+"月");
      	    			  mTv_Cal3.setText(split[2]+"日");}
      	    		  else {
      	    			  if ("1".equals(split[1])) {
      	    				 mTv_Cal2.setText("Jan");
						}
      	    			  else if ("2".equals(split[1])) {
      	    				 mTv_Cal2.setText("Feb");
						             } 
      	    			else if ("3".equals(split[1])) {
      	    				 mTv_Cal2.setText("Mar");
			             } 
      	    			else if ("4".equals(split[1])) {
      	    				mTv_Cal2.setText("Apr");
			             } 
      	    			else if ("5".equals(split[1])) {
      	    				 mTv_Cal2.setText("May");
			             } 
      	    			else if ("6".equals(split[1])) {
      	    				 mTv_Cal2.setText("Jun");
			             } 
      	    			else if ("7".equals(split[1])) {
      	    				 mTv_Cal2.setText("Jul");
			             } 
      	    			else if ("8".equals(split[1])) {
      	    				 mTv_Cal2.setText("Aug");
			             } 
      	    			else if ("9".equals(split[1])) {
      	    				 mTv_Cal2.setText("Sep");
			             } 
      	    			else if ("10".equals(split[1])) {
      	    				 mTv_Cal2.setText("Otc");
			             } 
      	    			else if ("11".equals(split[1])) {
      	    				 mTv_Cal2.setText("Nov");
			             } else if ("12".equals(split[1])) {
			            	 mTv_Cal2.setText("Dec");	
			             } 
			             else {
							
						}
      	    			mTv_Cal1.setText(split[0]);
    	    			  mTv_Cal3.setText(split[2]);
					}
					}
      	    	 
				}
      	    catch (JSONException e) {
      	    	if (isCn) {
					
      	    		new Dialog_noInternet(Calculator.this).show();
				} else {
					new Dialog_noInternet_En(Calculator.this).show();
				}
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
      }
		@Override
		protected String doInBackground(String... params) {
			              String string = params[0];
			return getJson.getData(string);
		}
	}
       private void isChecked() {
       if (mRadioButton0.isChecked()) {
    	   mString_Group_year = "1";
		
	}
			 if (mRadioButton1.isChecked()) {
				 mString_Group_year = "2";
					
				}
    	 if (mRadioButton2.isChecked()) {
    			
    		 mString_Group_year = "3";
    		}
    	 if (mRadioButton7.isChecked()) {
    			
    		 mString_Group_pres = "1";
    		}
			 if (mRadioButton8.isChecked()) {
					
				 mString_Group_pres = "2";
				}
    	 if (mRadioButton5.isChecked()) {
    			
    		 mString_Group_Next = "1";
    		}  
			 if (mRadioButton6.isChecked()) {
					
				 mString_Group_Next = "2";
				}

	}
   	@Deprecated
	protected Dialog onCreateDialog(int id) {
   		if (isCn) {
   			if (id==1) {
   				return new AlertDialog.Builder(this).setTitle("提示").setMessage("请选择日期").setPositiveButton("確定", null).create();
   			}
   			
   			else if(id==2) {
   				
   				return new AlertDialog.Builder(this).setTitle("提示").setMessage("请选择月份").setPositiveButton("確定", null).create();
   			}
   	     	else if(id==3) {
   				
   				return new AlertDialog.Builder(this).setTitle("提示").setMessage("请选择年份").setPositiveButton("確定", null).create();
   			}
   	       else if(id==5) {
   				
   				return new AlertDialog.Builder(this).setTitle("提示").setMessage("十六至十七岁之青少年不能捐赠成份血").setPositiveButton("確定", null).create();
   			}
   	     	else if(id==4){
   	     		return new AlertDialog.Builder(this).setTitle("提示").setMessage("请选择有效日期").setPositiveButton("確定", null).create();
   	     	}
   	     	else if(id==6){
   	     		return new AlertDialog.Builder(this).setTitle("提示").setMessage("您已到期捐血，您可先进行「您是否适宜捐血自我评估」，如通过初步评估便可随时捐血，很多病人都需要血液去延续生命，请定期回来捐血。").setPositiveButton("確定", null).create();
   	     	}
   	     	else {
   	     		return new AlertDialog.Builder(this).setTitle("提示").setMessage("Error date").setPositiveButton("確定", null).create();
   	     	}
   			// TODO Auto-generated method stub
   		}
   		else {

   			if (id==1) {
   				return new AlertDialog.Builder(this).setTitle("Hint").setMessage("Please Select the Date").setPositiveButton("ensure", null).create();
   			}
   			
   			else if(id==2) {
   				
   				return new AlertDialog.Builder(this).setTitle("Hint").setMessage("Please Select the Month").setPositiveButton("ensure", null).create();
   			}
   	     	else if(id==3) {
   				
   				return new AlertDialog.Builder(this).setTitle("Hint").setMessage("Please Select the Year").setPositiveButton("ensure", null).create();
   			}
   	       else if(id==5) {
   				
   				return new AlertDialog.Builder(this).setTitle("Hint").setMessage("Among 16 - to 17 ingredients they can not donate blood").setPositiveButton("ensure", null).create();
   			}
   	     	else if(id==4){
   	     		return new AlertDialog.Builder(this).setTitle("hint").setMessage("Please Enter Valid Date").setPositiveButton("ensure", null).create();
   	     	}
   	     	else if(id==6){
   	     		return new AlertDialog.Builder(this).setTitle("Hint").setMessage("You are due for blood donation. You can carry out the 'Are you fit to donate blood' self-assessment first to determine your eligibility. If you are preliminarily eligible, please donate blood regularly as many patients rely on blood transfusion to extend their life").setPositiveButton("ensure", null).create();
   	     	}
   	     	else {
   	     		return new AlertDialog.Builder(this).setTitle("Hint").setMessage("Error date").setPositiveButton("ensure", null).create();
   	     	}
   			// TODO Auto-generated method stub
   		
		}
		}
		

}
