package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtill {
    public static <T>T execute(String sql, Object... strings ) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement ptsm = connection.prepareStatement(sql);

        for(int i=0; i < strings.length; i++){
            ptsm.setObject((i+1),strings[i]);
        }
        if(sql.startsWith("SELECT") || sql.startsWith("select")){
            return (T)ptsm.executeQuery();
        } else {
            return (T)(Boolean)(ptsm.executeUpdate() > 0);
        }
    }
}
