-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 07, 2019 at 08:06 PM
-- Server version: 5.7.26-0ubuntu0.18.04.1
-- PHP Version: 7.2.19-0ubuntu0.18.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `onlinejudge2019`
--

-- --------------------------------------------------------

--
-- Table structure for table `problems`
--

CREATE TABLE `problems` (
  `problem_id` varchar(10) NOT NULL,
  `problem_title` varchar(100) NOT NULL,
  `problem_description` varchar(5000) NOT NULL,
  `sample_input` varchar(255) NOT NULL,
  `sample_output` varchar(255) NOT NULL,
  `problem_input` varchar(255) NOT NULL,
  `problem_output` varchar(255) NOT NULL,
  `time_limit_Mils` int(10) NOT NULL,
  `memory_limit_kb` int(10) NOT NULL,
  `author_username` varchar(100) NOT NULL,
  `difficulty_level` varchar(20) NOT NULL,
  `point` int(255) NOT NULL DEFAULT '100',
  `active_status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `problems`
--

INSERT INTO `problems` (`problem_id`, `problem_title`, `problem_description`, `sample_input`, `sample_output`, `problem_input`, `problem_output`, `time_limit_Mils`, `memory_limit_kb`, `author_username`, `difficulty_level`, `point`, `active_status`) VALUES
('1187A', 'Print Hello', '<p>Comfort reached gay perhaps chamber his six detract besides add. Moonlight newspaper up he it enjoyment agreeable depending. Timed voice share led his widen noisy young. On weddings believed laughing although material do exercise of. Up attempt offered ye civilly so sitting to. She new course get living within elinor joy. She her rapturous suffering concealed.</p>\n\n<p>Certainty listening no no behaviour existence assurance situation is. Because add why not esteems amiable him. Interested the unaffected mrs law friendship add principles. Indeed on people do merits to. Court heard which up above hoped grave do. Answer living law things either sir bed length. Looked before we an on merely. These no death he at share alone. Yet outward the him compass hearted are tedious. In it except to so temper mutual tastes mother. Interested cultivated its continuing now yet are. Out interested acceptance our partiality affronting unpleasant why add. Esteem garden men yet shy course. Consulted up my tolerably sometimes perpetual oh. Expression acceptance imprudence particular had eat unsatiable.</p>\n\n<b>Print \"Hello World\"</b>', '', 'Hello World', '', 'Hello World', 100, 1024, 'shaykhsiddique', 'Easy', 100, 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `u_id` int(255) NOT NULL,
  `fullname` varchar(255) NOT NULL,
  `username` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_rule` int(10) NOT NULL DEFAULT '3',
  `score` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`u_id`, `fullname`, `username`, `email`, `password`, `user_rule`, `score`) VALUES
(5, 'Mr. bob', 'bob123', 'bob@bob.com', '$2a$10$qP/p3VDr5D5mr4n6jaYSfemGINF9JncIypkNsQ4z1kqnKjvNQnhGe', 3, 0),
(12, 'Shaykh Siddique', 'shaykhsiddique', 'shaykh.siddique@hotmail.com', '$2a$10$2OfyekXfgUb/qJTgI8UdjONxOi861/6uTouCWvNQNDLq3.Jit6UEi', 1, 0),
(35, 'Mr. Alice', 'alice007', 'alice@root.hp', '$2a$10$VRrs3VU4QjvbP9X6uEGJhOskLelAvw0fGLpwbz6iyN8zXaR0V3uwK', 3, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `problems`
--
ALTER TABLE `problems`
  ADD PRIMARY KEY (`problem_id`),
  ADD KEY `username` (`author_username`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`u_id`,`username`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `u_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `problems`
--
ALTER TABLE `problems`
  ADD CONSTRAINT `username` FOREIGN KEY (`author_username`) REFERENCES `users` (`username`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
