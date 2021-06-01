

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
(16, 'David', 'Strange', 'imthedoc', '123', 'DOCTOR', 'MALE'),
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
(20, 'Louise', 'Gold', 'RESIDENT', NULL),
(23, 'Brian', 'Cummins', 'RESIDENT', NULL),
(25, 'Geoffrey', 'Gold', 'RESIDENT', NULL);


-- --------------------------------------------------------


--
-- Table structure for table `conditions`
--

CREATE TABLE IF NOT EXISTS `conditions` (
  ID int(10) NOT NULL AUTO_INCREMENT,
  `CName` varchar(100) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `conditions`
--

INSERT INTO `conditions` (ID, `CName`, `type`) VALUES
(30, 'Covid19', 'Infectious_Mild'),
(33, 'AIDS', 'Safe_Serious'),
(35, 'Cancer', 'Safe_Terminal');

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
  `username` varchar(50) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `mName` varchar(50) DEFAULT NULL,
  `dosage` int(20) DEFAULT NULL,
  `times` int(20) DEFAULT NULL,
  `idbed` varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `overall`
--

INSERT INTO `overall` (id, `FName`, `LName`, `gender`, `CName`, `type`, `mName`, `dosage`, `times`, `idbed`) VALUES
(01, 'Louise', 'Gold', 'FEMALE', 'Covid19', 'Infectious_Serious', 'Endep', '5', '2', '1');

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
-- Table structure for table `doctorper`
--

CREATE TABLE IF NOT EXISTS `doctorper` (
  ID int(05) NOT NULL AUTO_INCREMENT,
  `DName` varchar(50) DEFAULT NULL,
  `RName` varchar(50) DEFAULT NULL,
  `MName` varchar(30) DEFAULT NULL,
  `dosage` int(20) DEFAULT NULL,
  `times` int(20) DEFAULT NULL,
  `time` timestamp(6) DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctorper`
--

INSERT INTO `doctorper` (ID, `DName`, `RName`, `MName`, `dosage`, `times`, `time`) VALUES
(01, 'Maisie', 'Leigh', 'Morphine', '1', '1', NULL);



-- ----------------

--
-- Table structure for table `roster`
--

CREATE TABLE IF NOT EXISTS `roster` (
  ID int(05) NOT NULL AUTO_INCREMENT,
  `FName` varchar(50) DEFAULT NULL,
  `LName` varchar(50) DEFAULT NULL,
  `shift` int(30) DEFAULT NULL,
  `day` varchar(50) DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roster`
--

INSERT INTO `roster` (ID, `FName`, `LName`, `shift`, `day`) VALUES
(11, 'David', 'Strange', NULL, NULL),
(13, 'Maisie', 'Gold', NULL, NULL);

-- ----------------

--
-- Table structure for table `nurroster`
--

CREATE TABLE IF NOT EXISTS `nurroster` (
  ID int(05) NOT NULL AUTO_INCREMENT,
  `FName` varchar(50) DEFAULT NULL,
  `LName` varchar(50) DEFAULT NULL,
  `shift` int(30) DEFAULT NULL,
  `day` varchar(50) DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nurroster`
--

INSERT INTO `nurroster` (ID, `FName`, `LName`, `shift`, `day`) VALUES
(13, 'Maisie', 'Gold', NULL, NULL);

-- ----------------

--
-- Table structure for table `bed`
--

CREATE TABLE IF NOT EXISTS `bed` (
  ID int(05) NOT NULL AUTO_INCREMENT,
  `FName` varchar(50) DEFAULT NULL,
  `LName` varchar(50) DEFAULT NULL,
  `idbed` varchar(50) DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bed`
--

INSERT INTO `bed` (ID, `FName`, `LName`, `idbed`, `gender`) VALUES
(01, NULL, NULL, '1', NULL),
(02, NULL, NULL, '2', NULL),
(03, NULL, NULL, '3', NULL),
(04, NULL, NULL, '4', NULL),
(05, NULL, NULL, '5', NULL),
(06, NULL, NULL, '6', NULL),
(07, NULL, NULL, '7', NULL),
(08, NULL, NULL, '8', NULL),
(09, NULL, NULL, '9', NULL),
(10, NULL, NULL, '10', NULL),
(11, NULL, NULL, '11', NULL),
(12, NULL, NULL, '12', NULL),
(13, NULL, NULL, '13', NULL),
(14, NULL, NULL, '14', NULL),
(15, NULL, NULL, '15', NULL),
(16, NULL, NULL, '16', NULL),
(17, NULL, NULL, '17', NULL),
(18, NULL, NULL, '18', NULL),
(19, NULL, NULL, '19', NULL),
(20, NULL, NULL, '20', NULL),
(21, NULL, NULL, '21', NULL),
(22, NULL, NULL, '22', NULL),
(23, NULL, NULL, '23', NULL),
(24, NULL, NULL, '24', NULL),
(25, NULL, NULL, '25', NULL),
(26, NULL, NULL, '26', NULL),
(27, NULL, NULL, '27', NULL),
(28, NULL, NULL, '28', NULL),
(29, NULL, NULL, '29', NULL),
(30, NULL, NULL, '30', NULL),
(31, NULL, NULL, '31', NULL),
(32, NULL, NULL, '32', NULL),
(33, NULL, NULL, '33', NULL),
(34, NULL, NULL, '34', NULL),
(35, NULL, NULL, '35', NULL),
(36, NULL, NULL, '36', NULL),
(37, NULL, NULL, '37', NULL),
(38, NULL, NULL, '38', NULL);

-- ----------------
--
-- Table structure for table `nurseadm`
--

CREATE TABLE IF NOT EXISTS `nurseadm` (
  ID int(05) NOT NULL AUTO_INCREMENT,
  `NName` varchar(50) DEFAULT NULL,
  `RName` varchar(50) DEFAULT NULL,
  `MName` varchar(30) DEFAULT NULL,
  `dosage` int(20) DEFAULT NULL,
  `times` int(20) DEFAULT NULL,
  `time` timestamp(6) DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nurseadm`
--

INSERT INTO `doctorper` (ID, `NName`, `RName`, `MName`, `dosage`, `times`, `time`) VALUES
(01, 'Night', 'Geoffrey', 'Morphine', '1', '1', NULL);



-- ----------------

--
-- Indexes for dumped tables
--



--
-- Indexes for table `users`
--


--
-- AUTO_INCREMENT for dumped tables
--



