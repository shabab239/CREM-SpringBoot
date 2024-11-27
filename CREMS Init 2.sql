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

/*Table structure for table `acc_accounts` */

DROP TABLE IF EXISTS `acc_accounts`;

CREATE TABLE `acc_accounts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `balance` double NOT NULL DEFAULT 0,
  `company_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `company` bigint(20) DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `worker_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKe3b6rbx7f7qle2llv6qy0wku9` (`company`),
  UNIQUE KEY `UK829a3ha7nypnbdeasr8yi53us` (`supplier_id`),
  UNIQUE KEY `UKau3wybk2dtgd0tdp8ww3d2ke0` (`user_id`),
  UNIQUE KEY `UK6e5wuxwn64dbqhfy1836khsur` (`worker_id`),
  CONSTRAINT `FKkai23dqjxfo2aih9aro3xxd4q` FOREIGN KEY (`supplier_id`) REFERENCES `raw_material_suppliers` (`id`),
  CONSTRAINT `FKkyb74coqypxkua451kwk3n312` FOREIGN KEY (`company`) REFERENCES `sec_companies` (`id`),
  CONSTRAINT `FKpfefedx3o24gy6sb16rghvcc2` FOREIGN KEY (`worker_id`) REFERENCES `workers` (`id`),
  CONSTRAINT `FKqklmmav9tuf5ssrkq60kmypmu` FOREIGN KEY (`user_id`) REFERENCES `sec_users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `acc_accounts` */

insert  into `acc_accounts`(`id`,`balance`,`company_id`,`name`,`company`,`supplier_id`,`user_id`,`worker_id`) values 
(1,56600,1,'ABC Supplier A/C',NULL,1,NULL,NULL),
(2,-56600,1,'ABC Real Estate Solutions Company A/C',1,NULL,NULL,NULL),
(3,0,1,'TEST Worker Worker A/C',NULL,NULL,NULL,1);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `acc_bookings` */

/*Table structure for table `acc_payments` */

DROP TABLE IF EXISTS `acc_payments`;

CREATE TABLE `acc_payments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `company_id` bigint(20) NOT NULL,
  `date` date NOT NULL,
  `group_transaction_id` varchar(255) DEFAULT NULL,
  `booking_id` bigint(20) NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmd3e5i9j3yns3l8fn0d3aso4i` (`booking_id`),
  KEY `FKbokqvpt9rcavhp4iljy17fqqt` (`customer_id`),
  CONSTRAINT `FKbokqvpt9rcavhp4iljy17fqqt` FOREIGN KEY (`customer_id`) REFERENCES `sec_users` (`id`),
  CONSTRAINT `FKmd3e5i9j3yns3l8fn0d3aso4i` FOREIGN KEY (`booking_id`) REFERENCES `acc_bookings` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `acc_payments` */

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `acc_transactions` */

insert  into `acc_transactions`(`id`,`amount`,`company_id`,`date`,`group_transaction_id`,`particular`,`type`,`account_id`) values 
(1,56600,1,'2024-11-23 18:30:49.000000','2ac45186-59c5-4c78-8821-2f2f3f5d4ac3','Raw Material Order - null','EXPENSE',2),
(2,56600,1,'2024-11-23 18:30:49.000000','2ac45186-59c5-4c78-8821-2f2f3f5d4ac3','Raw Material Order - null','INCOME',1);

/*Table structure for table `campaigns` */

DROP TABLE IF EXISTS `campaigns`;

CREATE TABLE `campaigns` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `start_date` date DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') NOT NULL,
  `type` enum('EMAIL','NEWS','SOCIAL_MEDIA') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `campaigns` */

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `const_buildings` */

insert  into `const_buildings`(`id`,`company_id`,`name`,`type`,`project_id`) values 
(1,1,'Building A','RESIDENTIAL',1);

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `const_floors` */

insert  into `const_floors`(`id`,`company_id`,`name`,`building_id`) values 
(1,1,'BASEMENT',1),
(2,1,'GROUND',1),
(3,1,'FIRST',1),
(4,1,'SECOND',1);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `const_projects` */

insert  into `const_projects`(`id`,`budget`,`company_id`,`description`,`end_date`,`location`,`name`,`start_date`,`status`,`manager_id`) values 
(1,15000000,1,'TEST',NULL,'Dhaka','Project A','2024-11-30','PLANNING',1);

/*Table structure for table `const_projects_team_members` */

DROP TABLE IF EXISTS `const_projects_team_members`;

CREATE TABLE `const_projects_team_members` (
  `project_id` bigint(20) NOT NULL,
  `team_members_id` bigint(20) NOT NULL,
  KEY `FKfxcfctfq6g7f3jntf28qvn2xy` (`team_members_id`),
  KEY `FKg9g6rm0g4op1b9jdqc73ecbrp` (`project_id`),
  CONSTRAINT `FKfxcfctfq6g7f3jntf28qvn2xy` FOREIGN KEY (`team_members_id`) REFERENCES `sec_users` (`id`),
  CONSTRAINT `FKg9g6rm0g4op1b9jdqc73ecbrp` FOREIGN KEY (`project_id`) REFERENCES `const_projects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `const_projects_team_members` */

