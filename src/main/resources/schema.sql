-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: uhoogma
-- ------------------------------------------------------
-- Server version	5.7.12-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `AbstractedCode`
--

DROP TABLE IF EXISTS `AbstractedCode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AbstractedCode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `attempt_id` bigint(20) NOT NULL,
  `version_id` bigint(20) NOT NULL,
  `abstractedCode` text,
  `task_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_abstractedcode_version` (`version_id`),
  KEY `FK_abstractedcode_attempt` (`attempt_id`),
  CONSTRAINT `FK_abstractedcode_attempt` FOREIGN KEY (`attempt_id`) REFERENCES `Attempt` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_abstractedcode_version` FOREIGN KEY (`version_id`) REFERENCES `Version` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `attempt`
--

DROP TABLE IF EXISTS `Attempt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Attempt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `round_id` bigint(20) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `code` text,
  `codeAcquired` tinyint(1) DEFAULT NULL,
  `isBoilerPlate` tinyint(1) DEFAULT NULL,
  `task_id` bigint(20) DEFAULT NULL,
  `fileName` varchar(100) DEFAULT NULL,
  `moodleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_attempt_round` (`round_id`),
  KEY `FK_attempt_task` (`task_id`),
  KEY `FK_attempt_student` (`student_id`),
  KEY `moodleId` (`moodleId`),
  CONSTRAINT `FK_attempt_round` FOREIGN KEY (`round_id`) REFERENCES `Round` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_attempt_student` FOREIGN KEY (`student_id`) REFERENCES `Student` (`moodleId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_attempt_task` FOREIGN KEY (`task_id`) REFERENCES `Task` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `round`
--

DROP TABLE IF EXISTS `Round`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Round` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_id` bigint(20) DEFAULT NULL,
  `year` varchar(9) DEFAULT NULL,
  `semester` varchar(20) DEFAULT NULL,
  `subject` varchar(6) DEFAULT NULL,
  `roundName` varchar(100) DEFAULT NULL,
  `url` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_round_task` (`task_id`),
  CONSTRAINT `FK_round_task` FOREIGN KEY (`task_id`) REFERENCES `Task` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `round_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `Task` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `SavedComparison`
--

DROP TABLE IF EXISTS `SavedComparison`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SavedComparison` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_id` bigint(20) NOT NULL,
  `version_id` bigint(20) NOT NULL,
  `firstStudentId` int(11) DEFAULT NULL,
  `secondStudentId` int(11) DEFAULT NULL,
  `firstAttemptId` int(11) DEFAULT NULL,
  `secondAttemptId` int(11) DEFAULT NULL,
  `firstToSecondResult` double NOT NULL,
  `secondToFirstResult` double NOT NULL,
  `firstToSecondIsInfinite` tinyint(1) DEFAULT NULL,
  `secondToFirstIsInfinite` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_savedcomparison_task` (`task_id`),
  KEY `FK_savedcomparison_version` (`version_id`),
  KEY `FK_savedcomparison_first_student` (`firstStudentId`),
  KEY `FK_savedcomparison_second_student` (`secondStudentId`),
  KEY `firstAttemptId` (`firstAttemptId`),
  KEY `secondAttemptId` (`secondAttemptId`),
  CONSTRAINT `FK_savedcomparison_first_attempt` FOREIGN KEY (`firstAttemptId`) REFERENCES `Attempt` (`moodleId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_savedcomparison_first_student` FOREIGN KEY (`firstStudentId`) REFERENCES `Student` (`moodleId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_savedcomparison_second_attempt` FOREIGN KEY (`secondAttemptId`) REFERENCES `Attempt` (`moodleId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_savedcomparison_second_student` FOREIGN KEY (`secondStudentId`) REFERENCES `Student` (`moodleId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_savedcomparison_task` FOREIGN KEY (`task_id`) REFERENCES `Task` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_savedcomparison_version` FOREIGN KEY (`version_id`) REFERENCES `Version` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Student`
--

DROP TABLE IF EXISTS `Student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `moodleId` int(11) DEFAULT NULL,
  `fullName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `moodleId` (`moodleId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `Task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `taskName` varchar(50) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `creationTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastSyncTime` timestamp NULL DEFAULT NULL,
  `t` int(11) NOT NULL,
  `k` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `version`
--

DROP TABLE IF EXISTS `Version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Version` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `abstractionVersionId` int(11) NOT NULL,
  `SimilarityVersionId` int(11) NOT NULL,
  `defaultT` int(11) NOT NULL,
  `defaultK` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

    
-- data
insert into Version (abstractionVersionId,SimilarityVersionId,defaultK,defaultT) values(1,1,13,39);

-- Dump completed on 2016-05-26 14:08:52
