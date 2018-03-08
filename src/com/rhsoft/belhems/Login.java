package com.rhsoft.belhems;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends Activity implements OnClickListener {
	
	@SuppressWarnings("unused")
	private TextView appName;
	@SuppressWarnings("unused")
	private TextView welcome;
	
	@SuppressWarnings("unused")
	private TextView password;
	
	private EditText passwordBox;
	private Button enter;
	
	public Client client;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        appName = (TextView) findViewById(R.id.textView1);
        welcome = (TextView) findViewById(R.id.textView2);
        
        password = (TextView) findViewById(R.id.textView4);
        
        
        passwordBox = (EditText) findViewById(R.id.editText2);
        
        enter = (Button) findViewById(R.id.button1);
        enter.setOnClickListener(this);
        
      
        
        
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == enter) {
			if(passwordBox.getText().toString().equals("edison")) {
				Intent i = new Intent(this, Main.class);
				 Bundle b = new Bundle();
  				 i.putExtras(b);
  				 startActivity(i);		        
 				}
			else {
				runOnUiThread(new Runnable() {
									
									@Override
									public void run() {
										// TODO Auto-generated method stub
					        	         Toast.makeText(getApplicationContext(), "Incorrect Password",
					        	        		 Toast.LENGTH_LONG).show();
									}
								});
			}
		}
		
	}
	
}
