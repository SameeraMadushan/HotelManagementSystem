CREATE DATABASE  IF NOT EXISTS `glen_hotel_management_system` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `glen_hotel_management_system`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
--Table Structure and sample data for glen DB
--
-- Host: 127.0.0.1    Database: glen_hotel_management_system
-- ------------------------------------------------------
-- Server version	5.6.28-log

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
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `event_type` varchar(45) DEFAULT NULL,
  `event_duration` double DEFAULT NULL,
  `event_entertainment` varchar(45) DEFAULT NULL,
  `event_date` date DEFAULT NULL,
  `event_time` varchar(10) DEFAULT NULL,
  `no_of_guests` int(11) DEFAULT NULL,
  `hall_name` varchar(45) DEFAULT NULL,
  `hall_price` double DEFAULT NULL,
  `event_status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=410009 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (410001,'Wedding',5,'Band','2016-09-28','08 :30 AM',230,'Ockroom - max 250',12000,NULL),(410002,'Birthday Party',4,'DJ','2016-09-25','07 :30 PM',190,'Ballroom - max 200',10000,NULL),(410003,'Anniversary',3,'Calipso','2016-09-30','04 :30 PM',280,'Platinum - max 300',16000,NULL),(410004,'Get together',6,'DJ','2016-09-30','06 :00 PM',350,'Rooftop - max 350',20000,NULL),(410005,'Office Function',5,'Band','2016-09-29','07 :30 PM',230,'Ockroom - max 250',12000,NULL),(410006,'Wedding',4,'Dancing Group','2016-10-05','07 :30 PM',275,'Platinum - max 300',16000,NULL),(410007,'Anniversary',5,'Band','2016-10-12','07 :30 PM',175,'Ballroom - max 200',10000,NULL),(410008,'Birthday Party',5,'DJ','2016-09-28','06 :00 PM',180,'Ballroom - max 200',10000,NULL);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_booking`
--

