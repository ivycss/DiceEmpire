CREATE DATABASE  IF NOT EXISTS `diceempire` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `diceempire`;
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
-- Table structure for table `dettagliordine`
--

DROP TABLE IF EXISTS `dettagliordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dettagliordine` (
  `idDettagli` int NOT NULL AUTO_INCREMENT,
  `idOrdine` int DEFAULT NULL,
  `nome` varchar(140) DEFAULT NULL,
  `quantita` int DEFAULT NULL,
  `descrizione` varchar(200) DEFAULT NULL,
  `foto` varchar(30) DEFAULT NULL,
  `prezzoIva` double(4,2) DEFAULT NULL,
  `totale` double(4,2) DEFAULT NULL,
  `iva` double DEFAULT NULL,
  PRIMARY KEY (`idDettagli`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dettagliordine`
--

LOCK TABLES `dettagliordine` WRITE;
/*!40000 ALTER TABLE `dettagliordine` DISABLE KEYS */;
INSERT INTO `dettagliordine` VALUES (12,0,'Star Wars: The Clone Wars',1,'Che la forza sia con te!',NULL,49.99,49.99,1.2),(13,0,'Uno',1,'Il gioco di carte numero UNO al mondo',NULL,12.99,12.99,1.2),(14,0,'Exploding Kittens',1,'Gioco di carte per amanti dei gatti',NULL,19.99,19.99,1.2),(15,0,'Unstable Unicorns',1,'Unicorni, Follia e Tradimenti!',NULL,14.99,14.99,1.2),(16,9,'Monopoly',1,'Gioco di strategia',NULL,9.99,9.99,1.2),(17,10,'Pokemon: Silver Tempest',1,'Bustina di carte pokemon',NULL,4.99,4.99,1.2),(18,12,'Pathfinder',1,'Gioco GDR',NULL,34.99,34.99,1.2),(19,13,'Monopoly',1,'Gioco di strategia',NULL,9.99,9.99,1.2),(20,14,'Uno',1,'Il gioco di carte numero UNO al mondo',NULL,12.99,12.99,1.2),(21,14,'Exploding Kittens',1,'Gioco di carte per amanti dei gatti',NULL,19.99,19.99,1.2),(22,15,'Unstable Unicorns',1,'Unicorni, Follia e Tradimenti!',NULL,14.99,14.99,1.2),(23,16,'Unstable Unicorns',1,'Unicorni, Follia e Tradimenti!',NULL,14.99,14.99,1.2),(24,17,'Uno',1,'Il gioco di carte numero UNO al mondo',NULL,12.99,12.99,1.2),(25,18,'Dungeon & Dragons Starter Kit',1,'Set Introduttivo per D&D',NULL,24.99,24.99,1.2),(26,19,'Dungeon & Dragons Starter Kit',1,'Set Introduttivo per D&D',NULL,24.99,24.99,1.2),(27,20,'Exploding Kittens',1,'Gioco di carte per amanti dei gatti',NULL,19.99,19.99,1.2),(28,21,'Exploding Kittens',1,'Gioco di carte per amanti dei gatti',NULL,19.99,19.99,1.2),(29,22,'Exploding Kittens',1,'Gioco di carte per amanti dei gatti',NULL,19.99,19.99,1.2),(30,23,'Exploding Kittens',1,'Gioco di carte per amanti dei gatti',NULL,19.99,19.99,1.2),(31,24,'Unstable Unicorns',1,'Unicorni, Follia e Tradimenti!',NULL,14.99,14.99,1.2),(32,25,'Unstable Unicorns',1,'Unicorni, Follia e Tradimenti!',NULL,14.99,14.99,1.2),(33,26,'Star Wars: The Clone Wars',1,'Che la forza sia con te!',NULL,49.99,49.99,1.2),(34,27,'Star Wars: The Clone Wars',1,'Che la forza sia con te!',NULL,49.99,49.99,1.2),(35,28,'Uno',1,'Il gioco di carte numero UNO al mondo',NULL,12.99,12.99,1.2),(36,29,'Little Dinosaur',1,'Piccoli dinosauri muoiono',NULL,19.99,19.99,1.2),(37,30,'Star Wars: The Clone Wars',1,'Che la forza sia con te!',NULL,49.99,49.99,1.2),(38,31,'Unstable Unicorns',1,'Unicorni, Follia e Tradimenti!',NULL,14.99,14.99,1.2),(39,31,'Star Wars: The Clone Wars',1,'Che la forza sia con te!',NULL,49.99,49.99,1.2),(40,31,'Exploding Kittens',1,'Gioco di carte per amanti dei gatti',NULL,19.99,19.99,1.2),(41,32,'Star Wars: The Clone Wars',1,'Che la forza sia con te!',NULL,49.99,49.99,1.2);
/*!40000 ALTER TABLE `dettagliordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordine`
--

DROP TABLE IF EXISTS `ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordine` (
  `idOrdine` int NOT NULL AUTO_INCREMENT,
  `idUtente` int NOT NULL,
  `dataOrdine` date DEFAULT NULL,
  PRIMARY KEY (`idOrdine`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
INSERT INTO `ordine` VALUES (1,3,'2024-06-24'),(2,3,'2024-06-24'),(3,3,'2024-06-24'),(4,3,'2024-06-24'),(5,3,'2024-06-24'),(6,3,'2024-06-24'),(7,3,'2024-06-24'),(8,3,'2024-06-24'),(9,3,'2024-06-24'),(10,3,'2024-06-24'),(11,3,'2024-06-24'),(12,1,'2024-06-24'),(13,1,'2024-06-24'),(14,1,'2024-06-25'),(15,1,'2024-06-25'),(16,7,'2024-06-25'),(17,7,'2024-06-25'),(18,7,'2024-06-25'),(19,7,'2024-06-25'),(20,7,'2024-06-25'),(21,1,'2024-06-25'),(22,1,'2024-06-25'),(23,7,'2024-06-25'),(24,1,'2024-06-25'),(25,7,'2024-06-25'),(26,1,'2024-06-25'),(27,7,'2024-06-25'),(28,7,'2024-06-25'),(29,1,'2024-06-26'),(30,1,'2024-06-26'),(31,1,'2024-06-26'),(32,5,'2024-06-26');
/*!40000 ALTER TABLE `ordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamento`
--

DROP TABLE IF EXISTS `pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagamento` (
  `numeroCarta` bigint NOT NULL,
  `dataScadenza` int NOT NULL,
  `cvc` int NOT NULL,
  `nomeInt` varchar(25) NOT NULL,
  `cognomeInt` varchar(25) NOT NULL,
  `idUtente` int NOT NULL,
  PRIMARY KEY (`numeroCarta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamento`
--

LOCK TABLES `pagamento` WRITE;
/*!40000 ALTER TABLE `pagamento` DISABLE KEYS */;
INSERT INTO `pagamento` VALUES (574867279,2026,234,'Abdif','Babill',7),(346125631245312,2027,333,'Simon','Carbone',1);
/*!40000 ALTER TABLE `pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prodotti`
--

DROP TABLE IF EXISTS `prodotti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prodotti` (
  `nome` varchar(100) DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `tipoProdotto` enum('Gioco','Carte') DEFAULT NULL,
  `tipoGioco` enum('Ruolo','Società','Tavolo','No') NOT NULL DEFAULT 'No',
  `tipoCarte` enum('Mazzi','Collezione','No') NOT NULL DEFAULT 'No',
  `produttore` varchar(50) NOT NULL,
  `prezzo` double NOT NULL,
  `descrizione` varchar(200) DEFAULT NULL,
  `descrizioneDettagliata` varchar(500) DEFAULT NULL,
  `eta` int DEFAULT NULL,
  `edizione` int DEFAULT NULL,
  `edizioneLimitata` enum('Si','No') NOT NULL,
  `quantita` int NOT NULL,
  `nomeImmagine` varchar(100) DEFAULT NULL,
  `iva` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodotti`
--

LOCK TABLES `prodotti` WRITE;
/*!40000 ALTER TABLE `prodotti` DISABLE KEYS */;
INSERT INTO `prodotti` VALUES ('Exploding Kittens',1,'Carte','No','Mazzi','Asmodee',19.99,'Gioco di carte per amanti dei gatti','Gatti? Sì, gatti. Pesca un gatto. Che gatto è? Un miciocomero? Un ottagatto? Di sicuro sarà qualcosa di assolutamente sopra le righe e di memorabile. Prendete al volo una copia di questo famosissimo successo di Kickstarter, e gettatevi nel gioco più folle degli ultimi anni. Ora in edizione riveduta e corretta.',7,6,'No',22,'explodingkittens.jpg',1.2),('Star Wars: The Clone Wars',2,'Gioco','Tavolo','No','Zman',49.99,'Che la forza sia con te!','Affronta il movimento dei Separatisti e decidi il destino delle Guerre dei Cloni: l\'Ordine Jedi mette in campo i suoi più esperti Maestri per contrastare il potere oscuro di Darth Maul, Dooku e gli altri leader segretamente al servizio del subdolo Palpatine, ma lo scontro deve essere vinto pianeta dopo pianeta, con il gioco di squadra e il supporto reciproco.',6,1,'No',16,'starwars.jpg',1.2),('Unstable Unicorns',3,'Carte','No','Mazzi','Asmodee',14.99,'Unicorni, Follia e Tradimenti!','Se pensavate di averne viste abbastanza con giochi come Exploding Kittens... Beh, fatevelo dire: era solo la punta dell\'iceberg. Un iceberg variopinto e molto, molto glamour. Radunate la vostra congrega di unicornofili e appassionati del glitter, e gettatevi nella mischia più brutale e colorata degli ultimi tempi. E ricordatevi: il corno sulla vostra testa è sì bello, ma soprattutto è anche molto appunito.',8,2,'No',43,'unstableunicorns.jpg',1.2),('Uno',4,'Carte','No','Mazzi','Mattel',12.99,'Il gioco di carte numero UNO al mondo','Tra i giochi di carte più famosi e amati oggi in tutto il mondo c’è sicuramente Uno. Questo gioco ha un regolamento molto semplice e, per questa ragione, è adatto a tutte le età.',6,4,'No',86,'uno.jpg',1.2),('Monopoly',5,'Gioco','Tavolo','No','Hasbro',9.99,'Gioco di strategia','gioco da tavolo per bambini e adulti',6,2,'No',23,'monopoly.jpg',1.2),('Pathfinder',6,'Gioco','Ruolo','No','Wizard',34.99,'Gioco GDR','gioco in cui bisogna impersonare un personaggio fittizio',12,5,'No',13,'pathfinder.jpg',1.2),('Pokemon: Silver Tempest',7,'Carte','No','Collezione','Nintendo',4.99,'Bustina di carte pokemon','Dalle profondità degli abissi, una nuova speranza! I Pokémon ASTRO continuano il loro dominio sul campo di battaglia, mentre si fanno avanti per gettarsi nella mischia. La potenza leggendaria di Lugia non ha rivali! Fatti avanti: prendi le redini di questa forza. Procurati un po\' di bustine, e nessun allenatore al mondo oserà opporsi a te.',6,1,'Si',64,'pokemon.jpg',1.2),('Il Signore Degli Anelli',8,'Gioco','Ruolo','No','Asmodee',38.99,'Espansione per Il Signore degli Anelli LCG','Dopo la dipartita di Smaug, per la Valle è iniziato un nuovo periodo di prosperità, ma nella Terra di Mezzo il Male è sempre pronto a tornare e così una nuova minaccia si fa avanti strisciando nell\'Oscurità! Impugna le tue armi e raccogli il coraggio necessario a opporti!',14,1,'No',16,'signoredeglianelli.jpg',1.2),('Dungeon & Dragons Starter Kit',12,'Gioco','Ruolo','No','Wizard',24.99,'Set Introduttivo per D&D','Un\'ombra oscura i cieli. Ali e scaglie, un soffio e un boato. La vostra avventura inizia su quest\'isola dimenticata, dove i draghi spadroneggiano e si danno battaglia. Siete ancora alle prime armi, ma non potete certo tirarvi indietro proprio ora! Scoprite Dungeons & Dragons con questo Starter Set pensato apposta per chi vuole imparare a giocare.',12,2,'No',31,'starterkit.jpg',1.2),('Little Dinosaur',14,'Carte','No','Mazzi','Asmodee',19.99,'Piccoli dinosauri muoiono','Un salto nella preistoria! Impersona il tuo dinosauro preferito e resisti a tutte le disgrazie che l',8,2,'Si',38,'littledinosaurs.jpg',1.2);
/*!40000 ALTER TABLE `prodotti` ENABLE KEYS */;
UNLOCK TABLES;

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
  `età` int NOT NULL,
  `numeroTelefono` int NOT NULL,
  `mail` varchar(30) NOT NULL,
  `citta` varchar(20) NOT NULL,
  `cap` int NOT NULL,
  `via` varchar(30) NOT NULL,
  `role` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`idUtente`),
  UNIQUE KEY `mail` (`mail`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (1,'CRBSHG123SDA','0e612e7720e26ca93ccec34f1c9f9b7ac0cfe2c79339741893de66fd8be941d1','Simon','carbone',26,2147483647,'simon@simon.it','Saviano',80039,'Roma','user'),(3,'CRBSMN76WBC','24ae07e3da729efebe2dce1f595299f8d2d5e40ec7b11864ad781f9b503a7a38','Silvana','De Martino',21,34765231,'silvana@silvana.it','Salerno',65432,'Tafuri','admin'),(5,'CRBSMN76WD','a9d5195c2bfedeb0f184225ef8f40dad577c009f7dc8bf2b232ebd812bad8d3a','Rosa','notaro',56,123145123,'rosa@rosa.it','Napoli',54321,'Gianduia','admin'),(7,'ABTFDR32312','10e3f9f0cf41deb5823e2fa6bcf294fa93489afb59f763f291d0116df5bb9799','Abdif','Abdillof',20,123641212,'abdif@abdif.it','Napoli',80035,'Selvaniti','user'),(9,'CRBSMN412WD','4b16e7e231bd44df8675066552a00f25dcb2d65d9aa46ac4c8dfd8f18f93b89d','Simon1','carbone',21,2314343,'simon1@simon.it','Salerno',80039,'Tafuri','user');
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

-- Dump completed on 2024-06-26 17:05:11
