package com.project.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.project.bean.LightInfo;

public class DataInfoDB  extends SQLiteOpenHelper
{
	private Context context;
	private static final int VERSION = 1;
	private static final String DB_NAME = "jiajite.db";
	
	private String LIGHTS = "CREATE TABLE IF NOT EXISTS  LIGHTS "+
							"(ID integer primary key ,NAME varchar(50),LIGHTLEVEL integer,CORLORTEMP integer,"
							+ "TIMEON varchar(50) ,TOMEOFF varchar(50),DELAY varchar(50),JUMP integer,"
							+"WATER integer, TOUCH integer, GFLIASH integer, BFLASH integer, WARMING integer,"
							+"LEDSTATE integer,NIGHTLAMPSTATE integer)";
	
	private String LED = "CREATE TABLE IF NOT EXISTS  LED (ID integer primary key ,MAC,GROUPS,LID)";
	
	public DataInfoDB(Context context, String name, CursorFactory factory,int version)
	{
		super(context, name, factory, version);
	}

	public DataInfoDB(Context c) 
	{ 
		
		super(c, DB_NAME, null, VERSION); 
		this.context = c;
		
	} 
	
	public void getLightInfo(LightInfo light, int id)
	{
		SQLiteDatabase db = getReadableDatabase();
		String sql="select * from LIGHTS where id = "+id;
		int ret = 0;
		Cursor mCursor = db.rawQuery(sql, null);
		if(null != mCursor &&  mCursor.getCount() >0)
		{
			mCursor.moveToFirst();
			do
			{
				light.setId(mCursor.getInt(mCursor.getColumnIndex("ID")));
				light.setName(mCursor.getString(mCursor.getColumnIndex("NAME")));
				light.setLight_level(mCursor.getInt(mCursor.getColumnIndex("LIGHTLEVEL")));
				light.setColor_temp(mCursor.getInt(mCursor.getColumnIndex("CORLORTEMP")));
				light.setTime_off(mCursor.getString(mCursor.getColumnIndex("TOMEOFF")));
				light.setTime_on(mCursor.getString(mCursor.getColumnIndex("TIMEON")));
				light.setDelay(mCursor.getString(mCursor.getColumnIndex("DELAY")));
				light.setJump(mCursor.getInt(mCursor.getColumnIndex("JUMP")));
				light.setWater(mCursor.getInt(mCursor.getColumnIndex("WATER")));
				light.setTouch(mCursor.getInt(mCursor.getColumnIndex("TOUCH")));
				light.setGflash(mCursor.getInt(mCursor.getColumnIndex("GFLIASH")));
				light.setBflash(mCursor.getInt(mCursor.getColumnIndex("BFLASH")));
				light.setWarming(mCursor.getInt(mCursor.getColumnIndex("WARMING")));
				light.setLed_state(mCursor.getInt(mCursor.getColumnIndex("LEDSTATE")));
				light.setNight_lamp_state(mCursor.getInt(mCursor.getColumnIndex("NIGHTLAMPSTATE")));
				
			}while (mCursor.moveToNext());
		}
		
		mCursor.close();
		db.close();
	}
	
