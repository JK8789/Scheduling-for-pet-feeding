
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
  `idpersons` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(60) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idpersons`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `idpersons_UNIQUE` ON `mydb`.`persons` (`idpersons` ASC) VISIBLE;

CREATE UNIQUE INDEX `email_UNIQUE` ON `mydb`.`persons` (`email` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `mydb`.`pets`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`pets` ;

CREATE TABLE IF NOT EXISTS `mydb`.`pets` (
  `idpets` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `persons_idpersons` INT NOT NULL,
  PRIMARY KEY (`idpets`, `persons_idpersons`),
  CONSTRAINT `fk_pets_persons1`
    FOREIGN KEY (`persons_idpersons`)
    REFERENCES `mydb`.`persons` (`idpersons`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `name_UNIQUE` ON `mydb`.`pets` (`name` ASC) VISIBLE;

CREATE INDEX `fk_pets_persons1_idx` ON `mydb`.`pets` (`persons_idpersons` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `mydb`.`schedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`schedule` ;

CREATE TABLE IF NOT EXISTS `mydb`.`schedule` (
  `idschedule` INT NOT NULL,
  `time` TIME NOT NULL,
  `note` VARCHAR(200) NULL,
  `completion_mark` TINYINT NULL,
  `persons_idpersons` INT NOT NULL,
  PRIMARY KEY (`idschedule`, `persons_idpersons`),
  CONSTRAINT `fk_schedule_persons1`
    FOREIGN KEY (`persons_idpersons`)
    REFERENCES `mydb`.`persons` (`idpersons`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `idschedule_UNIQUE` ON `mydb`.`schedule` (`idschedule` ASC) VISIBLE;

CREATE INDEX `fk_schedule_persons1_idx` ON `mydb`.`schedule` (`persons_idpersons` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `mydb`.`schedule_has_pets`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`schedule_has_pets` ;

CREATE TABLE IF NOT EXISTS `mydb`.`schedule_has_pets` (
  `schedule_idschedule` INT NOT NULL,
  `pets_idpets` INT NOT NULL,
  PRIMARY KEY (`schedule_idschedule`, `pets_idpets`),
  CONSTRAINT `fk_schedule_has_pets_schedule`
    FOREIGN KEY (`schedule_idschedule`)
    REFERENCES `mydb`.`schedule` (`idschedule`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_schedule_has_pets_pets1`
    FOREIGN KEY (`pets_idpets`)
    REFERENCES `mydb`.`pets` (`idpets`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_schedule_has_pets_pets1_idx` ON `mydb`.`schedule_has_pets` (`pets_idpets` ASC) VISIBLE;

CREATE INDEX `fk_schedule_has_pets_schedule_idx` ON `mydb`.`schedule_has_pets` (`schedule_idschedule` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
