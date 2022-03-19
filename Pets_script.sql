
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`persons`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`persons` ;

CREATE TABLE IF NOT EXISTS `mydb`.`persons` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(60) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `mydb`.`persons` (`id` ASC) VISIBLE;

CREATE UNIQUE INDEX `email_UNIQUE` ON `mydb`.`persons` (`email` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `mydb`.`pets`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`pets` ;

CREATE TABLE IF NOT EXISTS `mydb`.`pets` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `persons_id` INT NOT NULL,
  PRIMARY KEY (`id`, `persons_id`),
  CONSTRAINT `fk_pets_persons1`
    FOREIGN KEY (`persons_id`)
    REFERENCES `mydb`.`persons` (`id`)
    ON DELETE SET NULl
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `name_UNIQUE` ON `mydb`.`pets` (`name` ASC) VISIBLE;

CREATE INDEX `fk_pets_persons1_idx` ON `mydb`.`pets` (`persons_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `mydb`.`schedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`schedule` ;

CREATE TABLE IF NOT EXISTS `mydb`.`schedule` (
  `id` INT NOT NULL,
  `time` TIME NOT NULL,
  `note` VARCHAR(200) NULL,
  `completion_mark` TINYINT NULL,
  `persons_id` INT NOT NULL,
  PRIMARY KEY (`id`, `persons_id`),
  CONSTRAINT `fk_schedule_persons1`
    FOREIGN KEY (`persons_id`)
    REFERENCES `mydb`.`persons` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `mydb`.`schedule` (`id` ASC) VISIBLE;

CREATE INDEX `fk_schedule_persons1_idx` ON `mydb`.`schedule` (`persons_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `mydb`.`schedule_has_pets`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`schedule_has_pets` ;

CREATE TABLE IF NOT EXISTS `mydb`.`schedule_has_pets` (
  `schedule_id` INT NOT NULL,
  `pets_id` INT NOT NULL,
  PRIMARY KEY (`schedule_id`, `pets_id`),
  CONSTRAINT `fk_schedule_has_pets_schedule`
    FOREIGN KEY (`schedule_id`)
    REFERENCES `mydb`.`schedule` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_schedule_has_pets_pets1`
    FOREIGN KEY (`pets_id`)
    REFERENCES `mydb`.`pets` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_schedule_has_pets_pets1_idx` ON `mydb`.`schedule_has_pets` (`pets_id` ASC) VISIBLE;

CREATE INDEX `fk_schedule_has_pets_schedule_idx` ON `mydb`.`schedule_has_pets` (`schedule_id` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
