Repo for EECS3311 W23 Project

 [Wiki](https://github.com/TimothyLai77/eecs-3311-project/wiki)

## Iteration 2
### MySQL database setup
There are two sql scripts provided, both will create the database the app will use and create the tables. 

databaseSetupProduction.sql will populate the tables with data, so the user can just order a sandwich. To manually add ingredients, run the above script without the `insert into` queries, then go to the manager page (bottom right of home screen)  within the app to add ingredients. 

The other script (databaseSetupTesting.sql) is used for integration testing. To set up database, check the comment section within the script for credentials. 

* [SQL Script Production](https://raw.githubusercontent.com/TimothyLai77/eecs-3311-project/main/databaseSetupProduction.sql)*

* [SQL Script Testing](https://raw.githubusercontent.com/TimothyLai77/eecs-3311-project/main/databaseSetupTesting.sql)*

The app is configured to use the database with the following credentials:
* Username: `root`
* password: `root1234`



\* The easiest way to run the setup scripts is to open MySQL Workbench. Navigate to File > Open SQL Script... > select the desired script > open > execute 
