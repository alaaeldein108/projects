package Masrouqa;

import java.util.Scanner;

public class signup {

    String name ;
    int password ;
    String email ;
    int PhoneNumber ;

public void setdata()
{
          Scanner input = new Scanner(System.in) ;
        System.out.println("Enter  name");
        this.name = input.next();
        
        System.out.println("Enter Password");
        this.password = input.nextInt();
        
        System.out.println("Enter email");
        this.email = input.next();
        
        System.out.println("Enter PhoneNumber");
        this.PhoneNumber = input.nextInt();
}
public signup get()
{
    return this ;
}
    
}
