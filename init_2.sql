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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `acc_accounts` */

insert  into `acc_accounts`(`id`,`balance`,`company_id`,`name`) values 
(1,0,1,'Shabab Ahmed Cash A/C'),
(2,0,1,'Supplier A Cash A/C'),
(3,0,1,'Supplier B Cash A/C'),
(4,0,1,'Customer A Cash A/C'),
(5,0,1,'Custoemr B Cash A/C'),
(6,0,1,'Supplier C Cash A/C');

/*Table structure for table `const_bookings` */

DROP TABLE IF EXISTS `const_bookings`;

CREATE TABLE `const_bookings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `booking_date` date NOT NULL,
  `company_id` bigint(20) NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  `unit_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKjqmiak06i9qhm7jfu6g4rd8nj` (`unit_id`),
  KEY `FKsso3ow1x4ttcve4qp1jy7cwro` (`customer_id`),
  CONSTRAINT `FKbed6mati72ql0w3si4sqr3ejc` FOREIGN KEY (`unit_id`) REFERENCES `core_units` (`id`),
  CONSTRAINT `FKsso3ow1x4ttcve4qp1jy7cwro` FOREIGN KEY (`customer_id`) REFERENCES `sec_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `const_bookings` */

/*Table structure for table `const_payments` */

DROP TABLE IF EXISTS `const_payments`;

CREATE TABLE `const_payments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `company_id` bigint(20) NOT NULL,
  `date` date NOT NULL,
  `group_transaction_id` varchar(255) NOT NULL,
  `booking_id` bigint(20) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3lbynphwnsv6i7q8i5k6vvnig` (`booking_id`),
  KEY `FKcpq6kgyxmr7ss2eq2hutuvcsg` (`customer_id`),
  CONSTRAINT `FK3lbynphwnsv6i7q8i5k6vvnig` FOREIGN KEY (`booking_id`) REFERENCES `const_bookings` (`id`),
  CONSTRAINT `FKcpq6kgyxmr7ss2eq2hutuvcsg` FOREIGN KEY (`customer_id`) REFERENCES `sec_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `const_payments` */

/*Table structure for table `const_raw_material_orders` */

DROP TABLE IF EXISTS `const_raw_material_orders`;

CREATE TABLE `const_raw_material_orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `delivery_date` date DEFAULT NULL,
  `order_date` date NOT NULL,
  `quantity` double NOT NULL,
  `status` tinyint(4) NOT NULL,
  `total_price` double NOT NULL,
  `unit_price` double NOT NULL,
  `raw_material_id` bigint(20) NOT NULL,
  `supplier_id` bigint(20) NOT NULL,
  `group_transaction_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtoqtegg3rmljuo8158vy0v6t0` (`raw_material_id`),
  KEY `FK7av56umc0cgd5oqu4883qqhbc` (`supplier_id`),
  CONSTRAINT `FK7av56umc0cgd5oqu4883qqhbc` FOREIGN KEY (`supplier_id`) REFERENCES `const_suppliers` (`id`),
  CONSTRAINT `FKtoqtegg3rmljuo8158vy0v6t0` FOREIGN KEY (`raw_material_id`) REFERENCES `const_raw_materials` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `const_raw_material_orders` */

/*Table structure for table `const_raw_material_stocks` */

DROP TABLE IF EXISTS `const_raw_material_stocks`;

CREATE TABLE `const_raw_material_stocks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `last_updated` datetime(6) NOT NULL,
  `quantity` double NOT NULL,
  `raw_material_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKth6nctptbthmo7qsb6376rnfq` (`raw_material_id`),
  CONSTRAINT `FKth6nctptbthmo7qsb6376rnfq` FOREIGN KEY (`raw_material_id`) REFERENCES `const_raw_materials` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `const_raw_material_stocks` */

/*Table structure for table `const_raw_material_usage` */

DROP TABLE IF EXISTS `const_raw_material_usage`;

CREATE TABLE `const_raw_material_usage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `entry_date` date DEFAULT NULL,
  `quantity` double NOT NULL,
  `raw_material_id` bigint(20) NOT NULL,
  `stage_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK50nrrs2wd5lkw83juoe0543dm` (`raw_material_id`),
  KEY `FKomf8lwgc5huse7o53bewj0cc2` (`stage_id`),
  CONSTRAINT `FK50nrrs2wd5lkw83juoe0543dm` FOREIGN KEY (`raw_material_id`) REFERENCES `const_raw_materials` (`id`),
  CONSTRAINT `FKomf8lwgc5huse7o53bewj0cc2` FOREIGN KEY (`stage_id`) REFERENCES `const_stages` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `const_raw_material_usage` */

/*Table structure for table `const_raw_materials` */

DROP TABLE IF EXISTS `const_raw_materials`;

CREATE TABLE `const_raw_materials` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `const_raw_materials` */

insert  into `const_raw_materials`(`id`,`company_id`,`name`) values 
(1,1,'Sand'),
(2,1,'Brick'),
(3,1,'Rod'),
(4,1,'Cement');

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
  KEY `FKd103821kalv27jd3u83f9ht2p` (`building_id`),
  KEY `FKhq2uqw5u7qgcapu42rxtcmoa9` (`floor_id`),
  KEY `FKg4rang1wy6vhge97t8gsyaxh7` (`unit_id`),
  CONSTRAINT `FKd103821kalv27jd3u83f9ht2p` FOREIGN KEY (`building_id`) REFERENCES `core_buildings` (`id`),
  CONSTRAINT `FKg4rang1wy6vhge97t8gsyaxh7` FOREIGN KEY (`unit_id`) REFERENCES `core_units` (`id`),
  CONSTRAINT `FKhq2uqw5u7qgcapu42rxtcmoa9` FOREIGN KEY (`floor_id`) REFERENCES `core_floors` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `const_stages` */

