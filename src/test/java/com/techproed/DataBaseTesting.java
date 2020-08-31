package com.techproed;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class DataBaseTesting {
/*
1. Database baglanma
connection=DriverManager.getConnection(“url”, “kullaniciAdi”, “sifre”);
2.Query gonderip data alma
statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
3. Bu dataları test caselerde kullanıp assertion yapma.  ResultSet objesiyle datayı kullanırız.
resultSet = statement.executeQuery("SELECT * FROM Book;”);
String beklenenDeger = resultSet.getString(“BookName”);
Assert.assertEquals(beklenenDeger, gercekDeger);
 */


    String url = "jdbc:mysql://107.182.225.121:3306/LibraryMgmt";
    String username = "techpro";
    String password = "tchpr2020";

    //Connection, Statement, ResultSet objelerini olusturalim

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @Before
    public void setUp() throws SQLException {

        connection= DriverManager.getConnection(url,username,password);
        statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

    }

    @Test
    public void Test1() throws SQLException {
        resultSet=statement.executeQuery("SELECT * FROM Book;");
        resultSet.next();
        String deger1=resultSet.getString("BookName");
        System.out.println("DEGER1: "+deger1);

        //TC_02_BookName deki tum degerleri yazdir
        int rowSayisi=1;
        while (resultSet.next()){
            Object deger2=resultSet.getObject("BookName");
            System.out.println(deger2);
            rowSayisi++;
        }


        //TC_03_toplam kac 14 satirin olup olmadigini test et
        System.out.println(rowSayisi);
        Assert.assertEquals(14,rowSayisi);

        //TC_04_5. degerin JAVA olup olmadigini test et
        resultSet.absolute(5);
        String deger5=resultSet.getString("BookName");
        Assert.assertEquals("JAVA",deger5);
    }

    @Test
    public void Test2() throws SQLException {
        resultSet=statement.executeQuery("SELECT * FROM Book;");
        //TC_05_son degerin UIPath olup olmadigini test et
        resultSet.last();
        String degerSon=resultSet.getString("BookName");
        Assert.assertEquals("UIPath",degerSon);

        //TC_06_ilk satirdaki degerin SQL olup olmadigii test et
        resultSet.first();
        String actualResult=resultSet.getString("BookName");
        String expectedResult="SQL";
        Assert.assertEquals(expectedResult,actualResult);
        }

        @Test
    public void Test3() throws SQLException {

        //MetaData: Datayla ilgili bilgiler
            DatabaseMetaData dbaseMetaData=connection.getMetaData();
            System.out.println("USERNAME: "+dbaseMetaData.getUserName());
            System.out.println("Database Name: "+dbaseMetaData.getDatabaseProductName());
            System.out.println("Database Version: "+dbaseMetaData.getDatabaseProductVersion());

        }

}
