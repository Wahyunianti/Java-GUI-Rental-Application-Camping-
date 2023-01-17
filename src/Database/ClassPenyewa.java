/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wahyu
 */
public class ClassPenyewa {

    private int ndata = 0;
    private Object[][] data;

    private Connection conn;
    private Statement st;

    public int getNumberDataPenyewa() {
        return ndata;
    }

    public Object[][] getAllDataPenyewa() {
        return data;
    }

    public void getDataPenyewa() {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "Select count(*) from penyewa";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ndata = rs.getInt("COUNT(*)");
            }
            sql = "Select * from penyewa";
            rs = st.executeQuery(sql);
            data = new Object[ndata][5];
            int idx = 0;
            while (rs.next()) {
                data[idx][0] = rs.getInt("id_penyewa");
                data[idx][1] = rs.getString("nama_penyewa");
                data[idx][2] = rs.getString("no_telp");
                data[idx][3] = rs.getString("alamat");
                data[idx][4] = rs.getString("jaminan");
                idx++;
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClassConnection.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void InsertPenyewa(String nama, String notelp, String alamat, String jaminan) {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "INSERT INTO penyewa (nama_penyewa, no_telp, alamat, jaminan) VALUES(?,?,?,?)";
            try ( PreparedStatement p = (PreparedStatement) conn.prepareStatement(sql)) {
                p.setString(1, nama);
                p.setString(2, notelp);
                p.setString(3, alamat);
                p.setString(4, jaminan);
                p.execute();
                System.out.println("Sukses menambah data...");
            }
        } catch (SQLException ex) {

        }
    }

    public void UpdatePenyewa(String id, String nama, String notelp, String alamat, String jaminan) {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "UPDATE penyewa SET nama_penyewa=?, no_telp=?, alamat=?, jaminan=? WHERE id_penyewa=?";
            try ( PreparedStatement p = conn.prepareStatement(sql)) {
                p.setString(1, nama);
                p.setString(2, notelp);
                p.setString(3, alamat);
                p.setString(4, jaminan);
                p.setString(5, id);
                p.executeUpdate();
                p.close();
                System.out.println("Sukses update data...");
            }
        } catch (SQLException ex) {

        }
    }

    public void DeletePenyewa(String id) {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "DELETE FROM penyewa WHERE id_penyewa=?";
            try ( PreparedStatement p = conn.prepareStatement(sql)) {
                p.setString(1, id);
                p.executeUpdate();
                System.out.println("Sukses menghapus data...");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassConnection.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

}