insert  into `const_projects_team_members`(`project_id`,`team_members_id`) values 
(1,2),
(1,3),
(1,4),
(1,5),
(1,6),
(1,7),
(1,8);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `const_stages` */

insert  into `const_stages`(`id`,`company_id`,`end_date`,`name`,`start_date`,`status`,`building_id`,`floor_id`,`unit_id`) values 
(1,1,'2024-11-29','Slab','2024-11-24','NOT_STARTED',NULL,NULL,1),
(2,1,'2024-11-21','Piling','2024-11-16','IN_PROGRESS',NULL,NULL,1);

/*Table structure for table `const_stages_raw_materials` */

DROP TABLE IF EXISTS `const_stages_raw_materials`;

CREATE TABLE `const_stages_raw_materials` (
  `construction_stage_id` bigint(20) NOT NULL,
  `raw_materials_id` bigint(20) NOT NULL,
  KEY `FKr6afsty29w7n4ck9wkkmj8mqa` (`raw_materials_id`),
  KEY `FKsx1qfb5ydrv6cwy6lw0caicfp` (`construction_stage_id`),
  CONSTRAINT `FKr6afsty29w7n4ck9wkkmj8mqa` FOREIGN KEY (`raw_materials_id`) REFERENCES `raw_materials` (`id`),
  CONSTRAINT `FKsx1qfb5ydrv6cwy6lw0caicfp` FOREIGN KEY (`construction_stage_id`) REFERENCES `const_stages` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `const_stages_workers` */

insert  into `const_stages_workers`(`construction_stage_id`,`workers_id`) values 
(2,1);

/*Table structure for table `const_units` */

DROP TABLE IF EXISTS `const_units`;

CREATE TABLE `const_units` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `price` double DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `status` enum('AVAILABLE','RESERVED','SOLD') DEFAULT NULL,
  `type` enum('APARTMENT','OFFICE','OTHER','SHOP') DEFAULT NULL,
  `floor_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbyv8qlr8bmpsx8tfj0suj6bku` (`floor_id`),
  CONSTRAINT `FKbyv8qlr8bmpsx8tfj0suj6bku` FOREIGN KEY (`floor_id`) REFERENCES `const_floors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `const_units` */

insert  into `const_units`(`id`,`company_id`,`image`,`name`,`price`,`size`,`status`,`type`,`floor_id`) values 
(1,1,NULL,'Unit G101',500000,300,'AVAILABLE','SHOP',2);

/*Table structure for table `conversations` */

DROP TABLE IF EXISTS `conversations`;

CREATE TABLE `conversations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `date` date DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `follow_up_date` date DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `employee_id` bigint(20) DEFAULT NULL,
  `lead_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5gffsbmrw03u85esodm0jdc1a` (`customer_id`),
  KEY `FKp63orpdfecdt98kelkin8kduc` (`employee_id`),
  KEY `FKfmije2ojlqu87o4nj39e45que` (`lead_id`),
  CONSTRAINT `FK5gffsbmrw03u85esodm0jdc1a` FOREIGN KEY (`customer_id`) REFERENCES `sec_users` (`id`),
  CONSTRAINT `FKfmije2ojlqu87o4nj39e45que` FOREIGN KEY (`lead_id`) REFERENCES `leads` (`id`),
  CONSTRAINT `FKp63orpdfecdt98kelkin8kduc` FOREIGN KEY (`employee_id`) REFERENCES `sec_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `conversations` */

/*Table structure for table `leads` */

DROP TABLE IF EXISTS `leads`;

CREATE TABLE `leads` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `contact_info` varchar(255) NOT NULL,
  `interest` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `source` varchar(255) DEFAULT NULL,
  `status` enum('CONTACTED','CONVERTED','INTERESTED','LOST','NEW','NOT_INTERESTED') NOT NULL,
  `campaign_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf5okqmm1apco959009ic8ptnr` (`campaign_id`),
  CONSTRAINT `FKf5okqmm1apco959009ic8ptnr` FOREIGN KEY (`campaign_id`) REFERENCES `campaigns` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `leads` */

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `raw_material_orders` */

insert  into `raw_material_orders`(`id`,`company_id`,`delivery_date`,`group_transaction_id`,`order_date`,`quantity`,`status`,`total_price`,`unit_price`,`raw_material_id`,`supplier_id`) values 
(1,1,'2024-11-24','2ac45186-59c5-4c78-8821-2f2f3f5d4ac3','2024-11-23',100,1,56600,566,1,1);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `raw_material_stocks` */

insert  into `raw_material_stocks`(`id`,`company_id`,`last_updated`,`quantity`,`raw_material_id`) values 
(1,1,'2024-11-23 18:31:12.000000',88,1);

/*Table structure for table `raw_material_suppliers` */

DROP TABLE IF EXISTS `raw_material_suppliers`;

CREATE TABLE `raw_material_suppliers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `raw_material_suppliers` */

insert  into `raw_material_suppliers`(`id`,`company_id`,`name`) values 
(1,1,'ABC');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `raw_material_usage` */

insert  into `raw_material_usage`(`id`,`company_id`,`entry_date`,`quantity`,`raw_material_id`,`stage_id`) values 
(1,1,'2024-11-23',12,1,2);

/*Table structure for table `raw_materials` */

DROP TABLE IF EXISTS `raw_materials`;

CREATE TABLE `raw_materials` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `raw_materials` */

insert  into `raw_materials`(`id`,`company_id`,`name`) values 
(1,1,'Piling Equipment');

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
(1,'Dhaka','01700000000','ABC Real Estate Solutions');

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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sec_tokens` */

insert  into `sec_tokens`(`id`,`password`,`username`,`user_id`) values 
(1,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','customer1',1),
(2,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','customer2',2),
(3,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','customer3',3),
(4,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','customer4',4),
(5,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','customer5',5),
(6,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','customer6',6),
(7,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','customer7',7),
(8,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','customer8',8),
(9,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','customer9',9),
(10,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','customer10',10),
(11,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','owner1',11),
(12,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','owner2',12),
(13,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','employee1',13),
(14,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','employee2',14),
(15,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','employee3',15),
(16,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','employee4',16),
(17,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','employee5',17),
(18,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','employee6',18),
(19,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','employee7',19),
(20,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','employee8',20),
(21,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','employee9',21),
(22,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','employee10',22),
(23,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','employee11',23),
(24,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','employee12',24),
(25,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','employee13',25),
(26,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','employee14',26),
(27,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','employee15',27),
(28,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','employee16',28),
(29,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','manager1',29),
(30,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','manager2',30),
(31,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','manager3',31),
(32,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','manager4',32),
(33,'$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2','admin1',33);

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
  `status` enum('ACTIVE','INACTIVE','LOCKED') NOT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKjp9f1xtuq2lpe6ky4me6yib1w` (`cell`),
  UNIQUE KEY `UKcfu0ko0i9l8afdu520rvtf318` (`email`),
  KEY `FK63iggr2p3ojqe8sioi8lp9dm9` (`company_id`),
  CONSTRAINT `FK63iggr2p3ojqe8sioi8lp9dm9` FOREIGN KEY (`company_id`) REFERENCES `sec_companies` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sec_users` */

insert  into `sec_users`(`id`,`address`,`avatar`,`blood_group`,`cell`,`date_of_birth`,`email`,`gender`,`joining_date`,`name`,`role`,`status`,`company_id`) values 
(1,'123 Main St, Cityville',NULL,'O+','01700000001','1990-05-15','customer1@company.com','Male','2020-01-01','John Doe','ROLE_CUSTOMER','ACTIVE',1),
(2,'456 Elm St, Cityville',NULL,'A+','01700000002','1985-08-20','customer2@company.com','Female','2020-02-01','Jane Smith','ROLE_CUSTOMER','ACTIVE',1),
(3,'789 Pine St, Cityville',NULL,'B+','01700000003','1992-11-12','customer3@company.com','Male','2021-03-01','Mike Johnson','ROLE_CUSTOMER','ACTIVE',1),
(4,'321 Oak St, Cityville',NULL,'AB+','01700000004','1991-02-22','customer4@company.com','Female','2021-04-01','Emily Davis','ROLE_CUSTOMER','ACTIVE',1),
(5,'654 Maple St, Cityville',NULL,'O-','01700000005','1993-04-18','customer5@company.com','Male','2021-05-01','David Clark','ROLE_CUSTOMER','ACTIVE',1),
(6,'987 Cedar St, Cityville',NULL,'A-','01700000006','1994-07-25','customer6@company.com','Female','2021-06-01','Sophia Walker','ROLE_CUSTOMER','ACTIVE',1),
(7,'123 Birch St, Cityville',NULL,'B-','01700000007','1990-09-12','customer7@company.com','Male','2020-07-01','James Allen','ROLE_CUSTOMER','ACTIVE',1),
(8,'456 Pine St, Cityville',NULL,'O+','01700000008','1995-10-05','customer8@company.com','Female','2021-08-01','Olivia Taylor','ROLE_CUSTOMER','ACTIVE',1),
(9,'789 Oak St, Cityville',NULL,'AB-','01700000009','1992-12-30','customer9@company.com','Male','2021-09-01','Liam Thomas','ROLE_CUSTOMER','ACTIVE',1),
(10,'321 Maple St, Cityville',NULL,'A+','01700000010','1996-11-20','customer10@company.com','Female','2021-10-01','Emma Harris','ROLE_CUSTOMER','ACTIVE',1),
(11,'111 High St, Cityville',NULL,'O+','01700000011','1980-01-01','owner1@company.com','Male','2015-01-01','Johnathan Black','ROLE_OWNER','ACTIVE',1),
(12,'222 Low St, Cityville',NULL,'B-','01700000012','1975-03-15','owner2@company.com','Female','2016-03-01','Anna White','ROLE_OWNER','ACTIVE',1),
(13,'111 First St, Cityville',NULL,'AB+','01700000013','1994-05-10','employee1@company.com','Male','2021-01-01','Tom Green','ROLE_EMPLOYEE','ACTIVE',1),
(14,'222 Second St, Cityville',NULL,'O-','01700000014','1991-07-22','employee2@company.com','Female','2021-02-01','Sophia Lee','ROLE_EMPLOYEE','ACTIVE',1),
(15,'333 Third St, Cityville',NULL,'A+','01700000015','1993-03-17','employee3@company.com','Male','2021-03-01','Daniel Brown','ROLE_EMPLOYEE','ACTIVE',1),
(16,'444 Fourth St, Cityville',NULL,'B+','01700000016','1992-06-10','employee4@company.com','Female','2021-04-01','Olivia White','ROLE_EMPLOYEE','ACTIVE',1),
(17,'555 Fifth St, Cityville',NULL,'O+','01700000017','1990-05-12','employee5@company.com','Male','2021-05-01','Luke Harris','ROLE_EMPLOYEE','ACTIVE',1),
(18,'666 Sixth St, Cityville',NULL,'A-','01700000018','1992-09-23','employee6@company.com','Female','2021-06-01','Mia Lewis','ROLE_EMPLOYEE','ACTIVE',1),
(19,'777 Seventh St, Cityville',NULL,'B-','01700000019','1994-02-18','employee7@company.com','Male','2021-07-01','Liam Carter','ROLE_EMPLOYEE','ACTIVE',1),
(20,'888 Eighth St, Cityville',NULL,'AB-','01700000020','1995-12-14','employee8@company.com','Female','2021-08-01','Emma Adams','ROLE_EMPLOYEE','ACTIVE',1),
(21,'999 Ninth St, Cityville',NULL,'O+','01700000021','1991-03-22','employee9@company.com','Male','2021-09-01','James Miller','ROLE_EMPLOYEE','ACTIVE',1),
(22,'1010 Tenth St, Cityville',NULL,'A+','01700000022','1993-10-19','employee10@company.com','Female','2021-10-01','Emily Clark','ROLE_EMPLOYEE','ACTIVE',1),
(23,'1111 Eleventh St, Cityville',NULL,'B+','01700000023','1994-08-03','employee11@company.com','Male','2021-11-01','Joshua King','ROLE_EMPLOYEE','ACTIVE',1),
(24,'1212 Twelfth St, Cityville',NULL,'O-','01700000024','1992-07-16','employee12@company.com','Female','2021-12-01','Isabella Turner','ROLE_EMPLOYEE','ACTIVE',1),
(25,'1313 Thirteenth St, Cityville',NULL,'AB+','01700000025','1990-04-05','employee13@company.com','Male','2022-01-01','Michael Scott','ROLE_EMPLOYEE','ACTIVE',1),
(26,'1414 Fourteenth St, Cityville',NULL,'A-','01700000026','1995-01-13','employee14@company.com','Female','2022-02-01','Sophia Robinson','ROLE_EMPLOYEE','ACTIVE',1),
(27,'1515 Fifteenth St, Cityville',NULL,'B-','01700000027','1996-09-25','employee15@company.com','Male','2022-03-01','William White','ROLE_EMPLOYEE','ACTIVE',1),
(28,'1616 Sixteenth St, Cityville',NULL,'O+','01700000028','1993-11-30','employee16@company.com','Female','2022-04-01','Charlotte Moore','ROLE_EMPLOYEE','ACTIVE',1),
(29,'111 Manager St, Cityville',NULL,'AB+','01700000029','1985-02-01','manager1@company.com','Male','2018-01-01','John King','ROLE_MANAGER','ACTIVE',1),
(30,'222 Manager St, Cityville',NULL,'O-','01700000030','1980-09-10','manager2@company.com','Female','2019-03-01','Sarah Evans','ROLE_MANAGER','ACTIVE',1),
(31,'333 Manager St, Cityville',NULL,'A+','01700000031','1987-07-17','manager3@company.com','Male','2020-01-01','Michael Carter','ROLE_MANAGER','ACTIVE',1),
(32,'444 Manager St, Cityville',NULL,'B+','01700000032','1983-12-25','manager4@company.com','Female','2021-02-01','Jessica Taylor','ROLE_MANAGER','ACTIVE',1),
(33,'Dhaka',NULL,'B+','01700000000','2000-09-28','admin@company.com','Male','2020-01-01','Shabab Ahmed','ROLE_ADMIN','ACTIVE',1);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tasks` */

insert  into `tasks`(`id`,`company_id`,`description`,`end_date`,`start_date`,`status`) values 
(1,1,'Get workers and materials of piling to site','2024-11-24','2024-11-24','PENDING'),
(2,1,'Brief workers on their roles and commence','2024-11-25','2024-11-25','PENDING');

/*Table structure for table `tasks_employees` */

DROP TABLE IF EXISTS `tasks_employees`;

CREATE TABLE `tasks_employees` (
  `task_id` bigint(20) NOT NULL,
  `employees_id` bigint(20) NOT NULL,
  KEY `FKp098impwy810oqv08wswhfw0s` (`employees_id`),
  KEY `FKmq840d193vlb004iwungs5atr` (`task_id`),
  CONSTRAINT `FKmq840d193vlb004iwungs5atr` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`id`),
  CONSTRAINT `FKp098impwy810oqv08wswhfw0s` FOREIGN KEY (`employees_id`) REFERENCES `sec_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tasks_employees` */

insert  into `tasks_employees`(`task_id`,`employees_id`) values 
(1,1),
(2,2);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `worker_attendances` */

insert  into `worker_attendances`(`id`,`company_id`,`date`,`status`,`stage_id`,`worker_id`) values 
(1,1,'2024-11-23',NULL,2,1);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `workers` */

insert  into `workers`(`id`,`address`,`avatar`,`blood_group`,`cell`,`company_id`,`gender`,`joining_date`,`name`,`salary`) values 
(1,NULL,NULL,NULL,'01710295968',1,NULL,NULL,'TEST Worker',500);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;