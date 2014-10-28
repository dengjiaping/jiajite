package com.project.jaijite;

import com.project.jiajiteInterface.InterFace;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AppliancesFragment  extends Fragment implements InterFace
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		return inflater.inflate(R.layout.appliances_ragment, container, false);
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
