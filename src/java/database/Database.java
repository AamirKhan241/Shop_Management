package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    
    private static Database d=new Database();
    private static Connection conn;
    
    private Database(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","mobile","22647");
            System.out.println("Connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Database getInstance(){
        return d;
    }
    
    public static Connection getConnection(){
        return conn;
    }
    
}

//Tables
/*
    create table brands(serial_no int primary key,brand_name varchar(100) not null,supplier_name varchar(50),Contact_number varchar(50)
    ,Description varchar(500));
    create table item(serial_no int primary key,item_name varchar (100) not null,brand varchar(100) not null,
    quality varchar(50) default'-',color varchar(30) default'-',weight varchar(10) default'-',stock varchar(10) default'-',
    shelf_location varchar(20) default'-',cost_price varchar(20) default'-',sale_price varchar(20) default'-',
    tax varchar(5) default'-');
    create table sale_reportp(serial_no int unique not null,invoice_number varchar(20) primary key,Buyer_name varchar(50) not null,
    Buyer_contact varchar(15),buyer_address varchar(100),buyer_state varchar(20),buyer_pincode varchar(7),Buyer_GSTIN varchar(30),
    sale_date date default sysdate,amount varchar(20) not null,payment_Mode varchar(100),transportation varchar(200),GST varchar(5) 
    default 'NO',Cartage varchar(10));
    create table salep(serial_no int primary key,Invoice_Number varchar(20) references sale_reportp(invoice_number),
    Item_Id int references item(serial_no),Quantity int not null);
    create table orders(serial_No int primary key, user_id int references users(user_id), Item_Id int references item(serial_no),
    Quantity int,Amount int);
    create table users(user_id int primary key, username varchar(20) unique not null, password varchar(20) not null)
    create table credit(serial_No int primary key,credittor_Name varchar(50) not null,Tdate date default sysdate,amount_Creditted 
    varchar(10) default '0',amount_Payed varchar(10) default '0',amount_Balance varchar(10) default '0');
    create table shortage(serial_no int primary key, item_id int not null, item_name varchar(100) not null);
*/