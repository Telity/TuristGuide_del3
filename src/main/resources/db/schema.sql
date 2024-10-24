
insert into CITY (city_name) values ('København');
insert into CITY (city_name) values ('Paris');
insert into CITY (city_name) values ('New York');

insert into ATTRACTION (name, description, fee, city_id) values ('Den Lille Havfrue', "En lille statue nede ved vandet", 0, 1);
insert into ATTRACTION (name, description, fee, city_id) values ('Eiffel Tower', "Et stort tårn i midten af Paris", 100, 2);
insert into ATTRACTION (name, description, fee, city_id) values ('Frihedsgudinden', "Symbol på fred og frihed", 0, 3);

insert into TAG (tag_name) values ('Historisk');
insert into TAG (tag_name) values ('Mytisk');
insert into TAG (tag_name) values ('Gammelt');
insert into TAG (tag_name) values ('Æstetisk');
insert into TAG (tag_name) values ('Moderne');
insert into TAG (tag_name) values ('Nyt');
insert into TAG (tag_name) values ('Guddommeligt');

insert into ATTRACTION_TAG (attraction_id, tag_id) values (1, 1);
insert into ATTRACTION_TAG (attraction_id, tag_id) values (1, 3);
insert into ATTRACTION_TAG (attraction_id, tag_id) values (2, 1);
insert into ATTRACTION_TAG (attraction_id, tag_id) values (2, 3);
insert into ATTRACTION_TAG (attraction_id, tag_id) values (2, 4);
insert into ATTRACTION_TAG (attraction_id, tag_id) values (2, 5);
insert into ATTRACTION_TAG (attraction_id, tag_id) values (3, 2);
insert into ATTRACTION_TAG (attraction_id, tag_id) values (3, 7);

COMMIT;

select * from ATTRACTION_TAG ;
select * from ATTRACTION;
select * from ATTRACTION where name = "Eiffel Tower" ;
SELECT tag_name FROM TAG
                         JOIN ATTRACTION_TAG AT ON TAG.tag_id = AT.tag_id
    JOIN ATTRACTION A ON A.attraction_id = AT.attraction_id
WHERE A.name = "Eiffel Tower";
select * from ATTRACTION where fee = 100;


