/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wahyu
 */
public class ClassUser {

    private int ndata = 0;
    private Object[][] data;

    private Connection conn;
    private Statement st;

    public int getNumberDataUser() {
        return ndata;
    }

    public Object[][] getAllDataUser() {
        return data;
    }

    public void getDataUser(String key) {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "Select count(*) from user where username='" + key + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ndata = rs.getInt("COUNT(*)");
            }
            sql = "Select * from user where username='" + key + "'";
            System.out.println(sql);
            rs = st.executeQuery(sql);
            data = new Object[ndata][4];
            int idx = 0;
            while (rs.next()) {
                data[idx][0] = rs.getString("nama");
                data[idx][1] = rs.getString("username");
                data[idx][2] = rs.getString("password");
                data[idx][3] = rs.getString("level");
                idx++;
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClassConnection.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void InsertUser(String nama, String username, String password, String level) {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "INSERT INTO user VALUES(?, ?, ?, ?)";
            try ( PreparedStatement p = (PreparedStatement) conn.prepareStatement(sql)) {
                p.setString(1, nama);
                p.setString(2, username);
                p.setString(3, password);
                p.setString(4, level);
                p.execute();
                System.out.println("Sukses menambah data...");
            }
        } catch (SQLException ex) {

        }
    }

}
