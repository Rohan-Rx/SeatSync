package com.seatsync.ui;
import com.seatsync.model.User;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;
import com.seatsync.dao.UserDAO;
import com.seatsync.dao.SeatDAO;
import com.seatsync.model.Seat;
import com.seatsync.dao.EventDAO;
import com.seatsync.dao.BookingDAO;
import com.seatsync.model.Event;

public class UserMenu {
    Scanner sc = new Scanner(System.in);
    public void registerUser() throws SQLException {
        System.out.println("USER REGISTRATION");
        User user = new User();
        System.out.print("Full Name: ");
        user.setName(sc.nextLine());

        System.out.print("Email: ");
        user.setEmail(sc.nextLine());

        System.out.print("Phone: ");
        user.setPhone(sc.nextLine());

        System.out.print("Password: ");
        user.setPassword(sc.nextLine());

        user.setCreated_at(new Timestamp(System.currentTimeMillis()));

        if (UserDAO.registerUser(user)) {
            System.out.println("\n Registration Successful!");
        } else {
            System.out.println("\n Registration Failed!");
        }
    }
    public void login() throws SQLException {

        sc.nextLine();

        System.out.println("\n=========== USER LOGIN ===========");

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        User user = UserDAO.loginUser(email,password);

        if (user != null) {

            System.out.println("\nWelcome " + user.getName());

            userDashboard(user);

        } else {

            System.out.println("\nInvalid Email or Password!");

        }

    }
    private void userDashboard(User user) throws SQLException {

        while (true) {

            System.out.println("\n========== USER MENU ==========");
            System.out.println("1. View Events");
            System.out.println("2. View Available Seats");
            System.out.println("3. Book Ticket");
            System.out.println("4. Booking History");
            System.out.println("5. Cancel Ticket");
            System.out.println("6. Logout");

            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewEvents();
                    break;

                case 2:
                    viewAvailableSeats();
                    break;

                case 3:
                    bookTicket(user);
                    break;

                case 4:
                    System.out.println("Booking History Module");
                    break;

                case 5:
                    System.out.println("Cancel Ticket Module");
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid Choice!");

            }

        }

    }
    private void viewAvailableSeats() {

        System.out.print("\nEnter Event ID: ");
        int eventId = sc.nextInt();

        SeatDAO seatDAO = new SeatDAO();

        java.util.List<Seat> seats = seatDAO.getAvailableSeats(eventId);

        if (seats.isEmpty()) {

            System.out.println("\nNo Available Seats!");

            return;
        }

        System.out.println("\n========== AVAILABLE SEATS ==========");

        int count = 0;

        for (Seat seat : seats) {

            System.out.printf("%-6s", seat.getSeat_number());

            count++;

            if (count % 10 == 0) {
                System.out.println();
            }
        }

        System.out.println();
    }
    private void bookTicket(User user) throws SQLException {

        EventDAO eventDAO = new EventDAO();
        SeatDAO seatDAO = new SeatDAO();
        BookingDAO bookingDAO = new BookingDAO();

        System.out.println("\n========== BOOK TICKET ==========");

        // Show Events
        eventDAO.getAllEvents();

        System.out.print("\nEnter Event ID: ");
        int eventId = sc.nextInt();
        sc.nextLine();

        // Show Available Seats
        List<Seat> seats = seatDAO.getAvailableSeats(eventId);

        if (seats.isEmpty()) {
            System.out.println("No seats available.");
            return;
        }

        System.out.println("\nAvailable Seats:");

        for (Seat seat : seats) {
            System.out.print(seat.getSeat_number() + " ");
        }

        System.out.print("\n\nEnter Seat Number: ");
        String seatNumber = sc.nextLine().toUpperCase();

        boolean booked = bookingDAO.bookTicket(
                user.getUser_id(),
                eventId,
                seatNumber
        );

        if (booked) {

            System.out.println("\nTicket Booked Successfully!");

        } else {

            System.out.println("\nBooking Failed!");

        }

    }
    private void viewEvents() {

        EventDAO eventDAO = new EventDAO();

        try {

            List<Event> events = eventDAO.getAllEvents();

            if (events.isEmpty()) {
                System.out.println("\nNo Events Available.");
                return;
            }

            System.out.println("\n================ AVAILABLE EVENTS ================");

            for (Event event : events) {

                System.out.println("-------------------------------------------");
                System.out.println("Event ID      : " + event.getEvent_id());
                System.out.println("Event Name    : " + event.getEvent_name());
                System.out.println("Venue         : " + event.getVenue());
                System.out.println("Date          : " + event.getEvent_date());
                System.out.println("Time          : " + event.getEvent_time());
                System.out.println("Ticket Price  : ₹" + event.getTicket_price());
                System.out.println("Total Seats   : " + event.getTotal_seats());

            }

            System.out.println("-------------------------------------------");

        } catch (SQLException e) {

            System.out.println("Unable to fetch events.");
            e.printStackTrace();

        }

    }
}
