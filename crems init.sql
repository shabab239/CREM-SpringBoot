/*
SQLyog Professional v13.1.1 (64 bit)
MySQL - 10.4.32-MariaDB : Database - crems
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`crems` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `crems`;

/*Table structure for table `sec_companies` */

DROP TABLE IF EXISTS `sec_companies`;

CREATE TABLE `sec_companies` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `contact` varchar(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKiep6aelpkubbmrtwx2qambuou` (`contact`),
  UNIQUE KEY `UK4efvtcwpf5e15o9xynyo6nniu` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sec_companies` */

insert  into `sec_companies`(`id`,`address`,`contact`,`name`) values 
(1,'Dhaka','01700000000','ABC Properties Ltd.');

/*Table structure for table `sec_tokens` */

DROP TABLE IF EXISTS `sec_tokens`;

CREATE TABLE `sec_tokens` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKm5b6v0997r817fnsaknheak4e` (`username`),
  UNIQUE KEY `UKherp3iou75759xlnkuk7mq633` (`user_id`),
  CONSTRAINT `FKledgtviiby7dm6303cyxm6f0p` FOREIGN KEY (`user_id`) REFERENCES `sec_users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sec_tokens` */

insert  into `sec_tokens`(`id`,`password`,`username`,`user_id`) values 
(1,'$2a$12$gQTOwzULa//tJJkAg1AxheGXw/tkhjP2vPto5pr6ciq5TF8vaRB2.','shabab',1),
(3,'$2a$12$gQTOwzULa//tJJkAg1AxheGXw/tkhjP2vPto5pr6ciq5TF8vaRB2.','employeeA',2),
(8,'$2a$12$gQTOwzULa//tJJkAg1AxheGXw/tkhjP2vPto5pr6ciq5TF8vaRB2.','managerA',5);

/*Table structure for table `sec_users` */

DROP TABLE IF EXISTS `sec_users`;

CREATE TABLE `sec_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `blood_group` varchar(255) DEFAULT NULL,
  `cell` varchar(11) NOT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `joining_date` date DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `role` enum('ROLE_ADMIN','ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_MANAGER','ROLE_OWNER') NOT NULL,
  `status` varchar(255) NOT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKjp9f1xtuq2lpe6ky4me6yib1w` (`cell`),
  UNIQUE KEY `UKcfu0ko0i9l8afdu520rvtf318` (`email`),
  KEY `FK63iggr2p3ojqe8sioi8lp9dm9` (`company_id`),
  CONSTRAINT `FK63iggr2p3ojqe8sioi8lp9dm9` FOREIGN KEY (`company_id`) REFERENCES `sec_companies` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sec_users` */

insert  into `sec_users`(`id`,`address`,`avatar`,`blood_group`,`cell`,`date_of_birth`,`email`,`gender`,`joining_date`,`name`,`role`,`status`,`company_id`) values 
(1,'Dhaka',NULL,'B+','01700000000',NULL,'test@test.com','Male',NULL,'Shabab Ahmed','ROLE_ADMIN','Active',1),
(2,NULL,NULL,NULL,'01900000000',NULL,NULL,'Male',NULL,'Employee A','ROLE_EMPLOYEE','Active',1),
(5,NULL,NULL,NULL,'01300000000',NULL,NULL,NULL,NULL,'Manager A','ROLE_MANAGER','Active',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
