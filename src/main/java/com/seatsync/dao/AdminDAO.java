package com.seatsync.dao;
import com.seatsync.database.db;
import com.seatsync.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAO {
    public Admin login(String username,String password){
        String sql ="select * from admins where username=? AND password =?";
        try(Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Admin(rs.getInt("admin_id"),
                        rs.getString("username"),
                        rs.getString("password")
                        );
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
