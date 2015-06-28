/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2008                    */
/* Created on:     2015/6/28/ÐÇÆÚÈÕ 16:15:15                       */
/*==============================================================*/


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('dbo.record') and o.name = 'FK_record_admin')
alter table dbo.record
   drop constraint FK_record_admin
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('dbo.record') and o.name = 'FK_record_item')
alter table dbo.record
   drop constraint FK_record_item
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.admin')
            and   type = 'U')
   drop table dbo.admin
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.item')
            and   type = 'U')
   drop table dbo.item
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.record')
            and   type = 'U')
   drop table dbo.record
go

execute sp_revokedbaccess dbo
go

/*==============================================================*/
/* User: dbo                                                    */
/*==============================================================*/
execute sp_grantdbaccess dbo
go

/*==============================================================*/
/* Table: admin                                                 */
/*==============================================================*/
create table dbo.admin (
   aid                  char(10)             collate Chinese_PRC_CI_AS not null,
   aname                nchar(10)            collate Chinese_PRC_CI_AS not null,
   apass                nchar(10)            collate Chinese_PRC_CI_AS not null,
   constraint PK_admin primary key (aid)
         on "PRIMARY"
)
on "PRIMARY"
go

/*==============================================================*/
/* Table: item                                                  */
/*==============================================================*/
create table dbo.item (
   iid                  char(10)             collate Chinese_PRC_CI_AS not null,
   iname                nchar(10)            collate Chinese_PRC_CI_AS not null,
   icount               int                  not null constraint DF_item_icount default (0),
   constraint PK_item primary key (iid)
         on "PRIMARY"
)
on "PRIMARY"
go

/*==============================================================*/
/* Table: record                                                */
/*==============================================================*/
create table dbo.record (
   rid                  char(10)             collate Chinese_PRC_CI_AS not null,
   iid                  char(10)             collate Chinese_PRC_CI_AS not null,
   aid                  char(10)             collate Chinese_PRC_CI_AS not null,
   time                 nchar(10)            collate Chinese_PRC_CI_AS not null,
   count                int                  not null constraint DF_record_count default (0),
   constraint PK_record primary key (rid)
         on "PRIMARY"
)
on "PRIMARY"
go

alter table dbo.record
   add constraint FK_record_admin foreign key (aid)
      references dbo.admin (aid)
go

alter table dbo.record
   add constraint FK_record_item foreign key (iid)
      references dbo.item (iid)
go

