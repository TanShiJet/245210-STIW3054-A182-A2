package com.Assignment2;

public class thread1 extends Thread {
	
	int totalcourse ;
	
	thread1 (String coursetable []){
	
		totalcourse = coursetable.length;//calculate total courses
	}
	
	public synchronized void run () {
	
		System.out.println("The total number of courses : " + totalcourse );
	}

}
