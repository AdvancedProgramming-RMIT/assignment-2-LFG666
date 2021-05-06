

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";




--
-- Database: `assignment2`
--

-- --------------------------------------------------------



--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  id int(10) NOT NULL AUTO_INCREMENT,
  `FName` varchar(50) DEFAULT NULL,
  `LName` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (id, `FName`, `LName`, `username`, `password`, `type`, `gender`) VALUES
(10, 'Louise', 'Gold', 'loulou', '123', 'RESIDENT', 'FEMALE'),
(11, 'Leigh', 'Gold', 'lethal', 'abcd', 'ADMIN', 'MALE'),
(12, 'Maisie', 'Gold', 'moomoo', '123', 'DOCTOR', 'FEMALE'),
(13, 'Brian', 'Cummins', 'brian', '123', 'RESIDENT', 'MALE'),
(14, 'Allie', 'Hipkins', 'allie', '123', 'NURSE', 'FEMALE'),
(15, 'Geoffrey', 'Gold', 'geoff', '123', 'RESIDENT', 'MALE'),
(16, 'Dr', 'Strange', 'imthedoc', '123', 'DOCTOR', 'MALE'),
(17, 'Night', 'Nurse', 'night', '123', 'NURSE', 'FEMALE');

-- --------------------------------------------------------

--
-- Table structure for table `residents`
--

CREATE TABLE IF NOT EXISTS `residents` (
  ID int(05) NOT NULL AUTO_INCREMENT,
  `FName` varchar(50) DEFAULT NULL,
  `LName` varchar(50) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `residents`
--

INSERT INTO `residents` (ID, `FName`, `LName`, `type`, `gender`) VALUES
(10, 'Louise', 'Gold', 'RESIDENT', NULL),
(13, 'Brian', 'Cummins', 'RESIDENT', NULL),
(15, 'Geoffrey', 'Gold', 'RESIDENT', NULL);


-- --------------------------------------------------------


--
-- Table structure for table `conditions`
--

CREATE TABLE IF NOT EXISTS `conditions` (
  ID int(05) NOT NULL AUTO_INCREMENT,
  `CName` varchar(50) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `conditions`
--

INSERT INTO `conditions` (ID, `CName`, `type`) VALUES
(10, 'Covid19', 'Infectious_Mild'),
(13, 'AIDS', 'Safe_Serious'),
(15, 'Cancer', 'Safe_Terminal');

-- --------------------------------------------------------



--
-- Table structure for table `condres`
--

CREATE TABLE IF NOT EXISTS `condres` (
  ID int(05) NOT NULL AUTO_INCREMENT,
   `FName` varchar(50) DEFAULT NULL,
  `LName` varchar(30) DEFAULT NULL,
   `gender` varchar(50) DEFAULT NULL,
  `CName` varchar(50) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `condres`
--

INSERT INTO `condres` (ID, `FName`, `LName`, `gender`, `CName`, `type`) VALUES
(01, 'Louise', 'Gold', 'FEMALE', 'Covid19', 'Infectious_Serious');

-- --------------------------------------------------------

--
-- Table structure for table `overall`
--

CREATE TABLE IF NOT EXISTS `overall` (
  id int(05) NOT NULL AUTO_INCREMENT,
   `FName` varchar(50) DEFAULT NULL,
  `LName` varchar(30) DEFAULT NULL,
   `gender` varchar(10) DEFAULT NULL,
  `CName` varchar(50) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `mName` varchar(50) DEFAULT NULL,
  `dosage` int(20) DEFAULT NULL,
  `times` int(20) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `overall`
--

INSERT INTO `overall` (id, `FName`, `LName`, `gender`, `CName`, `type`, `mName`, `dosage`, `times`) VALUES
(01, 'Louise', 'Gold', 'FEMALE', 'Covid19', 'Infectious_Serious', 'Endep', '5', '2');

-- --------------------------------------------------------

--
-- Table structure for table `medicine`
--

CREATE TABLE IF NOT EXISTS `medicine` (
  id int(5) NOT NULL AUTO_INCREMENT,
  `MName` varchar(50) DEFAULT NULL,
  `stock` int(30) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `medicine`
--

INSERT INTO `medicine` (id, `MName`, `stock`) VALUES
(01, 'Morphine', '100'),
(02, 'Dexampetamines', '50'),
(03, 'Endep', '20'),
(04, 'Endone', '50'),
(05, 'Paracetamol', '200'),
(06, 'Magnesium', '50'),
(07, 'Doxepin', '30'),
(08, 'Salbutamol', '300');



-- --------------------------------------------------------






--
-- Indexes for dumped tables
--



--
-- Indexes for table `users`
--


--
-- AUTO_INCREMENT for dumped tables
--



