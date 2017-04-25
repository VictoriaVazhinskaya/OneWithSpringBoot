DROP DATABASE IF EXISTS cinema;

CREATE SCHEMA `cinema` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `cinema`.`films` (

  `id` INT NOT NULL AUTO_INCREMENT,

  `name` VARCHAR(45) NOT NULL,

  `year` YEAR NOT NULL,

  `genre` VARCHAR(30) NOT NULL,

  PRIMARY KEY (`id`));

CREATE TABLE `cinema`.`seances` (

  `id` INT NOT NULL AUTO_INCREMENT,

  `code` CHAR(11) NOT NULL,

  `film_id` INT NOT NULL,

  `date` DATE NOT NULL,

  `time` TIME NOT NULL,

  PRIMARY KEY (`id`),

  UNIQUE INDEX `code_UNIQUE` (`code` ASC),

  INDEX `film_id_fk_idx` (`film_id` ASC),

  CONSTRAINT `film_id_fk`

    FOREIGN KEY (`film_id`)

    REFERENCES `cinema`.`films` (`id`)

    ON DELETE CASCADE

    ON UPDATE CASCADE);

CREATE TABLE `cinema`.`reservations` (

  `id` INT NOT NULL AUTO_INCREMENT,

  `code` VARCHAR(15) NOT NULL,

  `seat` TINYINT UNSIGNED NOT NULL,

  `seance_id` INT NOT NULL,

  PRIMARY KEY (`id`),

  INDEX `seance_id_fk_idx` (`seance_id` ASC),

  CONSTRAINT `seance_id_fk`

    FOREIGN KEY (`seance_id`)

    REFERENCES `cinema`.`seances` (`id`)

    ON DELETE CASCADE
    ON UPDATE CASCADE);
