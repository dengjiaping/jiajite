package com.project.jaijite;

import android.os.Bundle;

import com.project.jiajiteInterface.InterFace;
import com.project.service.MainService;

public class AppliancesActivity  extends BaseActivity implements InterFace
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.appliances_ragment);
		MainService.addActivity(this);
	}

	@Override
	public void init() 
	{
		
	}

	@Override
	public void refresh(Object... params) 
	{
		
	}
	
	@Override
	protected void onDestroy()
	{
		MainService.removeActivity(this);
		super.onDestroy();
	}
}
