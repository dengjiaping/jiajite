package com.project.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.http.util.ByteArrayBuffer;

import android.R.integer;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.project.bean.Info;
import com.project.bean.Task;

public class MainService extends Service implements Runnable
{
	private Socket client_socket = null;
	private static boolean isRun = false;
	// Task Queue
	private static Queue<Task> tasks = new LinkedList<Task>();
	private static final int READ_TIME_OUT = 10;

	String task_result = "";
	@Override
	public void onCreate() 
	{
		super.onCreate();
		isRun = true;
		Thread thread = new Thread(this); // creater new thread
		thread.start();
	}

	public static void newTask(Task t)
	{
		tasks.add(t);
	}

	@Override
	public void run()
	{
		try
		{
			client_socket=new Socket(Info.SERVER_ADDR, Info.PORT);
		} catch (UnknownHostException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		while (isRun)
		{

			Task task = null;
			int try_count = 0;
			if (!tasks.isEmpty())
			{
				task = tasks.poll();// The change removes the task from the task
				// queue after executing tasks
				if (null != task)
				{
					//连接3次
					while (try_count < 3) 
					{
						try_count++;
						task_result = doTask(task);
						if (!task_result.equals("")) 
						{
							// 更新ui
							System.out.println("=====go to update ui======");
							try_count = 0;
							break;
						}
					}
					if (try_count >= 3) 
					{
						//重连3次失败
						try_count = 0;
						System.out.println("=====connect fail======");
					}
					
				}
			}
			try
			{
				Thread.sleep(1000);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private synchronized String doTask(Task task)
	{
		String command= task.getCommand();
		PrintWriter out=null;
		InputStream reader = null;
		String resultStr = "";
		try {
			client_socket.setSoTimeout(5*1000);
			out=new PrintWriter(client_socket.getOutputStream());
			out.println(command);
			out.flush();
			reader = client_socket.getInputStream();
			byte[] buffer = new byte[256];
			int lenght;
			int read_counts = 0;
			while (read_counts < READ_TIME_OUT) 
			{
				if (reader.available() != 0)
				{
					if ((lenght = reader.read(buffer)) != -1)
					{
						ByteArrayBuffer byteBuffer = new ByteArrayBuffer(lenght);
						byteBuffer.append(buffer, 0, lenght);
						byte[] data = byteBuffer.buffer();
						resultStr = new String(data, "UTF-8");
						System.out.println("send command resultStr====["+resultStr+"]");
					}
				}
				if (!resultStr.equals("")) 
				{
					return resultStr;
				}
				read_counts++;
				Thread.sleep(1 * 1000);
			}
		}

		catch (Exception ex)
		{
			System.out.println("error:"+ex.toString());
			return "";
		}
		return "";
	}


	@Override
	public IBinder onBind(Intent arg0)
	{
		return null;
	}
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		isRun = false;
		try
		{
			if (client_socket != null)
			{
				client_socket.close();
				System.out.println("client Socket close");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}