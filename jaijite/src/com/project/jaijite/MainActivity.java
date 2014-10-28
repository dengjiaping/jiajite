package com.project.jaijite;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity 
{
	private FragmentManager fm ;
	Fragment led_fragment,appliances_fragment,security_fragment,
	car_fragment,setting_fragment;
	private RadioGroup rg_main_btns = null;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fm = getSupportFragmentManager();

		led_fragment = new LedFragment();
		appliances_fragment = new AppliancesFragment();
		security_fragment = new SecurityFragment();
		car_fragment = new CarFragment();
		setting_fragment = new SettingFragment();
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(R.id.container, led_fragment).commit();
		initUI();
	}

	private void initUI()
	{
		rg_main_btns =  (RadioGroup) findViewById(R.id.rg_main_btns);
		rg_main_btns.setOnCheckedChangeListener(new OnCheckedChangeListener() 
		{

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId)
			{
				FragmentTransaction ft = fm.beginTransaction();
				switch (checkedId) {
				case R.id.rd_led:
					ft.replace(R.id.container,led_fragment );
					ft.commit();
					break;
				case R.id.rd_appliances:
					ft.replace(R.id.container,appliances_fragment );
					ft.commit();
					break;
				case R.id.rd_security:
					ft.replace(R.id.container,security_fragment );
					ft.commit();
					break;
				case R.id.rd_car:
					ft.replace(R.id.container,car_fragment );
					ft.commit();
					break;
				case R.id.rd_setting:
					ft.replace(R.id.container,setting_fragment );
					ft.commit();
					break;

				default:
					break;
				}
			}
		});
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
