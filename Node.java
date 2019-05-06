package ass2;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.*;
class Node
{
	static void CreateRecordsFile (String filename, int numberOfRecords) throws IOException
	{
	File obj = new File(filename);
            RandomAccessFile ob = new RandomAccessFile(obj , "rw");
            int i=0;
	    while(i<numberOfRecords)
	    {
	    	if(i!=numberOfRecords-1)
	    	{ob.writeInt(i+1);ob.writeInt(0);ob.writeInt(0);ob.writeInt(0);}
	    	else
	    	{ob.writeInt(-1);ob.writeInt(0);ob.writeInt(0);ob.writeInt(0);}
                i++;
	    } 
	}
	public static void InsertNewRecordAtIndex (String filename, int Key, int ByteOffset) throws FileNotFoundException, IOException
	{
		File obj = new File(filename);
		RandomAccessFile ob = new RandomAccessFile(obj , "rw");
		ob.seek(0);
        int id =ob.readInt();
        int ID=1;
        if(id==-1)
        {
        	System.out.println("There is no place to insert the record or the index");
        	System.out.println("====================================================");
        }
        else
        {
        	ob.seek(id*16);
            ob.writeInt(Key);ob.writeInt(ByteOffset);ob.writeInt(-1);ob.writeInt(-1);
            if(id>1)
            {
                while(true)
                {
                	ob.seek(id*16);
                    int g=ob.readInt();
                    ob.seek(ID*16);
                    int d=ob.readInt();
              		if(d<g) 
                    {
              			ob.seek((ID*16)+12);
                   		int right=ob.readInt();
                  		if(right==-1)
                   		{
                  			ob.seek((ID*16)+12);
                    		ob.writeInt(id);break;
                    	}
                   		else if(right!=-1)
                 		{
                   			ID=right;continue;
                    	}
                     }
                    else if(d>g) 
                    {
                         ob.seek(ID*16+8);
                         int left=ob.readInt();
                         if(left==-1)
                          {
                              ob.seek((ID*16)+8);
                              ob.writeInt(id);break;
                          }
                          else if(left!=-1)
                          {
                        	  ID=left;continue;
                          }
                     }
                       }}
               ob.seek(0);
               ob.writeInt(id+1);
               int NR=(int) ((ob.length())/16);
               ob.seek(0);
               int m=ob.readInt();
               if(m==NR)
               {
            	   ob.seek(0);
            	   ob.writeInt(-1);
               }
                }
     }
        public static void Display(String filename) throws FileNotFoundException, IOException
        {
            File obj = new File(filename);
            RandomAccessFile ob = new RandomAccessFile(obj , "rw");
            int key,left,right,offset;
            int NR=(int) ((ob.length())/16);
            for(int i=0;i<NR;i++)
            {
                ob.seek(i*16);
                key=ob.readInt();
                offset=ob.readInt();
                left=ob.readInt();
                right=ob.readInt();
                System.out.println(key+"|"+offset+"|"+left+"|"+right);
            }
        }
	public static void SearchRecordInIndex (String filename, int Key) throws IOException
	{
		File obj = new File(filename);
		RandomAccessFile ob = new RandomAccessFile(obj , "rw");
		ob.seek(16);
		int existkey;
		while(true) {
				existkey=ob.readInt();
                        if(Key>existkey) { 
                        	ob.skipBytes(8);
				int right=ob.readInt();
				if(right==-1) {
					System.out.println("This Key is not Exist");
					break;
				}
				else
					ob.seek(right*16);
			}
			else if(Key<existkey)
			{
				ob.skipBytes(4);
				int left=ob.readInt();
				if(left==-1) {
					System.out.println("This Key is not Exist");
					break;
				}
				else
					ob.seek(left*16);
			}
			else {
				System.out.println("the key is found");
				System.out.println("offset ="+ob.readInt());
				break;
			}
		}
	}
	public static void TraverseBinarySearchTreeInOrder (String FileName,int root) throws IOException
	{
		File obj = new File(FileName);
		RandomAccessFile ob = new RandomAccessFile(obj , "rw");
		int offset=root;
		if(offset!=-1)
		{
			ob.seek(root*16);
			int key=ob.readInt();
			ob.seek(root*16+8);
			int left=ob.readInt();
			int right=ob.readInt();
			TraverseBinarySearchTreeInOrder (FileName,left);
			System.out.print(key+" ");
			TraverseBinarySearchTreeInOrder (FileName,right);
		}
	}
public static void main(String[] args)throws Exception {
	Scanner input= new Scanner(System.in);
	int num;
	int id;
	int offset,choice;
	String DFile = "BinaryTree.bin";
	boolean y=true,x=true;
	while(y)
	{
		System.out.println("1-Create.");
		System.out.println("2-Insert.");
		System.out.println("3-Search.");
		System.out.println("4-Traverse.");
		System.out.println("5-Display");
		System.out.print("Choice => ");
		choice=input.nextInt();
		switch(choice)
		{
		case 1:
			{
				if(x)
				{
					System.out.print("Enter number of records : ");
					num=input.nextInt();
					CreateRecordsFile (DFile,num);
				    Display(DFile);
				    x=false;
				}
			}break;
		case 2:
			{
			    System.out.print("Enter key of node : " );
			    id=input.nextInt();
			    System.out.print("Enter offset of node : " );
			    offset=input.nextInt();
			    InsertNewRecordAtIndex(DFile,id,offset);
			    Display(DFile);
			}break;
		case 3:
			{
				int q;
				System.out.print("Enter the key to Search : ");
				q=input.nextInt();
				SearchRecordInIndex(DFile,q);
				/*if(r==-1)
					System.out.println("the key is not found");
				else
					System.out.println(q+" is found and byte offset : "+r);*/
			}break;
		case 4:
			{
				TraverseBinarySearchTreeInOrder(DFile,1);
				System.out.println();
			}break;
		case 5:Display(DFile);break;
			default:
				System.out.println("Try Again");
		}
		char c;
		System.out.print("if you [Insert - Search - Traverse tree -Display] Enter c : ");
		c=input.next().charAt(0);
		if(c!='c')
			y=false;
	}
}
}