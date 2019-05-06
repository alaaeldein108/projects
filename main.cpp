#include <iostream>
#include <fstream>
#include <string.h>
#include <strstream>
#include <bits/stdc++.h>
using namespace std;

static int Address_Space = 997;
static int Bucket_Size = 1;
const int sizee = 110;


struct Student
{
    char ID[9];
    char Name[49];
    char Address[49];
};

struct Index
{
    char ID[9];
    int offset;
};

int hash1(char id[])
{
    double A=0.6180339887;
    int M=997;
    int Key=atoi(id);
    int x=Key*A;
    int z=M*x;
    int y=z%100;
    return (y);
}


void intializeFile(fstream& file)
{
    Index s;
    s.offset = -1;
    for(int i = 0; i < Address_Space; i++)
    {
        for(int j = 0; j < Bucket_Size; j++)
        {
            file.write((char*)&s, sizeof(Index));
        }
    }
}

int add(Student s, fstream& Dfile)
{
    Dfile.seekp(0, ios::end);
    int offset = Dfile.tellg();
    char buffer[sizee];
    strcpy(buffer,s.ID);
    strcat(buffer,"|");
    strcat(buffer,s.Name);
    strcat(buffer,"|");
    strcat(buffer,s.Address);
    strcat(buffer,"$");
    short length = strlen(buffer);
    Dfile.write((char*)&length, sizeof(length));
    Dfile.write(buffer, length);

    return offset;
}


istream& operator>>(istream& in, Student& s)
{
    cout<<"Enter Student ID: ";
    in.getline(s.ID, 9);
    cout<<"Enter Student Name: ";
    in.getline(s.Name, 49);
    cout<<"Enter Student Address: ";
    in.getline(s.Address, 49);
    return in;
}

void Print(Student s)
{
    cout<<"Student Name: "<<s.Name<<endl;
    cout<<"Student ID: "<<s.ID<<endl;
    cout<<"Student Address: "<<s.Address<<endl;
}

int addStudent(Student s, fstream& Dfile, fstream& Ifile)
{
    int offset = add(s, Dfile);
    Dfile.flush();
    Index i;
    strcpy(i.ID, s.ID);
    i.offset = offset;

    Index temp;
    int RRN = hash1(s.ID);
    int newRRN = RRN;

    while(true)
    {
        Ifile.seekg(newRRN*sizeof(Index));
        Ifile.read((char*)&temp, sizeof(temp));

        if(temp.offset == -1)
            break;

        newRRN = (newRRN + 1)%(Address_Space * Bucket_Size);

        if(newRRN == RRN)
        {
            cout<<"There is no free slots in the file of students\n";
            Print(s);
            return 1;
        }

    }

    Ifile.seekp(newRRN*sizeof(Index));
    Ifile.write((char*)& i, sizeof(i));
    return 0;
}

int searchStudent(char id[], fstream& dFile, fstream& Ifile)
{
    Index i;
    int RRN = hash1(id);
    int newRRN = RRN;
    int count1 = 0;
    char c;
    while(true)
    {
        count1++;
        Ifile.seekg(newRRN*sizeof(Index));
        Ifile.get(c);
        if(c == '*')
        {
            cout<<"The Record is Deleted"<<endl;
            return -1;
        }
        else if(c != '*')
        {
            Ifile.seekg(-1, ios::cur);
            Ifile.read((char*)& i, sizeof(i));

            if(strcmp(i.ID, id) == 0)
                break;
            if(i.offset == -1)
                return -1;
        }

        newRRN = (newRRN + 1)%997;

        if(newRRN == RRN)
            return -1;

    }

    cout<<"Total Number of iterations to search about "<<id<<" = "<<count1<<endl;
    Student s;
    dFile.seekg(i.offset, ios::beg);
    short length;

    dFile.read((char*)&length,sizeof(length));
    char*buffer=new char[length];
    dFile.read(buffer,length);
    istrstream strbuf(buffer);
    strbuf.getline(s.ID,9,'|');
    strbuf.getline(s.Name,49,'|');
    strbuf.getline(s.Address,48,'$');
    cout<<"Student ID: "<<s.ID<<endl;
    cout<<"Student Name: "<<s.Name<<endl;
    cout<<"Student Address: "<<s.Address<<endl;
    cout<<"========================================"<<endl;
    delete buffer;

    return 1;

}


int deleteStudent(char id[], fstream& dFile, fstream& iFile)
{
    Index i;
    int RRN = hash1(id);
    int newRRN = RRN;
    while(true)
    {
        iFile.seekg(newRRN*sizeof(Index));
        iFile.read((char*)&i, sizeof(i));

        if(strcmp(i.ID,id) == 0)
            break;
        if(i.offset == -1)
            return -1;
        newRRN = (newRRN + 1)%Address_Space;

        if(newRRN == RRN)
            return -1;
    }

    dFile.seekp(i.offset, ios::beg);
    dFile.put('*');
    dFile.flush();

    iFile.seekp(newRRN*sizeof(Index), ios::beg);
    i.ID[0] = '*';
    i.offset = -1;
    iFile.write((char*)&i, sizeof(i));
    iFile.flush();
    cout <<"The Student That have ID = "<<id<<" is Deleted."<<endl;
    return 1;
}



int main()
{
    fstream Dfile, Ifile;
    Dfile.open("DataFile.txt", ios::in|ios::out|ios::binary|ios::trunc);
    Ifile.open("IndexFile.txt", ios::in|ios::out|ios::binary|ios::trunc);
    Student s;
    int choice;
    intializeFile(Ifile);
    while(true)
    {
        cout<<"1- Add Student"<<endl;
        cout<<"2- Search Student"<<endl;
        cout<<"3- Delete Student"<<endl;
        cout<<"0- Exit"<<endl<<endl;
        cout<<"Enter Your Choice: ";
        cin>>choice;
        cout<<"===================================="<<endl;
        if(choice == 1)
        {
            cin.ignore();
            cin>>s;
            addStudent(s, Dfile, Ifile);
            cout<<"======================================"<<endl;
            Print(s);
            cout<<"======================================"<<endl;
        }
        else if(choice == 2)
        {
            char id[9];
            cout<<"Enter ID: ";
            cin>>id;
            searchStudent(id, Dfile, Ifile);
            cout<<"======================================"<<endl;
        }
        else if(choice == 3)
        {
            char id[9];
            cout<<"Enter ID: ";
            cin>>id;
            deleteStudent(id,Dfile,Ifile);
            cout<<"======================================"<<endl;
        }
        else if(choice == 0)
        {
            cout<<"Thank you for Using our Program."<<endl;
            return 0;
        }
    }
}

