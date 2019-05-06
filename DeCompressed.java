import java.util.*;
import java.io.*;

public class DeCompressed {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner reader=new Scanner(System.in);
	     
	     String Strtag="";
	     File file = new File("Compressed.txt");
	     StringBuilder fileContents = new StringBuilder((int)file.length());        

	     try (Scanner scanner = new Scanner(file)) {
	         while(scanner.hasNextLine()) {
	             fileContents.append(scanner.nextLine());
	         }
	         
	     }
	     finally
	     {
	    	 
	     }
	     Strtag=fileContents.toString();
	     String[]tags=Strtag.split(" ");
	     
	     ArrayList<String>Dictionary=new ArrayList<String>();
	     ArrayList<String>Stream=new ArrayList<String>();
	     for (int c=0; c<128; c++) {char s=(char)c;
	    	    Dictionary.add(String.valueOf(s));
	    	   } 
		 String Concat="";
		 int index=0;
		 String prev="";
		 for(int i=0;i<tags.length;i++)
		 {
			 index=Integer.parseInt(tags[i]);
			 if(i==0)
				{
					Stream.add(Dictionary.get(index));
					prev=Dictionary.get(index);
				}
			else
			{
                if(index<Dictionary.size())
                {
    			    char[]arr=Dictionary.get(index).toCharArray();
    				Concat=prev+arr[0];
    				Dictionary.add(Concat);
    				Stream.add(Dictionary.get(index));
    				prev=Dictionary.get(index);
                }
                else
                {
                	char[]arr=prev.toCharArray();
                	Concat=prev+arr[0];
                	Dictionary.add(Concat);
                	Stream.add(Concat);
                	prev=Dictionary.get(index);
                			
                }
			}
		 }
		    File file1=new File("De-Compressed.txt");
			try(BufferedWriter br1=new BufferedWriter(new FileWriter(file1)))
			{
			
				for(int ii=0;ii<Stream.size();ii++)
					br1.write(Stream.get(ii));
				
			}
			catch(IOException ex1)
			{
				System.out.print("Unable to write.");
			}

	}

}
