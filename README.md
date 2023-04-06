Repo for EECS3311 W23 Project

 [Wiki](https://github.com/TimothyLai77/eecs-3311-project/wiki)

## Iteration 3
Almost all of the planned stories from iteration 0 have been implemented. We added icons to the ordering page for customers with dietary restrictions. We added ratings so the customers can leave their order feedback after they place the order. Finally we added randomized discounts. The manager can set the discount in the manager view page, and the customer will just get the discount applied at random. 
In addition issues brought up from our QA have also been resolved. We have implemented most of their feedback, although we couldn't fix one of their issues.

### Documentation 

[System Architecture Sketch](https://raw.githubusercontent.com/TimothyLai77/eecs-3311-project/main/Documentation/Iteration%203/System_Architecure_Sketch.pdf)

[UML Class Diagram](https://raw.githubusercontent.com/TimothyLai77/eecs-3311-project/main/Documentation/Iteration%203/UML%20Class%20DIagrams.drawio.pdf)

[Refactoring Report](https://raw.githubusercontent.com/TimothyLai77/eecs-3311-project/main/Documentation/Iteration%203/Refactor%20Writeup.pdf)

[Addressing our issues](https://raw.githubusercontent.com/TimothyLai77/eecs-3311-project/main/Documentation/Iteration%203/EECS%203311%20-%20QA%20Bug%20Fix%20Report.pdf)

[QA report for Team2](https://raw.githubusercontent.com/TimothyLai77/eecs-3311-project/main/Documentation/Iteration%203/Bug%20Report%20List%20for%20Team2.pdf)

[Retrospective writeup](https://raw.githubusercontent.com/TimothyLai77/eecs-3311-project/main/Documentation/Iteration%203/Software%20Design%20Retrospective-1.pdf)


### Running the App
* Download the VirtualRestaurantAssistant.jar from the root folder
* Setup the SQL Database (see below)
* Run the .jar file 
   * Note: At least Java 18 might be needed to run the file.

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




## Iteration 2
### Documentation

[System Architecture Sketch](https://raw.githubusercontent.com/TimothyLai77/eecs-3311-project/main/Documentation/Iteration%202/updated%20sas.png).

[Updated UML Diagram](https://raw.githubusercontent.com/TimothyLai77/eecs-3311-project/main/Documentation/Iteration%202/iteration2_uml_diagram.pdf)

[Original Iteration 2 Stories and Developer Tasks](https://raw.githubusercontent.com/TimothyLai77/eecs-3311-project/main/Documentation/Iteration%202/iteration2_original_stories%20.png)

[Updated Iteration 2 Stories and Developer Tasks](https://raw.githubusercontent.com/TimothyLai77/eecs-3311-project/main/Documentation/Iteration%202/iteration2_updated_stories_with_client_feature.png) 

### Running the App
* Download the `VirtualRestaurantAssistant.jar` from the root folder
* Setup the SQL Database (see below)
* Run the .jar file
   * Note: At least Java 18 might be needed to run the file.
   * Also needs JUnit5 for the tests



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
