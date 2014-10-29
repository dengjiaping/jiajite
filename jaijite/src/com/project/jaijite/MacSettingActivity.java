package com.project.jaijite;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.project.util.PreferencesBase;
import com.project.util.ToastUtil;

public class MacSettingActivity extends Activity
{
	private Button cancel_btn = null;
	private Button save_btn = null;
	private EditText mac_edit = null;
	private String isFirstEnter = "";
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mac_setting);
		isFirstEnter = PreferencesBase.getStringByTargetKey(PreferencesBase.isFirsEnter);
		initUI();
	}
	
	private void initUI()
	{
		cancel_btn =  (Button) findViewById(R.id.cancel_btn);
		save_btn =  (Button) findViewById(R.id.save_btn);
		mac_edit =  (EditText) findViewById(R.id.mac_edit);
		
		cancel_btn.setOnClickListener(new cancelBtnClick());
		save_btn.setOnClickListener(new saveBtnClick());
		
		String mac =  PreferencesBase.getStringByTargetKey(PreferencesBase.Mac);
		if (!mac.equals(""))
		{
			mac_edit.setText(mac);
		}
		
	}
	
	
	private class cancelBtnClick implements OnClickListener
	{
		@Override
		public void onClick(View arg0)
		{
			finish();
		}
		
	}
	
	private class saveBtnClick implements OnClickListener
	{
		@Override
		public void onClick(View arg0)
		{
			if(!mac_edit.getText().toString().equals(""))
			{
				showCommitDialog();
			}
			else 
			{
				ToastUtil.showShortToast("请输入MAC地址");
			}
			
		}
	}
	
	private void showCommitDialog()
	{
		 Dialog alertDialog = new AlertDialog.Builder(this). 
	                setTitle("保存"). 
	                setMessage("确认保存吗？"). 
	                setIcon(R.drawable.ic_launcher). 
	                setPositiveButton("确定", new DialogInterface.OnClickListener() 
	                { 
	                     
	                    @Override 
	                    public void onClick(DialogInterface dialog, int which)
	                    { 
	                    	PreferencesBase
	                    	.setValueForTargetKey(PreferencesBase.Mac, mac_edit.getText().toString());
	                    	if (isFirstEnter.equals(""))
							{
	                    		PreferencesBase
		                    	.setValueForTargetKey(PreferencesBase.isFirsEnter, "false");
	                    		Intent intent = new Intent(MacSettingActivity.this, PasswordSettingActivity.class);
	                    		startActivity(intent);
	                    		finish();
							}
	                    	else
	                    	{
	                    		finish();
							}
	                    } 
	                }). 
	                setNegativeButton("取消", new DialogInterface.OnClickListener() 
	                { 
	                     
	                    @Override 
	                    public void onClick(DialogInterface dialog, int which) 
	                    { 
	                    	dialog.dismiss();
	                    } 
	                }). 
	                create(); 
	        alertDialog.show(); 
	}
}
