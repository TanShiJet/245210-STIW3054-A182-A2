package com.Assignment2;


import java.util.ArrayList;

public class thread3 extends Thread {
	
	String codetable [];
	String coursetable [];
	ArrayList <String> listsoc = new ArrayList<String>();
	Boolean value;
	
	thread3 (String codetable [],String coursetable []){
		
		this.codetable = codetable ;
		this.coursetable = coursetable;
		
		
	}
	
	public void run  () {
		// save soc course to array
		for(int i =0 ; i < codetable.length ; i++) { 
			
			if(codetable[i].contains("ST")&&!codetable[i].contains("STIV"))
				listsoc.add(coursetable[i]);
		}
		
		
	}

	public ArrayList<String> getListsoc() {
		
		return listsoc;
		
	}
	
	public void printlist() {
		
		listsoc.forEach(System.out::println);
		
	}
}
