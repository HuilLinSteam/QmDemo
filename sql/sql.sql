/*
SQLyog Enterprise v12.08 (32 bit)
MySQL - 5.6.41 : Database - ssms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ssms` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `ssms`;

/*Table structure for table `clazz` */

DROP TABLE IF EXISTS `clazz`;

CREATE TABLE `clazz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `gradeid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gid_clazz_FK` (`gradeid`),
  CONSTRAINT `gradeid_clazz_FK` FOREIGN KEY (`gradeid`) REFERENCES `grade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `clazz` */

insert  into `clazz`(`id`,`name`,`gradeid`) values (1,'1班',1),(2,'2班',1),(3,'3班',1),(4,'4班',1),(5,'1班',2),(6,'2班',2),(7,'3班',2),(8,'4班',2),(9,'1班',3),(11,'3班',3),(12,'4班',3),(13,'5班',3),(16,'1班',4),(17,'2班',4);

/*Table structure for table `clazz_course_teacher` */

DROP TABLE IF EXISTS `clazz_course_teacher`;

CREATE TABLE `clazz_course_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clazzid` int(11) DEFAULT NULL,
  `gradeid` int(11) DEFAULT NULL,
  `courseid` int(11) DEFAULT NULL,
  `teacherid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `clazzid_cct_FK` (`clazzid`),
  KEY `tid_cct_FK` (`teacherid`),
  KEY `courseid_cct_FK` (`courseid`),
  KEY `gradeid_cct_FK` (`gradeid`),
  CONSTRAINT `clazzid_cct_FK` FOREIGN KEY (`clazzid`) REFERENCES `clazz` (`id`),
  CONSTRAINT `teacherid_cct_FK` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `clazz_course_teacher` */

insert  into `clazz_course_teacher`(`id`,`clazzid`,`gradeid`,`courseid`,`teacherid`) values (16,3,1,2,14),(17,1,1,2,14),(19,1,1,4,9),(20,1,1,5,8),(21,2,1,5,8);

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `course` */

insert  into `course`(`id`,`name`) values (1,'语文'),(2,'数学'),(3,'英语'),(4,'物理'),(5,'化学'),(6,'生物'),(7,'历史'),(8,'政治'),(9,'计算机'),(10,'体育');

/*Table structure for table `escore` */

DROP TABLE IF EXISTS `escore`;

CREATE TABLE `escore` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `examid` int(11) DEFAULT NULL,
  `clazzid` int(11) DEFAULT NULL,
  `studentid` int(11) DEFAULT NULL,
  `gradeid` int(11) DEFAULT NULL,
  `courseid` int(11) DEFAULT NULL,
  `score` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `eid_escore_FK` (`examid`),
  KEY `sid_escore_FK` (`studentid`),
  KEY `clazzid_escore_FK` (`clazzid`),
  KEY `courseid_escore_FK` (`courseid`),
  KEY `gradeid_escore_FK` (`gradeid`),
  CONSTRAINT `clazzid_escore_FK` FOREIGN KEY (`clazzid`) REFERENCES `clazz` (`id`),
  CONSTRAINT `courseid_escore_FK` FOREIGN KEY (`courseid`) REFERENCES `grade_course` (`courseid`),
  CONSTRAINT `examid_escore_FK` FOREIGN KEY (`examid`) REFERENCES `exam` (`id`),
  CONSTRAINT `gradeid_escore_FK` FOREIGN KEY (`gradeid`) REFERENCES `grade_course` (`gradeid`),
  CONSTRAINT `studentid_escore_FK` FOREIGN KEY (`studentid`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1329 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `escore` */

insert  into `escore`(`id`,`examid`,`clazzid`,`studentid`,`gradeid`,`courseid`,`score`) values (614,39,1,1,1,3,NULL),(615,39,1,2,1,3,NULL),(616,39,1,3,1,3,NULL),(617,39,1,4,1,3,NULL),(618,39,1,5,1,3,NULL),(619,39,1,6,1,3,NULL),(620,39,1,7,1,3,NULL),(621,39,1,8,1,3,NULL),(622,39,1,9,1,3,NULL),(623,39,1,10,1,3,NULL),(624,39,1,11,1,3,NULL),(625,39,1,12,1,3,NULL),(626,39,1,13,1,3,NULL),(627,40,3,23,1,2,NULL),(628,40,3,24,1,2,NULL),(629,40,3,25,1,2,NULL),(630,40,3,26,1,2,NULL),(631,40,3,27,1,2,NULL),(632,40,3,28,1,2,NULL),(633,41,2,14,1,5,NULL),(634,41,2,15,1,5,NULL),(635,41,2,16,1,5,NULL),(636,41,2,17,1,5,NULL),(637,41,2,18,1,5,NULL),(638,41,2,19,1,5,NULL),(639,41,2,20,1,5,NULL),(640,41,2,21,1,5,NULL),(641,41,2,22,1,5,NULL),(642,42,1,1,1,5,120),(643,42,1,2,1,5,0),(644,42,1,3,1,5,0),(645,42,1,4,1,5,0),(646,42,1,5,1,5,0),(647,42,1,6,1,5,0),(648,42,1,7,1,5,0),(649,42,1,8,1,5,0),(650,42,1,9,1,5,0),(651,42,1,10,1,5,0),(652,42,1,11,1,5,0),(653,42,1,12,1,5,0),(654,42,1,13,1,5,0),(655,43,2,14,1,1,100),(656,43,2,15,1,1,0),(657,43,2,16,1,1,990),(658,43,2,17,1,1,0),(659,43,2,18,1,1,90),(660,43,2,19,1,1,0),(661,43,2,20,1,1,0),(662,43,2,21,1,1,0),(663,43,2,22,1,1,90),(664,44,1,1,1,1,NULL),(665,44,1,2,1,1,NULL),(666,44,1,3,1,1,NULL),(667,44,1,4,1,1,NULL),(668,44,1,5,1,1,NULL),(669,44,1,6,1,1,NULL),(670,44,1,7,1,1,NULL),(671,44,1,8,1,1,NULL),(672,44,1,9,1,1,NULL),(673,44,1,10,1,1,NULL),(674,44,1,11,1,1,NULL),(675,44,1,12,1,1,NULL),(676,44,1,13,1,1,NULL);

/*Table structure for table `exam` */

DROP TABLE IF EXISTS `exam`;

CREATE TABLE `exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `time` date DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` tinyint(2) DEFAULT '1',
  `gradeid` int(11) DEFAULT NULL,
  `clazzid` int(11) DEFAULT NULL,
  `courseid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gid_exam_FK` (`gradeid`),
  KEY `clazzid_exam_FK` (`clazzid`),
  CONSTRAINT `gradeid_exam_FK` FOREIGN KEY (`gradeid`) REFERENCES `grade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `exam` */

