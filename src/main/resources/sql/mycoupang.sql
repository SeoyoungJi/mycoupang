-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.2.17-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- mycoupangdb 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `mycoupangdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mycoupangdb`;

-- 테이블 mycoupangdb.tb_user 구조 내보내기
CREATE TABLE IF NOT EXISTS `tb_user` (
  `USERNO` int(11) NOT NULL AUTO_INCREMENT,
  `USERID` varchar(50) NOT NULL,
  `USERPW` varchar(300) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `GENDER` char(1) NOT NULL,
  `ADDR` varchar(300) NOT NULL,
  `HP` varchar(11) NOT NULL,
  `EMAIL` varchar(100) NOT NULL,
  `REGISTERDAY` datetime DEFAULT current_timestamp(),
  `INTEREST` varchar(50) NOT NULL,
  `ACTIVE` tinyint(4) DEFAULT 0,
  `ROLE` varchar(50) DEFAULT 'MEMBER',
  `CODE` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`USERNO`),
  UNIQUE KEY `USERID` (`USERID`),
  CONSTRAINT `CONSTRAINT_1` CHECK (`GENDER` in ('F','M')),
  CONSTRAINT `CONSTRAINT_2` CHECK (`ACTIVE` in (0,1))
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

-- 테이블 데이터 mycoupangdb.tb_user:~17 rows (대략적) 내보내기
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` (`USERNO`, `USERID`, `USERPW`, `NAME`, `GENDER`, `ADDR`, `HP`, `EMAIL`, `REGISTERDAY`, `INTEREST`, `ACTIVE`, `ROLE`, `CODE`) VALUES
	(80, 'asdf', '$2a$10$SQb1q86.5QAl6jf8tP4GIusekE4uRHE5JQ1O3Z46jz9p04UyZvrs2', '11', 'F', 'dd', 'dd', 'wltjdud91@naver.com', '2020-09-28 01:22:24', '1', 0, 'MEMBER', 'mycoupang39410869'),
	(81, '56', '$2a$10$mpKRoaCk6NjEF3/RUGA4Weer9myG/iAoemXkG1ZtsWU/z9j56fxnm', '11', 'F', 'dfs', 'dfs', 'wltjdud91@naver.com', '2020-09-28 01:29:28', '6', 0, 'MEMBER', 'mycoupang4142639'),
	(82, 'FG', '$2a$10$bxaxoC9KTthKzU2NSF1vPeTSM82.nTs6IqLV6AxonFms/3270.e2K', '11', 'F', 'dfs', 'dfs', 'wltjdud91@naver.com', '2020-09-28 01:38:27', '8', 0, 'MEMBER', 'mycoupang4927684'),
	(83, 'zxc', '$2a$10$Cx7AOAL9SQIDelTmIQ5j3uprEyvssUMTNo/4LOejRjJWLUn2n0aie', '11', 'F', 'dd', 'dd', 'wltjdud91@naver.com', '2020-09-28 01:46:57', '9', 0, 'MEMBER', 'mycoupang4951333');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
