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
public class ClassPenyewaan {

    private int ndata = 0;
    private Object[][] data;

    private Connection conn;
    private Statement st;

    public int getNumberDataPenyewaan() {
        return ndata;
    }

    public Object[][] getAllDataPenyewaan() {
        return data;
    }

    public void InsertPenyewaan(String id, String nama, String tglk, int harga, int delay, int denda, int total) {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "INSERT INTO penyewaan (id_trx, nama_penyewa, tgl_kembali, harga_hitung, delay, denda, total_inv) VALUES(?, ?, ?, ?, ?, ?, ?)";
            try ( PreparedStatement p = (PreparedStatement) conn.prepareStatement(sql)) {
                p.setString(1, id);
                p.setString(2, nama);
                p.setString(3, tglk);
                p.setInt(4, harga);
                p.setInt(5, delay);
                p.setInt(6, denda);
                p.setInt(7, total);
                p.execute();
                System.out.println("Sukses menambah data...");
            }
        } catch (SQLException ex) {

        }
    }

    public void getDataSum() {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "Select sum(total_inv) from penyewaan";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ndata = rs.getInt("SUM(total_inv)");
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClassConnection.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void getDataTersedia() {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "Select COUNT(id_alat) from barang where status='Tersedia'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ndata = rs.getInt("COUNT(id_alat)");
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClassConnection.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void getDataDisewa() {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "Select COUNT(id_alat) from barang where status='Disewa'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ndata = rs.getInt("COUNT(id_alat)");
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClassConnection.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
