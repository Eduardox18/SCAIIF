# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.19)
# Database: scaiif
# Generation Time: 2018-03-15 15:56:42 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table actividad
# ------------------------------------------------------------

DROP TABLE IF EXISTS `actividad`;

CREATE TABLE `actividad` (
  `noActividad` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) CHARACTER SET latin1 NOT NULL,
  `horaInicio` time NOT NULL,
  `horaFin` time NOT NULL,
  `fecha` date NOT NULL,
  `cupo` int(11) NOT NULL,
  `noPersonal` int(11) NOT NULL,
  PRIMARY KEY (`noActividad`),
  KEY `fk_Actividad_Usuario1_idx` (`noPersonal`),
  CONSTRAINT `fk_Actividad_Usuario1` FOREIGN KEY (`noPersonal`) REFERENCES `usuario` (`noPersonal`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `actividad` WRITE;
/*!40000 ALTER TABLE `actividad` DISABLE KEYS */;

INSERT INTO `actividad` (`noActividad`, `nombre`, `horaInicio`, `horaFin`, `fecha`, `cupo`, `noPersonal`)
VALUES
	(1,'Conversación Inglés I 1','14:00:00','15:00:00','2018-03-15',30,18109),
	(2,'Conversación Francés I 1','15:30:00','16:30:00','2018-04-01',30,18109),
	(3,'Taller de lectura Francés I','16:00:00','18:00:00','2018-03-22',30,18109),
	(4,'Asesoría Inglés I 1','14:00:00','15:00:00','2018-03-23',30,18109),
	(5,'Asesoría Inglés I 2','18:00:00','19:30:00','2018-03-28',30,93812),
	(6,'Película Francés I 1','13:00:00','15:00:00','2018-03-02',30,93812);

/*!40000 ALTER TABLE `actividad` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table alumno
# ------------------------------------------------------------

DROP TABLE IF EXISTS `alumno`;

CREATE TABLE `alumno` (
  `matricula` varchar(9) CHARACTER SET latin1 NOT NULL,
  `nombre` varchar(45) CHARACTER SET latin1 NOT NULL,
  `apPaterno` varchar(45) CHARACTER SET latin1 NOT NULL,
  `apMaterno` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `correo` varchar(45) CHARACTER SET latin1 NOT NULL,
  `lenguaIndigena` tinyint(4) NOT NULL,
  PRIMARY KEY (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `alumno` WRITE;
/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;

INSERT INTO `alumno` (`matricula`, `nombre`, `apPaterno`, `apMaterno`, `correo`, `lenguaIndigena`)
VALUES
	('S15011601','Ricardo','Domínguez','González','ricardo@gmail.com',1),
	('S15011602','Guadalupe Mariel','Domínguez','González','lalupe@gmail.com',1),
	('S15011603','Brenis Guadalupe','Hernández','González','brenis@gmail.com',0),
	('S15011604','Astrid Dayan ','Hernández','González','dayan@gmail.com',1),
	('S15011605','Kevin Jair','Ortega',NULL,'elkevin@gmail.com',1),
	('S15011606','Robert Lee','Barrera','Pérez','jackiechan@gmail.com',1),
	('S15011607','Alba Cristina','Garza','Cayetano','cristina@gmail.com',1),
	('S15011608','Enrique','Rodríguez',NULL,'zeroghie@gmail.com',0),
	('S15011609','Zaret','Roque','Sahaar','zaret@gmail.com',0),
	('S15011610','Mariana','Cadena','Romero','mariana@gmail.com',1),
	('S15011611','Maribel','Tello','Rodríguez','mari_campana@gmail.com',0),
	('S15011612','Cassandra','Gómez','González','cassandra@gmail.com',1),
	('S15011613','Hendrix','González','González','hendrix@gmail.com',0),
	('S15011614','Mónica','Fernández','Ortega','moni@gmail.com',1),
	('S15011615','Fabiola','Cuellar','Gutiérrez','fabiola@gmail.com',0),
	('S15011624','Ámbar','Espinosa','Madera','ambar@gmail.com',0);

/*!40000 ALTER TABLE `alumno` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table aviso
# ------------------------------------------------------------

DROP TABLE IF EXISTS `aviso`;

CREATE TABLE `aviso` (
  `noAviso` int(11) NOT NULL AUTO_INCREMENT,
  `asunto` varchar(45) CHARACTER SET latin1 NOT NULL,
  `mensaje` varchar(45) CHARACTER SET latin1 NOT NULL,
  `fechaCreacion` date NOT NULL,
  `fechaLimite` date NOT NULL,
  PRIMARY KEY (`noAviso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table calendario
# ------------------------------------------------------------

DROP TABLE IF EXISTS `calendario`;

CREATE TABLE `calendario` (
  `idCalendario` int(11) NOT NULL AUTO_INCREMENT,
  `mes` varchar(45) CHARACTER SET latin1 NOT NULL,
  `periodo` varchar(45) CHARACTER SET latin1 NOT NULL,
  `diaInicio` int(11) NOT NULL,
  `diaFin` int(11) NOT NULL,
  `diasFestivos` int(11) NOT NULL,
  `fechaLimiteExamen` date NOT NULL,
  `inicioVacaciones` date NOT NULL,
  `finVacaciones` date NOT NULL,
  `nrc` int(5) NOT NULL,
  `idModulo` int(11) NOT NULL,
  `idSeccion` int(11) NOT NULL,
  `idConversacion` int(11) NOT NULL,
  `noMaterial` int(11) NOT NULL,
  PRIMARY KEY (`idCalendario`),
  KEY `fk_Calendario_Curso1_idx` (`nrc`),
  KEY `fk_Calendario_Modulo1_idx` (`idModulo`),
  KEY `fk_Calendario_Seccion1_idx` (`idSeccion`),
  KEY `fk_Calendario_Conversacion1_idx` (`idConversacion`),
  KEY `fk_Calendario_MaterialReportar1_idx` (`noMaterial`),
  CONSTRAINT `fk_Calendario_Conversacion1` FOREIGN KEY (`idConversacion`) REFERENCES `conversacion` (`idConversacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Calendario_Curso1` FOREIGN KEY (`nrc`) REFERENCES `curso` (`nrc`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Calendario_MaterialReportar1` FOREIGN KEY (`noMaterial`) REFERENCES `materialreportar` (`noMaterial`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Calendario_Modulo1` FOREIGN KEY (`idModulo`) REFERENCES `modulo` (`idModulo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Calendario_Seccion1` FOREIGN KEY (`idSeccion`) REFERENCES `seccion` (`idSeccion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table calificacion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `calificacion`;

CREATE TABLE `calificacion` (
  `nrc` int(5) NOT NULL,
  `matricula` varchar(9) CHARACTER SET latin1 NOT NULL,
  `calificacion` double DEFAULT NULL,
  PRIMARY KEY (`nrc`,`matricula`),
  KEY `fk_Curso_has_Alumno_Alumno1_idx` (`matricula`),
  KEY `fk_Curso_has_Alumno_Curso1_idx` (`nrc`),
  CONSTRAINT `fk_Curso_has_Alumno_Alumno1` FOREIGN KEY (`matricula`) REFERENCES `alumno` (`matricula`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Curso_has_Alumno_Curso1` FOREIGN KEY (`nrc`) REFERENCES `curso` (`nrc`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `calificacion` WRITE;
/*!40000 ALTER TABLE `calificacion` DISABLE KEYS */;

INSERT INTO `calificacion` (`nrc`, `matricula`, `calificacion`)
VALUES
	(28192,'S15011601',NULL),
	(28192,'S15011603',NULL),
	(28192,'S15011604',NULL),
	(28192,'S15011610',NULL),
	(28192,'S15011611',NULL),
	(28192,'S15011614',NULL),
	(28192,'S15011615',NULL),
	(92812,'S15011602',NULL),
	(92812,'S15011605',NULL),
	(92812,'S15011606',NULL),
	(92812,'S15011607',NULL),
	(92812,'S15011624',NULL);

/*!40000 ALTER TABLE `calificacion` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table cargo
# ------------------------------------------------------------

DROP TABLE IF EXISTS `cargo`;

CREATE TABLE `cargo` (
  `idCargo` int(11) NOT NULL AUTO_INCREMENT,
  `cargo` varchar(45) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`idCargo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `cargo` WRITE;
/*!40000 ALTER TABLE `cargo` DISABLE KEYS */;

INSERT INTO `cargo` (`idCargo`, `cargo`)
VALUES
	(1,'Coordinador'),
	(2,'Asesor'),
	(3,'Recepcionista');

/*!40000 ALTER TABLE `cargo` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table conversacion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `conversacion`;

CREATE TABLE `conversacion` (
  `idConversacion` int(11) NOT NULL AUTO_INCREMENT,
  `noConversacion` int(11) NOT NULL,
  PRIMARY KEY (`idConversacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `conversacion` WRITE;
/*!40000 ALTER TABLE `conversacion` DISABLE KEYS */;

INSERT INTO `conversacion` (`idConversacion`, `noConversacion`)
VALUES
	(1,1),
	(2,2),
	(3,3),
	(4,4),
	(5,5),
	(6,6);

/*!40000 ALTER TABLE `conversacion` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table curso
# ------------------------------------------------------------

DROP TABLE IF EXISTS `curso`;

CREATE TABLE `curso` (
  `nrc` int(5) NOT NULL,
  `nombreCurso` varchar(45) CHARACTER SET latin1 NOT NULL,
  `noCreditos` int(2) NOT NULL,
  `noPersonal` int(11) NOT NULL,
  PRIMARY KEY (`nrc`),
  KEY `fk_Curso_Usuario1_idx` (`noPersonal`),
  CONSTRAINT `fk_Curso_Usuario1` FOREIGN KEY (`noPersonal`) REFERENCES `usuario` (`noPersonal`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;

INSERT INTO `curso` (`nrc`, `nombreCurso`, `noCreditos`, `noPersonal`)
VALUES
	(28192,'Inglés I',7,19201),
	(92812,'Francés I',6,93812);

/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table induccion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `induccion`;

CREATE TABLE `induccion` (
  `matricula` varchar(9) CHARACTER SET latin1 NOT NULL,
  `nrc` int(5) NOT NULL,
  `idInduccion` int(11) NOT NULL,
  `cursoInduccion` tinyint(4) NOT NULL,
  `primeraAsesoria` tinyint(4) NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`matricula`,`nrc`,`idInduccion`),
  KEY `fk_Alumno_has_Curso_Curso1_idx` (`nrc`),
  KEY `fk_Alumno_has_Curso_Alumno1_idx` (`matricula`),
  CONSTRAINT `fk_Alumno_has_Curso_Alumno1` FOREIGN KEY (`matricula`) REFERENCES `alumno` (`matricula`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Alumno_has_Curso_Curso1` FOREIGN KEY (`nrc`) REFERENCES `curso` (`nrc`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table materialReportar
# ------------------------------------------------------------

DROP TABLE IF EXISTS `materialReportar`;

CREATE TABLE `materialReportar` (
  `noMaterial` int(11) NOT NULL AUTO_INCREMENT,
  `nombreMaterial` varchar(45) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`noMaterial`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table modulo
# ------------------------------------------------------------

DROP TABLE IF EXISTS `modulo`;

CREATE TABLE `modulo` (
  `idModulo` int(11) NOT NULL AUTO_INCREMENT,
  `noModulo` int(11) NOT NULL,
  PRIMARY KEY (`idModulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table observacion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `observacion`;

CREATE TABLE `observacion` (
  `matricula` varchar(9) CHARACTER SET latin1 NOT NULL,
  `noPersonal` int(11) NOT NULL,
  `idObservacion` int(11) NOT NULL,
  `asunto` varchar(45) CHARACTER SET latin1 NOT NULL,
  `comentario` varchar(45) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`matricula`,`noPersonal`,`idObservacion`),
  KEY `fk_Alumno_has_Usuario_Usuario1_idx` (`noPersonal`),
  KEY `fk_Alumno_has_Usuario_Alumno1_idx` (`matricula`),
  CONSTRAINT `fk_Alumno_has_Usuario_Alumno1` FOREIGN KEY (`matricula`) REFERENCES `alumno` (`matricula`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Alumno_has_Usuario_Usuario1` FOREIGN KEY (`noPersonal`) REFERENCES `usuario` (`noPersonal`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table reservacion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `reservacion`;

CREATE TABLE `reservacion` (
  `noActividad` int(11) NOT NULL,
  `matricula` varchar(9) CHARACTER SET latin1 NOT NULL,
  `noReservacion` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `asistencia` tinyint(4) NOT NULL,
  PRIMARY KEY (`noActividad`,`matricula`,`noReservacion`),
  KEY `fk_Actividad_has_Alumno_Alumno1_idx` (`matricula`),
  KEY `fk_Actividad_has_Alumno_Actividad1_idx` (`noActividad`),
  CONSTRAINT `fk_Actividad_has_Alumno_Actividad1` FOREIGN KEY (`noActividad`) REFERENCES `actividad` (`noActividad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Actividad_has_Alumno_Alumno1` FOREIGN KEY (`matricula`) REFERENCES `alumno` (`matricula`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `reservacion` WRITE;
/*!40000 ALTER TABLE `reservacion` DISABLE KEYS */;

INSERT INTO `reservacion` (`noActividad`, `matricula`, `noReservacion`, `fecha`, `asistencia`)
VALUES
	(1,'S15011601',3,'2018-03-02',0),
	(1,'S15011602',4,'2018-02-28',0),
	(1,'S15011606',6,'2018-02-18',0),
	(1,'S15011608',5,'2018-02-27',0),
	(1,'S15011624',7,'2018-03-01',0),
	(2,'S15011601',8,'2018-02-24',0),
	(2,'S15011604',2,'2018-03-02',0),
	(5,'S15011602',9,'2018-02-24',0),
	(6,'S15011603',1,'2018-03-01',0);

/*!40000 ALTER TABLE `reservacion` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table seccion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seccion`;

CREATE TABLE `seccion` (
  `idSeccion` int(11) NOT NULL AUTO_INCREMENT,
  `noSeccion` int(11) NOT NULL,
  PRIMARY KEY (`idSeccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table usuario
# ------------------------------------------------------------

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `noPersonal` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) CHARACTER SET latin1 NOT NULL,
  `apPaterno` varchar(45) CHARACTER SET latin1 NOT NULL,
  `apMaterno` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `correo` varchar(45) CHARACTER SET latin1 NOT NULL,
  `password` varchar(256) CHARACTER SET latin1 NOT NULL,
  `idCargo` int(11) NOT NULL,
  PRIMARY KEY (`noPersonal`),
  KEY `fk_Usuario_Cargo_idx` (`idCargo`),
  CONSTRAINT `fk_Usuario_Cargo` FOREIGN KEY (`idCargo`) REFERENCES `cargo` (`idCargo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;

INSERT INTO `usuario` (`noPersonal`, `nombre`, `apPaterno`, `apMaterno`, `correo`, `password`, `idCargo`)
VALUES
	(18109,'Ángel Eduardo','Domínguez','Delgado','lalo_dd@hotmail.com','BD03CB6BEC94A7B787A6DAAEE465F877CE06ED34C791DF3A3090C83FBFDEEA7A',2),
	(19201,'María Fernanda','Rodríguez','Hernández','mafer@gmail.com','BD03CB6BEC94A7B787A6DAAEE465F877CE06ED34C791DF3A3090C83FBFDEEA7A',2),
	(38192,'Esmeralda Yamileth','Hernández','González','esmeyhg@gmail.com','BD03CB6BEC94A7B787A6DAAEE465F877CE06ED34C791DF3A3090C83FBFDEEA7A',1),
	(55555,'José Andrés','Domínguez','González','andresdglez@gmail.com','BD03CB6BEC94A7B787A6DAAEE465F877CE06ED34C791DF3A3090C83FBFDEEA7A',1),
	(93812,'José Manuel','Flores','Peña','josemanu@gmail.com','BD03CB6BEC94A7B787A6DAAEE465F877CE06ED34C791DF3A3090C83FBFDEEA7A',2);

/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
