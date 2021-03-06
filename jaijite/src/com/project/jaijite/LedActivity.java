package com.project.jaijite;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.project.adapter.LedAdapter;
import com.project.bean.Info;
import com.project.bean.LightInfo;
import com.project.bean.Task;
import com.project.db.DataInfoDB;
import com.project.jiajiteInterface.InterFace;
import com.project.service.MainService;

public class LedActivity extends BaseActivity implements InterFace
{
	private ListView led_list_iew = null;
	private LedAdapter ledAdapter = null;
	private List<LightInfo> lightInfos = new ArrayList<LightInfo>();;
	private DataInfoDB dataDB = null;
	private Button add_btn = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.led_fragment);
		MainService.addActivity(this);
		
		dataDB = new DataInfoDB(this);

		init();
		updateUI();
	}
	
	@Override
	public void init() 
	{
		led_list_iew = (ListView) findViewById(R.id.led_list_iew);
		add_btn = (Button) findViewById(R.id.add_btn);
		add_btn.setOnClickListener(new addBtnClick());
	
		led_list_iew.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) 
			{
				Intent intent = new Intent(LedActivity.this, LedControlActivity.class);
				Bundle bundle=new Bundle();
				bundle.putSerializable("lights", lightInfos.get(position));
				intent.putExtras(bundle);
				startActivity(intent);
				
			}
		});
	}

	private class addBtnClick implements OnClickListener
	{
		@Override
		public void onClick(View arg0)
		{
			startActivityForResult((new Intent(LedActivity.this, LedEditActivity.class)),1);
		}
		
	}
	@Override
	public void refresh(Object... params) 
	{
		Task task = (Task)params[0];
		switch (task.getFunction()) 
		{
		case Info.TURN_ON:
			dataDB.UpdateLedState(Info.LED_ON, task.getId());
			dataDB.UpdateNightLampsState(Info.LED_OFF, task.getId());
			
			break;
		case Info.TURN_OFF:
			dataDB.UpdateLedState(Info.LED_OFF, task.getId());
			break;

		default:
			break;
		}
		
		updateUI();
		
	}
	
	@Override
	protected void onResume()
	{
		updateUI();
		super.onResume();
	}
	
	private void updateUI()
	{
		dataDB.getAllLights(lightInfos);
		if (ledAdapter != null) 
		{
			ledAdapter.notifyDataSetChanged();
		}
		else 
		{
			ledAdapter = new LedAdapter(this, lightInfos);
			led_list_iew.setAdapter(ledAdapter);
		}
		
	}
	
	@Override
	protected void onDestroy()
	{
		MainService.removeActivity(this);
		super.onDestroy();
	}
}