insert  into `exam`(`id`,`name`,`time`,`remark`,`type`,`gradeid`,`clazzid`,`courseid`) values (39,'第一次考试','2019-06-20','',2,1,1,3),(40,'第一次月考','2019-06-19','不准作弊啊！！',2,1,3,2),(41,'第一次考试','2019-06-20','',2,1,2,5),(42,'第二次考试','2019-06-21','',2,1,1,5),(43,'第一次考试','2019-06-24','',2,1,2,1),(44,'第2考试','2019-06-28','',2,1,1,1);

/*Table structure for table `grade` */

DROP TABLE IF EXISTS `grade`;

CREATE TABLE `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `grade` */

insert  into `grade`(`id`,`name`) values (1,'2016级'),(2,'2017级'),(3,'2018级'),(4,'2019级');

/*Table structure for table `grade_course` */

DROP TABLE IF EXISTS `grade_course`;

CREATE TABLE `grade_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gradeid` int(11) DEFAULT NULL,
  `courseid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gid_gc_FK` (`gradeid`),
  KEY `cid_gc_FK` (`courseid`),
  CONSTRAINT `courseid_gc_FK` FOREIGN KEY (`courseid`) REFERENCES `course` (`id`),
  CONSTRAINT `gradeid_gc_FK` FOREIGN KEY (`gradeid`) REFERENCES `grade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `grade_course` */

