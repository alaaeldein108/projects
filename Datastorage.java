package datastorage;
import java.nio.file.*;
import java.io.EOFException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.HashMap; 
import java.util.Map;; 
public class Datastorage {
     public int offset=0;
     public static int sid,uid,deleteid,temp;
    public static int id;
	public static int price;
	public static int quantity;
	
	public void add_record(String f) throws IOException {
		
		File obj = new File(f);
		RandomAccessFile ob = new RandomAccessFile(obj , "rw");
	    ob.seek(obj.length());
	    ob.writeInt(id);ob.writeInt(price);ob.writeInt(quantity);
	  }
	
		public void update_record(String f,int upid) throws IOException {
		Scanner input= new Scanner(System.in);
		File obj = new File(f);
		RandomAccessFile ob = new RandomAccessFile(obj , "rw");
		int i=0;
        int Id = 0,p=0,q=0;
        while(i<ob.length())
        {
          ob.seek(i);
          Id=ob.readInt();
          p= ob.readInt();
          q= ob.readInt();
          if(Id==uid)
        	  break;
          i+=12;
        }
        if(Id==upid)
        {
        	System.out.println("The old data is : ");
            System.out.println("---------------------");
            System.out.println("The old id : "+ Id );
            System.out.println("The old Price : "+ p);
            System.out.println("The old Quantity : "+ q);
            System.out.println("---------------------");
            ob.seek(i+4);
            System.out.println("The new data is : ");
            System.out.println("---------------------");
            System.out.print("The new Price : ");
            p=input.nextInt();
            ob.writeInt(p);
            System.out.print("The new Quantity : ");
            q=input.nextInt();
            ob.writeInt(q);
        }
        else
        	System.out.println("Id not Found");
        
	}
	public static  void sortindex(String DFile , String Ifile) throws IOException//arrange id and offset in index file
	{
		int id ; 
		int offset = 0 ;
		File obj = new File(DFile);
		File obj2 = new File(Ifile);
		RandomAccessFile ob = new RandomAccessFile(obj , "rw");
		RandomAccessFile obj3 = new RandomAccessFile(obj2 , "rw");	
		Map<Integer,Integer> my_map = new HashMap<Integer, Integer>();
		ob.seek(0);
		while (ob.getFilePointer()<ob.length()) {
			id = ob.readInt();
			my_map.put(id,new Integer(offset));
			ob.skipBytes(8);
			offset+=12;
		}
		Map<Integer, Integer> treeMap = new TreeMap<Integer,Integer>(my_map);
		            for (Map.Entry<Integer,Integer> entry:treeMap.entrySet()){
		                obj3.writeInt(entry.getKey());
		                obj3.writeInt(entry.getValue());
		            }
        	
	}
	
