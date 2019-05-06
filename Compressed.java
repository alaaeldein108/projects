import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
//import java.io.*;

public class Compressed {

	public static void main(String[] args) throws FileNotFoundException {
	     Scanner reader=new Scanner(System.in);
	     String Stream="";
	     File file = new File("Original.txt");
	     StringBuilder fileContents = new StringBuilder((int)file.length());        

	     try (Scanner scanner = new Scanner(file)) {
	         while(scanner.hasNextLine()) {
	             fileContents.append(scanner.nextLine());
	         }
	         
	     }
	     finally
	     {
	    	 
	     }
	     Stream=fileContents.toString();

	     
	     char[]Strbuffer=Stream.toCharArray();
	    
	     
	     ArrayList<String>Tag=new ArrayList<String>();
	     ArrayList<String>Dictionary=new ArrayList<String>();
	     for (int c=0; c<128; c++) 
	     {
	    	 char s=(char)c;
 	          Dictionary.add(String.valueOf(s));
 	     }
	     char S;
	     String T="";
		 String Concat="";
		 boolean flag=false;
	   for(int i=0;i<Strbuffer.length;i++)
	   {
		   S=Strbuffer[i];
		   
		   if(i==0)
		   {
			   Tag.add(""+Dictionary.indexOf(String.valueOf(S)));
			   Dictionary.add(S+String.valueOf(Strbuffer[i+1]));
			    
		   }
		   else if((i==Strbuffer.length-1) &&( T.equals("")))
		   {
			   Tag.add(""+Dictionary.indexOf(String.valueOf(S)));
		   }
		   else
		   {
			   if(i+1!=Strbuffer.length&&flag==true)
				      Concat=T+String.valueOf(Strbuffer[i+1]);
			   else if(i+1!=Strbuffer.length&&flag==false)
				   Concat=String.valueOf(S)+String.valueOf(Strbuffer[i+1]);
			   
				if(Dictionary.contains(Concat))
				{
					T=Concat;
					flag=true;
					if(i+1==Strbuffer.length-1)
					{
						Tag.add(""+Dictionary.indexOf(T));
					}
				}
				else
				{
					if(T=="")
						Tag.add(""+Dictionary.indexOf(String.valueOf(S)));
					else
						Tag.add(""+Dictionary.indexOf(T));
					Dictionary.add(Concat);
					T="";
					flag=false;
					
					
				}
		   }
	   }
	   
	    File file1=new File("Compressed.txt");
		try(BufferedWriter br1=new BufferedWriter(new FileWriter(file1)))
		{
		
			for(int ii=0;ii<Tag.size();ii++)
				br1.write(Tag.get(ii)+" ");
			
		}
		catch(IOException ex1)
		{
			System.out.print("Unable to write.");
		}
	   
	   
	     
	}

}
