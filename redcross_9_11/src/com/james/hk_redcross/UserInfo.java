package com.james.hk_redcross;import java.util.Calendar;import org.json.JSONException;import org.json.JSONObject;import com.james.hk_redcross.util.Dialog_noInternet;import com.james.hk_redcross.util.Dialog_noInternet_En;import com.james.hk_redcross.util.getJson;import com.webdesign688.redcross.R;import android.app.Activity;import android.app.DatePickerDialog;import android.app.Dialog;import android.app.DatePickerDialog.OnDateSetListener;import android.content.SharedPreferences;import android.os.AsyncTask;import android.os.Bundle;import android.telephony.TelephonyManager;import android.text.Editable;import android.util.Log;import android.view.View;import android.view.View.OnClickListener;import android.widget.Button;import android.widget.DatePicker;import android.widget.EditText;import android.widget.RadioButton;import android.widget.TextView;public class UserInfo extends Activity{	 	private EditText et_setting_name;	private RadioButton mRadio_Male;	private RadioButton mRadio_Female;	private RadioButton setting_radio_1;	private RadioButton setting_radio_2;	private RadioButton mRadio_A;	private RadioButton mRadio_B;	private RadioButton mRadio_AB;	private RadioButton mRadio_O;	private TextView et_setting_date;    private int year1;	private int monthOfYear1;	private int dayOfMonth1;	private Button btn_setting_save;	private TextView tv_setting_title;	private boolean isCn;	private TextView tv_setting_sex;	private TextView tv_setting_notice;	private TextView tv_setting_birthday;	private TextView tv_setting_bloodtype;	private String IMEI;	@Override	protected void onCreate(Bundle savedInstanceState) {		super.onCreate(savedInstanceState);		setContentView(R.layout.activity_userinfo);		TelephonyManager tm = (TelephonyManager) this				.getSystemService(TELEPHONY_SERVICE);		IMEI = tm.getDeviceId();		     Calendar calendar = Calendar.getInstance();		   year1 = calendar.get(Calendar.YEAR);	    monthOfYear1 = calendar.get(Calendar.MONTH);	   dayOfMonth1 = calendar.get(Calendar.DAY_OF_MONTH);       SharedPreferences sp = getSharedPreferences(getResources().getString(R.string.sp_name), 0);		 isCn = sp.getBoolean(getResources().getString(R.string.sp_value), true);		initView();			}	private void initView() {		tv_setting_title =(TextView)this.findViewById(R.id.tv_setting_title);		et_setting_name =(EditText)this.findViewById(R.id.et_setting_name);		mRadio_Male = (RadioButton) this.findViewById(R.id.setting_radio_male);		mRadio_Female = (RadioButton) this.findViewById(R.id.setting_radio_female);		setting_radio_1 = (RadioButton) this.findViewById(R.id.setting_radio_1);		setting_radio_2 = (RadioButton) this.findViewById(R.id.setting_radio_2);		mRadio_A = (RadioButton) this.findViewById(R.id.radio_A);		mRadio_B = (RadioButton) this.findViewById(R.id.radio_B);		mRadio_AB = (RadioButton) this.findViewById(R.id.radio_AB);		mRadio_O = (RadioButton) this.findViewById(R.id.radio_O);		et_setting_date =(TextView)this.findViewById(R.id.et_setting_date);		et_setting_date.setOnClickListener(listener);		btn_setting_save =(Button)this.findViewById(R.id.btn_setting_save);		btn_setting_save.setOnClickListener(listener);		tv_setting_sex =(TextView)this.findViewById(R.id.tv_setting_sex);		tv_setting_notice =(TextView)this.findViewById(R.id.tv_setting_notice);		tv_setting_birthday =(TextView)this.findViewById(R.id.tv_setting_birthday);		tv_setting_bloodtype=(TextView)this.findViewById(R.id.tv_setting_bloodtype);						if (isCn) {								} else {                 			tv_setting_title.setText("Registration information");			tv_setting_notice.setText("Whether to receive the notice");			et_setting_name.setText("Name  ");			tv_setting_sex.setText("Gender");			tv_setting_bloodtype.setText("Bloodtype");			btn_setting_save.setText("Sure");			tv_setting_birthday.setText("Date of birth");			setting_radio_1.setText("Yes");			setting_radio_2.setText("No");			mRadio_Female.setText("Female");			mRadio_Male.setText("Male");					}			}	private OnClickListener listener =new OnClickListener() {				@Override		public void onClick(View v) {			switch (v.getId()) {			case R.id.et_setting_date:				showDialog(1);								break;			case R.id.btn_setting_save:				String man ="1";				if(mRadio_Male.isChecked()){					man ="1";				}else{					man ="2";				}				String bload ="A";				if(mRadio_A.isChecked()){					bload ="A";				}else if(mRadio_B.isChecked()){					bload ="B";				}else if(mRadio_AB.isChecked()){					bload ="AB";				}else if(mRadio_O.isChecked()){					bload ="O";				}				String isnotice ="1";				if(setting_radio_1.isChecked()){					isnotice ="1";				}else{					isnotice ="0";				}				new DownLoadAsy().execute("http://josie.imymedia.com/redcross/json/reg.php?" +						"IMEI=" +IMEI+						"&UserName=" +et_setting_name.getText().toString()+						"&UserGender=" +man+						"&BirthYear=" +year1+						"&BirthMonth=" +monthOfYear1 +						"&BirthDate=" +dayOfMonth1 +						"&BloodType=" +bload +						"&ReceiveNotice=" +isnotice +						"&PreferredLanguage="+"0");							break;			default:				break;			}					}	};	class  DownLoadAsy extends  AsyncTask<String, Void, String>{		@Override      protected void onPostExecute(String result) {      	super.onPostExecute(result);      	JSONObject   gen;       	    try {      	    	 gen=new JSONObject(result);      	    	 String    code= gen.getString("code");      	    	 if ("0".equals(code)) {      	    		       	    	 }      	    	 else {      	    		    String mdata = gen.getString("data");      	    		 Log.e("int_StockB", mdata+"");      	    		 String[] split = mdata.split("/");      	    		  if (isCn) {      	    			        	    		  }      	    		  else {}					}      	    	 				}      	    catch (JSONException e) {      	    	if (isCn) {					      	    		new Dialog_noInternet(UserInfo.this).show();				} else {					new Dialog_noInternet_En(UserInfo.this).show();				}					// TODO Auto-generated catch block					e.printStackTrace();				}      }		@Override		protected String doInBackground(String... params) {			              String string = params[0];			return getJson.getData(string);		}	}	 protected Dialog onCreateDialog(int id) {		 switch (id) {		case 1:			return new DatePickerDialog(UserInfo.this,new OnDateSetListener(){				@Override				public void onDateSet(DatePicker view, int year,						int monthOfYear, int dayOfMonth) {				 year1 =year;				 monthOfYear1 =monthOfYear;				 dayOfMonth1 =dayOfMonth;				    				}							}, year1, monthOfYear1, dayOfMonth1);			}		return null;	 }	}