/*Table structure for table `const_stages_raw_materials` */

DROP TABLE IF EXISTS `const_stages_raw_materials`;

CREATE TABLE `const_stages_raw_materials` (
  `construction_stage_id` bigint(20) NOT NULL,
  `raw_materials_id` bigint(20) NOT NULL,
  KEY `FKdkfg9wlb0j5avh5jinli0ob15` (`raw_materials_id`),
  KEY `FKsx1qfb5ydrv6cwy6lw0caicfp` (`construction_stage_id`),
  CONSTRAINT `FKdkfg9wlb0j5avh5jinli0ob15` FOREIGN KEY (`raw_materials_id`) REFERENCES `const_raw_materials` (`id`),
  CONSTRAINT `FKsx1qfb5ydrv6cwy6lw0caicfp` FOREIGN KEY (`construction_stage_id`) REFERENCES `const_stages` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `const_stages_raw_materials` */

/*Table structure for table `const_stages_workers` */

DROP TABLE IF EXISTS `const_stages_workers`;

CREATE TABLE `const_stages_workers` (
  `construction_stage_id` bigint(20) NOT NULL,
  `workers_id` bigint(20) NOT NULL,
  KEY `FKo33a6e34qe84rkg8vcs8qprp3` (`workers_id`),
  KEY `FKnnrc1q4hw0ixfu78jg818targ` (`construction_stage_id`),
  CONSTRAINT `FKnnrc1q4hw0ixfu78jg818targ` FOREIGN KEY (`construction_stage_id`) REFERENCES `const_stages` (`id`),
  CONSTRAINT `FKo33a6e34qe84rkg8vcs8qprp3` FOREIGN KEY (`workers_id`) REFERENCES `sec_workers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `const_stages_workers` */

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `const_suppliers` */

insert  into `const_suppliers`(`id`,`company_id`,`name`,`account_id`) values 
(1,1,'Supplier A',2),
(2,1,'Supplier B',3),
(3,1,'Supplier C',6);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `core_buildings` */

insert  into `core_buildings`(`id`,`company_id`,`name`,`type`,`project_id`) values 
(1,1,'Building A','MIXED_USE',1),
(2,1,'Building A-2','COMMERCIAL',1);

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `core_floors` */

insert  into `core_floors`(`id`,`company_id`,`name`,`building_id`) values 
(1,1,'BASEMENT',1),
(2,1,'GROUND',1),
(3,1,'FIRST',1),
(4,1,'SECOND',1),
(5,1,'THIRD',1),
(6,1,'FOURTH',1);

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `core_projects` */

insert  into `core_projects`(`id`,`budget`,`company_id`,`description`,`end_date`,`location`,`name`,`start_date`,`status`,`manager_id`) values 
(1,15000000,1,'Multi Purpose','2024-12-31','Dhaka','Project A','2024-10-01','IN_PROGRESS',1),
(3,15000000,1,'Multi Purpose','2024-10-30','Dhaka','Project B','2024-10-01','COMPLETED',1),
(4,15000000,1,'Multi Purpose',NULL,'Dhaka','Project C',NULL,'PLANNING',1);

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

/*Table structure for table `core_units` */

DROP TABLE IF EXISTS `core_units`;

CREATE TABLE `core_units` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `status` enum('AVAILABLE','RESERVED','SOLD') DEFAULT NULL,
  `type` enum('APARTMENT','OFFICE','OTHER','SHOP') DEFAULT NULL,
  `floor_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKeyvtk2j1j9hwnf81p5856b2pj` (`floor_id`),
  CONSTRAINT `FKeyvtk2j1j9hwnf81p5856b2pj` FOREIGN KEY (`floor_id`) REFERENCES `core_floors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `core_units` */

insert  into `core_units`(`id`,`company_id`,`name`,`price`,`size`,`status`,`type`,`floor_id`) values 
(1,1,'Unit 201',700000,500,'AVAILABLE','OFFICE',4);

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

/*Table structure for table `sec_workers` */

DROP TABLE IF EXISTS `sec_workers`;

CREATE TABLE `sec_workers` (
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sec_workers` */

insert  into `sec_workers`(`id`,`address`,`avatar`,`blood_group`,`cell`,`company_id`,`gender`,`joining_date`,`name`,`salary`) values 
(1,'123 Elm Street','avatar/77ed92de-3def-4bb1-bc9c-c31decc2ad1f.png','B+','01777777777',1,'Male','2024-10-02','Worker A',500),
(2,'123 Elm Street',NULL,NULL,'01777777777',1,NULL,NULL,'Worker B',500),
(3,'123 Elm Street',NULL,NULL,'01777777777',1,NULL,NULL,'Worker C',500),
(4,'123 Elm Street',NULL,NULL,'01777777777',1,NULL,NULL,'Worker D',500),
(5,'123 Elm Street',NULL,NULL,'01777777777',1,NULL,NULL,'Worker E',500),
(6,'123 Elm Street',NULL,NULL,'01777777777',1,NULL,NULL,'Worker F',500);

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
  KEY `FKdrhpm3mnr47xapwtg5a9cvrs6` (`worker_id`),
  CONSTRAINT `FK9y1osevpn0kdo0myewkcdf3rf` FOREIGN KEY (`stage_id`) REFERENCES `const_stages` (`id`),
  CONSTRAINT `FKdrhpm3mnr47xapwtg5a9cvrs6` FOREIGN KEY (`worker_id`) REFERENCES `sec_workers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `worker_attendances` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
