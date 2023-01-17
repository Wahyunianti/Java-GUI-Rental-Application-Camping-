/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author wahyu
 */
public class ClassValidasi {

    String username;
    String password;

    Connection conn;
    Statement st;

    public String Login2(String username, String password) {
        String level = null;
        String sql = "SELECT * FROM user WHERE username = '" + username + "' AND password = '" + password + "'";
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                level = rs.getString("level");
            } else {
                JOptionPane.showMessageDialog(null, "Username atau Password Salah !");
            }
        } catch (Exception ex) {
            Logger.getLogger(ClassValidasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return level;
    }

}
