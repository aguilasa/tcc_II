DROP TABLE IF EXISTS `endereco`;
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

DROP TABLE IF EXISTS `sintomas`;
CREATE TABLE `sintomas` (
  `idsintomas` int(11) NOT NULL AUTO_INCREMENT,
  `nmSintomas` varchar(45) DEFAULT NULL,
  `descricao` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idsintomas`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tipousuario`;
CREATE TABLE `tipousuario` (
  `idtipoUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `tipoUsuario` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idtipoUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `usuario`;
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

DROP TABLE IF EXISTS `diagnostico`;
CREATE TABLE `diagnostico` (
  `iddiagnostico` int(11) NOT NULL AUTO_INCREMENT,
  `resultado` varchar(45) DEFAULT NULL,
  `descricao` varchar(200) DEFAULT NULL,
  `data` date DEFAULT NULL,
  PRIMARY KEY (`iddiagnostico`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `doencas`;
CREATE TABLE `doencas` (
  `iddoencas` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(200) DEFAULT NULL,
  `duracaoMedia` int(11) DEFAULT NULL,
  `nome` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`iddoencas`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;DROP TABLE IF EXISTS `diagnosticodoencas`;
CREATE TABLE `diagnosticodoencas` (
  `diagnostico_iddiagnostico` int(11) NOT NULL,
  `doencas_iddoencas` int(11) NOT NULL,
  PRIMARY KEY (`diagnostico_iddiagnostico`,`doencas_iddoencas`),
  KEY `fk_diagnostico_has_doencas_doencas1_idx` (`doencas_iddoencas`),
  KEY `fk_diagnostico_has_doencas_diagnostico1_idx` (`diagnostico_iddiagnostico`),
  CONSTRAINT `fk_diagnostico_has_doencas_diagnostico1` FOREIGN KEY (`diagnostico_iddiagnostico`) REFERENCES `diagnostico` (`iddiagnostico`),
  CONSTRAINT `fk_diagnostico_has_doencas_doencas1` FOREIGN KEY (`doencas_iddoencas`) REFERENCES `doencas` (`iddoencas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;DROP TABLE IF EXISTS `ponto`;
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
DROP TABLE IF EXISTS `sintomasdoencas`;
CREATE TABLE `sintomasdoencas` (
  `sintomas_idsintomas` int(11) NOT NULL,
  `doencas_iddoencas` int(11) NOT NULL,
  PRIMARY KEY (`sintomas_idsintomas`,`doencas_iddoencas`),
  KEY `fk_sintomas_has_doencas_doencas1_idx` (`doencas_iddoencas`),
  KEY `fk_sintomas_has_doencas_sintomas1_idx` (`sintomas_idsintomas`),
  CONSTRAINT `fk_sintomas_has_doencas_doencas1` FOREIGN KEY (`doencas_iddoencas`) REFERENCES `doencas` (`iddoencas`),
  CONSTRAINT `fk_sintomas_has_doencas_sintomas1` FOREIGN KEY (`sintomas_idsintomas`) REFERENCES `sintomas` (`idsintomas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `usuariodiagnostico`;
CREATE TABLE `usuariodiagnostico` (
  `usuario_idusuario` int(11) NOT NULL,
  `diagnostico_iddiagnostico` int(11) NOT NULL,
  PRIMARY KEY (`usuario_idusuario`,`diagnostico_iddiagnostico`),
  KEY `fk_usuario_has_diagnostico_diagnostico1_idx` (`diagnostico_iddiagnostico`),
  KEY `fk_usuario_has_diagnostico_usuario1_idx` (`usuario_idusuario`),
  CONSTRAINT `fk_usuario_has_diagnostico_diagnostico1` FOREIGN KEY (`diagnostico_iddiagnostico`) REFERENCES `diagnostico` (`iddiagnostico`),
  CONSTRAINT `fk_usuario_has_diagnostico_usuario1` FOREIGN KEY (`usuario_idusuario`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;