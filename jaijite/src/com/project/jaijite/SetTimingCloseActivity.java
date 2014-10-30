
package com.project.jaijite;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.project.db.DataInfoDB;
import com.project.util.ToastUtil;


public class SetTimingCloseActivity extends Activity implements OnClickListener {

	private EditText hoursEt;
    private EditText minutesEt;
    private String hours;
    private String minutes;
    private Button leftButton,rightButton;
    private int ledID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_timing_close);
        minutesEt = (EditText) findViewById(R.id.minutesEt);
        hoursEt = (EditText) findViewById(R.id.hoursEt);
        leftButton=(Button)findViewById(R.id.leftButton);
        rightButton=(Button)findViewById(R.id.rightButton);
        leftButton.setOnClickListener(this);
        rightButton.setOnClickListener(this);
        
        ledID = getIntent().getIntExtra("ledID", 0);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.leftButton:
                finish();
                break;

            case R.id.rightButton:

                if (checkInput())
                {
                    DataInfoDB dataDB = new DataInfoDB(SetTimingCloseActivity.this);
                    dataDB.UpdateTimeOff(hours+"时"+minutes+"分", ledID);
                    finish();
                }
                break;

            default:
                break;
        }
    }

    private boolean checkInput() {
        String hourStr = hoursEt.getEditableText().toString();
        String minuteStr = minutesEt.getEditableText().toString();
        hours=hourStr;
        minutes=minuteStr;
        if (hourStr == null || "".equals(hourStr)) {
            ToastUtil.showShortToast("定时小时不能为空");
            hoursEt.requestFocus();
            return false;
        }
        
        int h = Integer.valueOf(hourStr);
        if (h > 23) {
            hoursEt.requestFocus();
            ToastUtil.showShortToast("不能大于23时");
            return false;
        }
        int m= Integer.valueOf(minuteStr);
        if (m > 60) {
            minutesEt.requestFocus();
            ToastUtil.showShortToast("不能大于60分钟");
            return false;
        }
        hideInput();
        return true;
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
}

