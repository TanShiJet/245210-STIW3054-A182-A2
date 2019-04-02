package com.Assignment2;
import java.util.ArrayList;

public class thread4 extends Thread  {
	
	int NoSoc;

		thread4 (ArrayList <String> listsoc){
			NoSoc = listsoc.size();//calculate soc courses
		}
		
	public synchronized void run () {
		
		System.out.println("\nThe number of courses from SOC :" + NoSoc+ "\n");
		
	}
}
