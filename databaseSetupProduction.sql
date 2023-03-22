/*This is the script for the production version of the app (actual database instance).
Create your database using this script and the credentials below:

String driver = "com.mysql.cj.jdbc.Driver";
String url = "jdbc:mysql://127.0.0.1:3306/3311project";
String username = "root";
String password = "root1234";
*/

CREATE DATABASE 3311project;
USE 3311project;

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

INSERT INTO INGREDIENTS(ingredient_name, ingredient_type, quantity, price) VALUES('Bread','bread',100,3);
INSERT INTO INGREDIENTS(ingredient_name, ingredient_type, quantity, price) VALUES('Beef','meat',100,6.9);
INSERT INTO INGREDIENTS(ingredient_name, ingredient_type, quantity, price) VALUES('Chicken','meat',100,4.25);
INSERT INTO INGREDIENTS(ingredient_name, ingredient_type, quantity, price) VALUES('Meatball','meat',600,0.75);
INSERT INTO INGREDIENTS(ingredient_name, ingredient_type, quantity, price) VALUES('Veggiepatty','vegetable',100,5);

INSERT INTO INGREDIENTS(ingredient_name, ingredient_type, quantity, price) VALUES('Tomato','vegetable',100,1.25);
INSERT INTO INGREDIENTS(ingredient_name, ingredient_type, quantity, price) VALUES('Lettuce','vegetable',100,3.10);
INSERT INTO INGREDIENTS(ingredient_name, ingredient_type, quantity, price) VALUES('Ketchup','sauce',100,0.50);
INSERT INTO INGREDIENTS(ingredient_name, ingredient_type, quantity, price) VALUES('Mayo','sauce',100,0.75);
INSERT INTO INGREDIENTS(ingredient_name, ingredient_type, quantity, price) VALUES('Cheddar','cheese',100,5);
INSERT INTO INGREDIENTS(ingredient_name, ingredient_type, quantity, price) VALUES('American','cheese',100,2.50);