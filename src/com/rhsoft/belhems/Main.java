package com.rhsoft.belhems;



import java.io.IOException;
import java.net.InetAddress;


import android.app.Activity;


import android.os.Bundle;
import android.os.StrictMode;

import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity implements OnClickListener {
	
	private Button temprature;
	private Button motion;
	private Button motionLog;
	private TextView data;
	private Button exit;
	Client client;
	Thread t;
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        
        data = (TextView) findViewById(R.id.textView1);
        
        temprature = (Button) findViewById(R.id.button1);
        temprature.setOnClickListener(this);
        
        motion = (Button) findViewById(R.id.button2);
        motion.setOnClickListener(this);
        
        motionLog = (Button) findViewById(R.id.button3);
        motionLog.setOnClickListener(this);
       
        
        exit = (Button) findViewById(R.id.button7);
        exit.setOnClickListener(this);
        
        
        
        connect();
       
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if (v == exit) {
			connect();
			try {
				client.send("exit");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			t.interrupt();
			android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
		}
		else if (v == temprature){
			connect();
			try {
				client.send("temp");
				String response = client.recv();
				Log.v("HAZ","Client received: " + response);
				data.setText(response);
				data.refreshDrawableState();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (v == motion){
			connect();
			try {
				client.send("motion");
				String response = client.recv();
				Log.v("HAZ","Client received: " + response);
				data.setText(response);
				data.refreshDrawableState();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if (v == motionLog){
			connect();
			try {
				client.send("motion log");
				String response = client.recv();
				Log.v("HAZ","Client received: " + response);
				data.setText(response);
				data.refreshDrawableState();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private void connect() {
		t = new Thread(){
        	public void run() {
        		try {
        	         InetAddress host = InetAddress.getByName("192.168.0.101");
        	         client = new Client(host, 5555);

        	         //client.send("Hello server.\n");
        	         //String response = client.recv();
        	         //Log.v("HAZ","Client received: " + response);
        	         runOnUiThread(new Runnable() {

    					@Override
    					public void run() {
    						// TODO Auto-generated method stub
    	        	         Toast.makeText(getApplicationContext(), "Done",
    	        	        		 Toast.LENGTH_LONG).show();
    					}
        	        	 
        	         });

        	        
        	      }
        	      catch (IOException e) {
        	    	  runOnUiThread(new Runnable() {
    					
    					@Override
    					public void run() {
    						// TODO Auto-generated method stub
    	        	         Toast.makeText(getApplicationContext(), "Server is not responding",
    	        	        		 Toast.LENGTH_LONG).show();
    					}
    				});
        	    	  Log.e("HAZ","Caught Exception: " + e.toString());
        	    	  android.os.Process.killProcess(android.os.Process.myPid());
                      System.exit(1);		        
        	         
        	      }
        		
        	}
        };
        t.start();
	}

}