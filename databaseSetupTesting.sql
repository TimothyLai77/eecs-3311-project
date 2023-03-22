/*This is the script for integration testing of database.
Create your testing database using this script and the credentials below:

String driver = "com.mysql.cj.jdbc.Driver";
String url = "jdbc:mysql://127.0.0.1:3306/3311test";
String username = "root";
String password = "root1234";
*/

CREATE DATABASE 3311test;
USE 3311test;

create table INGREDIENTS (
    ingredient_name        VARCHAR(40)        PRIMARY KEY     NOT NULL,
    ingredient_type     ENUM('bread', 'meat', 'cheese', 'vegetable', 'sauce', 'other')     NOT NULL,
    quantity            INT        NOT NULL  DEFAULT 0    CHECK (quantity >= 0),
    price                DECIMAL(10, 2)        NOT NULL
);

create table ORDERS (
    order_total        DECIMAL(10, 2)    NOT NULL,
    order_id        VARCHAR(9)        NOT NULL     PRIMARY KEY,
    order_date        DATE
);

create table favourites (
    sandwichBase        VARCHAR(30)        NOT NULL     PRIMARY KEY,
    counts                INT         NOT NULL    DEFAULT 0
);

create table ratings (
    score		INT		NOT NULL,
    feedback	VARCHAR(300),
    ROrder_id	VARCHAR(9)		NOT NULL 	PRIMARY KEY
);


create table manager (
    passcode VARCHAR(9) NOT NULL UNIQUE
);