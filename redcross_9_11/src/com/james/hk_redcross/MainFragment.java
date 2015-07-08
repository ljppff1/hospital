package com.james.hk_redcross;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.AlertDialog;
import android.content.Intent;  
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;  
import android.support.v4.app.Fragment;  
import android.util.Log;  
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.View.OnClickListener;
import android.view.ViewGroup;  
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
  
import com.james.hk_redcross.util.MySwitch;
import com.webdesign688.redcross.R;
  
public class MainFragment extends Fragment implements OnClickListener {  
    private static final String TAG = "MainFragment";  
    private CheckBox mCheckBox_En;
	private CheckBox mCheckBox_Cn;
	private RadioButton mRadio_Female;
	private RadioButton mRadio_Male;
	private MainFragment mainFragment;
	private EditText mEditText_name;
	private EditText mEditText_date;
	private RadioButton mRadio_A;
	private RadioButton mRadio_B;
	private RadioButton mRadio_AB;
	private RadioButton mRadio_O;
	private boolean isCn;
	private View mView;
	private Button share_myfrist;

    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);

    }  
    
    @Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState) {  

        mView = inflater.inflate(R.layout.activity_setting, container, false);  
  
        
        SharedPreferences sp = getActivity().getSharedPreferences(getResources().getString(R.string.sp_name), 0);
		 isCn = sp.getBoolean(getResources().getString(R.string.sp_value), true);
		 Log.e("onCreate", isCn+"");
			TextView   tv_Title = (TextView) mView.findViewById(R.id.tv_setting_title);
			TextView   tv_Notify= (TextView)mView. findViewById(R.id.tv_setting_notify);
			TextView   tv_Language= (TextView)mView. findViewById(R.id.tv_setting_language);
			TextView   tv_Data= (TextView) mView.findViewById(R.id.tv_setting_data);
			TextView   tv_Name= (TextView) mView.findViewById(R.id.tv_setting_name);
			TextView   tv_Sex= (TextView) mView.findViewById(R.id.tv_setting_sex);
			TextView   tv_Bloodtype= (TextView) mView.findViewById(R.id.tv_setting_bloodtype);
			TextView tv_Birthday= (TextView)mView.findViewById(R.id.tv_setting_birthday);
			Button mBtn_Save= (Button) mView.findViewById(R.id.btn_setting_save);
			mBtn_Save.setOnClickListener(this);
			mEditText_name = (EditText)mView. findViewById(R.id.et_setting_name);
			mEditText_date = (EditText)mView. findViewById(R.id.et_setting_date);
			share_myfrist =(Button)mView.findViewById(R.id.share_myfrist);
			share_myfrist.setOnClickListener(listener);
			mRadio_Male = (RadioButton) mView.findViewById(R.id.setting_radio_male);
			mRadio_Female = (RadioButton) mView.findViewById(R.id.setting_radio_female);
			mRadio_A = (RadioButton) mView.findViewById(R.id.radio_A);
			mRadio_B = (RadioButton) mView.findViewById(R.id.radio_B);
			mRadio_AB = (RadioButton) mView.findViewById(R.id.radio_AB);
			mRadio_O = (RadioButton) mView.findViewById(R.id.radio_O);
			boolean isEdit = sp.getBoolean("isedit", false);
			 if (isEdit) {
				 
				 mEditText_name.setText(sp.getString("name", "")) ;
				 mEditText_date.setText(sp.getString("date", "")) ;
				 if (sp.getBoolean("man", false)) {
					 mRadio_Male.setChecked(true);
				}
				 if (sp.getBoolean("women", false)) {
					 mRadio_Female.setChecked(true);
				}
				 if (sp.getBoolean("a", false)) {
					 mRadio_A.setChecked(true);
				}
				 if (sp.getBoolean("b", false)) {
					 mRadio_B.setChecked(true);
				}
				 if (sp.getBoolean("ab", false)) {
					 mRadio_AB.setChecked(true);
				}
				 if (sp.getBoolean("o", false)) {
					 mRadio_O.setChecked(true);
				}
			}
			
			if (isCn) {
				

				
			} else {
				tv_Title.setText("Setting");
				tv_Notify.setText("Notification");
				tv_Language.setText("Language");
				tv_Name.setText("Name     ");
				mEditText_name.setText(sp.getString("name", "Surname first")) ;
				tv_Sex.setText("Gender");
				tv_Bloodtype.setText("Bloodtype");
				tv_Data.setText("Personal Data");
				mBtn_Save.setText("Save");
				tv_Birthday.setText("Date of birth");
				share_myfrist.setText("Share");
				mRadio_Female.setText("Female");
				mRadio_Male.setText("Male");
				
			}
			initUi();
		 
		 
        getActivity().getSharedPreferences("", 0);
        return mView;  
    }  
    
    OnClickListener listener =new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.share_myfrist:
				shareother();
				break;
			default:
				break;
			}
		}

		
	};
	

	private void shareother() {
		if (isCn) {
			 Intent intent=new Intent(Intent.ACTION_SEND);
	         intent.setType("text/plain");
	         intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
	         intent.putExtra(Intent.EXTRA_TEXT, "香港紅十字會輸血服務中心邀請您下載中心的APP，下載地址：  https://play.google.com/store/apps/details?id=com.webdesign688.redcross");
	         startActivity(Intent.createChooser(intent, "分享到"));
		}else{
			 Intent intent=new Intent(Intent.ACTION_SEND);
	         intent.setType("text/plain");
	         intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
	         intent.putExtra(Intent.EXTRA_TEXT, "Hongkong Red Cross blood center invites you to download APP, Download address:  https://play.google.com/store/apps/details?id=com.webdesign688.redcross");
	         startActivity(Intent.createChooser(intent, "To share"));

		}
	}
    private void initUi() {
		// TODO Auto-generated method stub

		mCheckBox_En = (CheckBox) mView.findViewById(R.id.checkBox_language_en);
			mCheckBox_Cn = (CheckBox) mView.findViewById(R.id.checkBox_language_cn);
			listenCheckbox();
			SharedPreferences sp = getActivity().getSharedPreferences(getResources().getString(R.string.sp_name), 0);
			 isCn = sp.getBoolean(getResources().getString(R.string.sp_value), true);
			 if (isCn) {
				
				 mCheckBox_Cn.setChecked(true);
			}
			 else {
				mCheckBox_En.setChecked(true);
			}
			 
			 MySwitch mySwitch= (MySwitch) mView.findViewById(R.id.mySwitch1);
			 mySwitch.setChecked(true);
			
	
	}

	private void listenCheckbox() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		
		mCheckBox_Cn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (!isChecked) {
					mCheckBox_En.setChecked(true);
					mCheckBox_Cn.setChecked(false);
					isCn=false;
				}else{
					mCheckBox_En.setChecked(false);
					mCheckBox_Cn.setChecked(true);
					isCn=true;

				}
				
			}
		});
           mCheckBox_En.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (!isChecked) {
					mCheckBox_En.setChecked(false);
					mCheckBox_Cn.setChecked(true);
					isCn=true;
					
				}else{
					mCheckBox_En.setChecked(true);
					mCheckBox_Cn.setChecked(false);
					isCn=false;

				}
			}
		});
		
	
	}

      
    @Override  
    public void onResume() {  
        super.onResume();  
    }  

    @Override
    public void onPause() {
    	super.onPause();
    }
    @Override
    public void onDestroy() {

    	super.onDestroy();
    }
 
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_setting_save:

			// TODO Auto-generated method stub
			String name = mEditText_name.getText().toString();
			String date= mEditText_date.getText().toString();
					   SharedPreferences sp = getActivity().getSharedPreferences(getResources().getString(R.string.sp_name), 0);
					   Editor edit = sp.edit();
					   if (name!=null) {
						   edit.putString("name", name);
					}
					   if (date!=null) {
						   edit.putString("date", date);
					}
					  
					   edit.putBoolean(getResources().getString(R.string.sp_value), isCn)
					   .putBoolean("man", mRadio_Male.isChecked()).putBoolean("women", mRadio_Female.isChecked())
					   .putBoolean("a", mRadio_A.isChecked())
					   .putBoolean("b", mRadio_B.isChecked())
					   .putBoolean("ab", mRadio_AB.isChecked())
					   .putBoolean("o", mRadio_O.isChecked())
					   .putBoolean("isedit", true);
				
					   edit.commit();
					   startActivity(new Intent(getActivity(),MainActivity.class));
						 if (isCn) {
								
							   Toast.makeText(getActivity(), "成功 ", Toast.LENGTH_SHORT).show();
						}else{
							   Toast.makeText(getActivity(), "success ", Toast.LENGTH_SHORT).show();

						}

					   getActivity().finish();

		
			break;

		default:
			break;
		}
	}  
    
    
}
