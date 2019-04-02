package com.Assignment2;

public class thread5 extends Thread  {

	String table [];
	
		thread5 (String lines[]){
			this.table=lines;
		}
		
	public void run () {
		
		for(String line : table) {
			
			if (line.contains("STIW3054")) { //search for STIW3054 then print
				
				System.out.println("\n"+line);
			}
			
		}
			
	}
}
