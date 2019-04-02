package com.Assignment2;

public class thread2 extends Thread {
	
	String datetable[];
	int totaldays ;
	
	thread2 (String datetable []){
		
		this.datetable = datetable ;
		totaldays = 0;
	}
	
	
	public synchronized  void run (){
		
		for(int i =0; i<datetable.length - 1 ; i++) {
			
			if (! datetable [i].equals("0")) {
				++totaldays;  //calculate total days
				for(int j = i +1 ; j <datetable.length  ; j++) {
					
					if (datetable [i].equals(datetable [j])) {
						datetable [j] = "0" ; 
					}
				}
				
			}
			
		}
		
		
		System.out.println("\nTotal number of days :"+totaldays);
	}
	

}
