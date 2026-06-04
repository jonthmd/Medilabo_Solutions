-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema MSPatient
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema MSPatient
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `MSPatient` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `MSPatient` ;

-- -----------------------------------------------------
-- Table `MSPatient`.`patient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MSPatient`.`patient` (
                                                     `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                     `address` VARCHAR(255) NULL DEFAULT NULL,
    `birth_date` VARCHAR(255) NULL DEFAULT NULL,
    `first_name` VARCHAR(255) NULL DEFAULT NULL,
    `gender` VARCHAR(255) NULL DEFAULT NULL,
    `last_name` VARCHAR(255) NULL DEFAULT NULL,
    `phone` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 6
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
