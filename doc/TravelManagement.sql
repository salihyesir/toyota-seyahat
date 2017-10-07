-- MySQL Script generated by MySQL Workbench
-- 03/02/17 23:55:55
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema TravelManagement
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema TravelManagement
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `TravelManagement` DEFAULT CHARACTER SET utf8 COLLATE utf8_turkish_ci ;
USE `TravelManagement` ;

-- -----------------------------------------------------
-- Table `TravelManagement`.`Roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TravelManagement`.`Roles` (
  `RoleId` INT NOT NULL AUTO_INCREMENT,
  `RoleName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`RoleId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TravelManagement`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TravelManagement`.`Users` (
  `UserId` INT NOT NULL AUTO_INCREMENT,
  `UserName` NVARCHAR(45) NOT NULL,
  `UserRegister` INT NOT NULL,
  `UserPassword` NVARCHAR(45) NOT NULL,
  `UserDepartment` NVARCHAR(20) NOT NULL,
  `DepartmentAdmin` NVARCHAR(50) NOT NULL,
  `RoleId` INT NOT NULL,
  `Notification` NVARCHAR(15) NULL,
  `IsDeleted` BIT NOT NULL,
  PRIMARY KEY (`UserId`),
  INDEX `RoleId_idx` (`RoleId` ASC),
  UNIQUE INDEX `UserRegister_UNIQUE` (`UserRegister` ASC),
  CONSTRAINT `RoleId`
    FOREIGN KEY (`RoleId`)
    REFERENCES `TravelManagement`.`Roles` (`RoleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TravelManagement`.`TravelDetails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TravelManagement`.`TravelDetails` (
  `TravelId` INT NOT NULL AUTO_INCREMENT,
  `UserId` INT NOT NULL,
  `TravelLocation` NVARCHAR(30) NOT NULL,
  `TravelMission` NVARCHAR(20) NOT NULL,
  `ProjectCode` NVARCHAR(5) NOT NULL,
  `TravelStartDate` DATE NOT NULL,
  `TravelFinishDate` DATE NOT NULL,
  `EstimatedCost` NVARCHAR(10) NULL,
  `IsDeleted` BIT NOT NULL,
  PRIMARY KEY (`TravelId`),
  INDEX `UserId_idx` (`UserId` ASC),
  CONSTRAINT `UserId`
    FOREIGN KEY (`UserId`)
    REFERENCES `TravelManagement`.`Users` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;