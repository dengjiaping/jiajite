package com.project.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.bean.Info;
import com.project.bean.LightInfo;
import com.project.bean.Task;
import com.project.jaijite.R;
import com.project.service.MainService;

public class LedAdapter extends BaseAdapter
{
	private List<LightInfo> lightInfos = null;
	private Context context = null;

	public LedAdapter( Context context, List<LightInfo> lightInfos)
	{
		this.context = context;
		this.lightInfos = lightInfos;
	}

	@Override
	public int getCount() 
	{
		return lightInfos == null?0:lightInfos.size();
	}

	@Override
	public Object getItem(int position)
	{
		return position;
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View contentView, ViewGroup viewGroup) 
	{
		Viewholder holder = null;
		if (contentView == null)
		{
			holder = new Viewholder();
			contentView = LayoutInflater.from(context).inflate(R.layout.item_led_main, null);
			holder.ledNameTv = (TextView) contentView.findViewById(R.id.ledNameTv);
			holder.deleteBtn = contentView.findViewById(R.id.deleteBtn);
			holder.ledLightTv=(TextView) contentView.findViewById(R.id.ledLightTv);
			holder.timingOpenLedTv=(TextView) contentView.findViewById(R.id.timingOpenLedTv);
			holder.timingCloseLedTv=(TextView) contentView.findViewById(R.id.timingCloseLedTv);
			holder.delayCloseLedTv=(TextView) contentView.findViewById(R.id.delayCloseLedTv);
			holder.ledTempTv=(TextView) contentView.findViewById(R.id.ledTempTv);
			holder.ledPowerBtn =(ImageView) contentView.findViewById(R.id.ledPowerBtn);
			holder.detailslayout = contentView.findViewById(R.id.detailslayout);
			contentView.setTag(holder);
		}
		else
		{
			holder = (Viewholder) contentView.getTag();
		}

		LightInfo light = lightInfos.get(position);
		holder.ledNameTv.setText(light.getName());
		holder.ledLightTv.setText("亮度："+light.getLight_level()+"%");
		holder.ledTempTv.setText("色温："+light.getColor_temp()+"k");

		String state = light.getTime_on();
		holder.timingOpenLedTv.setText("定时开灯："+(state.equals("00:00")?"关":"开"));
		state = light.getTime_off();
		holder.timingCloseLedTv.setText("定时关灯："+(state.equals("00:00")?"关":"开"));
		state = light.getDelay();
		holder.delayCloseLedTv.setText("延时关灯："+(state.equals("00:00")?"关":"开"));
		holder.position = position;
		holder.detailslayout.setTag(position);
		holder.ledPowerBtn.setOnClickListener(new ledPowerBtnListener(light));
		holder.ledPowerBtn.setTag(position);
		if (light.getLed_state()== Info.LED_ON) 
		{
			holder.ledPowerBtn.setImageResource(R.drawable.led_opend);
		}
		else
		{
			holder.ledPowerBtn.setImageResource(R.drawable.led_offed);
		}
		return contentView;
	}

	private class ledPowerBtnListener implements OnClickListener
	{
		LightInfo light = null;
		public ledPowerBtnListener(LightInfo light)
		{
			this.light = light;
		}
		@Override
		public void onClick(View arg0)
		{
			Task task = new Task(context);
			if (light.getLed_state() == Info.LED_ON) 
			{
				task.setId(light.getId());
				task.setFunction(Info.TURN_OFF);
				task.setAttribute(Info.LIGHT_PER+"");
			}
			else 
			{
				task.setId(light.getId());
				task.setFunction(Info.TURN_ON);
				task.setAttribute(Info.LIGHT_PER+"");
			}
		
			MainService.newTask(task, true);
		}
		
	}
	class Viewholder
	{
		ImageView ledPowerBtn;
		TextView ledNameTv;
		View deleteBtn;
		int position;
		View detailslayout;
		TextView ledLightTv;
		TextView ledTempTv;
		TextView timingOpenLedTv;
		TextView timingCloseLedTv;
		TextView delayCloseLedTv; 

	}
}
