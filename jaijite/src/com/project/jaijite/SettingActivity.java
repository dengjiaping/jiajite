package com.project.jaijite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.project.jiajiteInterface.InterFace;
import com.project.service.MainService;

public class SettingActivity  extends BaseActivity implements InterFace, OnClickListener, OnItemClickListener
{
	private ListView settingListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_fragment);
		MainService.addActivity(this);
		
		init();
	}
	
	@Override
	public void init()
	{
		settingListView = (ListView)findViewById(R.id.settingListView);
		String[] names = getResources().getStringArray(R.array.settingNames);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_setting,
				R.id.settingName, names);
		settingListView.setAdapter(adapter);
		settingListView.setOnItemClickListener(this);
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
	@Override
	public void onClick(View view) {

		switch (view.getId()) {



		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		switch (arg2) {
		case 0:
			startActivity(new Intent(SettingActivity.this, ManualActivity.class));
			break;
		case 1:
			startActivity(new Intent(SettingActivity.this, PasswordSettingActivity.class));
			break;
		case 2:

			break;
		case 3:
			startActivity(new Intent(SettingActivity.this, MacSettingActivity.class));
			break;
		case 4:
			startActivity(new Intent(SettingActivity.this, AboutUsActivity.class));
			break;

		default:
			break;
		}
	}
}

