CREATE DATABASE  IF NOT EXISTS `seminario_2023` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `seminario_2023`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: seminario_2023
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `ID_CLIENTE` int(11) NOT NULL AUTO_INCREMENT,
  `ESTADO` bit(1) NOT NULL,
  `NOMBRE_1` varchar(50) NOT NULL,
  `NOMBRE_2` varchar(50) DEFAULT NULL,
  `APELLIDO_1` varchar(50) NOT NULL,
  `APELLIDO_2` varchar(50) DEFAULT NULL,
  `NIT` varchar(15) NOT NULL,
  `DIRECCION` varchar(45) DEFAULT NULL,
  `TELEFONO` varchar(12) DEFAULT NULL,
  `USUARIO_INGRESO` int(11) NOT NULL,
  `FECHA_INGRESO` datetime NOT NULL,
  `USUARIO_MODIFICA` int(11) DEFAULT NULL,
  `FECHA_MODIFICA` datetime DEFAULT NULL,
  PRIMARY KEY (`ID_CLIENTE`),
  KEY `fk_cliente_usuario_idx` (`USUARIO_INGRESO`),
  KEY `fk_cliente_usuario1_idx` (`USUARIO_MODIFICA`),
  CONSTRAINT `fk_cliente_usuario` FOREIGN KEY (`USUARIO_INGRESO`) REFERENCES `usuario` (`ID_USUARIO`),
  CONSTRAINT `fk_cliente_usuario1` FOREIGN KEY (`USUARIO_MODIFICA`) REFERENCES `usuario` (`ID_USUARIO`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `forma_pago`
--

DROP TABLE IF EXISTS `forma_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forma_pago` (
  `ID_FORMA_PAGO` int(11) NOT NULL AUTO_INCREMENT,
  `ESTADO` bit(1) DEFAULT NULL,
  `DESCRIPCION` varchar(45) DEFAULT NULL,
  `USUARIO_INGRESO` int(11) DEFAULT NULL,
  `FECHA_INGRESO` datetime DEFAULT NULL,
  `USUARIO_MODIFICA` int(11) DEFAULT NULL,
  `FECHA_MODIFICA` datetime DEFAULT NULL,
  PRIMARY KEY (`ID_FORMA_PAGO`),
  KEY `FK_FORMA_PAGO_U_idx` (`USUARIO_INGRESO`),
  KEY `FK_FORMA_PAGO_U_M_idx` (`USUARIO_MODIFICA`),
  CONSTRAINT `FK_FORMA_PAGO_U` FOREIGN KEY (`USUARIO_INGRESO`) REFERENCES `usuario` (`ID_USUARIO`),
  CONSTRAINT `FK_FORMA_PAGO_U_M` FOREIGN KEY (`USUARIO_MODIFICA`) REFERENCES `usuario` (`ID_USUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `perfil`
--

DROP TABLE IF EXISTS `perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfil` (
  `ID_PERFIL` int(11) NOT NULL AUTO_INCREMENT,
  `ESTADO` bit(1) DEFAULT NULL,
  `DESCRIPCION` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID_PERFIL`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `ID_PRODUCTO` int(11) NOT NULL AUTO_INCREMENT,
  `ESTADO` bit(1) DEFAULT NULL,
  `NOMBRE_PRODUCTO` varchar(50) DEFAULT NULL,
  `CANTIDAD` int(11) DEFAULT NULL,
  `PRECIO` decimal(10,2) DEFAULT NULL,
  `USUARIO_INGRESO` int(11) DEFAULT NULL,
  `FECHA_INGRESO` datetime DEFAULT NULL,
  `USUARIO_MODIFICA` int(11) DEFAULT NULL,
  `FECHA_MODIFICA` datetime DEFAULT NULL,
  PRIMARY KEY (`ID_PRODUCTO`),
  KEY `fk_PRODUCTO_usuario1_idx` (`USUARIO_INGRESO`),
  KEY `fk_PRODUCTO_usuario2_idx` (`USUARIO_MODIFICA`),
  CONSTRAINT `fk_PRODUCTO_usuario1` FOREIGN KEY (`USUARIO_INGRESO`) REFERENCES `usuario` (`ID_USUARIO`),
  CONSTRAINT `fk_PRODUCTO_usuario2` FOREIGN KEY (`USUARIO_MODIFICA`) REFERENCES `usuario` (`ID_USUARIO`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `ID_USUARIO` int(11) NOT NULL AUTO_INCREMENT,
  `ESTADO` bit(1) DEFAULT NULL,
  `USUARIO` varchar(50) DEFAULT NULL,
  `CONTRASENIA` varchar(70) DEFAULT NULL,
  `ID_PERFIL` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_USUARIO`),
  KEY `fk_usuario_perfil1_idx` (`ID_PERFIL`),
  CONSTRAINT `fk_usuario_perfil1` FOREIGN KEY (`ID_PERFIL`) REFERENCES `perfil` (`ID_PERFIL`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venta` (
  `ID_VENTA` int(11) NOT NULL AUTO_INCREMENT,
  `ESTADO` bit(1) DEFAULT NULL,
  `ID_CLIENTE` int(11) DEFAULT NULL,
  `FECHA_VENTA` datetime DEFAULT NULL,
  `ID_FORMA_PAGO` int(11) DEFAULT NULL,
  `ESTADO_FINALIZADO` bit(1) DEFAULT NULL,
  `USUARIO_INGRESO` int(11) DEFAULT NULL,
  `FECHA_INGRESO` datetime DEFAULT NULL,
  `USUARIO_MODIFICA` int(11) DEFAULT NULL,
  `FECHA_MODIFICA` datetime DEFAULT NULL,
  PRIMARY KEY (`ID_VENTA`),
  KEY `fk_venta_cliente1_idx` (`ID_CLIENTE`),
  KEY `fk_venta_usuario1_idx` (`USUARIO_INGRESO`),
  KEY `fk_venta_usuario2_idx` (`USUARIO_MODIFICA`),
  KEY `FK_FORMA_PAGO_V_idx` (`ID_FORMA_PAGO`),
  CONSTRAINT `FK_FORMA_PAGO_V` FOREIGN KEY (`ID_FORMA_PAGO`) REFERENCES `forma_pago` (`ID_FORMA_PAGO`),
  CONSTRAINT `fk_venta_cliente1` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `cliente` (`ID_CLIENTE`),
  CONSTRAINT `fk_venta_usuario1` FOREIGN KEY (`USUARIO_INGRESO`) REFERENCES `usuario` (`ID_USUARIO`),
  CONSTRAINT `fk_venta_usuario2` FOREIGN KEY (`USUARIO_MODIFICA`) REFERENCES `usuario` (`ID_USUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `venta_detalle`
--

DROP TABLE IF EXISTS `venta_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venta_detalle` (
  `ID_VENTA_DETALLE` int(11) NOT NULL AUTO_INCREMENT,
  `ID_VENTA` int(11) DEFAULT NULL,
  `ID_PRODUCTO` int(11) DEFAULT NULL,
  `CANTIDAD` int(11) DEFAULT NULL,
  `MONTO` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`ID_VENTA_DETALLE`),
  KEY `fk_venta_detalle_venta1_idx` (`ID_VENTA`),
  KEY `fk_venta_detalle_PRODUCTO1_idx` (`ID_PRODUCTO`),
  CONSTRAINT `fk_venta_detalle_PRODUCTO1` FOREIGN KEY (`ID_PRODUCTO`) REFERENCES `producto` (`ID_PRODUCTO`),
  CONSTRAINT `fk_venta_detalle_venta1` FOREIGN KEY (`ID_VENTA`) REFERENCES `venta` (`ID_VENTA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-29 22:09:55
