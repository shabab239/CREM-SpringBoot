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
  `balance` double NOT NULL DEFAULT 0,
  `name` varchar(255) NOT NULL,
  `company` bigint(20) DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `worker_id` bigint(20) DEFAULT NULL,
  `company_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKe3b6rbx7f7qle2llv6qy0wku9` (`company`),
  UNIQUE KEY `UK829a3ha7nypnbdeasr8yi53us` (`supplier_id`),
  UNIQUE KEY `UKau3wybk2dtgd0tdp8ww3d2ke0` (`user_id`),
  UNIQUE KEY `UK6e5wuxwn64dbqhfy1836khsur` (`worker_id`),
  CONSTRAINT `FKkai23dqjxfo2aih9aro3xxd4q` FOREIGN KEY (`supplier_id`) REFERENCES `raw_material_suppliers` (`id`),
  CONSTRAINT `FKkyb74coqypxkua451kwk3n312` FOREIGN KEY (`company`) REFERENCES `sec_companies` (`id`),
  CONSTRAINT `FKpfefedx3o24gy6sb16rghvcc2` FOREIGN KEY (`worker_id`) REFERENCES `workers` (`id`),
  CONSTRAINT `FKqklmmav9tuf5ssrkq60kmypmu` FOREIGN KEY (`user_id`) REFERENCES `sec_users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

/*Data for the table `acc_accounts` */

insert  into `acc_accounts`(`id`,`balance`,`name`,`company`,`supplier_id`,`user_id`,`worker_id`,`company_id`) values 
(1,500000,'TEST Supplier A/C',NULL,1,NULL,NULL,1),
(10,-499500,'ABC Properties Ltd. Company A/C',1,NULL,NULL,NULL,1),
(11,0,'Worker A Worker A/C',NULL,NULL,NULL,1,1),
(12,0,'Worker B Worker A/C',NULL,NULL,NULL,2,1),
(13,0,'Worker C Worker A/C',NULL,NULL,NULL,3,1),
(14,-500,'Customer A User A/C',NULL,NULL,6,NULL,1);

/*Table structure for table `acc_bookings` */

DROP TABLE IF EXISTS `acc_bookings`;

CREATE TABLE `acc_bookings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `booking_date` date NOT NULL,
  `company_id` bigint(20) NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  `unit_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKqiivhk4p85u832837docwraod` (`unit_id`),
  KEY `FKsyfdt88rjypxjtmcy0um5emaf` (`customer_id`),
  CONSTRAINT `FKpbkh1gj31jkeo0u5gdtwjjyb7` FOREIGN KEY (`unit_id`) REFERENCES `const_units` (`id`),
  CONSTRAINT `FKsyfdt88rjypxjtmcy0um5emaf` FOREIGN KEY (`customer_id`) REFERENCES `sec_users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `acc_bookings` */

insert  into `acc_bookings`(`id`,`booking_date`,`company_id`,`customer_id`,`unit_id`) values 
(1,'2024-11-14',1,6,1);

/*Table structure for table `acc_payments` */

DROP TABLE IF EXISTS `acc_payments`;

CREATE TABLE `acc_payments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `company_id` bigint(20) NOT NULL,
  `date` date NOT NULL,
  `booking_id` bigint(20) NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  `group_transaction_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmd3e5i9j3yns3l8fn0d3aso4i` (`booking_id`),
  KEY `FKbokqvpt9rcavhp4iljy17fqqt` (`customer_id`),
  CONSTRAINT `FKbokqvpt9rcavhp4iljy17fqqt` FOREIGN KEY (`customer_id`) REFERENCES `sec_users` (`id`),
  CONSTRAINT `FKmd3e5i9j3yns3l8fn0d3aso4i` FOREIGN KEY (`booking_id`) REFERENCES `acc_bookings` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `acc_payments` */

insert  into `acc_payments`(`id`,`amount`,`company_id`,`date`,`booking_id`,`customer_id`,`group_transaction_id`) values 
(1,500,1,'2024-11-14',1,6,'9100433b-9880-4890-8449-08e528de59eb');

/*Table structure for table `acc_transactions` */

DROP TABLE IF EXISTS `acc_transactions`;

CREATE TABLE `acc_transactions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `company_id` bigint(20) NOT NULL,
  `date` datetime(6) NOT NULL,
  `group_transaction_id` varchar(255) NOT NULL,
  `particular` varchar(255) NOT NULL,
  `type` enum('EXPENSE','INCOME') NOT NULL,
  `account_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKln0ee8gf3dthc6v0gubl0jp8h` (`account_id`),
  CONSTRAINT `FKln0ee8gf3dthc6v0gubl0jp8h` FOREIGN KEY (`account_id`) REFERENCES `acc_accounts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

