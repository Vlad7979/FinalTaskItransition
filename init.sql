INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

SELECT * FROM roles;

SELECT * FROM users;

select * from user_roles;

truncate table user_roles;

truncate table users cascade;

insert into fandoms(name, image) VALUES ('Голодные игры', 'https://fanfics.me/images/fandoms_avatars/wallpaper/4.jpg');
insert into fandoms(name, image) VALUES ('Звездные войны', 'https://fanfics.me/images/fandoms_avatars/wallpaper/28.jpg');
insert into fandoms(name, image) VALUES ('Очень странные дела', 'https://fanfics.me/images/fandoms_avatars/wallpaper/754-1496659436.jpg');
insert into fandoms(name, image) VALUES ('Марвел', 'https://fanfics.me/images/fandoms_avatars/wallpaper/14.jpg');
insert into fandoms(name, image) VALUES ('Властелин колец', 'https://fanfics.me/images/fandoms_avatars/wallpaper/25.jpg');
insert into fandoms(name, image) VALUES ('Гарри Поттер', 'https://fanfics.me/images/fandoms_avatars/wallpaper/2.jpg');

select * from pg_stat_activity where usename = 'dfeyceqwntpvrc';

alter system set idle_in_transaction_session_timeout='1min';

SET SESSION idle_in_transaction_session_timeout = '1min';

show idle_in_transaction_session_timeout;

SET SESSION idle_in_transaction_session_timeout = 0;


