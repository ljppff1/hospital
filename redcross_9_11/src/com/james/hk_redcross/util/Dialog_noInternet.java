package com.james.hk_redcross.util;


import com.webdesign688.redcross.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Dialog_noInternet extends Dialog implements android.view.View.OnClickListener{



	private Context context;
	private CharSequence text;
	private TextView mTextView_Text;
	private TextView mTextView_Title;
	private Button mButton_Negetive;
	private Button mButton_Positive;
	private android.view.View.OnClickListener mClickListener;

	public Dialog_noInternet(Context context) {
		super(context);
		
		this.context=context;
		
	}
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setTitle("警告");
	//setBlurEffect();
	init();
}
	private void setBlurEffect() {
		Window window = getWindow();
		WindowManager.LayoutParams lp= window.getAttributes();
	    lp.dimAmount=0.8f;
	    window.setAttributes(lp);
	    window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
	    
}
	
	private void init() {
		setContentView(R.layout.dialog_nointernet);
		mTextView_Title = (TextView) findViewById(R.id.tv_title);
		
		
	   
	   mButton_Negetive = (Button) findViewById(R.id.btn_negetive);
	   mButton_Negetive.setOnClickListener(this);
		
	
}
	public void setOnclickListener(android.view.View.OnClickListener  clicklistener){
		  this.mClickListener=clicklistener;
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_negetive:
			this.dismiss();
			break;

		default:
			break;
		}
	}

}
