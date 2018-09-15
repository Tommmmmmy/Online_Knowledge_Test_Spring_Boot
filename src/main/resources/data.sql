CREATE DATABASE  IF NOT EXISTS `insticator`;
USE `insticator`;

--
-- Table structure for table `book_detail`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `checkbox`;
CREATE TABLE `checkbox` (
  `qId` varchar(255) unsigned NOT NULL,
  `question` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`qId`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `trivia`;
CREATE TABLE `poll` (
  `qId` varchar(255) unsigned NOT NULL,
  `question` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`qId`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `trivia`;
CREATE TABLE `trivia` (
  `qId` varchar(255) unsigned NOT NULL,
  `question` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`qId`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;