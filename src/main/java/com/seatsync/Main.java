package com.seatsync;

import com.seatsync.dao.EventDAO;
import com.seatsync.dao.SeatDAO;
import com.seatsync.model.Event;
import com.seatsync.ui.MainMenu;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) throws SQLException {
        MainMenu menu = new MainMenu();
        menu.start();

        Event event = new Event();
        event.setEvent_name("Tech fest 2026");
        event.setVenue("Main Auditorium");
        event.setEvent_date(LocalDate.of(2026,9,15));
        event.setEvent_time(LocalTime.of(18,0));
        event.setTicket_price(500.0);
        event.setTotal_seats(100);
        EventDAO eventDAO = new EventDAO();
        SeatDAO seatDAO = new SeatDAO();

        int eventId =eventDAO.addEvent(event);
        if(eventId >0){
            seatDAO.generateSeats(eventId,event.getTotal_seats());
            System.out.println("Event Added");
            System.out.println("Seats Generated");

        }
        else{
            System.out.println("Event Creation Failed!");
        }
    }
}
