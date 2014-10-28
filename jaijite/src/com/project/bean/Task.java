package com.project.bean;

import java.util.Map;

import android.content.Context;

public class Task
{
	
	private String command;
	private Context context = null;
	
	public Task(Context ctx, String command)
	{
		super();
		this.command = command;
		this.context = ctx;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
}
