CREATE DATABASE  IF NOT EXISTS `hf` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `hf`;
-- MySQL dump 10.13  Distrib 5.6.23, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: hf
-- ------------------------------------------------------
-- Server version	5.6.23

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
-- Table structure for table `ID_GEN`
--

DROP TABLE IF EXISTS `ID_GEN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ID_GEN` (
  `GEN_NAME` varchar(255) DEFAULT NULL,
  `GEN_VAL` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ID_GEN`
--

LOCK TABLES `ID_GEN` WRITE;
/*!40000 ALTER TABLE `ID_GEN` DISABLE KEYS */;
INSERT INTO `ID_GEN` VALUES ('Addr_Gen',1);
/*!40000 ALTER TABLE `ID_GEN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banner`
--

DROP TABLE IF EXISTS `banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banner` (
  `banner_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_desc` longtext,
  `product_id` int(11) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`banner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banner`
--

LOCK TABLES `banner` WRITE;
/*!40000 ALTER TABLE `banner` DISABLE KEYS */;
INSERT INTO `banner` VALUES (1,'Black grapes have been associated with health and nutrition for centuries and they even appear in ancient literature and works of art. The very word ‘grapes’ conjures up images of bunches of plump black grapes, the jeweled wine goblets of the ancient Romans, and the splendors of a bygone era. ',1,'Black Grapes'),(2,'Yellow banana plant is the largest herbaceous flowering plant.[7] All the above-ground parts of a banana plant grow from a structure usually called a corm.[8] Plants are normally tall and fairly sturdy, and are often mistaken for trees, but what appears to be a trunk is actually a false stem or pseudostem.',6,'Yellow Banana'),(3,'The lychee has a history and cultivation going back to 2000 BC according to records in China. Cultivation began in the area of southern China, Malaysia, and Vietnam. Wild trees still grow in parts of southern China and on Hainan Island. There are many stories of the fruit\'s use as a delicacy in the Chinese Imperial Court. ',13,'Litchi');
/*!40000 ALTER TABLE `banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_unique_key`
--

DROP TABLE IF EXISTS `hibernate_unique_key`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_unique_key` (
  `next_hi` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_unique_key`
--

LOCK TABLES `hibernate_unique_key` WRITE;
/*!40000 ALTER TABLE `hibernate_unique_key` DISABLE KEYS */;
INSERT INTO `hibernate_unique_key` VALUES (1);
/*!40000 ALTER TABLE `hibernate_unique_key` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_products`
--

DROP TABLE IF EXISTS `orders_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders_products` (
  `orderList_order_id` int(11) NOT NULL,
  `productList_product_id` int(11) NOT NULL,
  KEY `FK_rg8vmib2tllqgbjba2w4tbigq` (`productList_product_id`),
  CONSTRAINT `FK_rg8vmib2tllqgbjba2w4tbigq` FOREIGN KEY (`productList_product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_products`
--

LOCK TABLES `orders_products` WRITE;
/*!40000 ALTER TABLE `orders_products` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pincode`
--

DROP TABLE IF EXISTS `pincode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pincode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `district` varchar(30) DEFAULT NULL,
  `location` varchar(30) DEFAULT NULL,
  `pincode` varchar(15) DEFAULT NULL,
  `state` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=502 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pincode`
--

LOCK TABLES `pincode` WRITE;
/*!40000 ALTER TABLE `pincode` DISABLE KEYS */;
INSERT INTO `pincode` VALUES (1,'Ahmedabad','Abasana','382120','Gujarat'),(2,'Ahmedabad','Adroda','382220','Gujarat'),(3,'Ahmedabad','Adval','382460','Gujarat'),(4,'Ahmedabad','Ahmedabad.','380001','Gujarat'),(5,'Ahmedabad','Akru','382250','Gujarat'),(6,'Ahmedabad','Alampur','363610','Gujarat'),(7,'Ahmedabad','Alau','382255','Gujarat'),(8,'Ahmedabad','Ambaliara','387810','Gujarat'),(9,'Ahmedabad','Ambareli','387810','Gujarat'),(10,'Ahmedabad','Ambawadi','380006','Gujarat'),(11,'Ahmedabad','Ambawadi Vistar','380015','Gujarat'),(12,'Ahmedabad','Ambli','382463','Gujarat'),(13,'Ahmedabad','Amraiwadi','380026','Gujarat'),(14,'Ahmedabad','Anandnagar','380007','Gujarat'),(15,'Ahmedabad','Andej','382115','Gujarat'),(16,'Ahmedabad','Aniali ( k )','363610','Gujarat'),(17,'Ahmedabad','Aniali Bhimji','382250','Gujarat'),(18,'Ahmedabad','Arnej','382230','Gujarat'),(19,'Ahmedabad','Asalgam','382150','Gujarat'),(20,'Ahmedabad','Asarwa Chakla','380016','Gujarat'),(21,'Ahmedabad','Asarwa Ext south','380024','Gujarat'),(22,'Ahmedabad','Ashoknagar','382140','Gujarat'),(23,'Ahmedabad','Ashram Road','380009','Gujarat'),(24,'Ahmedabad','Aslali','382427','Gujarat'),(25,'Ahmedabad','Azad Society','380015','Gujarat'),(26,'Ahmedabad','Badanpur','382465','Gujarat'),(27,'Ahmedabad','Badarkha','382276','Gujarat'),(28,'Ahmedabad','Bagad','382255','Gujarat'),(29,'Ahmedabad','Bagodara','382230','Gujarat'),(30,'Ahmedabad','Bajarda','382460','Gujarat'),(31,'Ahmedabad','Bakrana','382170','Gujarat'),(32,'Ahmedabad','Bakrol','382430','Gujarat'),(33,'Ahmedabad','Baldana','382240','Gujarat'),(34,'Ahmedabad','Balsasan','382145','Gujarat'),(35,'Ahmedabad','Bamroli','382145','Gujarat'),(36,'Ahmedabad','Bamsara','382240','Gujarat'),(37,'Ahmedabad','Bapunagar','380024','Gujarat'),(38,'Ahmedabad','Bareja','382425','Gujarat'),(39,'Ahmedabad','Barwala Ghelasha','382450','Gujarat'),(40,'Ahmedabad','Bavaliary','382455','Gujarat'),(41,'Ahmedabad','Bavla','382220','Gujarat'),(42,'Ahmedabad','Bavla Market yard','382220','Gujarat'),(43,'Ahmedabad','Behrampura','380022','Gujarat'),(44,'Ahmedabad','Bela','382450','Gujarat'),(45,'Ahmedabad','Bhadaj','380060','Gujarat'),(46,'Ahmedabad','Bhadana','382140','Gujarat'),(47,'Ahmedabad','Bhadiad','382463','Gujarat'),(48,'Ahmedabad','Bhairavnath Road','380028','Gujarat'),(49,'Ahmedabad','Bhangadh','382455','Gujarat'),(50,'Ahmedabad','Bhankoda','382140','Gujarat'),(51,'Ahmedabad','Bhat','382210','Gujarat'),(52,'Ahmedabad','Bhavda','382433','Gujarat'),(53,'Ahmedabad','Bhayla','382220','Gujarat'),(54,'Ahmedabad','Bhojwa','382150','Gujarat'),(55,'Ahmedabad','Bholad','382230','Gujarat'),(56,'Ahmedabad','Bhoyani','382145','Gujarat'),(57,'Ahmedabad','Bhurkhi','382230','Gujarat'),(58,'Ahmedabad','Bhuvaldi','382430','Gujarat'),(59,'Ahmedabad','Bilasia','382330','Gujarat'),(60,'Ahmedabad','Bodakdev','380054','Gujarat'),(61,'Ahmedabad','Bodia','363610','Gujarat'),(62,'Ahmedabad','Bol','382170','Gujarat'),(63,'Ahmedabad','Bopal','380058','Gujarat'),(64,'Ahmedabad','Bordibazar Viramgam','382150','Gujarat'),(65,'Ahmedabad','Calico Mills','380022','Gujarat'),(66,'Ahmedabad','Cantonment','380004','Gujarat'),(67,'Ahmedabad','Chaloda','382260','Gujarat'),(68,'Ahmedabad','Chandarva','382255','Gujarat'),(69,'Ahmedabad','Chandial','382433','Gujarat'),(70,'Ahmedabad','Chandisar','382260','Gujarat'),(71,'Ahmedabad','Chandlodia','382481','Gujarat'),(72,'Ahmedabad','Chandranagar','382150','Gujarat'),(73,'Ahmedabad','Changodar','382213','Gujarat'),(74,'Ahmedabad','Charanki','382450','Gujarat'),(75,'Ahmedabad','Charel','382110','Gujarat'),(76,'Ahmedabad','Charol','382120','Gujarat'),(77,'Ahmedabad','Chaulaj','382433','Gujarat'),(78,'Ahmedabad','Chekhla','382115','Gujarat'),(79,'Ahmedabad','Chhabasar','382220','Gujarat'),(80,'Ahmedabad','Chhaniar','382140','Gujarat'),(81,'Ahmedabad','Chharodi','382170','Gujarat'),(82,'Ahmedabad','Chhasiana','382460','Gujarat'),(83,'Ahmedabad','Chiada','382220','Gujarat'),(84,'Ahmedabad','Chokdi','382250','Gujarat'),(85,'Ahmedabad','Chuval Dangarva','382145','Gujarat'),(86,'Ahmedabad','Civil Hospital','380016','Gujarat'),(87,'Ahmedabad','Ctm Char rasta','380026','Gujarat'),(88,'Ahmedabad','D Cabin','380019','Gujarat'),(89,'Ahmedabad','Dabhsar','382120','Gujarat'),(90,'Ahmedabad','Dadhana','382120','Gujarat'),(91,'Ahmedabad','Daduka','382110','Gujarat'),(92,'Ahmedabad','Dadusar','387810','Gujarat'),(93,'Ahmedabad','Dalod','382130','Gujarat'),(94,'Ahmedabad','Daran','382220','Gujarat'),(95,'Ahmedabad','Dariapur','380001','Gujarat'),(96,'Ahmedabad','Daslana','382150','Gujarat'),(97,'Ahmedabad','Daxini Society','380008','Gujarat'),(98,'Ahmedabad','Dehgamda','382220','Gujarat'),(99,'Ahmedabad','Dekawada','382120','Gujarat'),(100,'Ahmedabad','Delhi Gate','380004','Gujarat'),(101,'Ahmedabad','Deo Dholera','382240','Gujarat'),(102,'Ahmedabad','Detroj','382120','Gujarat'),(103,'Ahmedabad','Devaliya','363610','Gujarat'),(104,'Ahmedabad','Devpura','382140','Gujarat'),(105,'Ahmedabad','Devtimoti','382213','Gujarat'),(106,'Ahmedabad','Dhakdi','382150','Gujarat'),(107,'Ahmedabad','Dhamatvan','382435','Gujarat'),(108,'Ahmedabad','Dhanala','382465','Gujarat'),(109,'Ahmedabad','Dhandhuka','382460','Gujarat'),(110,'Ahmedabad','Dhanwada','382220','Gujarat'),(111,'Ahmedabad','Dharpipla','363610','Gujarat'),(112,'Ahmedabad','Dhedasana','382130','Gujarat'),(113,'Ahmedabad','Dhingada','382230','Gujarat'),(114,'Ahmedabad','Dholera','382455','Gujarat'),(115,'Ahmedabad','Dholi','382240','Gujarat'),(116,'Ahmedabad','Dholka','387810','Gujarat'),(117,'Ahmedabad','Digvijaynagar','382470','Gujarat'),(118,'Ahmedabad','District Court','380001','Gujarat'),(119,'Ahmedabad','Dodar','382170','Gujarat'),(120,'Ahmedabad','Dudheshwar Tavdipura','380004','Gujarat'),(121,'Ahmedabad','Dumana','382150','Gujarat'),(122,'Ahmedabad','Durgi','382230','Gujarat'),(123,'Ahmedabad','Ellisbridge','380006','Gujarat'),(124,'Ahmedabad','Endla','382130','Gujarat'),(125,'Ahmedabad','Fangadi','382110','Gujarat'),(126,'Ahmedabad','Fatepur','382465','Gujarat'),(127,'Ahmedabad','Fatewadi','382210','Gujarat'),(128,'Ahmedabad','Fedra','382465','Gujarat'),(129,'Ahmedabad','Galsana','363610','Gujarat'),(130,'Ahmedabad','Gamanpura','382120','Gujarat'),(131,'Ahmedabad','Gamdi','382435','Gujarat'),(132,'Ahmedabad','Gamph','382465','Gujarat'),(133,'Ahmedabad','Gandhi Ashram','380027','Gujarat'),(134,'Ahmedabad','Gandhi Road','380001','Gujarat'),(135,'Ahmedabad','Gangad','382240','Gujarat'),(136,'Ahmedabad','Ganol','382265','Gujarat'),(137,'Ahmedabad','Garodia','382115','Gujarat'),(138,'Ahmedabad','Gatrad','382449','Gujarat'),(139,'Ahmedabad','Geratpur','382435','Gujarat'),(140,'Ahmedabad','Ghatisana','382140','Gujarat'),(141,'Ahmedabad','Ghatlodia','380061','Gujarat'),(142,'Ahmedabad','Gheekanta Road','380001','Gujarat'),(143,'Ahmedabad','Ghoda','382150','Gujarat'),(144,'Ahmedabad','Ghodasar','380050','Gujarat'),(145,'Ahmedabad','Ghuma','380058','Gujarat'),(146,'Ahmedabad','Girdharnagar','380004','Gujarat'),(147,'Ahmedabad','Girmatha','382425','Gujarat'),(148,'Ahmedabad','Gita Mandir road','380022','Gujarat'),(149,'Ahmedabad','Godhavata','382255','Gujarat'),(150,'Ahmedabad','Godhavi','382115','Gujarat'),(151,'Ahmedabad','Gogla','382463','Gujarat'),(152,'Ahmedabad','Gomtipur','380021','Gujarat'),(153,'Ahmedabad','Goraiya','382150','Gujarat'),(154,'Ahmedabad','Goraj','382110','Gujarat'),(155,'Ahmedabad','Gorasu','382463','Gujarat'),(156,'Ahmedabad','Gota','382481','Gujarat'),(157,'Ahmedabad','Gujarat High court','380060','Gujarat'),(158,'Ahmedabad','Gujarat University','380009','Gujarat'),(159,'Ahmedabad','Gunda','382255','Gujarat'),(160,'Ahmedabad','Gundi Sarvodaya ashram','382230','Gujarat'),(161,'Ahmedabad','Gunjala','382120','Gujarat'),(162,'Ahmedabad','Gunjar','382460','Gujarat'),(163,'Ahmedabad','Gyaspur','382405','Gujarat'),(164,'Ahmedabad','Hansalpur','382150','Gujarat'),(165,'Ahmedabad','Harniav','382435','Gujarat'),(166,'Ahmedabad','Hathijan','382445','Gujarat'),(167,'Ahmedabad','Hebatpur','382455','Gujarat'),(168,'Ahmedabad','Hirapur','382435','Gujarat'),(169,'Ahmedabad','Hirapura','382150','Gujarat'),(170,'Ahmedabad','I E bapunagar','380024','Gujarat'),(171,'Ahmedabad','I I m','380015','Gujarat'),(172,'Ahmedabad','Iawa (vasna)','382170','Gujarat'),(173,'Ahmedabad','Ingoli','382265','Gujarat'),(174,'Ahmedabad','Isanpur','382443','Gujarat'),(175,'Ahmedabad','Jagatpur','382470','Gujarat'),(176,'Ahmedabad','Jakhada','382230','Gujarat'),(177,'Ahmedabad','Jakwada','382150','Gujarat'),(178,'Ahmedabad','Jalalpur Vajifa','387810','Gujarat'),(179,'Ahmedabad','Jalampur','382150','Gujarat'),(180,'Ahmedabad','Jalila','382255','Gujarat'),(181,'Ahmedabad','Jalisana','382120','Gujarat'),(182,'Ahmedabad','Jaliya','382460','Gujarat'),(183,'Ahmedabad','Jamalpur','380001','Gujarat'),(184,'Ahmedabad','Jamp','382110','Gujarat'),(185,'Ahmedabad','Jantanagar','382449','Gujarat'),(186,'Ahmedabad','Jawahar Chowk','380008','Gujarat'),(187,'Ahmedabad','Jawaraj','382230','Gujarat'),(188,'Ahmedabad','Jeska','382250','Gujarat'),(189,'Ahmedabad','Jetalpur','382426','Gujarat'),(190,'Ahmedabad','Jholapur','382170','Gujarat'),(191,'Ahmedabad','Jivraj Park','380051','Gujarat'),(192,'Ahmedabad','Jodhpur Char rasta','380015','Gujarat'),(193,'Ahmedabad','Joshipura','382150','Gujarat'),(194,'Ahmedabad','Juhapura','380055','Gujarat'),(195,'Ahmedabad','Junapadhar','382150','Gujarat'),(196,'Ahmedabad','Juval','382220','Gujarat'),(197,'Ahmedabad','Juval Rupavati','382220','Gujarat'),(198,'Ahmedabad','Kabir Chowk','380005','Gujarat'),(199,'Ahmedabad','Kadipur','382463','Gujarat'),(200,'Ahmedabad','Kadvasan','382130','Gujarat'),(201,'Ahmedabad','Kalana','382170','Gujarat'),(202,'Ahmedabad','Kali','382470','Gujarat'),(203,'Ahmedabad','Kalupur Chakla','380001','Gujarat'),(204,'Ahmedabad','Kalyangadh','382240','Gujarat'),(205,'Ahmedabad','Kamiala','382465','Gujarat'),(206,'Ahmedabad','Kamijala','382150','Gujarat'),(207,'Ahmedabad','Kanbha','382430','Gujarat'),(208,'Ahmedabad','Kaneti','382110','Gujarat'),(209,'Ahmedabad','Kaniyel','382433','Gujarat'),(210,'Ahmedabad','Kankravadi','382150','Gujarat'),(211,'Ahmedabad','Kanz','382140','Gujarat'),(212,'Ahmedabad','Karakthal','382150','Gujarat'),(213,'Ahmedabad','Kasindra','382210','Gujarat'),(214,'Ahmedabad','Kathwada','382430','Gujarat'),(215,'Ahmedabad','Kathwada Maize product','382430','Gujarat'),(216,'Ahmedabad','Katosan Road','382145','Gujarat'),(217,'Ahmedabad','Kauka','382265','Gujarat'),(218,'Ahmedabad','Kavitha','382260','Gujarat'),(219,'Ahmedabad','Kelia vasna','387810','Gujarat'),(220,'Ahmedabad','Kerala','382220','Gujarat'),(221,'Ahmedabad','Keria','363610','Gujarat'),(222,'Ahmedabad','Kesardi','382240','Gujarat'),(223,'Ahmedabad','Khadia','380001','Gujarat'),(224,'Ahmedabad','Khadol','382460','Gujarat'),(225,'Ahmedabad','Khambhada','382450','Gujarat'),(226,'Ahmedabad','Khamidana','382450','Gujarat'),(227,'Ahmedabad','Khandachora Dhandhuka','382460','Gujarat'),(228,'Ahmedabad','Khanpur','380001','Gujarat'),(229,'Ahmedabad','Kharad','382460','Gujarat'),(230,'Ahmedabad','Kharakuva Dholka','387810','Gujarat'),(231,'Ahmedabad','Khas','382255','Gujarat'),(232,'Ahmedabad','Khasta','382460','Gujarat'),(233,'Ahmedabad','Khoda','382170','Gujarat'),(234,'Ahmedabad','Khodiyar','382421','Gujarat'),(235,'Ahmedabad','Khodiyarnagar','382350','Gujarat'),(236,'Ahmedabad','Khokarnesh','363610','Gujarat'),(237,'Ahmedabad','Khokhara Mehmadabad','380008','Gujarat'),(238,'Ahmedabad','Khorajnanoda','382170','Gujarat'),(239,'Ahmedabad','Khudad','382150','Gujarat'),(240,'Ahmedabad','Khun','382455','Gujarat'),(241,'Ahmedabad','Kocharia','382220','Gujarat'),(242,'Ahmedabad','Kointia','382140','Gujarat'),(243,'Ahmedabad','Kokta','382150','Gujarat'),(244,'Ahmedabad','Kolat','382210','Gujarat'),(245,'Ahmedabad','Koth','382240','Gujarat'),(246,'Ahmedabad','Kothadia','382460','Gujarat'),(247,'Ahmedabad','Krishnanagar','382345','Gujarat'),(248,'Ahmedabad','Kubadthal','382430','Gujarat'),(249,'Ahmedabad','Kubernagar','382340','Gujarat'),(250,'Ahmedabad','Kubernagar B a','382340','Gujarat'),(251,'Ahmedabad','Kuha','382433','Gujarat'),(252,'Ahmedabad','Kujad','382430','Gujarat'),(253,'Ahmedabad','Kukvav','382120','Gujarat'),(254,'Ahmedabad','Kumarkhan','382150','Gujarat'),(255,'Ahmedabad','Kundal','382110','Gujarat'),(256,'Ahmedabad','Kundli','363610','Gujarat'),(257,'Ahmedabad','Kunpur','382130','Gujarat'),(258,'Ahmedabad','Kunvar','382110','Gujarat'),(259,'Ahmedabad','L G hospital','380008','Gujarat'),(260,'Ahmedabad','Lal Darwaja','380001','Gujarat'),(261,'Ahmedabad','Lambha','382405','Gujarat'),(262,'Ahmedabad','Lapkaman','380060','Gujarat'),(263,'Ahmedabad','Limbadia','382330','Gujarat'),(264,'Ahmedabad','Loliya','382465','Gujarat'),(265,'Ahmedabad','Lothal Bhurkhi rs','382230','Gujarat'),(266,'Ahmedabad','M D marg','380022','Gujarat'),(267,'Ahmedabad','Madhupura Market','380004','Gujarat'),(268,'Ahmedabad','Madrisana','382145','Gujarat'),(269,'Ahmedabad','Mahijada','382425','Gujarat'),(270,'Ahmedabad','Makarba','380051','Gujarat'),(271,'Ahmedabad','Makhiav','382110','Gujarat'),(272,'Ahmedabad','Mandal','382130','Gujarat'),(273,'Ahmedabad','Manek Chowk','380001','Gujarat'),(274,'Ahmedabad','Maninagar','380008','Gujarat'),(275,'Ahmedabad','Manipur','382150','Gujarat'),(276,'Ahmedabad','Mankol','382110','Gujarat'),(277,'Ahmedabad','Marusana','382120','Gujarat'),(278,'Ahmedabad','Matoda','382213','Gujarat'),(279,'Ahmedabad','Medra','382330','Gujarat'),(280,'Ahmedabad','Meghaningar','380016','Gujarat'),(281,'Ahmedabad','Melaj','382150','Gujarat'),(282,'Ahmedabad','Memnagar','380052','Gujarat'),(283,'Ahmedabad','Mingalpur','382455','Gujarat'),(284,'Ahmedabad','Miroli','382425','Gujarat'),(285,'Ahmedabad','Mithapur','382230','Gujarat'),(286,'Ahmedabad','Modasar','382220','Gujarat'),(287,'Ahmedabad','Moraiya','382213','Gujarat'),(288,'Ahmedabad','Mota Tradia','382460','Gujarat'),(289,'Ahmedabad','Motera','380005','Gujarat'),(290,'Ahmedabad','Moti kishol','382110','Gujarat'),(291,'Ahmedabad','Moti Boru','382230','Gujarat'),(292,'Ahmedabad','Moti Rantai','382145','Gujarat'),(293,'Ahmedabad','Moti Vavdi','382255','Gujarat'),(294,'Ahmedabad','Muktipur','382425','Gujarat'),(295,'Ahmedabad','Municipal Corporation','380001','Gujarat'),(296,'Ahmedabad','N C market','380002','Gujarat'),(297,'Ahmedabad','N C mills','382345','Gujarat'),(298,'Ahmedabad','Nadishala','382120','Gujarat'),(299,'Ahmedabad','Nana Chiloda','382330','Gujarat'),(300,'Ahmedabad','Nana Ubhada','382130','Gujarat'),(301,'Ahmedabad','Nandej','382435','Gujarat'),(302,'Ahmedabad','Nani Boru','382230','Gujarat'),(303,'Ahmedabad','Nani Devti','382220','Gujarat'),(304,'Ahmedabad','Nani Vavdi','363610','Gujarat'),(305,'Ahmedabad','Nanodara','382220','Gujarat'),(306,'Ahmedabad','Naranpura Vistar','380013','Gujarat'),(307,'Ahmedabad','Narayannagar','380007','Gujarat'),(308,'Ahmedabad','Naroda','382330','Gujarat'),(309,'Ahmedabad','Naroda I e','382330','Gujarat'),(310,'Ahmedabad','Naroda Road','382345','Gujarat'),(311,'Ahmedabad','Naroda S a','382330','Gujarat'),(312,'Ahmedabad','Narol','382405','Gujarat'),(313,'Ahmedabad','Nava Vadaj','380013','Gujarat'),(314,'Ahmedabad','Navagam','382130','Gujarat'),(315,'Ahmedabad','Navapura','382210','Gujarat'),(316,'Ahmedabad','Navda','382450','Gujarat'),(317,'Ahmedabad','Navjivan','380014','Gujarat'),(318,'Ahmedabad','Navrangpura','380009','Gujarat'),(319,'Ahmedabad','Nayakpur','382130','Gujarat'),(320,'Ahmedabad','Nedharad','382115','Gujarat'),(321,'Ahmedabad','Nesda','387810','Gujarat'),(322,'Ahmedabad','Nikol','382350','Gujarat'),(323,'Ahmedabad','Nirnaynagar','382481','Gujarat'),(324,'Ahmedabad','Noblenagar','382340','Gujarat'),(325,'Ahmedabad','O N g c','380005','Gujarat'),(326,'Ahmedabad','Ode','382427','Gujarat'),(327,'Ahmedabad','Odhav','382120','Gujarat'),(328,'Ahmedabad','Odhav Industrial estate','382415','Gujarat'),(329,'Ahmedabad','Ogan','382150','Gujarat'),(330,'Ahmedabad','Ognaj','380060','Gujarat'),(331,'Ahmedabad','Otaria','382463','Gujarat'),(332,'Ahmedabad','Otaria Sarvodaya ashram','382463','Gujarat'),(333,'Ahmedabad','Padana','382460','Gujarat'),(334,'Ahmedabad','Paldi','380007','Gujarat'),(335,'Ahmedabad','Paldi Kankaj','382425','Gujarat'),(336,'Ahmedabad','Panar','382140','Gujarat'),(337,'Ahmedabad','Panchham','382465','Gujarat'),(338,'Ahmedabad','Panchhi','382455','Gujarat'),(339,'Ahmedabad','Parabadi','382250','Gujarat'),(340,'Ahmedabad','Pardhol','382330','Gujarat'),(341,'Ahmedabad','Pasunj','382430','Gujarat'),(342,'Ahmedabad','Pimpan','382110','Gujarat'),(343,'Ahmedabad','Pipli','382465','Gujarat'),(344,'Ahmedabad','Pirana','382425','Gujarat'),(345,'Ahmedabad','Pisawada','382265','Gujarat'),(346,'Ahmedabad','Polarpur','382250','Gujarat'),(347,'Ahmedabad','Polytechnic','380015','Gujarat'),(348,'Ahmedabad','Post Bureau extn counter','380004','Gujarat'),(349,'Ahmedabad','Public Office','380016','Gujarat'),(350,'Ahmedabad','Rahemalpur','382150','Gujarat'),(351,'Ahmedabad','Raikhad','380001','Gujarat'),(352,'Ahmedabad','Railway Colony','380019','Gujarat'),(353,'Ahmedabad','Railwaypura','380002','Gujarat'),(354,'Ahmedabad','Raipur','380001','Gujarat'),(355,'Ahmedabad','Rajoda','382220','Gujarat'),(356,'Ahmedabad','Rajpur Gomtipur','380021','Gujarat'),(357,'Ahmedabad','Rajpura','363610','Gujarat'),(358,'Ahmedabad','Rakhial','380023','Gujarat'),(359,'Ahmedabad','Rakhial Udyog vistar','380023','Gujarat'),(360,'Ahmedabad','Rakhiana','382130','Gujarat'),(361,'Ahmedabad','Ramol','382449','Gujarat'),(362,'Ahmedabad','Rampur','387810','Gujarat'),(363,'Ahmedabad','Rampura','382140','Gujarat'),(364,'Ahmedabad','Ranesar','382220','Gujarat'),(365,'Ahmedabad','Rangpur','382150','Gujarat'),(366,'Ahmedabad','Ranip','382480','Gujarat'),(367,'Ahmedabad','Ranoda','387810','Gujarat'),(368,'Ahmedabad','Ranodra','382433','Gujarat'),(369,'Ahmedabad','Ranpur','363610','Gujarat'),(370,'Ahmedabad','Rasam','382220','Gujarat'),(371,'Ahmedabad','Rayka','382460','Gujarat'),(372,'Ahmedabad','Refda','382255','Gujarat'),(373,'Ahmedabad','Rethal','382110','Gujarat'),(374,'Ahmedabad','Revdibazar','380002','Gujarat'),(375,'Ahmedabad','Ribadi','382130','Gujarat'),(376,'Ahmedabad','Rohika','382230','Gujarat'),(377,'Ahmedabad','Rojid','382450','Gujarat'),(378,'Ahmedabad','Rojka','382460','Gujarat'),(379,'Ahmedabad','Rudatal','382120','Gujarat'),(380,'Ahmedabad','Rupal','382220','Gujarat'),(381,'Ahmedabad','Rupgadh','382240','Gujarat'),(382,'Ahmedabad','S A c','380015','Gujarat'),(383,'Ahmedabad','S A mills','380008','Gujarat'),(384,'Ahmedabad','Sabarmati','380005','Gujarat'),(385,'Ahmedabad','Sachana','382150','Gujarat'),(386,'Ahmedabad','Sadatpura','382120','Gujarat'),(387,'Ahmedabad','Sahij','387810','Gujarat'),(388,'Ahmedabad','Saijpur','382405','Gujarat'),(389,'Ahmedabad','Saijpur Bogha','382345','Gujarat'),(390,'Ahmedabad','Saiwada','387810','Gujarat'),(391,'Ahmedabad','Salajada','382220','Gujarat'),(392,'Ahmedabad','Salangpur - hanuman','382451','Gujarat'),(393,'Ahmedabad','Salasar','363610','Gujarat'),(394,'Ahmedabad','Sanand','382110','Gujarat'),(395,'Ahmedabad','Sanathal','382210','Gujarat'),(396,'Ahmedabad','Sandhida','382455','Gujarat'),(397,'Ahmedabad','Sangasar','382463','Gujarat'),(398,'Ahmedabad','Sangpura','382120','Gujarat'),(399,'Ahmedabad','Sankod','382220','Gujarat'),(400,'Ahmedabad','Sarandi','387810','Gujarat'),(401,'Ahmedabad','Saraspur','380018','Gujarat'),(402,'Ahmedabad','Sardarnagar','382475','Gujarat'),(403,'Ahmedabad','Sargwala','382230','Gujarat'),(404,'Ahmedabad','Sari','382220','Gujarat'),(405,'Ahmedabad','Sarkhej','382210','Gujarat'),(406,'Ahmedabad','Sarkhej Road','380007','Gujarat'),(407,'Ahmedabad','Saroda','382260','Gujarat'),(408,'Ahmedabad','Sarsavadi','382150','Gujarat'),(409,'Ahmedabad','Sathal','387810','Gujarat'),(410,'Ahmedabad','Shah Alam roza','380028','Gujarat'),(411,'Ahmedabad','Shahibag','380004','Gujarat'),(412,'Ahmedabad','Shahpur','382150','Gujarat'),(413,'Ahmedabad','Shahwadi','382405','Gujarat'),(414,'Ahmedabad','Shardanagar','380007','Gujarat'),(415,'Ahmedabad','Shastrinagar','380013','Gujarat'),(416,'Ahmedabad','Shela','380058','Gujarat'),(417,'Ahmedabad','Sher','382130','Gujarat'),(418,'Ahmedabad','Sherpura','387810','Gujarat'),(419,'Ahmedabad','Shilaj','380058','Gujarat'),(420,'Ahmedabad','Shivpura','382140','Gujarat'),(421,'Ahmedabad','Shiyal','382230','Gujarat'),(422,'Ahmedabad','Siawada','382170','Gujarat'),(423,'Ahmedabad','Sihor','382140','Gujarat'),(424,'Ahmedabad','Simej','382265','Gujarat'),(425,'Ahmedabad','Sinaj','382120','Gujarat'),(426,'Ahmedabad','Sindharej','387810','Gujarat'),(427,'Ahmedabad','Sitapur','382130','Gujarat'),(428,'Ahmedabad','Sobhasan','382145','Gujarat'),(429,'Ahmedabad','Sodhi','382463','Gujarat'),(430,'Ahmedabad','Sola','380060','Gujarat'),(431,'Ahmedabad','Sola H b c','380013','Gujarat'),(432,'Ahmedabad','Solgam','382130','Gujarat'),(433,'Ahmedabad','Stadium Marg','380013','Gujarat'),(434,'Ahmedabad','Sub Foreign','380004','Gujarat'),(435,'Ahmedabad','Sukhrampura','380023','Gujarat'),(436,'Ahmedabad','Sunderiana','382255','Gujarat'),(437,'Ahmedabad','Sunvala','382145','Gujarat'),(438,'Ahmedabad','T B nagar','382350','Gujarat'),(439,'Ahmedabad','Tagadi','382250','Gujarat'),(440,'Ahmedabad','Telav','382210','Gujarat'),(441,'Ahmedabad','Telavi','382145','Gujarat'),(442,'Ahmedabad','Thaltej','380059','Gujarat'),(443,'Ahmedabad','Thaltej Road','380054','Gujarat'),(444,'Ahmedabad','Thori Mubarak','382150','Gujarat'),(445,'Ahmedabad','Thori Thumbha','382150','Gujarat'),(446,'Ahmedabad','Thuleta','382150','Gujarat'),(447,'Ahmedabad','Timba','382425','Gujarat'),(448,'Ahmedabad','Tragad','382470','Gujarat'),(449,'Ahmedabad','Transad','387810','Gujarat'),(450,'Ahmedabad','Trent','382150','Gujarat'),(451,'Ahmedabad','Ughroj','382120','Gujarat'),(452,'Ahmedabad','Ukardi','382140','Gujarat'),(453,'Ahmedabad','Ukhlod','382150','Gujarat'),(454,'Ahmedabad','Umrela','363610','Gujarat'),(455,'Ahmedabad','Unchadi','382250','Gujarat'),(456,'Ahmedabad','Undrel','382433','Gujarat'),(457,'Ahmedabad','Upardal','382110','Gujarat'),(458,'Ahmedabad','Utelia','382230','Gujarat'),(459,'Ahmedabad','Vadaj','380013','Gujarat'),(460,'Ahmedabad','Vadgas','382150','Gujarat'),(461,'Ahmedabad','Vadhela','382450','Gujarat'),(462,'Ahmedabad','Vadod','382433','Gujarat'),(463,'Ahmedabad','Vagad','363610','Gujarat'),(464,'Ahmedabad','Valdhera','387810','Gujarat'),(465,'Ahmedabad','Valhia','382450','Gujarat'),(466,'Ahmedabad','Valinda','382463','Gujarat'),(467,'Ahmedabad','Vanch','382449','Gujarat'),(468,'Ahmedabad','Vani','382150','Gujarat'),(469,'Ahmedabad','Vanpardi','382130','Gujarat'),(470,'Ahmedabad','Vansva','382150','Gujarat'),(471,'Ahmedabad','Vanthal','382150','Gujarat'),(472,'Ahmedabad','Varmor','382130','Gujarat'),(473,'Ahmedabad','Varna','382265','Gujarat'),(474,'Ahmedabad','Vasai','382425','Gujarat'),(475,'Ahmedabad','Vasisthnagar','380008','Gujarat'),(476,'Ahmedabad','Vasna','382460','Gujarat'),(477,'Ahmedabad','Vasna (iawa)','382170','Gujarat'),(478,'Ahmedabad','Vasna chacharvadi','382213','Gujarat'),(479,'Ahmedabad','Vastral','382418','Gujarat'),(480,'Ahmedabad','Vataman','382265','Gujarat'),(481,'Ahmedabad','Vatva','382440','Gujarat'),(482,'Ahmedabad','Vatva Industrial estate','382445','Gujarat'),(483,'Ahmedabad','Vautha','387810','Gujarat'),(484,'Ahmedabad','Vehlal','382330','Gujarat'),(485,'Ahmedabad','Vejalka','382230','Gujarat'),(486,'Ahmedabad','Vejalpur','380051','Gujarat'),(487,'Ahmedabad','Vekaria','382110','Gujarat'),(488,'Ahmedabad','Vinchhan','382120','Gujarat'),(489,'Ahmedabad','Vinchhiya','382110','Gujarat'),(490,'Ahmedabad','Vinzol','382445','Gujarat'),(491,'Ahmedabad','Vinzuvada','382130','Gujarat'),(492,'Ahmedabad','Viramgam','382150','Gujarat'),(493,'Ahmedabad','Virdi','382265','Gujarat'),(494,'Ahmedabad','Virochannagar','382170','Gujarat'),(495,'Ahmedabad','Vishalpur','382210','Gujarat'),(496,'Ahmedabad','Vithlpura','382120','Gujarat'),(497,'Ahmedabad','Zanu','382430','Gujarat'),(498,'Ahmedabad','Zanzarka','382460','Gujarat'),(499,'Ahmedabad','Zanzarva','382120','Gujarat'),(500,'Ahmedabad','Zezra','382150','Gujarat'),(501,'Ahmedabad','Zinzar','382250','Gujarat');
/*!40000 ALTER TABLE `pincode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_cotegory`
--

DROP TABLE IF EXISTS `product_cotegory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_cotegory` (
  `product_cotegory_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_cotegory_name` varchar(255) DEFAULT NULL,
  `stock_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`product_cotegory_id`),
  KEY `FK_kttn4j5i80r4c37u5ya0002tr` (`stock_id`),
  CONSTRAINT `FK_kttn4j5i80r4c37u5ya0002tr` FOREIGN KEY (`stock_id`) REFERENCES `stock_cotegory` (`stock_cotegory_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_cotegory`
--

LOCK TABLES `product_cotegory` WRITE;
/*!40000 ALTER TABLE `product_cotegory` DISABLE KEYS */;
INSERT INTO `product_cotegory` VALUES (3,'Apple',1),(4,'Banana',1),(5,'Grapes',1),(6,'Guava',1),(7,'Mango',1),(8,'Pine Apple',1),(9,'Litchi',1),(10,'Kiwi',1),(11,'onion',2),(12,'pea',2),(13,'potato',2),(14,'cucumber',2),(15,'radish',2),(16,'mushroom',2),(17,'sweet corn',2),(18,'tomato',2);
/*!40000 ALTER TABLE `product_cotegory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `is_new` bit(1) DEFAULT NULL,
  `is_premium` bit(1) DEFAULT NULL,
  `is_sale` bit(1) DEFAULT NULL,
  `points_offered` int(11) DEFAULT NULL,
  `product_amount` double NOT NULL,
  `product_desc` longtext NOT NULL,
  `product_name` varchar(100) NOT NULL,
  `reputation` int(11) DEFAULT '0',
  `product_cotegory_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `UK_f55t6sm19p5lrihq24a6knota` (`product_name`),
  KEY `FK_l6nlb3xqnfwp3rjx3ob7kkf2o` (`product_cotegory_id`),
  CONSTRAINT `FK_l6nlb3xqnfwp3rjx3ob7kkf2o` FOREIGN KEY (`product_cotegory_id`) REFERENCES `product_cotegory` (`product_cotegory_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'','','\0',13,123.2,'Black grapes have been associated with health and nutrition for centuries and they even appear in ancient literature and works of art. The very word ‘grapes’ conjures up images of bunches of plump black grapes, the jeweled wine goblets of the ancient Romans, and the splendors of a bygone era. ','Black Grapes',NULL,5),(2,'','','\0',113,1223.2,'Green grapes have been associated with health and nutrition for centuries and they even appear in ancient literature and works of art. The very word ‘grapes’ conjures up images of bunches of plump black grapes, the jeweled wine goblets of the ancient Romans, and the splendors of a bygone era. ','grren Grapes',NULL,5),(3,'\0','\0','',113,123.2,'Green Apple is also very nutritious. As the saying goes – an apple a day, keeps the doctor away and may help you with keeping your Health & Life Insurance premiums low too!  You need to think about your health which apples can help, but also make sure you have good finanacial health also.','Green Apple',NULL,3),(4,'','\0','\0',43,223.2,'Red Apple is also very nutritious. As the saying goes – an apple a day, keeps the doctor away and may help you with keeping your Health & Life Insurance premiums low too!  You need to think about your health which apples can help, but also make sure you have good finanacial health also.','Red Apple',NULL,3),(5,'','\0','\0',43,223.2,'Green banana plant is the largest herbaceous flowering plant.[7] All the above-ground parts of a banana plant grow from a structure usually called a corm.[8] Plants are normally tall and fairly sturdy, and are often mistaken for trees, but what appears to be a trunk is actually a false stem or pseudostem.','Green Banana',NULL,4),(6,'','','\0',33,243.2,'Yellow banana plant is the largest herbaceous flowering plant.[7] All the above-ground parts of a banana plant grow from a structure usually called a corm.[8] Plants are normally tall and fairly sturdy, and are often mistaken for trees, but what appears to be a trunk is actually a false stem or pseudostem.','Yellow Banana',NULL,4),(7,'','','\0',33,543.2,'Red Guava fruits, usually 4 to 12 centimetres (1.6 to 4.7 in) long, are round or oval depending on the species. They have a pronounced and typical fragrance, similar to lemon rind but less sharp. The outer skin may be rough, often with a bitter taste, or soft and sweet. Varying between species, the skin can be any thickness, is usually green before maturity, but becomes yellow, maroon, or green when ripe.','Red Guava',NULL,6),(8,'','','\0',33,543.2,'White Guava fruits, usually 4 to 12 centimetres (1.6 to 4.7 in) long, are round or oval depending on the species. They have a pronounced and typical fragrance, similar to lemon rind but less sharp. The outer skin may be rough, often with a bitter taste, or soft and sweet. Varying between species, the skin can be any thickness, is usually green before maturity, but becomes yellow, maroon, or green when ripe.','White Guava',NULL,6),(9,'\0','\0','',33,543.2,'Red mango is a juicy stone fruit belonging to the genus Mangifera, consisting of numerous tropical fruiting trees, cultivated mostly for edible fruit. The majority of these species are found in nature as wild mangoes. They all belong to the flowering plant family Anacardiaceae. The mango is native to South and Southeast Asia, from where it has been distributed worldwide to become one of the most cultivated fruits in the tropics. The center of diversity of the Mangifera genus is in India.','Red mango',NULL,7),(10,'\0','\0','',33,543.2,'Yellow mango is a juicy stone fruit belonging to the genus Mangifera, consisting of numerous tropical fruiting trees, cultivated mostly for edible fruit. The majority of these species are found in nature as wild mangoes. They all belong to the flowering plant family Anacardiaceae. The mango is native to South and Southeast Asia, from where it has been distributed worldwide to become one of the most cultivated fruits in the tropics. The center of diversity of the Mangifera genus is in India.','White mango',NULL,7),(11,'\0','\0','',33,543.2,'Yellow pineapple (Ananas comosus) is a tropical plant with edible multiple fruit consisting of coalesced berries,[2][3] and the most economically significant plant in the Bromeliaceae family.[4] Pineapples may be cultivated from a crown cutting of the fruit,[2][5] possibly flowering in 20–24 months and fruiting in the following six months.','Yellow pineapple',NULL,8),(12,'\0','\0','',33,543.2,'Juicy pineapple (Ananas comosus) is a tropical plant with edible multiple fruit consisting of coalesced berries,[2][3] and the most economically significant plant in the Bromeliaceae family.[4] Pineapples may be cultivated from a crown cutting of the fruit,[2][5] possibly flowering in 20–24 months and fruiting in the following six months.','Juicy pineapple',NULL,8),(13,'\0','\0','',33,543.2,'The lychee has a history and cultivation going back to 2000 BC according to records in China. Cultivation began in the area of southern China, Malaysia, and Vietnam. Wild trees still grow in parts of southern China and on Hainan Island. There are many stories of the fruit\'s use as a delicacy in the Chinese Imperial Court. ','Litchi',NULL,8),(14,'\0','\0','',33,543.2,'Yellow onion (Allium cepa) (Latin \'cepa\' = onion), also known as the bulb onion or common onion, is used as a vegetable and is the most widely cultivated species of the genus Allium. This genus also contains several other species variously referred to as onions and cultivated for food, such as the Japanese bunching onion (A. fistulosum), the Egyptian onion (A. ×proliferum), and the Canada onion (A. canadense). ','Yellow onion',NULL,11),(15,'\0','\0','',33,543.2,'Red onion (Allium cepa) (Latin \'cepa\' = onion), also known as the bulb onion or common onion, is used as a vegetable and is the most widely cultivated species of the genus Allium. This genus also contains several other species variously referred to as onions and cultivated for food, such as the Japanese bunching onion (A. fistulosum), the Egyptian onion (A. ×proliferum), and the Canada onion (A. canadense). ','Red onion',NULL,11);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promo`
--

DROP TABLE IF EXISTS `promo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promo` (
  `promoId` int(11) NOT NULL AUTO_INCREMENT,
  `promo_code` varchar(50) NOT NULL,
  `promo_discount` double DEFAULT NULL,
  `promo_expiry` datetime DEFAULT NULL,
  `promo_tnc` longtext,
  PRIMARY KEY (`promoId`),
  UNIQUE KEY `UK_a5vwdbf9cugqt6qx2bcwyxu80` (`promo_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promo`
--

LOCK TABLES `promo` WRITE;
/*!40000 ALTER TABLE `promo` DISABLE KEYS */;
/*!40000 ALTER TABLE `promo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_cotegory`
--

DROP TABLE IF EXISTS `stock_cotegory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock_cotegory` (
  `stock_cotegory_id` int(11) NOT NULL AUTO_INCREMENT,
  `stock_cotegory_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`stock_cotegory_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_cotegory`
--

LOCK TABLES `stock_cotegory` WRITE;
/*!40000 ALTER TABLE `stock_cotegory` DISABLE KEYS */;
INSERT INTO `stock_cotegory` VALUES (1,'Fruits'),(2,'Vegetables');
/*!40000 ALTER TABLE `stock_cotegory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(150) DEFAULT NULL,
  `f_name` varchar(30) DEFAULT NULL,
  `l_name` varchar(30) DEFAULT NULL,
  `mob_no` varchar(15) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  `points` int(11) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_address`
--

DROP TABLE IF EXISTS `user_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_address` (
  `user_id` int(11) NOT NULL,
  `city` varchar(30) DEFAULT NULL,
  `f_line` varchar(200) DEFAULT NULL,
  `pincode` varchar(15) DEFAULT NULL,
  `s_line` varchar(200) DEFAULT NULL,
  `state` varchar(30) DEFAULT NULL,
  `address_id` int(11) NOT NULL,
  PRIMARY KEY (`address_id`),
  KEY `FK_kfu0161nvirkey6fwd6orucv7` (`user_id`),
  CONSTRAINT `FK_kfu0161nvirkey6fwd6orucv7` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_address`
--

LOCK TABLES `user_address` WRITE;
/*!40000 ALTER TABLE `user_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_address` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-03-30  3:16:23
