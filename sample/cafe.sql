-- MySQL dump 10.13  Distrib 5.7.30, for Linux (x86_64)
--
-- Host: localhost    Database: cafe
-- ------------------------------------------------------
-- Server version	5.7.30-0ubuntu0.18.04.1

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
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food` (
  `id` char(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  `cate` varchar(255) NOT NULL,
  `price` decimal(13,2) DEFAULT NULL,
  `sales` int(11) DEFAULT NULL,
  `des` text,
  `img` text,
  `stock` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dish_category_id_fk` (`cate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES ('3c4f5b9e-fa5b-4366-8d96-c586253c436b','拿铁咖啡','咖啡',20.00,44,'意大利浓缩咖啡与牛奶的经典混合','https://i.loli.net/2020/07/22/OG6ziV8jgUuervo.jpg',43),('42c19e3a-7ebe-4e39-9455-396c12b558c8','西瓜汁','果汁',14.00,30,'口味清甜细腻','https://i.loli.net/2020/07/22/sCrf6T7dWyAQLBF.jpg',34),('4bd57c99-f316-4067-93dd-950ee41a7acd','苹果汁','果汁',15.00,70,'app1e juice','https://i.loli.net/2020/07/22/lLYXizZuaSJAI1e.jpg',66),('5745bb1c-ed5b-40b9-8ac4-3d4a5a45e76f','鸡翅','小吃',10.00,21,'上等鸡翅，以椒盐淬之，色泽金黄、椒盐清香、咸香酥嫩。','https://i.loli.net/2020/07/22/IbtnfkoGwjSL1s2.jpg',45),('5c01fb0e-28a5-4ad2-a285-162248ab1712','美式咖啡','咖啡',16.00,31,'一杯好的Espresso','https://i.loli.net/2020/07/22/Xt6xpwQZvL9YiNz.jpg',31),('7a236bdb-6235-4370-bce3-bd8391278d9d','薯条','小吃',9.00,71,'可咸可甜','https://i.loli.net/2020/07/22/ONGlZwyj5vrAfoY.jpg',54),('ab12de66-67f2-472a-b2a5-7ee5df12eeb6','珍珠奶茶','奶茶',11.00,23,'咖啡店卖奶茶难道不是常识吗？','https://i.loli.net/2020/07/22/qYMU8xyLukKCJ5I.jpg',62),('de77ac3c-28c9-4db2-9113-de9150e94f2f','摩卡咖啡','咖啡',18.00,42,'在濃縮咖啡中加入摩卡醬，與蒸煮牛奶一起交織出絲滑與醇厚。','https://i.loli.net/2020/07/22/Uu3tTdFM1pZ2IEX.jpg',31),('e9a11136-b39d-4341-a447-e3084e33066b','黑糖奶茶','奶茶',12.00,33,'抵擋不了誘惑！','https://i.loli.net/2020/07/22/5YrcQkFbDL6K8Ej.jpg',71);
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordre`
--

DROP TABLE IF EXISTS `ordre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordre` (
  `id` char(36) NOT NULL,
  `time` datetime DEFAULT NULL,
  `user` char(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_user_id_fk` (`user`),
  CONSTRAINT `ordre_user_id_fk` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordre`
--

LOCK TABLES `ordre` WRITE;
/*!40000 ALTER TABLE `ordre` DISABLE KEYS */;
INSERT INTO `ordre` VALUES ('09b5e6da-c201-4b7d-b3a3-fc533f4fb770','2020-07-13 19:29:29','468e78f3-0532-4a10-99d5-4cddf7618b11'),('101c6d25-f116-443f-8959-648240289efe','2020-07-21 04:30:23','468e78f3-0532-4a10-99d5-4cddf7618b11'),('2088712f-cdbc-41f1-aefe-6a873b93edc5','2020-07-01 19:34:19','d3ce1d07-34f9-4333-a557-6cfdd06bdbcc'),('2b7d6d5d-67e1-4830-89e6-5b801e9d6ea1','2020-07-22 08:57:59','d3ce1d07-34f9-4333-a557-6cfdd06bdbcc'),('7200517f-1516-42fa-b94b-3972ea12ee64','2020-07-17 03:24:29','8b7a0ef9-df1e-4319-a941-b1624ed6cf23'),('b0efe4aa-fc63-4127-8855-ed7474e7b050','2020-07-12 19:34:19','d3ce1d07-34f9-4333-a557-6cfdd06bdbcc'),('ce1edad5-5d8b-4f70-81f8-9b16921a5727','2020-07-08 22:28:00','468e78f3-0532-4a10-99d5-4cddf7618b11'),('de2d7d14-65ed-4192-af4d-5c117544f129','2020-07-12 10:31:06','2f729779-d174-44d5-b74a-5e0797f50f92');
/*!40000 ALTER TABLE `ordre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordre_food`
--

DROP TABLE IF EXISTS `ordre_food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordre_food` (
  `ordre` char(36) NOT NULL,
  `food` char(36) NOT NULL,
  `qty` int(11) DEFAULT NULL,
  PRIMARY KEY (`ordre`,`food`),
  KEY `order_food_food_id_fk` (`food`),
  CONSTRAINT `ordre_food_food_id_fk` FOREIGN KEY (`food`) REFERENCES `food` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `ordre_food_order_id_fk` FOREIGN KEY (`ordre`) REFERENCES `ordre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordre_food`
--

LOCK TABLES `ordre_food` WRITE;
/*!40000 ALTER TABLE `ordre_food` DISABLE KEYS */;
INSERT INTO `ordre_food` VALUES ('09b5e6da-c201-4b7d-b3a3-fc533f4fb770','5c01fb0e-28a5-4ad2-a285-162248ab1712',1),('09b5e6da-c201-4b7d-b3a3-fc533f4fb770','ab12de66-67f2-472a-b2a5-7ee5df12eeb6',1),('101c6d25-f116-443f-8959-648240289efe','42c19e3a-7ebe-4e39-9455-396c12b558c8',17),('2b7d6d5d-67e1-4830-89e6-5b801e9d6ea1','3c4f5b9e-fa5b-4366-8d96-c586253c436b',2),('2b7d6d5d-67e1-4830-89e6-5b801e9d6ea1','42c19e3a-7ebe-4e39-9455-396c12b558c8',2),('2b7d6d5d-67e1-4830-89e6-5b801e9d6ea1','4bd57c99-f316-4067-93dd-950ee41a7acd',1),('7200517f-1516-42fa-b94b-3972ea12ee64','42c19e3a-7ebe-4e39-9455-396c12b558c8',1),('b0efe4aa-fc63-4127-8855-ed7474e7b050','42c19e3a-7ebe-4e39-9455-396c12b558c8',1),('b0efe4aa-fc63-4127-8855-ed7474e7b050','4bd57c99-f316-4067-93dd-950ee41a7acd',2),('b0efe4aa-fc63-4127-8855-ed7474e7b050','7a236bdb-6235-4370-bce3-bd8391278d9d',3),('ce1edad5-5d8b-4f70-81f8-9b16921a5727','3c4f5b9e-fa5b-4366-8d96-c586253c436b',2),('ce1edad5-5d8b-4f70-81f8-9b16921a5727','de77ac3c-28c9-4db2-9113-de9150e94f2f',3),('de2d7d14-65ed-4192-af4d-5c117544f129','5745bb1c-ed5b-40b9-8ac4-3d4a5a45e76f',1),('de2d7d14-65ed-4192-af4d-5c117544f129','de77ac3c-28c9-4db2-9113-de9150e94f2f',2);
/*!40000 ALTER TABLE `ordre_food` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`cafer`@`localhost`*/ /*!50003 trigger sales_track after insert on ordre_food
    for each row
    begin
        update food
            set sales = (sales + NEW.qty),
                stock = (stock - NEW.qty)
        where id = NEW.food;
    end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('ROLE_ADMIN'),('ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` char(36) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('2f729779-d174-44d5-b74a-5e0797f50f92','user_c',NULL,'pass_c',NULL),('3f214078-7b1c-4f27-a0ff-a2a79aa023f1','user_x',NULL,'pass_x',NULL),('468e78f3-0532-4a10-99d5-4cddf7618b11','user_a','','pass_a',''),('8b7a0ef9-df1e-4319-a941-b1624ed6cf23','user_d',NULL,'pass_d',NULL),('d3ce1d07-34f9-4333-a557-6cfdd06bdbcc','user_b','a@b.c','pass_b',NULL),('e48b5f8b-3cd3-49cb-aec8-2f8fbce7e88c','user_y',NULL,'pass_y',NULL),('fa54097d-6d95-44df-b4b0-a2d6283d9500','cafe_master',NULL,'secret_agent',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
ALTER DATABASE `cafe` CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`cafer`@`localhost`*/ /*!50003 trigger auto_user_role after insert on user for each row
          BEGIN
              insert into user_role values (NEW.id,'ROLE_USER');
          end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `cafe` CHARACTER SET utf8 COLLATE utf8_general_ci ;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user` char(36) NOT NULL,
  `role` varchar(10) DEFAULT 'ROLE_USER',
  PRIMARY KEY (`user`),
  KEY `user_role_role_name_fk` (`role`),
  CONSTRAINT `user_role_role_name_fk` FOREIGN KEY (`role`) REFERENCES `role` (`name`) ON UPDATE CASCADE,
  CONSTRAINT `user_role_user_id_fk` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES ('468e78f3-0532-4a10-99d5-4cddf7618b11','ROLE_ADMIN'),('fa54097d-6d95-44df-b4b0-a2d6283d9500','ROLE_ADMIN'),('2f729779-d174-44d5-b74a-5e0797f50f92','ROLE_USER'),('3f214078-7b1c-4f27-a0ff-a2a79aa023f1','ROLE_USER'),('8b7a0ef9-df1e-4319-a941-b1624ed6cf23','ROLE_USER'),('d3ce1d07-34f9-4333-a557-6cfdd06bdbcc','ROLE_USER'),('e48b5f8b-3cd3-49cb-aec8-2f8fbce7e88c','ROLE_USER');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-22 23:51:48
