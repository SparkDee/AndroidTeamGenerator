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
-- Table structure for table `final_app_player_team`
--

CREATE TABLE `final_app_player_team` (
  `player_team_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  `team_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `final_app_player_team`
--

INSERT INTO `final_app_player_team` (`player_team_id`, `player_id`, `team_id`) VALUES
(26, 58, 37),
(28, 69, 37),
(29, 69, 41),
(31, 58, 43);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `final_app_player_team`
--
ALTER TABLE `final_app_player_team`
  ADD PRIMARY KEY (`player_team_id`),
  ADD KEY `FK_Player` (`player_id`),
  ADD KEY `FK_Team` (`team_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `final_app_player_team`
--
ALTER TABLE `final_app_player_team`
  MODIFY `player_team_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `final_app_player_team`
--
ALTER TABLE `final_app_player_team`
  ADD CONSTRAINT `FK_Player` FOREIGN KEY (`player_id`) REFERENCES `final_app_player` (`player_id`),
  ADD CONSTRAINT `FK_Team` FOREIGN KEY (`team_id`) REFERENCES `final_app_team` (`team_id`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
