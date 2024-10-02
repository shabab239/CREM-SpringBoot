/*
SQLyog Professional v13.1.1 (64 bit)
MySQL - 10.4.11-MariaDB : Database - crems
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`crems` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `crems`;

/*Table structure for table `acc_accounts` */

DROP TABLE IF EXISTS `acc_accounts`;

CREATE TABLE `acc_accounts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `balance` double NOT NULL,
  `company_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `acc_accounts` */

insert  into `acc_accounts`(`id`,`balance`,`company_id`,`name`) values 
(1,0,1,'Shabab Ahmed Cash A/C'),
(2,0,1,'Supplier A Cash A/C'),
(3,0,1,'Supplier B Cash A/C'),
(4,0,1,'Customer A Cash A/C'),
(5,0,1,'Custoemr B Cash A/C');

/*Table structure for table `const_suppliers` */

DROP TABLE IF EXISTS `const_suppliers`;

CREATE TABLE `const_suppliers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `account_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKe4cvodnudyf0qy1k21irxiqk5` (`account_id`),
  CONSTRAINT `FK4a0d3opntk778yqwwutn8q12a` FOREIGN KEY (`account_id`) REFERENCES `acc_accounts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `const_suppliers` */

insert  into `const_suppliers`(`id`,`company_id`,`name`,`account_id`) values 
(1,1,'Supplier A',2),
(2,1,'Supplier B',3);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sec_companies` */

insert  into `sec_companies`(`id`,`address`,`contact`,`name`) values 
(1,'Dhaka','01300000000','ABC Construction & Real Estate');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sec_tokens` */

insert  into `sec_tokens`(`id`,`password`,`username`,`user_id`) values 
(1,'$2a$12$sedEacITyzNq8bDWuRXsyeoTJdfHuYS0cWiCgzfLW3Aj/W7E.QY16','shabab1',1);

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
  `account_id` bigint(20) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKjp9f1xtuq2lpe6ky4me6yib1w` (`cell`),
  UNIQUE KEY `UKcfu0ko0i9l8afdu520rvtf318` (`email`),
  UNIQUE KEY `UKo68pfc90mh5xa7acmhrjcwoga` (`account_id`),
  KEY `FK63iggr2p3ojqe8sioi8lp9dm9` (`company_id`),
  CONSTRAINT `FK63iggr2p3ojqe8sioi8lp9dm9` FOREIGN KEY (`company_id`) REFERENCES `sec_companies` (`id`),
  CONSTRAINT `FKks27a6m1i8qw3mb5og7xgiep7` FOREIGN KEY (`account_id`) REFERENCES `acc_accounts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sec_users` */

insert  into `sec_users`(`id`,`address`,`avatar`,`blood_group`,`cell`,`date_of_birth`,`email`,`gender`,`joining_date`,`name`,`role`,`status`,`account_id`,`company_id`) values 
(1,'Dhaka',NULL,'B+','01300000000',NULL,NULL,'Male',NULL,'Shabab Ahmed','ROLE_ADMIN','Active',1,1),
(2,NULL,NULL,NULL,'01200000000',NULL,NULL,NULL,NULL,'Customer A','ROLE_CUSTOMER','Active',4,1),
(3,NULL,NULL,NULL,'01300000001',NULL,NULL,NULL,NULL,'Customer B','ROLE_CUSTOMER','Active',5,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
