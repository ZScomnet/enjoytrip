drop database if exists enjoytrip;
create database enjoytrip;
use enjoytrip;
CREATE TABLE `enjoytrip`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(300) NOT NULL,
  `permission` CHAR(1) NOT NULL,
  `created` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `new_tablecol_UNIQUE` (`email` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));
  # 유저 테이블 추상화

  CREATE TABLE `enjoytrip`.`admin` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idadmin_UNIQUE` (`id` ASC),
  CONSTRAINT `fk_admin_id`
    FOREIGN KEY (`id`)
    REFERENCES `enjoytrip`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
# 관리자 유저의 테이블

CREATE TABLE `enjoytrip`.`member` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  `age` INT NOT NULL,
  `phone_number` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idmember_UNIQUE` (`id` ASC),
  CONSTRAINT `fk_member_id`
    FOREIGN KEY (`id`)
    REFERENCES `enjoytrip`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
# 일반 유저의 테이블


INSERT INTO user(id, email, password, permission,created) values (9999999, 'admin@admin.com', 'admin', 'A', NOW());
INSERT INTO admin(id, username) values(9999999, 'admin');
# 관리자 계정 등록