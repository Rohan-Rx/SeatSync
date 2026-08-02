package com.seatsync.model;

public class Seat {
    private int seat_id;
    private int event_id;
    private String seat_number;
    private String status;

    public Seat() {
    }

    public Seat(int seat_id, int event_id, String seat_number, String status) {
        this.seat_id = seat_id;
        this.event_id = event_id;
        this.seat_number = seat_number;
        this.status = status;
    }

    public int getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(String seat_number) {
        this.seat_number = seat_number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seat_id=" + seat_id +
                ", event_id=" + event_id +
                ", seat_number='" + seat_number + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
