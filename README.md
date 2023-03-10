Repo for EECS3311 W23 Project

 [Wiki](https://github.com/TimothyLai77/eecs-3311-project/wiki)

## Iteration 2
### MySQL database setup
There are two sql scripts provided, both will create the database the app will use and create the tables. databaseSetupWithIngredients.sql will populate the tables with data, so the user can just order a sandwich. The other script will only make the tables, and the user must go to the manager page (bottom right of home screen)  within the app to add ingredients. 

* [SQL Script No Ingredients](https://raw.githubusercontent.com/TimothyLai77/eecs-3311-project/main/databaseSetupNoIngredients.sql)*

* [SQL Script With Ingredients](https://raw.githubusercontent.com/TimothyLai77/eecs-3311-project/main/databaseSetupWithIngredients.sql)*

The app is configured to use the database with the following credentials:
* Username: `root`
* password: `root1234`



\* The easiest way to run the setup scripts is to open MySQL Workbench. Navigate to File > Open SQL Script... > select the desired script > open > execute 
