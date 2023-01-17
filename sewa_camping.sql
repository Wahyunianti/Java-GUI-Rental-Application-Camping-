-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 23, 2022 at 04:56 AM
-- Server version: 10.4.24-MariaDB-log
-- PHP Version: 7.4.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sewa_camping`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id_alat` varchar(20) NOT NULL,
  `nama_alat` varchar(50) NOT NULL,
  `kategori` varchar(20) NOT NULL,
  `status` varchar(50) NOT NULL,
  `harga` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id_alat`, `nama_alat`, `kategori`, `status`, `harga`) VALUES
('28A', 'Tas Carrier 60 Liter + Cover', 'Tas', 'Tersedia', 1000),
('28B', 'Kompor Camping Mini Kotak', 'Alat Masak', 'Disewa', 5000),
('28C', 'Matras Tiup / Sleeping pad', 'Matras', 'Disewa', 25000),
('28D', 'Meja Lipat Alloy L', 'Lainnya', 'Tersedia', 30000),
('28E', 'Grill / Panggangan', 'Alat Masak', 'Disewa', 10000),
('28F', 'Tenda Kapasitas 2-3 Singe Layer', 'Tenda', 'Tersedia', 15000),
('28G', 'Coverbag 60 Liter /  80 Liter', 'Tas', 'Disewa', 5000);

-- --------------------------------------------------------

--
-- Table structure for table `penyewa`
--

CREATE TABLE `penyewa` (
  `id_penyewa` int(20) NOT NULL,
  `nama_penyewa` varchar(50) NOT NULL,
  `no_telp` varchar(20) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `jaminan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `penyewa`
--

INSERT INTO `penyewa` (`id_penyewa`, `nama_penyewa`, `no_telp`, `alamat`, `jaminan`) VALUES
(185, 'Widodo', '082344565011', 'Jombang', 'BPKB'),
(285, 'Wahyuni', '081946174344', 'Ngampel', 'KTP'),
(474, 'Bambang', '089213173256', 'Mojokerto', 'Sertifikat Tanah'),
(475, 'Dyah', '08523353572', 'Nganjuk', 'KTP');

-- --------------------------------------------------------

--
-- Table structure for table `penyewaan`
--

CREATE TABLE `penyewaan` (
  `id` int(20) NOT NULL,
  `id_trx` varchar(20) NOT NULL,
  `nama_penyewa` varchar(50) NOT NULL,
  `tgl_kembali` date NOT NULL,
  `harga_hitung` int(20) NOT NULL,
  `delay` int(20) DEFAULT NULL,
  `denda` int(20) DEFAULT NULL,
  `total_inv` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `penyewaan`
--

INSERT INTO `penyewaan` (`id`, `id_trx`, `nama_penyewa`, `tgl_kembali`, `harga_hitung`, `delay`, `denda`, `total_inv`) VALUES
(1, 'trx-1', 'Wahyuni', '2022-12-25', 12000, 4, 20000, 32000),
(2, 'trx-2', 'Widodo', '2022-12-11', 24000, 3, 15000, 39000),
(3, 'trx-3', 'Dyah', '2022-12-27', 140000, 4, 20000, 160000),
(4, 'trx-4', 'Wahyuni', '2022-12-28', 18000, 6, 30000, 48000),
(5, 'trx-5', 'Bambang', '2022-12-30', 36000, 20, 100000, 136000),
(6, 'trx-6', 'Widodo', '2022-12-29', 217000, 3, 15000, 232000),
(7, 'trx-7', 'Wahyuni', '2022-12-20', 15000, 0, 0, 15000),
(8, 'trx-8', 'Wahyuni', '2022-12-30', 130000, 4, 20000, 150000),
(9, 'trx-9', 'Widodo', '2022-12-27', 125000, 1, 5000, 130000),
(10, 'trx-12', 'Wahyuni', '2022-12-28', 93000, 3, 15000, 108000);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `tgl_dibuat` date NOT NULL,
  `petugas` varchar(50) NOT NULL,
  `idtrx` varchar(20) NOT NULL,
  `id_alat` varchar(20) NOT NULL,
  `nama_penyewa` varchar(50) NOT NULL,
  `nama_barang` varchar(50) NOT NULL,
  `tgl_sewa` date NOT NULL,
  `tgl_kembali` date NOT NULL,
  `hari` int(20) NOT NULL,
  `lama` int(20) NOT NULL,
  `harga_harian` int(20) NOT NULL,
  `harga_subtotal` int(20) NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`tgl_dibuat`, `petugas`, `idtrx`, `id_alat`, `nama_penyewa`, `nama_barang`, `tgl_sewa`, `tgl_kembali`, `hari`, `lama`, `harga_harian`, `harga_subtotal`, `status`) VALUES
