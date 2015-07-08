package com.james.hk_redcross;

import java.io.ObjectOutputStream.PutField;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.james.hk_redcross.util.MySwitch;
import com.webdesign688.redcross.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Setting extends FragmentActivity {

//	private CheckBox mCheckBox_En;
//  private CheckBox mCheckBox_Cn;
//	private boolean isCn;
//	private RadioButton mRadio_Female;
//	private RadioButton mRadio_Male;
	private MainFragment mainFragment;
//	private EditText mEditText_name;
//	private EditText mEditText_date;
//	private RadioButton mRadio_A;
//	private RadioButton mRadio_B;
//	private RadioButton mRadio_AB;
//	private RadioButton mRadio_O;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		
		  
		 if (savedInstanceState == null) {  
		      // Add the fragment on initial activity setup  
		      mainFragment = new MainFragment();  
		      getSupportFragmentManager()  
		      .beginTransaction()  
		      .add(android.R.id.content, mainFragment)  
		      .commit();  
		 } else {  
		    // Or set the fragment from restored state info  
		     mainFragment = (MainFragment) getSupportFragmentManager()  
		     .findFragmentById(android.R.id.content);  
		 }  
		    
/*		  try {  
		      PackageInfo info = getPackageManager().getPackageInfo(  
		              "com.james.hk_redcross",   
		              PackageManager.GET_SIGNATURES);  
		      for (Signature signature : info.signatures) {  
		          MessageDigest md = MessageDigest.getInstance("SHA");  
		          md.update(signature.toByteArray());  
		          Log.i("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));  
		          }  
		  } catch (NameNotFoundException e) {  

		  } catch (NoSuchAlgorithmException e) {  

		  }  
*/
	}

	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode==KeyEvent.KEYCODE_BACK) {
			startActivity(new Intent(this,MainActivity.class));
			finish();
		}
    	return super.onKeyDown(keyCode, event);
    }

}
