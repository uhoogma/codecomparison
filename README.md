# Code Comparison

## Final project that attempts to bring together
* acquiring data from Moodle (through OUnit)
* abstracting code using work from repo https://github.com/uhoogma/codesimplifier.git
* comparing code using work from repo https://github.com/uhoogma/codesimilarity.git
* presenting findings to user through Spring web application

Project is not meant to be deployed to web but used locally in one computer as so called "fat client".
MySql database must also be installed in computer that wants to use this project.

## Target audience:
Instructors who have access to Moodle's programming courses that are being evaluated using OUnit testing system.

## TaskList:

1. DONE - update Entity-Relationship Model and database table script
2. DONE - CRUD operations for Round
3. FINISH - CRUD operations for Task
4. DONE - display several rounds in one task in index view
5. DONE - either make Selenium Webdriver work properly or make sure it will gracefully degrade in case of an error
6. TODO - user feedback
7. DONE - save data fetched with help of Selenium Webdriver into database
8. DONE - include codesimplifier and codesimilarity into this project
9. DONE - make analysis work
10. TODO - make that in case of round or task deletion only relevant data is deleted (Cascade only when necessary)
11. FINISH - CSS and HTML cleanup
