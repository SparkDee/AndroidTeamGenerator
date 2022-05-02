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
-- Table structure for table `final_app_player`
--

CREATE TABLE `final_app_player` (
  `player_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `final_app_player`
--

INSERT INTO `final_app_player` (`player_id`, `name`, `email`, `password`) VALUES
(58, 'Mark Davidson', 'mark', '$2y$10$x34th9VHi4zVvMkblDawGOcJ82o7Rs0joA2QzDJEaeBihXxFNCe5O'),
(59, 'Edel Davidson', 'edel', '$2y$10$1y3aQwivL6iKToidkvpO4u3PEdhqdiKyBarpjWP/AX/wBAIklyv6C'),
(60, 'Keira Davidson', 'keira', '$2y$10$Y2pe6pZMOPd2.I8zTjHS4u7mf/Rc4SYwxyG2UmacdXWYVPMOfva2m'),
(61, 'Willow Davidson', 'willow', '$2y$10$qdWdjBsMLr512vZSpZ9RJuclAbPcI.N5h7ONzEl8tBKWst9NjEAoq'),
(62, 'Daisy Davidson', 'daisy', '$2y$10$ZM7SapN9va7gl0ca6GcnHeQAsYZPKGXLzujhkfnOSAX034/JCxvD.'),
(63, 'Joe Davidson', 'joe', '$2y$10$mj8CAn1mW61eORJVaWtETuk76mJ2EsPg037YXrYXRCMJ8dKO.eacS'),
(64, 'Johnny Caren', 'jonny', '$2y$10$.WYvAEA5bcmsgV2HZ68CweE1HopRpVo6WbTTehFrMLFgdVnMLZgTy'),
(65, 'Paul Doherty', 'paul', '$2y$10$kBTGkH0f9wBtS1dHAQ2nF.G8Khqd/6ZTE4A2ITvQF6hMymmMg0cna'),
(66, 'Adrian McElvanna', 'adrian', '$2y$10$gyrj2KmcHg/vRz98e.mm0.JT0xfGxaPAbsdjEjw6OK7C9pt0djG4a'),
(67, 'Mark Drysdale', 'dryz', '$2y$10$nU0duEbt4IZbL6Yt3.fPu.1dCuuGqlbIlaU/qzhMMQzzN6htsk0oe'),
(69, 'kevin', 'kevin', '$2y$10$gLsFEXbELCA0/EU5SxTyMOsdGjN0jl.PzckdB0Thl0EE8hy/qx6i.');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `final_app_player`
--
ALTER TABLE `final_app_player`
  ADD PRIMARY KEY (`player_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `final_app_player`
--
ALTER TABLE `final_app_player`
  MODIFY `player_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
