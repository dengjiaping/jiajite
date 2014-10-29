package com.project.jaijite;

import android.app.Activity;
import android.os.Bundle;

import com.project.jiajiteInterface.InterFace;

public class LedActivity extends Activity implements InterFace
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.led_fragment);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Object... params) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
	}
}
