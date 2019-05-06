/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2012                    */
/* Created on:     09/05/2018 07:22:40 ?                        */
/*==============================================================*/


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('BOOK') and o.name = 'FK_BOOK_BELONG_TO_SECTIONE')
alter table BOOK
   drop constraint FK_BOOK_BELONG_TO_SECTIONE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('BOOK') and o.name = 'FK_BOOK_RESPOSIBL_ADMIN')
alter table BOOK
   drop constraint FK_BOOK_RESPOSIBL_ADMIN
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('BORROW') and o.name = 'FK_BORROW_BORROWED__BOOK')
alter table BORROW
   drop constraint FK_BORROW_BORROWED__BOOK
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('BORROW') and o.name = 'FK_BORROW_BORROWWIN_STUDENT')
alter table BORROW
   drop constraint FK_BORROW_BORROWWIN_STUDENT
go

alter table ADMIN
   drop constraint PK_ADMIN
go

if exists (select 1
            from  sysobjects
           where  id = object_id('ADMIN')
            and   type = 'U')
   drop table ADMIN
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('BOOK')
            and   name  = 'RESPOSIBLE_FOR_FK'
            and   indid > 0
            and   indid < 255)
   drop index BOOK.RESPOSIBLE_FOR_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('BOOK')
            and   name  = 'BELONG_TO_FK'
            and   indid > 0
            and   indid < 255)
   drop index BOOK.BELONG_TO_FK
go

alter table BOOK
   drop constraint PK_BOOK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('BOOK')
            and   type = 'U')
   drop table BOOK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('BORROW')
            and   name  = 'BORROWED_BOOK_FK'
            and   indid > 0
            and   indid < 255)
   drop index BORROW.BORROWED_BOOK_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('BORROW')
            and   name  = 'BORROWWING_BOOK_FK'
            and   indid > 0
            and   indid < 255)
   drop index BORROW.BORROWWING_BOOK_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('BORROW')
            and   type = 'U')
   drop table BORROW
go

alter table SECTIONE
   drop constraint PK_SECTIONE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SECTIONE')
            and   type = 'U')
   drop table SECTIONE
go

alter table STUDENT
   drop constraint PK_STUDENT
go

if exists (select 1
            from  sysobjects
           where  id = object_id('STUDENT')
            and   type = 'U')
   drop table STUDENT
go

/*==============================================================*/
/* Table: ADMIN                                                 */
/*==============================================================*/
create table ADMIN (
   AID                  int                  not null,
   ANAME                varchar(50)          null,
   SALARY               int                  null,
   AGE                  int                  null
)
go

alter table ADMIN
   add constraint PK_ADMIN primary key nonclustered (AID)
go

/*==============================================================*/
/* Table: BOOK                                                  */
/*==============================================================*/
create table BOOK (
   BOOKID               int                  not null,
   SECTIONNUM           int                  not null,
   AID                  int                  null,
   TITEL                varchar(50)          null,
   SNUM                 int                  not null,
   AUTHORNAME           varchar(1024)        null
)
go

alter table BOOK
   add constraint PK_BOOK primary key nonclustered (BOOKID)
go

/*==============================================================*/
/* Index: BELONG_TO_FK                                          */
/*==============================================================*/
create index BELONG_TO_FK on BOOK (
SECTIONNUM ASC
)
go

/*==============================================================*/
/* Index: RESPOSIBLE_FOR_FK                                     */
/*==============================================================*/
create index RESPOSIBLE_FOR_FK on BOOK (
AID ASC
)
go

/*==============================================================*/
/* Table: BORROW                                                */
/*==============================================================*/
create table BORROW (
   BOOKID               int                  null,
   SID                  int                  null,
   DEADLINE             datetime             null,
   DBORROW              datetime             null
)
go

/*==============================================================*/
/* Index: BORROWWING_BOOK_FK                                    */
/*==============================================================*/
create index BORROWWING_BOOK_FK on BORROW (
SID ASC
)
go

/*==============================================================*/
/* Index: BORROWED_BOOK_FK                                      */
/*==============================================================*/
create index BORROWED_BOOK_FK on BORROW (
BOOKID ASC
)
go

/*==============================================================*/
/* Table: SECTIONE                                              */
/*==============================================================*/
create table SECTIONE (
   SECTIONNUM           int                  not null,
   SECTIONNAME          varchar(50)          null
)
go

alter table SECTIONE
   add constraint PK_SECTIONE primary key nonclustered (SECTIONNUM)
go

/*==============================================================*/
/* Table: STUDENT                                               */
/*==============================================================*/
create table STUDENT (
   SID                  int                  not null,
   SNAME                varchar(50)          not null,
   PHONE                int                  not null
)
go

alter table STUDENT
   add constraint PK_STUDENT primary key nonclustered (SID)
go

alter table BOOK
   add constraint FK_BOOK_BELONG_TO_SECTIONE foreign key (SECTIONNUM)
      references SECTIONE (SECTIONNUM)
go

alter table BOOK
   add constraint FK_BOOK_RESPOSIBL_ADMIN foreign key (AID)
      references ADMIN (AID)
go

alter table BORROW
   add constraint FK_BORROW_BORROWED__BOOK foreign key (BOOKID)
      references BOOK (BOOKID)
go

alter table BORROW
   add constraint FK_BORROW_BORROWWIN_STUDENT foreign key (SID)
      references STUDENT (SID)
go

