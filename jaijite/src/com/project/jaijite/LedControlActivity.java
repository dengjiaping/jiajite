package com.project.jaijite;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.project.jiajiteInterface.InterFace;
import com.project.service.MainService;

public class LedControlActivity extends BaseActivity implements InterFace,
OnClickListener, OnTouchListener 
{

	private Button back_btn = null;
	private TextView title_txt = null;
	private FrameLayout add_btn = null;
	private TextView led_turn_btn = null;
	private TextView night_line_btn = null;
	private TextView group1Tv = null;
	private TextView group2Tv = null;
	private TextView group3Tv = null;
	private TextView group4Tv = null;
	private TextView brightnessTv = null;
	private SeekBar sblight = null;
	private TextView colorTempTv = null;
	private TextView delayCloseTv;
	private FrameLayout delaySet, timingopenset, timingcloseset;
	private TextView timingOpenTv;
	private TextView timingCloseTv;
	private TextView flash1tv, flash2tv, flash3tv, flash4tv, flash5tv,
	flash6tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_led_control);
		MainService.addActivity(this);
		init();

	}

	@Override
	public void init()
	{
		back_btn = (Button) findViewById(R.id.back_btn);
		title_txt = (TextView) findViewById(R.id.title_txt);
		add_btn = (FrameLayout) findViewById(R.id.add_btn);
		night_line_btn = (TextView) findViewById(R.id.night_line_btn);
		led_turn_btn = (TextView) findViewById(R.id.led_turn_btn);
		brightnessTv = (TextView) findViewById(R.id.brightnessTv);
		sblight = (SeekBar) findViewById(R.id.sblight);
		colorTempTv = (TextView) findViewById(R.id.colorTempTv);
		delayCloseTv = (TextView) findViewById(R.id.delayCloseTv);
		delaySet = (FrameLayout) findViewById(R.id.delaySet);
		timingopenset = (FrameLayout) findViewById(R.id.timingopenset);
		timingcloseset = (FrameLayout) findViewById(R.id.timingcloseset);
		timingOpenTv = (TextView) findViewById(R.id.timingOpenTv);
		timingCloseTv = (TextView) findViewById(R.id.timingCloseTv);
		flash1tv = (TextView) findViewById(R.id.falsh1Tv);
		flash1tv.setOnClickListener(this);
		flash2tv = (TextView) findViewById(R.id.falsh2Tv);
		flash2tv.setOnClickListener(this);
		flash3tv = (TextView) findViewById(R.id.falsh3Tv);
		flash3tv.setOnClickListener(this);
		flash4tv = (TextView) findViewById(R.id.falsh4Tv);
		flash4tv.setOnClickListener(this);
		flash5tv = (TextView) findViewById(R.id.falsh5Tv);
		flash5tv.setOnClickListener(this);
		flash6tv = (TextView) findViewById(R.id.falsh6Tv);
		flash6tv.setOnClickListener(this);
		group1Tv = (TextView) findViewById(R.id.group1Tv);
		group1Tv.setOnClickListener(this);
		group2Tv = (TextView) findViewById(R.id.group2Tv);
		group2Tv.setOnClickListener(this);
		group3Tv = (TextView) findViewById(R.id.group3Tv);
		group3Tv.setOnClickListener(this);
		group4Tv = (TextView) findViewById(R.id.group4Tv);
		group4Tv.setOnClickListener(this);
	}

	@Override
	public void refresh(Object... params)
	{
		
	}
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		MainService.removeActivity(this);
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onClick(View arg0)
	{
		// TODO Auto-generated method stub
		
	}

}
