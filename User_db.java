package Masrouqa;

public class User_db {
	//Attributes&Setters&Getters
		String name;
		int Password;
		String email;
		int PhoneNumber;
		String answer;
		String address;
		public User_db parameter;
		public void setUser(String name,int Password,String email,int PhoneNumber,String answer,String address)
		{
			parameter.name=name;
			parameter.Password=Password;
			parameter.email=email;
			parameter.PhoneNumber=PhoneNumber;
			parameter.answer=answer;
			parameter.address=address;
		}
		public User_db getUser(User_db paramter)
		{
			return parameter;
		}
}