('2022-12-19', 'Wahyuni Anti', 'trx-1', '28G', 'Wahyuni', 'Coverbag 60 Liter /  80 Liter', '2022-12-19', '2022-12-21', 19346, 2, 5000, 10000, 'Dikembalikan'),
('2022-12-19', 'Wahyuni Anti', 'trx-1', '28A', 'Wahyuni', 'Tas Carrier 60 Liter + Cover', '2022-12-19', '2022-12-21', 19346, 2, 1000, 2000, 'Dikembalikan'),
('2022-12-19', 'Wahyuni Anti', 'trx-2', '28A', 'Widodo', 'Tas Carrier 60 Liter + Cover', '2022-12-04', '2022-12-08', 19333, 4, 1000, 4000, 'Dikembalikan'),
('2022-12-19', 'Wahyuni Anti', 'trx-2', '28B', 'Widodo', 'Kompor Camping Mini Kotak', '2022-12-04', '2022-12-08', 19333, 4, 5000, 20000, 'Dikembalikan'),
('2022-12-19', 'Wahyuni Anti', 'trx-3', '28D', 'Dyah', 'Meja Lipat Alloy L', '2022-12-19', '2022-12-23', 19348, 4, 30000, 120000, 'Dikembalikan'),
('2022-12-19', 'Wahyuni Anti', 'trx-3', '28B', 'Dyah', 'Kompor Camping Mini Kotak', '2022-12-19', '2022-12-23', 19348, 4, 5000, 20000, 'Dikembalikan'),
('2022-12-19', 'Wahyuni Anti', 'trx-4', '28A', 'Wahyuni', 'Tas Carrier 60 Liter + Cover', '2022-12-19', '2022-12-22', 19347, 3, 1000, 3000, 'Dikembalikan'),
('2022-12-19', 'Wahyuni Anti', 'trx-4', '28B', 'Wahyuni', 'Kompor Camping Mini Kotak', '2022-12-19', '2022-12-22', 19347, 3, 5000, 15000, 'Dikembalikan'),
('2022-12-19', 'Wahyuni Anti', 'trx-5', '28A', 'Bambang', 'Tas Carrier 60 Liter + Cover', '2022-12-04', '2022-12-10', 19335, 6, 1000, 6000, 'Dikembalikan'),
('2022-12-19', 'Wahyuni Anti', 'trx-5', '28B', 'Bambang', 'Kompor Camping Mini Kotak', '2022-12-04', '2022-12-10', 19335, 6, 5000, 30000, 'Dikembalikan'),
('2022-12-19', 'Wahyuni Anti', 'trx-6', '28A', 'Widodo', 'Tas Carrier 60 Liter + Cover', '2022-12-19', '2022-12-26', 19351, 7, 1000, 7000, 'Dikembalikan'),
('2022-12-19', 'Wahyuni Anti', 'trx-6', '28D', 'Widodo', 'Meja Lipat Alloy L', '2022-12-19', '2022-12-26', 19351, 7, 30000, 210000, 'Dikembalikan'),
('2022-12-19', 'Wahyuni Anti', 'trx-7', '28E', 'Wahyuni', 'Grill / Panggangan', '2022-12-19', '2022-12-20', 19345, 1, 10000, 10000, 'Dikembalikan'),
('2022-12-19', 'Wahyuni Anti', 'trx-7', '28G', 'Wahyuni', 'Coverbag 60 Liter /  80 Liter', '2022-12-19', '2022-12-20', 19345, 1, 5000, 5000, 'Dikembalikan'),
('2022-12-20', 'Wahyuni Anti', 'trx-8', '28A', 'Wahyuni', 'Tas Carrier 60 Liter + Cover', '2022-12-20', '2022-12-25', 19351, 5, 1000, 5000, 'Dikembalikan'),
('2022-12-20', 'Wahyuni Anti', 'trx-8', '28C', 'Wahyuni', 'Matras Tiup / Sleeping pad', '2022-12-20', '2022-12-25', 19351, 5, 25000, 125000, 'Dikembalikan'),
('2022-12-20', 'Wahyuni Anti', 'trx-9', '28E', 'Widodo', 'Grill / Panggangan', '2022-12-20', '2022-12-25', 19351, 5, 10000, 50000, 'Dikembalikan'),
('2022-12-20', 'Wahyuni Anti', 'trx-9', '28F', 'Widodo', 'Tenda Kapasitas 2-3 Singe Layer', '2022-12-20', '2022-12-25', 19351, 5, 15000, 75000, 'Dikembalikan'),
('2022-12-20', 'Wahyuni Anti', 'trx-10', '28B', 'Wahyuni', 'Kompor Camping Mini Kotak', '2022-12-01', '2022-12-03', 19329, 2, 5000, 10000, 'Disewa'),
('2022-12-20', 'Wahyuni Anti', 'trx-10', '28E', 'Wahyuni', 'Grill / Panggangan', '2022-12-01', '2022-12-03', 19329, 2, 10000, 20000, 'Disewa'),
('2022-12-21', 'Wahyuni Anti', 'trx-11', '28C', 'Widodo', 'Matras Tiup / Sleeping pad', '2022-12-04', '2022-12-08', 19334, 5, 25000, 125000, 'Disewa'),
('2022-12-21', 'Wahyuni Anti', 'trx-11', '28G', 'Widodo', 'Coverbag 60 Liter /  80 Liter', '2022-12-04', '2022-12-08', 19334, 4, 5000, 20000, 'Disewa'),
('2022-12-21', 'Wahyuni Anti', 'trx-12', '28A', 'Wahyuni', 'Tas Carrier 60 Liter + Cover', '2022-12-21', '2022-12-24', 19350, 3, 1000, 3000, 'Dikembalikan'),
('2022-12-21', 'Wahyuni Anti', 'trx-12', '28D', 'Wahyuni', 'Meja Lipat Alloy L', '2022-12-21', '2022-12-24', 19350, 3, 30000, 90000, 'Dikembalikan');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `nama` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `level` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`nama`, `username`, `password`, `level`) VALUES
('Wahyuni Anti', 'wahyunianti19', '12345', 'Admin'),
('Dhea Layna Agustiya', 'dhea', '12345', 'Penyewa');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_alat`);

--
-- Indexes for table `penyewa`
--
ALTER TABLE `penyewa`
  ADD PRIMARY KEY (`id_penyewa`);

--
-- Indexes for table `penyewaan`
--
ALTER TABLE `penyewaan`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `penyewa`
--
ALTER TABLE `penyewa`
  MODIFY `id_penyewa` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=476;

--
-- AUTO_INCREMENT for table `penyewaan`
--
ALTER TABLE `penyewaan`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
