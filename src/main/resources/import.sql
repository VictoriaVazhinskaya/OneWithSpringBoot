INSERT INTO cinema.film (name, year, genre) VALUES('Southbound',2015, 'horror');
INSERT INTO cinema.film (name, year, genre) VALUES('Lady Macbeth',2016, 'drama');
INSERT INTO cinema.film (name, year, genre) VALUES('Lumière!', 2016, 'documentary');
INSERT INTO cinema.film (name, year, genre) VALUES('Paris pieds nus',2016, 'comedy');
INSERT INTO cinema.film (name, year, genre) VALUES('Despicable Me 3',2017, 'family');
INSERT INTO cinema.film (name, year, genre) VALUES('Gilda', 1946, 'melodrama-noir');
INSERT INTO cinema.film (name, year, genre) VALUES('The Dark Tower',2017, 'fantastic');
INSERT INTO cinema.film (name, year, genre) VALUES('Ex Drummer',2007, 'drama');
INSERT INTO cinema.film (name, year, genre) VALUES('Ghostbusters', 1984, 'fantastic');


INSERT INTO cinema.cinema (name, address, phone) VALUES('Oktyabr', 'Minsk, 73 Nezavisimosti Avenue', '+375172929426');
INSERT INTO cinema.cinema (name, address, phone) VALUES('Silver Screen', 'Minsk, 6 Bobruisk street', '+375296216886');
INSERT INTO cinema.cinema (name, address, phone) VALUES('Pioner', 'Minsk, 20 Engels street', '+375173278945');
INSERT INTO cinema.cinema (name, address, phone) VALUES('Avrora', 'Minsk, 23 Pritytsky street', '+375173638012');
INSERT INTO cinema.cinema (name, address, phone) VALUES('Tsentralniy', 'Minsk, 13 Nezavisimosti Avenue', '+375172003416');

INSERT INTO cinema.hall (number, capacity, cinema_id) VALUES (1, 250, 1);
INSERT INTO cinema.hall (number, capacity, cinema_id) VALUES (1, 250, 2);
INSERT INTO cinema.hall (number, capacity, cinema_id) VALUES (2, 250, 2);
INSERT INTO cinema.hall (number, capacity, cinema_id) VALUES (3, 250, 2);
INSERT INTO cinema.hall (number, capacity, cinema_id) VALUES (4, 250, 2);
INSERT INTO cinema.hall (number, capacity, cinema_id) VALUES (5, 250, 2);
INSERT INTO cinema.hall (number, capacity, cinema_id) VALUES (6, 250, 2);
INSERT INTO cinema.hall (number, capacity, cinema_id) VALUES (7, 250, 2);
INSERT INTO cinema.hall (number, capacity, cinema_id) VALUES (1, 240, 3);
INSERT INTO cinema.hall (number, capacity, cinema_id) VALUES (1, 225, 4);
INSERT INTO cinema.hall (number, capacity, cinema_id) VALUES (2, 225, 4);
INSERT INTO cinema.hall (number, capacity, cinema_id) VALUES (1, 250, 5);

-----------------//Oktyabr//---------------
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (3, 1, '2017-08-15', '10:20');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (3, 1, '2017-08-16', '10:20');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (2, 1, '2017-08-17', '12:40');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (4, 1, '2017-08-15', '14:30');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (9, 1, '2017-08-18', '16:30');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (1, 1, '2017-08-19', '18:30');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (8, 1, '2017-08-15', '21:00');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (4, 1, '2017-08-18', '12:30');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (8, 1, '2017-08-16', '21:00');
--------------//Silver Screen//------------
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (3, 2, '2017-08-15', '10:20');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (3, 2, '2017-08-16', '10:20');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (2, 3, '2017-08-17', '12:40');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (4, 6, '2017-08-15', '14:30');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (9, 2, '2017-08-18', '16:30');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (1, 5, '2017-08-19', '18:30');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (8, 4, '2017-08-15', '21:00');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (4, 6, '2017-08-18', '12:30');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (8, 8, '2017-08-16', '21:00');
-----------------//Pioner//----------------
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (3, 9, '2017-08-15', '10:00');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (2, 9, '2017-08-16', '12:00');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (6, 9, '2017-08-15', '14:00');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (7, 9, '2017-08-17', '16:00');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (1, 9, '2017-08-18', '18:00');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (8, 9, '2017-08-17', '20:00');
-----------------//Avrora//----------------
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (3, 11, '2017-08-15', '10:20');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (3, 11, '2017-08-16', '10:20');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (2, 10, '2017-08-17', '12:40');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (4, 10, '2017-08-15', '14:30');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (9, 11, '2017-08-18', '16:30');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (1, 10, '2017-08-19', '18:30');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (8, 11, '2017-08-15', '21:00');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (4, 11, '2017-08-18', '12:30');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (8, 10, '2017-08-16', '21:00');
---------------//Tsentralniy//-------------
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (4, 12, '2017-08-18', '10:20');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (5, 12, '2017-08-15', '12:40');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (3, 12, '2017-08-16', '14:30');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (9, 12, '2017-08-17', '16:30');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (1, 12, '2017-08-19', '18:30');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (8, 12, '2017-08-15', '21:00');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (9, 12, '2017-08-18', '16:30');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (1, 12, '2017-08-16', '18:30');
INSERT INTO cinema.seance (film_id, hall_id, date, time) VALUES (8, 12, '2017-08-19', '21:00');

--//PW for "odmen"(ADMIN) = god
--//PW for "vice-odmen"(MANAGER) = manager
--//PW for "lenochka-princesochka1431"(USER) = mimimi2017
--//PW for "boginya638"(USER) = sushiOneLove
--//PW for "GrustniyVadim"(USER) = mneGrustno2017
INSERT INTO cinema.user (login, password, role) VALUES ('odmen', '$2a$04$4K.fPyvdfvSiqvvQ8AEmn.po1hhA2PG4Z8PznqBqLEuTkABLlEJs6', 'ROLE_ADMIN');
INSERT INTO cinema.user (login, password, role) VALUES ('manager', '$2a$04$3EOsLEIHqhiXLGZevdZqh.XqjEXrEClOCrlDpCa1xB0m8i4zD0Y3u', 'ROLE_MANAGER');
INSERT INTO cinema.user (login, password, role) VALUES ('lenochka-princesochka1431', '$2a$04$2TLmPjukuJgO.uztkxrigOK9HAwHPUg3EZ9k17quPN423XXEfYF9u', 'ROLE_USER');
INSERT INTO cinema.user (login, password, role) VALUES ('boginya638', '$2a$04$HT3xomWMqOQwOFtkULA8XeuCkpwtKSEH.c22jNhkrXFpu68x5Ukdu', 'ROLE_USER');
INSERT INTO cinema.user (login, password, role) VALUES ('GrustniyVadim', '$2a$04$p4BMc.3WtzLzKnnuLpe.x.czq1ONK59hPa.tpcx8Yl3ERgExe1uGm', 'ROLE_USER');



