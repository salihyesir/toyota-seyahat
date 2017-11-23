-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: travelmanagement
-- ------------------------------------------------------
-- Server version	5.7.20

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
-- Table structure for table `traveldetails`
--

DROP TABLE IF EXISTS `traveldetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `traveldetails` (
  `TravelId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL,
  `TravelLocation` varchar(30) CHARACTER SET utf8 NOT NULL,
  `TravelMission` varchar(20) CHARACTER SET utf8 NOT NULL,
  `ProjectCode` varchar(5) CHARACTER SET utf8 NOT NULL,
  `TravelStartDate` date NOT NULL,
  `TravelFinishDate` date NOT NULL,
  `EstimatedCost` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `IsDeleted` bit(1) NOT NULL,
  PRIMARY KEY (`TravelId`),
  KEY `UserId_idx` (`UserId`),
  CONSTRAINT `UserId` FOREIGN KEY (`UserId`) REFERENCES `users` (`UserId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `traveldetails`
--

LOCK TABLES `traveldetails` WRITE;
/*!40000 ALTER TABLE `traveldetails` DISABLE KEYS */;
INSERT INTO `traveldetails` VALUES (1,1,'Istanbul','Tatil','A2','2025-11-29','2025-11-29','1200','\0'),(2,2,'Giresun','Gezi','A1','2017-04-23','2017-04-26','300','\0'),(20,1,'Ankara','Toyota','A0001','2017-04-23','2017-04-26','12000','\0'),(21,34,'Manisa','Turizm','A1','2017-04-25','2017-04-29','800','\0'),(22,2,'İzmir','Yok','A2','2017-04-02','2017-04-02','600','\0'),(23,1,'Sinop','Tur','ABC','2017-04-11','2017-04-26','250','\0'),(24,2,'Bursa','Gezi','A','2017-04-05','2017-04-05','500','\0'),(25,34,'Manisa','Memleket','B','2017-04-23','2017-04-27','1000','\0'),(26,1,'Kastamonu','Gezi','C','2017-04-04','2017-04-22','200',''),(27,2,'sad','asd','250','2017-11-19','2017-11-22','0','\0'),(28,2,'deneme','ads','554','2017-11-23','2017-11-30','0','\0');
/*!40000 ALTER TABLE `traveldetails` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-23 14:36:37
