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

/*Table structure for table `const_expenses` */

DROP TABLE IF EXISTS `const_expenses`;

CREATE TABLE `const_expenses` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `company_id` bigint(20) NOT NULL,
  `date` date DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `construction_stage_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4lxshco4xrsgnxt3ur91jj4yb` (`construction_stage_id`),
  CONSTRAINT `FK4lxshco4xrsgnxt3ur91jj4yb` FOREIGN KEY (`construction_stage_id`) REFERENCES `const_stages` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `const_expenses` */

/*Table structure for table `const_stages` */

DROP TABLE IF EXISTS `const_stages`;

CREATE TABLE `const_stages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `budget` double DEFAULT NULL,
  `company_id` bigint(20) NOT NULL,
  `end_date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `status` enum('COMPLETED','IN_PROGRESS','NOT_STARTED') DEFAULT NULL,
  `building_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd103821kalv27jd3u83f9ht2p` (`building_id`),
  CONSTRAINT `FKd103821kalv27jd3u83f9ht2p` FOREIGN KEY (`building_id`) REFERENCES `core_buildings` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `const_stages` */

/*Table structure for table `core_buildings` */

DROP TABLE IF EXISTS `core_buildings`;

CREATE TABLE `core_buildings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` enum('COMMERCIAL','MIXED_USE','RESIDENTIAL') DEFAULT NULL,
  `project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn5ovbugnsmm6f4r8ubsc2bhl6` (`project_id`),
  CONSTRAINT `FKn5ovbugnsmm6f4r8ubsc2bhl6` FOREIGN KEY (`project_id`) REFERENCES `core_projects` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `core_buildings` */

insert  into `core_buildings`(`id`,`company_id`,`name`,`type`,`project_id`) values 
(1,1,'Building 1','RESIDENTIAL',1);

/*Table structure for table `core_floors` */

DROP TABLE IF EXISTS `core_floors`;

CREATE TABLE `core_floors` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `name` enum('BASEMENT','EIGHTH','ELEVENTH','FIFTEENTH','FIFTH','FIRST','FOURTEENTH','FOURTH','GROUND','NINTH','SECOND','SEVENTH','SIXTH','TENTH','THIRD','THIRTEENTH','TWELVETH') DEFAULT NULL,
  `building_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmch73qpt1j0a0kk7h5jpwy0tt` (`building_id`),
  CONSTRAINT `FKmch73qpt1j0a0kk7h5jpwy0tt` FOREIGN KEY (`building_id`) REFERENCES `core_buildings` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `core_floors` */

insert  into `core_floors`(`id`,`company_id`,`name`,`building_id`) values 
(1,1,'BASEMENT',1);

/*Table structure for table `core_projects` */

DROP TABLE IF EXISTS `core_projects`;

CREATE TABLE `core_projects` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `budget` double DEFAULT NULL,
  `company_id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `location` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `start_date` date DEFAULT NULL,
  `status` enum('COMPLETED','IN_PROGRESS','PLANNING') NOT NULL,
  `manager_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoutk3e54cyul162a2963avg53` (`manager_id`),
  CONSTRAINT `FKoutk3e54cyul162a2963avg53` FOREIGN KEY (`manager_id`) REFERENCES `sec_users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `core_projects` */

insert  into `core_projects`(`id`,`budget`,`company_id`,`description`,`end_date`,`location`,`name`,`start_date`,`status`,`manager_id`) values 
(1,15000000,1,'TEST','2024-10-01','Dhaka','Project 1','2024-09-01','PLANNING',1);

/*Table structure for table `core_projects_team_members` */

DROP TABLE IF EXISTS `core_projects_team_members`;

CREATE TABLE `core_projects_team_members` (
  `project_id` bigint(20) NOT NULL,
  `team_members_id` bigint(20) NOT NULL,
  KEY `FKmihnqqukom13up8mf31r7qkby` (`team_members_id`),
  KEY `FKkj06qd7s1wbf1uku0htlopvu5` (`project_id`),
  CONSTRAINT `FKkj06qd7s1wbf1uku0htlopvu5` FOREIGN KEY (`project_id`) REFERENCES `core_projects` (`id`),
  CONSTRAINT `FKmihnqqukom13up8mf31r7qkby` FOREIGN KEY (`team_members_id`) REFERENCES `sec_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `core_projects_team_members` */

insert  into `core_projects_team_members`(`project_id`,`team_members_id`) values 
(1,2),
(1,3);

/*Table structure for table `core_units` */

DROP TABLE IF EXISTS `core_units`;

CREATE TABLE `core_units` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `price` double DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `status` enum('AVAILABLE','RESERVED','SOLD') DEFAULT NULL,
  `type` enum('APARTMENT','OFFICE','OTHER','SHOP') DEFAULT NULL,
  `unit_number` varchar(255) DEFAULT NULL,
  `floor_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKeyvtk2j1j9hwnf81p5856b2pj` (`floor_id`),
  CONSTRAINT `FKeyvtk2j1j9hwnf81p5856b2pj` FOREIGN KEY (`floor_id`) REFERENCES `core_floors` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `core_units` */

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
(1,'Dhaka','01700000000','XYZ Properties Limited');

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
(1,'$2a$12$TqiRCG4f1hKoV8BylOyxcOJ026UBz.cvn2SFH5B1XRrNxwbYek25q','shabab1',1);

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
  `role` enum('ROLE_ADMIN','ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_MANAGER','ROLE_OWNER','ROLE_WORKER') NOT NULL,
  `status` varchar(255) NOT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKjp9f1xtuq2lpe6ky4me6yib1w` (`cell`),
  UNIQUE KEY `UKcfu0ko0i9l8afdu520rvtf318` (`email`),
  KEY `FK63iggr2p3ojqe8sioi8lp9dm9` (`company_id`),
  CONSTRAINT `FK63iggr2p3ojqe8sioi8lp9dm9` FOREIGN KEY (`company_id`) REFERENCES `sec_companies` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sec_users` */

insert  into `sec_users`(`id`,`address`,`avatar`,`blood_group`,`cell`,`date_of_birth`,`email`,`gender`,`joining_date`,`name`,`role`,`status`,`company_id`) values 
(1,'Dhaka',NULL,'B+','01700000000','2000-09-28','test@email.com','Male','2024-08-31','Shabab Ahmed','ROLE_ADMIN','Active',1),
(2,'Chittagong',NULL,'A+','01900000000','2000-09-28','test2@email.com','Male','2024-08-31','Samin Ahmed','ROLE_EMPLOYEE','Active',1),
(3,'Jhenaidah',NULL,'A+','01800000000','2000-09-28','test3@email.com','Male','2024-08-31','Shadman Labib','ROLE_EMPLOYEE','Active',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