DROP TABLE IF EXISTS `event_booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_booking` (
  `booking_event_id` int(11) NOT NULL,
  `booking_customer_NIC` varchar(10) NOT NULL,
  `booking_date` date DEFAULT NULL,
  `booking_status` varchar(45) DEFAULT NULL,
  `booking_time` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`booking_event_id`,`booking_customer_NIC`),
  KEY `fk_booking_customer_NIC_idx` (`booking_customer_NIC`),
  CONSTRAINT `fk2_cus_nic` FOREIGN KEY (`booking_customer_NIC`) REFERENCES `event_customer_info` (`customer_NIC`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_booking_event_id` FOREIGN KEY (`booking_event_id`) REFERENCES `event` (`event_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_booking`
--

LOCK TABLES `event_booking` WRITE;
/*!40000 ALTER TABLE `event_booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_customer_info`
--

DROP TABLE IF EXISTS `event_customer_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_customer_info` (
  `customer_id` int(11) DEFAULT NULL,
  `customer_NIC` varchar(10) NOT NULL,
  `cus_event_id` int(11) DEFAULT NULL,
  `customer_name` varchar(30) DEFAULT NULL,
  `customer_address` varchar(50) DEFAULT NULL,
  `customer_tel` int(11) DEFAULT NULL,
  PRIMARY KEY (`customer_NIC`),
  KEY `fk_cus_event_id_idx` (`cus_event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_customer_info`
--

LOCK TABLES `event_customer_info` WRITE;
/*!40000 ALTER TABLE `event_customer_info` DISABLE KEYS */;
INSERT INTO `event_customer_info` VALUES (NULL,'786547865v',410007,'Uthpala Liyanage','Galle',876567865),(NULL,'786754876v',410005,'Dinuka Perera','Maharagama',112657654),(NULL,'897675347v',410004,'Chethana perera','Athurugiriya',716547654),(NULL,'897685467v',410006,'Dimansha Malrindu','Gampaha',774567872),(NULL,'936758334v',410003,'Sapumal Kalutota','Nawala',773456432),(NULL,'937656467v',410002,'Sameera Madushan','Maharagama',772346537),(NULL,'948602920v',410001,'Ashani Dickowita','no23,Malabe',772356242);
/*!40000 ALTER TABLE `event_customer_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_decoration`
--

DROP TABLE IF EXISTS `event_decoration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_decoration` (
  `decor_id` int(11) NOT NULL AUTO_INCREMENT,
  `decor_event_id` int(11) NOT NULL,
  `decor_customer_NIC` varchar(10) DEFAULT NULL,
  `theme_color` varchar(45) DEFAULT NULL,
  `decor_description` varchar(500) DEFAULT NULL,
  `table_decoration` varchar(25) DEFAULT NULL,
  `wall_decoration` varchar(25) DEFAULT NULL,
  `flower_decoration` varchar(25) DEFAULT NULL,
  `disco_light` varchar(25) DEFAULT NULL,
  `entrance_arch` varchar(25) DEFAULT NULL,
  `decor_special_requirements` varchar(200) DEFAULT NULL,
  `decor_special_req_price` double DEFAULT NULL,
  `decor_price` double DEFAULT NULL,
  `decor_total` double DEFAULT NULL,
  PRIMARY KEY (`decor_id`,`decor_event_id`),
  KEY `fk_decor_event_id_idx` (`decor_event_id`),
  CONSTRAINT `fk_decor_event_id` FOREIGN KEY (`decor_event_id`) REFERENCES `event` (`event_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=440008 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_decoration`
--

LOCK TABLES `event_decoration` WRITE;
/*!40000 ALTER TABLE `event_decoration` DISABLE KEYS */;
INSERT INTO `event_decoration` VALUES (440001,410001,'948602920v','Blue','25 tables','Table Decorations','null','Flower Decorations','null','null','Baloons',1000,12000,13000),(440002,410002,'937656467v','Green','20 tables','Table Decorations','null','Flower Decorations','null','null','Baloons',1000,14000,15000),(440003,410003,'936758334v','Purple','20 tables','Table Decorations','null','Flower Decorations','Disco Light','null','Oil lamp',1500,15000,16500),(440004,410004,'897675347v','Gold','28 tables','Table Decorations','null','null','Disco Light','null','oil lamp, baloons',3000,16000,19000),(440005,410005,'786754876v','Yellow','18 tables','Table Decorations','Wall Decorations','null','null','null','candles',1500,12000,13500),(440006,410006,'897685467v','Magenta','20 tables','Table Decorations','null','null','Disco Light','Entrance Arch','candles, baloon',2500,17000,19500),(440007,410007,'786547865v','Orange','25 tables','Table Decorations','null','null','Disco Light','Entrance Arch','candles, baloon',2500,17000,19500);
/*!40000 ALTER TABLE `event_decoration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_food_packages`
--

DROP TABLE IF EXISTS `event_food_packages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_food_packages` (
  `fp_event_id` int(11) NOT NULL,
  `fp_customer_NIC` varchar(10) NOT NULL,
  `no_of_plates` int(11) DEFAULT NULL,
  `fp_regular` varchar(45) DEFAULT NULL,
  `fp_customized` varchar(45) DEFAULT NULL,
  `fp_description` varchar(500) DEFAULT NULL,
  `fp_price` double DEFAULT NULL,
  `fp_total` double DEFAULT NULL,
  `order_status` smallint(1) DEFAULT NULL,
  `fp_menu_id` int(6) DEFAULT NULL,
  PRIMARY KEY (`fp_event_id`,`fp_customer_NIC`),
  KEY `fk_fp_cus_nic_idx` (`fp_customer_NIC`),
  CONSTRAINT `fk_fp_cus_nic` FOREIGN KEY (`fp_customer_NIC`) REFERENCES `event_customer_info` (`customer_NIC`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_fp_event_id` FOREIGN KEY (`fp_event_id`) REFERENCES `event` (`event_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_food_packages`
--

LOCK TABLES `event_food_packages` WRITE;
/*!40000 ALTER TABLE `event_food_packages` DISABLE KEYS */;
INSERT INTO `event_food_packages` VALUES (410001,'948602920v',230,'Package C',NULL,NULL,4590,NULL,0,550006),(410002,'937656467v',290,NULL,'Customized','Welcome Drink ,Chilli Paste ,Fish Stew/Ambulthiyal ,Pineapple Red Curry ,Steam Rice ,',133400,NULL,NULL,NULL),(410003,'936758334v',200,'Package A',NULL,NULL,4450,NULL,0,550004),(410004,'897675347v',350,'Package C',NULL,NULL,4590,NULL,0,550006),(410005,'786754876v',280,NULL,'Customized','Raita Salad ,Chicken Salad ,Fish Stew/Ambulthiyal ,Chicken Black Curry ,Welcome Drink ,',159600,NULL,NULL,NULL),(410006,'897685467v',230,'Package C',NULL,NULL,4590,NULL,0,550006),(410007,'786547865v',230,'Package A',NULL,NULL,4450,NULL,0,550004);
/*!40000 ALTER TABLE `event_food_packages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_hall`
--

DROP TABLE IF EXISTS `event_hall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_hall` (
  `hall_name` varchar(20) NOT NULL,
  `max_no_of_guests` int(11) DEFAULT NULL,
  `hall_price` double DEFAULT NULL,
  PRIMARY KEY (`hall_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_hall`
--

LOCK TABLES `event_hall` WRITE;
/*!40000 ALTER TABLE `event_hall` DISABLE KEYS */;
INSERT INTO `event_hall` VALUES ('Ballroom - max 200',200,10000),('Ockroom - max 250',250,12000),('Platinum - max 300',300,16000),('Rooftop - max 350',350,20000);
/*!40000 ALTER TABLE `event_hall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_order`
--

DROP TABLE IF EXISTS `event_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_order` (
  `order_event_id` int(11) NOT NULL,
  `order_customer_NIC` varchar(10) DEFAULT NULL,
  `order_menu_id` varchar(45) DEFAULT NULL,
  `order_status` varchar(10) DEFAULT NULL,
  `event_order_id` int(11) NOT NULL AUTO_INCREMENT,
  `event_order_des` varchar(500) DEFAULT NULL,
  `event_order_cus` varchar(45) DEFAULT NULL,
  `event_order_no_of_plates` int(11) DEFAULT NULL,
  PRIMARY KEY (`event_order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=430008 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_order`
--

LOCK TABLES `event_order` WRITE;
/*!40000 ALTER TABLE `event_order` DISABLE KEYS */;
INSERT INTO `event_order` VALUES (410001,'948602920v','550006','0',430001,NULL,NULL,230),(410002,'937656467v',NULL,'0',430002,'Welcome Drink ,Chilli Paste ,Fish Stew/Ambulthiyal ,Pineapple Red Curry ,Steam Rice ,','Customized',290),(410003,'936758334v','550004','0',430003,NULL,NULL,200),(410004,'897675347v','550006','0',430004,NULL,NULL,350),(410005,'786754876v',NULL,'0',430005,'Raita Salad ,Chicken Salad ,Fish Stew/Ambulthiyal ,Chicken Black Curry ,Welcome Drink ,','Customized',280),(410006,'897685467v','550006','0',430006,NULL,NULL,230),(410007,'786547865v','550004','0',430007,NULL,NULL,230);
/*!40000 ALTER TABLE `event_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_payment`
--

DROP TABLE IF EXISTS `event_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_payment` (
  `event_payment_customer_NIC` varchar(10) NOT NULL,
  `event_payment_event_ID` int(11) DEFAULT NULL,
  `event_payment_method` varchar(10) DEFAULT NULL,
  `event_payment_amount` float DEFAULT NULL,
  `event_Credit/Debit` varchar(10) DEFAULT NULL,
  `event_payment_due_date` date DEFAULT NULL,
  `event_payment_cashflow_ID` int(11) DEFAULT NULL,
  `event_payment_description` varchar(50) DEFAULT NULL,
  `event_payment_total` double DEFAULT NULL,
  `event_payment_balance` double DEFAULT NULL,
  PRIMARY KEY (`event_payment_customer_NIC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_payment`
--

LOCK TABLES `event_payment` WRITE;
/*!40000 ALTER TABLE `event_payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fin_assests`
--

DROP TABLE IF EXISTS `fin_assests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fin_assests` (
  `assests_id` int(11) NOT NULL AUTO_INCREMENT,
  `assests_cashflow_id` int(11) DEFAULT NULL,
  `assests_payment_status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`assests_id`),
  KEY `assests_cashflow_id_idx` (`assests_cashflow_id`),
  CONSTRAINT `assests_cashflow_id` FOREIGN KEY (`assests_cashflow_id`) REFERENCES `fin_cashflow` (`cashflow_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=140020 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fin_assests`
--

LOCK TABLES `fin_assests` WRITE;
/*!40000 ALTER TABLE `fin_assests` DISABLE KEYS */;
INSERT INTO `fin_assests` VALUES (140015,110017,'Credit'),(140016,110018,'Credit'),(140017,110019,'Credit'),(140018,110020,'Credit'),(140019,110021,'Debit');
/*!40000 ALTER TABLE `fin_assests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fin_budget`
--

DROP TABLE IF EXISTS `fin_budget`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fin_budget` (
  `budget_id` int(11) NOT NULL AUTO_INCREMENT,
  `budget_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `budget_description` varchar(45) DEFAULT NULL,
  `budget_department` varchar(20) DEFAULT NULL,
  `budget_amount` double DEFAULT NULL,
  `budget_payment_status` varchar(10) DEFAULT NULL,
  `budget_balance` double DEFAULT NULL,
  PRIMARY KEY (`budget_id`)
) ENGINE=InnoDB AUTO_INCREMENT=170007 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fin_budget`
--

LOCK TABLES `fin_budget` WRITE;
/*!40000 ALTER TABLE `fin_budget` DISABLE KEYS */;
INSERT INTO `fin_budget` VALUES (170000,'2016-08-01 00:00:00','Transport Budget','Transport',200000,'Debit',50000),(170001,'2016-08-01 00:00:00','Stock Monthly budget','Stock',750000,'Debit',492050),(170002,'2016-08-01 00:00:00','Room Budget','Room',200000,'Debit',192000),(170003,'2016-08-01 00:00:00','Restaurant Budget','Restaurant',300000,'Debit',300000),(170004,'2016-08-01 00:00:00','Hr Monthly budget','HR',1000000,'Debit',990000),(170005,'2016-09-01 00:00:00','Fin Monthly budget','Finance',200000,'Debit',200000),(170006,'2016-08-01 00:00:00','Event Monthly budget','Event',500000,'Debit',430000);
/*!40000 ALTER TABLE `fin_budget` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fin_budget_request`
--

DROP TABLE IF EXISTS `fin_budget_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fin_budget_request` (
  `budget_request_id` int(11) NOT NULL AUTO_INCREMENT,
  `request_budget_id` int(11) DEFAULT NULL,
  `budget_request_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `budget_request_department` varchar(20) DEFAULT NULL,
  `budget_request_amount` double DEFAULT NULL,
  `budget_request_status` varchar(10) DEFAULT 'Pending',
  PRIMARY KEY (`budget_request_id`),
  KEY `request_budget_id_idx` (`request_budget_id`),
  CONSTRAINT `request_budget_id` FOREIGN KEY (`request_budget_id`) REFERENCES `fin_budget` (`budget_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=180002 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fin_budget_request`
--

LOCK TABLES `fin_budget_request` WRITE;
/*!40000 ALTER TABLE `fin_budget_request` DISABLE KEYS */;
INSERT INTO `fin_budget_request` VALUES (180001,170003,'2016-09-20 19:40:20','Restaurant',66666,'Pending');
/*!40000 ALTER TABLE `fin_budget_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fin_cashflow`
--

DROP TABLE IF EXISTS `fin_cashflow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fin_cashflow` (
  `cashflow_id` int(11) NOT NULL AUTO_INCREMENT,
  `cashflow_date` date DEFAULT NULL,
  `cashflow_description` varchar(50) DEFAULT NULL,
  `cashflow_department` varchar(30) DEFAULT NULL,
  `cashflow_method` varchar(10) DEFAULT NULL,
  `cashflow_amount` double DEFAULT NULL,
  `cashflow_payment_type` varchar(20) DEFAULT NULL,
  `cashflow_payment_status` varchar(10) DEFAULT NULL,
  `cashflow_approval` varchar(20) DEFAULT 'not approved',
  PRIMARY KEY (`cashflow_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110041 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fin_cashflow`
--

LOCK TABLES `fin_cashflow` WRITE;
/*!40000 ALTER TABLE `fin_cashflow` DISABLE KEYS */;
INSERT INTO `fin_cashflow` VALUES (110001,'2016-08-20','Bill Payment','Restaurant','Money',440,'Income','Credit','approved'),(110002,'2016-09-22','Travel Package Booking','Transport','Money',5000,'Income','Credit','approved'),(110003,'2016-09-20','Chargers','Room','Money',8000,'Income','Credit','approved'),(110004,'2016-09-05','Birthday Party','Event','Money',60000,'Income','Credit','approved'),(110005,'2016-09-10','School Event','Event','Money',50000,'Income','Credit','approved'),(110006,'2016-09-04','Telephone Bill','HR','Bank',10000,'Expenditure','Debit','approved'),(110007,'2016-09-02','Decoration Chargers','Event','Money',6000,'Expenditure','Debit','approved'),(110008,'2016-09-06','Room Services','Room','Money',8000,'Expenditure','Debit','approved'),(110009,'2016-09-13','Fuel Chargers','Transport','Bank',150000,'Expenditure','Debit','approved'),(110010,'2016-09-09','Delevery Chargers','Stock','Money',6000,'Expenditure','Debit','approved'),(110011,'2016-07-13','Stock Services','Stock','Money',7000,'Expenditure','Debit','approved'),(110012,'2016-06-02','Initial Capital','','Money',200000,'Investment','Credit','approved'),(110013,'2016-07-05','Share holders','','Bank',1000000,'Investment','Credit','approved'),(110014,'2016-07-04','Share Holdeers','','Bank',900000,'Investment','Credit','approved'),(110015,'2016-09-04','Returns','','Bank',100000,'Investment','Debit','approved'),(110016,'2016-07-03','investments','','Bank',350000,'Investment','Credi','approved'),(110017,'2016-09-01','Office wares','HR','Bank',100000,'Assest','Debit','approved'),(110018,'2016-10-03','Tables','Stock','Bank',50000,'Assest','Debit','approved'),(110019,'2016-08-02','Cupboards','Finance','Money',50000,'Assest','Debit','approved'),(110020,'2017-07-04','Table chargers','Finance','Money',100000,'Assest','Debit','approved'),(110021,'2016-06-10','Damages for Assets','Stock','Bank',30000,'Assest','Credit','approved'),(110022,'2016-06-07','Petty Cash','Event','Money',15000,'Expenditure','Debit','approved'),(110023,'2016-07-12','Electricity Bill','Event','Bank',50000,'Expenditure','Debit','not approved'),(110024,'2016-07-04','Decoration Carges','Event','Money',20000,'Expenditure','Debit','not approved'),(110025,'2016-07-05','Room Reservation','Room','Bank',100000,'Income','Credit','not approved'),(110026,'2016-07-05','Bank Loan','Room','Bank',500000,'Liability','Credit','approved'),(110027,'2016-09-03','Monthly intrest','Room','Bank',2000,'Liability','Credit','approved'),(110028,'2016-09-03','monthly installment','Room','Bank',40000,'Liability','Debit','approved'),(110029,'2016-09-21','Wine','Stock','Bank',20000,'Expenditure','Debit','not approved'),(110030,'2016-09-21','Toaster','Stock','Bank',21000,'Expenditure','Debit','not approved'),(110031,'2016-09-21','Dish towel','Stock','Bank',42000,'Expenditure','Debit','not approved'),(110032,'2016-09-21','Pillow case','Stock','Bank',15750,'Expenditure','Debit','not approved'),(110033,'2016-09-21','Toilet paper','Stock','Bank',12000,'Expenditure','Debit','not approved'),(110034,'2016-09-21','Lemon','Stock','Bank',12800,'Expenditure','Debit','not approved'),(110035,'2016-09-21','Bedsheet','Stock','Bank',24000,'Expenditure','Debit','not approved'),(110036,'2016-09-21','Bath towel','Stock','Bank',6500,'Expenditure','Debit','not approved'),(110037,'2016-09-21','Camping tent','Stock','Bank',60000,'Expenditure','Debit','not approved'),(110038,'2016-09-21','Waste basket','Stock','Bank',6300,'Expenditure','Debit','not approved'),(110039,'2016-09-21','Rope','Stock','Bank',9100,'Expenditure','Debit','not approved'),(110040,'2016-09-21','Hall carpet','Stock','Bank',22500,'Expenditure','Debit','not approved');
/*!40000 ALTER TABLE `fin_cashflow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fin_expenditure`
--

DROP TABLE IF EXISTS `fin_expenditure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fin_expenditure` (
  `expense_id` int(11) NOT NULL AUTO_INCREMENT,
  `expense_cashflow_id` int(11) DEFAULT NULL,
  `expense_payment_status` varchar(10) DEFAULT NULL,
  `expense_budget_status` varchar(10) DEFAULT 'pending',
  PRIMARY KEY (`expense_id`),
  KEY `expense_cashflow_id_idx` (`expense_cashflow_id`),
  CONSTRAINT `expense_cashflow_id` FOREIGN KEY (`expense_cashflow_id`) REFERENCES `fin_cashflow` (`cashflow_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=130008 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fin_expenditure`
--

LOCK TABLES `fin_expenditure` WRITE;
/*!40000 ALTER TABLE `fin_expenditure` DISABLE KEYS */;
INSERT INTO `fin_expenditure` VALUES (130001,110006,'Credit','pending'),(130002,110007,'Credit','pending'),(130003,110008,'Credit','pending'),(130004,110009,'Credit','pending'),(130005,110010,'Credit','pending'),(130006,110011,'Credit','pending'),(130007,110022,'Credit','pending');
/*!40000 ALTER TABLE `fin_expenditure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fin_income`
--

DROP TABLE IF EXISTS `fin_income`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fin_income` (
  `income_id` int(11) NOT NULL AUTO_INCREMENT,
  `income_cashflow_id` int(11) DEFAULT NULL,
  `income_payment_status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`income_id`),
  KEY `income_cashflow_id_idx` (`income_cashflow_id`),
  CONSTRAINT `income_cashflow_id` FOREIGN KEY (`income_cashflow_id`) REFERENCES `fin_cashflow` (`cashflow_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=120006 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fin_income`
--

LOCK TABLES `fin_income` WRITE;
/*!40000 ALTER TABLE `fin_income` DISABLE KEYS */;
INSERT INTO `fin_income` VALUES (120001,110003,'Debit'),(120002,110001,'Debit'),(120003,110002,'Debit'),(120004,110004,'Debit'),(120005,110005,'Debit');
/*!40000 ALTER TABLE `fin_income` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fin_investment`
--

DROP TABLE IF EXISTS `fin_investment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fin_investment` (
  `investment_id` int(11) NOT NULL AUTO_INCREMENT,
  `investment_cashflow_id` int(11) DEFAULT NULL,
  `investment_payment_status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`investment_id`),
  KEY `investment_cashflow_id_idx` (`investment_cashflow_id`),
  CONSTRAINT `investment_cashflow_id` FOREIGN KEY (`investment_cashflow_id`) REFERENCES `fin_cashflow` (`cashflow_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=150006 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fin_investment`
--

LOCK TABLES `fin_investment` WRITE;
/*!40000 ALTER TABLE `fin_investment` DISABLE KEYS */;
INSERT INTO `fin_investment` VALUES (150001,110012,'Debit'),(150002,110013,'Debit'),(150003,110014,'Debit'),(150004,110015,'Credit'),(150005,110016,'Debit');
/*!40000 ALTER TABLE `fin_investment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fin_liability`
--

DROP TABLE IF EXISTS `fin_liability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fin_liability` (
  `liability_id` int(11) NOT NULL AUTO_INCREMENT,
  `liability_cashflow_id` int(11) DEFAULT NULL,
  `liability_payment_status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`liability_id`),
  KEY `liability_cashflow_id_idx` (`liability_cashflow_id`),
  CONSTRAINT `liability_cashflow_id` FOREIGN KEY (`liability_cashflow_id`) REFERENCES `fin_cashflow` (`cashflow_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=160004 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fin_liability`
--

LOCK TABLES `fin_liability` WRITE;
/*!40000 ALTER TABLE `fin_liability` DISABLE KEYS */;
INSERT INTO `fin_liability` VALUES (160001,110026,'Debit'),(160002,110027,'Debit'),(160003,110028,'Credit');
/*!40000 ALTER TABLE `fin_liability` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fin_userlog`
--

DROP TABLE IF EXISTS `fin_userlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fin_userlog` (
  `userlog_id` int(11) NOT NULL AUTO_INCREMENT,
  `userlog_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `userlog_username` varchar(45) NOT NULL,
  `userlog_action_type` varchar(45) NOT NULL,
  `userlog_action` varchar(20) DEFAULT NULL,
  `userlog_action_id` int(11) DEFAULT NULL,
  `system_user_id` int(11) NOT NULL,
  PRIMARY KEY (`userlog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=190103 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fin_userlog`
--

LOCK TABLES `fin_userlog` WRITE;
/*!40000 ALTER TABLE `fin_userlog` DISABLE KEYS */;
INSERT INTO `fin_userlog` VALUES (190001,'2016-09-20 11:01:55','Sameera','System Login','Login',0,910023),(190002,'2016-09-20 11:02:29','Sameera','System Logout','Logout',0,910023),(190003,'2016-09-20 11:03:01','Sameera','System Login','Login',0,910023),(190004,'2016-09-20 11:07:38','Sameera','System Login','Login',0,910023),(190005,'2016-09-20 11:38:35','null','System Login','Login',0,0),(190006,'2016-09-20 11:38:46','null','Report','Cashflow Report',0,0),(190007,'2016-09-20 11:51:04','null','System Login','Login',0,0),(190008,'2016-09-20 11:52:11','null','System Login','Login',0,0),(190009,'2016-09-20 11:53:17','null','System Login','Login',0,0),(190010,'2016-09-20 11:54:56','null','System Login','Login',0,0),(190011,'2016-09-20 11:57:29','null','System Login','Login',0,0),(190012,'2016-09-20 11:59:20','null','System Login','Login',0,0),(190013,'2016-09-20 12:01:15','null','System Login','Login',0,0),(190014,'2016-09-20 12:01:37','null','System Login','Login',0,0),(190015,'2016-09-20 12:02:57','null','System Login','Login',0,0),(190016,'2016-09-20 12:33:27','Nisal','System Login','Login',0,910019),(190017,'2016-09-20 12:34:38','Nisal','System Login','Login',0,910019),(190018,'2016-09-20 12:36:50','Nisal','System Login','Login',0,910019),(190019,'2016-09-20 12:40:28','Nisal','System Login','Login',0,910019),(190020,'2016-09-20 12:41:43','Nisal','System Login','Login',0,910019),(190021,'2016-09-20 12:45:19','Nisal','System Login','Login',0,910019),(190022,'2016-09-20 12:49:00','null','System Login','Login',0,0),(190023,'2016-09-20 12:49:24','null','System Login','Login',0,0),(190024,'2016-09-20 12:49:51','null','System Login','Login',0,0),(190025,'2016-09-20 12:50:20','Nisal','System Login','Login',0,910019),(190026,'2016-09-20 12:51:06','Nisal','System Login','Login',0,910019),(190027,'2016-09-20 12:53:25','null','System Login','Login',0,0),(190028,'2016-09-20 12:56:42','null','System Login','Login',0,0),(190029,'2016-09-20 12:58:43','null','System Login','Login',0,0),(190030,'2016-09-20 13:00:52','null','System Login','Login',0,0),(190031,'2016-09-20 13:00:58','null','System Login','Login',0,0),(190032,'2016-09-20 13:07:46','null','System Login','Login',0,0),(190033,'2016-09-20 13:07:55','null','System Login','Login',0,0),(190034,'2016-09-20 13:09:17','null','System Login','Login',0,0),(190035,'2016-09-20 14:41:58','null','System Login','Login',0,0),(190036,'2016-09-20 14:43:00','null','System Login','Login',0,0),(190037,'2016-09-20 14:56:55','null','System Login','Login',0,0),(190038,'2016-09-20 14:57:47','null','System Login','Login',0,0),(190039,'2016-09-20 15:00:05','null','System Login','Login',0,0),(190040,'2016-09-20 15:02:10','null','System Login','Login',0,0),(190041,'2016-09-20 15:02:39','null','System Login','Login',0,0),(190042,'2016-09-20 15:03:13','null','System Login','Login',0,0),(190043,'2016-09-20 15:06:30','null','System Login','Login',0,0),(190044,'2016-09-20 15:09:38','null','System Login','Login',0,0),(190045,'2016-09-20 15:11:22','null','System Login','Login',0,0),(190046,'2016-09-20 15:24:00','null','System Login','Login',0,0),(190047,'2016-09-20 15:24:24','Nisal','System Login','Login',0,910019),(190048,'2016-09-20 15:25:37','Nisal','System Login','Login',0,910019),(190049,'2016-09-20 15:26:45','Nisal','System Login','Login',0,910019),(190050,'2016-09-20 15:27:39','Nisal','System Login','Login',0,910019),(190051,'2016-09-20 15:30:01','null','System Login','Login',0,0),(190052,'2016-09-20 17:51:41','Sameera','System Login','Login',0,910023),(190053,'2016-09-20 17:51:47','Sameera','System Logout','Logout',0,910023),(190054,'2016-09-20 17:51:53','Sameera','System Login','Login',0,910023),(190055,'2016-09-20 17:52:04','Sameera','Report','Cashflow Report',0,910023),(190056,'2016-09-20 17:52:14','Sameera','Report','Cashflow Report',0,910023),(190057,'2016-09-20 17:52:16','Sameera','Report','Cashflow Report',0,910023),(190058,'2016-09-20 17:52:18','Sameera','Report','Cashflow Report',0,910023),(190059,'2016-09-20 17:56:03','null','System Login','Login',0,0),(190060,'2016-09-20 17:56:18','null','Report','Cashflow Report',0,0),(190061,'2016-09-20 17:56:26','null','Report','Bank Statement',0,0),(190062,'2016-09-20 17:56:32','null','Report','Balance Sheet',0,0),(190063,'2016-09-20 17:56:39','null','Report','Profit/Loss',0,0),(190064,'2016-09-20 18:27:41','null','System Login','Login',0,0),(190065,'2016-09-21 04:03:23','Nisal','System Login','Login',0,910019),(190066,'2016-09-21 04:03:53','Nisal','System Login','Login',0,910019),(190067,'2016-09-21 04:03:57','Nisal','System Login','Login',0,910019),(190068,'2016-09-21 04:28:32','Nisal','System Login','Login',0,910019),(190069,'2016-09-21 04:28:48','Nisal','System Login','Login',0,910019),(190070,'2016-09-21 04:29:19','Nisal','Income','Accept',120001,910019),(190071,'2016-09-21 04:29:52','Nisal','Income','Update',120001,910019),(190072,'2016-09-21 04:29:58','Nisal','Income','Accept',120002,910019),(190073,'2016-09-21 04:30:00','Nisal','Income','Accept',120003,910019),(190074,'2016-09-21 04:31:48','Nisal','Income','Add',120004,910019),(190075,'2016-09-21 04:32:45','Nisal','Income','Add',120005,910019),(190076,'2016-09-21 04:34:00','Nisal','Expenditure','Add',130001,910019),(190077,'2016-09-21 04:34:49','Nisal','Expenditure','Add',130002,910019),(190078,'2016-09-21 04:35:32','Nisal','Expenditure','Add',130003,910019),(190079,'2016-09-21 04:36:55','Nisal','Expenditure','Add',130004,910019),(190080,'2016-09-21 04:38:11','Nisal','Expenditure','Add',130005,910019),(190081,'2016-09-21 04:38:59','Nisal','Expenditure','Add',130006,910019),(190082,'2016-09-21 04:40:09','Nisal','Investment','Add',150001,910019),(190083,'2016-09-21 04:40:49','Nisal','Investment','Add',150002,910019),(190084,'2016-09-21 04:42:32','Nisal','Investment','Add',150003,910019),(190085,'2016-09-21 04:43:42','Nisal','Investment','Update',150003,910019),(190086,'2016-09-21 04:43:48','Nisal','Investment','Update',150003,910019),(190087,'2016-09-21 04:43:56','Nisal','Investment','Update',150002,910019),(190088,'2016-09-21 04:44:03','Nisal','Investment','Update',150001,910019),(190089,'2016-09-21 04:44:09','Nisal','Investment','Add',150004,910019),(190090,'2016-09-21 04:44:41','Nisal','Investment','Add',150005,910019),(190091,'2016-09-21 04:45:59','Nisal','Assests','Add',140015,910019),(190092,'2016-09-21 04:46:27','Nisal','Assests','Add',140016,910019),(190093,'2016-09-21 04:47:04','Nisal','Assests','Add',140017,910019),(190094,'2016-09-21 04:47:38','Nisal','Assests','Add',140018,910019),(190095,'2016-09-21 04:48:51','Nisal','Assests','Add',140019,910019),(190096,'2016-09-21 04:50:11','Nisal','Expenditure','Add',130007,910019),(190097,'2016-09-21 04:51:58','Nisal','Cashflow','Add',110023,910019),(190098,'2016-09-21 04:53:56','Nisal','Cashflow','Add',110024,910019),(190099,'2016-09-21 04:55:20','Nisal','Cashflow','Add',110025,910019),(190100,'2016-09-21 04:55:59','Nisal','Liability','Add',160001,910019),(190101,'2016-09-21 04:56:52','Nisal','Liability','Add',160002,910019),(190102,'2016-09-21 04:57:51','Nisal','Liability','Add',160003,910019);
/*!40000 ALTER TABLE `fin_userlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_item`
--

DROP TABLE IF EXISTS `food_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food_item` (
  `food_id` int(6) NOT NULL AUTO_INCREMENT,
  `food_name` varchar(45) DEFAULT NULL,
  `food_price` varchar(45) DEFAULT NULL,
  `food_item_status` smallint(1) DEFAULT '1',
  PRIMARY KEY (`food_id`)
) ENGINE=InnoDB AUTO_INCREMENT=520051 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_item`
--

LOCK TABLES `food_item` WRITE;
/*!40000 ALTER TABLE `food_item` DISABLE KEYS */;
INSERT INTO `food_item` VALUES (520000,'Mixed Fried Rice (Basmathi)','Full-280.00,Half-180.00',1),(520001,'Pasta with Cheese Sauce','Full-220.00,Half-200.00',1),(520002,'Deviled Chicken','Full-320.00,Half-200.00',1),(520003,'Pork Black Curry','Full-250.00,Half-210.00',1),(520004,'Battered Mushrooms','Full-250.00,Half-210.00',1),(520005,'Mix Vegetable Curry','Full-150.00,Half-110.00',1),(520006,'Potato Tempered','Full-150.00,Half-110.00',1),(520007,'Vegetable Noodles','Full-230.00,Half-210.00',1),(520008,'Papadam','Full-150.00,Half-110.00',1),(520009,'Fish Ambulthiyal','Full-260.00,Half-210.00',1),(520010,'Ice Cream (Vanilla)','Full-150.00,Half-110.00',1),(520011,'Ice Cream (Chocolate)','Full-170.00,Half-140.00',1),(520012,'Watalappan ','Full-150.00,Half-110.00',1),(520013,'White Rice','Full-200.00,Half-180.00',1),(520014,'Noodles','Full-220.00,Half-190.00',1),(520015,'String Hoppers','Full-250.00,Half-210.00',1),(520016,'Sambol','Full-150.00,Half-110.00',1),(520017,'Prawns Curry','Full-450.00,Half-410.00',1),(520018,'Cashew Greenpeace Curry','Full-180.00,Half-120.00',1),(520019,'Maldives Fish Sambol','Full-250.00,Half-210.00',1),(520020,'Chutney','Full-350.00,Half-310.00',1),(520021,'Fish Cutlets','Full-150.00,Half-110.00',1),(520022,'Potatoes White Curry','Full-260.00,Half-210.00',1),(520023,'Gotukola Curry','Full-250.00,Half-210.00',0),(520024,'Brinjal Tempered','Full-150.00,Half-110.00',1),(520025,'Jelly','Full-200.00,Half-190.00',1),(520026,'Ice Coffee','Full-150.00,Half-110.00',1),(520027,'Dhal Curry','Full-280.00,Half-230.00',1),(520028,'Brinjal Moju','Full-150.00,Half-110.00',1),(520029,'Egg Salad','Full-150.00,Half-110.00',1),(520030,'Malay Pickle','Full-150.00,Half-110.00',1),(520031,'Fried Rice (Keeri Samba)','Full-240.00,Half-200.00',1),(520032,'Mushrooms with Garlic Sauce','Full-260.00,Half-210.00',1),(520033,'Russian Salad','Full-150.00,Half-110.00',1),(520034,'Devilled Prawns','Full-350.00,Half-310.00',1),(520035,'Welcome Drink','Full-80.00',1),(520036,'Fried Rice (Basmathi)','Full-200.00',1),(520037,'Steam Rice','Full-100.00',1),(520038,'Fish Stew/Fish Sweet Sauce','Full-120.00',1),(520039,'Brinjal Pahi','Full-110.00',1),(520040,'Pineapple Red Curry','Full-90.00',1),(520041,'Chicken Salad','Full-120.00',1),(520042,'Raita Salad','Full-100.00',1),(520043,'Chilli Paste','Full-70.00',1),(520044,'Fruit Trifle','Full-80.00',1),(520045,'Chocolate Biscuit Pudding','Full-110.00',1),(520046,'Cut Fruits','Full-150.00',1),(520047,'Plain Rice (Basmathi)','Full-110.00',1),(520048,'Macaroni with Chili Sauce','Full-140.00',1),(520049,'Fish Stew/Ambulthiyal','Full-120.00',1),(520050,'Chicken Black Curry','Full-150.00',1);
/*!40000 ALTER TABLE `food_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_employee`
--

DROP TABLE IF EXISTS `hr_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_employee` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_fname` varchar(15) DEFAULT NULL,
  `employee_lname` varchar(15) DEFAULT NULL,
  `employee_nic` varchar(15) DEFAULT NULL,
  `employee_telephone` varchar(10) DEFAULT NULL,
  `employee_address` varchar(50) DEFAULT NULL,
  `employee_sex` varchar(10) DEFAULT NULL,
  `employee_DOB` date DEFAULT NULL,
  `employee_designation` varchar(45) DEFAULT NULL,
  `employee_type` varchar(45) DEFAULT NULL,
  `employee_hourly_rate` varchar(45) DEFAULT '00',
  `employee_basic_salary` varchar(45) DEFAULT '00',
  `employee_department` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=210043 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_employee`
--

LOCK TABLES `hr_employee` WRITE;
/*!40000 ALTER TABLE `hr_employee` DISABLE KEYS */;
INSERT INTO `hr_employee` VALUES (210007,'Nisal','Hewagamage','952221999V','0723342215','Pannipitiya','Male','1992-09-05','Managing Dirtector','Permanent','00','500000','All'),(210008,'Sapumal','Kalutota','931233414V','0771223123','Nawala','Male','1993-10-23','Stock Manager','Permanent','00','100000','Stock'),(210009,'Nipun','Kalutota','933213414V','0771223123','Nawala','Male','1983-10-23','Stock Controller','Permanent','00','80000','Stock'),(210010,'Nirodha','Kalutota','833213414V','0711223123','Nawala','Male','1986-10-13','Store Keeper','Permanent','00','60000','Stock'),(210011,'Hansini','Kalutota','863213414V','0711228673','Rajagiriya','Female','1983-10-17','Store Keeper','Permanent','00','60000','Stock'),(210012,'Chathura','Herath','783213414V','0722228673','Rajagiriya','Male','1977-10-13','Stock Controller','Permanent','00','80000','Stock'),(210013,'Sameera','Madushan','930263621V','0712541256','Colombo 03','Male','1993-02-15','Finance Manager','Permanent','00','100000','Finance'),(210014,'Supun','Asanka','892541254V','0724154856','Maharagama','Male','1989-09-09','Accountant','Permanent','00','75000','Finance'),(210015,'Samith','Dilantha','921548752V','0719709950','Boralesgamuwa','Male','1993-05-14','Accounts Assistant','Permanent','00','40000','Finance'),(210016,'Damith','Perera','925142518V','0715245841','Colombo','Male','1994-02-20','Accounts Assistant','Permanent','00','40000','Finance'),(210017,'Sandun','Bandara','925845217V','0714521584','Nugegoda','Male','1990-11-15','Accounts Assistant','Permanent','00','40000','Finance'),(210018,'Rangana','Perera','874585652V','0713524517','Malabe','Male','1985-10-25','Accounts Assistant','Permanent','00','40000','Finance'),(210019,'Hashini','Silva','902548745V','0725412547','Malabe','Female','1990-12-15','Accounts Assistant','Permanent','00','40000','Finance'),(210020,'Chethana','Arunodh','941254125V','0725415588','Colombo','Male','1994-05-14','Transport Manager','Permanent','00','60000','Transport'),(210021,'Ashani','Dikowita','942586957V','0715245863','Malabe','Female','1994-11-02','Room Manager','Permanent','00','80000','Room'),(210022,'Buddhini','Dias','925486521V','0713524965','Nugegoda','Female','1992-02-14','Event Manager','Permanent','00','100000','Event'),(210023,'Uthpala','Liyanage','932548564V','0714524562','Kottawa','Female','1993-10-15','Restaurant Manager','Permanent','00','80000','Restaurant'),(210024,'Dinuka','Perera','952541485V','0722541215','Maharagama','Male','1995-09-05','HR Manager','Permanent','00','100000','HR'),(210025,'Dilshi','Jayalath','963232326V','0778364372','Galle','Female','1994-09-05','Cashier','Permanent','00','20000','Restaurant'),(210026,'Yomali','Sigera','674537454V','0725645375','Nugegoda','Female','1993-09-03','Cashier','Permanent','00','20000','Restaurant'),(210027,'Chethana','Arunodh','957524275V','0715654547','Piliyandala','Male','1995-07-23','Cashier','Permanent','00','20000','Restaurant'),(210028,'Jayani','Chathurangi','836357356V','0737567454','Galle','Female','1993-05-04','Cashier','Permanent','00','18000','Restaurant'),(210029,'Samantha','Liyanage','526542635V','0775436547','Piliyandala','Male','1992-05-23','Cashier','Permanent','00','23000','Restaurant'),(210030,'Senith','Perera','785387345V','0774326778','Galle','Male','1992-07-12','Cashier','Permanent','00','24000','Restaurant'),(210031,'Vimukthi','De Silva','975453576V','0784566778','Colombo','Male','1992-01-12','Cashier','Permanent','00','24000','Restaurant'),(210032,'Saranga','Dissanayake','988745377V','0924567889','Colombo','Male','1992-09-12','Cashier','Permanent','00','12000','Restaurant'),(210033,'Nethmie','Wijesinghe','985653427V','0715664799','Galle','Female','1992-07-12','Cashier','Permanent','00','20000','Restaurant'),(210034,'Sunil','Perera','950702310V','0752837634','Colombo','Female','1992-05-07','Finace Manager','Permanent','','15000','Finance Department'),(210035,'Ranjith','Perera','950715826V','0112509577','Colombo','Male','1991-05-15','Event Manager','Permanent','','45000','Event Management Department'),(210036,'Dilanka','Thusith','950712510V','0112509766','Malabe','Male','1990-11-02','Room Manager','Permanent','','85000','Room Mangement Department'),(210037,'Padmika','Malsri','950071510V','0773358366','Dehiwala','Female','1989-07-11','Stock Manager','Permanent','','80000','Stock Mangement Department'),(210038,'Ranil','Perera','920701580V','0785223471','Mahargama','Male','1987-03-03','HR Manager','Permanent','','72000','HR Department'),(210039,'Manuka','Perera','950201510V','0752837234','Colombo ','Male','1980-06-11','Stuart','Part Time ','120','80000','Room Mangement Department'),(210040,'Yashoda','Permachandra','950706510V','0112609788','Dehiwala','Female','1989-05-11','Clark','Part Time ','110','80000','Room Mangement Department'),(210041,'Geshani','Perera','950701520V','0752837631','Nikape','Female','1992-04-08','Stuart','Part Time ','132','80000','Room Mangement Department'),(210042,'Nuwan','Peiris','932845092V','0773593824','Homagama','Male','1993-12-03','Store Keeper','Permanent','00','50000','Stock');
/*!40000 ALTER TABLE `hr_employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_leaves`
--

DROP TABLE IF EXISTS `hr_leaves`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_leaves` (
  `leaves_id` int(11) NOT NULL AUTO_INCREMENT,
  `leaves_from_date` date DEFAULT NULL,
  `leaves_end_date` date DEFAULT NULL,
  `leaves_resson` varchar(45) DEFAULT NULL,
  `leaves_employee_id` int(11) NOT NULL,
  `leave_status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`leaves_id`),
  KEY `leaves_employee_id_idx` (`leaves_employee_id`),
  CONSTRAINT `leaves_employee_id` FOREIGN KEY (`leaves_employee_id`) REFERENCES `hr_employee` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=240001 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_leaves`
--

LOCK TABLES `hr_leaves` WRITE;
/*!40000 ALTER TABLE `hr_leaves` DISABLE KEYS */;
/*!40000 ALTER TABLE `hr_leaves` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_salary`
--

DROP TABLE IF EXISTS `hr_salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_salary` (
  `salary_id` int(11) NOT NULL AUTO_INCREMENT,
  `salary_other_pay` varchar(45) DEFAULT NULL,
  `salary_deduction` varchar(45) DEFAULT NULL,
  `salary_emp_id` int(11) NOT NULL,
  `salary_total` varchar(45) DEFAULT NULL,
  `salary_status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`salary_id`),
  UNIQUE KEY `salary_emp_id_UNIQUE` (`salary_emp_id`),
  CONSTRAINT `salary_employee_id` FOREIGN KEY (`salary_emp_id`) REFERENCES `hr_employee` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=220001 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_salary`
--

LOCK TABLES `hr_salary` WRITE;
/*!40000 ALTER TABLE `hr_salary` DISABLE KEYS */;
/*!40000 ALTER TABLE `hr_salary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_wage`
--

DROP TABLE IF EXISTS `hr_wage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_wage` (
  `wage_id` int(11) NOT NULL AUTO_INCREMENT,
  `wage_hours_worked` varchar(45) DEFAULT NULL,
  `wage_deduction` varchar(45) DEFAULT NULL,
  `wage_total` varchar(45) DEFAULT NULL,
  `wage_employee_id` int(11) NOT NULL,
  `wage_other_pay` varchar(45) DEFAULT NULL,
  `wage_status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`wage_id`),
  UNIQUE KEY `wage_employee_id_UNIQUE` (`wage_employee_id`),
  CONSTRAINT `wage_emp_id` FOREIGN KEY (`wage_employee_id`) REFERENCES `hr_employee` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=230001 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_wage`
--

LOCK TABLES `hr_wage` WRITE;
/*!40000 ALTER TABLE `hr_wage` DISABLE KEYS */;
/*!40000 ALTER TABLE `hr_wage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(45) NOT NULL,
  `item_units` varchar(45) NOT NULL,
  `item_description` varchar(60) DEFAULT NULL,
  `item_category` int(11) DEFAULT NULL,
  `item_reorder_level` int(11) NOT NULL,
  `item_availability` varchar(45) NOT NULL DEFAULT 'available',
  PRIMARY KEY (`item_id`),
  UNIQUE KEY `item_name_UNIQUE` (`item_name`),
  KEY `item_category_idx` (`item_category`),
  CONSTRAINT `item_category` FOREIGN KEY (`item_category`) REFERENCES `item_category` (`item_category_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=320038 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (320000,'Rice','kilogram','Samba',310000,500,'available'),(320001,'Curtain','metre','Silk',310003,100,'available'),(320002,'Event Chair','piece','Damro red ',310002,600,'available'),(320003,'Banana','kilogram','Kolikuttu',310000,425,'available'),(320004,'Wine','litre','Milk Wine',310001,100,'available'),(320005,'Spoons','piece','silver',310004,350,'available'),(320010,'Orange','kilogram','Australian',310000,200,'available'),(320011,'Onion','kilogram','Red Onion',310000,400,'available'),(320012,'Kettle','piece','Electric Kettle',310008,100,'available'),(320013,'Pillow case','piece','Cotton Pillow cases',310010,100,'available'),(320014,'Dish towel','piece','5x5 size towel',310004,300,'available'),(320015,'Toilet paper','piece','Simply soft paper',310010,100,'available'),(320016,'Sugar','kilogram','White sugar',310000,400,'available'),(320017,'Hand towel','piece','Ultra soft hand towel',310010,250,'available'),(320018,'Camping tent','piece','Tough armor tent',310011,200,'available'),(320019,'Bedsheet','piece','XL white bedsheet',310010,50,'available'),(320020,'Hanger','piece','black hanger',310010,50,'available'),(320021,'Soap dish','piece','Light soap ',310010,300,'removed'),(320022,'Lemon','litre','Lemon juice',310001,100,'available'),(320023,'Espresso','litre','Dark reddish crema',310001,150,'available'),(320024,'Rope','meter','Rhino ultradurable',310011,60,'available'),(320025,'Hot chocolate','litre','Extra thick',310001,100,'available'),(320026,'Bath towel','piece','XL white cotton',310010,60,'available'),(320027,'Fork','piece','Silver fork',310004,400,'available'),(320028,'Butter knife','piece','Light silver',310004,300,'available'),(320029,'Mattress pad','piece','Cotton extra soft',310010,250,'available'),(320030,'Waste basket','piece','Plastic medium size',310012,30,'available'),(320031,'Toaster','piece','Electric toaster',310008,50,'available'),(320032,'Fanta','litre','Lemon flavored',310001,300,'removed'),(320033,'Coca cola','litre','Diet cola',310001,300,'removed'),(320034,'Room Heater','piece','400W heater',310008,200,'available'),(320035,'Chandelier bulb','piece','Golden 5 40W bulb',310012,120,'available'),(320036,'Room carpet','meter','50x50m carpet',310003,300,'available'),(320037,'Hall carpet','piece','100x70m red ',310003,70,'available');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_category`
--

DROP TABLE IF EXISTS `item_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_category` (
  `item_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_category_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`item_category_id`),
  UNIQUE KEY `item_category_name_UNIQUE` (`item_category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=310013 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_category`
--

LOCK TABLES `item_category` WRITE;
/*!40000 ALTER TABLE `item_category` DISABLE KEYS */;
INSERT INTO `item_category` VALUES (310001,'Beverages'),(310003,'Cloth'),(310008,'Electric Items'),(310012,'Event essentials'),(310000,'Food '),(310002,'Furniture'),(310004,'Kitchen Items'),(310010,'Room items'),(310011,'Travel items');
/*!40000 ALTER TABLE `item_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `menu_id` int(6) NOT NULL AUTO_INCREMENT,
  `menu_des` varchar(500) NOT NULL,
  `menu_price` float(7,2) DEFAULT NULL,
  `menu_status` smallint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=550012 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (550000,'DISHES : , ,Mixed Fried Rice (Basmathi),Pasta with Cheese Sauce,Deviled Chicken,Pork Black Curry,Battered Mushrooms,Mix Vegetable Curry,Potato Tempered,Vegetable Noodles,Papadam,Fish Ambulthiyal, ,DESSERTS : , ,Ice Cream (Vanilla),Ice Cream (Chocolate),Watalappan ',2390.00,1),(550001,'DISHES : , ,White Rice,Noodles,String Hoppers,Sambol,Prawns Curry,Cashew Greenpeace Curry,Maldives Fish Sambol,Chutney,Fish Cutlets,Potatoes White Curry,Fish Ambulthiyal,Brinjal Tempered, ,DESSERTS : , ,Ice Cream (Vanilla),Ice Cream (Chocolate),Jelly,Watalappan,Ice Coffee',2960.00,1),(550002,'DISHES : , ,White Rice,Deviled Chicken,Dhal Curry,Hoppers,Sambol,Cashew Greenpeace Curry,Brinjal Moju,Egg Salad,Fish Cutlets,Malay Pickle,Fish Ambulthiyal,Brinjal Tempered, ,DESSERTS : , ,Ice Cream (Vanilla),Ice Cream (Chocolate),Jelly',3200.00,1),(550003,'DISHES : , ,Fried Rice (Keeri Samba),Deviled Chicken,Mushrooms with Garlic Sauce,Papadam,Sambol,Dhal Curry,Russian Salad,Brinjal Moju,Egg Salad,Fish Cutlets,Devilled Prawns,Fish Ambulthiyal,Potato Tempered, ,DESSERTS : , ,Ice Cream (Vanilla),Ice Cream (Chocolate),Jelly',2650.00,1),(550004,'Welcome Drink,Fried Rice (Basmathi),Steam Rice,Pasta with Cheese Sauce,Deviled Chicken,Pork Black Curry,Fish Stew/Fish Sweet Sauce,Brinjal Pahi,Pineapple Red Curry,Mix Vegetable Curry,Battered Mushrooms,Chicken Salad,Raita Salad,Egg Salad,Chilli Paste,Fruit Trifle,Chocolate Biscuit Pudding,Cut Fruits,Ice Cream (Vanilla),Jelly',4450.00,1),(550005,'Welcome Drink,Fried Rice (Basmathi),Plain Rice (Basmathi),Macaroni with Chili Sauce,Chicken Black Curry,Devilled Prawns,Fish Stew/Ambulthiya,Potato Tempered,Cashew Green Peas Curry,Brinjal Moju,Malay Pickle,Mushrooms with Garlic Sauce,Russian Salad,Fish Cutlets,Egg Salad on Mirror,Watalappam,Coffee Caramel/Chocolate Mousse,Cut Fruits\nJelly\nIce Cream (Vanilla)',3460.00,1),(550006,'Welcome Drink,Fried Rice (Keeri Samba),Plain Rice,Red Rice,Spicy Noodles,Deviled Chicken,Chicken Black Curry,Fish Ambulthiyal,Potato Tempered,Cashew Green Peas Curry,Maldive Fish Salad,Malay Pickle/Sinhala Pickel,Mix Vegetable Salad,Egg Salad,Fish Cutlets,Watalappam or Fruit Salad,Ice Cream (Vanilla),Coffee Caramel,Jelly',4590.00,1),(550007,'Welcome Drink,Fried Rice (Keeri Samba),Plain Rice,Noodles,Vegetable Noodles,Deviled Chicken,Fish Ambulthiyal,Cashew Green Peas Curry,Potato Tempered,Brinjal Moju,Malay Pickle,Egg Salad,Fish Cutlets,Papadam,Watalappam/Caramel Pudding,Ice Cream (Vanilla),Fruit Salad,Jelly',4520.00,1),(550008,'Welcome Drink,Fried Rice (Keeri Samba),Plain Rice,Deviled Chicken/Curry,Fish Ambulthiyal,Potato Tempered,Dhal Curry,Brinjal Moju,Malay Pickle,Vegetable Salad,Fish Cutlets,Papadam,Egg Salad,Watalappam,Ice Cream (Vanilla),Jelly',3670.00,1),(550010,'Deviled Chicken\nBattered Mushrooms\nMix Vegetable Curry\nPotato Tempered\nIce Cream (Chocolate)\nNoodles\nString Hoppers\nCashew Greenpeace Curry\nBattered Mushrooms\nSambol\nCashew Greenpeace Curry\n',1000.00,1),(550011,'DISHES : \n \nWhite Rice\nNoodles\nString Hoppers\nSambol\nPrawns Curry\nCashew Greenpeace Curry\nMaldives Fish Sambol\nChutney\nFish Cutlets\nPotatoes White Curry\nFish Ambulthiyal\nBrinjal Tempered\n \nDESSERTS : \n \nIce Cream (Vanilla)\nJelly\nWatalappan\nIce Coffee\nIce Cream (Chocolate)\n',2970.00,0);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_cashier`
--

DROP TABLE IF EXISTS `restaurant_cashier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant_cashier` (
  `cashier_id` int(6) NOT NULL,
  `cashier_fname` varchar(45) DEFAULT NULL,
  `cashier_lname` varchar(45) DEFAULT NULL,
  `cashier_shift_start` time DEFAULT NULL,
  `cashier_shift_end` time DEFAULT NULL,
  `cashier_status` smallint(1) DEFAULT '1',
  PRIMARY KEY (`cashier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_cashier`
--

LOCK TABLES `restaurant_cashier` WRITE;
/*!40000 ALTER TABLE `restaurant_cashier` DISABLE KEYS */;
INSERT INTO `restaurant_cashier` VALUES (210025,'Dilshi','Jayalath','08:30:00','13:30:00',1),(210026,'Yomali','Sigera','13:30:00','18:30:00',1),(210027,'Chethana','Arunodh','18:30:00','23:30:00',1),(210028,'Jayani','Chathurangi','08:30:00','13:30:00',0),(210029,'Samantha','De Silva','08:30:00','13:30:00',1),(210030,'Senith','Samarakoon','13:30:00','18:30:00',0),(210031,'Vimukthi','Alahakoon','13:30:00','18:30:00',1),(210032,'Saranga','Liyanage','18:30:00','23:30:00',1),(210033,'Nethmie','Pathirana','18:30:00','23:30:00',1);
/*!40000 ALTER TABLE `restaurant_cashier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_customer`
--

DROP TABLE IF EXISTS `restaurant_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant_customer` (
  `restaurant_customer_nic` varchar(10) NOT NULL,
  `restaurant_customer_name` varchar(45) DEFAULT NULL,
  `restaurant_customer_telephone` varchar(15) DEFAULT NULL,
  `restaurant_customer_email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`restaurant_customer_nic`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_customer`
--

LOCK TABLES `restaurant_customer` WRITE;
/*!40000 ALTER TABLE `restaurant_customer` DISABLE KEYS */;
INSERT INTO `restaurant_customer` VALUES ('672466427V','David De Silva','+61-345-678-911','david@hotmail.com'),('678944547V','Saman Jayathunga','+94-566-889-123','saman@yahoo.com'),('894253177V','Asmeth Ismail','+51-645-778-915','asmeth@gamil.com'),('895627887V','Buddhini Liyanage','+11-233-688-099','buddhini@yahoo.com'),('896723526V','Frank Daniel','+91-311-567-145','frank@gmail.com'),('918944547V','Saman Jayathunga','+94-566-889-123','saman@yahoo.com'),('926531266V','Nadeesha Gamage','+94-778-678-456','nadeesha@gmail.com'),('933954547V','Chethana Arunodh','+94-572-111-789','che@yahoo.com'),('958351518V','Saman Kodagoda','+94-547-073-672','samantha@yahoo.com'),('958353518V','Ashani Dickowita','+91-111-576-689','asha@gmail.com');
/*!40000 ALTER TABLE `restaurant_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_order`
--

DROP TABLE IF EXISTS `restaurant_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant_order` (
  `restaurant_order_id` int(6) NOT NULL AUTO_INCREMENT,
  `restaurant_customer_nic` varchar(10) DEFAULT NULL,
  `order_menu_id` int(6) DEFAULT NULL,
  `order_des` varchar(500) DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  `order_status` smallint(1) DEFAULT '1',
  PRIMARY KEY (`restaurant_order_id`),
  KEY `fk7_restaurant_order_menu_id_idx` (`order_menu_id`),
  KEY `fk2_restaurant_order_customer_nic` (`restaurant_customer_nic`),
  CONSTRAINT `fk2_restaurant_order_customer_nic` FOREIGN KEY (`restaurant_customer_nic`) REFERENCES `restaurant_customer` (`restaurant_customer_nic`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk3_restaurant_order_menu_id` FOREIGN KEY (`order_menu_id`) REFERENCES `menu` (`menu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=510012 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_order`
--

LOCK TABLES `restaurant_order` WRITE;
/*!40000 ALTER TABLE `restaurant_order` DISABLE KEYS */;
INSERT INTO `restaurant_order` VALUES (510000,'672466427V',550000,'Mixed Fried Rice (Basmathi)\n-Half-4\nPasta with Cheese Sauce\n-Full-2','2016-09-02',1),(510001,'895627887V',550001,'White Rice\n-Half-2\nPrawns Curry\n-Full-1\nMaldives Fish Sambol\n-Full-1\nBrinjal Tempered\n-Full-1','2016-09-11',1),(510002,'894253177V',550000,'Mixed Fried Rice (Basmathi)\n-Half-4\nPasta with Cheese Sauce\n-Full-1\nPork Black Curry\n-Half-1\nMix Vegetable Curry\n-Half-1\nPapadam\n-Half-2\nIce Cream (Vanilla)\n-Half-1','2016-09-11',1),(510003,'896723526V',550002,'White Rice\n-Half-1\nCashew Greenpeace Curry\n-Full-1\nFish Ambulthiyal\n-Full-1\nIce Cream (Chocolate)\n-Full-1','2016-09-12',1),(510004,'926531266V',550001,'String Hoppers\n-Half-7\nPotatoes White Curry\n-Full-2','2016-09-12',1),(510005,'933954547V',550003,'Fried Rice (Keeri Samba)\n-Half-1\nMushrooms with Garlic Sauce\n-Full-2\nDhal Curry\n-Full-2\nDevilled Prawns\n-Full-2','2016-09-12',1),(510006,'958351518V',550003,'Egg Salad\n-Half-4\nFish Cutlets\n-Full-2\nRussian Salad\n-Full-2\nIce Cream (Vanilla)\n-Full-2\nJelly\n-Full-1','2016-09-14',1),(510007,'958353518V',550002,'White Rice\n-Full-2\nFish Ambulthiyal\n-Full-2\nMalay Pickle\n-Full-2','2016-09-14',0),(510010,'918944547V',550000,'Mixed Fried Rice (Basmathi)\n-Full-6-\n','2016-08-20',1),(510011,'678944547V',550000,'Pasta with Cheese Sauce\n-Full-2-\n','2016-08-20',1);
/*!40000 ALTER TABLE `restaurant_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_payment`
--

DROP TABLE IF EXISTS `restaurant_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant_payment` (
  `restaurant_payment_id` int(6) NOT NULL AUTO_INCREMENT,
  `restaurant_payment_cashier_id` int(6) NOT NULL,
  `restaurant_payment_customer_nic` varchar(10) NOT NULL,
  `restaurant_payment_order_id` int(6) NOT NULL,
  `restaurant_payment_amount` float(7,2) DEFAULT NULL,
  `restaurant_payment_credit_or_cash` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`restaurant_payment_id`),
  KEY `fk6_payment_cashier_id_idx` (`restaurant_payment_cashier_id`),
  KEY `fk9_payment_order_id_idx` (`restaurant_payment_order_id`),
  KEY `fk5_payment_customer_nic` (`restaurant_payment_customer_nic`),
  CONSTRAINT `fk4_payment_cashier_id` FOREIGN KEY (`restaurant_payment_cashier_id`) REFERENCES `restaurant_cashier` (`cashier_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk5_payment_customer_nic` FOREIGN KEY (`restaurant_payment_customer_nic`) REFERENCES `restaurant_customer` (`restaurant_customer_nic`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk6_payment_order_id` FOREIGN KEY (`restaurant_payment_order_id`) REFERENCES `restaurant_order` (`restaurant_order_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=530012 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_payment`
--

LOCK TABLES `restaurant_payment` WRITE;
/*!40000 ALTER TABLE `restaurant_payment` DISABLE KEYS */;
INSERT INTO `restaurant_payment` VALUES (530000,210025,'672466427V',510000,2100.00,'credit'),(530001,210027,'895627887V',510001,3600.00,'cash'),(530002,210025,'894253177V',510002,2400.00,'credit'),(530003,210027,'896723526V',510003,4500.00,'cash'),(530004,210026,'926531266V',510004,1600.00,'credit'),(530005,210029,'933954547V',510005,2490.00,'credit'),(530006,210030,'958351518V',510006,3960.00,'cash'),(530007,210029,'958353518V',510007,2360.00,'credit'),(530011,210029,'678944547V',510011,440.00,'Cash');
/*!40000 ALTER TABLE `restaurant_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_customer`
--

DROP TABLE IF EXISTS `room_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_customer` (
  `room_customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `room_customer_name` varchar(45) DEFAULT NULL,
  `room_customer_nic` varchar(10) NOT NULL,
  `room_customer_address` varchar(45) DEFAULT NULL,
  `room_customer_telno` int(10) DEFAULT NULL,
  PRIMARY KEY (`room_customer_id`),
  UNIQUE KEY `room_customer_id_UNIQUE` (`room_customer_id`),
  UNIQUE KEY `room_customer_nic_UNIQUE` (`room_customer_nic`)
) ENGINE=InnoDB AUTO_INCREMENT=710004 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_customer`
--

LOCK TABLES `room_customer` WRITE;
/*!40000 ALTER TABLE `room_customer` DISABLE KEYS */;
INSERT INTO `room_customer` VALUES (710001,'Ashani Dickowita','948602920V','Malabe',112561282),(710002,'Buddhini Dias','928602920V','Nugegoda',112511285),(710003,'Uthpala Liyanage','930976857V','Kottawa',987545654);
/*!40000 ALTER TABLE `room_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_package`
--

DROP TABLE IF EXISTS `room_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_package` (
  `room_package_packageid` int(11) NOT NULL AUTO_INCREMENT,
  `room_package_description` varchar(45) DEFAULT NULL,
  `room_package_status` varchar(20) DEFAULT 'Available',
  `room_package_amount` double NOT NULL,
  `room_package_NoofAdults` int(11) DEFAULT NULL,
  `room_package_NoofChildren` int(11) DEFAULT NULL,
  `room_package_roomno` int(11) DEFAULT NULL,
  PRIMARY KEY (`room_package_packageid`),
  UNIQUE KEY `room_package_packageid_UNIQUE` (`room_package_packageid`)
) ENGINE=InnoDB AUTO_INCREMENT=720002 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_package`
--

LOCK TABLES `room_package` WRITE;
/*!40000 ALTER TABLE `room_package` DISABLE KEYS */;
INSERT INTO `room_package` VALUES (720001,'2 double beds','Available',2000,2,3,16);
/*!40000 ALTER TABLE `room_package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_payments`
--

DROP TABLE IF EXISTS `room_payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_payments` (
  `room_paymentspayment_Id` int(11) NOT NULL AUTO_INCREMENT,
  `room_payments_cusnic` varchar(10) NOT NULL,
  `room_payments_method` varchar(45) DEFAULT NULL,
  `room_payment_amount` float DEFAULT NULL,
  `room_payments_debitorcredit` varchar(10) DEFAULT NULL,
  `room_payments_date` date DEFAULT NULL,
  `room_payments_desc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`room_paymentspayment_Id`,`room_payments_cusnic`)
) ENGINE=InnoDB AUTO_INCREMENT=760002 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_payments`
--

LOCK TABLES `room_payments` WRITE;
/*!40000 ALTER TABLE `room_payments` DISABLE KEYS */;
INSERT INTO `room_payments` VALUES (760001,'948602920V','Money',8000,'Credit','2016-09-20','');
/*!40000 ALTER TABLE `room_payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_reservedroom`
--

DROP TABLE IF EXISTS `room_reservedroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_reservedroom` (
  `room_reservedRoom_ResId` int(11) NOT NULL,
  `room_reservedRoom_RoomNo` int(11) NOT NULL,
  `room_reservedRoom_cusnic` varchar(10) NOT NULL,
  `room_reservedroom_checkIn` date DEFAULT NULL,
  `room_reservedroom_checkOut` date DEFAULT NULL,
  PRIMARY KEY (`room_reservedRoom_ResId`,`room_reservedRoom_RoomNo`,`room_reservedRoom_cusnic`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_reservedroom`
--

LOCK TABLES `room_reservedroom` WRITE;
/*!40000 ALTER TABLE `room_reservedroom` DISABLE KEYS */;
INSERT INTO `room_reservedroom` VALUES (730000,1,'948602920V','2016-09-20','2016-09-20');
/*!40000 ALTER TABLE `room_reservedroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_roomdetails`
--

DROP TABLE IF EXISTS `room_roomdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_roomdetails` (
  `room_roomdetails_roomno` int(11) NOT NULL AUTO_INCREMENT,
  `room_roomdetails_status` varchar(20) DEFAULT 'Available',
  `room_roomdetails_amount` double NOT NULL,
  `room_roomdetails_NoofAdults` int(11) DEFAULT NULL,
  `room_roomdetails_NoofChildren` int(11) DEFAULT NULL,
  `room_roomdetails_roomType` varchar(20) DEFAULT NULL,
  `room_roomdetails_headcount` int(11) DEFAULT NULL,
  `room_roomdetails_Description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`room_roomdetails_roomno`),
  UNIQUE KEY `room_roomdetails_roomno_UNIQUE` (`room_roomdetails_roomno`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_roomdetails`
--

LOCK TABLES `room_roomdetails` WRITE;
/*!40000 ALTER TABLE `room_roomdetails` DISABLE KEYS */;
INSERT INTO `room_roomdetails` VALUES (1,'Available',4000,1,0,'Single bed A/C',1,'Free wifi,1 bed'),(2,'Available',3000,1,0,'Single bed Non A/C',1,'Free wifi,1 bed'),(3,'Available',8000,2,0,'Double bed A/C',2,'Free wifi,1 Double bed'),(4,'Available',6000,2,0,'Double bed Non A/C',2,'Free wifi,1 Double bed'),(5,'Available',10000,2,1,'Double bed A/C',3,'Free wifi,1 Double bed,1 Single bed'),(6,'Available',9000,2,1,'Double bed Non A/C',3,'Free wifi,1 Double bed,1 Single bed'),(7,'Available',12000,2,2,'Double bed A/C',4,'Free wifi,2 Double beds'),(8,'Available',15000,2,3,'Double bed A/C',5,'Free wifi,2 Double beds,1 Single bed'),(9,'Available',20000,4,4,'Double bed A/C',8,'Free wifi,4  Double beds'),(10,'Available',12000,4,0,'Double bed A/C',4,'Free wifi,2 Double beds'),(11,'Available',11000,2,2,'Double bed Non A/C',4,'Free wifi,2 Double beds'),(12,'Available',14000,2,3,'Double bed Non A/C',5,'Free wifi,2 Double beds,1 Single bed'),(13,'Available',19000,4,4,'Double bed Non A/C',8,'Free wifi,4 Double beds'),(14,'Available',11000,4,0,'Double bed Non A/C',4,'Free wifi,2 Double beds'),(15,'Available',14000,3,2,'Double bed Non A/C',5,'Free wifi,2 Double beds,1 Single bed');
/*!40000 ALTER TABLE `room_roomdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_roomlaundary`
--

DROP TABLE IF EXISTS `room_roomlaundary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_roomlaundary` (
  `room_roomlaundary_lid` int(11) NOT NULL AUTO_INCREMENT,
  `room_roomlaundary_cusnic` varchar(10) DEFAULT NULL,
  `room_roomlaundary_cusName` varchar(45) DEFAULT NULL,
  `room_roomlaundary_Desc` varchar(45) DEFAULT NULL,
  `room_roomlaundary_amount` double DEFAULT NULL,
  `room_roomlaundary_date` date DEFAULT NULL,
  `room_roomlaundary_status` varchar(45) DEFAULT 'notdone',
  `room_roomlaundary_Weight` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`room_roomlaundary_lid`)
) ENGINE=InnoDB AUTO_INCREMENT=740002 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_roomlaundary`
--

LOCK TABLES `room_roomlaundary` WRITE;
/*!40000 ALTER TABLE `room_roomlaundary` DISABLE KEYS */;
INSERT INTO `room_roomlaundary` VALUES (740001,'930976857V','Uthpala Liyanage','2 shirts',200,'2016-09-20','notdone',NULL);
/*!40000 ALTER TABLE `room_roomlaundary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_roommaintainance`
--

DROP TABLE IF EXISTS `room_roommaintainance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_roommaintainance` (
  `room_roommaintainance_product` varchar(30) NOT NULL,
  `room_roommaintainance_Quantity` int(2) DEFAULT NULL,
  `room_roommaintainance_Used` int(2) DEFAULT NULL,
  PRIMARY KEY (`room_roommaintainance_product`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_roommaintainance`
--

LOCK TABLES `room_roommaintainance` WRITE;
/*!40000 ALTER TABLE `room_roommaintainance` DISABLE KEYS */;
INSERT INTO `room_roommaintainance` VALUES ('Shampoo',79,21),('Soap',60,40);
/*!40000 ALTER TABLE `room_roommaintainance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_roomreservation`
--

DROP TABLE IF EXISTS `room_roomreservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_roomreservation` (
  `room_roomreservation_resid` int(11) NOT NULL AUTO_INCREMENT,
  `room_roomreservation_cusnic` varchar(10) DEFAULT NULL,
  `room_roomreservation_cusname` varchar(45) DEFAULT NULL,
  `room_roomreservation_date` date DEFAULT NULL,
  `room_roomreservation_amount` double NOT NULL,
  `room_roomreservation_type` varchar(45) DEFAULT NULL,
  `room_roomreservation_checkin` date DEFAULT NULL,
  `room_roomreservation_checkout` date DEFAULT NULL,
  `room_roomreservation_status` varchar(20) DEFAULT 'notdone',
  `room_roomreservation_RoomCount` int(11) DEFAULT NULL,
  `room_roomreservation_meals` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`room_roomreservation_resid`)
) ENGINE=InnoDB AUTO_INCREMENT=730001 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_roomreservation`
--

LOCK TABLES `room_roomreservation` WRITE;
/*!40000 ALTER TABLE `room_roomreservation` DISABLE KEYS */;
INSERT INTO `room_roomreservation` VALUES (730000,'948602920V','Ashani Dickowita','2016-09-20',8000,'Room','2016-09-20','2016-09-20','done',1,'With Meals');
/*!40000 ALTER TABLE `room_roomreservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_roomserviceorders`
--

DROP TABLE IF EXISTS `room_roomserviceorders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_roomserviceorders` (
  `room_roomserviceorders_rsid` int(11) NOT NULL AUTO_INCREMENT,
  `room_roomserviceorders_cusnic` varchar(10) DEFAULT NULL,
  `room_roomserviceorders_roomno` int(11) DEFAULT NULL,
  `room_roomserviceorders_noofplates` int(11) DEFAULT NULL,
  `room_roomserviceorders_regular` varchar(45) DEFAULT NULL,
  `room_roomserviceorders_customized` varchar(45) DEFAULT NULL,
  `room_roomserviceorders_date` date DEFAULT NULL,
  `room_roomserviceorders_price` double DEFAULT NULL,
  `room_roomserviceorders_package` varchar(45) DEFAULT NULL,
  `room_roomserviceorder_status` varchar(20) DEFAULT 'notdone',
  `room_roomserviceorder_Description` varchar(500) DEFAULT NULL,
  `room_Restaurant_Status` smallint(2) DEFAULT '0',
  PRIMARY KEY (`room_roomserviceorders_rsid`)
) ENGINE=InnoDB AUTO_INCREMENT=750001 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_roomserviceorders`
--

LOCK TABLES `room_roomserviceorders` WRITE;
/*!40000 ALTER TABLE `room_roomserviceorders` DISABLE KEYS */;
INSERT INTO `room_roomserviceorders` VALUES (750000,'948602920V',1,2,NULL,'Customized','2016-09-20',380,NULL,'notdone','Deviled Chicken-Half,Mixed Fried Rice (Basmathi)-Half,',0);
/*!40000 ALTER TABLE `room_roomserviceorders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock` (
  `stock_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) DEFAULT NULL,
  `stock_qty` float NOT NULL,
  PRIMARY KEY (`stock_id`),
  KEY `item_id_idx` (`item_id`),
  CONSTRAINT `item_id` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=330036 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (330002,320000,465),(330003,320001,360),(330004,320002,405),(330005,320003,555),(330006,320004,155),(330007,320005,540),(330008,320010,205),(330009,320011,325),(330010,320012,105),(330011,320013,350),(330012,320014,400),(330013,320015,400),(330014,320016,0),(330015,320017,0),(330016,320018,150),(330017,320019,60),(330018,320020,0),(330019,320021,0),(330020,320022,160),(330021,320023,0),(330022,320024,70),(330023,320025,0),(330024,320026,65),(330025,320027,0),(330026,320028,0),(330027,320029,0),(330028,320030,60),(330029,320031,70),(330030,320032,0),(330031,320033,0),(330032,320034,0),(330033,320035,0),(330034,320036,0),(330035,320037,75);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_purchase`
--

DROP TABLE IF EXISTS `stock_purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock_purchase` (
  `stock_purchase_id` int(11) NOT NULL AUTO_INCREMENT,
  `stock_id` int(11) DEFAULT NULL,
  `supplier_id` int(11) NOT NULL,
  `purchase_qty` float NOT NULL,
  `unit_price` double NOT NULL,
  `total_amount` double NOT NULL,
  `stock_purchase_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`stock_purchase_id`),
  KEY `stock_id_idx` (`stock_id`),
  KEY `stock_purchase_supplier_id_idx` (`supplier_id`),
  CONSTRAINT `stock_purchase_stock_id` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`stock_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stock_purchase_supplier_id` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=360013 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_purchase`
--

LOCK TABLES `stock_purchase` WRITE;
/*!40000 ALTER TABLE `stock_purchase` DISABLE KEYS */;
INSERT INTO `stock_purchase` VALUES (360001,330006,350003,100,200,20000,'2016-09-21 06:02:47'),(360002,330029,350002,70,300,21000,'2016-09-21 06:03:52'),(360003,330012,350006,400,105,42000,'2016-09-21 06:05:06'),(360004,330011,350003,350,45,15750,'2016-09-21 06:06:13'),(360005,330013,350003,400,30,12000,'2016-09-21 06:06:58'),(360006,330020,350000,160,80,12800,'2016-09-21 06:11:29'),(360007,330017,350006,60,400,24000,'2016-09-21 06:14:53'),(360008,330024,350005,65,100,6500,'2016-09-21 06:16:08'),(360009,330016,350000,150,400,60000,'2016-09-21 06:18:46'),(360010,330028,350007,60,105,6300,'2016-09-21 06:21:45'),(360011,330022,350006,70,130,9100,'2016-09-21 06:22:37'),(360012,330035,350002,75,300,22500,'2016-09-21 06:24:12');
/*!40000 ALTER TABLE `stock_purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_transfer`
--

DROP TABLE IF EXISTS `stock_transfer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock_transfer` (
  `stock_transfer_id` int(11) NOT NULL AUTO_INCREMENT,
  `stock_id` int(11) DEFAULT NULL,
  `transfer_department` varchar(45) NOT NULL,
  `transfer_qty` float NOT NULL,
  `transfer_status` varchar(45) NOT NULL DEFAULT 'ordered',
  `order_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `transfer_date` datetime DEFAULT NULL,
  PRIMARY KEY (`stock_transfer_id`),
  KEY `stock_id_idx` (`stock_id`),
  CONSTRAINT `stock_transfer_stock_id` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`stock_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=340011 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_transfer`
--

LOCK TABLES `stock_transfer` WRITE;
/*!40000 ALTER TABLE `stock_transfer` DISABLE KEYS */;
INSERT INTO `stock_transfer` VALUES (340001,330003,'Room',270,'ordered','2016-09-21 06:00:15',NULL),(340002,330016,'Transport',120,'ordered','2016-09-21 06:00:15',NULL),(340003,330035,'Event',50,'ordered','2016-09-21 06:00:15',NULL),(340004,330005,'Restaurant',250,'ordered','2016-09-21 06:00:16',NULL),(340005,330009,'Restaurant',100,'transfered','2016-09-21 06:00:16','2016-09-21 06:34:00'),(340006,330002,'Restaurant',800,'ordered','2016-09-21 06:00:16',NULL),(340007,330006,'Restaurant',60,'ordered','2016-09-21 06:25:24',NULL),(340008,330010,'Room',15,'ordered','2016-09-21 06:26:25',NULL),(340009,330008,'Restaurant',20,'ordered','2016-09-21 06:27:33',NULL),(340010,330007,'Restaurant',210,'ordered','2016-09-21 06:28:14',NULL);
/*!40000 ALTER TABLE `stock_transfer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_userlog`
--

DROP TABLE IF EXISTS `stock_userlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock_userlog` (
  `stock_userlog_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `stock_transaction_type` varchar(45) NOT NULL,
  `stock_transaction_id` int(11) DEFAULT NULL,
  `stock_transaction_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`stock_userlog_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `system_user` (`system_user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=370014 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_userlog`
--

LOCK TABLES `stock_userlog` WRITE;
/*!40000 ALTER TABLE `stock_userlog` DISABLE KEYS */;
INSERT INTO `stock_userlog` VALUES (370001,910020,'Stock Purchase',360001,'2016-09-21 06:02:47'),(370002,910020,'Stock Purchase',360002,'2016-09-21 06:03:52'),(370003,910020,'Stock Purchase',360003,'2016-09-21 06:05:06'),(370004,910020,'Stock Purchase',360004,'2016-09-21 06:06:13'),(370005,910020,'Stock Purchase',360005,'2016-09-21 06:06:58'),(370006,910020,'Stock Purchase',360006,'2016-09-21 06:11:30'),(370007,910020,'Stock Purchase',360007,'2016-09-21 06:14:53'),(370008,910020,'Stock Purchase',360008,'2016-09-21 06:16:08'),(370009,910020,'Stock Purchase',360009,'2016-09-21 06:18:46'),(370010,910020,'Stock Purchase',360010,'2016-09-21 06:21:45'),(370011,910020,'Stock Purchase',360011,'2016-09-21 06:22:38'),(370012,910020,'Stock Purchase',360012,'2016-09-21 06:24:13'),(370013,910020,'Stock Transfer',340005,'2016-09-21 06:34:39');
/*!40000 ALTER TABLE `stock_userlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `supplier_id` int(11) NOT NULL AUTO_INCREMENT,
  `supplier_name` varchar(45) NOT NULL,
  `supplier_address` varchar(60) DEFAULT NULL,
  `supplier_contact` int(11) DEFAULT NULL,
  `supplier_email` varchar(45) DEFAULT NULL,
  `supplier_description` varchar(60) DEFAULT NULL,
  `supplier_reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `supplier_availability` varchar(45) NOT NULL DEFAULT 'available',
  PRIMARY KEY (`supplier_id`),
  UNIQUE KEY `supplier_name_UNIQUE` (`supplier_name`)
) ENGINE=InnoDB AUTO_INCREMENT=350009 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (350000,'Nimal Diaz','Malabe',771213422,'nimaldiaz@yahoo.com','Fast delivery','2016-08-23 10:14:54','available'),(350002,'Damith Perera','Kandy',771213644,'damithperera43@gmail.com','Reliable products','2016-08-23 10:32:01','available'),(350003,'Saman Fernando','Kalutara',782312342,'samanfernando@yahoo.com','Cheap prices','2016-08-23 11:56:43','available'),(350004,'Sunil Perera','Malabe',721213521,'sunilperera@gmail.lk','Fast delivery','2016-08-23 22:08:16','removed'),(350005,'Sumal Perera','Homagama',771234652,'sumalperera@gmail.com','Highest Offer','2016-08-23 22:27:23','available'),(350006,'Sapumal Perera','Pannipitiya',771213411,'sapumalperera@gmail.com','Fast delivery','2016-08-24 10:44:43','available'),(350007,'Amal Perera','Kandy',772123948,'amalperera@gmail.com','Reliable goods','2016-09-17 02:02:27','available'),(350008,'Nishantha Pieris','Kottawa',773234598,'nishanthap34@gmail.com','Reliable products','2016-09-21 06:30:51','removed');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_user`
--

DROP TABLE IF EXISTS `system_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_user` (
  `system_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(60) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `access_level` varchar(45) NOT NULL,
  `department` varchar(45) NOT NULL,
  `last_updated_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `employee_id` int(11) DEFAULT NULL,
  `user_availability` varchar(45) NOT NULL DEFAULT 'available',
  PRIMARY KEY (`system_user_id`),
  UNIQUE KEY `user_name_UNIQUE` (`username`),
  KEY `employee_id_idx` (`employee_id`),
  CONSTRAINT `employee_id` FOREIGN KEY (`employee_id`) REFERENCES `hr_employee` (`employee_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=910048 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_user`
--

LOCK TABLES `system_user` WRITE;
/*!40000 ALTER TABLE `system_user` DISABLE KEYS */;
INSERT INTO `system_user` VALUES (910019,'Nisal Hewagamage','Nisal','321','System Admin','All','2016-09-20 12:18:11',210007,'available'),(910020,'Sapumal Kalutota','Sapumal','321','Admin','Stock','2016-09-16 14:00:23',210008,'available'),(910021,'Nipun Kalutota','Nipun','321','User','Stock','2016-09-16 14:01:54',210009,'available'),(910022,'Nirodha Kalutota','Nirodha','321','User','Stock','2016-09-16 14:04:35',210010,'available'),(910023,'Sameera Madushan','Sameera','321','Admin','Finance','2016-09-16 23:27:11',210013,'available'),(910024,'Supun Asanka','Supun','321','Accountant','Finance','2016-09-16 23:27:11',210014,'available'),(910025,'Samith Dilantha','Samith','321','Assistant','Finance','2016-09-16 23:27:11',210015,'removed'),(910026,'Damith Perera','Damith','321','Assistant','Finance','2016-09-17 22:01:06',210016,'available'),(910030,'Sandun Bandara','Sandun','321','Accountant','Finance','2016-09-17 22:19:20',210017,'available'),(910031,'Chethana Arunodh','Chethana','321','Admin','Transport','2016-09-19 17:43:35',210020,'available'),(910044,'Ashani Dikowita','Ashani','321','Admin','Room','2016-09-19 18:04:43',210021,'available'),(910045,'Buddhini Dias','Buddhini','321','Admin','Event','2016-09-19 18:04:43',210022,'available'),(910046,'Uthpala Liyanage','Uthpala','321','Admin','Restaurant','2016-09-19 18:04:43',210023,'available'),(910047,'Dinuka Perera','Dinuka','321','Admin','HR','2016-09-19 18:04:43',210024,'available');
/*!40000 ALTER TABLE `system_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trans_book`
--

DROP TABLE IF EXISTS `trans_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trans_book` (
  `idbook` int(11) NOT NULL AUTO_INCREMENT,
  `pac_name` varchar(45) DEFAULT NULL,
  `No_of_pass` int(3) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL,
  `cusid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idbook`)
) ENGINE=InnoDB AUTO_INCREMENT=700003 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trans_book`
--

LOCK TABLES `trans_book` WRITE;
/*!40000 ALTER TABLE `trans_book` DISABLE KEYS */;
INSERT INTO `trans_book` VALUES (700000,'Kalutara Adventure ',5,'none','Wed Sep 21 21:06:54 IST 2016',24),(700001,'package 1',15,'none','Thu Sep 22 00:00:00 IST 2016',720000),(700002,' Package 2',12,' Not Sure ','Wed Sep 28 21:38:53 IST 2016',720001);
/*!40000 ALTER TABLE `trans_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trans_customer`
--

DROP TABLE IF EXISTS `trans_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trans_customer` (
  `idcustomer` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `nic` varchar(45) NOT NULL,
  `telephone` varchar(13) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`idcustomer`)
) ENGINE=InnoDB AUTO_INCREMENT=720007 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trans_customer`
--

LOCK TABLES `trans_customer` WRITE;
/*!40000 ALTER TABLE `trans_customer` DISABLE KEYS */;
INSERT INTO `trans_customer` VALUES (720000,'Chethana','col07, Srilanka ','952341561V','0123456789','chethana@gmail.com'),(720001,'Arunodh','Moratuwa','952341531V','0716416351','Aruno@gmail.com'),(720002,' Ashan',' Makandana,Piliyandala','922341561X','0776416351','ashan@gmail.com'),(720003,'Uthpala','col07, Srilanka ','952341561V','0770464500','uthpala@gmail.com'),(720004,'Chamal','col07, Srilanka ','952341561V','0770646411','chamal@gmail.com'),(720005,'Dinuka','Nugegoda','992341561V','0123456789','dinuka@gmail.com'),(720006,'Janith','col07, Srilanka ','998441561V','0776416351','janioth@hotmail.com');
/*!40000 ALTER TABLE `trans_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trans_customize`
--

DROP TABLE IF EXISTS `trans_customize`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trans_customize` (
  `idcustom` int(11) NOT NULL AUTO_INCREMENT,
  `customized_description` varchar(45) NOT NULL,
  `fee_custom` int(11) NOT NULL,
  `place1` varchar(45) NOT NULL,
  `place2` varchar(45) NOT NULL,
  `place3` varchar(45) NOT NULL,
  `days_custom` varchar(45) NOT NULL,
  `date_custom` varchar(45) NOT NULL,
  `vehicleno_custom` varchar(45) NOT NULL,
  `driver_custom` varchar(45) NOT NULL,
  `guide_custom` varchar(45) NOT NULL,
  PRIMARY KEY (`idcustom`)
) ENGINE=InnoDB AUTO_INCREMENT=730003 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trans_customize`
--

LOCK TABLES `trans_customize` WRITE;
/*!40000 ALTER TABLE `trans_customize` DISABLE KEYS */;
INSERT INTO `trans_customize` VALUES (730000,'BBQ',20000,'Anuradhapura, Mathara ','8','26 ','8','Thu Sep 29 21:07:13 IST 2016','own','003','none'),(730001,'BBQ',20000,'Anuradhapura, Mathara ','8','720002','8','Wed Sep 28 21:39:25 IST 2016','own','003','none'),(730002,'none',10000,', Mathara ','4','720004','5','Fri Sep 30 21:40:06 IST 2016','GE4671','own','none');
/*!40000 ALTER TABLE `trans_customize` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trans_driver`
--

DROP TABLE IF EXISTS `trans_driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trans_driver` (
  `iddriver` int(11) NOT NULL AUTO_INCREMENT,
  `driver_nic` varchar(10) NOT NULL,
  `driver_address` varchar(45) NOT NULL,
  `driver_experience` varchar(45) NOT NULL,
  `driver_name` varchar(45) NOT NULL,
  `driver_license` varchar(45) NOT NULL,
  `allocate_vehicle_no` varchar(7) NOT NULL,
  PRIMARY KEY (`iddriver`)
) ENGINE=InnoDB AUTO_INCREMENT=740001 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trans_driver`
--

LOCK TABLES `trans_driver` WRITE;
/*!40000 ALTER TABLE `trans_driver` DISABLE KEYS */;
INSERT INTO `trans_driver` VALUES (740000,'012345678V','45,Piliyandala ','0123456789','Malinga Anuradha ','012345as ','BBJ6237');
/*!40000 ALTER TABLE `trans_driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trans_expenditures`
--

DROP TABLE IF EXISTS `trans_expenditures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trans_expenditures` (
  `idexpenditures` int(11) NOT NULL AUTO_INCREMENT,
  `payid_date` date DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `department` varchar(45) DEFAULT NULL,
  `payment_method` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idexpenditures`)
) ENGINE=InnoDB AUTO_INCREMENT=760000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trans_expenditures`
--

LOCK TABLES `trans_expenditures` WRITE;
/*!40000 ALTER TABLE `trans_expenditures` DISABLE KEYS */;
/*!40000 ALTER TABLE `trans_expenditures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trans_guide`
--

DROP TABLE IF EXISTS `trans_guide`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trans_guide` (
  `idguide` int(11) NOT NULL AUTO_INCREMENT,
  `guide_name` varchar(45) NOT NULL,
  `guide_address` varchar(45) NOT NULL,
  `guide_nic` varchar(10) NOT NULL,
  `guide_experience` varchar(45) DEFAULT NULL,
  `guide_tel` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idguide`),
  UNIQUE KEY `guide_nic_UNIQUE` (`guide_nic`)
) ENGINE=InnoDB AUTO_INCREMENT=790000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trans_guide`
--

LOCK TABLES `trans_guide` WRITE;
/*!40000 ALTER TABLE `trans_guide` DISABLE KEYS */;
/*!40000 ALTER TABLE `trans_guide` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trans_item`
--

DROP TABLE IF EXISTS `trans_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trans_item` (
  `iditem` int(11) NOT NULL,
  `item_description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`iditem`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trans_item`
--

LOCK TABLES `trans_item` WRITE;
/*!40000 ALTER TABLE `trans_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `trans_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trans_offers`
--

DROP TABLE IF EXISTS `trans_offers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trans_offers` (
  `idoffers` int(11) NOT NULL AUTO_INCREMENT,
  `name_of` varchar(45) DEFAULT NULL,
  `date_of` varchar(45) DEFAULT NULL,
  `duration_of` varchar(45) DEFAULT NULL,
  `discount_of` varchar(45) DEFAULT NULL,
  `discrip_of` varchar(45) DEFAULT NULL,
  `id_package` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idoffers`)
) ENGINE=InnoDB AUTO_INCREMENT=770003 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trans_offers`
--

LOCK TABLES `trans_offers` WRITE;
/*!40000 ALTER TABLE `trans_offers` DISABLE KEYS */;
INSERT INTO `trans_offers` VALUES (770000,'Maxa offer','2016/9/18','1 month','20%','least two packages ','20'),(770001,'Maxa offer','2016/9/18','1 month','20%','Book 3 packagers','720005'),(770002,'Maxa offer','2016/9/18','1 month','20%','least two packages ','720004');
/*!40000 ALTER TABLE `trans_offers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trans_package`
--

DROP TABLE IF EXISTS `trans_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trans_package` (
  `idpackage` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `destination` varchar(45) NOT NULL,
  `activity` varchar(45) NOT NULL,
  `fee` int(11) NOT NULL,
  `days` int(3) NOT NULL,
  PRIMARY KEY (`idpackage`)
) ENGINE=InnoDB AUTO_INCREMENT=780005 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trans_package`
--

LOCK TABLES `trans_package` WRITE;
/*!40000 ALTER TABLE `trans_package` DISABLE KEYS */;
INSERT INTO `trans_package` VALUES (780000,'kalutara Adventure ','Kalutara','Camping',5000,3),(780001,'Jaffna fun','jaffna ','none',25000,5),(780002,' Package 1',' Anuradapura',' none',9000,3),(780003,' Package 2',' Hikkaduwa',' Surffing',52000,5),(780004,' Package 3',' Badulla',' none',14000,5);
/*!40000 ALTER TABLE `trans_package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trans_payment`
--

DROP TABLE IF EXISTS `trans_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trans_payment` (
  `idpayment` int(11) NOT NULL AUTO_INCREMENT,
  `paymentdate` varchar(45) NOT NULL,
  `cusidp` int(11) NOT NULL,
  `paymentmethod` varchar(45) NOT NULL,
  `total` int(11) NOT NULL,
  `balance` int(11) NOT NULL,
  `paid` int(11) NOT NULL,
  PRIMARY KEY (`idpayment`)
) ENGINE=InnoDB AUTO_INCREMENT=710001 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trans_payment`
--

LOCK TABLES `trans_payment` WRITE;
/*!40000 ALTER TABLE `trans_payment` DISABLE KEYS */;
INSERT INTO `trans_payment` VALUES (710000,'Thu Sep 22 21:19:45 IST 2016',25,'Cash',5000,4000,1000);
/*!40000 ALTER TABLE `trans_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trans_vehicle`
--

DROP TABLE IF EXISTS `trans_vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trans_vehicle` (
  `idvehicle` int(10) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  `licence` varchar(45) NOT NULL,
  `driverid` int(10) NOT NULL,
  `driveri` int(10) DEFAULT NULL,
  PRIMARY KEY (`idvehicle`)
) ENGINE=InnoDB AUTO_INCREMENT=750000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trans_vehicle`
--

LOCK TABLES `trans_vehicle` WRITE;
/*!40000 ALTER TABLE `trans_vehicle` DISABLE KEYS */;
/*!40000 ALTER TABLE `trans_vehicle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-21  6:39:13
