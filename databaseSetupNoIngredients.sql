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

