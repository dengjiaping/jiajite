package com.project.bean;

import java.io.Serializable;

public class LightInfo implements Serializable
{
	private int id;
	private String name;
	private int light_level;
	private int color_temp;
	private String time_on;
	private String time_off;
	private String delay;
	private int jump;
	private int water;
	private int touch;
	private int gflash;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLight_level() {
		return light_level;
	}
	public void setLight_level(int light_level) {
		this.light_level = light_level;
	}
	public int getColor_temp() {
		return color_temp;
	}
	public void setColor_temp(int color_temp) {
		this.color_temp = color_temp;
	}
	public String getTime_on() {
		return time_on;
	}
	public void setTime_on(String time_on) {
		this.time_on = time_on;
	}
	public String getTime_off() {
		return time_off;
	}
	public void setTime_off(String time_off) {
		this.time_off = time_off;
	}
	public String getDelay() {
		return delay;
	}
	public void setDelay(String delay) {
		this.delay = delay;
	}
	public int getJump() {
		return jump;
	}
	public void setJump(int jump) {
		this.jump = jump;
	}
	public int getWater() {
		return water;
	}
	public void setWater(int water) {
		this.water = water;
	}
	public int getTouch() {
		return touch;
	}
	public void setTouch(int touch) {
		this.touch = touch;
	}
	public int getGflash() {
		return gflash;
	}
	public void setGflash(int gflash) {
		this.gflash = gflash;
	}
	public int getBflash() {
		return bflash;
	}
	public void setBflash(int bflash) {
		this.bflash = bflash;
	}
	public int getWarming() {
		return warming;
	}
	public void setWarming(int warming) {
		this.warming = warming;
	}
	public int getLed_state() {
		return led_state;
	}
	public void setLed_state(int led_state) {
		this.led_state = led_state;
	}
	public int getNight_lamp_state() {
		return night_lamp_state;
	}
	public void setNight_lamp_state(int night_lamp_state) {
		this.night_lamp_state = night_lamp_state;
	}
	private int bflash;
	private int warming;
	private int led_state;
	private int night_lamp_state;
	
}
