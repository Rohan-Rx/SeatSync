package com.seatsync.dao;

import com.seatsync.database.db;
import com.seatsync.model.Seat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//Batch Processing
public class SeatDAO {
    public boolean generateSeats(int event_id,int totalSeats){
        String sql ="Insert into seats(event_id,seat_number,status)values(?,?,?)";
        try(Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            int seatsPerRow =10;
            for(int i=0; i<totalSeats; i++){
                char row =(char) ('A'+(i/seatsPerRow));
                int seatNo = (i % seatsPerRow)+1;
            String seatNumber = row + String.valueOf(seatNo);
            ps.setInt(1,event_id);
            ps.setString(2,seatNumber);
            ps.setString(3,"AVAILABLE");
            ps.addBatch();
            }
            ps.executeBatch();
            return true;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Seat> getAvailableSeats(int eventId) {

        List<Seat> seatList = new ArrayList<>();

        String sql = "SELECT * FROM seats WHERE event_id=? AND status='AVAILABLE'";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, eventId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Seat seat = new Seat();

                seat.setSeat_id(rs.getInt("seat_id"));
                seat.setEvent_id(rs.getInt("event_id"));
                seat.setSeat_number(rs.getString("seat_number"));
                seat.setStatus(rs.getString("status"));

                seatList.add(seat);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return seatList;
    }
}
