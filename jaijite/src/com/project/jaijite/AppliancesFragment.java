package com.project.jaijite;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AppliancesFragment  extends Fragment
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
}
