package Masrouqa;

import java.util.ArrayList;
import java.util.Scanner;

public class Main 
{
	public static void main(String args)
	{
            ArrayList<PostService>postArr=new ArrayList();
            ArrayList<signup>userArr=new ArrayList();
            System.out.println("1-login 2-sign up");
            int check = 0 ;
            Scanner input = new Scanner(System.in) ;
            check = input.nextInt() ;
            int flag =0 ;
            while(check==1)
            {
                Login objlogin = new Login() ;
                objlogin.Enterdata();
                Login x = objlogin.VerifyForException() ;
                for(int i=0 ;i<userArr.size();i++)
                {
                    if(x.Password==userArr.get(i).password&&x.PhoneNumber==userArr.get(i).PhoneNumber)
                    {check=3 ;}
                    if(flag==1)
                    {
                        System.out.println("Enterd wrong account");
                        flag=0;
                    }
                        
                }
            }
            if(check==2)
            {
                signup objsign = new signup() ;
                objsign.setdata();
                userArr.add(objsign) ;  
            }
          
            
            for(int i=0 ;i<postArr.size();i++)
                {
                    System.out.print(i+1);
                     System.out.print("-post category:");
                   System.out.println(postArr.get(i).category);
                   System.out.print("-post id:");
                    System.out.println(postArr.get(i).id);
                     System.out.println("-post description:");
                     System.out.println(postArr.get(i).description);
                }
                
            
            int counter =0 ,flag2=0 ;
                System.out.println("1-Posting  2-Search 3-delete ");
                int j = input.nextInt() ;
                
                switch(j)
                {
                    case 1 :
                        {
                            PostService obj2 = new PostService() ;
                            obj2.setPost(counter); 
                             postArr.add(obj2) ;
                             counter ++ ;
                         }
                        case 2 :
                        {
                           System.out.print("Please enter catogry of your item");
                           String cate = input.next();
                   for(int i=0 ;i<postArr.size();i++)
                {
                       if(cate.equals(postArr.get(i).category))
                       {System.out.println(postArr.get(i).category);
                       flag2 = 1;
                       }
                }
                   if(flag2==0)
                     {
                         System.out.println("Category not found");
                     }
                         }
                        
                }
                
                
                
                
	}

}
