CREATE DATABASE  IF NOT EXISTS `crm_basedidati` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `crm_basedidati`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: crm_basedidati
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `appuntamento`
--

DROP TABLE IF EXISTS `appuntamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appuntamento` (
  `sede` varchar(45) NOT NULL,
  `data` date NOT NULL,
  `ora` time NOT NULL,
  `idappuntamento` int NOT NULL AUTO_INCREMENT,
  `cliente_codicefiscale` varchar(25) NOT NULL,
  `interazione_idinterazione` int NOT NULL,
  PRIMARY KEY (`idappuntamento`),
  UNIQUE KEY `idappuntamento_UNIQUE` (`idappuntamento`),
  KEY `fk_appuntamento_cliente1_idx` (`cliente_codicefiscale`),
  KEY `fk_appuntamento_interazione1_idx` (`interazione_idinterazione`),
  CONSTRAINT `fk_appuntamento_cliente1` FOREIGN KEY (`cliente_codicefiscale`) REFERENCES `cliente` (`codicefiscale`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_appuntamento_interazione1` FOREIGN KEY (`interazione_idinterazione`) REFERENCES `interazione` (`idinterazione`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appuntamento`
--

LOCK TABLES `appuntamento` WRITE;
/*!40000 ALTER TABLE `appuntamento` DISABLE KEYS */;
INSERT INTO `appuntamento` VALUES ('Scraton','2025-01-27','15:30:00',1,'VRDSRA90M08H501Z',12),('Scraton','2025-01-31','11:00:00',2,'NRICLD95B51Z404Z',15),('Roma','2025-02-10','10:30:00',3,'NRICLD95B51Z404Z',17);
/*!40000 ALTER TABLE `appuntamento` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `checkAppuntamento` BEFORE INSERT ON `appuntamento` FOR EACH ROW BEGIN
    -- Verifica che esista un'interazione con l'ID fornito
    IF NOT EXISTS (SELECT 1 FROM interazione WHERE idinterazione = NEW.interazione_idinterazione) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Impossibile inserire un appuntamento senza un interazione';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `check_orari_appuntamento` BEFORE INSERT ON `appuntamento` FOR EACH ROW BEGIN
    -- Controlla che la data e l'ora siano coerenti con gli orari lavorativi
    IF WEEKDAY(NEW.data) > 4 OR
       TIME(NEW.ora) < '09:00:00' OR
       TIME(NEW.ora) > '18:00:00' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Errore: La data e l\'ora devono rientrare negli orari lavorativi (lun-ven, 9:00-18:00).';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `VerificaConflittiAppuntamenti` BEFORE INSERT ON `appuntamento` FOR EACH ROW BEGIN
    -- Verifica se esiste un conflitto di appuntamento
    IF EXISTS (
        SELECT 1
        FROM appuntamento
        WHERE cliente_codicefiscale = NEW.cliente_codicefiscale
        AND data = NEW.data
        AND ora = NEW.ora
        AND sede = NEW.sede
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Conflitto di appuntamento: un cliente non può avere più appuntamenti nella stessa sede, data e ora.';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `check_duplicate_appointment` BEFORE INSERT ON `appuntamento` FOR EACH ROW BEGIN
    DECLARE var_conta_appuntamenti INT;

    -- Controlla se esiste già un appuntamento non scaduto per lo stesso cliente e operatore
    SELECT COUNT(*) INTO var_conta_appuntamenti
    FROM appuntamento
    WHERE cliente_codicefiscale = NEW.cliente_codicefiscale
      AND interazione_idinterazione = NEW.interazione_idinterazione
      AND data >= CURDATE();  -- Assicurati che l'appuntamento non sia scaduto

    -- Se esiste già un appuntamento non scaduto, impedisce l'inserimento
    IF var_conta_appuntamenti > 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Errore: L\'operatore ha già un appuntamento con il cliente che non è scaduto.';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `codicefiscale` varchar(25) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `dataDiNascita` datetime NOT NULL,
  `dataDiRegistrazione` datetime NOT NULL,
  `segreteria` varchar(45) NOT NULL,
  PRIMARY KEY (`codicefiscale`),
  UNIQUE KEY `codice fiscale_UNIQUE` (`codicefiscale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('NRICLD95B51Z404Z','Claudia','Neri','1995-01-16 00:00:00','2025-01-20 00:00:00','mario_rossi'),('PRTGCM56D26L949X','Giacomo','Poretti','1956-04-15 00:00:00','2025-01-30 00:00:00','giovanni_storti'),('SCTMHL64C15F205Z','Michael','Scott','1964-03-15 00:00:00','2025-01-24 00:00:00','mario_rossi'),('VRDSRA90M08H501Z','Sara','Verdi','1990-08-08 00:00:00','2025-01-20 00:00:00','mario_rossi');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `ImpostaDataDiRegistrazione` BEFORE INSERT ON `cliente` FOR EACH ROW BEGIN
    -- Imposta la data corrente se non specificata
    IF NEW.dataDiRegistrazione IS NULL THEN
        SET NEW.dataDiRegistrazione = CURRENT_DATE;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `contatti`
--

DROP TABLE IF EXISTS `contatti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contatti` (
  `valore` varchar(45) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `cliente_codicefiscale` varchar(25) NOT NULL,
  PRIMARY KEY (`valore`,`cliente_codicefiscale`),
  UNIQUE KEY `valore_UNIQUE` (`valore`),
  KEY `fk_contatti_cliente1_idx` (`cliente_codicefiscale`),
  CONSTRAINT `fk_contatti_cliente1` FOREIGN KEY (`cliente_codicefiscale`) REFERENCES `cliente` (`codicefiscale`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contatti`
--

LOCK TABLES `contatti` WRITE;
/*!40000 ALTER TABLE `contatti` DISABLE KEYS */;
INSERT INTO `contatti` VALUES ('3215709358','phone','NRICLD95B51Z404Z'),('3339229987','phone','VRDSRA90M08H501Z'),('3339229998','phone','NRICLD95B51Z404Z'),('338','phone','PRTGCM56D26L949X'),('3458847836','phone','SCTMHL64C15F205Z'),('5709346','phone','PRTGCM56D26L949X'),('claudia@gmail.com','email','NRICLD95B51Z404Z'),('giacomo@gmail.com','email','PRTGCM56D26L949X'),('michael@gmail.com','email','SCTMHL64C15F205Z'),('sara@gmail.com','email','VRDSRA90M08H501Z'),('scott@gmail.com','email','SCTMHL64C15F205Z'),('verdi@gmail.com','email','VRDSRA90M08H501Z');
/*!40000 ALTER TABLE `contatti` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `VerificaEmailTelefono` BEFORE INSERT ON `contatti` FOR EACH ROW BEGIN
    -- Verifica l'email
    IF NEW.tipo = 'email' AND NEW.valore NOT REGEXP '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Formato email non valido. Assicurati di usare un formato corretto.';
    END IF;

    -- Verifica il telefono
    IF NEW.tipo = 'telefono' AND NEW.valore NOT REGEXP '^\\+?[0-9]{8,15}$' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Formato telefono non valido. Deve contenere solo numeri (opzionalmente un + all’inizio) e deve essere lungo tra 8 e 15 cifre.';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `VerificaEmailTelefonoUpdate` BEFORE UPDATE ON `contatti` FOR EACH ROW BEGIN
    -- Verifica l'email
    IF NEW.tipo = 'email' AND NEW.valore NOT REGEXP '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Formato email non valido. Assicurati di usare un formato corretto.';
    END IF;

    -- Verifica il telefono
    IF NEW.tipo = 'telefono' AND NEW.valore NOT REGEXP '^\\+?[0-9]{8,15}$' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Formato telefono non valido. Deve contenere solo numeri (opzionalmente un + all’inizio) e deve essere lungo tra 8 e 15 cifre.';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `indirizzi`
--

DROP TABLE IF EXISTS `indirizzi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `indirizzi` (
  `via` varchar(25) NOT NULL,
  `cap` varchar(25) NOT NULL,
  `città` varchar(25) NOT NULL,
  `cliente_codicefiscale` varchar(25) NOT NULL,
  PRIMARY KEY (`cliente_codicefiscale`),
  UNIQUE KEY `via_UNIQUE` (`via`),
  UNIQUE KEY `via` (`via`),
  CONSTRAINT `fk_indirizzi_cliente1` FOREIGN KEY (`cliente_codicefiscale`) REFERENCES `cliente` (`codicefiscale`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indirizzi`
--

LOCK TABLES `indirizzi` WRITE;
/*!40000 ALTER TABLE `indirizzi` DISABLE KEYS */;
INSERT INTO `indirizzi` VALUES ('via milano 1','00021','Milano','NRICLD95B51Z404Z'),('via roma 1','00021','Milano','PRTGCM56D26L949X'),('scraton number 1','00130','Scraton','SCTMHL64C15F205Z'),('via del corso 1','00001','Roma','VRDSRA90M08H501Z');
/*!40000 ALTER TABLE `indirizzi` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `VerificaIndirizzi` BEFORE INSERT ON `indirizzi` FOR EACH ROW BEGIN
    -- Verifica che la via sia valida
    IF NEW.via NOT REGEXP '^[a-zA-Z0-9 ]+$' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Formato via non valido. Può contenere solo lettere, numeri e spazi.';
    END IF;

    -- Verifica che la città sia valida
    IF NEW.città NOT REGEXP '^[a-zA-Z ]+$' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Formato città non valido. Può contenere solo lettere e spazi.';
    END IF;

    -- Verifica che il CAP sia valido (solo numeri, 5 cifre)
    IF NEW.cap NOT REGEXP '^[0-9]{5}$' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Formato CAP non valido. Deve essere composto da 5 cifre.';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `VerificaIndirizziUpdate` BEFORE UPDATE ON `indirizzi` FOR EACH ROW BEGIN
    -- Verifica che la via sia valida
    IF NEW.via NOT REGEXP '^[a-zA-Z0-9 ]+$' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Formato via non valido. Può contenere solo lettere, numeri e spazi.';
    END IF;

    -- Verifica che la città sia valida
    IF NEW.città NOT REGEXP '^[a-zA-Z ]+$' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Formato città non valido. Può contenere solo lettere e spazi.';
    END IF;

    -- Verifica che il CAP sia valido (solo numeri, 5 cifre)
    IF NEW.cap NOT REGEXP '^[0-9]{5}$' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Formato CAP non valido. Deve essere composto da 5 cifre.';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `interazione`
--

DROP TABLE IF EXISTS `interazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interazione` (
  `idinterazione` int NOT NULL AUTO_INCREMENT,
  `data` datetime NOT NULL,
  `ora` time NOT NULL,
  `operatore` varchar(45) NOT NULL,
  `offerta_idofferta` int NOT NULL,
  `cliente_codicefiscale` varchar(25) NOT NULL,
  `risultato` tinyint NOT NULL,
  `dettagli` varchar(45) NOT NULL,
  PRIMARY KEY (`idinterazione`),
  UNIQUE KEY `idinterazione_UNIQUE` (`idinterazione`),
  KEY `fk_interazione_offerta1_idx` (`offerta_idofferta`),
  KEY `fk_interazione_cliente1_idx` (`cliente_codicefiscale`),
  KEY `idx_interazione_cliente_offerta` (`cliente_codicefiscale`,`offerta_idofferta`,`risultato`),
  CONSTRAINT `fk_interazione_cliente1` FOREIGN KEY (`cliente_codicefiscale`) REFERENCES `cliente` (`codicefiscale`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_interazione_offerta1` FOREIGN KEY (`offerta_idofferta`) REFERENCES `offerta` (`idofferta`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interazione`
--

LOCK TABLES `interazione` WRITE;
/*!40000 ALTER TABLE `interazione` DISABLE KEYS */;
INSERT INTO `interazione` VALUES (11,'2025-01-23 00:00:00','15:00:00','bruno_bianchi',13,'VRDSRA90M08H501Z',0,'Non è andata a buon fine'),(12,'2025-01-24 00:00:00','10:00:00','jim_halpert',13,'VRDSRA90M08H501Z',1,'Ho riproposto ha cambiato idea'),(13,'2025-01-24 00:00:00','15:00:00','jim_halpert',14,'VRDSRA90M08H501Z',1,'Daje'),(14,'2025-01-24 00:00:00','15:00:00','bruno_bianchi',14,'SCTMHL64C15F205Z',1,'Andata a buon fine'),(15,'2025-01-24 00:00:00','10:30:00','jim_halpert',13,'NRICLD95B51Z404Z',0,'Rifiutata ma vorrebbe un appuntamento'),(17,'2025-01-30 00:00:00','11:00:00','bruno_bianchi',16,'NRICLD95B51Z404Z',0,'Non andata a buon fine');
/*!40000 ALTER TABLE `interazione` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `VerificaDateInterazione` BEFORE INSERT ON `interazione` FOR EACH ROW BEGIN
    DECLARE dataRegistrazione DATE;

    -- Recupera la data di registrazione del cliente
    SELECT dataDiRegistrazione 
    INTO dataRegistrazione 
    FROM cliente 
    WHERE codicefiscale = NEW.cliente_codicefiscale;

    -- Verifica che la data dell'interazione non sia futura
    IF NEW.data > CURRENT_DATE THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'La data di interazione non può essere futura.';
    END IF;

    -- Verifica che la data dell'interazione non sia precedente alla registrazione del cliente
    IF NEW.data < dataRegistrazione THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'La data di interazione non può essere antecedente alla registrazione del cliente.';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `ImpostaOraInterazione` BEFORE INSERT ON `interazione` FOR EACH ROW BEGIN
    IF NEW.ora IS NULL THEN
        SET NEW.ora = HOUR(CURRENT_TIME());
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `offerta_proposta_accettata` BEFORE INSERT ON `interazione` FOR EACH ROW BEGIN
    -- Controlla se l'offerta è già stata proposta al cliente
    IF EXISTS (
        SELECT 1
        FROM interazione
        WHERE cliente_codicefiscale = NEW.cliente_codicefiscale
          AND offerta_idofferta = NEW.offerta_idofferta AND risultato=1
    ) THEN
        -- Segnala un errore
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Errore: L\'offerta è già stata proposta a questo cliente ed è stata accettata.';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `check_orari_interazione` BEFORE INSERT ON `interazione` FOR EACH ROW BEGIN
    -- Controlla che la data e l'ora siano coerenti con gli orari lavorativi
    IF WEEKDAY(NEW.data) > 4 OR
       TIME(NEW.ora) < '09:00:00' OR
       TIME(NEW.ora) > '18:00:00' THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Errore: giorno e ora non sono conformi agli orari e ai giorni di lavoro.' ;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `offerta`
--

DROP TABLE IF EXISTS `offerta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offerta` (
  `idofferta` int NOT NULL AUTO_INCREMENT,
  `tipo` int NOT NULL,
  `descrizione` varchar(100) NOT NULL,
  PRIMARY KEY (`idofferta`),
  UNIQUE KEY `idofferta_UNIQUE` (`idofferta`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offerta`
--

LOCK TABLES `offerta` WRITE;
/*!40000 ALTER TABLE `offerta` DISABLE KEYS */;
INSERT INTO `offerta` VALUES (11,1,''),(12,4,'Premio della lotteria'),(13,2,'Sconto del 30% per i nuovi clienti'),(14,1,'Fino al 31 gennaio sconti del 50% sulla vecchia collezione'),(15,3,'Regalo il giorno del compleanno dei clienti'),(16,2,'Sconto del 50%');
/*!40000 ALTER TABLE `offerta` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `prevent_duplicate_offer` BEFORE INSERT ON `offerta` FOR EACH ROW BEGIN
    IF EXISTS (
        SELECT 1 
        FROM offerta
        WHERE descrizione=NEW.descrizione
    ) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Non si posso inserire offerte con la stessa descrizione.';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `password` char(32) NOT NULL,
  `role` enum('SEGRETERIA','OPERATORE') NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('bruno_bianchi','7c6a180b36896a0a8c02787eeafb0e4c','OPERATORE'),('giovanni_storti','5f4dcc3b5aa765d61d8327deb882cf99','SEGRETERIA'),('jim_halpert','5f4dcc3b5aa765d61d8327deb882cf99','OPERATORE'),('mario_rossi','5f4dcc3b5aa765d61d8327deb882cf99','SEGRETERIA');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `VerificaUsernamePassword` BEFORE INSERT ON `users` FOR EACH ROW BEGIN
    -- Verifica che lo username sia univoco
    IF EXISTS (
        SELECT 1
        FROM users
        WHERE username = NEW.username
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Lo username deve essere univoco.';
    END IF;

    -- Verifica che lo username rispetti il formato
    IF NEW.username NOT REGEXP '^[a-zA-Z0-9._]{3,45}$' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Lo username deve avere tra 3 e 45 caratteri e può contenere solo lettere, numeri, punti e underscore.';
    END IF;

    -- Verifica la lunghezza della password
    IF CHAR_LENGTH(NEW.password) < 8 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La password deve avere almeno 8 caratteri.';
    END IF;

   
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping events for database 'crm_basedidati'
--
/*!50106 SET @save_time_zone= @@TIME_ZONE */ ;
/*!50106 DROP EVENT IF EXISTS `appuntamentiScaduti` */;
DELIMITER ;;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;;
/*!50003 SET character_set_client  = utf8mb4 */ ;;
/*!50003 SET character_set_results = utf8mb4 */ ;;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;;
/*!50003 SET @saved_time_zone      = @@time_zone */ ;;
/*!50003 SET time_zone             = 'SYSTEM' */ ;;
/*!50106 CREATE*/ /*!50117 DEFINER=`root`@`localhost`*/ /*!50106 EVENT `appuntamentiScaduti` ON SCHEDULE EVERY 1 DAY STARTS '2025-01-21 14:04:04' ON COMPLETION NOT PRESERVE ENABLE DO DELETE FROM appuntamento
WHERE data < CURDATE() - INTERVAL 7 DAY */ ;;
/*!50003 SET time_zone             = @saved_time_zone */ ;;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;;
/*!50003 SET character_set_client  = @saved_cs_client */ ;;
/*!50003 SET character_set_results = @saved_cs_results */ ;;
/*!50003 SET collation_connection  = @saved_col_connection */ ;;
/*!50106 DROP EVENT IF EXISTS `CleanOldCustomers` */;;
DELIMITER ;;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;;
/*!50003 SET character_set_client  = utf8mb4 */ ;;
/*!50003 SET character_set_results = utf8mb4 */ ;;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;;
/*!50003 SET @saved_time_zone      = @@time_zone */ ;;
/*!50003 SET time_zone             = 'SYSTEM' */ ;;
/*!50106 CREATE*/ /*!50117 DEFINER=`root`@`localhost`*/ /*!50106 EVENT `CleanOldCustomers` ON SCHEDULE EVERY 1 DAY STARTS '2025-01-01 02:00:00' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
    -- Elimina i clienti che non hanno avuto interazioni negli ultimi 3 anni
    DELETE FROM cliente
    WHERE cliente_codicefiscale NOT IN (
        SELECT DISTINCT cliente_codicefiscale
        FROM interazione
        WHERE data_interazione >= CURDATE() - INTERVAL 3 YEAR
    );
END */ ;;
/*!50003 SET time_zone             = @saved_time_zone */ ;;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;;
/*!50003 SET character_set_client  = @saved_cs_client */ ;;
/*!50003 SET character_set_results = @saved_cs_results */ ;;
/*!50003 SET collation_connection  = @saved_col_connection */ ;;
/*!50106 DROP EVENT IF EXISTS `NotificaAppuntamenti` */;;
DELIMITER ;;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;;
/*!50003 SET character_set_client  = utf8mb4 */ ;;
/*!50003 SET character_set_results = utf8mb4 */ ;;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;;
/*!50003 SET @saved_time_zone      = @@time_zone */ ;;
/*!50003 SET time_zone             = 'SYSTEM' */ ;;
/*!50106 CREATE*/ /*!50117 DEFINER=`root`@`localhost`*/ /*!50106 EVENT `NotificaAppuntamenti` ON SCHEDULE EVERY 1 DAY STARTS '2025-01-20 19:06:23' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
    -- Aggiorna lo stato degli appuntamenti imminenti
    UPDATE appuntamento
    SET dettagli = CONCAT('Promemoria: appuntamento imminente il ', data, ' alle ', ora)
    WHERE data = CURRENT_DATE + INTERVAL 1 DAY;
END */ ;;
/*!50003 SET time_zone             = @saved_time_zone */ ;;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;;
/*!50003 SET character_set_client  = @saved_cs_client */ ;;
/*!50003 SET character_set_results = @saved_cs_results */ ;;
/*!50003 SET collation_connection  = @saved_col_connection */ ;;
/*!50106 DROP EVENT IF EXISTS `PulisciInterazioniVecchie` */;;
DELIMITER ;;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;;
/*!50003 SET character_set_client  = utf8mb4 */ ;;
/*!50003 SET character_set_results = utf8mb4 */ ;;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;;
/*!50003 SET @saved_time_zone      = @@time_zone */ ;;
/*!50003 SET time_zone             = 'SYSTEM' */ ;;
/*!50106 CREATE*/ /*!50117 DEFINER=`root`@`localhost`*/ /*!50106 EVENT `PulisciInterazioniVecchie` ON SCHEDULE EVERY 1 MONTH STARTS '2025-01-20 19:06:15' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
    DELETE FROM interazione WHERE data < DATE_SUB(CURRENT_DATE, INTERVAL 2 YEAR);
END */ ;;
/*!50003 SET time_zone             = @saved_time_zone */ ;;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;;
/*!50003 SET character_set_client  = @saved_cs_client */ ;;
/*!50003 SET character_set_results = @saved_cs_results */ ;;
/*!50003 SET collation_connection  = @saved_col_connection */ ;;
DELIMITER ;
/*!50106 SET TIME_ZONE= @save_time_zone */ ;

--
-- Dumping routines for database 'crm_basedidati'
--
/*!50003 DROP PROCEDURE IF EXISTS `customer` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `customer`()
BEGIN 
	-- Handler per le eccezioni SQL
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN 
		ROLLBACK;
        RESIGNAL;
    END;
    -- Imposta il livello di isolamento della transazione
    SET TRANSACTION ISOLATION LEVEL repeatable read;
	 -- Inizia una transazione
    START TRANSACTION;
	
	-- Seleziona i dati specifici
    SELECT 
        c.codicefiscale AS cliente_codicefiscale,
        c.nome AS cliente_nome,
        c.cognome AS cliente_cognome,
        c.dataDiNascita AS cliente_data_nascita,
        c.dataDiRegistrazione AS cliente_data_di_registrazione,
        c.segreteria AS cliente_segreteria,
        cont.valore AS contatto_valore,
        cont.tipo AS contatto_tipo,
        addr.via AS indirizzo_via,
        addr.città AS indirizzo_citta,
        addr.cap AS indirizzo_cap
    FROM 
        cliente AS c
    JOIN 
        contatti AS cont ON c.codicefiscale = cont.cliente_codicefiscale
	JOIN 
        indirizzi AS addr ON c.codicefiscale = addr.cliente_codicefiscale
    ORDER BY c.cognome; -- Ordina per cognome
     -- Completa la transazione 
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getAppointment` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAppointment`()
BEGIN 
	-- Handler per le eccezioni SQL
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN 
		ROLLBACK;
        RESIGNAL;
    END;

    -- Imposta il livello di isolamento per una transazione
    SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

    -- Avvia la transazione
    START TRANSACTION;

    -- Seleziona le informazioni degli appuntamenti
    SELECT distinct
        a.sede AS Branch,
        a.ora AS Time,
        a.data AS Date,
        a.cliente_codicefiscale AS Customer,
        i.operatore AS Operator
    FROM appuntamento AS a
    JOIN interazione AS i 
        ON a.cliente_codicefiscale = i.cliente_codicefiscale;

    
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getOffers` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getOffers`()
BEGIN
	-- Handler per le eccezioni SQL
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN 
		ROLLBACK;
        RESIGNAL;
    END;
    -- Imposta il livello di isolamento
    SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

    -- Inizia la transazione
    START TRANSACTION;

    -- Recupera le offerte
    SELECT 
        descrizione AS Descrizione,
        tipo AS Tipo
    FROM 
        offerta;

    -- Conferma la transazione
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertAppointment` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertAppointment`(
    IN var_data DATE,
    IN var_time TIME,
    IN var_cliente VARCHAR(25),
    IN var_branch VARCHAR(45),
    IN var_operatore VARCHAR(45)
)
BEGIN 
    DECLARE var_id_inter INT;

    -- Gestione degli errori
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    -- Imposta il livello di isolamento e avvia la transazione
    SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;  -- Livello di isolamento migliorato
    START TRANSACTION;

    -- Recupera l'ID dell'interazione per l'operatore e il cliente
    SELECT i.idinterazione
    INTO var_id_inter
    FROM interazione as i
    WHERE i.operatore = var_operatore 
    AND i.cliente_codicefiscale = var_cliente 
    AND var_data > i.data  -- Controllo della data
    ORDER BY i.data DESC, i.ora DESC
    LIMIT 1;

    -- Controlla se l'ID è valido
    IF var_id_inter IS NOT NULL THEN
        -- Inserisce il nuovo appuntamento (il controllo sugli appuntamenti esistenti è gestito dal trigger)
        INSERT INTO appuntamento (sede, data, ora, cliente_codicefiscale, interazione_idinterazione)
        VALUES (var_branch, var_data, var_time, var_cliente, var_id_inter);
    ELSE
        SIGNAL SQLSTATE '45001' 
        SET MESSAGE_TEXT = 'Errore: Nessuna interazione valida trovata per il cliente.';
    END IF;

    -- Conferma la transazione
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertCustomer` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertCustomer`(
    IN var_codiceFiscale VARCHAR(25),
    IN var_nome VARCHAR(45),
    IN var_cognome VARCHAR(45),
    IN var_dataDiNascita DATETIME,
    IN var_cap VARCHAR(5),
    IN var_address VARCHAR(255),
    IN var_city VARCHAR(45),
    IN var_segreteria VARCHAR(45)
)
BEGIN
    -- Dichiarazione di variabili locali
    DECLARE cliente_count INT;

    -- Gestione degli errori
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK; -- Annulla la transazione
        RESIGNAL; -- Propaga ulteriormente l'errore
    END;

    -- Setto il livello di isolamento
    SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;  -- Livello di isolamento migliorato
    -- Inizio della transazione
    START TRANSACTION;


    -- Verifica se il cliente esiste già
    SELECT COUNT(*)
    INTO cliente_count
    FROM cliente
    WHERE codicefiscale = var_codiceFiscale;

    -- Se il cliente esiste già, lancia un errore
    IF cliente_count > 0 THEN
        SIGNAL SQLSTATE '45003' SET MESSAGE_TEXT = 'Errore: Il cliente esiste già.';
    END IF;

    -- Inserimento del cliente
    INSERT INTO cliente (codicefiscale, nome, cognome, dataDiNascita, segreteria)
    VALUES (var_codiceFiscale, var_nome, var_cognome, var_dataDiNascita, var_segreteria);

    -- Inserimento dell'indirizzo
    INSERT INTO indirizzi (via, cap, città, cliente_codicefiscale)
    VALUES (var_address, var_cap, var_city, var_codiceFiscale);

    -- Completamento della transazione
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertEmail` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertEmail`(
    IN var_codiceFiscale VARCHAR(25),
    IN var_email VARCHAR(45)
)
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK; -- Annulla la transazione
        RESIGNAL; -- Propaga ulteriormente l'errore
    END;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
    START TRANSACTION;
    IF var_email IS NOT NULL THEN  
		INSERT INTO contatti (valore, tipo,cliente_codicefiscale)
		VALUES (var_email, 'email', var_codiceFiscale);
	ELSE 
		SIGNAL SQLSTATE '45001' 
        SET MESSAGE_TEXT = 'Errore: Email non trovata non valida';
	END IF;
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertPhone` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertPhone`(
    IN var_codiceFiscale VARCHAR(25),
    IN var_phone VARCHAR(45)
)
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK; -- Annulla la transazione
        RESIGNAL; -- Propaga ulteriormente l'errore
    END;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
    START TRANSACTION;
    IF var_phone IS NOT NULL THEN 
		INSERT INTO contatti (valore, tipo,cliente_codicefiscale)
		VALUES (var_phone, 'phone', var_codiceFiscale);
	ELSE 
		SIGNAL SQLSTATE '45001' 
        SET MESSAGE_TEXT = 'Errore: Numero di telefono non valido o non trovato';
	END IF;
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_offer` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_offer`(
    IN var_type_offer INT,
    IN var_description_offer VARCHAR(225)
)
BEGIN 
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN 
        ROLLBACK;
        RESIGNAL;
    END;

    -- Imposta il livello di isolamento della transazione
    SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

    -- Inizia la transazione
    START TRANSACTION;

    -- Controlla che i parametri siano validi
    IF var_description_offer IS NULL OR var_description_offer = '' THEN
        SIGNAL SQLSTATE '45001' 
        SET MESSAGE_TEXT = 'Errore: Descrizione dell\'offerta non valida.';
    ELSEIF var_type_offer IS NULL OR var_type_offer = '' THEN
        SIGNAL SQLSTATE '45002' 
        SET MESSAGE_TEXT = 'Errore: Tipo offerta non valido.';
    ELSE
        -- Inserisci i dati nell'offerta
        INSERT INTO offerta (descrizione, tipo)
        VALUES (var_description_offer, var_type_offer);
    END IF;

    -- Commit della transazione
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `login` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `login`(
    IN var_username VARCHAR(45),
    IN var_pass CHAR(32),
    OUT var_role INT
)
BEGIN
    DECLARE var_user_role ENUM('SEGRETERIA', 'OPERATORE') DEFAULT NULL;

    -- Inizializza il parametro OUT per evitare problemi JDBC
    SET var_role = -1;  

    -- Seleziona il ruolo dell'utente dalla tabella users
    SELECT role INTO var_user_role
    FROM users
    WHERE username = var_username AND `password` = MD5(var_pass);

    -- Controlla se l'utente è stato trovato
    IF var_user_role IS NOT NULL THEN
        IF var_user_role = 'SEGRETERIA' THEN 
            SET var_role = 1;
        ELSEIF var_user_role = 'OPERATORE' THEN
            SET var_role = 2;
        END IF;
    ELSE
        SET var_role = 0; -- Nessun utente trovato
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `register` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `register`(
    IN var_username VARCHAR(50),
    IN var_password VARCHAR(255),
    IN var_role INT,
    OUT result INT
)
BEGIN
    -- Variabili per il risultato e l'errore
    DECLARE ruolo VARCHAR(50);
    DECLARE error_message VARCHAR(255);
    
    -- Gestione degli errori
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        -- Log dettagliato dell'errore
        GET DIAGNOSTICS CONDITION 1
            error_message = MESSAGE_TEXT;

        -- Se l'errore non contiene un messaggio, usa un messaggio di fallback
        IF error_message IS NULL THEN
            SET error_message = 'Errore sconosciuto durante la registrazione.';
        END IF;

        -- Rollback e invio del messaggio d'errore
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = error_message;
    END;

    -- Verifica se il ruolo è valido
    IF var_role NOT IN (1, 2) THEN
        SIGNAL SQLSTATE '45004' SET MESSAGE_TEXT = 'Errore: Ruolo non valido';
    END IF;

    -- Verifica se l'username è già presente
    IF EXISTS (
        SELECT 1
        FROM users
        WHERE username = var_username
    ) THEN
        SIGNAL SQLSTATE '45005' SET MESSAGE_TEXT = 'Errore: Username già in uso';
    END IF;

    -- Impostazione della password (MD5)
    SET var_password = MD5(var_password);

    -- Inizio della transazione
    SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;  -- Usato un livello più performante rispetto a SERIALIZABLE
    START TRANSACTION;

    -- Determina il ruolo in base al parametro var_role
    IF var_role = 1 THEN
        SET ruolo = 'SEGRETERIA';
    ELSEIF var_role = 2 THEN
        SET ruolo = 'OPERATORE';
    ELSE
        SIGNAL SQLSTATE '45005' SET MESSAGE_TEXT = 'Ruolo errato';
    END IF;

    -- Inserimento del nuovo utente
    INSERT INTO users (username, password, role)
    VALUES (var_username, var_password, ruolo);

    -- Verifica dell'inserimento
    IF ROW_COUNT() = 1 THEN
        SET result = 1;  -- Registrazione riuscita
    ELSE
        ROLLBACK;
        SIGNAL SQLSTATE '45006' SET MESSAGE_TEXT = 'Errore: Impossibile registrare l\'utente';
    END IF;

    -- Conferma della transazione
    COMMIT;

    -- Restituzione del risultato
    SELECT result;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `reportCustomers` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `reportCustomers`(
    IN start_date DATE,
    IN end_date DATE
)
BEGIN 
    -- Dichiarazione del gestore di errore
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    -- Verifica che la data di inizio non sia successiva alla data di fine
    IF start_date > end_date THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Start date non può essere maggiore di end date';
    END IF;

    -- Imposta il livello di isolamento
    SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
    
    -- Avvia la transazione
    START TRANSACTION;

    -- Creazione di una tabella temporanea consolidata per le informazioni necessarie
    CREATE TEMPORARY TABLE temp_report AS
    SELECT 
        c.codicefiscale AS cliente_codicefiscale,
        c.nome AS cliente_nome,
        c.cognome AS cliente_cognome,
        COUNT(i.cliente_codicefiscale) AS interazioni_cliente,
        COUNT(CASE WHEN i.risultato = 1 THEN 1 END) AS numero_offerte_accettate,
        GROUP_CONCAT(DISTINCT CASE WHEN i.risultato=1 THEN  o.descrizione END ORDER BY o.descrizione ASC SEPARATOR ', ') AS tipi_offerte_accettate
    FROM cliente AS c
    LEFT JOIN interazione AS i 
        ON c.codicefiscale = i.cliente_codicefiscale 
        AND i.data BETWEEN start_date AND end_date
    LEFT JOIN offerta AS o 
        ON i.offerta_idofferta = o.idofferta
    GROUP BY c.codicefiscale, c.nome, c.cognome;
    
    -- Selezione finale del report
    SELECT 
        cliente_codicefiscale,
        cliente_nome,
        cliente_cognome,
        interazioni_cliente,
        numero_offerte_accettate,
        tipi_offerte_accettate
    FROM temp_report
    ORDER BY cliente_cognome;

    -- Rimozione della tabella temporanea
    DROP TEMPORARY TABLE IF EXISTS temp_report;

    -- Completa la transazione
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `reportNotes` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `reportNotes`(IN var_cliente VARCHAR(25))
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    -- Verifica che il cliente esista
    IF NOT EXISTS (
        SELECT 1 
        FROM cliente 
        WHERE codicefiscale = var_cliente
    ) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Errore: il codice fiscale fornito non corrisponde a un cliente esistente.';
    END IF;

    -- Imposta il livello di isolamento serializable per garantire la consistenza
    SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
    START TRANSACTION;

    -- Creazione della tabella temporanea per le interazioni del cliente
    CREATE TEMPORARY TABLE temp_interazioni AS
    SELECT 
        i.idinterazione,
        i.cliente_codicefiscale,
        i.data AS data_interazione,
        i.ora AS ora_interazione,
        i.risultato AS nota_risultato,
        i.dettagli AS nota_dettagli,
        i.operatore AS operatore_interazione,
        i.offerta_idofferta
    FROM interazione AS i
    WHERE i.cliente_codicefiscale = var_cliente;

    -- Creazione della tabella temporanea per le offerte
    CREATE TEMPORARY TABLE temp_offerte AS
    SELECT 
        o.idofferta,
        o.descrizione AS offerta_scelta
    FROM offerta AS o;

    -- Creazione della tabella temporanea per gli appuntamenti
    CREATE TEMPORARY TABLE temp_appuntamenti AS
    SELECT 
        a.interazione_idinterazione,
        a.sede AS appuntamento_sede,
        a.data AS appuntamento_data,
        a.ora AS appuntamento_ora
    FROM appuntamento AS a;

    -- Generazione del report combinando le tabelle temporanee
    SELECT 
        c.nome AS cliente_nome,
        c.cognome AS cliente_cognome,
        ti.data_interazione,
        ti.ora_interazione,
        ti.nota_risultato,
        ti.nota_dettagli,
        ti.operatore_interazione,
        toff.offerta_scelta,
        tapp.appuntamento_sede,
        tapp.appuntamento_data,
        tapp.appuntamento_ora
    FROM cliente AS c
    INNER JOIN temp_interazioni AS ti
        ON c.codicefiscale = ti.cliente_codicefiscale
    LEFT JOIN temp_offerte AS toff
        ON ti.offerta_idofferta = toff.idofferta
    LEFT JOIN temp_appuntamenti AS tapp
        ON ti.idinterazione = tapp.interazione_idinterazione
    ORDER BY ti.data_interazione DESC, ti.ora_interazione DESC;

    -- Rimozione delle tabelle temporanee
    DROP TEMPORARY TABLE IF EXISTS temp_interazioni;
    DROP TEMPORARY TABLE IF EXISTS temp_offerte;
    DROP TEMPORARY TABLE IF EXISTS temp_appuntamenti;

    -- Completamento della transazione
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `searchContact` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `searchContact`(IN var_cliente VARCHAR(25))
BEGIN
    -- Validazione dell'input
    IF var_cliente IS NULL OR var_cliente = '' THEN
        SIGNAL SQLSTATE '45001' SET MESSAGE_TEXT = 'Errore: Cliente non valido.';
    END IF;

    -- Livello di isolamento per l'operazione di lettura
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

    -- Inizia la transazione 
    START TRANSACTION;

    -- Query per recuperare i contatti
    IF EXISTS (
        SELECT 1 
        FROM contatti 
        WHERE cliente_codicefiscale = var_cliente
    ) THEN
        SELECT 
            valore AS ContactValue,
            tipo AS Contact
        FROM 
            contatti
        WHERE 
            cliente_codicefiscale = var_cliente;
    ELSE
        SIGNAL SQLSTATE '45002' SET MESSAGE_TEXT = 'Nessun contatto trovato per il cliente specificato.';
    END IF;

    -- Convalida della transazione 
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `updateContacts` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateContacts`(
    IN var_valore VARCHAR(45), 
    IN var_tipo VARCHAR(45), 
    IN var_cliente VARCHAR(25)
)
BEGIN
    -- Gestione degli errori
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    -- Validazione dei parametri
    IF var_valore IS NULL OR var_valore = '' THEN
        SIGNAL SQLSTATE '45001' SET MESSAGE_TEXT = 'Errore: Valore non valido.';
    END IF;

    IF var_tipo NOT IN ('email', 'phone') THEN
        SIGNAL SQLSTATE '45002' SET MESSAGE_TEXT = 'Errore: Tipo non valido.';
    END IF;

    IF var_cliente IS NULL OR var_cliente = '' THEN
        SIGNAL SQLSTATE '45003' SET MESSAGE_TEXT = 'Errore: Cliente non valido.';
    END IF;

    -- Imposta il livello di isolamento
    SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

    -- Inizia la transazione
    START TRANSACTION;

    -- Esegue l'update sulla tabella contatti
    UPDATE contatti 
    SET valore = var_valore
    WHERE cliente_codicefiscale = var_cliente AND tipo = var_tipo;

    -- Verifica se l'update ha modificato righe
    IF ROW_COUNT() = 0 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45004' SET MESSAGE_TEXT = 'Errore: Nessun contatto trovato per aggiornamento.';
    ELSE
        -- Conferma la transazione
        COMMIT;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `updateIndirizzi` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateIndirizzi`(
    IN var_via VARCHAR(25), 
    IN var_cap VARCHAR(5), 
    IN var_città VARCHAR(25),
    IN var_cliente VARCHAR(25)
)
BEGIN
    -- Gestione degli errori
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        -- Rollback in caso di errore
        ROLLBACK;
        RESIGNAL;
    END;

    -- Validazione dei parametri
    IF var_via IS NULL OR var_via = '' THEN
        SIGNAL SQLSTATE '45001' SET MESSAGE_TEXT = 'Errore: Via non valida.';
    END IF;

    IF var_cap IS NULL OR var_cap = '' OR CHAR_LENGTH(var_cap) != 5 THEN
        SIGNAL SQLSTATE '45002' SET MESSAGE_TEXT = 'Errore: CAP non valido.';
    END IF;

    IF var_città IS NULL OR var_città = '' THEN
        SIGNAL SQLSTATE '45003' SET MESSAGE_TEXT = 'Errore: Città non valida.';
    END IF;

    IF var_cliente IS NULL OR var_cliente = '' THEN
        SIGNAL SQLSTATE '45004' SET MESSAGE_TEXT = 'Errore: Cliente non valido.';
    END IF;

    -- Imposta il livello di isolamento
    SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

    -- Inizia la transazione
    START TRANSACTION;

    -- Esegue l'update e verifica il risultato
    UPDATE indirizzi
    SET via = var_via, cap = var_cap, città = var_città
    WHERE cliente_codicefiscale = var_cliente;

    -- Verifica se l'aggiornamento ha avuto successo
    IF ROW_COUNT() = 0 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cliente non trovato.';
    ELSE
        -- Conferma la transazione
        COMMIT;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `writeNote` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `writeNote`(
    IN var_risultato BOOLEAN,
    IN var_dettagli  VARCHAR(100),
    IN var_cliente VARCHAR(25),
    IN var_ora  TIME,
    IN var_data DATETIME,
    IN var_operatore VARCHAR(45),
    IN var_dofferta VARCHAR(225),
    IN var_branch_app VARCHAR(45),
    IN var_app_date DATE,
    IN var_app_time TIME
)
BEGIN
    DECLARE var_id_off INT;
    DECLARE var_id_inter INT;

    -- Gestione degli errori
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    -- Validazione dei parametri principali
    IF var_cliente IS NULL OR var_cliente = '' THEN
        SIGNAL SQLSTATE '45002' 
        SET MESSAGE_TEXT = 'Errore: Cliente non valido.';
    END IF;

    IF var_dofferta IS NULL OR var_dofferta = '' THEN
        SIGNAL SQLSTATE '45003' 
        SET MESSAGE_TEXT = 'Errore: Descrizione offerta non valida.';
    END IF;

    -- Controllo validità data appuntamento
    IF var_app_date IS NOT NULL AND var_app_date < CURRENT_DATE THEN
        SIGNAL SQLSTATE '45004' 
        SET MESSAGE_TEXT = 'Errore: Data appuntamento non valida.';
    END IF;

    -- Imposta il livello di isolamento e avvia la transazione
    SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
    START TRANSACTION;

    -- Recupera l'ID dell'offerta corrispondente
    SELECT idofferta
    INTO var_id_off
    FROM offerta
    WHERE var_dofferta = offerta.descrizione;

    -- Controllo esistenza offerta
    IF var_id_off IS NULL THEN
        SIGNAL SQLSTATE '45005' 
        SET MESSAGE_TEXT = 'Errore: Offerta non trovata.';
    END IF;

    -- Inserisce la nuova interazione
    INSERT INTO interazione(data, ora, operatore, offerta_idofferta, cliente_codicefiscale, risultato, dettagli)
    VALUES (var_data, var_ora, var_operatore, var_id_off, var_cliente, var_risultato, var_dettagli);

    -- Recupera l'ID dell'interazione appena creata
    SET var_id_inter = LAST_INSERT_ID();

    -- Inserisce l'appuntamento solo se i parametri sono validi
    IF var_branch_app IS NOT NULL AND var_app_date IS NOT NULL AND var_app_time IS NOT NULL THEN
        INSERT INTO appuntamento (sede, data, ora, cliente_codicefiscale, interazione_idinterazione)
        VALUES (var_branch_app, var_app_date, var_app_time, var_cliente, var_id_inter);
    END IF;

    -- Conferma la transazione
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-30 13:36:32