/*Data for the table `acc_transactions` */

insert  into `acc_transactions`(`id`,`amount`,`company_id`,`date`,`group_transaction_id`,`particular`,`type`,`account_id`) values 
(5,500000,1,'2024-11-09 11:18:12.000000','6022cd3c-8877-43d6-9b7c-5ea2cd0753ae','Raw Material Order - Steel','EXPENSE',10),
(6,500000,1,'2024-11-09 11:18:12.000000','6022cd3c-8877-43d6-9b7c-5ea2cd0753ae','Raw Material Order - Steel','INCOME',1),
(7,500,1,'2024-11-14 07:27:15.000000','9100433b-9880-4890-8449-08e528de59eb','Booking Payment - Unit 101','EXPENSE',14),
(8,500,1,'2024-11-14 07:27:15.000000','9100433b-9880-4890-8449-08e528de59eb','Booking Payment - Unit 101','INCOME',10);

/*Table structure for table `const_buildings` */

DROP TABLE IF EXISTS `const_buildings`;

CREATE TABLE `const_buildings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` enum('COMMERCIAL','MIXED_USE','RESIDENTIAL') DEFAULT NULL,
  `project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo5prue9001hoyljt7ckq7dyvc` (`project_id`),
  CONSTRAINT `FKo5prue9001hoyljt7ckq7dyvc` FOREIGN KEY (`project_id`) REFERENCES `const_projects` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

/*Data for the table `const_buildings` */

insert  into `const_buildings`(`id`,`company_id`,`name`,`type`,`project_id`) values 
(1,1,'Building B','RESIDENTIAL',1);

/*Table structure for table `const_floors` */

DROP TABLE IF EXISTS `const_floors`;

CREATE TABLE `const_floors` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `name` enum('BASEMENT','EIGHTH','ELEVENTH','FIFTEENTH','FIFTH','FIRST','FOURTEENTH','FOURTH','GROUND','NINTH','SECOND','SEVENTH','SIXTH','TENTH','THIRD','THIRTEENTH','TWELVETH') DEFAULT NULL,
  `building_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5qnqv8nwuw4w46mf4arcsbbpn` (`building_id`),
  CONSTRAINT `FK5qnqv8nwuw4w46mf4arcsbbpn` FOREIGN KEY (`building_id`) REFERENCES `const_buildings` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `const_floors` */

insert  into `const_floors`(`id`,`company_id`,`name`,`building_id`) values 
(1,1,'FIRST',1),
(2,1,'BASEMENT',1),
(3,1,'SECOND',1);

/*Table structure for table `const_projects` */

DROP TABLE IF EXISTS `const_projects`;

