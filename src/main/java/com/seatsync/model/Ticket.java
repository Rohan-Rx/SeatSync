package com.seatsync.model;

public class Ticket {
    private int ticketId;
    private int bookingId;
    private int seatId;
    private String ticketCode;

    public Ticket() {
    }

    public Ticket(int ticketId, int bookingId, int seatId, String ticketCode) {
        this.ticketId = ticketId;
        this.bookingId = bookingId;
        this.seatId = seatId;
        this.ticketCode = ticketCode;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", bookingId=" + bookingId +
                ", seatId=" + seatId +
                ", ticketCode='" + ticketCode + '\'' +
                '}';
    }
}
