//Executed Prepared Statement
package com.seatsync.dao;
import com.seatsync.model.User;
import com.seatsync.database.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public static boolean registerUser(User user) throws SQLException {
        String sql = "Insert into users(name,email,phone,password) values(?,?,?,?)";
        try (Connection con = db.getConnection();             //Automatically closes connection when block ends
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getPassword());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }


        return false;
    }

    //Login User
    public static User loginUser(String email, String password) throws SQLException {
        String sql = "SELECT * from users where email=? AND password=?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("password"),
                        rs.getTimestamp("created_at")
                );
            }

        }
        return null;
    }
}
