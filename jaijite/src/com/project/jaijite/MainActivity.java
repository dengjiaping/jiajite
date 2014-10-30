package com.project.jaijite;

import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.project.db.DataInfoDB;
import com.project.service.MainService;
import com.project.util.PreferencesBase;

@SuppressWarnings("deprecation")
public class MainActivity extends ActivityGroup 
{
	private RadioGroup rg_main_btns = null;
	private Intent service = null;
	private static LocalActivityManager manager;
	private static FrameLayout container;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initUI();
		//start server
		service = new Intent(this, MainService.class);
		startService(service);
		
		ifFirstEnter();
		showPage(LedActivity.class); 
	}

	private void ifFirstEnter()
    {
    	String isFirstEnter = PreferencesBase.getStringByTargetKey(PreferencesBase.isFirsEnter);
    	if (isFirstEnter.equals(""))
		{
    		DataInfoDB dataInfo = new DataInfoDB(this);
    		if (dataInfo.isDataEmpty()) 
    		{
    			dataInfo.Add("大厅灯");
        		dataInfo.Add("厕所灯");
        		dataInfo.Add("餐厅灯");
        		dataInfo.Add("卧室灯");
        		dataInfo.Add("厨房灯");
			}
    		
    		Intent intent = new Intent(MainActivity.this, MacSettingActivity.class);
    		MainActivity.this.startActivity(intent);
		}
    }
	
	@SuppressWarnings("deprecation")
	private void initUI()
	{
		manager = getLocalActivityManager();
		container = (FrameLayout) findViewById(R.id.container);
		
		rg_main_btns =  (RadioGroup) findViewById(R.id.rg_main_btns);
		rg_main_btns.setOnCheckedChangeListener(new OnCheckedChangeListener() 
		{

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId)
			{
				switch (checkedId) {
				case R.id.rd_led:
					showPage(LedActivity.class); 
					break;
				case R.id.rd_appliances:
					showPage(AppliancesActivity.class); 
					break;
				case R.id.rd_security:
					showPage(SecurityActivity.class); 
					break;
				case R.id.rd_car:
					showPage(CarActivity.class); 
					break;
				case R.id.rd_setting:
					showPage(SettingActivity.class); 
					break;

				default:
					break;
				}
			}
		});
	}

	@SuppressWarnings("deprecation")
	public void showPage(Class<?> cls)
	{
		container.removeAllViews();
		manager.removeAllActivities();
		System.gc(); 
		// add page
		View view = manager.startActivity(cls.getName(), new Intent(this, cls)
		.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
		container.addView(view);
	}
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		stopService(service);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK) 
		{
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
