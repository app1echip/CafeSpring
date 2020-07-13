-- MySQL dump 10.13  Distrib 8.0.20, for Linux (x86_64)
--
-- Host: localhost    Database: cafe
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` char(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('5c01fb0e-28a5-4ad2-a285-162248ab1712','咖啡'),('67f4c843-d997-4910-b135-583f5368ea3c','小吃'),('7e7aae0b-e9cf-40fd-b6dd-7ced57ebd40c','奶茶'),('dd928605-29b6-4289-ad4d-a9017330bcf7','果汁');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dish`
--

DROP TABLE IF EXISTS `dish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dish` (
  `id` char(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  `category` char(36) DEFAULT NULL,
  `price` decimal(13,2) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  `sales` int DEFAULT NULL,
  `description` text,
  `picture` text,
  PRIMARY KEY (`id`),
  KEY `dish_category_id_fk` (`category`),
  CONSTRAINT `dish_category_id_fk` FOREIGN KEY (`category`) REFERENCES `category` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dish`
--

LOCK TABLES `dish` WRITE;
/*!40000 ALTER TABLE `dish` DISABLE KEYS */;
INSERT INTO `dish` VALUES ('3c4f5b9e-fa5b-4366-8d96-c586253c436b','拿铁咖啡','5c01fb0e-28a5-4ad2-a285-162248ab1712',16.00,NULL,NULL,NULL,NULL),('42c19e3a-7ebe-4e39-9455-396c12b558c8','西瓜汁','dd928605-29b6-4289-ad4d-a9017330bcf7',14.00,NULL,NULL,NULL,NULL),('4bd57c99-f316-4067-93dd-950ee41a7acd','苹果汁','dd928605-29b6-4289-ad4d-a9017330bcf7',15.00,NULL,NULL,NULL,NULL),('5745bb1c-ed5b-40b9-8ac4-3d4a5a45e76f','鸡翅','67f4c843-d997-4910-b135-583f5368ea3c',10.00,NULL,NULL,NULL,NULL),('5c01fb0e-28a5-4ad2-a285-162248ab1712','美式咖啡','5c01fb0e-28a5-4ad2-a285-162248ab1712',16.00,NULL,NULL,NULL,NULL),('7a236bdb-6235-4370-bce3-bd8391278d9d','薯条','67f4c843-d997-4910-b135-583f5368ea3c',9.00,NULL,NULL,NULL,NULL),('ab12de66-67f2-472a-b2a5-7ee5df12eeb6','珍珠奶茶','7e7aae0b-e9cf-40fd-b6dd-7ced57ebd40c',11.00,NULL,NULL,NULL,NULL),('de77ac3c-28c9-4db2-9113-de9150e94f2f','摩卡咖啡','5c01fb0e-28a5-4ad2-a285-162248ab1712',18.00,NULL,NULL,NULL,NULL),('e9a11136-b39d-4341-a447-e3084e33066b','黑糖奶茶','7e7aae0b-e9cf-40fd-b6dd-7ced57ebd40c',12.00,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `dish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` char(36) NOT NULL,
  `date` datetime DEFAULT NULL,
  `no` int DEFAULT NULL,
  `user` char(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_user_id_fk` (`user`),
  CONSTRAINT `order_user_id_fk` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES ('09b5e6da-c201-4b7d-b3a3-fc533f4fb770','2020-07-13 19:29:29',12,'468e78f3-0532-4a10-99d5-4cddf7618b11'),('2088712f-cdbc-41f1-aefe-6a873b93edc5','2020-07-01 19:34:19',9,'d3ce1d07-34f9-4333-a557-6cfdd06bdbcc'),('b0efe4aa-fc63-4127-8855-ed7474e7b050','2020-07-12 19:34:19',39,'d3ce1d07-34f9-4333-a557-6cfdd06bdbcc'),('ce1edad5-5d8b-4f70-81f8-9b16921a5727','2020-07-08 22:28:00',45,'468e78f3-0532-4a10-99d5-4cddf7618b11'),('de2d7d14-65ed-4192-af4d-5c117544f129','2020-07-12 10:31:06',14,'2f729779-d174-44d5-b74a-5e0797f50f92');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_dish`
--

DROP TABLE IF EXISTS `order_dish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_dish` (
  `order` char(36) NOT NULL,
  `dish` char(36) NOT NULL,
  `qty` int DEFAULT NULL,
  PRIMARY KEY (`order`,`dish`),
  KEY `order_dish_dish_id_fk` (`dish`),
  CONSTRAINT `order_dish_dish_id_fk` FOREIGN KEY (`dish`) REFERENCES `dish` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `order_dish_order_id_fk` FOREIGN KEY (`order`) REFERENCES `order` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_dish`
--

LOCK TABLES `order_dish` WRITE;
/*!40000 ALTER TABLE `order_dish` DISABLE KEYS */;
INSERT INTO `order_dish` VALUES ('09b5e6da-c201-4b7d-b3a3-fc533f4fb770','5c01fb0e-28a5-4ad2-a285-162248ab1712',1),('09b5e6da-c201-4b7d-b3a3-fc533f4fb770','ab12de66-67f2-472a-b2a5-7ee5df12eeb6',1),('b0efe4aa-fc63-4127-8855-ed7474e7b050','42c19e3a-7ebe-4e39-9455-396c12b558c8',1),('b0efe4aa-fc63-4127-8855-ed7474e7b050','4bd57c99-f316-4067-93dd-950ee41a7acd',2),('b0efe4aa-fc63-4127-8855-ed7474e7b050','7a236bdb-6235-4370-bce3-bd8391278d9d',3),('ce1edad5-5d8b-4f70-81f8-9b16921a5727','3c4f5b9e-fa5b-4366-8d96-c586253c436b',2),('ce1edad5-5d8b-4f70-81f8-9b16921a5727','de77ac3c-28c9-4db2-9113-de9150e94f2f',1),('de2d7d14-65ed-4192-af4d-5c117544f129','5745bb1c-ed5b-40b9-8ac4-3d4a5a45e76f',1),('de2d7d14-65ed-4192-af4d-5c117544f129','de77ac3c-28c9-4db2-9113-de9150e94f2f',1);
/*!40000 ALTER TABLE `order_dish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` char(36) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('2f729779-d174-44d5-b74a-5e0797f50f92','user_c',NULL,'pass_c',NULL),('468e78f3-0532-4a10-99d5-4cddf7618b11','user_a',NULL,'pass_a',NULL),('d3ce1d07-34f9-4333-a557-6cfdd06bdbcc','user_b',NULL,'pass_b',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-13 17:54:08