	public void getAllLights(List<LightInfo> lightInfos)
	{
		String sql="select * from LIGHTS";
		SQLiteDatabase db = getReadableDatabase();
		Cursor mCursor=db.rawQuery(sql, null);
		if(null != mCursor &&  mCursor.getCount() >0)
		{
			lightInfos.clear();
			mCursor.moveToFirst();
			LightInfo light = null;
			do
			{
				light = new LightInfo();
				light.setId(mCursor.getInt(mCursor.getColumnIndex("ID")));
				light.setName(mCursor.getString(mCursor.getColumnIndex("NAME")));
				light.setLight_level(mCursor.getInt(mCursor.getColumnIndex("LIGHTLEVEL")));
				light.setColor_temp(mCursor.getInt(mCursor.getColumnIndex("CORLORTEMP")));
				light.setTime_off(mCursor.getString(mCursor.getColumnIndex("TOMEOFF")));
				light.setTime_on(mCursor.getString(mCursor.getColumnIndex("TIMEON")));
				light.setDelay(mCursor.getString(mCursor.getColumnIndex("DELAY")));
				light.setJump(mCursor.getInt(mCursor.getColumnIndex("JUMP")));
				light.setWater(mCursor.getInt(mCursor.getColumnIndex("WATER")));
				light.setTouch(mCursor.getInt(mCursor.getColumnIndex("TOUCH")));
				light.setGflash(mCursor.getInt(mCursor.getColumnIndex("GFLIASH")));
				light.setBflash(mCursor.getInt(mCursor.getColumnIndex("BFLASH")));
				light.setWarming(mCursor.getInt(mCursor.getColumnIndex("WARMING")));
				light.setLed_state(mCursor.getInt(mCursor.getColumnIndex("LEDSTATE")));
				light.setNight_lamp_state(mCursor.getInt(mCursor.getColumnIndex("NIGHTLAMPSTATE")));
				lightInfos.add(light);
				
			}while (mCursor.moveToNext());
		}
		
		mCursor.close();
		db.close();
		
	}
	
	public boolean isDataEmpty()
	{
		boolean ret = false;
		String sql="select * from LIGHTS";
		SQLiteDatabase db = getReadableDatabase();
		Cursor mCursor=db.rawQuery(sql, null);
		if(null != mCursor &&  mCursor.getCount() >0)
		{
			ret = false;
		}
		else
		{
			ret = true;
		}
		
		mCursor.close();
		db.close();
		return ret;
	}
	
	public int getWarmingState(int id)
	{
		SQLiteDatabase db = getReadableDatabase();
		String sql="select WARMING from LIGHTS where id = "+id;
		int ret = 0;
		Cursor mCursor = db.rawQuery(sql, null);
		mCursor.moveToFirst();
		do
		{
			ret = mCursor.getInt(0);
		}while (mCursor.moveToNext());
		db.close();
		mCursor.close();
		return ret;
	}
	
	public int getBflashState(int id)
	{
		SQLiteDatabase db = getReadableDatabase();
		String sql="select BFLASH from LIGHTS where id = "+id;
		int ret = 0;
		Cursor mCursor = db.rawQuery(sql, null);
		mCursor.moveToFirst();
		do
		{
			ret = mCursor.getInt(0);
		}while (mCursor.moveToNext());
		db.close();
		mCursor.close();
		return ret;
	}
	
	public int getGflashState(int id)
	{
		SQLiteDatabase db = getReadableDatabase();
		String sql="select GFLIASH from LIGHTS where id = "+id;
		int ret = 0;
		Cursor mCursor = db.rawQuery(sql, null);
		mCursor.moveToFirst();
		do
		{
			ret = mCursor.getInt(0);
		}while (mCursor.moveToNext());
		db.close();
		mCursor.close();
		return ret;
	}
	
	public int getTouchState(int id)
	{
		SQLiteDatabase db = getReadableDatabase();
		String sql="select TOUCH from LIGHTS where id = "+id;
		int ret = 0;
		Cursor mCursor = db.rawQuery(sql, null);
		mCursor.moveToFirst();
		do
		{
			ret = mCursor.getInt(0);
		}while (mCursor.moveToNext());
		db.close();
		mCursor.close();
		return ret;
	}
	
	public int getWaterState(int id)
	{
		SQLiteDatabase db = getReadableDatabase();
		String sql="select WATER from LIGHTS where id = "+id;
		int ret = 0;
		Cursor mCursor = db.rawQuery(sql, null);
		mCursor.moveToFirst();
		do
		{
			ret = mCursor.getInt(0);
		}while (mCursor.moveToNext());
		db.close();
		mCursor.close();
		return ret;
	}
	
