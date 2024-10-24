CREATE DATABASE turist DEFAULT CHARACTER SET utf8mb4;

USE turist;

CREATE TABLE CITY(
                     city_id int auto_increment,
                     city_name VARCHAR(30),
                     PRIMARY KEY(city_id)
);

CREATE TABLE ATTRACTION(
                           attraction_id int auto_increment,
                           name VARCHAR(30),
                           description VARCHAR(50),
                           fee int,
                           city_id int,
                           PRIMARY KEY(attraction_id),
                           FOREIGN KEY(city_id) REFERENCES CITY(city_id) ON DELETE CASCADE
);

CREATE TABLE TAG(
                    tag_id int auto_increment,
                    tag_name VARCHAR(30),
                    PRIMARY KEY(tag_id)
);

CREATE TABLE ATTRACTION_TAG(
                               attraction_id int,
                               tag_id int,
                               PRIMARY KEY(attraction_id, tag_id),
                               FOREIGN KEY(attraction_id) REFERENCES ATTRACTION(attraction_id) ON DELETE CASCADE,
                               FOREIGN KEY(tag_id) REFERENCES TAG(tag_id)
);