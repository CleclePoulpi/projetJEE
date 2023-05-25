-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema info_team06_schema
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema info_team06_schema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `info_team06_schema` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `info_team06_schema` ;

-- -----------------------------------------------------
-- Table `info_team06_schema`.`Athlete_ignorer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_team06_schema`.`Athlete_ignorer` (
  `idAthlete` INT NOT NULL AUTO_INCREMENT,
  `nomAthlete` VARCHAR(45) NOT NULL,
  `prenomAthlete` VARCHAR(45) NOT NULL,
  `nationaliteAthlete` VARCHAR(45) NOT NULL,
  `naissanceAthlete` DATE NOT NULL,
  `disciplineAthlete` INT NOT NULL,
  `genreAthlete` ENUM('Femme', 'Homme') NOT NULL,
  PRIMARY KEY (`idAthlete`),
  INDEX `DisciplineDeAthlete` (`disciplineAthlete` ASC) VISIBLE,
  CONSTRAINT `disciplineAthlete`
    FOREIGN KEY (`disciplineAthlete`)
    REFERENCES `info_team06_schema`.`Discipline_ignorer` (`idDiscipline`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `info_team06_schema`.`Site_ignorer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_team06_schema`.`Site_ignorer` (
  `idSite` INT NOT NULL AUTO_INCREMENT,
  `nomSite` VARCHAR(45) NOT NULL,
  `villeSite` VARCHAR(45) NOT NULL,
  `categorieSite` ENUM('stade', 'salle de spectacle', 'lieu public', 'centre aquatique') NOT NULL,
  PRIMARY KEY (`idSite`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `info_team06_schema`.`Session_ignorer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_team06_schema`.`Session_ignorer` (
  `codeSession` VARCHAR(45) NOT NULL,
  `dateSession` DATE NOT NULL,
  `debutSession` TIME NOT NULL,
  `finSession` TIME NOT NULL,
  `disciplineSession` INT NOT NULL,
  `siteSession` INT NOT NULL,
  `descriptionSession` LONGTEXT NOT NULL,
  `typeSession` ENUM('qualifications', 'médailles') NOT NULL,
  `categorieSession` ENUM('H', 'F', 'mixte') NOT NULL,
  PRIMARY KEY (`codeSession`),
  INDEX `DisciplineDeSession` (`disciplineSession` ASC) VISIBLE,
  INDEX `SiteDeSession` (`siteSession` ASC) VISIBLE,
  CONSTRAINT `disciplineSession`
    FOREIGN KEY (`disciplineSession`)
    REFERENCES `info_team06_schema`.`Discipline_ignorer` (`idDiscipline`),
  CONSTRAINT `siteSession`
    FOREIGN KEY (`siteSession`)
    REFERENCES `info_team06_schema`.`Site_ignorer` (`idSite`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `info_team06_schema`.`discipline`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_team06_schema`.`discipline` (
  `id` BIGINT NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `paralympic` BIT(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `info_team06_schema`.`athlete`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_team06_schema`.`athlete` (
  `id` BIGINT NOT NULL,
  `date_of_birth` DATETIME(6) NULL DEFAULT NULL,
  `gender` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `nationality` VARCHAR(255) NULL DEFAULT NULL,
  `surname` VARCHAR(255) NULL DEFAULT NULL,
  `discipline_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK3qkwvabh5uiiiix01bsrkvgu7` (`discipline_id` ASC) VISIBLE,
  CONSTRAINT `FK3qkwvabh5uiiiix01bsrkvgu7`
    FOREIGN KEY (`discipline_id`)
    REFERENCES `info_team06_schema`.`discipline` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `info_team06_schema`.`athlete_seq`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_team06_schema`.`athlete_seq` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `info_team06_schema`.`discipline_seq`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_team06_schema`.`discipline_seq` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `info_team06_schema`.`site`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_team06_schema`.`site` (
  `id` BIGINT NOT NULL,
  `location_category` VARCHAR(255) NULL DEFAULT NULL,
  `location_city` VARCHAR(255) NULL DEFAULT NULL,
  `location_name` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `info_team06_schema`.`event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_team06_schema`.`event` (
  `id` BIGINT NOT NULL,
  `category` VARCHAR(255) NULL DEFAULT NULL,
  `code` INT NOT NULL,
  `date` DATE NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `end_time` TIME NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `start_time` TIME NULL DEFAULT NULL,
  `type` VARCHAR(255) NULL DEFAULT NULL,
  `discipline_id` BIGINT NULL DEFAULT NULL,
  `location_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKjd8wk6sd9rfqf2m30npfqktcm` (`discipline_id` ASC) VISIBLE,
  INDEX `FK5rv7006m3x1w947mey57k2wty` (`location_id` ASC) VISIBLE,
  CONSTRAINT `FK5rv7006m3x1w947mey57k2wty`
    FOREIGN KEY (`location_id`)
    REFERENCES `info_team06_schema`.`site` (`id`),
  CONSTRAINT `FKjd8wk6sd9rfqf2m30npfqktcm`
    FOREIGN KEY (`discipline_id`)
    REFERENCES `info_team06_schema`.`discipline` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `info_team06_schema`.`event_seq`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_team06_schema`.`event_seq` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `info_team06_schema`.`login`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_team06_schema`.`login` (
  `id` BIGINT NOT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `info_team06_schema`.`login_seq`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_team06_schema`.`login_seq` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `info_team06_schema`.`site_seq`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_team06_schema`.`site_seq` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `info_team06_schema`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_team06_schema`.`user` (
  `id` BIGINT NOT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `privilege` INT NOT NULL,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `info_team06_schema`.`user_seq`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_team06_schema`.`user_seq` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
