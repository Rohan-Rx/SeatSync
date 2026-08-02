package com.seatsync.dao;

import java.sql.*;
import java.util.Random;

import com.seatsync.database.db;
public class BookingDAO {
    public boolean bookTicket(int userId, int eventId,String seatNumber) throws SQLException {

        try(Connection con=db.getConnection();){
        //Transaction part
            con.setAutoCommit(false);
            String checkSeat =
                    "SELECT seat_id FROM seats WHERE event_id=? AND seat_number=? AND status='AVAILABLE'";

            PreparedStatement psCheck = con.prepareStatement(checkSeat);

            psCheck.setInt(1, eventId);
            psCheck.setString(2, seatNumber);

            ResultSet rs = psCheck.executeQuery();

            if (!rs.next()) {

                System.out.println("Seat is already booked.");

                return false;
            }
            int seatId = rs.getInt("seat_id");
            String updateSeat =
                    "UPDATE seats SET status=? WHERE seat_id=?";

            PreparedStatement psSeat = con.prepareStatement(updateSeat);

            psSeat.setString(1, "BOOKED");
            psSeat.setInt(2, seatId);

            int seatUpdated = psSeat.executeUpdate();

            if (seatUpdated == 0) {

                con.rollback();

                System.out.println("Seat update failed.");

                return false;

            }

            //Insert Booking
            String bookingSql =
                    "INSERT INTO bookings(user_id, event_id, total_amount) VALUES(?,?,?)";

            PreparedStatement psBooking =
                    con.prepareStatement(bookingSql, Statement.RETURN_GENERATED_KEYS);

            psBooking.setInt(1, userId);
            psBooking.setInt(2, eventId);
            double eventPrice = 500.0;
            psBooking.setDouble(3, eventPrice);

            psBooking.executeUpdate();
            //Get Booking ID
            ResultSet rsBooking = psBooking.getGeneratedKeys();

            if (!rsBooking.next()) {
                con.rollback();
                return false;
            }

            int bookingId = rsBooking.getInt(1);

            //Insert Payment
            String paymentSql =
                    "INSERT INTO payments(booking_id, payment_method, payment_status) VALUES(?,?,?)";

            PreparedStatement psPayment =
                    con.prepareStatement(paymentSql);

            psPayment.setInt(1, bookingId);
            psPayment.setString(2, "UPI");
            psPayment.setString(3, "SUCCESS");

            psPayment.executeUpdate();
            //Generate Ticket Number
            String ticketCode =
                    "SS-" + eventId + "-" + seatNumber + "-" + (1000 + new Random().nextInt(9000));

            String ticketSql =
                    "INSERT INTO tickets(booking_id, seat_id, ticket_code) VALUES (?, ?, ?)";

            PreparedStatement psTicket = con.prepareStatement(ticketSql);

            psTicket.setInt(1, bookingId);
            psTicket.setInt(2, seatId);
            psTicket.setString(3, ticketCode);

            psTicket.executeUpdate();
            con.commit();

            System.out.println("\n====================================");
            System.out.println(" Booking Successful");
            System.out.println(" Ticket Code : " + ticketCode);
            System.out.println("====================================");

            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
