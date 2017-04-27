INSERT INTO `cinema`.`films` (`name`, `year`, `genre`) VALUES ('Power Rangers', 2017, 'fantastic');
INSERT INTO `cinema`.`films` (`name`, `year`, `genre`) VALUES ('Smurfs: The Lost Village', 2017, 'for children');
INSERT INTO `cinema`.`films` (`name`, `year`, `genre`) VALUES ('Ghost in the Shell', 2017, 'thriller');
INSERT INTO `cinema`.`films` (`name`, `year`, `genre`) VALUES ('Beauty and the Beast', 2017, 'fantasy');
INSERT INTO `cinema`.`films` (`name`, `year`, `genre`) VALUES ('Manchester by the Sea', 2016, 'drama');
INSERT INTO `cinema`.`films` (`name`, `year`, `genre`) VALUES ('Split', 2016, 'thriller');
INSERT INTO `cinema`.`films` (`name`, `year`, `genre`) VALUES ('The Boss Baby', 2017, 'for children');
INSERT INTO `cinema`.`films` (`name`, `year`, `genre`) VALUES ('O lyubvi', 2017, 'melodrama');
INSERT INTO `cinema`.`films` (`name`, `year`, `genre`) VALUES ('Una', 2016, 'drama');

INSERT INTO `cinema`.`seances` (`film_id`, `date`, `time`) VALUES ('1', '2017-04-05', '10:00');
INSERT INTO `cinema`.`seances` (`film_id`, `date`, `time`) VALUES ('2', '2017-04-05', '12:00');
INSERT INTO `cinema`.`seances` (`film_id`, `date`, `time`) VALUES ('3', '2017-04-05', '14:00');
INSERT INTO `cinema`.`seances` (`film_id`, `date`, `time`) VALUES ('4', '2017-04-05', '15:50');
INSERT INTO `cinema`.`seances` (`film_id`, `date`, `time`) VALUES ('1', '2017-04-05', '17:10');
INSERT INTO `cinema`.`seances` (`film_id`, `date`, `time`) VALUES ('5', '2017-04-05', '19:00');
INSERT INTO `cinema`.`seances` (`film_id`, `date`, `time`) VALUES ('6', '2017-04-05', '21:20');
INSERT INTO `cinema`.`seances` (`film_id`, `date`, `time`) VALUES ('2', '2017-04-06', '10:00');
INSERT INTO `cinema`.`seances` (`film_id`, `date`, `time`) VALUES ('7', '2017-04-06', '11:50');
INSERT INTO `cinema`.`seances` (`film_id`, `date`, `time`) VALUES ('8', '2017-04-06', '14:10');
INSERT INTO `cinema`.`seances` (`film_id`, `date`, `time`) VALUES ('1', '2017-04-06', '18:30');
INSERT INTO `cinema`.`seances` (`film_id`, `date`, `time`) VALUES ('4', '2017-04-06', '20:30');
INSERT INTO `cinema`.`seances` (`film_id`, `date`, `time`) VALUES ('4', '2017-04-07', '10:00');
INSERT INTO `cinema`.`seances` (`film_id`, `date`, `time`) VALUES ('3', '2017-04-07', '18:20');
INSERT INTO `cinema`.`seances` (`film_id`, `date`, `time`) VALUES ('6', '2017-04-07', '22:10');
INSERT INTO `cinema`.`seances` (`film_id`, `date`, `time`) VALUES ('5', '2017-04-08', '12:00');
INSERT INTO `cinema`.`seances` (`film_id`, `date`, `time`) VALUES ('9', '2017-04-08', '20:50');

INSERT INTO `cinema`.`reservations` (`code`, `seance_id`) VALUES ('12017040510007', '1');
INSERT INTO `cinema`.`reservations` (`code`, `seance_id`) VALUES ('120170405100010',  '1');
INSERT INTO `cinema`.`reservations` (`code`, `seance_id`) VALUES ('22017040512008', '2');
INSERT INTO `cinema`.`reservations` (`code`, `seance_id`) VALUES ('220170405120025', '2');
INSERT INTO `cinema`.`reservations` (`code`, `seance_id`) VALUES ('220170405120011', '2');
INSERT INTO `cinema`.`reservations` (`code`, `seance_id`) VALUES ('32017040514007','3');
INSERT INTO `cinema`.`reservations` (`code`, `seance_id`) VALUES ('320170405140019', '3');
INSERT INTO `cinema`.`reservations` (`code`, `seance_id`) VALUES ('42017040515508', '4');
INSERT INTO `cinema`.`reservations` (`code`, `seance_id`) VALUES ('420170405155041',  '4');
INSERT INTO `cinema`.`reservations` (`code`, `seance_id`) VALUES ('520170405190010', '6');
INSERT INTO `cinema`.`reservations` (`code`, `seance_id`) VALUES ('520170405190064', '6');
INSERT INTO `cinema`.`reservations` (`code`, `seance_id`) VALUES ('62017040521207', '7');
INSERT INTO `cinema`.`reservations` (`code`, `seance_id`) VALUES ('620170405212013', '7');
INSERT INTO `cinema`.`reservations` (`code`, `seance_id`) VALUES ('620170405212028', '7');
INSERT INTO `cinema`.`reservations` (`code`, `seance_id`) VALUES ('920170408205012', '17');
INSERT INTO `cinema`.`reservations` (`code`, `seance_id`) VALUES ('920170408205022', '17');

INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('7', '1');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('8', '1');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('21', '1');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('10', '2');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('23', '2');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('24', '2');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('8', '3');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('33', '3');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('40', '3');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('41', '3');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('42', '3');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('25', '4');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('26', '4');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('36', '4');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('37', '4');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('11', '5');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('12', '5');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('13', '5');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('7', '6');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('8', '6');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('19', '7');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('9', '8');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('10', '8');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('11', '8');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('41', '9');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('42', '9');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('17', '10');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('18', '10');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('64', '11');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('65', '11');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('66', '11');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('7', '12');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('8', '12');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('15', '13');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('16', '13');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('28', '14');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('11', '15');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('12', '15');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('22', '16');
INSERT INTO `cinema`.`seats` (`number`, `reservation_id`) VALUES ('23', '16');


