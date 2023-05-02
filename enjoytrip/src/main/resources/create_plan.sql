CREATE TABLE IF NOT EXISTS `enjoytrip`.`plan` (
  `user_id` BIGINT NOT NULL,
  `plan_id` INT NOT NULL AUTO_INCREMENT,
  `plan_name` VARCHAR(50) NULL DEFAULT NULL,
  `like` INT NULL DEFAULT NULL,
  PRIMARY KEY (`plan_id`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `enjoytrip`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci

CREATE TABLE IF NOT EXISTS `enjoytrip`.`plan_info` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `plan_id` INT NOT NULL,
  `content_id` INT NOT NULL,
  `plan_day` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uk_content_user` (`plan_id` ASC, `content_id` ASC) VISIBLE,
  INDEX `content_id_idx` (`content_id` ASC) VISIBLE,
  CONSTRAINT `content_id`
    FOREIGN KEY (`content_id`)
    REFERENCES `enjoytrip`.`attraction_info` (`content_id`),
  CONSTRAINT `plan_id`
    FOREIGN KEY (`plan_id`)
    REFERENCES `enjoytrip`.`plan` (`plan_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci