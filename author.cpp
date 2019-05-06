#include "author.h"
#include <iostream>
#include <fstream>
#include <cstring>
#include <vector>
#include <iomanip>
#include <sstream>
#include <windows.h>
#include <stdlib.h>
using namespace std;


int author::Current_Size=0;
unsigned int author::SizeOf_Sec_Index=0;
author::author() {} //loadIndex();}
void author :: sortIndex() //bubble sort
{
    int len = Current_Size-1;
    PK temp;
    SK t2;
    for (int i = 0; i<len; i++)
    {
        for (int j = 0; j<(len-i); j++)
        {
            if (atoi(PrimIndex[j].AuthorID)>atoi(PrimIndex[j + 1].AuthorID))
            {
                temp = PrimIndex[j];
                PrimIndex[j] = PrimIndex[j + 1];
                PrimIndex[j + 1] = temp;
            }
        }
    }

    len=SizeOf_Sec_Index-1;
    for (int i = 0; i<len; i++)
    {
        for (int j = 0; j<(len-i); j++)
        {
            if (atoi(SecIndex[j].name)>atoi(SecIndex[j + 1].name))
            {
                t2 = SecIndex[j];
                SecIndex[j] = SecIndex[j + 1];
                SecIndex[j + 1] = t2;
            }
        }
    }
}
short author:: getSize(char len[])
{
    short siz=0;
    int deci = 1;
    for(int i = strlen(len)-1; i>=0; i--)
    {
        siz += ((short)len[i] - 48) * deci;
        deci *= 10;
    }
    return siz;
}
int author ::IndexBinarySearch(char key[])
{
    int low = 0, high = Current_Size;
    while (low <= high)
    {
        int middle = (low + high) / 2;
        if (strcmp(PrimIndex[middle].AuthorID, key) == 0)
            return middle;
        else if (atoi(PrimIndex[middle].AuthorID)<atoi(key))
            low = middle + 1;
        else
            high = middle - 1;
    }
    return -1;
}
int author ::IndexBinarySearchByName(char key[])
{
    int low = 0, high = SizeOf_Sec_Index;
    while (low <= high)
    {
        int middle = (low + high) / 2;
        if (strcmp(SecIndex[middle].name, key) == 0)
            return middle;
        else if (atoi(SecIndex[middle].name)<atoi(key))
            low = middle + 1;
        else
            high = middle - 1;
    }
    return -1;
}
int author::Search_In_Inverted_list(short key)
{
    int size = Current_Size;
    int low = 0, high = size - 1;
    while (low <= high)
    {
        int middle = (low + high) / 2;
        if (key == List[middle].rrn)
            return middle;
        else if (List[middle].rrn<key)
            low = middle + 1;
        else
            high = middle - 1;
    }
    return -1;
}

void author:: AddNewAuthor(int num)
{

    fstream file ;
    cin.ignore() ;

    file.open("data.txt", ios::app|ios::out) ;
    for(int i= 0 ; i<num; i++,Current_Size++ )
    {
        author record  ;
        cout<<"\n\nEnter th data about Author No. "<<i+1<<endl;
        cout << "please enter ID: " ;
        cin.getline(record.Author_ID,30) ;
        cout << "please enter Name: " ;
        cin.getline(record.Author_Name,50) ;
        cout << "please enter Mobile: " ;
        cin.getline(record.Author_Mobile,50) ;
        cout << "please enter Address: " ;
        cin.getline(record.Author_Address,10) ;

        char buffer[140] ;
        strcpy(buffer,record.Author_ID) ;
        strcat(buffer,"|");
        strcat(buffer,record.Author_Name) ;
        strcat(buffer,"|") ;
        strcat(buffer,record.Author_Mobile) ;
        strcat(buffer,"|") ;
        strcat(buffer,record.Author_Address) ;
        strcat(buffer,"|") ;

        short len=strlen(buffer);
        file.seekp(0,ios::end);
        short length = file.tellp() ;
        file<<len;
        file<<"#";
        file.write(buffer,len);

        strcpy(PrimIndex[Current_Size].AuthorID,record.Author_ID);
        PrimIndex[Current_Size].offset=length;

        strcpy(SecIndex[Current_Size].name,record.Author_Name);
        strcpy(SecIndex[Current_Size].AuthorID,record.Author_ID);

    }
    sortIndex();
    for(int i=0; i<Current_Size; i++)
    {
        List[i].rrn=i;
        strcpy(List[i].id,SecIndex[i].AuthorID);
    }
    GetInvertedList();
    saveIndex();

}
void author::GetInvertedList()
{
    for(int i=0; i<Current_Size; i++)
    {
        int  count  = -1;

        for (int j=i+1; j<Current_Size; j++)
        {
            if (strcmp(SecIndex[i].name,SecIndex[j].name)==0)
                count=j;
        }
        List[i].frequency=count;
    }

    vector <char*>pos;
    vector <int>ind;
    for(int i=0; i<Current_Size; i++)
    {
        bool check =false;
        for (unsigned int j=0; j<pos.size(); j++)
        {
            if (strcmp(SecIndex[i].name,pos[j])==0)
                check=true;
        }
        if (check==true)
            continue;
        else
        {
            pos.push_back(SecIndex[i].name);
            ind.push_back(i);
        }

    }

    for (unsigned int k=0; k<pos.size(); SizeOf_Sec_Index++,k++)
    {
        strcpy(SecIndex[SizeOf_Sec_Index].name,pos[k]);
        SecIndex[SizeOf_Sec_Index].RRN=ind[k];
    }


}
void author:: saveIndex()
{
    fstream file;
    fstream f2,f3;
    file.open("PK.txt", ios::out);
    f3.open("Author Inverted.txt",ios::out);
    f2.open("SK.txt",ios::out);
    for (int j=0; j<SizeOf_Sec_Index; j++)
    {
        f2<<SecIndex[j].name<<" "<<SecIndex[j].RRN<<" ";
    }
    for (int i =0 ; i<Current_Size ; i++)
    {
        f3<<List[i].rrn<<" "<<List[i].id<<" "<<List[i].frequency<<" ";
        file<<PrimIndex[i].AuthorID<<" "<<PrimIndex[i].offset<<" ";
    }
    file.close();
}
void author:: DeleteAuthor(char id[31])
{
    fstream file ;
    file.open("datafile.txt",ios::out|ios::in) ;
    int offset = IndexBinarySearch(id) ;
    if(offset==-1)
        cout << "not found \n" ;
    else
    {
        file.seekg(offset+2,ios::beg) ;
        file<<'*'  ;
        for(int i=offset; i<Current_Size-1; i++)
        {
            PrimIndex[i] = PrimIndex[i+1] ;

        }

        if (List[offset].frequency==-1)
            SecIndex[List[offset].rrn].RRN=-1;
        else
            SecIndex[List[offset].rrn].RRN=List[offset].frequency;

        for (int i = offset; i<Current_Size - 1; i++)
            List[i] = List[i+1];

    }
    cout<<"\n\n\t\tIT HAS DELETED SUCCESSFULLY ! ! !\n\n";

}
void author::loadIndex()
{
    fstream file,file2,file3 ;
    char temp[30];
    char t2[50];

    int offst2;
    int offst;

    int rrn,freq;
    char id[30];

    file.open("PK.txt",ios::in) ;
    file2.open("SK.txt",ios::in);
    file3.open("Author Inverted.txt",ios::in);
    file.seekg(0,ios::end);
    file2.seekg(0,ios::end);
    file3.seekg(0,ios::end);
    if (file.tellg()==0||file2.tellg()==0||file3.tellg()==0) // empty file
        return;

    else
    {
        file.seekg(0);
        file2.seekg(0);
        file3.seekg(0);
        while (1)
        {

            file2>>t2;
            file2>>offst2;
            strcpy(SecIndex[SizeOf_Sec_Index].name,t2);
            SecIndex[SizeOf_Sec_Index].RRN=offst2;
            if (file2.eof())
                break;
            SizeOf_Sec_Index++;

        }

        while(!file.eof()&&!file.fail())
        {
            file>>temp;
            file>>offst;
            strcpy(PrimIndex[Current_Size].AuthorID,temp) ;
            PrimIndex[Current_Size].offset=offst ;

            file3>>rrn;
            file3>>id;
            file3>>freq;
            List[Current_Size].rrn=rrn;
            strcpy(List[Current_Size].id,id);
            List[Current_Size].frequency=freq;
            Current_Size++;
        }
    }
}


