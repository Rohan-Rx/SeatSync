package com.seatsync.model;

import java.sql.Time;
import java.sql.Timestamp;

public class Booking {
    private int booking_id;
    private int user_id;
    private int event_id;
    private Timestamp bookingDate;
    private double totalAmount;

    public Booking() {
    }

    public Booking(int booking_id, int user_id, int event_id, Timestamp bookingDate, double totalAmount) {
        this.booking_id = booking_id;
        this.user_id = user_id;
        this.event_id = event_id;
        this.bookingDate = bookingDate;
        this.totalAmount = totalAmount;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public Timestamp getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "booking_id=" + booking_id +
                ", user_id=" + user_id +
                ", event_id=" + event_id +
                ", bookingDate=" + bookingDate +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
