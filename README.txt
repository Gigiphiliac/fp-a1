Welcome to my A1 project.
Clayton Phillips - s3952384

This program provides the following functionalities:
 - Search a course database using a keyword.
 - Enrol in courses from keyword matches.
 - Show all enrolled courses.
 - Withdraw from enrolled courses.
 - Exit the program.


To run the MyTimetable program's Main and test classes, follow these steps:
 1) Navigate to the folder where the 'fp-a1' folder has been downloaded
 2) Copy the full directory path
 3) Open cmd and type 'cd [copied dir path]'
 4) Enter the folder by typing 'cd fp-a1'
 5) Locate your junit and hamcrest .jar files (could look like 'C:\Users\xxx\plugins\org.junit_4.13.2.v20211018-1956.jar' and 'C:\Users\xxx\plugins\org.hamcrest.core_1.3.0.v20180420-1519.jar')
 6) Use these commands on separate consecutive lines. These will a) compile the test classes, b) run the compiled program, c)-d) run the compiled test classes
  a) javac -cp [junit jar filepath] ./src/*.java
  b) java -cp ./src Main
  b) java -cp [junit jar filepath];[hamcrest jar filepath];./src;. org.junit.runner.JUnitCore MyTimetableTest
  c) java -cp [junit jar filepath];[hamcrest jar filepath];./src;. org.junit.runner.JUnitCore ToolboxTest


To run the sample program on Canvas, follow these steps:
 1) Navigate to the folder where the 'SampleConsoleProgram' folder has been downloaded
 2) Copy the full directory path
 3) Open cmd and type 'cd [copied dir path]'
 4) Enter the folder by typing 'cd SampleConsoleProgram'
 5) Use these commands on separate consecutive lines. These will a) compile the program, b) run the compiled program
  a) javac ./src/console/program/*.java
  b) java -cp ./src console.program.Main


To run the sample program's test classes, follow these steps:
 1) Navigate to the folder where the 'SampleConsoleProgramWithUnitTesting' folder has been downloaded
 2) Copy the full directory path
 3) Open cmd and type 'cd [copied dir path]'
 4) Enter the folder by typing 'cd SampleConsoleProgramWithUnitTesting'
 5) Locate your junit and hamcrest .jar files (could look like 'C:\Users\xxx\plugins\org.junit_4.13.2.v20211018-1956.jar' and 'C:\Users\xxx\plugins\org.hamcrest.core_1.3.0.v20180420-1519.jar')
 6) Use these commands on separate consecutive lines. These will a) compile the test class, b) run the compiled test class
  a) javac -cp [junit jar filepath] ./src/console/program/*.java
  b) java -cp [junit jar filepath];[hamcrest jar filepath];./src;. org.junit.runner.JUnitCore console.program.RestaurantTest