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
    email_password VARCHAR(25),
    user_points INTEGER
);

CREATE TABLE IF NOT EXISTS car_type (
	id INTEGER PRIMARY KEY,
    type_name VARCHAR(20),
    price_booking FLOAT(6,2),
    price_per_km FLOAT(6,2),
    capacity INTEGER
);

CREATE TABLE IF NOT EXISTS car (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    car_name VARCHAR(20),
    photo_url VARCHAR(16000),
    car_type_id INTEGER,
    location_id VARCHAR(200),
    is_active INTEGER,
    FOREIGN KEY (car_type_id) REFERENCES car_type (id)

);

CREATE TABLE IF NOT EXISTS drive_order (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	origin_id VARCHAR(200),
	destination_id VARCHAR(200),
	car_id INTEGER,
	price FLOAT(8,2),
	user_id VARCHAR(30),

    FOREIGN KEY (car_id) REFERENCES car (id),
    FOREIGN KEY (user_id) REFERENCES user (email)
);