CREATE TABLE `const_projects` (
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
  KEY `FK88a7fwmqsi3grog063agr8cu8` (`manager_id`),
  CONSTRAINT `FK88a7fwmqsi3grog063agr8cu8` FOREIGN KEY (`manager_id`) REFERENCES `sec_users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `const_projects` */

insert  into `const_projects`(`id`,`budget`,`company_id`,`description`,`end_date`,`location`,`name`,`start_date`,`status`,`manager_id`) values 
(1,15000000,1,'TEST','2024-11-14','Dhaka','Project A','2024-11-01','IN_PROGRESS',1),
(2,123,1,'TEST','2024-11-13','Dhaka2','KELTU','2024-11-13','IN_PROGRESS',NULL),
(3,123,1,'yyy','2024-11-14','yyy2','yyy2','2024-11-14','PLANNING',2);

/*Table structure for table `const_projects_team_members` */

DROP TABLE IF EXISTS `const_projects_team_members`;

CREATE TABLE `const_projects_team_members` (
  `project_id` bigint(20) NOT NULL,
  `team_members_id` bigint(20) NOT NULL,
  KEY `FKfxcfctfq6g7f3jntf28qvn2xy` (`team_members_id`),
  KEY `FKg9g6rm0g4op1b9jdqc73ecbrp` (`project_id`),
  CONSTRAINT `FKfxcfctfq6g7f3jntf28qvn2xy` FOREIGN KEY (`team_members_id`) REFERENCES `sec_users` (`id`),
  CONSTRAINT `FKg9g6rm0g4op1b9jdqc73ecbrp` FOREIGN KEY (`project_id`) REFERENCES `const_projects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `const_projects_team_members` */

insert  into `const_projects_team_members`(`project_id`,`team_members_id`) values 
(1,1),
(2,2),
(2,5),
(3,1),
(3,1),
(3,2),
(3,2),
(3,2),
(3,5),
(3,5),
(3,5),
(3,5),
(3,5),
(3,5),
(3,2);

/*Table structure for table `const_stages` */

DROP TABLE IF EXISTS `const_stages`;

CREATE TABLE `const_stages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `end_date` date DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `start_date` date DEFAULT NULL,
  `status` enum('COMPLETED','IN_PROGRESS','NOT_STARTED') DEFAULT NULL,
  `building_id` bigint(20) DEFAULT NULL,
  `floor_id` bigint(20) DEFAULT NULL,
  `unit_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7wh4dgi68qmu7wo26j6bb8fox` (`building_id`),
  KEY `FKm8wr8eckcnmmyoah263v5aaec` (`floor_id`),
  KEY `FKp3hxy4hxb5etwq4vsab2bfn6w` (`unit_id`),
  CONSTRAINT `FK7wh4dgi68qmu7wo26j6bb8fox` FOREIGN KEY (`building_id`) REFERENCES `const_buildings` (`id`),
  CONSTRAINT `FKm8wr8eckcnmmyoah263v5aaec` FOREIGN KEY (`floor_id`) REFERENCES `const_floors` (`id`),
  CONSTRAINT `FKp3hxy4hxb5etwq4vsab2bfn6w` FOREIGN KEY (`unit_id`) REFERENCES `const_units` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `const_stages` */

insert  into `const_stages`(`id`,`company_id`,`end_date`,`name`,`start_date`,`status`,`building_id`,`floor_id`,`unit_id`) values 
(1,1,'2024-11-16','Stage A','2024-11-09','IN_PROGRESS',NULL,NULL,1);

/*Table structure for table `const_stages_raw_materials` */

DROP TABLE IF EXISTS `const_stages_raw_materials`;

CREATE TABLE `const_stages_raw_materials` (
  `construction_stage_id` bigint(20) NOT NULL,
  `raw_materials_id` bigint(20) NOT NULL,
  KEY `FKr6afsty29w7n4ck9wkkmj8mqa` (`raw_materials_id`),
  KEY `FKsx1qfb5ydrv6cwy6lw0caicfp` (`construction_stage_id`),
  CONSTRAINT `FKr6afsty29w7n4ck9wkkmj8mqa` FOREIGN KEY (`raw_materials_id`) REFERENCES `raw_materials` (`id`),
  CONSTRAINT `FKsx1qfb5ydrv6cwy6lw0caicfp` FOREIGN KEY (`construction_stage_id`) REFERENCES `const_stages` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `const_stages_raw_materials` */

/*Table structure for table `const_stages_workers` */

DROP TABLE IF EXISTS `const_stages_workers`;

CREATE TABLE `const_stages_workers` (
  `construction_stage_id` bigint(20) NOT NULL,
  `workers_id` bigint(20) NOT NULL,
  KEY `FK9u128qyk9b1gkxgerqm4o07f7` (`workers_id`),
  KEY `FKnnrc1q4hw0ixfu78jg818targ` (`construction_stage_id`),
  CONSTRAINT `FK9u128qyk9b1gkxgerqm4o07f7` FOREIGN KEY (`workers_id`) REFERENCES `workers` (`id`),
  CONSTRAINT `FKnnrc1q4hw0ixfu78jg818targ` FOREIGN KEY (`construction_stage_id`) REFERENCES `const_stages` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `const_stages_workers` */

insert  into `const_stages_workers`(`construction_stage_id`,`workers_id`) values 
(1,1),
(1,2),
(1,3);

/*Table structure for table `const_units` */

DROP TABLE IF EXISTS `const_units`;

CREATE TABLE `const_units` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` double DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `status` enum('AVAILABLE','RESERVED','SOLD') DEFAULT NULL,
  `type` enum('APARTMENT','OFFICE','OTHER','SHOP') DEFAULT NULL,
  `floor_id` bigint(20) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbyv8qlr8bmpsx8tfj0suj6bku` (`floor_id`),
  CONSTRAINT `FKbyv8qlr8bmpsx8tfj0suj6bku` FOREIGN KEY (`floor_id`) REFERENCES `const_floors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `const_units` */

insert  into `const_units`(`id`,`company_id`,`name`,`price`,`size`,`status`,`type`,`floor_id`,`image`) values 
(1,1,'Unit 101',500000,120,'RESERVED','APARTMENT',1,'demo.jpg'),
(2,1,'TEST 234',1234,1234,'AVAILABLE','APARTMENT',1,'demo.jpg'),
(3,1,'TTT',123123,112,'SOLD','OFFICE',2,'demo.jpg');

/*Table structure for table `raw_material_orders` */

DROP TABLE IF EXISTS `raw_material_orders`;

CREATE TABLE `raw_material_orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `delivery_date` date DEFAULT NULL,
  `group_transaction_id` varchar(255) DEFAULT NULL,
  `order_date` date NOT NULL,
  `quantity` double NOT NULL,
  `status` tinyint(4) NOT NULL,
  `total_price` double NOT NULL,
  `unit_price` double NOT NULL,
  `raw_material_id` bigint(20) NOT NULL,
  `supplier_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsgkcuc3bwrh1vd9ypochdw8lo` (`raw_material_id`),
  KEY `FKoik5chwny4avv1loj1rbtcxt0` (`supplier_id`),
  CONSTRAINT `FKoik5chwny4avv1loj1rbtcxt0` FOREIGN KEY (`supplier_id`) REFERENCES `raw_material_suppliers` (`id`),
  CONSTRAINT `FKsgkcuc3bwrh1vd9ypochdw8lo` FOREIGN KEY (`raw_material_id`) REFERENCES `raw_materials` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `raw_material_orders` */

insert  into `raw_material_orders`(`id`,`company_id`,`delivery_date`,`group_transaction_id`,`order_date`,`quantity`,`status`,`total_price`,`unit_price`,`raw_material_id`,`supplier_id`) values 
(1,1,'2024-11-19','6022cd3c-8877-43d6-9b7c-5ea2cd0753ae','2024-11-09',1000,1,500000,500,1,1);

/*Table structure for table `raw_material_stocks` */

DROP TABLE IF EXISTS `raw_material_stocks`;

CREATE TABLE `raw_material_stocks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `last_updated` datetime(6) NOT NULL,
  `quantity` double NOT NULL,
  `raw_material_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1wys3qshmta5qlmmla31xjs3y` (`raw_material_id`),
  CONSTRAINT `FK1wys3qshmta5qlmmla31xjs3y` FOREIGN KEY (`raw_material_id`) REFERENCES `raw_materials` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `raw_material_stocks` */

insert  into `raw_material_stocks`(`id`,`company_id`,`last_updated`,`quantity`,`raw_material_id`) values 
(5,1,'2024-11-09 11:54:08.000000',880,1);

/*Table structure for table `raw_material_suppliers` */

DROP TABLE IF EXISTS `raw_material_suppliers`;

CREATE TABLE `raw_material_suppliers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `raw_material_suppliers` */

insert  into `raw_material_suppliers`(`id`,`company_id`,`name`) values 
(1,1,'TEST');

/*Table structure for table `raw_material_usage` */

DROP TABLE IF EXISTS `raw_material_usage`;

CREATE TABLE `raw_material_usage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `entry_date` date DEFAULT NULL,
  `quantity` double NOT NULL,
  `raw_material_id` bigint(20) NOT NULL,
  `stage_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6jrpa0t2qlu9p3v38mj5fjcwa` (`raw_material_id`),
  KEY `FKqmtfm3f4ult6hgkerykdv4bbi` (`stage_id`),
  CONSTRAINT `FK6jrpa0t2qlu9p3v38mj5fjcwa` FOREIGN KEY (`raw_material_id`) REFERENCES `raw_materials` (`id`),
  CONSTRAINT `FKqmtfm3f4ult6hgkerykdv4bbi` FOREIGN KEY (`stage_id`) REFERENCES `const_stages` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `raw_material_usage` */

insert  into `raw_material_usage`(`id`,`company_id`,`entry_date`,`quantity`,`raw_material_id`,`stage_id`) values 
(1,1,'2024-11-09',120,1,1);

/*Table structure for table `raw_materials` */

DROP TABLE IF EXISTS `raw_materials`;

CREATE TABLE `raw_materials` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `raw_materials` */

insert  into `raw_materials`(`id`,`company_id`,`name`) values 
(1,1,'Steel');

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sec_tokens` */

insert  into `sec_tokens`(`id`,`password`,`username`,`user_id`) values 
(1,'$2a$12$gQTOwzULa//tJJkAg1AxheGXw/tkhjP2vPto5pr6ciq5TF8vaRB2.','shabab',1),
(3,'$2a$12$gQTOwzULa//tJJkAg1AxheGXw/tkhjP2vPto5pr6ciq5TF8vaRB2.','employeeA',2),
(8,'$2a$12$gQTOwzULa//tJJkAg1AxheGXw/tkhjP2vPto5pr6ciq5TF8vaRB2.','managerA',5),
(9,'$2a$12$gQTOwzULa//tJJkAg1AxheGXw/tkhjP2vPto5pr6ciq5TF8vaRB2.','customerA',6);

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sec_users` */

insert  into `sec_users`(`id`,`address`,`avatar`,`blood_group`,`cell`,`date_of_birth`,`email`,`gender`,`joining_date`,`name`,`role`,`status`,`company_id`) values 
(1,'Dhaka','83783ceb-1143-4a24-b843-1dd3f4eb06ef.jpg','B+','01700000000',NULL,'test@test.com','Male',NULL,'Shabab Ahmed','ROLE_ADMIN','Active',1),
(2,NULL,'83783ceb-1143-4a24-b843-1dd3f4eb06ef.jpg',NULL,'01900000000',NULL,NULL,'Male',NULL,'Employee A','ROLE_EMPLOYEE','Active',1),
(5,NULL,'83783ceb-1143-4a24-b843-1dd3f4eb06ef.jpg',NULL,'01300000000',NULL,NULL,NULL,NULL,'Manager A','ROLE_MANAGER','Active',1),
(6,NULL,'83783ceb-1143-4a24-b843-1dd3f4eb06ef.jpg',NULL,'01500000000',NULL,NULL,'Male',NULL,'Customer A','ROLE_CUSTOMER','Active',1);

/*Table structure for table `tasks` */

DROP TABLE IF EXISTS `tasks`;

CREATE TABLE `tasks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `description` varchar(255) NOT NULL,
  `end_date` date NOT NULL,
  `start_date` date NOT NULL,
  `status` enum('COMPLETED','IN_PROGRESS','PENDING') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tasks` */

insert  into `tasks`(`id`,`company_id`,`description`,`end_date`,`start_date`,`status`) values 
(1,1,'Task A','2024-11-22','2024-11-16','PENDING');

/*Table structure for table `tasks_employees` */

DROP TABLE IF EXISTS `tasks_employees`;

CREATE TABLE `tasks_employees` (
  `task_id` bigint(20) NOT NULL,
  `employees_id` bigint(20) NOT NULL,
  KEY `FKp098impwy810oqv08wswhfw0s` (`employees_id`),
  KEY `FKmq840d193vlb004iwungs5atr` (`task_id`),
  CONSTRAINT `FKmq840d193vlb004iwungs5atr` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`id`),
  CONSTRAINT `FKp098impwy810oqv08wswhfw0s` FOREIGN KEY (`employees_id`) REFERENCES `sec_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `tasks_employees` */

insert  into `tasks_employees`(`task_id`,`employees_id`) values 
(1,1);

/*Table structure for table `worker_attendances` */

DROP TABLE IF EXISTS `worker_attendances`;

CREATE TABLE `worker_attendances` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `date` date NOT NULL,
  `status` enum('ABSENT','ON_LEAVE','PRESENT') DEFAULT NULL,
  `stage_id` bigint(20) NOT NULL,
  `worker_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9y1osevpn0kdo0myewkcdf3rf` (`stage_id`),
  KEY `FKd7r0b67mdkpqan2t6v51f8d1u` (`worker_id`),
  CONSTRAINT `FK9y1osevpn0kdo0myewkcdf3rf` FOREIGN KEY (`stage_id`) REFERENCES `const_stages` (`id`),
  CONSTRAINT `FKd7r0b67mdkpqan2t6v51f8d1u` FOREIGN KEY (`worker_id`) REFERENCES `workers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `worker_attendances` */

insert  into `worker_attendances`(`id`,`company_id`,`date`,`status`,`stage_id`,`worker_id`) values 
(1,1,'2024-11-09','PRESENT',1,1),
(2,1,'2024-11-09','ABSENT',1,2),
(3,1,'2024-11-09','PRESENT',1,3);

/*Table structure for table `workers` */

DROP TABLE IF EXISTS `workers`;

CREATE TABLE `workers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `blood_group` varchar(255) DEFAULT NULL,
  `cell` varchar(11) DEFAULT NULL,
  `company_id` bigint(20) NOT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `joining_date` date DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `salary` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `workers` */

insert  into `workers`(`id`,`address`,`avatar`,`blood_group`,`cell`,`company_id`,`gender`,`joining_date`,`name`,`salary`) values 
(1,'Dhaka',NULL,'A+','01900000000',1,'Male','2024-11-08','Worker A',1000),
(2,'Dhaka',NULL,NULL,'01900000000',1,NULL,NULL,'Worker B',1000),
(3,'Dhaka',NULL,NULL,'01900000000',1,NULL,NULL,'Worker C',1000);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
