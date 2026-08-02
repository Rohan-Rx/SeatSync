package com.seatsync.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

public class Event {
    private int event_id;
    private String event_name;
    private String venue;
    private LocalDate event_date;
    private LocalTime event_time;
    private double ticket_price;
    private  int total_seats;

    public Event() {
    }

    public Event(int event_id, String event_name, LocalDate event_date, LocalTime event_time, double ticket_price, int total_seats) {
        this.event_id = event_id;
        this.event_name = event_name;
        this.event_date = event_date;
        this.event_time = event_time;
        this.ticket_price = ticket_price;
        this.total_seats = total_seats;
    }

    public Event(int event_id, String event_name, String venue, LocalDate event_date, LocalTime event_time, double ticket_price, int total_seats) {
        this.event_id = event_id;
        this.event_name = event_name;
        this.venue = venue;
        this.event_date = event_date;
        this.event_time = event_time;
        this.ticket_price = ticket_price;
        this.total_seats = total_seats;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public LocalDate getEvent_date() {
        return event_date;
    }

    public void setEvent_date(LocalDate event_date) {
        this.event_date = event_date;
    }

    public LocalTime getEvent_time() {
        return event_time;
    }

    public void setEvent_time(LocalTime event_time) {
        this.event_time = event_time;
    }

    public double getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(double ticket_price) {
        this.ticket_price = ticket_price;
    }

    public int getTotal_seats() {
        return total_seats;
    }

    public void setTotal_seats(int total_seats) {
        this.total_seats = total_seats;
    }

    @Override
    public String toString() {
        return "Event{" +
                "event_id=" + event_id +
                ", event_name='" + event_name + '\'' +
                ", venue='" + venue + '\'' +
                ", event_date=" + event_date +
                ", event_time=" + event_time +
                ", ticket_price=" + ticket_price +
                ", total_seats=" + total_seats +
                '}';
    }
}
