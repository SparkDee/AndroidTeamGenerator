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
-- Table structure for table `final_app_match`
--

CREATE TABLE `final_app_match` (
  `match_id` int(11) NOT NULL,
  `admin_id` int(11) NOT NULL,
  `team_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `venue` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `final_app_match`
--

INSERT INTO `final_app_match` (`match_id`, `admin_id`, `team_id`, `date`, `time`, `venue`) VALUES
(52, 53, 37, '2021-10-10', '21:00:00', 'Stormont'),
(58, 57, 41, '2021-10-10', '09:00:00', 'Ulidia');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `final_app_match`
--
ALTER TABLE `final_app_match`
  ADD PRIMARY KEY (`match_id`),
  ADD KEY `FK_match_admin` (`admin_id`),
  ADD KEY `FK_match_team` (`team_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `final_app_match`
--
ALTER TABLE `final_app_match`
  MODIFY `match_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `final_app_match`
--
ALTER TABLE `final_app_match`
  ADD CONSTRAINT `FK_match_admin` FOREIGN KEY (`admin_id`) REFERENCES `final_app_admin` (`admin_id`),
  ADD CONSTRAINT `FK_match_team` FOREIGN KEY (`team_id`) REFERENCES `final_app_team` (`team_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
