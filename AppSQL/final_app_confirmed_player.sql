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
-- Table structure for table `final_app_confirmed_player`
--

CREATE TABLE `final_app_confirmed_player` (
  `id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  `match_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `final_app_confirmed_player`
--

INSERT INTO `final_app_confirmed_player` (`id`, `player_id`, `match_id`) VALUES
(150, 66, 52),
(151, 62, 52),
(152, 59, 52),
(153, 63, 52),
(154, 64, 52),
(155, 60, 52),
(157, 67, 52),
(158, 65, 52),
(159, 61, 52),
(160, 58, 52),
(161, 69, 58);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `final_app_confirmed_player`
--
ALTER TABLE `final_app_confirmed_player`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_ConfPlayer_Player` (`player_id`),
  ADD KEY `FK_ConfPlayer_Match` (`match_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `final_app_confirmed_player`
--
ALTER TABLE `final_app_confirmed_player`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=162;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `final_app_confirmed_player`
--
ALTER TABLE `final_app_confirmed_player`
  ADD CONSTRAINT `FK_ConfPlayer_Match` FOREIGN KEY (`match_id`) REFERENCES `final_app_match` (`match_id`),
  ADD CONSTRAINT `FK_ConfPlayer_Player` FOREIGN KEY (`player_id`) REFERENCES `final_app_player` (`player_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
