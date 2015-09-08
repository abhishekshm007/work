package ainaa.acup.execution;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.util.ArrayList;

import ainaa.acup.server.ClientThread;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RemoteManager implements Runnable{

	private Double HR;
	private Double WR;
	private Gson gson;
	private ArrayList<Double> coordinates;
	private Robot robot;
	PointerInfo poinInfo ;
	Point point ;
	int Mx = 0, My = 0;
	String mode ="";
	public RemoteManager() {
		
	}
	
	@Override
	public void run() {
		try
		{
			poinInfo = MouseInfo.getPointerInfo();
			point = poinInfo.getLocation();
			gson = new Gson();
			robot = new Robot();
			HR = (double) 1;
			WR = (double) 2;
			Mx = (int) point.getX();
			 My = (int) point.getY();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		while(true)
		{
		try
		{
			{
				String obj = (String) ClientThread.objectIn.readObject();
				System.out.println(obj);
				 if(obj.trim().equalsIgnoreCase("left"))
					{
						robot.mousePress(InputEvent.BUTTON1_MASK);
			            robot.mouseRelease(InputEvent.BUTTON1_MASK);
			            System.out.println("heloo");
			            continue;
			            
					}
					else if(obj.trim().equalsIgnoreCase("right"))
					{
						robot.mousePress(InputEvent.BUTTON3_MASK);
			            robot.mouseRelease(InputEvent.BUTTON3_MASK);
			            continue;
					}
					else
					{
				coordinates = gson.fromJson(obj, new TypeToken<ArrayList<Double>>() {}.getType());
				System.out.println(obj);
				{
					Double x =  (WR *coordinates.get(0));
					Double y =  (HR * coordinates.get(1));
					int a = (int)(Mx +x);
					int b = (int)( My +y);
					robot.mouseMove(a,b);
					Mx = a;
					My = b;
				}
					}
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			break;
		}
		}
	}
	

}
