CREATE DATABASE  IF NOT EXISTS `dailyshopv2` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dailyshopv2`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: dailyshopv2
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
-- Table structure for table `chitiethoadon`
--

DROP TABLE IF EXISTS `chitiethoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitiethoadon` (
  `IdHoaDon` int(11) NOT NULL AUTO_INCREMENT,
  `IdChiTietSanPham` int(11) NOT NULL,
  `SoLuong` int(11) DEFAULT NULL,
  `GiaTien` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdHoaDon`,`IdChiTietSanPham`),
  KEY `fk_ChiTietHoaDon_ChiTietSanPham` (`IdChiTietSanPham`),
  CONSTRAINT `fk_ChiTietHoaDon_ChiTietSanPham` FOREIGN KEY (`IdChiTietSanPham`) REFERENCES `chitietsanpham` (`IdChiTietSanPham`),
  CONSTRAINT `fk_ChiTietHoaDon_HoaDon` FOREIGN KEY (`IdHoaDon`) REFERENCES `hoadon` (`IdHoaDon`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitiethoadon`
--

LOCK TABLES `chitiethoadon` WRITE;
/*!40000 ALTER TABLE `chitiethoadon` DISABLE KEYS */;
INSERT INTO `chitiethoadon` VALUES (12,96,1,344),(14,99,1,341),(14,100,1,341),(14,117,1,325);
/*!40000 ALTER TABLE `chitiethoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitietsanpham`
--

DROP TABLE IF EXISTS `chitietsanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitietsanpham` (
  `IdChiTietSanPham` int(11) NOT NULL AUTO_INCREMENT,
  `IdSanPham` int(11) DEFAULT NULL,
  `IdSize` int(11) DEFAULT NULL,
  `IdMau` int(11) DEFAULT NULL,
  `SoLuong` int(11) DEFAULT NULL,
  `NgayNhap` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`IdChiTietSanPham`),
  KEY `fk_ChiTietSP_SanPham` (`IdSanPham`),
  KEY `fk_ChiTietSP_Size` (`IdSize`),
  KEY `fk_ChiTietSP_Mau` (`IdMau`),
  CONSTRAINT `fk_ChiTietSP_Mau` FOREIGN KEY (`IdMau`) REFERENCES `mausanpham` (`IdMau`),
  CONSTRAINT `fk_ChiTietSP_SanPham` FOREIGN KEY (`IdSanPham`) REFERENCES `sanpham` (`IdSanPham`),
  CONSTRAINT `fk_ChiTietSP_Size` FOREIGN KEY (`IdSize`) REFERENCES `sizesanpham` (`IdSize`)
) ENGINE=InnoDB AUTO_INCREMENT=253 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitietsanpham`
--

LOCK TABLES `chitietsanpham` WRITE;
/*!40000 ALTER TABLE `chitietsanpham` DISABLE KEYS */;
INSERT INTO `chitietsanpham` VALUES (36,17,1,1,122,NULL),(37,17,1,3,141,NULL),(38,17,2,1,123,NULL),(39,18,2,1,321,NULL),(40,18,1,2,234,NULL),(41,19,1,1,432,NULL),(42,19,2,2,345,NULL),(43,20,2,2,543,NULL),(44,20,1,1,456,NULL),(45,21,2,1,654,NULL),(46,21,1,2,567,NULL),(47,22,2,2,764,NULL),(48,22,2,1,677,NULL),(49,23,2,3,876,NULL),(50,23,3,2,789,NULL),(51,24,2,4,987,NULL),(52,24,4,2,123,NULL),(53,25,2,5,432,NULL),(54,25,5,2,321,NULL),(59,26,2,3,234,NULL),(60,26,3,2,432,NULL),(61,27,4,3,345,NULL),(62,27,3,4,543,NULL),(73,56,3,3,123,NULL),(74,56,3,2,423,NULL),(75,57,5,3,124,NULL),(76,57,5,2,342,NULL),(77,58,5,2,124,NULL),(78,58,2,5,361,NULL),(79,59,4,2,175,NULL),(80,59,2,4,312,NULL),(81,60,1,1,257,NULL),(82,60,1,2,174,NULL),(83,61,2,2,126,NULL),(84,61,1,2,257,NULL),(85,62,1,2,237,NULL),(86,62,2,3,147,NULL),(87,63,3,4,361,NULL),(88,63,4,5,246,NULL),(89,64,4,4,384,NULL),(90,64,3,3,136,NULL),(91,65,2,2,357,NULL),(92,65,1,1,257,NULL),(93,66,1,2,468,NULL),(94,66,2,2,249,NULL),(95,67,3,3,135,NULL),(96,67,4,4,454,NULL),(97,68,4,5,230,NULL),(98,68,3,4,346,NULL),(99,69,2,3,230,NULL),(100,69,1,2,456,NULL),(101,70,2,1,311,NULL),(102,70,3,2,121,NULL),(103,71,3,2,324,NULL),(104,71,4,3,164,NULL),(105,72,5,4,287,NULL),(106,72,4,5,258,NULL),(107,73,3,3,357,NULL),(108,73,2,2,237,NULL),(109,74,1,1,457,NULL),(110,74,2,2,653,NULL),(111,75,3,3,126,NULL),(112,75,4,4,423,NULL),(113,76,5,5,111,NULL),(114,76,5,3,222,NULL),(115,77,4,2,333,NULL),(116,77,3,1,444,NULL),(117,78,2,2,554,NULL),(118,78,1,3,179,NULL),(119,79,2,2,346,NULL),(120,79,3,2,226,NULL),(121,80,4,3,563,NULL),(122,80,5,4,223,NULL),(123,81,3,2,542,NULL),(124,81,2,1,221,NULL),(125,82,1,2,416,NULL),(126,82,2,3,259,NULL),(127,83,3,4,234,NULL),(128,83,4,5,323,NULL),(129,84,4,3,331,NULL),(130,84,3,2,513,NULL),(131,85,2,2,135,NULL),(132,85,3,3,542,NULL),(133,86,5,4,264,NULL),(134,86,3,3,235,NULL),(135,87,2,3,358,NULL),(136,87,1,2,246,NULL),(137,88,2,2,235,NULL),(138,88,3,1,326,NULL),(139,89,4,2,268,NULL),(140,89,3,5,247,NULL),(141,90,2,4,246,NULL),(142,90,2,3,357,NULL),(143,91,1,2,321,NULL),(144,91,3,5,235,NULL),(145,92,5,4,421,NULL),(146,92,3,3,468,NULL),(147,93,2,2,241,NULL),(148,93,2,1,211,NULL),(248,125,1,5,111,NULL),(249,125,4,3,222,NULL),(250,126,3,3,234,NULL),(251,126,5,2,333,NULL),(252,127,1,1,1234,NULL);
/*!40000 ALTER TABLE `chitietsanpham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `danhmucsanpham`
--

DROP TABLE IF EXISTS `danhmucsanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `danhmucsanpham` (
  `IdDanhMuc` int(11) NOT NULL AUTO_INCREMENT,
  `TenDanhMuc` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `MetaTitle` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`IdDanhMuc`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `danhmucsanpham`
--

LOCK TABLES `danhmucsanpham` WRITE;
/*!40000 ALTER TABLE `danhmucsanpham` DISABLE KEYS */;
INSERT INTO `danhmucsanpham` VALUES (1,'Men','men'),(2,'Women','women'),(3,'Kids','kid'),(4,'Sports','sport'),(5,'Furniture','furniture');
/*!40000 ALTER TABLE `danhmucsanpham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_detail`
--

DROP TABLE IF EXISTS `group_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `non_del` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_detail`
--

LOCK TABLES `group_detail` WRITE;
/*!40000 ALTER TABLE `group_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (10),(10);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon` (
  `IdHoaDon` int(11) NOT NULL AUTO_INCREMENT,
  `TenKhachHang` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Sdt` varchar(20) DEFAULT NULL,
  `DiaChiGiao` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `HinhThucGiao` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `GhiChu` text,
  `NgayLap` varchar(30) DEFAULT NULL,
  `DaThanhToan` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`IdHoaDon`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
INSERT INTO `hoadon` VALUES (12,'Huy Huy','0987654321','33 Hoàng Sĩ Khải, Sơn Trà, Đà Nẵng',NULL,'hihihihihihihihihihi','2019/12/18 20:35:59',0),(14,'Huy Huy','0987654321','33 Hoàng Sĩ Khải, Sơn Trà, Đà Nẵng',NULL,'hi hi h ih ih ih ih i hi hi','2019/12/19 10:12:35',0);
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mausanpham`
--

DROP TABLE IF EXISTS `mausanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mausanpham` (
  `IdMau` int(11) NOT NULL AUTO_INCREMENT,
  `Mau` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`IdMau`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mausanpham`
--

LOCK TABLES `mausanpham` WRITE;
/*!40000 ALTER TABLE `mausanpham` DISABLE KEYS */;
INSERT INTO `mausanpham` VALUES (1,'White'),(2,'Red'),(3,'Yellow'),(4,'Gray'),(5,'Black');
/*!40000 ALTER TABLE `mausanpham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'MEMBER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanpham`
--

DROP TABLE IF EXISTS `sanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanpham` (
  `IdSanPham` int(11) NOT NULL AUTO_INCREMENT,
  `IdDanhMuc` int(11) DEFAULT NULL,
  `TenSanPham` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `GiaTien` int(11) DEFAULT NULL,
  `MoTa` text,
  `GioiTinh` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `HinhAnh` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`IdSanPham`),
  KEY `fk_SanPham_DanhMuc` (`IdDanhMuc`),
  CONSTRAINT `fk_SanPham_DanhMuc` FOREIGN KEY (`IdDanhMuc`) REFERENCES `danhmucsanpham` (`IdDanhMuc`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanpham`
--

LOCK TABLES `sanpham` WRITE;
/*!40000 ALTER TABLE `sanpham` DISABLE KEYS */;
INSERT INTO `sanpham` VALUES (17,1,'Cable Twist Contrast',333,'Made in Chị Na','male','Brave Soul Cable Twist Contrast Jumper.jpg'),(18,1,'Burton Menswear',230,'Hàng Việt Nam chất lượng Tàu','male','Burton Menswear.jpg'),(19,1,'Cable Knit',435,'Chất liệu len','male','Cable Knit Jumper In White.jpg'),(20,1,'Connection Reindeer Fairisle 3',253,'Sản xuất tại Mỹ','male','Connection Reindeer Fairisle Christmas Jumper.jpg'),(21,1,'crew neck',364,'Áo len sọc trắng đỏ','male','crew neck jumper in red stripe.jpg'),(22,1,'diagonal jumper stripe',245,'Áo len sọc chéo','male','diagonal jumper stripe.jpg'),(23,1,'hand knitted',653,'Giá mắc khủng khiếp','male','hand knitted heavyweight jumper.jpg'),(24,1,'Homme knitted',200,'Gái rẻ như cho','male','Homme knitted jumper.jpg'),(25,1,'jacquard knit',432,'Màu mè xôi chè','male','jacquard knit jumper.jpg'),(26,1,'lambswool cable crew neck',231,'Thiết kế đơn giản','male','lambswool cable crew neck jumper.jpg'),(27,1,'Menswear pucker up',634,'Giá mắc khủng khiếp. Cho còn đánh','male','Menswear pucker up Christmas jumper.jpg'),(56,2,'Baggy Denim Sherpa Trucker Jacket',457,'Không có mô tả đâu nha mấy má','female','Baggy Denim Sherpa Trucker Jacket.jpg'),(57,2,'Champion Reverse Weave',356,'Mô tả mẹ gì nữa','female','Weave Logo Patch Sweatshirt.jpg'),(58,2,'UO Bobby Boyfriend Striped Crew',686,'Giá mắc kinh hồn. Cho không thèm lấy','female','UO Bobby Boyfriend Striped Crew.jpg'),(59,2,'UO Brea Fleece Hooded Zip-Up Jacket',475,'Giá hơi mắc','female','Hooded Zip-Up Jacket.jpg'),(60,2,'UO Casey Kick Flare Pant',346,'Quần chấm bi mặc dô cảm thấy thoải mái','female','UO Casey Kick Flare Pant.jpg'),(61,2,'UO Cropped Teddy Jacket',567,'Không có mô tả','female','UO Cropped Teddy Jacket.jpg'),(62,2,'UO Dolman Teddy Jacket',235,'Giá rẻ bất ngờ','female','UO Dolman Teddy Jacket.jpg'),(63,2,'UO Willow Fuzzy Drawstring Teddy Jacket',123,'Hàng xịn giá rẻ. Mua ngay ko hết','female','Drawstring Teddy Jacket.jpg'),(64,3,'Infant Unicorn Sk8-Hi Crib',355,'Giá rẻ như cho. Mua ngay ko hết','female','Infant Unicorn Sk8-Hi Crib.jpg'),(65,3,'Kids Checkerboard Slip-On',234,'Giá rẻ bất ngờ','male','Kids Checkerboard Slip-On.jpg'),(66,3,'Kids Floatie Sharks Slip-On',235,'Hàng nhập khẩu từ Lào','male','Kids Floatie Sharks Slip-On.jpg'),(67,3,'Kids Old Skool V',344,'Hàng Mỹ giá rẻ','male','Kids Old Skool V.jpg'),(68,3,'Kids Primary Check Old Skool',364,'Không có mô tả đâu','male','Kids Primary Check Old Skool.jpg'),(69,3,'Kids Unicorn Old Skool',341,'Không mô tả','male','Kids Unicorn Old Skool.jpg'),(70,3,'Kids Unicorn Sk8-Hi Zip',324,'Mô tả ma tổ','male','Kids Unicorn Sk8-Hi Zip.jpg'),(71,3,'Toddler Asher V',235,'Hổng có mô tả','male','Toddler Asher V.jpg'),(72,3,'Toddler Checkerboard Slip-On',363,'Không nghĩ ra gì để mô tả','male','Toddler Checkerboard Slip-On.jpg'),(73,3,'Toddler UltraRange Rapidweld',346,'Mô tả mẹ gì','male','Toddler UltraRange Rapidweld.jpg'),(74,3,'Toddler Unicorn Sk8-Hi Zip',463,'Giá mắc bà cố','female','Toddler Unicorn Sk8-Hi Zip.jpg'),(75,4,'nike-dri-fit-flex-stride-5-short-men',235,'Mặc vô thoải mái','male','nike-dri-fit-flex-stride-5-short-men.jpg'),(76,4,'nike-dry-miler-ls-top-women',246,'Chất liệu thấm mồ hôi','male','nike-dry-miler-ls-top-women.jpg'),(77,4,'nike-hooded-printed-jacket-women',356,'Áo giành cho mấy má bánh bèo','female','nike-hooded-printed-jacket-women.jpg'),(78,4,'nike-miler-essential-men',325,'Chưa nghĩ ra mô tả','male','nike-miler-essential-men.jpg'),(79,4,'nike-miler-ss-top-men',245,'Thiết kế đơn giản, mặc vô thoải mái','male','nike-miler-ss-top-men.jpg'),(80,4,'nike-miler-tanktop-men',235,'Áo khoe lông nách','male','nike-miler-tanktop-men.jpg'),(81,4,'nike-rise-365-ss-top-men',325,'Chất tới từng đồng','male','nike-rise-365-ss-top-men.jpg'),(82,4,'nike-rise-aj',134,'Thiết kế trẻ trung','male','nike-rise-365-ss-top-men-aj7671.jpg'),(83,4,'nike-windrunner-jacket-men',335,'Áo phao chống chìm','male','nike-windrunner-jacket-men.jpg'),(84,4,'nike-zonal-cooling-relay-men',465,'Thiết kế đơn giản mà sâu sắc','male','nike-zonal-cooling-relay-men.jpg'),(85,5,'Dining-Chair',346,'Giá mắc kinh dị','nosex','Dining-Chair.jpg'),(86,5,'Hall-Furniture',253,'Thiết kế thông minh tiện lợi','nosex','Hall-Furniture.jpg'),(87,5,'Living_Room_Chairs',314,'Ngồi lên thoải mái','nosex','Living_Room_Chairs.jpg'),(88,5,'Shoe-Cabinet',235,'Tủ vạn năng, đựng được mọi thứ','nosex','Shoe-Cabinet.jpg'),(89,5,'Shoe-Rack-tobago',423,'Giá để dép','nosex','Shoe-Rack-tobago.jpg'),(90,5,'Stool-jefferson',352,'Ngồi lên không ê đít','nosex','Stool-jefferson.jpg'),(91,5,'Stools-Mozart',235,'Ghế nghệ sĩ','nosex','Stools-Mozart.jpg'),(92,5,'TV-Bench',143,'Giá rẻ như cho ho ho','nosex','TV-Bench.jpg'),(93,5,'Vail-Linen-Upholstered-Sofa',312,'Đồ Mỹ chất lượng cao','nosex','Vail-Linen-Upholstered-Sofa.jpg'),(123,1,'aaaaaaa',123,'aaaaaaaaaaaaaaaaaa','male','spring.png'),(125,1,'jdfghljnb',123,'bdofbonfuob','male','huyheo.png'),(126,1,'hi hi hi hi',9669,'Made in Chị Na','male','spring.png'),(127,1,'213123132123',123123,'123123123123123','male','huyheo.png'),(129,1,'asdfsdfsdf',124,'12412412rwfdsf','male','spring.png');
/*!40000 ALTER TABLE `sanpham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sizesanpham`
--

DROP TABLE IF EXISTS `sizesanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sizesanpham` (
  `IdSize` int(11) NOT NULL AUTO_INCREMENT,
  `Size` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`IdSize`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sizesanpham`
--

LOCK TABLES `sizesanpham` WRITE;
/*!40000 ALTER TABLE `sizesanpham` DISABLE KEYS */;
INSERT INTO `sizesanpham` VALUES (1,'S'),(2,'X'),(3,'L'),(4,'XL'),(5,'SM'),(6,'MM');
/*!40000 ALTER TABLE `sizesanpham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token_verfication`
--

DROP TABLE IF EXISTS `token_verfication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token_verfication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token_code` varchar(100) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token_verfication`
--

LOCK TABLES `token_verfication` WRITE;
/*!40000 ALTER TABLE `token_verfication` DISABLE KEYS */;
INSERT INTO `token_verfication` VALUES (1,'$2a$10$J/YpMNw9m1UpKnHcaUZIvuk4Vra06f0ecaAGD4YgmHhVV8xzckInO',7,'2019-12-25 16:28:48','1'),(2,'$2a$10$8p7kn4a/gP3Iz1GspcrVSOPQocQRRnstMwjHyfgEuM9g79SCbkbuy',9,'2019-12-25 16:37:13','1'),(3,'$2a$10$aita5gJnOXKY8iN5Nj1s2e7skdWX5Kj2eiIXVoOFH3p.9J2nYtHay',9,'2019-12-25 16:40:37','1'),(4,'$2a$10$4p9RxnOOVevq6l9Ia0cIeO54A9nF5DN0v9p0./eLq8BoMiGG2bCQ2',9,'2019-12-25 16:42:23','1'),(5,'$2a$10$WNkXDP20sVYU00S4/6V6n.BqBA8IAFC.j6/fWm4.Kodny0DjtzmC6',9,'2019-12-25 17:12:39','1'),(6,'$2a$10$VzOAgmqjvl1RpswsTWLrieSK4sWQ7qtqLvV30hw..CYI2hPcKO7fi',9,'2019-12-25 17:25:07','1'),(7,'$2a$10$hYEcdw9dHGb.pxw1hlMWNe0vi2XwT0fUr4EYjdsSctip3JPATHy3a',9,'2019-12-25 17:25:44','1'),(8,'$2a$10$K37Yn3aoc06CYRk2Geqdxukp6hntIJVBo9DPxnFEmv6GsAB6/A5jG',9,'2019-12-25 17:36:58','1'),(9,'$2a$10$IEK1DTA0Xrr1Tl.9gf.41eZWWRa2b.4S5E8Z6BUuPlc85/HZNNAe2',9,'2019-12-25 17:39:23','1'),(10,'$2a$10$/xtWnuy4GmuQ8WgM60IAHuLM8L8lH5f58d6aGXSuR89rNmYyNsBXm',9,'2019-12-25 21:15:18','1'),(11,'$2a$10$sx8VJXQ8NPE6DpRV9CQmBu0BW1ZGNylFc1L7rhFUy.4mvF9trthWC',9,'2019-12-25 21:20:56','1');
/*!40000 ALTER TABLE `token_verfication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(60) NOT NULL,
  `fullname` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (6,'admin@gmail.com','$2a$10$7fCcyjSq81se1mPdZO8D2uhpfhqCzTfAddehUEjgqsgGsNH4I6q9y','Admin','Asian Tech'),(7,'huyhuy@gmail.com','$2a$10$Gsyexk6rCQCMcl5pV5pjnO4cRni4LTwKTZE.2fEg5o9IH4yvmdgp.','Huy Huy','33 Hoàng Sĩ Khải, Sơn Trà, Đà Nẵng'),(8,'huyheo@gmail.com','$2a$10$5/vGMTjBGhhExQbZ/8cZnOhAQBeMor5f4xnaSaMRAwogXwxoyKx5m','Huy Heo','30 Đỗ Xuân Hợp, Sơn Trà, Đà Nẵng'),(9,'dqnhathuy96.ch@gmail.com','$2a$10$oxpEgM697SjbMnTy99dOFOuZqpbOyyJpYU.VITUAVqgDUkdDHX8se','Huy Huy','30 Hoàng Sĩ Khải, Sơn Trà, Đà Nẵng');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (6,1),(6,2),(7,2),(8,2),(9,2);
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

-- Dump completed on 2019-12-25 11:18:53
