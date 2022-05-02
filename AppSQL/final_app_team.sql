-- phpMyAdmin SQL Dump
-- version 4.9.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 02, 2022 at 10:36 PM
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
-- Table structure for table `final_app_team`
--

CREATE TABLE `final_app_team` (
  `team_id` int(11) NOT NULL,
  `admin_id` int(11) NOT NULL,
  `team_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `final_app_team`
--

INSERT INTO `final_app_team` (`team_id`, `admin_id`, `team_name`) VALUES
(37, 53, 'Tenth Avenue Freezers'),
(41, 57, 'QUB allstars'),
(42, 57, 'Aquinas'),
(43, 53, 'Thursday Night Lakers');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `final_app_team`
--
ALTER TABLE `final_app_team`
  ADD PRIMARY KEY (`team_id`),
  ADD KEY `FK_team_admin_` (`admin_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `final_app_team`
--
ALTER TABLE `final_app_team`
  MODIFY `team_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `final_app_team`
--
ALTER TABLE `final_app_team`
  ADD CONSTRAINT `FK_team_admin_` FOREIGN KEY (`admin_id`) REFERENCES `final_app_admin` (`admin_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