void author::PrintAuthorID(char id[30])
{
    int length ;
    int address=IndexBinarySearch(id) ;
    fstream file ;
    file.open("data.txt",ios::in|ios::out) ;


    if (address == -1)
    {
        cout << "\nthis student not exists";
    }
    else
    {
        author s ;

        file.seekg(PrimIndex[address].offset,ios::beg) ;
        file.read((char *)&length,sizeof(short)) ;
        char *buffer=new char [length] ;
        file.read(buffer,length) ;
        istringstream stream(buffer);

        stream.getline(s.Author_ID, 31, '|');
        stream.getline(s.Author_Name, 50, '|');
        stream.getline(s.Author_Address, 50, '|');
        stream.getline(s.Author_Mobile, 10, '|');

        cout << endl <<"ID: "<<s.Author_ID<<"  Name: " <<s.Author_Name <<"  Address: " <<s.Author_Address<<"  Mobile: " <<s.Author_Mobile<<endl;
        file.close();  //delete []buffer;

    }


}
int author:: printlist()
{
    int x ;
    cout << "1) Add New Book" << endl ;
    cout << "2) Add New Author" << endl ;
    cout << "3) Delete Book(ID) " << endl ;
    cout << "4) Delete Author (ID)" << endl ;
    cout << "5) Print Book(ID)" << endl ;
    cout << "6) Print Book(Author_ID)" << endl ;
    cout << "7) Print Author(ID)" << endl ;
    cout << "8) Print Author(name)" << endl ;
    cout << "9) Write a Query" << endl ;
    cout << "10) Exit" << endl ;
    cin >> x ;
    if(x>10 || x<0)
    {
        cout<<"Wrong Choice. . \n";
        cout << "please enter from 0 to 10 only \n" ;
        printlist();
    }
    else
        return (x) ;

}
void author::PrintAuthorName(char name[])
{
    int length ;
    int address=IndexBinarySearchByName(name) ;

    fstream file ;
    file.open("data.txt",ios::in|ios::out) ;


    if (address == -1)
    {
        cout << "\nthis Author not exists";
    }
    else
    {
        author s ;

        file.seekg(PrimIndex[address].offset,ios::beg) ;
        file.read((char *)&length,sizeof(short)) ;
        char *buffer=new char [length] ;
        file.read(buffer,length) ;
        istringstream stream(buffer);

        stream.getline(s.Author_ID, 31, '|');
        stream.getline(s.Author_Name, 50, '|');
        stream.getline(s.Author_Address, 50, '|');
        stream.getline(s.Author_Mobile, 10, '|');

        cout << endl <<"ID: "<<s.Author_ID<<"  Name: " <<s.Author_Name <<"  Address: " <<s.Author_Address<<"  Mobile: " <<s.Author_Mobile<<endl;
        file.close();  //delete []buffer;

    }
}