        public void read_record(String f)throws IOException{
         
        	File obj = new File(f);
    		RandomAccessFile ob = new RandomAccessFile(obj , "rw");
        int i=0;
        int Id,p,q;
        while(i<ob.length())
        {
          ob.seek(i);
          Id=ob.readInt();
          p= ob.readInt();
          q= ob.readInt();
          i+=12;
          System.out.println(Id+" "+p+" "+q);
        }
     }
        public int search_record(String x,int seid)throws IOException
        {
        	File obj = new File(x);
    		RandomAccessFile ob = new RandomAccessFile(obj , "rw");
    		Datastorage obj1=new Datastorage();
    		int middle;
    		int left=0;
    		int right=(int)(ob.length()/8-1);
    		while(left<=right)
    		{
    			middle=(left+right)/2;
				ob.seek(middle*8); 
				obj1.id=ob.readInt();
				obj1.offset=ob.readInt();
				if(obj1.id==seid)
					return obj1.offset;
				else if(obj1.id>seid)
					right=middle-1;
				else if(obj1.id<seid)
					left=middle+1;
    		}
    		return -1;
    		
        }
        public int search_record1(String x,int seid)throws IOException
        {
        	File obj = new File(x);
    		RandomAccessFile ob = new RandomAccessFile(obj , "rw");
    		Datastorage obj1=new Datastorage();
    		int middle;
    		int left=0;
    		int right=(int)(ob.length()/8-1);
    		while(left<=right)
    		{
    			middle=(left+right)/2;
				ob.seek(middle*8); 
				obj1.id=ob.readInt();
				obj1.offset=ob.readInt();
				if(obj1.id==seid)
					return middle*8;
				else if(obj1.id>seid)
					right=middle-1;
				else if(obj1.id<seid)
					left=middle+1;
    		}
    		return -1;
    		
        }
        public void printsearch_record(String x,String y,int z)throws IOException
        {
        	File f = new File(x);
        	File g = new File(y);
        	RandomAccessFile ob = new RandomAccessFile(f , "rw"); //ob -> data
        	RandomAccessFile ob1 = new RandomAccessFile(g , "rw"); //ob1 ->index 
    		temp=search_record(y,z);
    		if(temp==-1) 
    			System.out.println("the record is not found.");
    		else
    		{
    			int i,p,q;
    			ob.seek(temp);
    			i=ob.readInt();
    			p=ob.readInt();
    			q=ob.readInt();
    			System.out.println("The record is : "+i+" "+p+" "+q);
    		}
    	}
        public void deleterecord(String x,String y,int z)throws IOException
        {
        	File f = new File(x);
        	File g = new File(y);
        	RandomAccessFile ob = new RandomAccessFile(f , "rw"); //ob -> data
        	RandomAccessFile ob1 = new RandomAccessFile(g , "rw"); //ob1 ->index 
   		    printsearch_record(x,y,z);
   			ob.seek(temp);
   			ob.writeChar('*');
   			System.out.println("the record has been deleted");
   			int var =search_record1(y,z);
   			int temp1=var,temp2=var+8;
   			while(true) {
   				//temp1 is upper
   				//temp2 is the lower
   				ob1.seek(temp2);//read
   				int c=ob1.readInt();
   				int v=ob1.readInt();
   				ob1.seek(temp1);//write the lower record
   				ob1.writeInt(c);
   				ob1.writeInt(v);
   				
   				temp1=temp2;
   				temp2=temp2+8;
  
   				if(ob1.length()==temp2+8)
   					{ob1.setLength(temp2);
   					break;
   					}	
   			}
   	}       
    public static void main(String[] args)throws Exception {
    	String DFile = "product.bin";
    	String IFile = "index.bin";
		String SDFile="SampleDataFile.bin";
		
		Datastorage obj=new Datastorage();
		
		Scanner input= new Scanner(System.in);
		int v;
		System.out.println("1-to read and write in file by your self.");
		System.out.println("2-to read another file.");
		System.out.print("Enter the option that you want : ");
		v=input.nextInt();
		int choice,choice1;
		switch(v)
		{
		
		case 1: {
			boolean y=true;
			while(y)
			{
				System.out.println("1-Add Record.");
				System.out.println("2-Update Record.");
				System.out.println("3-Search Record.");
				System.out.println("4-Delete Record.");
				System.out.print("Enter the choice: ");
				choice=input.nextInt();
				switch(choice)
				{
				case 1:
					{
						System.out.print("Enter the ID:");
						obj.id=input.nextInt();
						System.out.print("Enter the Price:");
						obj.price=input.nextInt();
						System.out.print("Enter the Quantity:");
						obj.quantity=input.nextInt();
						obj.add_record(DFile);
						obj.sortindex(DFile , IFile);
					    obj.read_record(DFile);
					}break;
				case 2:
					{
						
						System.out.print("Enter the Id to make Update : ");
						uid=input.nextInt();
						obj.update_record(DFile,uid);
					}break;
				case 3:
					{
						System.out.print("Enter the Id to make Search : ");
						sid=input.nextInt();
						obj.search_record(IFile, sid);
						obj.printsearch_record(DFile,IFile,sid);
					}break;
				case 4:
					{	
						System.out.print("Enter the Id to make Delete : ");
						deleteid=input.nextInt();
						obj.deleterecord(DFile,IFile,deleteid);
					}break;
				default:
					System.out.println("Try Again");
				}
				char c;
				System.out.print("if you [Add - Delete - Update -Search] Enter c : ");
				c=input.next().charAt(0);
				if(c!='c')
					y=false;
			}
					
				}break;
		case 2:
		{
			System.out.println("1-Add Record.");
			System.out.println("2-SortFileIndex.");
			System.out.println("3-Update Record.");
			System.out.println("4-Search Record.");
			System.out.println("5-Delete Record.");
			System.out.print("Enter the choice: ");
			choice1=input.nextInt();
			switch(choice1)
			{
			case 1:
				{
					System.out.print("Enter the ID:");
					obj.id=input.nextInt();
					System.out.print("Enter the Price:");
					obj.price=input.nextInt();
					System.out.print("Enter the Quantity:");
					obj.quantity=input.nextInt();
					obj.add_record(SDFile);
				    obj.read_record(SDFile);
				}break;
			case 2:
				obj.sortindex(SDFile , IFile);break;
			case 3:
				{
					System.out.print("Enter the Id to make Update : ");
					uid=input.nextInt();
					obj.update_record(SDFile,uid);
				}break;
			case 4:
				{
					System.out.print("Enter the Id to make Search : ");
					sid=input.nextInt();
					obj.search_record(IFile, sid);
					obj.printsearch_record(SDFile,IFile,sid);
				}break;
			case 5:
				{
					System.out.print("Enter the Id to make Delete : ");
					deleteid=input.nextInt();
					obj.deleterecord(SDFile,IFile,deleteid);
				}break;
				default:
					System.out.println("Try Again");
			}
		}break;
		default:
			System.out.println("Try Again");
		}
		
    }
}