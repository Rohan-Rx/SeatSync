package com.seatsync.dao;
import com.seatsync.database.db;
import com.seatsync.model.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {
    public int addEvent(Event event){
        String sql ="Insert into events(event_name,venue,event_date,event_time,ticket_price,total_seats)" +
                "values(?,?,?,?,?,?)";
        try(Connection con = db.getConnection();
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, event.getEvent_name());
            ps.setString(2, event.getVenue());
            ps.setDate(3, Date.valueOf(event.getEvent_date()));
            ps.setTime(4, Time.valueOf(event.getEvent_time()));
            ps.setDouble(5,event.getTicket_price());
            ps.setInt(6,event.getTotal_seats());
            ps.executeUpdate();
            ResultSet rs =ps.getGeneratedKeys();
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return -1;
    }
    public List<Event> getAllEvents() throws SQLException {
        List<Event> events = new ArrayList<>();
        String sql = "Select * from events";

        try(Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                Event event = new Event(
                        rs.getInt("event_id"),
                        rs.getString("event_name"),
                        rs.getDate("event_date").toLocalDate(),
                        rs.getTime("event_time").toLocalTime(),
                        rs.getDouble("ticket_price"),
                        rs.getInt("total_seats")
                );
                events.add(event);
            }


        }
        return events;
    }

    //Update Events
    public boolean updateEvent(Event event) {
        String sql ="UPDATE events set event_name=?,venue=?,event_date=?,event_time=?,ticket_price=?,total_seats=? where event_id=?";
        try(Connection con = db.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1,event.getEvent_name());
            ps.setString(2,event.getVenue());
            ps.setDate(3,Date.valueOf(event.getEvent_date()));
            ps.setTime(4,Time.valueOf(event.getEvent_time()));
            ps.setDouble(5,event.getTicket_price());
            ps.setInt(6,event.getTotal_seats());
            ps.setInt(7,event.getEvent_id());
            return ps.executeUpdate() >0;
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    //Delete Event
    public boolean deleteEvent(int event_id){
        String sql = "DELETE from events where event_id=?";
        try(Connection con = db.getConnection();
        PreparedStatement ps =con.prepareStatement(sql)){
            ps.setInt(1,event_id);
            return ps.executeUpdate() >0;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

}
