-- phpMyAdmin SQL Dump
-- version 4.9.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 02, 2022 at 10:35 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mdavidson04`
--

-- --------------------------------------------------------

--
-- Table structure for table `final_app_admin`
--

CREATE TABLE `final_app_admin` (
  `admin_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `final_app_admin`
--

INSERT INTO `final_app_admin` (`admin_id`, `name`, `email`, `password`) VALUES
(53, 'Mark Davidson', 'mark', '$2y$10$aanWenvu46deHh/iLSIR8OSdmAXMZoAp79wogIJHAT06qdOLM2wS2'),
(57, 'kevin', 'kevin', '$2y$10$Fyi5YRvjzSlEl6qvQrvsleD5fqk.OfoItZmSzEH0Ct3RFAGaFXP/S');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `final_app_admin`
--
ALTER TABLE `final_app_admin`
  ADD PRIMARY KEY (`admin_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `final_app_admin`
--
ALTER TABLE `final_app_admin`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
