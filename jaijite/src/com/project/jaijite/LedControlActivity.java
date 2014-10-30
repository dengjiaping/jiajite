package com.project.jaijite;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.project.bean.Info;
import com.project.bean.LightInfo;
import com.project.bean.Task;
import com.project.db.DataInfoDB;
import com.project.jiajiteInterface.InterFace;
import com.project.service.MainService;
import com.project.util.ToastUtil;

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
	private DataInfoDB dataDb = null;
	private LightInfo lightInfo = null;
	List<View> ledgroups = new ArrayList<View>();
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_led_control);
		MainService.addActivity(this);

		dataDb = new DataInfoDB(this);
		lightInfo = (LightInfo) getIntent().getSerializableExtra("lights");

		init();
		groupInit(group1Tv);
	}

	public void groupInit(View view) 
	{
		ledgroups.add(view);
		view.setTag(1);
		((TextView) view).setTextColor(Color.RED);
	}

	@Override
	public void init()
	{
		back_btn = (Button) findViewById(R.id.back_btn);
		back_btn.setOnClickListener(this);
		title_txt = (TextView) findViewById(R.id.title_txt);
		add_btn = (FrameLayout) findViewById(R.id.add_btn);
		add_btn.setOnClickListener(this);
		night_line_btn = (TextView) findViewById(R.id.night_line_btn);
		night_line_btn.setOnClickListener(this);
		led_turn_btn = (TextView) findViewById(R.id.led_turn_btn);
		led_turn_btn.setOnClickListener(this);
		brightnessTv = (TextView) findViewById(R.id.brightnessTv);
		sblight = (SeekBar) findViewById(R.id.sblight);
		colorTempTv = (TextView) findViewById(R.id.colorTempTv);
		delayCloseTv = (TextView) findViewById(R.id.delayCloseTv);
		delaySet = (FrameLayout) findViewById(R.id.delaySet);
		delaySet.setOnClickListener(this);
		timingopenset = (FrameLayout) findViewById(R.id.timingopenset);
		timingopenset.setOnClickListener(this);
		timingcloseset = (FrameLayout) findViewById(R.id.timingcloseset);
		timingcloseset.setOnClickListener(this);
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


	private void updateUI()
	{
		dataDb.getLightInfo(lightInfo, lightInfo.getId());

		delayCloseTv.setText("延时关灯"+lightInfo.getDelay()+"分钟");
		timingOpenTv.setText("定时开灯"+lightInfo.getTime_on());
		timingCloseTv.setText("定时关灯"+lightInfo.getTime_off());

		flash1tv.setTextColor(lightInfo.getJump() == Info.OFF?Color.WHITE:Color.RED);
		flash2tv.setTextColor(lightInfo.getWater() == Info.OFF?Color.WHITE:Color.RED);
		flash3tv.setTextColor(lightInfo.getTouch() == Info.OFF?Color.WHITE:Color.RED);
		flash4tv.setTextColor(lightInfo.getGflash()== Info.OFF?Color.WHITE:Color.RED);
		flash5tv.setTextColor(lightInfo.getBflash() == Info.OFF?Color.WHITE:Color.RED);
		flash6tv.setTextColor(lightInfo.getWarming() == Info.OFF?Color.WHITE:Color.RED);

		if (lightInfo.getLed_state() == Info.LED_ON)
		{
			led_turn_btn.setTextColor(Color.RED);
		}
		else 
		{
			led_turn_btn.setTextColor(Color.WHITE);
		}

		if (lightInfo.getNight_lamp_state() == Info.LED_ON)
		{
			night_line_btn.setTextColor(Color.RED);
		}
		else 
		{
			night_line_btn.setTextColor(Color.WHITE);
		}

	}

	@Override
	public void refresh(Object... params)
	{
		Task task = (Task)params[0];
		switch (task.getFunction()) 
		{
		case Info.TURN_ON:
			dataDb.UpdateLedState(Info.LED_ON, task.getId());
			dataDb.UpdateNightLampsState(Info.LED_OFF, task.getId());
			break;
		case Info.TURN_OFF:
			dataDb.UpdateLedState(Info.LED_OFF, task.getId());
			break;

		case Info.NIGHT_LAMPSS:
			if (task.getAttribute().equals(String.valueOf(Info.ON)))
			{
				dataDb.UpdateLedState(Info.LED_OFF, task.getId());
				dataDb.UpdateNightLampsState(Info.ON, task.getId());
			}
			else 
			{
				dataDb.UpdateNightLampsState(Info.OFF, task.getId());
			}
			break;
		case Info.FLASH:
			int flashID = Integer.valueOf(task.getAttribute());
			updateFlashData(flashID);
			break;
		default:
			break;
		}

		updateUI();
	}

	private void updateFlashData(int flashID)
	{
		int ledID  = lightInfo.getId();
		switch (flashID) 
		{
		case 0:
			dataDb.setFlashOff(ledID);
			break;
		case 1:
			dataDb.setJumpOn(ledID);
			break;
		case 2:
			dataDb.setWaterOn(ledID);
			break;
		case 3:
			dataDb.setTouchOn(ledID);
			break;
		case 4:
			dataDb.setGflashOn(ledID);
			break;
		case 5:
			dataDb.setBflashOff(ledID);
			break;
		case 6:
			dataDb.setWarmingOn(ledID);
			break;
		default:
			break;
		}
	}
	@Override
	protected void onDestroy()
	{
		MainService.removeActivity(this);
		super.onDestroy();
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1)
	{
		// TODO Auto-generated method stub
		return false;
	}

	private void ledTask()
	{
		Task task = new Task(LedControlActivity.this);
		if (dataDb.getLedState(lightInfo.getId()) == Info.LED_ON)
		{
			task.setId(lightInfo.getId());
			task.setFunction(Info.TURN_OFF);
			task.setAttribute(Info.LIGHT_PER+"");
		}
		else
		{
			task.setId(lightInfo.getId());
			task.setFunction(Info.TURN_ON);
			task.setAttribute(Info.LIGHT_PER+"");
		}
		MainService.newTask(task, true);
	}

	private void nightLampsTask()
	{
		Task task = new Task(LedControlActivity.this);
		if (dataDb.getNightLampState(lightInfo.getId()) == Info.LED_ON)
		{
			task.setId(lightInfo.getId());
			task.setFunction(Info.NIGHT_LAMPSS);
			task.setAttribute(Info.OFF+"");
		}
		else
		{
			task.setId(lightInfo.getId());
			task.setFunction(Info.NIGHT_LAMPSS);
			task.setAttribute(Info.ON+"");
		}
		MainService.newTask(task, true);
	}

	public void groupEdit(View view) {

		int[] c = { Color.RED, Color.WHITE };
		int[] idxs = { 1, 0 };
		int idx = 0;
		if (view.getTag() == null || view.getTag().equals(0)) {
			ledgroups.add(view);
			idx = 0;
		} else {
			if (ledgroups.size() > 1) {
				ledgroups.remove(view);
				idx = 1;
			} else {
				ToastUtil.showShortToast("至少有一组选中");
				return;
			}

		}
		System.out.println(idx);
		view.setTag(idxs[idx]);
		((TextView) view).setTextColor(c[idx]);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId()) 
		{
		case R.id.back_btn:
			finish();
			break;
		case R.id.led_turn_btn:
			ledTask();
			break;
		case R.id.night_line_btn:
			nightLampsTask();
			break;
		case R.id.delaySet:
			Intent intent1 = new Intent(LedControlActivity.this, SetSleepTimeActivity.class);
			intent1.putExtra("ledID", lightInfo.getId());
			startActivity(intent1);
			break;
		case R.id.timingopenset:
			Intent intent2 = new Intent(LedControlActivity.this, SetTimingOpenActivity.class);
			intent2.putExtra("ledID", lightInfo.getId());
			startActivity(intent2);
			break;
		case R.id.timingcloseset:
			Intent intent3 = new Intent(LedControlActivity.this, SetTimingCloseActivity.class);
			intent3.putExtra("ledID", lightInfo.getId());
			startActivity(intent3);
			break;
		case R.id.falsh1Tv:
			flashEdit(1);
			break;
		case R.id.falsh2Tv:
			flashEdit(2);
			break;
		case R.id.falsh3Tv:
			flashEdit(3);
			break;
		case R.id.falsh4Tv:
			flashEdit(4);
			break;
		case R.id.falsh5Tv:
			flashEdit(5);
			break;
		case R.id.falsh6Tv:
			flashEdit(6);
			break;
		case R.id.group1Tv:
		case R.id.group2Tv:
		case R.id.group3Tv:
		case R.id.group4Tv:
			groupEdit(v);
			break;

		default:
			break;
		}

	}

	private void flashEdit(int flashID)
	{
		Task task = new Task(LedControlActivity.this);
		switch (flashID) 
		{
		case 1:
			if (lightInfo.getJump() == Info.ON) 
			{
				task.setId(lightInfo.getId());
				task.setFunction(Info.FLASH);
				task.setAttribute("0000");
			}
			else
			{
				task.setId(lightInfo.getId());
				task.setFunction(Info.FLASH);
				task.setAttribute(flashID+"");
			}
			break;
		case 2:
			if (lightInfo.getWater() == Info.ON) 
			{
				task.setId(lightInfo.getId());
				task.setFunction(Info.FLASH);
				task.setAttribute("0000");
			}
			else
			{
				task.setId(lightInfo.getId());
				task.setFunction(Info.FLASH);
				task.setAttribute(flashID+"");
			}
			break;
		case 3:
			if (lightInfo.getTouch() == Info.ON) 
			{
				task.setId(lightInfo.getId());
				task.setFunction(Info.FLASH);
				task.setAttribute("0000");
			}
			else
			{
				task.setId(lightInfo.getId());
				task.setFunction(Info.FLASH);
				task.setAttribute(flashID+"");
			}
			break;
		case 4:
			if (lightInfo.getGflash() == Info.ON) 
			{
				task.setId(lightInfo.getId());
				task.setFunction(Info.FLASH);
				task.setAttribute("0000");
			}
			else
			{
				task.setId(lightInfo.getId());
				task.setFunction(Info.FLASH);
				task.setAttribute(flashID+"");
			}
			break;
		case 5:
			if (lightInfo.getBflash() == Info.ON) 
			{
				task.setId(lightInfo.getId());
				task.setFunction(Info.FLASH);
				task.setAttribute("0000");
			}
			else
			{
				task.setId(lightInfo.getId());
				task.setFunction(Info.FLASH);
				task.setAttribute(flashID+"");
			}
			break;
		case 6:
			if (lightInfo.getWarming() == Info.ON) 
			{
				task.setId(lightInfo.getId());
				task.setFunction(Info.FLASH);
				task.setAttribute("0000");
			}
			else
			{
				task.setId(lightInfo.getId());
				task.setFunction(Info.FLASH);
				task.setAttribute(flashID+"");
			}
			break;
		default:
			break;
		}
		MainService.newTask(task, true);
	}

	@Override
	protected void onResume() 
	{
		super.onResume();
		updateUI();

	}

}
