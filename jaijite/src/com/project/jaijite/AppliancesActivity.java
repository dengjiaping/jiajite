package com.project.jaijite;

import com.project.jiajiteInterface.InterFace;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AppliancesActivity  extends Activity implements InterFace
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.appliances_ragment);
	}
	
	@Override
	public void onDestroy() 
	{
		super.onDestroy();
	}

	@Override
	public void init() 
	{
		
	}

	@Override
	public void refresh(Object... params) 
	{
		
	}
}
