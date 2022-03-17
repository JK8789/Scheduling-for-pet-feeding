
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema petsdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `petsdb` ;

-- -----------------------------------------------------
-- Schema petsdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `petsdb` DEFAULT CHARACTER SET utf8 ;
USE `petsdb` ;

-- -----------------------------------------------------
-- Table `petsdb`.`persons`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `petsdb`.`persons` ;

CREATE TABLE IF NOT EXISTS `petsdb`.`persons` (
  `idpersons` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(60) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idpersons`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `idpersons_UNIQUE` ON `petsdb`.`persons` (`idpersons` ASC) VISIBLE;

CREATE UNIQUE INDEX `email_UNIQUE` ON `petsdb`.`persons` (`email` ASC) VISIBLE;

INSERT INTO `persons` (`idpersons`, `name`, `email`, `password`)
VALUES 
(1, 'Julia', 'korotkovayuliav@gmail.com', '111'),
(2, 'Alex', 'Yulia-89@yandex.com', '111');
SELECT * FROM persons;
-- -----------------------------------------------------
-- Table `petsdb`.`pets`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `petsdb`.`pets` ;

CREATE TABLE IF NOT EXISTS `petsdb`.`pets` (
  `idpets` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `persons_idpersons` INT NOT NULL,
  PRIMARY KEY (`idpets`, `persons_idpersons`),
  CONSTRAINT `fk_pets_persons1`
    FOREIGN KEY (`persons_idpersons`)
    REFERENCES `petsdb`.`persons` (`idpersons`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `name_UNIQUE` ON `petsdb`.`pets` (`name` ASC) VISIBLE;

CREATE INDEX `fk_pets_persons1_idx` ON `petsdb`.`pets` (`persons_idpersons` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `petsdb`.`schedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `petsdb`.`schedule` ;

CREATE TABLE IF NOT EXISTS `petsdb`.`schedule` (
  `idschedule` INT NOT NULL,
  `time` TIME NOT NULL,
  `note` VARCHAR(200) NULL,
  `completion_mark` TINYINT NULL,
  `persons_idpersons` INT NOT NULL,
  PRIMARY KEY (`idschedule`, `persons_idpersons`),
  CONSTRAINT `fk_schedule_persons1`
    FOREIGN KEY (`persons_idpersons`)
    REFERENCES `petsdb`.`persons` (`idpersons`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `idschedule_UNIQUE` ON `petsdb`.`schedule` (`idschedule` ASC) VISIBLE;

CREATE INDEX `fk_schedule_persons1_idx` ON `petsdb`.`schedule` (`persons_idpersons` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `petsdb`.`schedule_has_pets`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `petsdb`.`schedule_has_pets` ;

CREATE TABLE IF NOT EXISTS `petsdb`.`schedule_has_pets` (
  `schedule_idschedule` INT NOT NULL,
  `pets_idpets` INT NOT NULL,
  PRIMARY KEY (`schedule_idschedule`, `pets_idpets`),
  CONSTRAINT `fk_schedule_has_pets_schedule`
    FOREIGN KEY (`schedule_idschedule`)
    REFERENCES `petsdb`.`schedule` (`idschedule`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_schedule_has_pets_pets1`
    FOREIGN KEY (`pets_idpets`)
    REFERENCES `petsdb`.`pets` (`idpets`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_schedule_has_pets_pets1_idx` ON `petsdb`.`schedule_has_pets` (`pets_idpets` ASC) VISIBLE;

CREATE INDEX `fk_schedule_has_pets_schedule_idx` ON `petsdb`.`schedule_has_pets` (`schedule_idschedule` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
