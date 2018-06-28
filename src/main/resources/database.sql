CREATE DATABASE restapi;

USE restapi;

CREATE TABLE car (
  id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  carNumber VARCHAR(500) NOT NULL,
  carLocation ENUM('INSIDE', 'OUTSIDE') NOT NULL,
  carStatus ENUM('VIP', 'REGULAR') NOT NULL,
  parkingMeterStatus ENUM('ON','OFF')
);

CREATE TABLE parkingMeter (
  id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  carNumber VARCHAR(500) NOT NULL,
  createdAt DATETIME NOT NULL,
  stoppedAt DATETIME NOT NULL,
  cost DOUBLE
);

INSERT INTO car(carNumber, carLocation, carStatus, parkingMeterStatus) VALUES ('AAAAAB222', 'OUTSIDE', 'VIP', 'OFF');
INSERT INTO car(carNumber, carLocation, carStatus, parkingMeterStatus) VALUES ('BBBBBSSS', 'OUTSIDE', 'REGULAR', 'OFF');
INSERT INTO car(carNumber, carLocation, carStatus, parkingMeterStatus) VALUES ('WWWWW', 'INSIDE', 'REGULAR', 'OFF');
INSERT INTO car(carNumber, carLocation, carStatus, parkingMeterStatus) VALUES ('WERWE2', 'OUTSIDE', 'REGULAR', 'OFF');
INSERT INTO car(carNumber, carLocation, carStatus, parkingMeterStatus) VALUES ('LUBRBR', 'OUTSIDE', 'VIP', 'OFF');
INSERT INTO car(carNumber, carLocation, carStatus, parkingMeterStatus) VALUES ('RRRR', 'INSIDE', 'REGULAR', 'OFF');
INSERT INTO car(carNumber, carLocation, carStatus, parkingMeterStatus) VALUES ('WERTVFR', 'OUTSIDE', 'REGULAR', 'OFF');
INSERT INTO car(carNumber, carLocation, carStatus, parkingMeterStatus) VALUES ('WDERF', 'INSIDE', 'VIP', 'OFF');
INSERT INTO car(carNumber, carLocation, carStatus, parkingMeterStatus) VALUES ('TRTRTRT', 'OUTSIDE', 'VIP', 'OFF');

INSERT INTO parkingMeter(carNumber, createdAt, stoppedAt, cost) VALUES ('AAAAAB222', '2017-07-04 12:08:56',
                                                                        '2017-07-04 12:18:56', '0');

INSERT INTO parkingMeter(carNumber, createdAt, stoppedAt, cost) VALUES ('BBBBBSSS', '2017-07-04 15:08:56',
                                                                        '2017-07-04 17:18:56', '2.4');
INSERT INTO parkingMeter(carNumber, createdAt, stoppedAt, cost) VALUES ('BBBBBSSS', '2017-07-05 12:08:56',
                                                                        '2017-07-05 12:18:56', '1');
INSERT INTO parkingMeter(carNumber, createdAt, stoppedAt, cost) VALUES ('WERTVFR', '2017-07-14 12:08:56',
                                                                        '2017-07-14 12:58:56', '1');
INSERT INTO parkingMeter(carNumber, createdAt, stoppedAt, cost) VALUES ('AAAAAB222', '2017-08-04 12:00:56',
                                                                        '2017-08-04 13:18:56', '1');
INSERT INTO parkingMeter(carNumber, createdAt, stoppedAt, cost) VALUES ('WERTVFR', '2017-08-04 13:00:56',
                                                                        '2017-08-04 13:18:56', '1');
INSERT INTO parkingMeter(carNumber, createdAt, stoppedAt, cost) VALUES ('WWWWW', '2017-08-05 13:01:56',
                                                                        '2017-08-05 14:38:56', '2');
INSERT INTO parkingMeter(carNumber, createdAt, stoppedAt, cost) VALUES ('AAAAAB222', '2017-08-05 12:00:56',
                                                                        '2017-08-05 12:48:56', '0');
INSERT INTO parkingMeter(carNumber, createdAt, stoppedAt, cost) VALUES ('RRRR', '2017-08-14 09:04:50',
                                                                        '2017-08-14 10:18:56', '2');