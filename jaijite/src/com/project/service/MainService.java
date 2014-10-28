package com.project.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.http.util.ByteArrayBuffer;

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

			if (!tasks.isEmpty())
			{
				task = tasks.poll();// The change removes the task from the task
				// queue after executing tasks
				if (null != task)
				{
					doTask(task);
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

	private synchronized void doTask(Task task)
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
		}

		catch (Exception ex)
		{
			System.out.println("error:"+ex.toString());
		}
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
