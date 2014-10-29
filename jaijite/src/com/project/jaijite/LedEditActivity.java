package com.project.jaijite;

import java.util.ArrayList;
import java.util.List;

import com.project.bean.LightInfo;
import com.project.db.DataInfoDB;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;

public class LedEditActivity extends Activity implements OnItemClickListener, OnClickListener {

    private Context context;
    List<String> lightname=new ArrayList<String>();
    List<String> lights=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_led_edit);
    	context = this;
    	getLeds();
    	initUI();
    }
    
    private void initUI(){
    	findViewById(R.id.cancelBtn).setOnClickListener(this);
    	findViewById(R.id.commitBtn).setOnClickListener(this);
    	ListView listView = (ListView) findViewById(R.id.ledListView);
    	listView.setAdapter(new LedNameAdapter());
    	listView.setOnItemClickListener(this);
    }

    private void getLeds() {
    	String[] ledNames = getResources().getStringArray(R.array.ledNameArray);
        for (int j = 0; j < ledNames.length; j++) {
			lights.add(ledNames[j]);
		}
        DataInfoDB ls = new DataInfoDB(context);
        List<LightInfo> l= ls.getAllLights();
        for (int i = 0; i < l.size(); i++) {
         	lights.remove(l.get(i).getName());
		}
        
    }

    class LedNameAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return lights.size();
        }

        @Override
        public Object getItem(int position) {
            return lights.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View contentView, ViewGroup parent) {
        	ViewHolder viewHolder=null;
        	if (contentView == null) {
        		viewHolder=new ViewHolder();
        		LayoutInflater layout = LayoutInflater.from(context);
        		contentView = layout.inflate(R.layout.item_led_edit, null);
        		viewHolder.textView= (TextView) contentView.findViewById(R.id.ledNameTv);
        		viewHolder.checkBtn= (CheckBox) contentView.findViewById(R.id.ledCheckBox);
        		contentView.setTag(viewHolder);
			}else
				viewHolder=(ViewHolder)contentView.getTag();
        	viewHolder.textView.setText(lights.get(position));
        	viewHolder.checkBtn.setChecked(false);
        	final int p=position;
        	contentView.setOnClickListener(new convertViewListener(viewHolder));
            CheckBox ledCheckBox = (CheckBox) contentView.findViewById(R.id.ledCheckBox);
            ledCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
					// TODO Auto-generated method stub
					System.out.println(arg0.getText()+":"+arg1+":"+p);
					if(arg1)
						lightname.add(lights.get(p));
					else
						lightname.remove(lights.get(p));
					
					System.out.println(lightname.size());
				}
			});
            return contentView;
        }

    }

    private class convertViewListener implements OnClickListener
	{
		ViewHolder viewHolder = null;
		public convertViewListener(ViewHolder viewHolder)
		{
			this.viewHolder = viewHolder;
		}
		@Override
		public void onClick(View arg0)
		{
			if(viewHolder.checkBtn.isChecked())
			{
				viewHolder.checkBtn.setChecked(false);
			}
			else
			{
				viewHolder.checkBtn.setChecked(true);
			}
			
		}
		
	}
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
    	System.out.println(position);
    }

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		
		case R.id.cancelBtn:
			finish();
			break;
		
		case R.id.commitBtn:
			DataInfoDB ls=new DataInfoDB(LedEditActivity.this);
			for (int i = 0; i < lightname.size(); i++) {
				ls.Add(lightname.get(i));
			}
			Intent intent=new Intent(LedEditActivity.this, LedActivity.class);
			setResult(1, intent);
			finish();
			break;

		default:
			break;
		}
	}
	
	public class ViewHolder
    {
        private TextView textView;
        private CheckBox checkBtn;
    }
	
}
