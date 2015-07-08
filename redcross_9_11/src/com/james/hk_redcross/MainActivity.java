package com.james.hk_redcross;

import com.webdesign688.redcross.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {

	private boolean hasPressedBack;
	private Handler  mHandler=new Handler();
	private boolean isCn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SharedPreferences sp = getSharedPreferences(getResources().getString(R.string.sp_name), MODE_PRIVATE);
		 isCn = sp.getBoolean(getResources().getString(R.string.sp_value), true);
		  ImageButton imageButton_1= (ImageButton) findViewById(R.id.ib_main_1);
		  ImageButton imageButton_2= (ImageButton) findViewById(R.id.ib_main_2);
		  ImageButton imageButton_3= (ImageButton) findViewById(R.id.ib_main_3);
		  ImageButton imageButton_4= (ImageButton) findViewById(R.id.ib_main_4);
		  ImageButton imageButton_5= (ImageButton) findViewById(R.id.ib_main_5);
		  ImageButton imageButton_6= (ImageButton) findViewById(R.id.ib_main_6);
		  ImageButton imageButton_7= (ImageButton) findViewById(R.id.ib_main_7);
		  ImageButton imageButton_8= (ImageButton) findViewById(R.id.ib_main_8);
		  ImageButton imageButton_9= (ImageButton) findViewById(R.id.ib_main_9);
		  ImageButton imageButton_10= (ImageButton) findViewById(R.id.ib_main_10);
		  ImageButton imageButton_11= (ImageButton) findViewById(R.id.ib_main_11);
		  ImageButton imageButton_12= (ImageButton) findViewById(R.id.ib_main_12);
		  ImageButton imageButton_13= (ImageButton) findViewById(R.id.ib_main_13);
		  ImageButton imageButton_14= (ImageButton) findViewById(R.id.ib_main_14);
		  ImageButton imageButton_15= (ImageButton) findViewById(R.id.ib_main_15);
		  ImageButton imageButton_16= (ImageButton) findViewById(R.id.ib_main_16);
		    if (isCn) {
				
			}
		    else {
				imageButton_1.setBackgroundResource(R.drawable.en_icon_calculator);
				imageButton_2.setBackgroundResource(R.drawable.en_icon_latestnews);
				imageButton_3.setBackgroundResource(R.drawable.en_icon_donationcriteria);
				imageButton_4.setBackgroundResource(R.drawable.en_icon_donationprocedure);
				imageButton_5.setBackgroundResource(R.drawable.en_icon_bloodgroup);
				imageButton_6.setBackgroundResource(R.drawable.en_icon_donation_appeal);
				imageButton_7.setBackgroundResource(R.drawable.en_icon_apheresis_donation);
				imageButton_8.setBackgroundResource(R.drawable.en_icon_groupdonation);
				imageButton_9.setBackgroundResource(R.drawable.en_icon_donor_centres);
				imageButton_10.setBackgroundResource(R.drawable.en_icon_mobiledonation);
				imageButton_11.setBackgroundResource(R.drawable.en_icon_bone_donation);
				imageButton_12.setBackgroundResource(R.drawable.en_icon_aboutus);
				imageButton_13.setBackgroundResource(R.drawable.en_icon_faqa);
				imageButton_14.setBackgroundResource(R.drawable.en_icon_downloa);
				imageButton_15.setBackgroundResource(R.drawable.en_icon_contact);
				imageButton_16.setBackgroundResource(R.drawable.en_icon_settins);
			}
		  
		  
	}
	public void btn_calculator(View v) {
    startActivity(new Intent(this,Calculator.class));
	}
	public void btn_latestnew(View v) {
		 startActivity(new Intent(this,LatestNew.class));
	}
	public void btn_condition(View v) {
		 startActivity(new Intent(this,Condition.class));
	}
	public void btn_process(View v) {
		 startActivity(new Intent(this,Process.class));
	}
	public void btn_bloodmatch(View v) {
		 startActivity(new Intent(this,BloodMatch.class));
	}
	public void btn_sound(View v) {
		 startActivity(new Intent(this,Sound.class));
	}
	public void btn_chengfenjuanxue(View v) {
		 startActivity(new Intent(this,ChengFen.class));
	}
	public void btn_friends(View v) {
		 startActivity(new Intent(this,Friends.class));
	}
	public void btn_home(View v) {
		 startActivity(new Intent(this,Home.class));
	}
	public void btn_station(View v) {
		 startActivity(new Intent(this,Station.class));
	}
	public  void btn_introduce(View v) {
		 startActivity(new Intent(this,Introduce.class));
	}
	public void btn_question(View v) {
		 startActivity(new Intent(this,FAQ.class));
	}
	public void btn_donate(View v) {
		 startActivity(new Intent(this,Donate.class));
	}
	public void btn_downloadtable(View v) {
		 startActivity(new Intent(this,DownLoad.class));
	}
	public void btn_contactus(View v) {
		 startActivity(new Intent(this,ContactUs.class));
	}
	public void btn_setting(View v) {
		 startActivity(new Intent(this,Setting.class));
		 finish();
	}
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:  
			if (!hasPressedBack)
            {
                hasPressedBack = true;
                if (isCn) {
                	Toast.makeText(MainActivity.this, "請再次按返回鍵退出", Toast.LENGTH_SHORT).show();
					
				} else {
					Toast.makeText(MainActivity.this, "Press the back button once again and quit", Toast.LENGTH_SHORT).show();
				}
                mHandler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {   
                        hasPressedBack = false;
                    }
                }, 3000);  
                return true;
            }
            
			break;

		default:
			break;
		}
    	return super.onKeyDown(keyCode, event);
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
