//Executed Prepared Statement
package com.seatsync.dao;
import com.seatsync.model.user;
import com.seatsync.database.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.seatsync.database.db.*;

public class UserDAO {
    public boolean registerUser(user user) throws SQLException {
        String sql = "Inser into users(name,email,phone,password) values(?,?,?,?)";
        try (Connection con = db.getConnection();             //Automatically closes connection when block ends
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setInt(3, user.getPhone());
            ps.setString(4, user.getPassword());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }


        return false;
    }

    //Login User
    public user loginUser(String email, String password) throws SQLException {
        String sql = "SELECT * from users where email=? AND password=?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new user(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("phone"),
                        rs.getString("password"),
                        rs.getTimestamp("created_at")
                );
            }

        }
        return null;
    }
}
