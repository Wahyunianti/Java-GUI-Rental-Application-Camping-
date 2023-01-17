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
public class ClassBarang {

    private int ndata = 0;
    private Object[][] data;

    private Connection conn;
    private Statement st;

    public int getNumberDataBarang() {
        return ndata;
    }

    public Object[][] getAllDataBarang() {
        return data;
    }

    public void getDataBarang() {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "Select count(*) from barang";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ndata = rs.getInt("COUNT(*)");
            }
            sql = "Select * from barang";
            rs = st.executeQuery(sql);
            data = new Object[ndata][5];
            int idx = 0;
            while (rs.next()) {
                data[idx][0] = rs.getString("id_alat");
                data[idx][1] = rs.getString("nama_alat");
                data[idx][2] = rs.getString("kategori");
                data[idx][3] = rs.getString("status");
                data[idx][4] = rs.getInt("harga");
                idx++;
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClassConnection.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void getDataBarang(String key) {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "Select count(*) from barang where id_alat ='" + key + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ndata = rs.getInt("COUNT(*)");
            }
            sql = "Select * from barang where id_alat ='" + key + "'";
            rs = st.executeQuery(sql);
            data = new Object[ndata][5];
            int idx = 0;
            while (rs.next()) {
                data[idx][0] = rs.getString("id_alat");
                data[idx][1] = rs.getString("nama_alat");
                data[idx][2] = rs.getString("kategori");
                data[idx][3] = rs.getString("status");
                data[idx][4] = rs.getInt("harga");
                idx++;
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClassConnection.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void getIdBarang(String key) {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "Select count(*) from barang where nama_alat ='" + key + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ndata = rs.getInt("COUNT(*)");
            }
            sql = "Select * from barang where nama_alat ='" + key + "'";
            rs = st.executeQuery(sql);
            data = new Object[ndata][5];
            int idx = 0;
            while (rs.next()) {
                data[idx][0] = rs.getString("id_alat");
                data[idx][1] = rs.getString("nama_alat");
                data[idx][2] = rs.getString("kategori");
                data[idx][3] = rs.getString("status");
                data[idx][4] = rs.getInt("harga");
                idx++;
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClassConnection.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void UpdateStatus(String idtrx, String status) {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "UPDATE barang, transaksi SET barang.status =? WHERE transaksi.idtrx=? AND barang.id_alat=transaksi.id_alat";
            try ( PreparedStatement p = conn.prepareStatement(sql)) {
                p.setString(1, status);
                p.setString(2, idtrx);
                p.executeUpdate();
                p.close();
                System.out.println("Sukses update data...");
            }
        } catch (SQLException ex) {

        }
    }

    public void UpdateStatus2(String idalat, String status) {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "UPDATE barang SET status =? WHERE id_alat=?";
            try ( PreparedStatement p = conn.prepareStatement(sql)) {
                p.setString(1, status);
                p.setString(2, idalat);
                p.executeUpdate();
                p.close();
                System.out.println("Sukses update data...");
            }
        } catch (SQLException ex) {

        }
    }

    public void getDataTersedia(String key) {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "Select count(*) from barang where status = '" + key + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ndata = rs.getInt("COUNT(*)");
            }
            sql = "Select * from barang where status = '" + key + "'";
            rs = st.executeQuery(sql);
            data = new Object[ndata][5];
            int idx = 0;
            while (rs.next()) {
                data[idx][0] = rs.getString("id_alat");
                data[idx][1] = rs.getString("nama_alat");
                data[idx][2] = rs.getString("kategori");
                data[idx][3] = rs.getString("status");
                data[idx][4] = rs.getInt("harga");
                idx++;
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClassConnection.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void InsertBarang(String id, String nama, String kategori, String status, int harga) {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "INSERT INTO barang VALUES(?, ?, ?, ?, ?)";
            try ( PreparedStatement p = (PreparedStatement) conn.prepareStatement(sql)) {
                p.setString(1, id);
                p.setString(2, nama);
                p.setString(3, kategori);
                p.setString(4, status);
                p.setInt(5, harga);
                p.execute();
                System.out.println("Sukses menambah data...");
            }
        } catch (SQLException ex) {

        }
    }

    public void UpdateBarang(String id, String nama, String kategori, String status, int harga) {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "UPDATE barang SET nama_alat=?, kategori=?, status=?, harga=? WHERE id_alat=?";
            try ( PreparedStatement p = conn.prepareStatement(sql)) {
                p.setString(1, nama);
                p.setString(2, kategori);
                p.setString(3, status);
                p.setInt(4, harga);
                p.setString(5, id);
                p.executeUpdate();
                p.close();
                System.out.println("Sukses update data...");
            }
        } catch (SQLException ex) {

        }
    }

    public void DeleteBarang(String id) {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "DELETE FROM barang WHERE id_alat=?";
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
