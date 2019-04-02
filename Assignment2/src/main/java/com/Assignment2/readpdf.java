package com.Assignment2;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class readpdf {
	

	public static void main(String[] args) throws IOException, InterruptedException {
			
		
			String ttable [];
			String codetable [];
			String datetable [];
			String timetable [];
			String coursetable [];
			String Sorttable[];
			ArrayList <String> listsoc = new ArrayList<String>();
			Scanner scan = new Scanner (System.in);
			
			
			Pattern r = Pattern.compile("^\\d+\\.");//
			Pattern d = Pattern.compile("^\\d+/");
			Pattern code = Pattern.compile("^[A-Z]+\\d+");
			Pattern date = Pattern.compile("\\d+\\/+\\d+\\/+\\d+");
			Pattern time = Pattern.compile("\\d+:\\d+");
			Pattern course = Pattern.compile("[A-Z]$");
			Pattern course2 = Pattern.compile("(?<!\\S)\\d(?!\\S)");
			 
			int count = 0;//use to create new array  
			
			System.out.println("Please insert your pdf directory :");	
			String scanedStr=scan.nextLine();//
			
	        try (PDDocument document = PDDocument.load(new File( scanedStr ))) {

		            document.getClass();
		            
	
		            if (!document.isEncrypted()) {

			            	PDFTextStripperByArea stripper = new PDFTextStripperByArea();
			                stripper.setSortByPosition(true);
		
			                PDFTextStripper tStripper = new PDFTextStripper();
		
			                String pdfFileInText = tStripper.getText(document);// convert PDF into string
			                
			                String lines[] = pdfFileInText.split("\\r?\\n"); // Split the String and store it in array
			                ttable = new String[lines.length];
			                
			                for (int i = 0 ; i<lines.length ; i++) { // make the array more organized
			            	   
				            	   Matcher m = r.matcher(lines[i]);
				            	   Matcher m2 = d.matcher(lines[i]);	
				            	   if (m.find()){
				                		
				                		ttable[count]=lines[i];
				                		count++;
				            	   }
			                	
				                   else if (m2.find()) {
				                		ttable[count-1]= lines[i-2]+lines[i-1]+lines[i];
				                   }
			                }
	               
			                codetable = new String[count];  //create a code table with correct size; 
			                datetable = new String[count];	//create a date table table with correct size; 
			                timetable = new String[count];  //create a time table with correct size; 
			                coursetable = new String[count];//create a course table with correct size; 
			                
			                for(int i = 0 ; i < count ; i++) {
			            	   
			                		Sorttable = ttable [i].split(" ");
			               
					                for(int j =0 ; j < Sorttable.length ; j++) {
					            	  
						            	   Matcher mcode = code.matcher(Sorttable[j]);// match alphabets digits esample :(qweqweq1231231)
						            	   Matcher mdate = date.matcher(Sorttable[j]);// match digits/digits/digits example (13/21/12)
						            	   Matcher mtime= time.matcher(Sorttable[j]);// match digits:digits example :(34:63)
						            	   Matcher mcourse = course.matcher(Sorttable[j]);// match end with alphabets : ( kjxdfkjfdklflkh )
						            	   Matcher mcourse2 = course2.matcher(Sorttable[j]);// match single digit example :( 1 )
						            	   
						            	   	   // add String to arrays according to their category
							            	   if(mcode.find()) {
							            		   
							            		   codetable [i] = mcode.group();
							            	   }
							            	   if(mdate.find()) {
							            		   
							            		   datetable [i] = mdate.group();
							            	   }
							            	   if(mtime.find()) {
							            		   
							            		   timetable [i] = mtime.group();
							            	   }
							            	   if(mcourse.find() || mcourse2.find() ) {
							            		   if (j<=3)
							            			   coursetable [i] = Sorttable[j];
							            		   else
							            			   coursetable [i] +=" "+ Sorttable[j];
							            	   }
					            	   
					               }     
			               
			                }
	               
			               thread1 t1 = new thread1(coursetable);
			               t1.start();
			              
			         
			               thread2 t2 = new thread2(datetable);
			               t2.start();
			               
			               
			               thread3 t3 = new thread3(codetable,coursetable);
			               t3.start();
			               t3.join();
			               listsoc=t3.getListsoc();
			              
			               thread4 t4 = new thread4(listsoc);
			               t4.start();
			               t4.join();
			               t3.printlist();//display soc course
			              
			               thread5 t5 = new thread5(lines);
			               t5.start();
	               
	               
		        }
		  
			} 

	}

}