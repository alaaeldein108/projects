package Masrouqa;

import java.util.Scanner;

public class PostService {
	//Operations
        String category ;
    String description ;
     int id;
    String place ;
    
    public void setPost(int co)
	{
		System.out.println("Enter the category of photo");
                Scanner input = new Scanner(System.in) ;
                this.category= input.next() ;
                System.out.println("Enter the description of photo");
                this.description= input.next() ;
                   System.out.println("Enter the place of photo");
                this.place= input.next() ;
                this.id= co ;
                
	}
    public PostService getpost()
	{
                return this ;
	}
    
}
