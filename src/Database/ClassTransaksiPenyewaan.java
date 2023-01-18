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
public class ClassTransaksiPenyewaan {

    String tanggal;
    String petugas;
    String idtrx;
    String idalat;
    String namaPyw;
    String namaBrg;
    String tgs;
    String tgk;
    String lama;
    String hrgHarian;
    String hrgSubTotal;

    private int ndata = 0;
    private Object[][] data;

    private Connection conn;
    private Statement st;

    public int getNumberTrxPenyewaan() {
        return ndata;
    }

    public Object[][] getAllDataTrxPenyewaan() {
        return data;
    }

    public void getDataPengembalian2() {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "Select count(*) from penyewaan";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ndata = rs.getInt("COUNT(*)");
            }
            sql = "Select id_trx, SUBSTRING(id_trx,5,2) * 1 AS urut , nama_penyewa, tgl_kembali, denda, total_inv from penyewaan ORDER BY urut+0";
            rs = st.executeQuery(sql);
            data = new Object[ndata][5];
            int idx = 0;
            while (rs.next()) {
                data[idx][0] = rs.getString("id_trx");
                data[idx][1] = rs.getString("nama_penyewa");
                data[idx][2] = rs.getString("tgl_kembali");
                data[idx][3] = rs.getString("denda");
                data[idx][4] = rs.getString("total_inv");
                idx++;
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClassConnection.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void getDataPenyewaan2() {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "Select count(distinct idtrx) from transaksi";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ndata = rs.getInt("COUNT(distinct idtrx)");
            }
            sql = "Select idtrx, SUBSTRING(idtrx,5,2) * 1 AS urut , nama_penyewa, tgl_sewa, tgl_kembali, SUM(harga_subtotal) as pemasukan from transaksi group by idtrx ORDER BY urut+0";
            rs = st.executeQuery(sql);
            data = new Object[ndata][5];
            int idx = 0;
            while (rs.next()) {
                data[idx][0] = rs.getString("idtrx");
                data[idx][1] = rs.getString("nama_penyewa");
                data[idx][2] = rs.getString("tgl_sewa");
                data[idx][3] = rs.getString("tgl_kembali");
                data[idx][4] = rs.getInt("pemasukan");
                idx++;
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClassConnection.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void getDataTrxPenyewaan() {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "Select count(distinct idtrx) from transaksi";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ndata = rs.getInt("COUNT(distinct idtrx)");
            }
            sql = "Select tgl_dibuat, idtrx, COUNT(idtrx) AS njenis, SUM(lama) AS lama_sewa, SUM(harga_subtotal) AS pemasukan FROM transaksi GROUP BY idtrx";
            rs = st.executeQuery(sql);
            data = new Object[ndata][5];
            int idx = 0;
            while (rs.next()) {
                data[idx][0] = rs.getString("tgl_dibuat");
                data[idx][1] = rs.getString("idtrx");
                data[idx][2] = rs.getString("njenis");
                data[idx][3] = rs.getString("lama_sewa");
                data[idx][4] = rs.getString("pemasukan");
                idx++;
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClassTransaksiPenyewaan.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void getDataPengembalian(String key) {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "Select count(distinct idtrx) from transaksi WHERE status='" + key + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ndata = rs.getInt("COUNT(distinct idtrx)");
            }
            sql = "Select idtrx, nama_penyewa, tgl_sewa, SUM(harga_subtotal) AS pemasukan FROM transaksi WHERE status='" + key + "' GROUP BY idtrx";
            rs = st.executeQuery(sql);
            data = new Object[ndata][4];
            int idx = 0;
            while (rs.next()) {
                data[idx][0] = rs.getString("idtrx");
                data[idx][1] = rs.getString("nama_penyewa");
                data[idx][2] = rs.getString("tgl_sewa");
                data[idx][3] = rs.getString("pemasukan");
                idx++;
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClassTransaksiPenyewaan.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void getDataTampil(String key) {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "Select count(distinct idtrx) from transaksi WHERE idtrx='" + key + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ndata = rs.getInt("COUNT(distinct idtrx)");
            }
            sql = "Select tgl_dibuat, petugas, idtrx, id_alat, nama_penyewa, nama_barang, tgl_sewa, tgl_kembali, hari, lama, harga_harian, harga_subtotal, status FROM transaksi WHERE idtrx='" + key + "' GROUP BY idtrx";
            rs = st.executeQuery(sql);
            data = new Object[ndata][13];
            int idx = 0;
            while (rs.next()) {
                data[idx][0] = rs.getString("tgl_dibuat");
                data[idx][1] = rs.getString("petugas");
                data[idx][2] = rs.getString("idtrx");
                data[idx][3] = rs.getString("id_alat");
                data[idx][4] = rs.getString("nama_penyewa");
                data[idx][5] = rs.getString("nama_barang");
                data[idx][6] = rs.getString("tgl_sewa");
                data[idx][7] = rs.getString("tgl_kembali");
                data[idx][8] = rs.getString("hari");
                data[idx][9] = rs.getString("lama");
                data[idx][10] = rs.getString("harga_harian");
                data[idx][11] = rs.getString("harga_subtotal");
                data[idx][12] = rs.getString("status");
                idx++;
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClassTransaksiPenyewaan.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void getDataTampil2(String key) {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "Select count(*) from transaksi WHERE idtrx = '" + key + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ndata = rs.getInt("COUNT(*)");
            }
            sql = "Select idtrx, id_alat, nama_penyewa, nama_barang, tgl_sewa, tgl_kembali, lama, harga_harian, harga_subtotal FROM transaksi WHERE idtrx = '" + key + "'";
            rs = st.executeQuery(sql);
            data = new Object[ndata][9];
            int idx = 0;
            while (rs.next()) {
                data[idx][0] = rs.getString("idtrx");
                data[idx][1] = rs.getString("id_alat");
                data[idx][2] = rs.getString("nama_penyewa");
                data[idx][3] = rs.getString("nama_barang");
                data[idx][4] = rs.getString("tgl_sewa");
                data[idx][5] = rs.getString("tgl_kembali");
                data[idx][6] = rs.getString("lama");
                data[idx][7] = rs.getString("harga_harian");
                data[idx][8] = rs.getString("harga_subtotal");
                idx++;
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClassTransaksiPenyewaan.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    public void getDataTampil3(String key) {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "Select count(nama_barang) from transaksi WHERE idtrx = '" + key + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ndata = rs.getInt("COUNT(nama_barang)");
            }
            sql = "Select nama_barang FROM transaksi WHERE idtrx = '" + key + "'";
            rs = st.executeQuery(sql);
            data = new Object[ndata][1];
            int idx = 0;
            while (rs.next()) {
                data[idx][0] = rs.getString("nama_barang");
                idx++;
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClassTransaksiPenyewaan.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void InsertTrxPenyewaan(String tanggal, String petugas, String idtrx, String penyewa, String sewa, String kembali, String total, int hari, String stt, String[][] data, int n) {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            for (int i = 0; i < n; i++) {
                idalat = data[i][0];
                namaBrg = data[i][1];
                lama = data[i][2];
                hrgHarian = data[i][3];
                hrgSubTotal = data[i][4];
                String sql = "INSERT INTO transaksi VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try ( PreparedStatement p = (PreparedStatement) conn.prepareStatement(sql)) {
                    p.setString(1, tanggal);
                    p.setString(2, petugas);
                    p.setString(3, idtrx);
                    p.setString(4, idalat);
                    p.setString(5, penyewa);
                    p.setString(6, namaBrg);
                    p.setString(7, sewa);
                    p.setString(8, kembali);
                    p.setInt(9, hari);
                    p.setString(10, lama);
                    p.setString(11, hrgHarian);
                    p.setString(12, hrgSubTotal);
                    p.setString(13, stt);

                    p.execute();
                    System.out.println("Sukses menambah data...");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassTransaksiPenyewaan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void UpdateStatus(String idtrx, String status) {
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "UPDATE transaksi SET status=? WHERE idtrx=?";
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

}