insert  into `grade_course`(`id`,`gradeid`,`courseid`) values (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,2,10),(7,2,9),(8,2,8),(9,2,1),(10,3,2),(11,3,5),(12,3,7),(13,3,8),(14,4,1);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `sex` varchar(4) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `qq` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `clazzid` int(11) DEFAULT NULL,
  `gradeid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number_student_FK` (`number`),
  KEY `cid_stu_FK` (`clazzid`),
  KEY `grade_student_FK` (`gradeid`),
  CONSTRAINT `clazzid_student_FK` FOREIGN KEY (`clazzid`) REFERENCES `clazz` (`id`),
  CONSTRAINT `grade_student_FK` FOREIGN KEY (`gradeid`) REFERENCES `grade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `student` */

insert  into `student`(`id`,`number`,`name`,`sex`,`phone`,`qq`,`clazzid`,`gradeid`) values (1,'201601001','蒙奇','男','18345345612','252345',1,1),(2,'201601002','妮可罗宾','女','18342346345','345745',1,1),(3,'201601003','罗罗诺亚卓洛','男','16234574564','734577',1,1),(4,'201601004','托尼托尼乔巴','男','15436574765','3546634',1,1),(5,'201601005','娜美','女','15346235622','7453256',1,1),(6,'201601006','山治','男','16234514532','8456257',1,1),(7,'201601007','布鲁克','男','16345234664','7257346',1,1),(8,'201601008','乌索普','男','16236457676','8345756',1,1),(9,'201601009','弗兰奇','男','17346734768','23563457',1,1),(10,'201601010','娜菲鲁塔利薇薇','女','11452356234','235345',1,1),(11,'201601011','小鱿','男','17632878467','6235745',1,1),(12,'201601012','梅里号','男','15235543456','2352346',1,1),(13,'201601013','阳光号','男','15232342355','45236',1,1),(14,'201602001','马歇尔蒂奇','男','13928398784','89872874',2,1),(15,'201602002','范奥卡','男','13984728784','89878372',2,1),(16,'201602003','基萨斯巴加斯','男','13787287843','99893727',2,1),(17,'201602004','毒Q','男','18787238748','89387823',2,1),(18,'201602005','雨之希留','男','18398782744','82094987',2,1),(19,'201602006','卡特琳娜','女','16392784264','9793845',2,1),(20,'201602007','圣胡安恶狼','男','12787467593','89874873',2,1),(21,'201602008','巴克斯乔特','男','15249797297','89923832',2,1),(22,'201602009','阿巴罗','男','12746763859','98791235',2,1),(23,'201603001','汉库克','女','15234235688','12575643',3,1),(24,'201603002','桑达索尼娅','女','15236386674','2456568',3,1),(25,'201603003','玛丽哥鲁德','女','12364573467','2634681',3,1),(26,'201603004','玛格丽特','女','16353462367','23467436',3,1),(27,'201603005','艾弗兰德拉','女','11345235678','2352366',3,1),(28,'201603006','贝拉董娜','女','14523462567','7912635',3,1),(29,'201604001','白胡子','男',NULL,NULL,4,1),(30,'201604002','马尔高','男',NULL,NULL,4,1),(31,'201604003','艾斯','男',NULL,NULL,4,1),(32,'201604004','乔兹','男',NULL,NULL,4,1),(33,'201604005','萨奇','男',NULL,NULL,4,1),(34,'201604006','比斯塔','男',NULL,NULL,4,1),(35,'201604007','布拉曼克','男',NULL,NULL,4,1),(36,'201604008','拉克约','男',NULL,NULL,4,1),(37,'201604009','那谬尔','男',NULL,NULL,4,1),(38,'201604010','布伦海姆','男',NULL,NULL,4,1),(39,'201604011','库利艾尔','男',NULL,NULL,4,1),(40,'201604012','金古多','男',NULL,NULL,4,1),(41,'201604013','佛萨','男',NULL,NULL,4,1),(42,'201604014','斯比多基尔','男',NULL,NULL,4,1),(43,'201701001','日向雏田','女',NULL,NULL,5,2),(44,'201701002','李洛克','男',NULL,NULL,5,2),(45,'201701003','日向花火','女',NULL,NULL,5,2),(46,'201701004','奈良鹿丸','男',NULL,NULL,5,2),(47,'201701005','日向宁次','男',NULL,NULL,5,2),(48,'201701006','佐井','男',NULL,NULL,5,2),(49,'201701007','山中井野','女',NULL,NULL,5,2),(50,'201701008','秋道丁次','男',NULL,NULL,5,2),(51,'201701009','犬冢牙','男',NULL,NULL,5,2),(52,'201701010','野原琳','女',NULL,NULL,5,2),(53,'201701011','天天','女',NULL,NULL,5,2),(54,'201701012','木叶丸','男',NULL,NULL,5,2),(55,'201701013','赤丸','男',NULL,NULL,5,2),(56,'201701014','漩涡鸣人','男',NULL,NULL,5,2),(57,'201701015','佐助','男',NULL,NULL,5,2),(58,'201701016','春野樱','女',NULL,NULL,5,2),(59,'201701017','油女志乃','男',NULL,NULL,5,2),(60,'201702001','宇智波带土','男',NULL,NULL,6,2),(61,'201702002','长门','男',NULL,NULL,6,2),(62,'201702003','绝','男',NULL,NULL,6,2),(63,'201702004','角都','男',NULL,NULL,6,2),(66,'201702007','大蛇丸','男',NULL,NULL,6,2),(67,'201702008','飞段','男',NULL,NULL,6,2),(68,'201702009','蝎','男','','',6,2),(69,'201702010','弥彦','男',NULL,NULL,6,2),(70,'201702011','千柿鬼鲛','男',NULL,NULL,6,2);

/*Table structure for table `system` */

DROP TABLE IF EXISTS `system`;

CREATE TABLE `system` (
  `id` int(11) NOT NULL,
  `schoolName` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `forbidTeacher` tinyint(2) DEFAULT NULL,
  `forbidStudent` tinyint(2) DEFAULT NULL,
  `noticeTeacher` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `noticeStudent` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `system` */

insert  into `system`(`id`,`schoolName`,`forbidTeacher`,`forbidStudent`,`noticeTeacher`,`noticeStudent`) values (1,'松田中学',0,0,'不删纲手','请好好照顾林齐心');

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `sex` varchar(4) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `qq` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number_teacher_FK` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `teacher` */

