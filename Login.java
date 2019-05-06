package Masrouqa;

import java.util.Scanner;

public class Login {
	//Bounadry
	int PhoneNumber;
	int Password;
	public void Enterdata(){
            Scanner input = new Scanner(System.in) ;
        System.out.println("Enter phone Number");
        this.PhoneNumber = input.nextInt();
        
        System.out.println("Enter Password");
        this.Password = input.nextInt();
        
        }
	public Login VerifyForException(){
        return this ;
        }
}
