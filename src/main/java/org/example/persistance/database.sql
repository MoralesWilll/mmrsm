-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mmrsm
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mmrsm
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mmrsm` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `mmrsm` ;

-- -----------------------------------------------------
-- Table `mmrsm`.`clients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mmrsm`.`clients` (
  `client_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(225) NOT NULL,
  `email` VARCHAR(225) NOT NULL,
  `phone` VARCHAR(225) NOT NULL,
  `address` VARCHAR(225) NOT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mmrsm`.`machines`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mmrsm`.`machines` (
  `machine_id` INT NOT NULL AUTO_INCREMENT,
  `model` VARCHAR(225) NOT NULL,
  `serial_number` VARCHAR(225) NOT NULL,
  `status` VARCHAR(225) NOT NULL,
  PRIMARY KEY (`machine_id`),
  UNIQUE INDEX `serial_number_UNIQUE` (`serial_number` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 59
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mmrsm`.`rents`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mmrsm`.`rents` (
  `rent_id` INT NOT NULL AUTO_INCREMENT,
  `client_id` INT NOT NULL,
  `machine_id` INT NOT NULL,
  `status` VARCHAR(225) NOT NULL,
  `start_date` DATETIME NOT NULL,
  `finish_date` DATETIME NOT NULL,
  PRIMARY KEY (`rent_id`),
  INDEX `client_id_idx` (`client_id` ASC) VISIBLE,
  INDEX `machine_id_idx` (`machine_id` ASC) VISIBLE,
  CONSTRAINT `client_id`
    FOREIGN KEY (`client_id`)
    REFERENCES `mmrsm`.`clients` (`client_id`),
  CONSTRAINT `machine_id`
    FOREIGN KEY (`machine_id`)
    REFERENCES `mmrsm`.`machines` (`machine_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
