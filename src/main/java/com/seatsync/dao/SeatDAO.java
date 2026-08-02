package com.seatsync.dao;

import com.seatsync.database.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
