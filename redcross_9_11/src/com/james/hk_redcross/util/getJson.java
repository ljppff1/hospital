package com.james.hk_redcross.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONException;
import org.json.JSONObject;

import com.james.hk_redcross.IndexActivity;

import android.os.AsyncTask;

/*
 * 
 *
     class  DownLoadAsy extends  AsyncTask<String, Void, String>{
    @Override
  protected void onPostExecute(String result) {
  	super.onPostExecute(result);
  	
  	
  }
	@Override
	protected String doInBackground(String... params) {
		              String string = params[0];
		return getJson.getData(string);
	}
	
}

super.onPostExecute(result);
        	JSONObject   gen=null; 
        	    try {
        	    	 gen=new JSONObject(result);
        	    	 String    code= gen.getString("code");
        	    	 if ("1".equals(code)) {

        	    		new AlertInfoDialog(IndexActivity.this).show();
					}
        	    	 else {
        	    		 JSONObject data = gen.getJSONObject("data");
        	    		 String string_StockA = data.getString("StockA");
        	    		 String string_StockB = data.getString("StockB");
        	    		 String string_StockO = data.getString("StockO");
        	    		 String string_StockAB = data.getString("StockAB");
						
					}
        	    	 
				}
        	    catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	







*/

public class getJson {
	private getJson(){};
	public static String getData(String str){
		InputStream is = null;
		ByteArrayBuffer arrayBuffer = new ByteArrayBuffer(3000);
		try
		{
			URL url = new URL(str);
			URLConnection openConnection = url.openConnection();
			is = openConnection.getInputStream();
			int len = 0;
			byte[] buffer = new byte[1024];
			while (-1 != (len = is.read(buffer)))
			{
				arrayBuffer.append(buffer, 0, len);
			}
		} catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			if (is != null)
			{
				try
				{
					is.close();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return new String(arrayBuffer.toByteArray(), 0,
				arrayBuffer.length());
	
	
	
	}
}
