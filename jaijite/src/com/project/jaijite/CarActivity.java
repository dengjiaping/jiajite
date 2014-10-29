package com.project.jaijite;

import android.os.Bundle;

import com.project.jiajiteInterface.InterFace;
import com.project.service.MainService;

public class CarActivity  extends BaseActivity implements InterFace
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.car_fragment);
		MainService.addActivity(this);
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
		MainService.removeActivity(this);
		super.onDestroy();
	}
}
