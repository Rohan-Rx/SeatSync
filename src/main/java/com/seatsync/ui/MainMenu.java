package com.seatsync.ui;

import com.seatsync.model.Admin;

import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {
    Scanner sc = new Scanner(System.in);
    public void start() throws SQLException {
        while(true){
            System.out.println("\n=================================================");
            System.out.println("SEATSYNC");
            System.out.println("Event Ticket Reservation System");
            System.out.println("====================================================");
            System.out.println("1.Admin Login");
            System.out.println("2.User Login");
            System.out.println("3.User Registration");
            System.out.println("4.Exit");
            System.out.println("Enter Choice:");
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    AdminMenu menu =new AdminMenu();
                    menu.login();
                    break;
                case 2:
                    System.out.println("User Comming soon");
                    break;
                case 3:
                    System.out.println("User Registration Comming soon");
                    break;
                case 4:
                    System.out.println("Thank you for Choosing Seat Sync");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
