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
							+ "TIMEON varchar(50),TOMEOFF varchar(50),DELAY varchar(50),JUMP integer,"
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
	
	public List<LightInfo> getAllLights()
	{
		List<LightInfo> lightInfos=null;
		String sql="select * from LIGHTS";
		SQLiteDatabase db = getReadableDatabase();
		Cursor mCursor=db.rawQuery(sql, null);
		if(null != mCursor &&  mCursor.getCount() >0)
		{
			mCursor.moveToFirst();
			lightInfos = new ArrayList<LightInfo>();
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
		return lightInfos;
	}
	
	public void UpdateLedState(int state, int id)
	{
		SQLiteDatabase db = getWritableDatabase();
		String sql ="update lights set LEDSTATE = "+state+" where id = " + id;
		System.out.println("sql=" + sql);
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
