package com.seatsync.ui;

import com.seatsync.dao.AdminDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import com.seatsync.dao.EventDAO;
import com.seatsync.dao.SeatDAO;
import com.seatsync.model.Admin;
import com.seatsync.model.Event;

public class AdminMenu {
   Scanner sc = new Scanner(System.in);

   public void login() throws SQLException {
       System.out.println("\n===========ADMIN LOGIN===========================");
       System.out.println("Username: ");
       String username = sc.next();

       System.out.println("Password: ");
       String password = sc.next();

       AdminDAO dao = new AdminDAO();
       Admin admin = dao.login(username,password);

       if(admin != null){
           System.out.println("\nLogin Successful");
           adminDashboard();
       }
       else{
           System.out.println("\nInvalid Username or Password");
       }
   }
   private void adminDashboard() throws SQLException {
       while(true){
           System.out.println("\n==============ADMIN MENU===================");
           System.out.println("1.Add Event");
           System.out.println("2.View Event");
           System.out.println("3.Update Event");
           System.out.println("4.Delete Event");
           System.out.println("5.Logout");
           System.out.println("Enter Choice:");
           int choice =sc.nextInt();
           switch (choice){
               case 1:
                   addEvent();
                   break;
               case 2:
                   viewEvents();
                   break;
               case 3:
                   updateEvent();
                   break;
               case 4:
                   deleteEvent();
                   break;
               case 5:
                   System.out.println("Logged Out");
                   System.exit(0);
               default:
                   System.out.println("Invalid Choice!");
           }
       }
   }
   private void addEvent(){
       sc.nextLine();
       System.out.println("\n=============ADD EVENT==================");
       System.out.println("Enter Event name:");
       String eventname = sc.nextLine();
       System.out.println("Enter Event Venue:");
       String eventvenue = sc.nextLine();
       System.out.println("Enter Event Date:");
       LocalDate eventdate = LocalDate.parse(sc.nextLine());
       System.out.println("Enter Event Time:");
       LocalTime eventtime = LocalTime.parse(sc.nextLine());
       System.out.println("Enter Ticket Price:");
       int price = sc.nextInt();
       System.out.println("Enter Total Seats:");
       int seats = sc.nextInt();

       Event event = new Event();
       event.setEvent_name(eventname);
       event.setVenue(eventvenue);
       event.setEvent_date(eventdate);
       event.setEvent_time(eventtime);
       event.setTicket_price(price);
       event.setTotal_seats(seats);

       EventDAO eventDAO = new EventDAO();
       SeatDAO seatDAO = new SeatDAO();
       int eventId = eventDAO.addEvent(event);

       if(eventId > 0){
           boolean generated = seatDAO.generateSeats(eventId,seats);

           if(generated){
               System.out.println("\n Event Added Successfully");
               System.out.println("Seats Generated Successfully");
           }
           else{
               System.out.println("Added Event but seat Generation Failed");
           }
       }
       else{
           System.out.println("Event creation Failed");
       }




   }
   private void viewEvents() throws SQLException {
       EventDAO eventDAO = new EventDAO();
       List<Event> events = eventDAO.getAllEvents();

       if(events.isEmpty()){
           System.out.println("\n No Events Found");
           return;
       }
       System.out.println("============================================================================================");
       System.out.printf("%-5s %-20s %-20s %-12s %-8s %-10s %-8s%n","ID","EVENT","VENUE","DATE","TIME","PRICE","SEATS");
       System.out.println("============================================================================================");
       for(Event event: events){
           System.out.printf("%-5s %-20s %-20s %-12s %-8s %-10s %-8s%n",
                   event.getEvent_id(),event.getEvent_name(),event.getVenue(),event.getEvent_date(),event.getEvent_time(),
                    event.getTicket_price(),event.getTotal_seats());
       }
       System.out.println("=============================================================================================");
   }

    private void updateEvent() {

        System.out.println("\n========== UPDATE EVENT ==========");

        System.out.print("Enter Event ID: ");
        int eventId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter New Event Name: ");
        String eventName = sc.nextLine();

        System.out.print("Enter New Venue: ");
        String venue = sc.nextLine();

        System.out.print("Enter New Date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(sc.nextLine());

        System.out.print("Enter New Time (HH:mm): ");
        LocalTime time = LocalTime.parse(sc.nextLine());

        System.out.print("Enter Ticket Price: ");
        double price = sc.nextDouble();

        System.out.print("Enter Total Seats: ");
        int seats = sc.nextInt();

        Event event = new Event();

        event.setEvent_id(eventId);
        event.setEvent_name(eventName);
        event.setVenue(venue);
        event.setEvent_date(date);
        event.setEvent_time(time);
        event.setTicket_price(price);
        event.setTotal_seats(seats);

        EventDAO dao = new EventDAO();

        if (dao.updateEvent(event)) {
            System.out.println("\n Event Updated Successfully.");
        } else {
            System.out.println("\n Event Update Failed.");
        }
    }
    private void deleteEvent() {

        System.out.println("\n========== DELETE EVENT ==========");

        System.out.print("Enter Event ID: ");
        int eventId = sc.nextInt();

        EventDAO dao = new EventDAO();

        if (dao.deleteEvent(eventId)) {
            System.out.println("\n Event Deleted Successfully.");
        } else {
            System.out.println("\n Event Not Found.");
        }
    }
}
