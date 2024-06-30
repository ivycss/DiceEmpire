-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: diceempire
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `idUtente` int NOT NULL AUTO_INCREMENT,
  `cf` varchar(16) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `nome` varchar(25) NOT NULL,
  `cognome` varchar(25) NOT NULL,
  `numeroTelefono` int NOT NULL,
  `mail` varchar(30) NOT NULL,
  `citta` varchar(20) NOT NULL,
  `cap` int NOT NULL,
  `via` varchar(30) NOT NULL,
  `role` varchar(10) DEFAULT 'user',
  `eta` int DEFAULT NULL,
  PRIMARY KEY (`idUtente`),
  UNIQUE KEY `mail` (`mail`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (1,'CRBSHG123SDA','0e612e7720e26ca93ccec34f1c9f9b7ac0cfe2c79339741893de66fd8be941d1','Simon','carbone',2147483647,'simon@simon.it','Saviano',80039,'Roma','user',25),(3,'CRBSMN76WBC','24ae07e3da729efebe2dce1f595299f8d2d5e40ec7b11864ad781f9b503a7a38','Silvana','De Martino',34765231,'silvana@silvana.it','Salerno',65432,'Tafuri','admin',20),(5,'CRBSMN76WD','a9d5195c2bfedeb0f184225ef8f40dad577c009f7dc8bf2b232ebd812bad8d3a','Rosa','notaro',123145123,'rosa@rosa.it','Napoli',54321,'Gianduia','admin',32),(7,'ABTFDR32312','10e3f9f0cf41deb5823e2fa6bcf294fa93489afb59f763f291d0116df5bb9799','Abdif','Abdillof',123641212,'abdif@abdif.it','Napoli',80035,'Selvaniti','user',31),(9,'CRBSMN412WD','4b16e7e231bd44df8675066552a00f25dcb2d65d9aa46ac4c8dfd8f18f93b89d','Simon1','carbone',2314343,'simon1@simon.it','Salerno',80039,'Tafuri','user',19);
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-30 20:41:47