	public int getJumpState(int id)
	{
		SQLiteDatabase db = getReadableDatabase();
		String sql="select JUMP from LIGHTS where id = "+id;
		int ret = 0;
		Cursor mCursor = db.rawQuery(sql, null);
		mCursor.moveToFirst();
		do
		{
			ret = mCursor.getInt(0);
		}while (mCursor.moveToNext());
		db.close();
		mCursor.close();
		return ret;
	}
	
	public int getNightLampState(int id)
	{
		SQLiteDatabase db = getReadableDatabase();
		String sql="select NIGHTLAMPSTATE from LIGHTS where id = "+id;
		int ret = 0;
		Cursor mCursor = db.rawQuery(sql, null);
		mCursor.moveToFirst();
		do
		{
			ret = mCursor.getInt(0);
		}while (mCursor.moveToNext());
		db.close();
		mCursor.close();
		return ret;
	}
	
	public int getFlash(int id)
	{
		SQLiteDatabase db = getReadableDatabase();
		String sql="select FLASH from LIGHTS where id = "+id;
		int ret = 0;
		Cursor mCursor = db.rawQuery(sql, null);
		mCursor.moveToFirst();
		do
		{
			ret = mCursor.getInt(0);
		}while (mCursor.moveToNext());
		db.close();
		mCursor.close();
		return ret;
	}
	
	public int getLedState(int id)
	{
		SQLiteDatabase db = getReadableDatabase();
		String sql="select LEDSTATE from LIGHTS where id = "+id;
		int ret = 0;
		Cursor mCursor = db.rawQuery(sql, null);
		mCursor.moveToFirst();
		do
		{
			ret = mCursor.getInt(0);
		}while (mCursor.moveToNext());
		db.close();
		mCursor.close();
		return ret;
	}
	
	public void UpdateTimeOn(String time, int id)
	{
		SQLiteDatabase db = getWritableDatabase();
		String sql ="update lights set TIMEON = \"" + time + "\" where id = " + id;
		db.execSQL(sql);
		db.close();
	}
	
	public void UpdateDelayTime(String time, int id)
	{
		
		SQLiteDatabase db = getWritableDatabase();
		String sql ="update lights set DELAY = \"" + time + "\" where id = " + id;
		db.execSQL(sql);
		db.close();
	}
	
	public void setJumpOff(int id)
	{
		
		SQLiteDatabase db = getWritableDatabase();
		String sql ="update lights set JUMP = " + 0 + " where id = " + id;
		db.execSQL(sql);
		db.close();
	}
	public void setWaterOff(int id)
	{
		
		SQLiteDatabase db = getWritableDatabase();
		String sql ="update lights set WATER = " + 0 + " where id = " + id;
		db.execSQL(sql);
		db.close();
	}
	public void setTouchOff(int id)
	{
		
		SQLiteDatabase db = getWritableDatabase();
		String sql ="update lights set TOUCH = " + 0 + " where id = " + id;
		db.execSQL(sql);
		db.close();
	}
	public void setGflashOff(int id)
	{
		
		SQLiteDatabase db = getWritableDatabase();
		String sql ="update lights set GFLASH = " + 0 + " where id = " + id;
		db.execSQL(sql);
		db.close();
	}
	public void setBflashOff(int id)
	{
		
		SQLiteDatabase db = getWritableDatabase();
		String sql ="update lights set BFLASH = " + 0 + " where id = " + id;
		db.execSQL(sql);
		db.close();
	}
	public void setWarmingOff(int id)
	{
		
		SQLiteDatabase db = getWritableDatabase();
		String sql ="update lights set WARMING = " + 0 + " where id = " + id;
		db.execSQL(sql);
		db.close();
	}
	
	public void setFlashOff(int id)
	{
		
		SQLiteDatabase db = getWritableDatabase();
		String sql ="update lights set JUMP = 0, WATER = 0,"+
					"TOUCH = 0, GFLIASH = 0, BFLASH = 0, WARMING = 0" + " where id = " + id;
		db.execSQL(sql);
		db.close();
	}
	
