use kasir;

create table Profil(
    NomorInduk varchar(20) primary key,
    Nama varchar(50),
    JenisKelamin enum('L','P'),
    TanggalLahir date,
    TempatLahir varchar(50),
    Alamat varchar(500),
    NamaAyah varchar(50),
    NamaIbu varchar(50),
    Telpon1 varchar(20),
    Telpon2 varchar(20),
    CurrentLevel varchar(25),
    TanggalMasuk date,
    TanggalLulus date
);

create table Clerk(
    ID int unsigned auto_increment primary key,
    NIP varchar(20) not null,
    Nama varchar(50) not null,
    Jabatan varchar(50),
    Username varchar(50) not null unique,
    Password varchar(32) not null comment 'SHA1'
);

create table TransactionSummary(
    ID bigint unsigned auto_increment primary key,
    UUID varchar(36) not null,
    NomorInduk varchar(20) not null,
    IDClerk int unsigned not null,
    CreateDate timestamp null,
    LastUpdateDate timestamp default current_timestamp on update current_timestamp,
    TotalAmount float(18,2) not null,
    Note varchar(2000)
);



create table IPSP(
    ID bigint unsigned auto_increment primary key,
    NomorInduk varchar(20) not null,
    ChargedLevel varchar(25) not null,
    Amount float(18,2) unsigned,
    TotalInstallment float(18,2) unsigned,
    SettledDate date,
    Note varchar(500),
    IDsTransactionDetail varchar(1000)
);

create table IPS like IPSP;
create table IPSB like IPSP;
create table IUA like IPSP;



create table IPP(
    ID bigint unsigned auto_increment primary key,
    NomorInduk varchar(20) not null,
    ChargedLevel varchar(25) not null,
    AmountMonth1 float(10,2) unsigned,
    IDTransactionDetail1 bigint unsigned,
    AmountMonth2 float(10,2) unsigned,
    IDTransactionDetail2 bigint unsigned,
    AmountMonth3 float(10,2) unsigned,
    IDTransactionDetail3 bigint unsigned,
    AmountMonth4 float(10,2) unsigned,
    IDTransactionDetail4 bigint unsigned,
    AmountMonth5 float(10,2) unsigned,
    IDTransactionDetail5 bigint unsigned,
    AmountMonth6 float(10,2) unsigned,
    IDTransactionDetail6 bigint unsigned,
    AmountMonth7 float(10,2) unsigned,
    IDTransactionDetail7 bigint unsigned,
    AmountMonth8 float(10,2) unsigned,
    IDTransactionDetail8 bigint unsigned,
    AmountMonth9 float(10,2) unsigned,
    IDTransactionDetail9 bigint unsigned,
    AmountMonth10 float(10,2) unsigned,
    IDTransactionDetail10 bigint unsigned,
    AmountMonth11 float(10,2) unsigned,
    IDTransactionDetail11 bigint unsigned,
    AmountMonth12 float(10,2) unsigned,
    IDTransactionDetail12 bigint unsigned
);

create table OSIS like IPP;

create table IUS(
    ID bigint unsigned auto_increment primary key,
    NomorInduk varchar(20) not null,
    ChargedLevel varchar(25) not null,
    AmountSemester1 float(10,2) unsigned,
    IDTransactionDetail1 bigint unsigned,
    AmountSemester2 float(10,2) unsigned,
    IDTransactionDetail2 bigint unsigned
);


create table IKK(
    ID bigint unsigned auto_increment primary key,
    NomorInduk varchar(20) not null,
    ChargedLevel varchar(25) not null,
    Amount float(10,2) unsigned,
    IDTransactionDetail bigint unsigned
);



create table IKS(
    ID bigint unsigned auto_increment primary key,
    NomorInduk varchar(20) not null,
    ChargedLevel varchar(25) not null,
    TransactionName varchar(500) not null,
    Amount float(18,2) unsigned,
    Note varchar(500),
    IDTransactionDetail bigint unsigned
);

create table Sumbangan like IKS;
create table ILL like IKS;



create table IPSPTransaction(
    ID bigint unsigned auto_increment primary key,
    UUID varchar(36) not null,
    IDIuran bigint unsigned not null,
    IDClerk int unsigned not null,
    IDTransactionSummary bigint unsigned not null,
    NomorInduk varchar(20) not null,
    Amount float(18,2) unsigned not null,
    PaymentMethod enum('CASH','TRANSFER') not null,
    CreateDate timestamp null,
    LastUpdateDate timestamp default current_timestamp on update current_timestamp,
    Note varchar(500)
);

create table IPSTransaction like IPSPTransaction;
create table IPSBTransaction like IPSPTransaction;
create table IUATransaction like IPSPTransaction;

create table IPPTransaction like IPSPTransaction;
create table OSISTransaction like IPSPTransaction;
create table IUSTransaction like IPSPTransaction;
create table IKKTransaction like IPSPTransaction;

create table IKSTransaction like IPSPTransaction;
create table SumbanganTransaction like IPSPTransaction;
create table ILLTransaction like IPSPTransaction;