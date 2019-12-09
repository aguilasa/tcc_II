-- MySQL dump 10.13  Distrib 5.5.62, for Win64 (AMD64)
--
-- Host: localhost    Database: furb
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
-- Table structure for table `diagnostico`
--

DROP TABLE IF EXISTS `diagnostico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diagnostico` (
  `iddiagnostico` int(11) NOT NULL AUTO_INCREMENT,
  `resultado` varchar(45) DEFAULT NULL,
  `descricao` varchar(200) DEFAULT NULL,
  `data` date DEFAULT NULL,
  PRIMARY KEY (`iddiagnostico`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagnostico`
--

LOCK TABLES `diagnostico` WRITE;
/*!40000 ALTER TABLE `diagnostico` DISABLE KEYS */;
INSERT INTO `diagnostico` VALUES (1,'Dengue','Paciente apresenta dengue','2019-10-02'),(2,'Sarampo','Paciente foi confirmado com sarampo','2018-01-04');
/*!40000 ALTER TABLE `diagnostico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diagnostico_has_doencas`
--

DROP TABLE IF EXISTS `diagnostico_has_doencas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diagnostico_has_doencas` (
  `diagnostico_iddiagnostico` int(11) NOT NULL,
  `doencas_iddoencas` int(11) NOT NULL,
  PRIMARY KEY (`diagnostico_iddiagnostico`,`doencas_iddoencas`),
  KEY `fk_diagnostico_has_doencas_doencas1_idx` (`doencas_iddoencas`),
  KEY `fk_diagnostico_has_doencas_diagnostico1_idx` (`diagnostico_iddiagnostico`),
  CONSTRAINT `fk_diagnostico_has_doencas_diagnostico1` FOREIGN KEY (`diagnostico_iddiagnostico`) REFERENCES `diagnostico` (`iddiagnostico`),
  CONSTRAINT `fk_diagnostico_has_doencas_doencas1` FOREIGN KEY (`doencas_iddoencas`) REFERENCES `doencas` (`iddoencas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagnostico_has_doencas`
--

LOCK TABLES `diagnostico_has_doencas` WRITE;
/*!40000 ALTER TABLE `diagnostico_has_doencas` DISABLE KEYS */;
INSERT INTO `diagnostico_has_doencas` VALUES (1,1),(2,3);
/*!40000 ALTER TABLE `diagnostico_has_doencas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doencas`
--

DROP TABLE IF EXISTS `doencas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doencas` (
  `iddoencas` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(200) DEFAULT NULL,
  `duracaoMedia` int(11) DEFAULT NULL,
  `nome` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`iddoencas`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doencas`
--

LOCK TABLES `doencas` WRITE;
/*!40000 ALTER TABLE `doencas` DISABLE KEYS */;
INSERT INTO `doencas` VALUES (1,'doenca tropical',15,'Dengue'),(2,'doenca predominante do verao',10,'Febre Amarela'),(3,'contagio rapido',7,'Sarampo');
/*!40000 ALTER TABLE `doencas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endereco` (
  `idendereco` int(11) NOT NULL AUTO_INCREMENT,
  `rua` varchar(45) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `cep` int(11) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `pais` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idendereco`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES (1,'Santa Tereza',32,'Liberdade',88888888,'Sao Paulo','SP','Brasil'),(2,'Avenida da Nacao',400,'Bairroso',4488888,'Santos','SP','Brasil'),(3,'Terezina',12,'Tereza',877877,'Curitiba','PR','Brasil');
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ponto`
--

DROP TABLE IF EXISTS `ponto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ponto` (
  `idponto` int(11) NOT NULL AUTO_INCREMENT,
  `categoria` varchar(45) DEFAULT NULL,
  `diagnostico_iddiagnostico` int(11) NOT NULL,
  `dataInicio` date DEFAULT NULL,
  `duracaoPonto` int(11) DEFAULT NULL,
  `endereco_idendereco` int(11) DEFAULT NULL,
  PRIMARY KEY (`idponto`),
  KEY `fk_ponto_diagnostico1_idx` (`diagnostico_iddiagnostico`),
  KEY `ponto_fk` (`endereco_idendereco`),
  CONSTRAINT `fk_ponto_diagnostico1` FOREIGN KEY (`diagnostico_iddiagnostico`) REFERENCES `diagnostico` (`iddiagnostico`),
  CONSTRAINT `ponto_fk` FOREIGN KEY (`endereco_idendereco`) REFERENCES `endereco` (`idendereco`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ponto`
--

LOCK TABLES `ponto` WRITE;
/*!40000 ALTER TABLE `ponto` DISABLE KEYS */;
INSERT INTO `ponto` VALUES (1,'grave',1,NULL,NULL,NULL),(2,'medio',2,NULL,NULL,NULL);
/*!40000 ALTER TABLE `ponto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sintomas`
--

DROP TABLE IF EXISTS `sintomas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sintomas` (
  `idsintomas` int(11) NOT NULL AUTO_INCREMENT,
  `nmSintomas` varchar(45) DEFAULT NULL,
  `descricao` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idsintomas`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sintomas`
--

LOCK TABLES `sintomas` WRITE;
/*!40000 ALTER TABLE `sintomas` DISABLE KEYS */;
INSERT INTO `sintomas` VALUES (1,'Febre','Temperatura superior a 37 graus'),(2,'Dor de cabeca','Dores localizadas na cabeca'),(3,'Tontura','Desvios na visao'),(4,'Vomito','Vomitar');
/*!40000 ALTER TABLE `sintomas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sintomas_has_doencas`
--

DROP TABLE IF EXISTS `sintomas_has_doencas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sintomas_has_doencas` (
  `sintomas_idsintomas` int(11) NOT NULL,
  `doencas_iddoencas` int(11) NOT NULL,
  PRIMARY KEY (`sintomas_idsintomas`,`doencas_iddoencas`),
  KEY `fk_sintomas_has_doencas_doencas1_idx` (`doencas_iddoencas`),
  KEY `fk_sintomas_has_doencas_sintomas1_idx` (`sintomas_idsintomas`),
  CONSTRAINT `fk_sintomas_has_doencas_doencas1` FOREIGN KEY (`doencas_iddoencas`) REFERENCES `doencas` (`iddoencas`),
  CONSTRAINT `fk_sintomas_has_doencas_sintomas1` FOREIGN KEY (`sintomas_idsintomas`) REFERENCES `sintomas` (`idsintomas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sintomas_has_doencas`
--

LOCK TABLES `sintomas_has_doencas` WRITE;
/*!40000 ALTER TABLE `sintomas_has_doencas` DISABLE KEYS */;
INSERT INTO `sintomas_has_doencas` VALUES (1,1),(1,2),(1,3),(2,1),(3,3),(4,1),(4,3);
/*!40000 ALTER TABLE `sintomas_has_doencas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipousuario`
--

DROP TABLE IF EXISTS `tipousuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipousuario` (
  `idtipoUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `tipoUsuario` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idtipoUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipousuario`
--

LOCK TABLES `tipousuario` WRITE;
/*!40000 ALTER TABLE `tipousuario` DISABLE KEYS */;
INSERT INTO `tipousuario` VALUES (1,'Administrador'),(2,'Profissional'),(3,'Usuario');
/*!40000 ALTER TABLE `tipousuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  `telefone` varchar(45) DEFAULT NULL,
  `tipoUsuario_idtipoUsuario` int(11) NOT NULL,
  `endereco_idendereco` int(11) NOT NULL,
  PRIMARY KEY (`idusuario`),
  KEY `fk_usuario_tipoUsuario_idx` (`tipoUsuario_idtipoUsuario`),
  KEY `fk_usuario_endereco1_idx` (`endereco_idendereco`),
  CONSTRAINT `fk_usuario_endereco1` FOREIGN KEY (`endereco_idendereco`) REFERENCES `endereco` (`idendereco`),
  CONSTRAINT `fk_usuario_tipoUsuario` FOREIGN KEY (`tipoUsuario_idtipoUsuario`) REFERENCES `tipousuario` (`idtipoUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Leopoldo','23443223','33778888',1,3),(2,'Paula','7787877','1188998898',2,1),(3,'Joao','3444334','1132234432',3,2),(4,'Diogo','23332332','212232323',3,2),(9,'Joana','555555','7777777',3,2);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_has_diagnostico`
--

DROP TABLE IF EXISTS `usuario_has_diagnostico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_has_diagnostico` (
  `usuario_idusuario` int(11) NOT NULL,
  `diagnostico_iddiagnostico` int(11) NOT NULL,
  PRIMARY KEY (`usuario_idusuario`,`diagnostico_iddiagnostico`),
  KEY `fk_usuario_has_diagnostico_diagnostico1_idx` (`diagnostico_iddiagnostico`),
  KEY `fk_usuario_has_diagnostico_usuario1_idx` (`usuario_idusuario`),
  CONSTRAINT `fk_usuario_has_diagnostico_diagnostico1` FOREIGN KEY (`diagnostico_iddiagnostico`) REFERENCES `diagnostico` (`iddiagnostico`),
  CONSTRAINT `fk_usuario_has_diagnostico_usuario1` FOREIGN KEY (`usuario_idusuario`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_has_diagnostico`
--

LOCK TABLES `usuario_has_diagnostico` WRITE;
/*!40000 ALTER TABLE `usuario_has_diagnostico` DISABLE KEYS */;
INSERT INTO `usuario_has_diagnostico` VALUES (3,1),(4,2);
/*!40000 ALTER TABLE `usuario_has_diagnostico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'furb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-01 10:17:10
