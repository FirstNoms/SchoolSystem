create database ileiwedb;
create user 'userSchool'@'localhost'identified by 'iwe111';
grant all privileges on ileiwedb.* to 'userSchool'@'localhost';
flush privileges;