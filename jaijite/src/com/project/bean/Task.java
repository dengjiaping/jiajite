package com.project.bean;

import android.app.Activity;
import android.content.Context;


public class Task
{
	private Context context = null;
	private String command;
	private int id;
	private String head = "LED:";
	private String mac = "E005C54DF500";
	private int function;
	private String attribute;
	
	private String new_paswd;
	private String old_paswd;
	
	public Task(Context context)
	{
		this.context = context;
	}
	
	public Context getContext() {
		return context;
	}
	
	public String getNew_paswd() {
		return new_paswd;
	}

	public void setNew_paswd(String new_paswd) {
		this.new_paswd = new_paswd;
	}

	public String getOld_paswd() {
		return old_paswd;
	}

	public void setOld_paswd(String old_paswd) {
		this.old_paswd = old_paswd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
	
	public String getHead() {
		return head;
	}

	public void sethead(String head) {
		this.head = head;
	}
	
	public int getFunction() {
		return function;
	}

	public void setFunction(int function) {
		this.function = function;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String  attribute) {
		this.attribute = attribute;
	}

	public String getCommand() 
	{
		if (head.equals("LED:")) 
		{
			command = head + mac + ","+ id+","+ function+","+ attribute;
		}
		else 
		{
			command = head + old_paswd +"," + new_paswd;
		}
		
		return command;
	}

	

}
