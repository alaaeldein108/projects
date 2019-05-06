package Masrouqa;

public class Address {
	//Attributes&Setters&Getters
	String Country;
	String City;
	String Governerate;
	String Street;
	Address parameter;
	public void setAddress(String Country,String City,String Governerate,String Street)
	{
		parameter.Country=Country;
		parameter.City=City;
		parameter.Governerate=Governerate;
		parameter.Street=Street;
	}
	public Address getAddress()
	{
		return parameter;
	}
}
