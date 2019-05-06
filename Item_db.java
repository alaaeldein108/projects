package Masrouqa;

public class Item_db {
	//Attributes&Setters&Getters
	int ID;
	String Category;
	String description;
	Item_db parameter;
	public void setItem(int ID,int Number,String Category,String description)
	{
		parameter.ID=ID;
		parameter.Category=Category;
		parameter.description=description;
	}
	public Item_db getItem()
	{
		return parameter;
	}
}
