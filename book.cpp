#include "book.h"
#include <iostream>
#include <fstream>
#include <cstring>
#include <vector>
#include <iomanip>
#include <sstream>
#include <windows.h>
#include <stdlib.h>
using namespace std;

int Book::CurrSize =0;
unsigned int Book:: SizeOf_Sec_Index=0;

void Book::HandleRecord(Book &obj,char *record)
{
    strcpy(record, obj.Book_ID);
    strcat(record, "|");
    strcat(record, obj.Author_ID);
    strcat(record, "|");
    strcat(record, obj.Book_Title);
    strcat(record, "|");
    strcat(record, obj.Book_Price);
    strcat(record, "|");
}
void Book :: WriteIndeces()
{
    fstream File1,File2,File3;
    File1.open(Book_PrimarPath,ios::out);
    File2.open(Book_SecondryPath,ios::out);
    File3.open(Inverted_list_Path,ios::out);

    for(unsigned int i =0; i< SizeOf_Sec_Index; i++)
        File2<<secIndecis[i].AuthID<<" "<<secIndecis[i].RRN<<"\n";

    for(int i =0; i< CurrSize; i++)
    {
        File1<<PrimIndeces[i].ID<<" "<<PrimIndeces[i].offset<<"\n";
        File3<<List[i].rrn<<" "<<List[i].id<<" "<<List[i].frequency<<"\n";

    }

    File2.close();
    File3.close();

}
void Book::AddBooks(int NumberOfBooks)
{
    fstream Data;
    Data.open(Book_DataPath,ios::out|ios::app);
    int temp = CurrSize;
    for(int i=0 ; i<NumberOfBooks ; i++,CurrSize++)
    {

        Book Books;

        cin.ignore();
        cout<<"Enter Book Data Number "<<i+1<<endl;

        cout<<"Book Title : ";
        cin.getline(Books.Book_Title,50);
        cout<<"Book ID : ";
        cin.getline(Books.Book_ID,13);
        cout<<"Author ID : ";
        cin.getline(Books.Author_ID,30);
        cout<<"Book Price : ";
        cin>>Books.Book_Price;
        char buffer[maxRecordSize];
        HandleRecord(Books,buffer);
        short length = strlen(buffer);
        Data.seekp(0,ios::end);
        short off = Data.tellp();
        Data<<length;
        Data<<"$";
        Data.write(buffer,length);

        strcpy(PrimIndeces[CurrSize].ID,Books.Book_ID);
        PrimIndeces[CurrSize].offset=off;

        strcpy(secIndecis[CurrSize].AuthID,Books.Author_ID);
        strcpy(secIndecis[CurrSize].BookID,Books.Book_ID);


    }
    Sort();
    //int last_RRN = List[temp].rrn;
    for(int i=0; i<CurrSize; i++)
    {
        List[i].rrn=i;
        strcpy(List[i].id,secIndecis[i].BookID);
    }
    GetInvertedList();
    WriteIndeces();
}

void Book::GetInvertedList()
{
    for(int i=0; i<CurrSize; i++)
    {
        int  count  = -1;

        for (int j=i+1; j<CurrSize; j++)
        {
            if (strcmp(secIndecis[i].AuthID,secIndecis[j].AuthID)==0)//
                count=j;
        }
        List[i].frequency=count;
    }
    vector <char*>pos;
    vector <int>ind;
    for(int i=0; i<CurrSize; i++)
    {
        bool check =false;
        for (unsigned int j=0; j<pos.size(); j++)
        {
            if (strcmp(secIndecis[i].AuthID,pos[j])==0)
                check=true;
        }
        if (check==true)
            continue;
        else
        {
            pos.push_back(secIndecis[i].AuthID);
            ind.push_back(i);
        }
    }
    for (unsigned int k=0; k<pos.size(); k++,SizeOf_Sec_Index++)
    {
        strcpy(secIndecis[SizeOf_Sec_Index].AuthID,pos[k]);
        secIndecis[SizeOf_Sec_Index].RRN=ind[k];
    }
}
void Book::Sort() //selection sort
{
    int len = CurrSize-1 ;
    PrimaryIndex temp;
    SecndaryIndex temp2;
    Inverted_list temp3;

    for (int i = 0; i<len; i++)
        for (int j = 0; j<len - i; j++)
        {
            if (atoi(PrimIndeces[j].ID)>atoi(PrimIndeces[j + 1].ID))
            {
                temp = PrimIndeces[j];
                PrimIndeces[j] = PrimIndeces[j + 1];
                PrimIndeces[j + 1] = temp;
            }
        }

    len = SizeOf_Sec_Index-1;
    for (int i = 0; i<len; i++)
        for (int j = 0; j<len - i; j++)
        {
            if (atoi(secIndecis[j].AuthID)>atoi(secIndecis[j + 1].AuthID))
            {
                temp2 = secIndecis[j];
                secIndecis[j] = secIndecis[j + 1];
                secIndecis[j + 1] = temp2;
            }
        }
}
int Book :: Search_By_BookID(char key[])
{
    int size = CurrSize;
    int low = 0, high = size - 1;
    while (low <= high)
    {
        int middle = (low + high) / 2;
        if (strcmp(PrimIndeces[middle].ID, key) == 0)
            return middle;
        else if (atoi(PrimIndeces[middle].ID)<atoi(key))
            low = middle + 1;
        else
            high = middle - 1;
    }
    return -1;
}

