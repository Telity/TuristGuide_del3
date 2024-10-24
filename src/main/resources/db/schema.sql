
insert into CITY (city_name) values
('København'),
('Paris'),
('New York');

insert into ATTRACTION (name, description, fee, city_id) values
('Den Lille Havfrue', "En lille statue nede ved vandet", 0, 1),
('Eiffel Tower', "Et stort tårn i midten af Paris", 100, 2),
('Frihedsgudinden', "Symbol på fred og frihed", 0, 3);

insert into TAG (tag_name) values
('Historisk'),
('Mytisk'),
('Gammelt'),
('Æstetisk'),
('Moderne'),
('Nyt'),
('Guddommeligt');

insert into ATTRACTION_TAG (attraction_id, tag_id) values
(1, 1),
(1, 3),
(2, 1),
(2, 3),
(2, 4),
(2, 5),
(3, 2),
(3, 7);

INSERT INTO City (name) values
("København"),
("Paris"),
("Odense"),
("Berlin"),
("Luxenborg"),
("Kairo"),
("Cape town");

COMMIT;


