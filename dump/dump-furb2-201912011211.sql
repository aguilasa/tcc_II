-- MySQL dump 10.13  Distrib 5.5.62, for Win64 (AMD64)
--
-- Host: localhost    Database: furb2
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.38-MariaDB

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
-- Table structure for table `alergias`
--

DROP TABLE IF EXISTS `alergias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alergias` (
  `cd_alergia` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cd_usuario` int(10) unsigned NOT NULL,
  `ds_alergia` varchar(255) DEFAULT NULL,
  `ds_observacao` varchar(255) DEFAULT NULL,
  `dt_alergia` date DEFAULT NULL,
  `qt_remedios` int(10) unsigned DEFAULT NULL,
  `ds_remedios` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cd_alergia`),
  KEY `cd_usuario` (`cd_usuario`),
  CONSTRAINT `alergias_ibfk_1` FOREIGN KEY (`cd_usuario`) REFERENCES `usuario` (`cd_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `alerta`
--

DROP TABLE IF EXISTS `alerta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alerta` (
  `cd_evento` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cd_usuario` int(10) unsigned NOT NULL,
  `ds_alerta` varchar(255) DEFAULT NULL,
  `ie_apresentar` char(1) DEFAULT NULL,
  `ie_apresentado` char(1) DEFAULT NULL,
  `dt_inicio_alerta` date DEFAULT NULL,
  `ie_tipo_alerta` char(1) DEFAULT NULL,
  `nr_evento_origem` int(11) DEFAULT NULL,
  `dt_fim_alerta` date DEFAULT NULL,
  `qt_min_intervalo` int(10) unsigned DEFAULT NULL,
  `nm_local` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cd_evento`),
  KEY `cd_usuario` (`cd_usuario`),
  CONSTRAINT `alerta_ibfk_1` FOREIGN KEY (`cd_usuario`) REFERENCES `usuario` (`cd_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aplicativo`
--

DROP TABLE IF EXISTS `aplicativo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aplicativo` (
  `nr_versao` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dt_versao` date NOT NULL,
  `ds_loc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`nr_versao`,`dt_versao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cirurgia`
--

DROP TABLE IF EXISTS `cirurgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cirurgia` (
  `cd_cirurgia` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cd_usuario` int(10) unsigned NOT NULL,
  `ds_cirurgia` varchar(255) DEFAULT NULL,
  `dt_cirurgia` date DEFAULT NULL,
  `qt_remedios` int(10) unsigned DEFAULT NULL,
  `ds_remedios` varchar(255) DEFAULT NULL,
  `ds_observacao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cd_cirurgia`),
  KEY `cd_usuario` (`cd_usuario`),
  CONSTRAINT `cirurgia_ibfk_1` FOREIGN KEY (`cd_usuario`) REFERENCES `usuario` (`cd_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `diaagenda`
--

DROP TABLE IF EXISTS `diaagenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diaagenda` (
  `nr_sequencia` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dt_agenda` date DEFAULT NULL,
  PRIMARY KEY (`nr_sequencia`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `diaagendaalerta`
--

DROP TABLE IF EXISTS `diaagendaalerta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diaagendaalerta` (
  `nr_sequencia` int(10) unsigned NOT NULL,
  `cd_evento` int(10) unsigned NOT NULL,
  PRIMARY KEY (`nr_sequencia`,`cd_evento`),
  KEY `cd_evento` (`cd_evento`),
  CONSTRAINT `diaagendaalerta_ibfk_1` FOREIGN KEY (`nr_sequencia`) REFERENCES `diaagenda` (`nr_sequencia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `diaagendaalerta_ibfk_2` FOREIGN KEY (`cd_evento`) REFERENCES `alerta` (`cd_evento`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `doenca`
--

DROP TABLE IF EXISTS `doenca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doenca` (
  `cd_doenca` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cd_usuario` int(10) unsigned NOT NULL,
  `ds_doenca` varchar(255) DEFAULT NULL,
  `ds_observacao` varchar(255) DEFAULT NULL,
  `dt_doenca` date DEFAULT NULL,
  `qt_remedios` varchar(255) DEFAULT NULL,
  `ds_remedios` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cd_doenca`),
  KEY `cd_usuario` (`cd_usuario`),
  CONSTRAINT `doenca_ibfk_1` FOREIGN KEY (`cd_usuario`) REFERENCES `usuario` (`cd_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `informacoes`
--

DROP TABLE IF EXISTS `informacoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `informacoes` (
  `nr_sequencia` int(10) unsigned NOT NULL,
  `cd_usuario` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ds_observacao` varchar(255) DEFAULT NULL,
  `ie_tipagem_sang` varchar(255) DEFAULT NULL,
  `ds_remedio` varchar(255) DEFAULT NULL,
  `qt_remedio` int(11) DEFAULT NULL,
  `dt_nasc` date DEFAULT NULL,
  `vl_peso` float(5,2) DEFAULT NULL,
  `vl_altura` float(5,2) DEFAULT NULL,
  `vl_imc` float(5,2) DEFAULT NULL,
  `ds_cpf` varchar(255) DEFAULT NULL,
  `ds_rg` varchar(255) DEFAULT NULL,
  `nm_pai` varchar(255) DEFAULT NULL,
  `nm_mae` varchar(255) DEFAULT NULL,
  `nm_responsavel` varchar(255) DEFAULT NULL,
  `ie_tipo_resp` char(1) DEFAULT NULL,
  `nm_social` varchar(255) DEFAULT NULL,
  `ds_nacionalidade` int(11) DEFAULT NULL,
  `qt_idade` int(11) DEFAULT NULL,
  PRIMARY KEY (`nr_sequencia`),
  KEY `cd_usuario` (`cd_usuario`),
  CONSTRAINT `informacoes_ibfk_1` FOREIGN KEY (`cd_usuario`) REFERENCES `usuario` (`cd_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `medicamento`
--

DROP TABLE IF EXISTS `medicamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicamento` (
  `cd_medicamento` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cd_usuario` int(10) unsigned NOT NULL,
  `ds_observacao` varchar(255) DEFAULT NULL,
  `ds_doenca` varchar(255) DEFAULT NULL,
  `dt_medicamento_inicio` date DEFAULT NULL,
  `qt_remedio` int(10) unsigned DEFAULT NULL,
  `vl_min_intervalo` date DEFAULT NULL,
  `dt_medicamento_fim` date DEFAULT NULL,
  `ds_remedio` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cd_medicamento`),
  KEY `cd_usuario` (`cd_usuario`),
  CONSTRAINT `medicamento_ibfk_1` FOREIGN KEY (`cd_usuario`) REFERENCES `usuario` (`cd_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `cd_usuario` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nm_usuario` varchar(255) DEFAULT NULL,
  `ds_senha` varchar(255) DEFAULT NULL,
  `ds_email` varchar(255) DEFAULT NULL,
  `dt_criacao` date DEFAULT NULL,
  `dt_atualizacao` date DEFAULT NULL,
  `ie_notific` char(1) DEFAULT NULL,
  PRIMARY KEY (`cd_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'furb2'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-01 11:11:50