insert  into `teacher`(`id`,`number`,`name`,`sex`,`phone`,`qq`) values (1,'2001','卡卡西','男','18987831233','63456345'),(2,'2002','卡普','男','13927387432','65686786'),(3,'2003','战国','男','11389823821','1233456'),(4,'2004','青雉','男','15234523454','7456345'),(5,'2005','爱德华纽盖特','男','16234243242','34634534'),(6,'2006','香克斯','男','16345475689','35464573'),(7,'2007','波风水门','男','15234234234','35683673'),(8,'2008','纲手','女','14352341231','73456236'),(9,'2009','大筒木辉夜','女','13452342342','234523455'),(14,'2011','夕日红','女','15234234523','7243821');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '111111',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` tinyint(1) DEFAULT '2',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_user_FK` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `user` */

insert  into `user`(`id`,`account`,`password`,`name`,`type`) values (1,'admin','222222','管理员',1),(2,'2001','111111','卡卡西',3),(3,'2002','111111','卡普',3),(4,'2003','111111','战国',3),(5,'2004','111111','青雉',3),(6,'2005','111111','爱德华纽盖特',3),(7,'2006','111111','香克斯',3),(8,'2007','111111','波风水门',3),(9,'2008','123456','纲手',3),(10,'2009','111111','大筒木辉夜',3),(15,'2011','111111','夕日红',3),(17,'201601001','123456','蒙奇',2),(18,'201601002','222222','妮可罗宾',2),(19,'201601003','111111','罗罗诺亚卓洛',2),(20,'201601004','111111','托尼托尼乔巴',2),(21,'201601005','111111','娜美',2),(22,'201601006','111111','山治',2),(23,'201601007','111111','布鲁克',2),(24,'201601008','111111','乌索普',2),(25,'201601009','111111','弗兰奇',2),(26,'201601010','111111','娜菲鲁塔利薇薇',2),(27,'201601011','111111','小鱿',2),(28,'201601012','111111','梅里号',2),(29,'201601013','111111','阳光号',2),(30,'201602001','111111','马歇尔蒂奇',2),(31,'201602002','111111','范奥卡',2),(32,'201602003','111111','基萨斯巴加斯',2),(33,'201602004','111111','毒Q',2),(34,'201602005','111111','雨之希留',2),(35,'201602006','111111','卡特琳娜',2),(36,'201602007','111111','圣胡安恶狼',2),(37,'201602008','111111','巴克斯乔特',2),(38,'201602009','111111','阿巴罗',2),(39,'201603001','111111','汉库克',2),(40,'201603002','111111','桑达索尼娅',2),(41,'201603003','111111','玛丽哥鲁德',2),(42,'201603004','111111','玛格丽特',2),(43,'201603005','111111','艾弗兰德拉',2),(44,'201603006','111111','贝拉董娜',2),(45,'201604001','111111','白胡子',2),(46,'201604002','111111','马尔高',2),(47,'201604003','111111','艾斯',2),(48,'201604004','111111','乔兹',2),(49,'201604005','111111','萨奇',2),(50,'201604006','111111','比斯塔',2),(51,'201604007','111111','布拉曼克',2),(52,'201604008','111111','拉克约',2),(53,'201604009','111111','那谬尔',2),(54,'201604010','111111','布伦海姆',2),(55,'201604011','111111','库利艾尔',2),(56,'201604012','111111','金古多',2),(57,'201604013','111111','佛萨',2),(58,'201604014','111111','斯比多基尔',2),(59,'201701001','111111','日向雏田',2),(60,'201701002','111111','李洛克',2),(61,'201701003','111111','日向花火',2),(62,'201701004','111111','奈良鹿丸',2),(63,'201701005','111111','日向宁次',2),(64,'201701006','111111','佐井',2),(65,'201701007','111111','山中井野',2),(66,'201701008','111111','秋道丁次',2),(67,'201701009','111111','犬冢牙',2),(68,'201701010','111111','野原琳',2),(69,'201701011','111111','天天',2),(70,'201701012','111111','木叶丸',2),(71,'201701013','111111','赤丸',2),(72,'201701014','111111','漩涡鸣人',2),(73,'201701015','111111','佐助',2),(74,'201701016','111111','春野樱',2),(75,'201701017','111111','油女志乃',2),(76,'201702001','111111','宇智波带土',2),(77,'201702002','111111','长门',2),(78,'201702003','111111','绝',2),(79,'201702004','111111','角都',2),(82,'201702007','111111','大蛇丸',2),(83,'201702008','111111','飞段',2),(84,'201702009','111111','蝎',2),(85,'201702010','111111','弥彦',2),(86,'201702011','111111','千柿鬼鲛',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
