DROP DATABASE IF EXISTS texi;

CREATE DATABASE texi;

USE texi;

CREATE TABLE IF NOT EXISTS discount (
	id VARCHAR(10) PRIMARY KEY,
    percent FLOAT(3,2)
);

CREATE TABLE IF NOT EXISTS user (
	email VARCHAR(30) PRIMARY KEY,
	first_name VARCHAR(20),
    last_name VARCHAR(20),
    email_password VARCHAR(25)
);

CREATE TABLE IF NOT EXISTS user_points (
	user_email VARCHAR(30),
	amount INTEGER
);

CREATE TABLE IF NOT EXISTS car_type (
	id INTEGER PRIMARY KEY,
    type_name VARCHAR(20),
    price_booking FLOAT(6,2),
    price_per_km FLOAT(6,2),
    capacity INTEGER
);

CREATE TABLE IF NOT EXISTS car (
	id INTEGER PRIMARY KEY,
    car_name VARCHAR(20),
    photo_url VARCHAR(16000),
    car_type_id INTEGER,

    FOREIGN KEY (car_type_id) REFERENCES car_type (id)

);

CREATE TABLE IF NOT EXISTS car_location (
    car_id INTEGER PRIMARY KEY,
    location_id VARCHAR(200),

    FOREIGN KEY (car_id) REFERENCES car (id)
);

CREATE TABLE IF NOT EXISTS car_active (
	car_id INTEGER PRIMARY KEY,

    FOREIGN KEY (car_id) REFERENCES car (id)
);

CREATE TABLE IF NOT EXISTS drive_order (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	origin_id VARCHAR(200),
	destination_id VARCHAR(200),
	car_id INTEGER,
	price FLOAT(8,2),

    FOREIGN KEY (car_id) REFERENCES car (id)
);