int Book :: Search_By_Author_ID(char key[])
{
    int low = 0, high = SizeOf_Sec_Index ;
    while (low <= high)
    {
        int middle = (low + high) / 2;
        if (strcmp(secIndecis[middle].AuthID, key) == 0)
            return middle;
        else if (atoi(secIndecis[middle].AuthID)<atoi(key))
            low = middle + 1;
        else
            high = middle - 1;
    }
    return -1;
}
int Book::Search_In_Inverted_list(short key)
{
    int size = CurrSize;
    int low = 0, high = size ;
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
bool Book::DeleteBook (char id[])
{
    int offset = Search_By_BookID(id);
    fstream data(Book_DataPath,ios::out|ios::binary|ios::in);
    if (offset==-1)
    {
        return false;
    }
    else
    {
        char mark ='*';
        data.seekp(PrimIndeces[offset].offset+2,ios::beg);
        data<<mark;
        for (int i = offset; i<CurrSize - 1; i++)
            PrimIndeces[i] = PrimIndeces[i + 1];

        if (List[offset].frequency==-1)
            secIndecis[List[offset].rrn].RRN=-1;
        else
            secIndecis[List[offset].rrn].RRN=List[offset].frequency;

        for (int i = offset; i<CurrSize - 1; i++)
            List[i] = List[i+1];

        CurrSize--;
        data.close();
        return true;

    }


}
void Book ::PrintBookBy_BookID(char id[])
{
    short length ;
    int address=Search_By_BookID(id) ;
    fstream file ;
    file.open(Book_DataPath,ios::in) ;


    if (address == -1)
    {
        cout << "\nthis Book not exists";
        return;
    }
    else
    {
        Book s ;
        char mark;
        file.seekg(PrimIndeces[address].offset,ios::beg) ;
        file>>length;
        file.read(&mark,1);
        if (mark=='*')
        {
            cout<<"This Book Is Not Exist ...\n\n";
            return;
        }
        char *buffer=new char [length] ;
        file.read(buffer,length) ;
        istringstream stream(buffer);

        stream.getline(s.Book_ID, 31, '|');
        stream.getline(s.Author_ID, 50, '|');
        stream.getline(s.Book_Title, 50, '|');
        stream.getline(s.Book_Price, 10, '|');

        cout << endl <<"ID: "<<s.Book_ID<<"\nAuthor ID : " <<s.Author_ID <<"\nBook Title : " <<s.Book_Title<<"\nPrice: " <<s.Book_Price<<endl<<endl;
        file.close();
        delete []buffer;

    }


}
void Book::PrintBookBY_AuthorID(char id[])
{
    short length ;
    int address=Search_By_Author_ID(id) ;

    if (address==-1)
    {
        cout<<"This Author Is Not Exist ...\n\n";
        return;
    }
    int rrn = Search_In_Inverted_list(address);
    vector <char*>IDs;
    for(int i=rrn; i<CurrSize+1;)
    {
        IDs.push_back(List[i].id);
        if (List[i].frequency!=-1)
        {
            i=List[i].frequency;
            continue;
        }
        break;
    }
    vector <int> OffSets;
    for (int j=0; j<IDs.size(); j++)
    {
        OffSets.push_back( Search_By_BookID(IDs[j]));
    }
    fstream file ;
    int n=20;
    file.open(Book_DataPath,ios::in) ;
    cout<<"Book ID"<<setw(n)<<"Author ID"<<setw(n)<<"Book Title"<<setw(n)<<"Book Price\n";
    cout<<"------------------------------------------------------------------------------\n";
    for(int k=0; k<OffSets.size(); k++)
    {
        Book s ;
        char mark;
        file.seekg(PrimIndeces[OffSets[k]].offset,ios::beg) ;
        file>>length;
        file.read(&mark,1);
        char *buffer=new char [length] ;
        file.read(buffer,length) ;
        istringstream stream(buffer);

        stream.getline(s.Book_ID, 31, '|');
        stream.getline(s.Author_ID, 50, '|');
        stream.getline(s.Book_Title, 50, '|');
        stream.getline(s.Book_Price, 10, '|');

        cout<<s.Book_ID<<setw(n)<<s.Author_ID<<setw(n+5)<<s.Book_Title<<setw(n)<<s.Book_Price<<endl;
        file.seekp(0);
        delete []buffer;


    }
    cout<<"-----------------------------------------------------------------------------------\n";
}