	public void setJumpOn(int id)
	{
		
		SQLiteDatabase db = getWritableDatabase();
		String sql ="update lights set JUMP = 1, WATER = 0,"+
					"TOUCH = 0, GFLIASH = 0, BFLASH = 0, WARMING = 0" + " where id = " + id;
		db.execSQL(sql);
		db.close();
	}
	public void setWaterOn(int id)
	{
		
		SQLiteDatabase db = getWritableDatabase();
		String sql ="update lights set JUMP = 0, WATER = 1,"+
					"TOUCH = 0, GFLIASH = 0, BFLASH = 0, WARMING = 0" + " where id = " + id;
		db.execSQL(sql);
		db.close();
	}
	public void setTouchOn(int id)
	{
		
		SQLiteDatabase db = getWritableDatabase();
		String sql ="update lights set JUMP = 0, WATER = 0,"+
					"TOUCH = 1, GFLIASH = 0, BFLASH = 0, WARMING = 0" + " where id = " + id;
		db.execSQL(sql);
		db.close();
	}
	public void setGflashOn(int id)
	{
		
		SQLiteDatabase db = getWritableDatabase();
		String sql ="update lights set JUMP = 0, WATER = 0,"+
					"TOUCH = 0, GFLIASH = 1, BFLASH = 0, WARMING = 0" + " where id = " + id;
		db.execSQL(sql);
		db.close();
	}
	public void setBflashOn(int id)
	{
		
		SQLiteDatabase db = getWritableDatabase();
		String sql ="update lights set JUMP = 0, WATER = 0,"+
					"TOUCH = 0, GFLIASH = 0, BFLASH = 1, WARMING = 0" + " where id = " + id;
		db.execSQL(sql);
		db.close();
	}
	public void setWarmingOn(int id)
	{
		
		SQLiteDatabase db = getWritableDatabase();
		String sql ="update lights set JUMP = 0, WATER = 0,"+
					"TOUCH = 0, GFLIASH = 0, BFLASH = 0, WARMING = 1" + " where id = " + id;
		db.execSQL(sql);
		db.close();
	}
	
	public void UpdateTimeOff(String time, int id)
	{
		
		SQLiteDatabase db = getWritableDatabase();
		String sql ="update lights set TOMEOFF = \"" + time + "\" where id = " + id;
		db.execSQL(sql);
		db.close();
	}
	
	public void UpdateLedState(int state, int id)
	{
		SQLiteDatabase db = getWritableDatabase();
		String sql ="update lights set LEDSTATE = "+state+" where id = " + id;
		db.execSQL(sql);
		db.close();
	}
	
	public void UpdateNightLampsState(int state, int id)
	{
		SQLiteDatabase db = getWritableDatabase();
		String sql ="update lights set NIGHTLAMPSTATE = "+state+" where id = " + id;
		db.execSQL(sql);
		db.close();
	}
	
	public void UpdatBright(int value, int id)
	{
		SQLiteDatabase db = getWritableDatabase();
		String sql ="update lights set LIGHTLEVEL = "+value+" where id = " + id;
		db.execSQL(sql);
		db.close();
	}
	
	public void UpdatColorTemp(int value, int id)
	{
		SQLiteDatabase db = getWritableDatabase();
		String sql ="update lights set CORLORTEMP = "+value+" where id = " + id;
		db.execSQL(sql);
		db.close();
	}
	
	public void Add(String name)
	{
		SQLiteDatabase db= getWritableDatabase();
		String sql ="insert into lights(ID ,NAME,LIGHTLEVEL,CORLORTEMP,TIMEON,TOMEOFF,DELAY,JUMP,WATER,TOUCH,GFLIASH,BFLASH,WARMING,LEDSTATE,NIGHTLAMPSTATE)"
				+" values (null,?,70,6500,'00:00','00:00','00:00',0,0,0,0,0,0,0,0)";
		db.execSQL(sql, new Object[]{name});
		db.close();
	}
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(LIGHTS);
		db.execSQL(LED);
	}

	@Override
	public void onUpgrade(SQLiteDatabase oldVersion, int arg1, int newVersion) 
	{
		
	}
}
