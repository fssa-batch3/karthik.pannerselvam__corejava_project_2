-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: karthik_pannerselvam_corejava_project
-- Source Schemata: karthik_pannerselvam_corejava_project
-- Created: Thu Sep  7 16:36:34 2023
-- Workbench Version: 8.0.34
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------------------------------------------------------
-- Schema karthik_pannerselvam_corejava_project
-- ----------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `karthik_pannerselvam_corejava_project` ;
CREATE SCHEMA IF NOT EXISTS `karthik_pannerselvam_corejava_project` ;

-- ----------------------------------------------------------------------------
-- Table karthik_pannerselvam_corejava_project.tasks
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `karthik_pannerselvam_corejava_project`.`tasks` (
  `task_id` INT NOT NULL AUTO_INCREMENT,
  `taskname` VARCHAR(30) NOT NULL,
  `task_status` VARCHAR(20) NULL DEFAULT NULL,
  `task_description` VARCHAR(500) NOT NULL,
  `user_email` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`task_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------------------------------------------------------
-- Table karthik_pannerselvam_corejava_project.users
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `karthik_pannerselvam_corejava_project`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `is_login` TINYINT(1) NULL DEFAULT NULL,
  `is_delete` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
SET FOREIGN_KEY_CHECKS = 1;
