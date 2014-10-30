
package com.project.jaijite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.project.bean.Task;
import com.project.service.MainService;
import com.project.util.PreferencesBase;
import com.project.util.ToastUtil;

public class PasswordSettingActivity extends BaseActivity implements OnClickListener {

	private EditText passEt;
	private String passStr;
	private Button rightButton = null;
	private String old_paswd = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password_setting);

		MainService.addActivity(this);
		passEt = (EditText) findViewById(R.id.passEt);

		findViewById(R.id.leftButton).setOnClickListener(this);
		rightButton = (Button) findViewById(R.id.rightButton);
		rightButton.setOnClickListener(this);

		old_paswd =  PreferencesBase.getStringByTargetKey(PreferencesBase.PassWord);
		if (!old_paswd.equals(""))
		{
			passEt.setText(old_paswd);
		}
	}

	/**
	 * Description：隐藏输入法
	 */
	private void hideInput() {
		// 是否存在焦点
		if (getCurrentFocus() != null) {
			InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {

		case R.id.leftButton:
			hideInput();
			finish();
			break;

		case R.id.rightButton:
			if (checkInput()) 
			{
				showCommitDialog();
			}
			break;

		default:
			break;
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
						PreferencesBase.setValueForTargetKey(PreferencesBase.PassWord, passStr);
						Task task = new Task(PasswordSettingActivity.this);
						task.sethead("CODE:");
						task.setOld_paswd(old_paswd);
						task.setNew_paswd(passStr);
						MainService.newTask(task, false);
						ToastUtil.showShortToast("重设成功");
						finish();
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

	private boolean checkInput() {
		passStr = passEt.getEditableText().toString();
		if (passStr.length() < 16) {
			ToastUtil.showShortToast("密码太短");
			passEt.requestFocus();
			return false;
		}
		hideInput();
		return true;
	}

	@Override
	protected void onDestroy() 
	{
		MainService.removeActivity(this);
		super.onDestroy();
	}
}
