package com.seatsync.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db  {
    private static final String URL="jdbc:mysql://localhost:3306/seatsync" ;
    private static final String USER="root";
    private static final String PASS = "Rohan@123";

   Connection con;

    {
        try {
            con = DriverManager.getConnection(URL,USER,PASS);
            System.out.println("Connection Established");
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            throw new RuntimeException(e);
        }
    }



}
